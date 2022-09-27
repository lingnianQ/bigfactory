#需求: 用户行为日志的记录

## 何为用户行为日志?
谁在什么时间,执行了什么操作,访问了什么方法,传递了什么参数,耗时多少,最后的状态如何等
## 如何进行表的设计?
```
drop table if exists log;
create table log(
id bigint auto_increment comment '自增id',
username varchar(100) default '' comment '登录用户',
ip varchar(50) not null comment '用户ip',
created_time datetime default  current_timestamp comment '操作时间',
opertion varchar(50) default '' comment '操作名称',
method varchar(100) not null comment '方法的方法',
params varchar(200) default '',
time bigint not null,
status tinyint(1) not null,
error varchar(200) default '',
primary key (id)
)engine=InnoDB character set utf8mb4;
create index index_username on log (username);
create index index_created_time on log(created_time);
```
## 获取并记录日志的方案是什么?
1)基于AOP方式获取用户行为日志
2)基于数据库表存储获取到的日志

## 代码落地实现?
1)创建一个POJO对象Log用于封装用户行为日志.
2)创建数据层Mapper接口(LogMapper),定义记录日志的方法(insert操作)
3)创建业务层Service接口(LogService)以及实现类.
4)创建一个注解(@RequiredLog),通过注解描述目标业务方法,将此方法声明为日志切入点方法?
5)创建一个切面对象(LogAspect),通过对象定义切入点表达式,通知方法.
6)在日志切面通知方法中调用日志业务对象进行日志记录.

##代码落地及运行时遇到了什么?
假如系统访问量比较大,很多方法都需要进行日志记录,但日志记录的过程
是默认基于tomcat的线程进行同步执行的.我们知道tomcat线程一般用于
处理客户端的请求,当大部分线程都去执行日志记录时,没有线程可以处理
客户端请求了,此时就会出现用户请求阻塞,带来很不好的用户体验.

##遇到如上问题是如何解决的?
核心思想:将日志记录动作放在异步线程去执行.
落地方案
1)自己创建线程,去执行日志记录.(频繁创建和销毁线程会带来很大系统开销)
2)基于Spring自带的线程池,然后基于AOP方式异步记录日志.
2.1)配置线程池
2.2)启动并初始化线程池
2.3)应用池中的线程执行异步任务

##假如并发量确实比较大,有没有更好的方案?
借助MQ进行流量削峰,先将日志输出到MQ,再将MQ日志写入到数据库.










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
1)基于AOP方式获取日志
2)基于数据库表存储获取到的日志

## 代码落地实现?







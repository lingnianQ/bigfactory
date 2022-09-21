#查看数据库的版本
select version();
#查看数据库引擎
show engines;
#查看数据库可以支持的最大连接数
show variables like '%max_connections%';
#设置mysql最大连接数
set global max_connections=200;

################################
help 'bigint';
help 'Data Types';

################################
help 'functions';
help 'Date and Time Functions';

################################

##查看字符集
show variables like 'collation%';

create database if not exists `dbtest`
    default character set utf8mb4
    collate utf8mb4_unicode_ci;

use `dbtest`;
drop table if exists dept;
create table `dept`
(
    `id` bigint not null auto_increment,
    `name` varchar(30) default '' comment '部门名称',
    primary key (id)
)engine=innodb default character set utf8mb4;

drop table if exists employee;
create table employee
(
    `id` bigint not null auto_increment,
    `name` varchar(30) default '' comment '姓名',
    `gender` enum('Male','Female') default 'Male',
    `dept_id` bigint,
    primary key (id)
)engine=innodb default  character set utf8mb4;

## 添加外键物理约束
alter table employee add foreign key(`dept_id`) references dept(`id`);

## 查询information_schema库的tables中名字为dbtest的数据库中有哪些表?

SELECT COUNT(*) TABLES, table_schema
FROM information_schema.TABLES
WHERE table_schema = 'dbtest'
GROUP BY table_schema;

###################################
#数据库表设计范式(范式与反范式)
###################################
#第一范式:列(字段)不可再分,要具备原子性.
#例如学生有一个姓名,这个姓名可以再分为名字和性,假如按第一范式设计可以这样:

create table student
(id bigint not null auto_increment,
 first_name varchar(50) not null comment '名',
 last_name varchar(30) not null comment '姓',
 mobile varchar(11) not null comment '手机号',
 primary key (id)
)engine=innoDB default character set utf8mb4;

#第二范式:首先要满足第一范式,同时不能存在非主属性(非主键)对主属性(相当于是主键)的部分依赖.

#如下表的设计是否满足第二范式?不满足,因为cname只依赖于cid,它并不依赖于sid.
create table score
(
    sid bigint not null comment '学生id',
    cid bigint not null comment '课程id',
    score smallint default 0 comment '成绩',
    cname varchar(100) default '' comment '课程名',
    primary key (sid,cid)
);
# 第三范式:首先满足第二范式,然后不能存在非主属性对主键属性的传递依赖.
#如下表的设计是否满足第三范式?(不满足,school_phone依赖于school_name,school_name依赖于id)
#解决方案:专门创建一张学校表,将学校名和学校电话放到学校表,在教师表中添加一个学校id
create table teacher
(id bigint not null auto_increment,
 first_name varchar(50) not null comment '名',
 last_name varchar(30) not null comment '姓',
 mobile varchar(11) not null comment '手机号',
 school_id bigint not null,
 school_name varchar(50) not null comment '学校名',
 school_phone varchar(10) not null comment '学校电话',
 primary key (id)
)engine=innoDB default character set utf8mb4;


#反范式:这种范式一般是为了提高查询的效率,在表中添加一些冗余字段
#例如,如下表中的school_name就为冗余字段(我们可以直接通过单表查询,就可找到老师在哪个学校任职)
create table teacher
(id bigint not null auto_increment,
 first_name varchar(50) not null comment '名',
 last_name varchar(30) not null comment '姓',
 mobile varchar(11) not null comment '手机号',
 school_id bigint not null,
 school_name varchar(50) not null comment '学校名',
 primary key (id)
)engine=innoDB default character set utf8mb4;

#但是这里有一个问题,假如对学校做了更新,更新了学校名称,但没有更新教师表,此时两个表中的数据可能就不一致了.
create table school(
 id bigint not null auto_increment,
 school_name varchar(50) not null comment '学校名',
 school_phone varchar(10) not null comment '学校电话',
 primary key (id)
)engine=innoDB default character set utf8mb4;

use hr;
show tables;
select employee_id,manager_id from employees;

######################################
# SQL 练习
#####################################
#1.查询雇员编号为206的雇员,他的经理人名字以及薪水?
##方案1:嵌套查询
select first_name,salary
from employees
where employee_id=(
    select manager_id
    from employees
    where employee_id=206);

##方案2:多表自关联
select m.first_name,m.salary
from employees e join employees m
                      on e.manager_id=m.employee_id
where e.employee_id=206;

#2.查询每个雇员的薪资,并显示薪资级别(例如 >10000的高,8000~10000的中等,<8000的低)

select first_name,salary,(case  when salary>10000 then '高'
                                when salary>8000 then '中'
                                else '低'
    end) level
from employees;

#3.统计出薪资大于等于10000和薪资小于10000的雇员人数

##方案1
select
    sum(case  when salary>=10000 then 1 else 0 end) '大于等于10000',
        sum(case  when salary<10000  then 1 else 0 end) '小于10000'
from employees;

##方案2:
select (case when salary>=10000 then '大于等于10000'
             else '小于10000' end) 'salary_level',count(*)
from employees
group by salary_level;

#4.求平均工资最低的那个部门的名字以及平均薪水?


#5.求每个雇员的名字,他所在部门的名字以及这个部门所在城市?


#6.对雇员表进行分页查询,每页最多显示10条,查询第二页数据,并按工资进行降序排序?







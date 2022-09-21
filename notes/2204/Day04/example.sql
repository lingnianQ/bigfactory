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
 school_name varchar(50) not null comment '学校名',
 school_phone varchar(10) not null comment '学校电话',
 primary key (id)
)engine=innoDB default character set utf8mb4;










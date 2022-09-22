#打开数据库
use dbtest;
#创建表
drop table if exists oper_log;
create table oper_log(
    id bigint not null  auto_increment comment '主键',
    username varchar(50) not null comment '登录用户',
    ip varchar(50) not null comment '访问ip',
    created_time datetime not null comment '访问时间',
    operation varchar(50) default '' comment '具体操作',
    method varchar(100) not null comment '访问的方法',
    params varchar(100) default '' comment '传递的参数',
    status tinyint default '1' comment '操作状态',
    error varchar(200) default '' comment '错误信息',
    index index_username (username),
    primary key (id)
)engine=InnoDB default character set utf8mb4;
#添加普通索引
create index index_ip on oper_log(ip);
alter table oper_log add index index_created_time (created_time);

#创建雇员表
drop table if exists employee;
create table employee
(
    `id` bigint not null auto_increment,
    `name` varchar(30) default '' comment '姓名',
    `gender` enum('Male','Female') default 'Male',
    `phone` varchar(20) not null comment '手机号',
    `email` varchar(50) default '' comment '邮箱',
    `dept_id` bigint,
     UNIQUE index_phone (phone),
     primary key (id)
)engine=innodb default  character set utf8mb4;
#创建唯一索引
create unique index index_email on employee(email);

#向employee表中添加数据并查询
insert into employee (id,name,gender,phone,dept_id)
values  (null,'A','Male','111',101),(null,'A','Male','112',102);

select * from employee;

# 创建组合索引
create index index_username_created_time on oper_log(username,created_time);

# 创建前缀索引
create index index_error on oper_log(error(5));

#显示索引
show index from oper_log;

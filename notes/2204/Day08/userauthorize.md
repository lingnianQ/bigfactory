#需求
对不同登录用户进行不同授权,然后用户基于自己的权限进行资源访问.
#如何进行表设计
1)菜单表(菜单表-菜单是用户资源的外在表现形式)
2)角色表(不同用户会有不同角色,例如产品经理,产品专员,....)
3)用户表(我们的权限控制是基于用户资源访问的权限控制)
4)部门表(用户的归属,在企业中每个用户都有它所属的部门或组织)
5)日志表(用户记录用户行为日志)
6)菜单角色关系表(菜单和角色是多对多关系)
7)角色和用户关系表(角色和用户是多对多的关系)
```
drop database if exists dblog;
create database dblog character set utf8mb4 collate utf8mb4_general_ci;
use dblog;
```

```
-- 菜单表
create table tb_menu
(
id int auto_increment primary key,
name varchar(20) not null comment '菜单名',
parent_id int comment '上级菜单',
sort smallint default 0 comment '排序',
status tinyint(1) default 1 comment '状态',
remark varchar(200) default '' comment '描述',
created_time datetime default current_timestamp comment '创建时间',
modified_time datetime default current_timestamp comment '创建时间',
created_by varchar(100) default '' comment '创建用户',
modified_by varchar(100) default '' comment '修改用户'
)engine=InnoDB default character set utf8mb4;
```

```
-- 角色表
create table tb_role
(
id int auto_increment primary key,
name varchar(20) not null comment '角色名',
status tinyint(1) default 1 comment '状态',
remark varchar(200) default '' comment '描述',
created_time datetime default current_timestamp comment '创建时间',
modified_time datetime default current_timestamp comment '创建时间',
created_by varchar(100) default '' comment '创建用户',
modified_by varchar(100) default '' comment '修改用户'
)engine=InnoDB default character set utf8mb4;;
```

```
-- 角色菜单关系表
create table tb_role_menu
(
role_id int comment '角色id',
menu_id int comment '菜单id',
primary key (role_id,menu_id)
)engine=InnoDB default character set utf8mb4;;
```
```
-- 用户表
create table tb_user
(
id int auto_increment primary key,
username varchar(50) not null comment '用户名',
password varchar(200) not null comment '密码',
phone varchar(20) not null comment '手机号',
email varchar(200) default '' comment '邮箱',
image varchar(100) default '' comment '头像',
status tinyint(1) default 1 comment '状态',
department_id int,
created_time datetime default current_timestamp comment '创建时间',
modified_time datetime default current_timestamp comment '创建时间',
created_by varchar(100) default '' comment '创建用户',
modified_by varchar(100) default '' comment '修改用户'
)engine=InnoDB default character set utf8mb4;;
```
```
-- 用户角色关系表

create table tb_role_menu(
user_id int comment '用户id',
role_id int comment '角色id',
primary key (role_id,user_id)
)engine=InnoDB default character set utf8mb4;;
```
```
-- 部门表
create table tb_department
(
id int auto_increment primary key,
name varchar(20) not null comment '部门名',
parent_id int comment '上级部门',
sort smallint default 0 comment '排序',
status tinyint(1) default 1 comment '状态',
remark varchar(200) default '' comment '描述',
created_time datetime default current_timestamp comment '创建时间',
modified_time datetime default current_timestamp comment '创建时间',
created_by varchar(100) default '' comment '创建用户',
modified_by varchar(100) default '' comment '修改用户'
)engine=InnoDB default character set utf8mb4;
```
```
-- 日志表
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

#权限控制方案设计
1)添加用户后对用户进行授权(将菜单访问权限授予角色,再将角色授予用户)
2)用户访问资源时先进行认证
3)用户认证通过访问资源需要鉴权
3.1)自己通过AOP写切面,在切面通知方法中进行鉴权.
3.2)基于市面的认证安全框架进行实现(Spring Security或者Shiro)








# 数据库技术增强

## 核心要点

* 常见术语
* 数据库及表的设计
* 索引的设计
* 数据库中的事务、锁应用

## 数据库常见术语

* DB (Database):数据库
* DBMS(Database Management System)：数据库管理系统
* SQL(Structured Query Language )：结构化的查询语言

## 数据库的设计

* 数据库名字(规范)
* 数据库的字符集(utf8mb4/utf8mb4_general_ci)
* 数据库中表的数量(information_schema.tables)

**常见问题分析(FAQ)**

* MySQL中创建数据库的语法是怎样的？

通过 help 'create database' 方式查看创建数据库的语法。

```
CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name
[create_specification] ...

create_specification:
[DEFAULT] CHARACTER SET [=] charset_name
| [DEFAULT] COLLATE [=] collation_name

```

* MySQL如何基于语法创建数据库？

```
CREATE DATABASE IF NOT EXISTS JSDTN2205  CHARACTER SET  utf8mb4;
```
```
CREATE DATABASE IF NOT EXISTS JSDVN2205  COLLATE  utf8mb4_general_ci;
```

* 如何查看Mysql中自带的字符集？

```
show variables like '%collation_%'
```

* 如何删除MySQL中的数据库？

首先通过 help 'drop database' 方式查询删除数据库的语法。

```
DROP {DATABASE | SCHEMA} [IF EXISTS] db_name
```
基于语法实现数据库的删除

```
DROP DATABASE IF EXISTS JSDTN2205;
```

* 如何打开数据库?

```
use JSDTN2205;
```

* 如何查看数据库中有哪些表？

```
show tables;
```

* 如何统计指定数据库中有多少张表？(mysql5.7)

```

select table_schema,count(*) tables
from information_schema.tables
where table_schema='JSDVN2205'
group by table_schema;

```

## 数据库中表的设计

* 语法
* 表的名字
* 表中字段的类型
* 表中字段常用约束
* 表中字段的默认值(default)
* 表中字段的描述(含义-comment)  
* 表中字段的数量(宽表/窄表)
* 表的设计范式与反范式

**常见问题分析**

* 如何查看创建表的语法？

```
 help 'create table'
```

案例分析：打开JSDTN2205数据，然后在库中创建学生表(student)

```
use jsdtn2205
```

```
create table if not exists student
(
id bigint auto_increment,
first_name varchar(50) not null comment '学生名字',
last_name varchar(20) not null comment '学生姓',
phone varchar(15) not null comment '手机号',
birthday date comment '出生日期',
create_time datetime default current_timestamp comment '注册日期',
primary key (id),
unique key (phone)
)engine = InnoDB character set utf8mb4;
```

* MySql中常用的数据类型有哪些？

1. 字符串类型 (char,varchar,text,...)
2. 日期/时间类型(date,time,datetime,timestamp,...)
3. 数值类型(tinyint,int,bigint,decimal,...)
4. 二进制类型(blob,mediumblob,longblob,...)
5. 其它(enum,set,json,...)

* MySQL中数据类型的应用有什么原则吗？

1. 尽量选择简单数据类型(例如存储整数用int不用varchar)
2. 尽量使用最小数据类型(例如能用tinyint不用int)
3. 假如要存储小数可以考虑使用decimal类型。
4. 尽量避免使用text、blob等大字段类型(假如需要使用则尽量放到一张表中)

* MySql表中常用的字段约束有哪些？

1. 非空约束(not null)：字段的值不允许为空
2. 主键约束(primary key)：字段值不允许为空并且唯一
3. 唯一约束(unique key)：字段值必须唯一
4. 检查约束(check)：字段值需要在指定范围(但是数据库之间的兼容不好)
5. 外键约束(foreign key)：字段值需要参考引用表中的字段值。

案例应用(创建课程分类表category,课程表course并相关约束进行应用)

```
drop table if exists category;

create table if not exists category(
id int auto_increment comment '主键值',
category_name varchar(100) not null comment '分类名称',
primary key (id)
)engine=InnoDB character set utf8mb4;

```

```
drop table if exists course;

create table if not exists course ( 
id bigint auto_increment comment '主键id',
name varchar(100) not null comment '课程名称',
credit tinyint not null  comment '学分',
category_id int comment '分类id',
check (credit between 0 and 100),
primary key (id),
unique key (name),
foreign key (category_id) references category (id)
)engine=InnoDB character set utf8mb4;

```

* 如何理解宽表和窄表这个概念？

宽表和窄表的定义一般由企业内部开发规范进行定义(例如超出40个字段定义宽表)。

1. 宽表就是表中字段比较多的表(字段越多维护越困难，甚至会影响查询效率)
2. 窄表就是表中字段比较少的表(维护简单、太少可能会导致大量的表关联)

* 如何理解表设计时的三大范式？

范式是一种设计规范，一种关系模式，可以对表的设计起到一个指导性作用。

1. 第一范式(1NF)：描述的是字段名不可再分。例如姓名可再分为姓和名，这属于可再分。
2. 第二范式(2NF): 描述的是不存在非主键字段对主键字段的部分依赖。
3. 第三范式(3NF): 不存在非主键字段对主键字段的传递依赖。

范式应用案例分析：

1. 分析如下表的设计是否满足第一范式？

创建一张教师表，具体代码如下：

```
create table teacher(
   id int auto_increment,
   name varchar(50) not null comment '姓名',
   primary key (id)
)engine=InnoDB character set utf8mb4;
```
在teacher表的设计中，对于name字段其实可再分为姓和名，按照第一范式的的定义来讲，
这个设计不满足第一范式，我们可以将这个设计调整为如下方案：
```
create table teacher
(
   id int auto_increment,
   first_name varchar(50) not null comment '名',
   last_name varchar(50) not null comment '姓',
   primary key (id)
)engine=InnoDB character set utf8mb4;
```

2. 分析如下表的设计是否满足第二范式？

创建一张成绩表，代码设计如下：

```
create table if not exists score(
   sid bigint comment '学生编号',
   cid bigint comment '课程编号',
   cname varchar(50) not null comment '课程名',
   score int not null comment '成绩',
   primary key (sid,cid)
)engine=InnoDB character set utf8mb4;
```
此表设计不满足第二范式，这里表中的cname依赖于cid，但不依赖于sid，
存在部分依赖。假如要满足第二范式，可调整为如下方案:

```
create table if not exists score
(
   sid bigint comment '学生编号',
   cid bigint comment '课程编号',
   score int not null comment '成绩',
   primary key (sid,cid)
)engine=InnoDB character set utf8mb4;
```
3. 分析如下设计是否满足第三范式？

创建一个部门表，其代码如下：
```
create table if not exists departments
(
  id int auto_increment comment '部门编号',
  name varchar(100) not null comment '部门名称',
  city varchar(20) not null comment '所在城市',
  street_address VARCHAR(40) not null '街道',
  postal_code VARCHAR(12) default '' comment '邮编',
  primary key (id)
)engine=InnoDB character set utf8mb4;

```
此表的设计不满足第三范式，因为存在传递依赖，这里的邮编依赖于街道，
街道又依赖于部门id，所以这里存在传递依赖，假如希望这个表的设计满
足第三范式，可以将部门地址信息写到locations表，然后departments表
再与locations建立关系，例如：

```
create table if not exists locations
(
  id int auto_increment comment '地址编号',
  city varchar(20) not null comment '城市',
  street_address VARCHAR(40) not null comment '街道',
  postal_code VARCHAR(12) default '' comment '邮编',
  primary key (id)
)engine=InnoDB character set utf8mb4;

```

```
create table if not exists departments
(
id int auto_increment comment '部门编号',
name varchar(100) not null comment '部门名称',
location_id int,
unique key (name),
primary key (id),
foreign key (location_id) references locations(id)
)engine=InnoDB character set utf8mb4;

```

* 如何理解表设计时的反范式？

范式设计为我们进行表设计提供一些指导性思想，但实际项目中有时为了提高查询
效率，可能会在表中适当的添加一些冗余字段。就类似于将课程名添加到成绩表中，
这样查询成绩表时可以直接查询出课程名，不需要再去关联课程表进行查询了。但
是这种冗余可能会带来更新的复杂读。例如更新课程表的课程名时，还要去更新
成绩表中的课程名。

# 总结(Summary)



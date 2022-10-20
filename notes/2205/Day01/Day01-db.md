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

* 数据库名字
* 数据库的字符集
* 数据库中表的数量

**FAQ**

* MySQL中创建数据库的语法是怎样的？

help 'create database';

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
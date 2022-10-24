# MySQL中的索引应用

## 课程安排

* 索引的定义
* 索引的类型
* 索引的应用
* 查看执行计划(Explain)

## 索引的定义

* 什么是索引(Index)?

官方的定义索引是一种数据结构,从生活维度讲,假如将一本书看成是一张表,这本书的目录就是表中的索引.

* 为什么要使用索引(Index)

数据量比较大时,为了快速找到们需要的数据可以使用索引,这样可以提高查询的效率.

* 索引(Index)有什么弊端?

1. 会额外的占用空间
2. 对更新操作会带来一定的复杂度.

* 使用索引(Index)的应用场景?

1. on 子句 
2. where 子句
3. group by 子句
4. having 子句
6. order by 子句

## 索引的分类

* 逻辑应用维度 (主键,普通,联合,唯一,空间索引,...)
* 物理存储维度 (聚簇索引,非聚簇索引)
* 数据存储结构维度(hash,B+树,....)

## 索引创建及应用

* 如何查看表中的索引?

show index from student;

* 如何创建索引?

1. 创建表的同时创建索引.(例如 create table tablename(....,index 索引名 (字段名)))
2. 创建表后通过create语句创建索引(例如 create index 索引名 on 表名(字段名))
3. 创建表后通过alter语句创建索引(例如 alter table add index 索引名(字段名))

例如,创建普通索引
```
create index index_first_name on student(first_name);
alter table student add index index_last_name (last_name);
```

例如创建唯一索引
```
create unique index index_first_name on student(first_name);
```






















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

* 创建普通索引案例分享.

```
create index index_first_name on student(first_name);
alter table student add index index_last_name (last_name);
```

* 创建唯一索引案例分享

```
create unique index index_first_name on student(first_name);
alter student add unique index index_first_name (last_name)
```

* 创建组合索引案例分享

```
create index index_first_last on student(first_name,last_name);
alter table student add index index_first_last (first_name,last_name);
```
* 如何删除索引?

drop index 索引名 on 表名;

例如

```
drop index index_first_last on student;
```

## 索引存储结构分析

* MySQL中索引支持哪些存储结果?

  hash,B+树,...

* MySQL中InnoDB默认的索引存储结构是什么?

B+树

* MySQL中的B+树有什么特点?

1. 树中的非叶子节点只存储索引和指针
2. 树中的叶子节点存储索引和数据
3. 树中的叶子节点之间会使用双向链表连接,可以更好的支持范围查询.

* MySQL中B+树相对于B树有什么优点?

1. 一个磁盘可以存储索引数量会更多.
2. 相同数据量的B+树相对于B树的高度相对会比较低(因为分叉多了)
3. 叶子节点之间B+树有双向链表的连接,可以支持快速的范围查询.

* 你觉得Hash索引有什么优势和劣势?

hash索引查询效率比较高,但是不支持范围查询.

## 作业

* 熟悉聚簇索引和非聚簇索引
* 熟悉执行计划





























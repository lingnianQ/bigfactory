# 数据库中的锁应用

* 锁类型及应用
* MVCC(多版本并发控制)

## 锁的类型

* 从性能维度进行分类(悲观锁,乐观锁-性能教好)
* 从操作类型上进行分类(共享锁,排它锁)
* 从数据锁定粒度上进行分析(全局锁,表锁,行锁,...)

## 全局锁应用

* 如何理解全局锁?

全局锁可以对库中所有表上锁,默认是关闭的,使用前可以手动打开.
  
* 如何打开全局读锁?
```
flush tables with read lock.
```

* 如何关闭全局锁(解锁)?
```
unlock tables;
```

* 全局锁案例演示

事务A | 事务B
----- | -----
flush tables with read lock|
select * from regions | select * from regions
select * from jobs    | select * from jobs
insert into jobs (region_name) values ('A8');|
Can't execute the query because you have a conflicting read lock | insert into jobs(region_name) values ('A9');
...                                                              | 阻塞;
unlock tables |
....          |阻塞结束开始执行.









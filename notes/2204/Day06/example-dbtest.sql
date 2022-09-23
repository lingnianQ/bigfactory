drop table if exists t1;        /* 如果表t1存在则删除表t1 */

CREATE TABLE `t1` (             /* 创建表t1 */
   `id` int(11) NOT NULL auto_increment,
   `a` int(11) DEFAULT NULL,
   `b` int(11) DEFAULT NULL,
   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
   PRIMARY KEY (`id`),
   KEY `idx_a` (`a`),
   KEY `idx_b` (`b`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#编写存储过程,对表进行批量数据插入
drop procedure if exists insert_t1; /* 如果存在存储过程insert_t1，则删除 */
delimiter ;;  /* 设置分隔符为;;下一次遇到分隔符则执行语句 */
create procedure insert_t1()        /* 创建存储过程insert_t1 */
begin
    declare i int;                    /* 声明变量i */
    set i=1;                          /* 设置i的初始值为1 */
    while(i<=1000000)
        do                  /* 对满足i<=100000的值进行while循环 */
    insert into t1(a,b) values(i, i); /* 写入表t1中a、b两个字段，值都为i当前的值 */
    set i=i+1;                      /* 将i加1 */
        end while;
end;;
delimiter ;                 /* 创建批量写入100000条数据到表t1的存储过程insert_t1 */
call insert_t1();           /* 运行存储过程insert_t1 */

#分页查询
select id,a,b
from t1
order by id
limit 900000,20;

#优化后的SQL分页查询
explain
select t1.id,t1.a,t1.b
from (select id from t1 order by id limit 900000,20) t0 join t1
                                                             on t0.id=t1.id;

#####################################
#事务隔离级别
#####################################
#问题?多个事务并发执行时可能会出现什么问题? 脏读,不可重复读,幻影读
#如何解决? 可以修改事务的隔离界别(隔离界别越高,数据的一致性就越好,同时性能就会越差)
#mysql默认的事务隔离级别是什么?repeatable-read
#如何查看mysql默认的事务隔离级别?
show variables like '%tx_isolation%';
select @@tx_isolation;
#select @@transaction_isolation; #mysql8以上版本
select @@global.tx_isolation; #查看全局事务隔离级别

#如何修改事务的隔离级别
set tx_isolation='READ-UNCOMMITTED';
set tx_isolation='READ-COMMITTED';
set session transaction isolation level read committed;
set global  transaction isolation level read committed;

##练习1:启动两个命令行SQL两窗口,演示脏读现象.










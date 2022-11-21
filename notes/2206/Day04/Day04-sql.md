# MySQL中的SQL优化

## 课程安排

* 优化原则
* 慢查询分析
* 制定优化策略

## SQL优化原则

* 减少数据访问量
* 减少数据计算操作

## SQL优化的方案

* 良好SQL编码的习惯
* 优秀SQL的编写逻辑
* 定位需要优化的慢SQL语句
* 调整优化策略并进行测试。
* 按业务进行分库分表。

## 优秀的SQL编写逻辑

* 查询时尽量避免实用select *; 

```
1. 这样减少可以数据扫描以及网络开销(很多查询不需要查询所有列)。
2. 要尽量使用覆盖索引(索引中已经包含你需要的数据)、减少回表查询。
```

* 尽量避免在where子句中使用or作为查询条件。

```
1. or可能会使用索引失效，进而执行全表扫描。
2. 全表查询的效率相对基于所引查询的效率会比较低。
```

例如
```
select first_name,hire_date,salary
from employees
where job_id='AD_VP' or salary>15000
```
方案优化

```
create index index_salary on employees(salary);

select first_name,hire_date,salary
from employees
where job_id='AD_VP'
union all
select first_name,hire_date,salary
from employees
where salary>15000;
```

* where 条件中尽量不要出现与null值的比较

例如:
```
create  index index_commission_pc on employees(commission_pct);

select first_name,salary,commission_pct
from employees
where commission_pct is not null;
```
可调整为:(表中数据量比较大，但是commission_pct>0的比较少，可以走索引)
```
select first_name,salary,commission_pct
from employees
where commission_pct>0;
```
* 避免在where子句中使用!=或者<>操作符
* 使用like查询条件时应尽量避免前缀使用"%" 
* 避免在查询条件中使用一些内置的SQL函数。
* 当有多个查询条件、分组条件、排序条件时，尽量使用联合索引(组合索引)
* 表连接时优先使用内连接(inner join),使用小表驱动大表。
* 表设计时字段类型能用简单数据类型不用复杂类型。  
* 清空表中数据可优先使用truncate.
* 插入多条数据时可考虑使用批量插入。

例如：
```
insert into regions (region_id,region_name) values (1,'A');
insert into regions (region_id,region_name) values (2,'B');
```
替换为
```
insert into regions (region_id,region_name) values (1,'A'),(2,'B');
```

## 慢SQL查询分析

* 如何定位慢SQL？

优化 SQL 的前提是能定位到慢 SQL ，其方案有如下两种：

1. 查看慢查询日志，确定已经执行完的慢查询。
2. show processlist 查看正在执行的慢查询。

* 如何进行慢查询分析？

使用慢查询日志一般分为四步：
1. 开启慢查询日志（一般默认是关闭状态）
2. 设置慢查询阀值(响应速度是多长时间被定为是慢查询)
3. 确定慢查询日志路径(日志文件在哪里)
4. 确定慢查询日志的文件名(具体日志文件是哪个)，然后对文件内容进行分析。

* 查看慢查询日志的打开状态？

```
show variables like '%slow_query_log%';
```

* 如何开启慢查询日志? 
  
在 MySQL 命令行下输入下面的命令：

```
mysql> set global slow_query_log = on;
Query OK, 0 rows affected (0.00 sec)
```

默认环境下，慢查询日志是关闭的。

* 查看默认慢查询阈值 (默认为10秒中)
show variables like '%long_query_time%';
  
* 如何设置慢查询的阈值？

设置慢查询时间阀值(响应时间是多长时间是慢查询)

```
mysql> set global long_query_time = 1;
Query OK, 0 rows affected (0.00 sec)
```

如果需要定位到慢查询，一般的方法是通过慢查询日志来查询的，
MySQL 的慢查询日志用来记录在 MySQL中响应时间超过参数 long_query_time
（单位秒，默认值 10）设置的值并且扫描记录数不小于 min_examined_row_limit（默认值0）的语句

* long_query_time 的值如何确定呢？

1. 线上业务一般建议把 long_query_time 设置为 1 秒，如果某个业务的 MySQL 要求比较高的 QPS，
可设置慢查询为 0.1 秒。发现慢查询及时优化或者提醒开发改写。
2. 一般测试环境建议 long_query_time 设置的阀值比生产环境的小，比如生产环境是 1 秒，则测试环境建议配置成 0.5 秒。便于在测试环境及时
发现一些效率低的 SQL。
3. 甚至某些重要业务测试环境 long_query_time 可以设置为 0，以便记录所有语
句。并留意慢查询日志的输出，上线前的功能测试完成后，分析慢查询日志每类语句的输出，重点关注 Rows_examined（语句执行期间从存储引擎读取的行数），提前优化。

* 如何知道慢查询日志路径？

慢查询日志的路径默认是 MySQL 的数据目录
```
mysql> show global variables like 'datadir';

+---------------+------------------------+
| Variable_name | Value                  |
+---------------+------------------------+
| datadir       | /data/mysql/data/3306/ |
+---------------+------------------------+

1 row in set (0.00 sec)
```

* 如何知道慢查询日志的文件名？

```
mysql> show global variables like 'slow_query_log_file';

+---------------------+----------------+
| Variable_name       | Value          |
+---------------------+----------------+
| slow_query_log_file | mysql-slow.log |
+---------------------+----------------+

1 row in set (0.00 sec)
```

打开日志文件，可以对日志文件中的内容进行分析，常用选项说明：

```
Time：慢查询发生的时间
User@Host：客户端用户和IP
Query_time：查询时间
Lock_time：等待表锁的时间
Rows_sent：语句返回的行数
Rows_examined：语句执行期间从存储引擎读取的行数
```

说明，后续也可以使用 pt-query-digest 或者 mysqldumpslow 等工具对慢查询日志进行分析。

* 如何查看正在运行的慢SQL？

1. 使用 show processlist 命令判断正在执行的慢查询。
2. 使用 show full processlist 看慢查询语句的全部内容可以。

* 如何对慢查询进行分析？

工欲善其事，必先利其器”，分析慢查询可以通过 explain、show profile 和 trace 等工具来
实现。

## 执行计划(Explain)

* 执行计划(Explain)是什么？

MySQL 提供了一个Explain命令，它可以对select语句进行分析，并输出select执行时的详细信
息，开发人员可以基于这些信息进行有针对性的优化，例如：
```
mysql> explain select * from employees where employee_id<100 \G;
*************************** 1. row ***************************
id: 1
select_type: SIMPLE
table: employees
partitions: NULL
type: range
possible_keys: PRIMARY
key: PRIMARY
key_len: 4
ref: NULL
rows: 1
filtered: 100.00
Extra: Using where
1 row in set, 1 warning (0.00 sec)
```  

* id

select 的序列号，有几个 select 就有几个 id，id 的顺序是按 select 出现的顺序增长的。
即：id 越大执行优先级越高，id相同则从上往下执行，id 为NULL最后执行。

```sql
select last_name,salary
from employees
where employee_id=(
select manager_id
from employees
where employee_id=206);
```

* select_type表示的查询类型有哪些？ 

```
1. SIMPLE ： 表示查询语句不包含子查询或 union
2. PRIMARY：表示此查询是最外层的查询
3. UNION：表示此查询是 union 的第二个或后续的查询
4. DEPENDENT UNION：union 中的第二个或后续的查询语句，使用了外面查询结果
5. UNION RESULT：union 的结果
6. SUBQUERY：SELECT 子查询语句
7. DEPENDENT SUBQUERY：SELECT 子查询语句依赖外层查询的结果。
8. DERIVED: from 子句后的相对比较复杂查询
```

案例分析：

> SIMPLE 、SUBQUERY
```sql
explain
select * 
from employees 
where employee_id<100;

```

> PRIMARY 
```sql
explain
select last_name,salary
from employees
where employee_id=(
    select manager_id
    from employees
    where employee_id=206);
```

> UNION、UNION RESULT
```sql
explain
select first_name,hire_date,salary
from employees
where job_id='AD_VP'
union
select first_name,hire_date,salary
from employees
where  salary>15000;
```

> DEPENDENT SUBQUERY
```sql
explain
select employee_id,first_name,salary
from employees e1
where salary=(
    select max(salary)
    from employees e2
    where e1.department_id=e2.department_id);
```

> DERIVED
```sql
explain
select min(avg_salary)
from (
select avg(salary) avg_salary
from employees
group by department_id) emp;
```


* type表示查询数据的方式。（重点）

type是一个比较重要的一个属性，通过它可以判断出查询是全表扫描还是基于索引的部分扫描。
常用属性值如下，从上至下效率依次增强。

```
1. ALL：表示全表扫描，性能最差。
2. index：表示基于索引的全表扫描，先扫描索引再扫描全表数据。
3. range：表示使用索引范围查询。使用 >、>=、<、<=、in 等等。
4. ref：表示使用非唯一索引进行单值查询。
5. eq_ref：一般情况下出现在多表 join 查询，表示前面表的每一个记录，都只能匹配后面表的一行结果。
6. const：表示使用主键或唯一索引做等值查询，常量查询。
7. NULL：表示不用访问表，速度最快。
```

案例分析：

> select_type为null

```sql
explain
select now()
```

> select_type 为const

```sql
explain
select *
from employees
where employee_id=206
```

> select_type为 ref
```sql
create index index_first_name on employees(first_name);
explain
select *
from employees
where first_name='Steven';
```

> select_type 为eq_ref

```sql
explain
select d.department_id,d.department_name,e.first_name
from departments d join employees e on d.manager_id = e.employee_id;
```

> select_type 为 range
```sql
create index index_salary on employees (salary);
explain
select first_name,salary
from employees
where salary between 10000 and 30000;
```

> select_type 为index
```sql
explain
select count(*)
from employees
group by department_id;
```


* Extra中值的含义是什么？

Extra 表示很多额外的信息，各种操作会在 Extra 提示相关信息，常见几种如下：

> 1. Using where 表示查询需要通过where条件查询数据(可能没有用到索引,也可能一部分用到了索引)。
```
explain
select *
from hr.employees
where salary>10000;
```
> 2. Using index 表示查询需要通过索引，索引就可以满足所需数据(不需要再回表查询，这里出现了索引覆盖)。

```
create index index_hire_date_salary on employees(hire_date,salary);
explain
select employee_id,hire_date,salary
from hr.employees
where hire_date>'2000-03-06' and salary>10000;
```

> 3. Using filesort 表示查询出来的结果需要额外排序，数据量小在内存，大的话在磁盘，因此有 Using filesort 建议优化。

```
explain
select first_name,hire_date,salary
from hr.employees
order by hire_date
```

> 4. Using temprorary 查询使用到了临时表，一般出现于去重、分组等操作(这里一般也需要优化)。

```
explain
select first_name,salary
from hr.employees
where first_name like 'A%'
union
select first_name,salary
from hr.employees
where first_name like 'B%'
```

> 5. Using index condition 表示查询的记录，在索引中没有完全覆盖(可能要基于where或二级索引对应的主键再次查询)。

```
explain
select employee_id,hire_date,salary,commission_pct
from hr.employees
where hire_date>'2000-03-06' and salary>10000;
```

## 性能分析工具(Profile)应用

* 什么是Profile？

Profile 是MySQL内置的一个性能分析工具，基于Profile可以更好的了解SQL执行过程中的资源情况。
例如你的CPU,内存，IO等

* 如何使用Profile?

1. 确定你数据库是否支持Profile。
2. 确定Profile是否是关闭的,假如是关闭的需要先开启。
3. 执行SQL语句
4. 查看执行SQL的query id。
5. 通过query id查看SQL每个状态的耗时时间。

* 如何确定数据是否支持Profile?

```
select @@have_profiling
假如查询结果为yes表示支持。
```

* 如何确定Profile是否是关闭的？

```
select @@profiling
假如查询结果为0表示为开启。
```

* 如何开启Profile呢？

```
set profiling=1
```
* 执行SQL语句？

```
select * from employees where salary>5000;
```

* 查看SQL的query id？

```
 show profiles;
```

* 查看具体SQL的执行详情？

```
 show profile for query 1;
```

## 总结(Summary)

* 思维导图(https://www.processon.com/view/link/632d82e35653bb1ab0ea0fab)
* SQL调优的原则是什么？(减少数据的访问量以及计算)
* SQL调优的基本方案？
* 优秀SQL编写的基本逻辑？
* 如何定位执行效率比较慢的SQL语句？(慢SQL日志)
* 如何对慢SQL进行分析？(执行计划-Explain,Profile,....)


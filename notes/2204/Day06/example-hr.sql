###############################
#慢查询分析
###############################

#查看慢查询日志是否开启(默认是OFF状态)
show variables like '%slow_query_log%';
#开启慢查询日志(要看到变化需要重新登录)
set global slow_query_log = 'on';

#查看慢查询默认的时间阈值
show variables  like 'long_query_time';
#设置慢查询时间阀值(响应时间是多长时间是慢查询)
set global long_query_time = 1;
#查看默认扫描多少行时,响应时间为指定阈值才认为是慢查询
show variables like '%min_examined_row_limit%';
#查看慢查询日志文件的路径
show global variables like 'datadir';
#查看慢查询日志文件名
show global variables like 'slow_query_log_file';

#查看正在执行的SQL中有哪些慢查询
show processlist;

show full processlist;

####################################
# profile
####################################
#查看数据库是否支持profile,假如值yes表示支持
select @@have_profiling;
#查看profile是否开启,假如结果为0则表示为开启
select @@profiling;

#开启当前会话profile
set profiling=1;

#开启全局的profile (所有会话都会开启)
set global profiling=1;

#确定 SQL 的 query id
select * from employees;
show profiles;

#查询 SQL 执行详情
show profile for query 78;

#######################################
#索引优化分析
#######################################
explain
select * from employees where employee_id='206'

explain
select * from employees where department_id='60';

explain
select * from employees where salary>'10000';

show index  from employees;
create index index_hire_date on employees(hire_date);

explain
select first_name,hire_date
from employees
where year(hire_date)='1999';


create index index_id_salary on employees(employee_id,salary);

explain
select employee_id,salary
from employees
where employees.employee_id>0 and salary>20000
order by employee_id,salary;
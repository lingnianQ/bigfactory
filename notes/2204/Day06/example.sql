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

#打开数据库
```
use dbtest;
```
#创建表
```
drop table if exists oper_log;
create table oper_log(
    id bigint not null  auto_increment comment '主键',
    username varchar(50) not null comment '登录用户',
    ip varchar(50) not null comment '访问ip',
    created_time datetime not null comment '访问时间',
    operation varchar(50) default '' comment '具体操作',
    method varchar(100) not null comment '访问的方法',
    params varchar(100) default '' comment '传递的参数',
    status tinyint default '1' comment '操作状态',
    error varchar(200) default '' comment '错误信息',
    index index_username (username),
    primary key (id)
)engine=InnoDB default character set utf8mb4;
```
#添加普通索引
```
create index index_ip on oper_log(ip);
alter table oper_log add index index_created_time (created_time);
```

#创建雇员表
```
drop table if exists employee;
create table employee
(
    `id` bigint not null auto_increment,
    `name` varchar(30) default '' comment '姓名',
    `gender` enum('Male','Female') default 'Male',
    `phone` varchar(20) not null comment '手机号',
    `email` varchar(50) default '' comment '邮箱',
    `dept_id` bigint,
     UNIQUE index_phone (phone),
     primary key (id)
)engine=innodb default  character set utf8mb4;
```
#创建唯一索引
```
create unique index index_email on employee(email);
```
#向employee表中添加数据并查询
```
insert into employee (id,name,gender,phone,dept_id)
values  (null,'A','Male','111',101),(null,'A','Male','112',102);

select * from employee;
```

# 创建组合索引
create index index_username_created_time on oper_log(username,created_time);

# 创建前缀索引
create index index_error on oper_log(error(5));

#显示索引
show index from oper_log;
############################################
#explain (执行计划)
############################################

```
select count(*) from employees;
explain select * from  employees where employee_id>100;
explain select * from  employees where employee_id=100;
```

```
explain
select * from  employees where first_name like 'd%';
```

```
explain
select * from employees where first_name='Lee';
create index index_first_name on employees(first_name);
```

```
show index from employees;

explain select m.first_name,m.salary
        from employees e join employees m
                              on e.manager_id=m.employee_id
        where e.employee_id=206;
```
```
explain select first_name,salary
        from employees
        where manager_id=(
            select manager_id
            from employees
            where employee_id=206);
```

```
explain
select employee_id,first_name
from employees
union
select department_id,department_name
from departments;
```

```
explain
select department_id,avg(salary)
from employees
group by department_id
having avg(salary)>(
    select avg(salary)
    from employees
    where department_id=60
);
```

```
select * from jobs;
show index from jobs;

explain
select *
from jobs
where job_title like 'A%' and min_salary>3000 and max_salary<30000;

create index index_title_min_max on jobs(job_title,min_salary,max_salary);
drop index index_title_min_max on jobs;
```

############################
# example
############################
#1.对雇员表进行分页查询,每页最多显示10条,查询第二页数据,并按工资进行降序排序?
```
explain
select e.employee_id
from (
         select *
         from employees
         where employees.employee_id>=(
             select employee_id
             from employees
             order by employee_id
             limit 100,1
         )
         limit 0,10
     ) e
order by e.salary desc
```










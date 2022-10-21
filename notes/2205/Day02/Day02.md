# 微人事(HR)系统数据库表设计及查询

## 课程安排

* 初始化
* 分析表设计
* 高级查询应用

## 数据初始化操作

命令行登陆mysql，然后执行source d:/hr_mysql.sql

## 关键表设计分析

* 区域表(Regions)
* 国家表(Countries)
* 地址表(Locations)
* 部门表(Departments)
* 雇员表(Employees)
* 岗位表(Jobs)
* 岗位变更历史表(job_history)

## 表中关键字段分析

区域表(Regions)

```
CREATE TABLE regions
(
region_id int auto_increment comment '编号',
region_name VARCHAR(25) comment '区域名称',
primary key (region_id),
unique key (region_name)
) engine=innodb default character set utf8;
```

国家表(Countries)

```
CREATE TABLE countries
(
country_id CHAR(2) primary key,
country_name VARCHAR(40) comment '国家名称',
region_id int
)engine=innodb default character set utf8;

ALTER TABLE countries
ADD (
CONSTRAINT countr_reg_fk
FOREIGN KEY (region_id)
REFERENCES regions(region_id)
);
```

地址表(Locations)

```
CREATE TABLE locations
( location_id int(4) primary key auto_increment,
  street_address VARCHAR(40),
  postal_code VARCHAR(12), 
  city VARCHAR(30) not null, 
  state_province VARCHAR(25),
  country_id CHAR(2)
) engine=innodb default character set utf8;

ALTER TABLE locations
ADD (
CONSTRAINT loc_c_id_fk
FOREIGN KEY (country_id)
REFERENCES countries(country_id)
);

```
部门表(Departments)
```
CREATE TABLE departments
( department_id int(4) primary key auto_increment, 
  department_name VARCHAR(30) NOT NULL,
  manager_id int(6), 
  location_id int(4)
) engine=innodb default character set utf8;

ALTER TABLE departments
ADD (
CONSTRAINT dept_loc_fk
FOREIGN KEY (location_id)
REFERENCES locations (location_id)
) ;

```
岗位表(Jobs)
```
CREATE TABLE jobs
( job_id VARCHAR(10) primary key, 
  job_title VARCHAR(35) NOT NULL, 
  min_salary numeric(6), 
  max_salary numeric(6)
) engine=innodb default character set utf8;
```

雇员表
```
CREATE TABLE employees
( employee_id int(6) primary key auto_increment,
  first_name VARCHAR(20), 
  last_name VARCHAR(25) NOT NULL, 
  email VARCHAR(25) NOT NULL, 
  phone_number VARCHAR(20),
  hire_date DATE NOT NULL, 
  job_id VARCHAR(10) NOT NULL, 
  salary numeric(8,2), 
  commission_pct numeric(2,2),
  manager_id int(6), 
  department_id int(4), 
  CONSTRAINT emp_salary_min CHECK (salary > 0),
  CONSTRAINT emp_email_uk UNIQUE (email)
) engine=innodb default character set utf8;

ALTER TABLE employees
ADD (
CONSTRAINT emp_dept_fk
FOREIGN KEY (department_id)
REFERENCES departments(department_id)
,
CONSTRAINT emp_job_fk
FOREIGN KEY (job_id)
REFERENCES jobs (job_id)
,
CONSTRAINT emp_manager_fk
FOREIGN KEY (manager_id)
REFERENCES employees(employee_id)
) ;
ALTER TABLE departments
ADD (
CONSTRAINT dept_mgr_fk
FOREIGN KEY (manager_id)
REFERENCES employees (employee_id)
) ;

```
岗位历史变更表
```
CREATE TABLE job_history
( employee_id int(6) NOT NULL, 
  start_date DATE NOT NULL, 
  end_date DATE NOT NULL, 
  job_id VARCHAR(10) NOT NULL,
  department_id int(4), 
  CONSTRAINT jhist_date_interval
  CHECK (end_date > start_date), 
  CONSTRAINT jhist_emp_id_st_date_pk PRIMARY KEY (employee_id, start_date)
) engine=innodb default character set utf8;

ALTER TABLE job_history
ADD (
CONSTRAINT jhist_job_fk
FOREIGN KEY (job_id)
REFERENCES jobs(job_id)
,
CONSTRAINT jhist_emp_fk
FOREIGN KEY (employee_id)
REFERENCES employees(employee_id)
,
CONSTRAINT jhist_dept_fk
FOREIGN KEY (department_id)
REFERENCES departments(department_id)
) ;
```

## 常用查询分析及实践

**1)求雇员编号206的经理人的名字和薪水？**

请问雇员的经理人是雇员吗?是
那经理人的id是不是需要在雇员id中有相同值?需要

方案1：嵌套查询
```
select first_name,salary
from employees
where employee_id=(
   select manager_id
   from employees
   where employee_id=206
)
```
方案2：自关联(可以将自身这张表看成是多张表)
```
select m.first_name,m.salary
from employees e join employees m
on e.manager_id=m.employee_id
where e.employee_id=206
```

**2)查询雇员206所在部门的部门名称以及这个部门所在的城市?**
-- 雇员表中有部门名称吗?没有,部门表(departments)中有
-- 雇员表中有城市名称名称?没有,地址表(locations)中有
```
select e.employee_id,d.department_name,l.city
from employees e join departments d on e.department_id = d.department_id
join locations l on d.location_id = l.location_id
where e.employee_id=206;
```

**3)统计每个岗位的雇员数,并按人数进行降序排序?**

```
select job_id,count(*) total
from employees
group by job_id
order by total desc;
```

**4)统计公司中有佣金提成的员工人数有多少?**

-- 方案1
select count(*)
from employees
where commission_pct is not null;

-- 方案2
select count(1)
from employees
where commission_pct is not null;

-- 方案3(count函数内部是列名,表示统计列值不为null的记录)
select count(commission_pct)
from employees;

**5)统计年入职的人数有多少?(查看系统函数 help 'functions')**
select year(hire_date),count(*)
from employees
group by year(hire_date);

**6)查询每个部门的平均薪资,并按降序排序**

```
select department_id,avg(salary)
from employees
group by department_id
order by avg(salary) desc;
```


**7)查询每个部门的平均薪资,只显示平均薪资大于10000的,并按降序排序**

```
select department_id,avg(salary)
from employees
group by department_id
having avg(salary)>10000
order by 2 desc;
```

**8)查询每个雇员的薪资，并给出薪资等级(例如>=10000高;>=8000and<10000 中等;<8000偏低)**

考核知识点：case when 表达式的应用
```
select first_name,salary,
      (case when salary>=10000 then '偏高'
            when salary>=8000 then '中等'
            else '偏低' end) level
from employees
```
**9)统计薪资大于等于10000的人数，小于10000的人数。**

```
select  sum(case when salary>=10000 then 1 else 0 end ) '大于等于10000',
        sum(case when salary<10000 then 1 else 0 end ) '小于10000'
from employees
```

**10)查询雇员表中第二页的数据(每页最多显示10条记录-页面大小)？**

方案1:(数据量比较小的情况下-这种查询是全表扫描，数据量大时查询会比较慢)
```
select *
from employees
limit 10,10
```
方案2:(数据量比较大时-例如500万数据)

```
select e2.*
from (
         select employee_id
         from employees
         order by employee_id
         limit 10,10) e1 join employees e2
                         on e1.employee_id=e2.employee_id

```





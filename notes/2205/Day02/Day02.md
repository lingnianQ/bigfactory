# 微人事系统数据库表设计及查询

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

## 常用查询分析及实践

* 求雇员编号206的经理人的名字和薪水？

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

* 查询每个雇员的薪资，并给出薪资等级(例如>=10000高;>=8000and<10000 中等;<8000偏低)

考核知识点：case when 表达式的应用
```
select first_name,salary,
      (case when salary>=10000 then '偏高'
            when salary>=8000 then '中等'
            else '偏低' end) level
from employees
```

* 统计每个岗位的雇员人数，并按人数多少进行降序排序。

```
select job_id,count(*) total
from employees
group by job_id
order by total desc
```

* 统计薪资大于等于10000的人数，小于10000的人数。

```
select  sum(case when salary>=10000 then 1 else 0 end ) '大于等于10000',
        sum(case when salary<10000 then 1 else 0 end ) '小于10000'
from employees
```

5)查询雇员所在部门的部门名称以及这个部门所在的城市？

```
select first_name,d.department_name,l.city
from employees e join departments d on e.department_id=d.department_id
                 join locations l on d.location_id=l.location_id
    
```

6)查询雇员表中第二页的数据(每页最多显示10条记录-页面大小)？

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
7)查询雇员表中每年入职的人数是多少？
```
select year(hire_date),  count(*)
from employees
group by year(hire_date);

```




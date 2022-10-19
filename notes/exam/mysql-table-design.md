# 核心要点
* 掌握相关术语
* 掌握数据库基本设计
* 掌握数据库表的基本设计

# 微人事数据库设计

## 创建数据库

* 语法
```
CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name
[create_specification] ...

create_specification:
[DEFAULT] CHARACTER SET [=] charset_name
| [DEFAULT] COLLATE [=] collation_name

```

* 案例

```
create database if not exists hr DEFAULT CHARACTER set utf8;
```

## 打开数据库


```
use hr;
```

## 删除数据库

语法：
```
DROP {DATABASE | SCHEMA} [IF EXISTS] db_name
```

案例
```
drop database if exists hr;
```



# 微人事相关表设计

区域表
```
CREATE TABLE regions
(
region_id int primary key auto_increment,
region_name VARCHAR(25)
) engine=innodb default character set utf8;
```

国家表
```
CREATE TABLE countries
(
country_id CHAR(2) primary key
, country_name VARCHAR(40)
, region_id int
)engine=innodb default character set utf8;

ALTER TABLE countries
ADD (
CONSTRAINT countr_reg_fk
FOREIGN KEY (region_id)
REFERENCES regions(region_id)
);
```

地址表
```
CREATE TABLE locations
( location_id int(4) primary key auto_increment
, street_address VARCHAR(40)
, postal_code VARCHAR(12)
, city VARCHAR(30) not null
, state_province VARCHAR(25)
, country_id CHAR(2)
) engine=innodb default character set utf8;

ALTER TABLE locations
ADD (
CONSTRAINT loc_c_id_fk
FOREIGN KEY (country_id)
REFERENCES countries(country_id)
);

```

```
CREATE TABLE departments
( department_id int(4) primary key auto_increment
, department_name VARCHAR(30) NOT NULL
, manager_id int(6)
, location_id int(4)
) engine=innodb default character set utf8;

ALTER TABLE departments
ADD (
CONSTRAINT dept_loc_fk
FOREIGN KEY (location_id)
REFERENCES locations (location_id)
) ;
```


岗位表
```
CREATE TABLE jobs
( job_id VARCHAR(10) primary key
, job_title VARCHAR(35) NOT NULL
, min_salary numeric(6)
, max_salary numeric(6)
) engine=innodb default character set utf8;
```

雇员表
```
CREATE TABLE employees
( employee_id int(6) primary key auto_increment
, first_name VARCHAR(20)
, last_name VARCHAR(25) NOT NULL
, email VARCHAR(25) NOT NULL
, phone_number VARCHAR(20)
, hire_date DATE NOT NULL
, job_id VARCHAR(10) NOT NULL
, salary numeric(8,2)
, commission_pct numeric(2,2)
, manager_id int(6)
, department_id int(4)
, CONSTRAINT emp_salary_min CHECK (salary > 0)
, CONSTRAINT emp_email_uk UNIQUE (email)
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
( employee_id int(6) NOT NULL
, start_date DATE NOT NULL
, end_date DATE NOT NULL
, job_id VARCHAR(10) NOT NULL
, department_id int(4)
, CONSTRAINT jhist_date_interval
CHECK (end_date > start_date)
, CONSTRAINT jhist_emp_id_st_date_pk PRIMARY KEY (employee_id, start_date)
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
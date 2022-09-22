#打开数据库
use dbtest;
#创建表
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

create index index_ip on oper_log(ip);
alter table oper_log add index index_created_time (created_time);

#显示索引
show index from oper_log;

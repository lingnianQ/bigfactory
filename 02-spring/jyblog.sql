DROP DATABASE IF EXISTS `jyblog`;
CREATE DATABASE `jyblog` DEFAULT CHARACTER SET utf8mb4;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
use `jyblog`;

DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
    `password` varchar(255) NOT NULL COMMENT '密码',
    `mobile` varchar(20) DEFAULT NULL COMMENT '电话号码',
    `status` tinyint(4) NOT NULL COMMENT '账号是否被锁住，0-》禁用，1-》启用',
    `created_time` datetime NOT NULL COMMENT '注册时间',
    `modified_time` datetime NOT NULL COMMENT '注册时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `tb_tags`;
create table `tb_tags`
(
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(50) default 'java',
    `remark` varchar(100) default '',
    `created_time` datetime NOT NULL COMMENT '添加时间',
    `modified_time` datetime NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
);

DROP TABLE IF EXISTS `tb_articles`;
create table `tb_articles`
(
    id             bigint(20)     unsigned auto_increment    comment 'ID',
    title          varchar(50)    not null          comment '标题',
    type           char(1)        not null          comment '类型（1 原创 2 转载 3翻译）',
    content        varchar(500)   default null      comment '文章内容',
    status         char(1)        default '0'       comment '状态（1审核  2通过  3关闭）',
    user_id        bigint(20)     unsigned not null comment '用户id',
    created_time   datetime                         comment '创建时间',
    modified_time  datetime                         comment '更新时间',
    primary key (id)
) engine=innodb auto_increment=1 comment = '文章表';

DROP TABLE IF EXISTS `tb_article_tags`;
create table `tb_article_tags`
(
    id    bigint(20)     unsigned auto_increment    comment 'ID',
    article_id bigint(20)  unsigned comment '文章 ID',
    tag_id bigint(20)  unsigned comment '标签 ID',
    primary  key (id)
);

DROP TABLE IF EXISTS `tb_logs`;
CREATE TABLE IF NOT EXISTS `tb_logs`
(
    id          bigint auto_increment primary key,
    username    varchar(50)   null comment '用户名',
    operation   varchar(50)   null comment '用户操作',
    method      varchar(200)  null comment '请求方法',
    params      varchar(5000) null comment '请求参数',
    time        bigint        not null comment '执行时长(毫秒)',
    ip          varchar(64)   null comment 'IP地址',
    created_time datetime     null comment '创建时间',
    status      int default       1 null,
    error       varchar(500)  null
)comment '用户日志' charset = utf8;
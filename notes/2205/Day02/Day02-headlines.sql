-- 微头条表设计
DROP DATABASE  if exists `headlines`;
CREATE database if NOT EXISTS `headlines` collate utf8mb4_unicode_ci;
use `headlines`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(100)  NOT NULL COMMENT '用户名' ,
    `password` varchar(100)  NOT NULL COMMENT '登录密码',
    `nickname` varchar(30)  DEFAULT '' COMMENT '昵称',
    `mobile` varchar(30)   DEFAULT '' COMMENT '手机号',
    `avatar` varchar(255)  DEFAULT '' COMMENT '头像地址',
    `last_login_time` datetime NOT NULL COMMENT '最近登录时间',
    `remark` varchar(100) DEFAULT '' COMMENT '用户备注',
    `status`  tinyint(1) default 1 COMMENT '用户状态',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8;


-- ----------------------------
-- Table structure for tb_article
-- ----------------------------

DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`
(
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` varchar(255) COMMENT '标题',
    `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `image` varchar(255)  COMMENT '封面图片',
    `content` mediumtext  COMMENT '内容',
    `status` tinyint(1)  DEFAULT 1 COMMENT '状态',
    `create_time` datetime  DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4;

-- ----------------------------
-- Table structure for biz_article_look
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_look`;
CREATE TABLE `tb_article_look`
(
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `article_id` bigint(20) UNSIGNED DEFAULT 0  COMMENT '文章ID',
    `user_id` bigint(20) UNSIGNED DEFAULT 0 COMMENT '已登录用户ID',
    `look_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8;


-- ----------------------------
-- Table structure for biz_article_love
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_love`;
CREATE TABLE `tb_article_love`
(
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `article_id` bigint(20) UNSIGNED NOT NULL COMMENT '文章ID',
    `user_id` bigint(20) UNSIGNED  DEFAULT NULL COMMENT '已登录用户ID',
    `love_time` datetime  COMMENT '收藏时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8;


-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`
(
    `id` bigint(20)  AUTO_INCREMENT,
    `article_id` bigint(20)  COMMENT '被评论的文章或者页面的ID',
    `user_id` bigint(20)  COMMENT '评论人的ID',
    `pid` bigint(20)  COMMENT '父级评论的id',
    `nickname` varchar(13) COMMENT '评论人的昵称（未登录用户）',
    `avatar` varchar(255)  COMMENT '评论人的头像地址',
    `url` varchar(200) COMMENT '评论人的网站地址（未登录用户）',
    `status` enum('VERIFYING','APPROVED','REJECT','DELETED') DEFAULT 'VERIFYING' COMMENT '评论的状态',
    `content` varchar(2000)  COMMENT '评论的内容',
    `support` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '支持（赞）',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8mb4;


-- ----------------------------
-- Table structure for biz_ad
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad`;
CREATE TABLE `tb_ad`
(
    `id` bigint(11) PRIMARY KEY  AUTO_INCREMENT,
    `title` varchar(255) NOT NULL COMMENT '广告标题',
    `content` varchar(500)  DEFAULT '' COMMENT '广告内容',
    `picture` varchar(1000)  DEFAULT '' COMMENT '广告图片',
    `link` varchar(1000)  NOT NULL COMMENT '广告链接',
    `expiring_date` datetime NOT NULL  COMMENT '广告到期日',
    `show_number` int(10) UNSIGNED  DEFAULT 0 COMMENT '展示次数',
    `click_number` int(10) UNSIGNED DEFAULT 0 COMMENT '点击次数',
    `create_time` datetime  DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `update_time` datetime  DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE = InnoDB AUTO_INCREMENT = 1;
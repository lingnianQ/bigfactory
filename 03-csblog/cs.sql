-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.67-MariaDB - mariadb.org binary distribution
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for cs
DROP DATABASE IF  EXISTS `cs`;
CREATE DATABASE IF NOT EXISTS `cs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cs`;

-- Dumping structure for table cs.banner
CREATE TABLE IF NOT EXISTS `banner` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `url` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table cs.banner: ~9 rows (大约)
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` (`id`, `url`) VALUES
(2, '/imgs/b2.jpg'),
(5, '/ec65deeb-f27a-4056-9fbd-45e1fdad1695.jpg'),
(6, '/cb3b50b1-9d55-4ecd-ba1e-d769f373fd39.png'),
(7, '/a01a6b5c-189a-494a-a956-027bf31b0dd6.jpg'),
(8, '/804d2827-7b03-4387-a6dc-e71040fe94f0.jpg'),
(9, '/70f9a0a8-c4ca-4587-86f0-4d1b95b0bf74.jpg'),
(10, '/25707171-d30d-4a8b-867e-514e5bbefe74.jpg'),
(11, '/ed9da81a-4e15-49a1-b760-2656921afb21.png'),
(13, '/05254939-b9c7-46ac-a20a-d4c1a65dda91.jpg');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;

-- Dumping structure for table cs.category
CREATE TABLE IF NOT EXISTS `category` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table cs.category: ~5 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`) VALUES
(1, '女装'),
(2, '男装'),
(3, '医药'),
(8, '新分类'),
(9, '果蔬食品');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table cs.product
CREATE TABLE IF NOT EXISTS `product` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(100) DEFAULT NULL,
    `url` varchar(100) DEFAULT NULL,
    `price` double(10,2) DEFAULT NULL,
    `old_price` double(10,2) DEFAULT NULL,
    `view_count` int(11) DEFAULT NULL,
    `sale_count` int(11) DEFAULT NULL,
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `category_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table cs.product: ~8 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `title`, `url`, `price`, `old_price`, `view_count`, `sale_count`, `created`, `category_id`) VALUES
(3, '森马牛仔裤女宽松慢跑裤运动风2022春季新款显瘦束脚长裤复古', '/8f57e641-1f9c-4393-8a36-b9f0d2c16ef2.jpg', 654.00, 3453.00, 23, 334, '2022-04-18 17:13:15', 2),
(4, '茵曼马甲连衣裙两件套春季新款娃娃领色织格长袖背心裙套装', '/5080711d-52bb-4e57-a645-1674857fd1f2.jpg', 123.00, 234.00, 4, 465, '2022-04-18 17:15:02', 2),
(5, '雪中飞墨绿色短袖t恤女夏2022新款纯棉半袖打底体恤夏季上衣潮ins', '/734de500-e2ce-4e98-93d9-edadf2e20bf6.jpg', 33.00, 432.00, 3, 23, '2022-03-30 16:07:12', 1),
(7, '弓箭', '/24bcbe4d-894d-4766-b8b2-20faf533a5cd.jpg', 5.00, 7.00, 2, 2, '2022-03-29 11:33:14', 8),
(8, '香影毛呢外套女中长款2021年冬季新款气质韩版娃娃领紫色呢子大衣', '/f1a9da0b-bd98-4586-b029-9a77a8ed993b.jpg', 343.00, 423.00, 4, 234, '2022-04-18 17:15:24', 1),
(9, '非常新鲜的食品', '/8b0c64cc-2987-4faf-80f1-9eddfc4400de.jpg', 50.00, 100.00, 3, 5, '2022-03-31 09:24:13', 9),
(10, 'JANE HARLOW男鞋运动鞋网面防臭老爹鞋韩版潮流学生百搭休闲鞋', '/6a3c5ce4-4a8b-410b-8b00-5b6e60e643ef.jpg', 22.00, 33.00, 0, 443, '2022-03-31 09:23:03', 3),
(11, 'JANE HARLOW男鞋运动鞋网面防臭老爹鞋韩版潮流学生百搭休闲鞋', '/22034b65-675f-4f65-8b02-2f5bfe235c28.jpg', 33.00, 44.00, 1, 234, '2022-03-31 09:23:57', 8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table cs.user
CREATE TABLE IF NOT EXISTS `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) DEFAULT NULL,
    `password` varchar(50) DEFAULT NULL,
    `nick` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table cs.user: ~1 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `nick`) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

CREATE TABLE IF NOT EXISTS `log`
(
    id          bigint auto_increment primary key,
    username    varchar(50)   null comment '用户名',
    operation   varchar(50)   null comment '用户操作',
    method      varchar(200)  null comment '请求方法',
    params      varchar(5000) null comment '请求参数',
    time        bigint        not null comment '执行时长(毫秒)',
    ip          varchar(64)   null comment 'IP地址',
    created_time datetime     null comment '创建时间',
    status      int default   1,
    error       varchar(500)  null
)comment '用户日志' charset = utf8;
/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - house_item
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `house_item`;

/*Table structure for table `t_bill_brand_item` */

CREATE TABLE `t_bill_brand_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_id` bigint(20) NOT NULL COMMENT 't_goods_bill表主键ID',
  `brand_id` bigint(20) NOT NULL COMMENT 't_brand表主键ID',
  `price` bigint(20) DEFAULT NULL COMMENT '价格（单位分）',
  `file_id` bigint(20) DEFAULT NULL COMMENT '图片ID',
  `status` varchar(8) DEFAULT 'NOR',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `t_bill_brand_item` */

insert  into `t_bill_brand_item`(`id`,`bill_id`,`brand_id`,`price`,`file_id`,`status`,`create_time`,`last_update_time`) values (1,1,1,2699,6,'NOR','2018-09-19 17:09:50','2018-09-19 17:09:50'),(2,2,1,3899,7,'NOR','2018-09-19 17:09:53','2018-09-19 17:09:53'),(3,3,1,3801,8,'NOR','2018-09-19 17:09:57','2018-09-19 17:09:57'),(4,4,1,12327,9,'NOR','2018-09-19 17:10:09','2018-09-19 17:10:09'),(5,5,1,8189,10,'NOR','2018-09-19 17:10:11','2018-09-19 17:10:11'),(6,6,1,2996,11,'NOR','2018-09-19 17:10:14','2018-09-19 17:10:14'),(7,7,1,4233,12,'NOR','2018-09-19 17:10:17','2018-09-19 17:10:17'),(8,8,1,6070,13,'NOR','2018-09-19 17:11:21','2018-09-19 17:11:21'),(9,9,1,13415,14,'NOR','2018-09-19 17:11:23','2018-09-19 17:11:23'),(10,10,1,100,NULL,'NOR','2018-09-19 17:11:27','2018-09-19 17:11:27'),(11,1,2,1960,15,'NOR','2018-09-19 17:11:28','2018-09-19 17:11:28'),(12,2,2,3479,16,'NOR','2018-09-19 17:11:29','2018-09-19 17:11:29'),(13,3,2,3220,17,'NOR','2018-09-19 17:11:29','2018-09-19 17:11:29'),(14,4,2,9450,18,'NOR','2018-09-19 17:11:31','2018-09-19 17:11:31'),(15,5,2,6300,19,'NOR','2018-09-19 17:11:31','2018-09-19 17:11:31'),(16,6,2,2770,20,'NOR','2018-09-19 17:11:32','2018-09-19 17:11:32'),(17,7,2,3650,21,'NOR','2018-09-19 17:11:33','2018-09-19 17:11:33'),(18,8,2,4850,22,'NOR','2018-09-19 17:11:33','2018-09-19 17:11:33'),(19,9,2,5650,23,'NOR','2018-09-19 17:11:36','2018-09-19 17:11:36'),(20,10,2,100,NULL,'NOR','2018-09-19 17:11:38','2018-09-19 17:11:38'),(21,1,6,1660,24,'NOR','2018-09-19 17:39:03','2018-09-19 17:39:03'),(22,2,6,0,33,'NOR','2018-09-19 17:40:01','2018-09-19 17:40:01'),(23,3,6,1939,26,'NOR','2018-09-19 17:40:06','2018-09-19 17:40:06'),(24,4,6,5590,27,'NOR','2018-09-19 17:40:20','2018-09-19 17:40:20'),(25,5,6,2740,28,'NOR','2018-09-19 17:40:35','2018-09-19 17:40:35'),(26,6,6,1520,29,'NOR','2018-09-19 17:40:46','2018-09-19 17:40:46'),(27,7,6,2350,30,'NOR','2018-09-19 17:41:03','2018-09-19 17:41:03'),(28,8,6,3170,31,'NOR','2018-09-19 17:41:17','2018-09-19 17:41:17'),(29,9,6,0,32,'NOR','2018-09-19 17:41:35','2018-09-19 17:41:35'),(30,10,6,100,NULL,'NOR','2018-09-19 17:41:38','2018-09-19 17:41:38'),(31,1,7,3979,NULL,'NOR','2018-09-19 17:42:25','2018-09-19 17:42:25'),(32,2,7,6450,NULL,'NOR','2018-09-19 17:42:42','2018-09-19 17:42:42'),(33,3,7,6830,NULL,'NOR','2018-09-19 17:42:50','2018-09-19 17:42:50'),(34,4,7,13180,NULL,'NOR','2018-09-19 17:43:07','2018-09-19 17:43:07'),(35,5,7,10100,NULL,'NOR','2018-09-19 17:43:18','2018-09-19 17:43:18'),(36,6,7,4780,NULL,'NOR','2018-09-19 17:43:40','2018-09-19 17:43:40'),(37,7,7,6750,NULL,'NOR','2018-09-19 17:43:52','2018-09-19 17:43:52'),(38,8,7,8530,NULL,'NOR','2018-09-19 17:44:03','2018-09-19 17:44:03'),(39,9,7,0,NULL,'NOR','2018-09-19 17:44:27','2018-09-19 17:44:27'),(40,10,7,100,NULL,'NOR','2018-09-19 17:44:29','2018-09-19 17:44:29');

/*Table structure for table `t_brand` */

CREATE TABLE `t_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '品牌名称',
  `note` varchar(128) NOT NULL DEFAULT '' COMMENT '备注',
  `status` varchar(8) NOT NULL DEFAULT 'NOR',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_brand` */

insert  into `t_brand`(`id`,`name`,`note`,`status`,`create_time`,`last_update_time`) values (1,'施耐德','施耐德电气是全球500强，质量、安全方面是可靠的。有四个主打系列：如意，C86系列，丰尚，轻点。','NOR','2018-09-19 11:16:19','2018-09-19 11:16:19'),(2,'罗格朗','TCL---罗格朗由罗格朗集团收购TCL国际电工的全部股份发展而来，罗格朗在全球开关插座领域拥有高达19%的市场份额，年销售额近38亿欧元。','NOR','2018-09-19 11:19:42','2018-09-19 11:19:42'),(3,'正泰','正泰开关插座是由浙江正泰电工精心打造的，由于企业注重研发，在产品系列上是比较全面的，消费者可以根据自己的需求选用。','NOR','2018-09-19 13:14:25','2018-09-19 13:14:25'),(4,'西蒙','西蒙开关是来自西班牙的电气品牌，具有外观漂亮、手感好、安装方便、安全性高的优点，在我国的鸟巢和水立方都得到了应用。','NOR','2018-09-19 13:14:42','2018-09-19 13:14:42'),(5,'飞雕','飞雕品牌是国内成立较早的开关插座品牌，技术力量雄厚，曾在2008年荣获中国电开关行业唯一标志性品牌，质量绝对是有保障的。','NOR','2018-09-19 13:14:54','2018-09-19 13:14:54'),(6,'公牛','作为国内开关插座的领军品牌，公牛开关插座在注重安全性和产品的丰富度方面的表现是大家有目共睹的，由此得到了大多数消费者的喜爱。','NOR','2018-09-19 13:15:08','2018-09-19 13:15:08'),(7,'西门子','国际上知名的电气品牌，西门子生产的开关插座不但在结构上更为科学和合理，在造型上也是非常的精美和时尚，完全可以满足不同家庭的实用和装饰需求。','NOR','2018-09-19 13:15:25','2018-09-19 13:15:25');

/*Table structure for table `t_goods_bill` */

CREATE TABLE `t_goods_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) NOT NULL COMMENT '关联清单类型',
  `name` varchar(32) NOT NULL COMMENT '清单名称',
  `num` int(8) NOT NULL COMMENT '清单数目',
  `status` varchar(8) NOT NULL DEFAULT 'NOR',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_goods_bill` */

insert  into `t_goods_bill`(`id`,`type_id`,`name`,`num`,`status`,`create_time`,`last_update_time`) values (1,1,'五孔插座',45,'NOR','2018-09-19 10:12:30','2018-09-19 10:12:30'),(2,1,'一开五孔插座',8,'NOR','2018-09-19 10:12:54','2018-09-19 10:12:54'),(3,1,'空调插座',5,'NOR','2018-09-19 10:13:07','2018-09-19 10:13:07'),(4,1,'电视+电脑',3,'NOR','2018-09-19 10:13:22','2018-09-19 10:13:22'),(5,1,'电脑插座',2,'NOR','2018-09-19 10:13:40','2018-09-19 10:13:40'),(6,1,'一开双控开关',10,'NOR','2018-09-19 10:14:06','2018-09-19 10:14:06'),(7,1,'二开双控开关',5,'NOR','2018-09-19 10:14:52','2018-09-19 10:14:52'),(8,1,'三开双控开关',1,'NOR','2018-09-19 10:15:18','2018-09-19 10:15:18'),(9,1,'一开多控开关',2,'NOR','2018-09-19 10:15:35','2018-09-19 10:15:35'),(10,1,'加长螺丝',50,'NOR','2018-09-19 10:15:54','2018-09-19 10:15:54');

/*Table structure for table `t_item_type` */

CREATE TABLE `t_item_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(8) NOT NULL DEFAULT 'NOR',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_item_type` */

insert  into `t_item_type`(`id`,`type`,`create_time`,`last_update_time`,`status`) values (1,'开关插座','2018-09-18 13:28:00','2018-09-18 13:28:00','NOR'),(5,'测试','2018-09-19 10:18:16','2018-09-19 10:18:16','NOR');

/*Table structure for table `t_type_brand_temp` */

CREATE TABLE `t_type_brand_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) NOT NULL COMMENT 't_item_type表主键ID',
  `brand_id` bigint(20) NOT NULL COMMENT 't_brand表主键ID',
  `status` varchar(8) NOT NULL DEFAULT 'NOR',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_type_brand_temp` */

insert  into `t_type_brand_temp`(`id`,`type_id`,`brand_id`,`status`,`create_time`,`last_update_time`) values (17,1,1,'NOR','2018-09-20 20:17:24','2018-09-20 20:17:24'),(18,1,2,'NOR','2018-09-20 20:17:24','2018-09-20 20:17:24'),(19,1,6,'NOR','2018-09-20 20:17:24','2018-09-20 20:17:24');

/*Table structure for table `t_upload_file` */

CREATE TABLE `t_upload_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '文件名称',
  `file` longblob COMMENT '文件',
  `status` varchar(8) DEFAULT 'NOR',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_upload_file` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
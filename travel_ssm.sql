/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.23-0ubuntu0.16.04.1 : Database - travel_ssm
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`travel_ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `travel_ssm`;

/*Table structure for table `citylist` */

DROP TABLE IF EXISTS `citylist`;

CREATE TABLE `citylist` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(20) NOT NULL,
  `province` varchar(10) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `yid` varchar(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  `cityNameEn` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `name` (`cityName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `hotel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hname` varchar(100) NOT NULL COMMENT '酒店名称',
  `address` text NOT NULL COMMENT '酒店地址',
  `city` varchar(255) NOT NULL COMMENT '城市',
  `tele` varchar(255) DEFAULT NULL,
  `detail` text,
  `ctripid` varchar(40) DEFAULT NULL,
  `tuniuid` varchar(40) DEFAULT NULL,
  `lvmamaid` varchar(40) DEFAULT NULL,
  `yilongid` varchar(40) DEFAULT NULL,
  `img` text,
  `services` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `lprice` int(11) DEFAULT NULL,
  `tip1` varchar(255) DEFAULT NULL,
  `tip2` varchar(255) DEFAULT NULL,
  `room` text,
  PRIMARY KEY (`hotel_id`),
  KEY `name` (`hname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1076 DEFAULT CHARSET=utf8 COMMENT='酒店表';

/*Table structure for table `notes` */

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `title` varchar(40) NOT NULL,
  `n_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(100) NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uri` varchar(255) NOT NULL,
  `hot` bigint(20) DEFAULT '0',
  PRIMARY KEY (`n_id`),
  UNIQUE KEY `only` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Table structure for table `rooms` */

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `type` varchar(30) NOT NULL,
  `r_id` bigint(20) NOT NULL,
  `person` int(11) NOT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `spider` */

DROP TABLE IF EXISTS `spider`;

CREATE TABLE `spider` (
  `name` varchar(50) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uname` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(30) NOT NULL,
  `city` varchar(20) NOT NULL,
  `gender` varchar(4) NOT NULL,
  `tele` varchar(12) NOT NULL,
  `mark` text,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name` (`uname`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

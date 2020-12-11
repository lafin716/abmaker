/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.0.17-MariaDB : Database - accountbook_maker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`accountbook_maker` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `accountbook_maker`;

/*Table structure for table `account_list` */

DROP TABLE IF EXISTS `account_list`;

CREATE TABLE `account_list` (
  `account_seq` int(11) NOT NULL AUTO_INCREMENT,
  `user_seq` int(11) DEFAULT NULL,
  `account_name` varchar(50) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `account_type` enum('main','form','page') DEFAULT NULL,
  `link_seq` int(11) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`account_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `account_list` */

/*Table structure for table `account_log_list` */

DROP TABLE IF EXISTS `account_log_list`;

CREATE TABLE `account_log_list` (
  `account_log_seq` int(11) NOT NULL AUTO_INCREMENT,
  `comp_seq` int(11) DEFAULT NULL,
  `account_seq` int(11) DEFAULT NULL,
  `account_name` varchar(50) DEFAULT NULL,
  `comp_name` varchar(50) DEFAULT NULL,
  `type` enum('plus','minus') DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`account_log_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `account_log_list` */

/*Table structure for table `component_list` */

DROP TABLE IF EXISTS `component_list`;

CREATE TABLE `component_list` (
  `comp_seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_seq` int(11) DEFAULT NULL,
  `comp_name` varchar(50) DEFAULT NULL,
  `type` enum('plus','minus') DEFAULT NULL,
  `is_writable` tinyint(1) DEFAULT NULL,
  `is_readonly` tinyint(1) DEFAULT NULL,
  `comp_content` text,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`comp_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `component_list` */

/*Table structure for table `component_location_link` */

DROP TABLE IF EXISTS `component_location_link`;

CREATE TABLE `component_location_link` (
  `comp_set_seq` int(11) NOT NULL AUTO_INCREMENT,
  `comp_seq` int(11) DEFAULT NULL,
  `page_seq` int(11) DEFAULT NULL,
  `comp_order` text,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`comp_set_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `component_location_link` */

/*Table structure for table `form_list` */

DROP TABLE IF EXISTS `form_list`;

CREATE TABLE `form_list` (
  `form_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '양식번호',
  `user_seq` int(11) DEFAULT NULL,
  `form_title` varchar(50) DEFAULT NULL,
  `is_main` tinyint(1) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`form_seq`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `form_list` */

insert  into `form_list`(`form_seq`,`user_seq`,`form_title`,`is_main`,`regist_date`) values 
(10,1,'weeq',0,'2020-12-11 14:52:00'),
(9,1,'123',0,'2020-12-11 14:51:56'),
(8,1,'월별양식',0,'2020-12-11 13:47:05'),
(7,1,'필수양식',1,'2020-12-11 13:46:57'),
(11,1,'3333',0,'2020-12-11 14:52:04'),
(12,1,'1111',0,'2020-12-11 14:52:08'),
(13,1,'rew',0,'2020-12-11 14:52:11'),
(14,1,'rew',0,'2020-12-11 14:52:13'),
(15,1,'dasdsa',0,'2020-12-11 14:52:19'),
(16,1,'1111',0,'2020-12-11 14:52:29'),
(17,1,'dsada',0,'2020-12-11 14:52:34');

/*Table structure for table `page_list` */

DROP TABLE IF EXISTS `page_list`;

CREATE TABLE `page_list` (
  `page_seq` int(11) NOT NULL AUTO_INCREMENT,
  `form_seq` int(11) DEFAULT NULL,
  `page_title` varchar(50) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`page_seq`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

/*Data for the table `page_list` */

/*Table structure for table `user_list` */

DROP TABLE IF EXISTS `user_list`;

CREATE TABLE `user_list` (
  `user_seq` int(11) NOT NULL AUTO_INCREMENT,
  `user_pw` varchar(255) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_status` enum('alive','dormant','lock','change','delete') DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_seq`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_list` */

insert  into `user_list`(`user_seq`,`user_pw`,`user_name`,`user_email`,`user_status`,`regist_date`) values 
(1,'*11845FCC23D8B114C4ADE3EE4503A22FC3FA08B7','박재욱','lafin716@naver.com','alive','2020-12-03 00:10:42'),
(3,'d41d8cd98f00b204e9800998ecf8427e','','','alive','2020-12-03 16:46:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

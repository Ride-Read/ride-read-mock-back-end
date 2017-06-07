-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: qiyue
-- ------------------------------------------------------
-- Server version	5.5.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_collection`
--

DROP TABLE IF EXISTS `t_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `mid` int(11) NOT NULL COMMENT '阅圈id',
  `uid` int(11) NOT NULL,
  `first_picture` varchar(125) NOT NULL COMMENT '阅圈第一张图片',
  `username` varchar(45) NOT NULL COMMENT '用户名',
  `type` int(1) NOT NULL COMMENT '收藏类型',
  `msg` text COMMENT '阅圈内容信息',
  `create_at` datetime NOT NULL COMMENT '收藏时间',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `face_url` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_collection`
--

LOCK TABLES `t_collection` WRITE;
/*!40000 ALTER TABLE `t_collection` DISABLE KEYS */;
INSERT INTO `t_collection` VALUES (1,233,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170519053127.jpg','书林',0,'翻倍黑马，帅气','2017-05-22 03:03:11','2017-05-21 19:03:11','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg'),(2,212,13,'http://om1ccbp21.bkt.clouddn.com/moment_33_20170512005659682_0.jpg','BLACK HOUSE',0,'纪念这一日','2017-05-22 03:04:27','2017-05-21 19:04:27','http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg'),(3,255,46,'http://om1ccbp21.bkt.clouddn.com/moment_46_20170527142130001_0.jpg','yayiji',3,'众创','2017-05-30 18:12:15','2017-05-30 10:12:15','http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg');
/*!40000 ALTER TABLE `t_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '评论人的id',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '评论者用户名',
  `moment_id` int(11) NOT NULL COMMENT '阅圈id',
  `msg` text COLLATE utf8_bin NOT NULL COMMENT '评论内容',
  `face_url` varchar(125) COLLATE utf8_bin NOT NULL COMMENT '评论者头像',
  `reply_uid` int(11) DEFAULT NULL COMMENT '回复评论的用户id',
  `reply_username` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '评论回复用户名',
  `created_at` datetime NOT NULL COMMENT '评论创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='评论数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (133,13,'书林',178,'哈哈','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 13:39:38','2017-05-06 05:39:38'),(134,13,'书林',181,'这是哪里','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 14:58:02','2017-05-06 06:58:02'),(135,11,'18374911770',181,'会展中心','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',13,'书林','2017-05-06 16:37:24','2017-05-06 08:37:24'),(136,13,'书林',186,'哇塞','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 23:50:25','2017-05-06 15:50:25'),(137,13,'书林',182,'nice','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 23:50:54','2017-05-06 15:50:54'),(138,13,'书林',182,'对话框，聊一下','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 23:51:03','2017-05-06 15:51:03'),(139,13,'书林',182,'我测一下消息','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-06 23:51:12','2017-05-06 15:51:12'),(140,23,'伍海玮',182,'你这么吊','http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',23,'伍海玮','2017-05-07 01:59:06','2017-05-06 17:59:06'),(141,13,'书林',187,'回复一下我','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-07 17:09:36','2017-05-07 09:09:36'),(142,30,'13570238143',191,'草拟吗','http://om1ccbp21.bkt.clouddn.com/icon_20170508095530.jpg',NULL,NULL,'2017-05-08 09:57:48','2017-05-08 01:57:48'),(143,13,'书林',191,'回复一下我的动态，我看看有没有消息提醒','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-08 11:47:10','2017-05-08 03:47:10'),(144,13,'书林',197,'雕塑','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-09 11:32:17','2017-05-09 03:32:17'),(145,13,'书林',195,'产品测试如何','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-09 12:37:14','2017-05-09 04:37:14'),(146,13,'书林',190,'这是中山大学','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-09 12:37:45','2017-05-09 04:37:45'),(147,13,'书林',211,'酷','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-11 15:48:13','2017-05-11 07:48:13'),(148,11,'18374911770',210,'今天','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',NULL,NULL,'2017-05-12 11:23:06','2017-05-12 03:23:06'),(149,11,'18374911770',210,'可以','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:23:12','2017-05-12 03:23:12'),(150,11,'18374911770',210,'跳跳糖','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:24:03','2017-05-12 03:24:03'),(151,11,'18374911770',210,'地点','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',NULL,NULL,'2017-05-12 11:24:32','2017-05-12 03:24:32'),(152,11,'18374911770',210,'菲菲','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:24:47','2017-05-12 03:24:47'),(153,11,'18374911770',210,'公共','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',NULL,NULL,'2017-05-12 11:26:02','2017-05-12 03:26:02'),(154,11,'18374911770',210,'跳糖','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:26:12','2017-05-12 03:26:12'),(155,11,'18374911770',210,'偷偷','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:26:22','2017-05-12 03:26:22'),(156,11,'18374911770',197,'有意义','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',NULL,NULL,'2017-05-12 11:27:36','2017-05-12 03:27:36'),(157,11,'18374911770',197,'原因','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',13,'书林','2017-05-12 11:27:46','2017-05-12 03:27:46'),(158,11,'18374911770',197,'就会回家','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-12 11:27:57','2017-05-12 03:27:57'),(159,13,'书林',212,'今天在忙啥','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-12 14:08:50','2017-05-12 06:08:50'),(160,11,'18374911770',216,'现在就想知道是怎么','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',NULL,NULL,'2017-05-17 09:29:10','2017-05-17 01:29:10'),(161,11,'18374911770',216,'就复习复习','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg',11,'18374911770','2017-05-17 09:29:17','2017-05-17 01:29:17'),(162,13,'书林',224,'庞总棒','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-17 17:25:21','2017-05-17 09:25:21'),(163,13,'书林',235,'这是松山湖吗','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-20 16:32:43','2017-05-20 08:32:43'),(164,13,'书林',225,'nice','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-20 17:58:32','2017-05-20 09:58:32'),(165,13,'书林',240,'加油','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-22 14:28:03','2017-05-22 06:28:03'),(166,13,'书林',242,'瘦了好多，今天在哪休息？','http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg',NULL,NULL,'2017-05-22 23:50:45','2017-05-22 15:50:45'),(167,39,'13670113566',188,'不错','http://om1ccbp21.bkt.clouddn.com/icon_20170523181922.jpg',NULL,NULL,'2017-05-23 18:28:24','2017-05-23 10:28:24'),(168,39,'13670113566',188,'，看到我吗？','http://om1ccbp21.bkt.clouddn.com/icon_20170523181922.jpg',NULL,NULL,'2017-05-23 21:43:06','2017-05-23 13:43:06'),(169,13,'书林',244,'阅主任加油','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-24 20:36:11','2017-05-24 12:36:11'),(170,13,'书林',246,'帅气','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-24 23:55:04','2017-05-24 15:55:04'),(171,13,'书林',245,'哇，禅姐好酷','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-24 23:58:17','2017-05-24 15:58:17'),(172,13,'书林',249,'加油加油','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-25 13:32:07','2017-05-25 05:32:07'),(173,13,'书林',248,'大神大神','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-25 13:33:54','2017-05-25 05:33:54'),(174,13,'书林',250,'真棒','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-25 13:34:53','2017-05-25 05:34:53'),(175,13,'书林',251,'距离我153km','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-26 15:52:47','2017-05-26 07:52:47'),(176,45,'test',253,'rttrt','http://om1ccbp21.bkt.clouddn.com/face_test_20170527140617050.jpg',45,'test','2017-05-27 14:13:57','2017-05-27 06:13:57'),(177,45,'test',253,'guuhg','http://om1ccbp21.bkt.clouddn.com/face_test_20170527140617050.jpg',45,'test','2017-05-27 14:14:14','2017-05-27 06:14:14'),(178,46,'yayiji',252,'哈哈哈','http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg',13,'书林','2017-05-27 14:16:43','2017-05-27 06:16:43'),(179,13,'书林',236,'积极','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-27 23:57:41','2017-05-27 15:57:41'),(180,13,'书林',261,'这狗有个性','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-31 13:34:31','2017-05-31 05:34:31'),(181,13,'书林',258,'怎么了？','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-05-31 13:35:27','2017-05-31 05:35:27'),(182,13,'书林',267,'可爱','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-06-03 19:41:28','2017-06-03 11:41:28'),(183,13,'书林',273,'真棒','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-06-04 16:24:24','2017-06-04 08:24:24'),(184,33,'BLACK HOUSE',272,'嗨咯','http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg',23,'伍海玮','2017-06-04 16:39:50','2017-06-04 08:39:50'),(185,14,'请叫我，鱼干女',272,'环境不错。。','http://om1ccbp21.bkt.clouddn.com/face_请叫我，鱼干女_20170415164823006.jpg',23,'伍海玮','2017-06-04 20:38:58','2017-06-04 12:38:58'),(186,13,'书林',277,'牛逼','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg',NULL,NULL,'2017-06-05 15:17:27','2017-06-05 07:17:27');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_follow`
--

DROP TABLE IF EXISTS `t_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '粉丝列表id',
  `fid` int(11) NOT NULL COMMENT '粉丝用户id',
  `follower_face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '粉丝用户头像url',
  `follower_signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '粉丝用户签名',
  `follower_username` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '粉丝用户名',
  `tid` int(11) NOT NULL COMMENT '被粉用户id',
  `followed_face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '被关注人用户头像url',
  `followed_signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '被关注人用户签名',
  `followed_username` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '被关注人用户名',
  `followed_remark` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '关注备注',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='关注和粉丝列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_follow`
--

LOCK TABLES `t_follow` WRITE;
/*!40000 ALTER TABLE `t_follow` DISABLE KEYS */;
INSERT INTO `t_follow` VALUES (139,21,'http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','Airing',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-08 10:07:49','2017-05-08 02:07:49'),(140,11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',16,'http://om1ccbp21.bkt.clouddn.com/icon_20170508095030.jpg','要努力，保护自己的理想','15521337313',NULL,'2017-05-08 10:15:29','2017-05-08 02:15:29'),(141,11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',NULL,'2017-05-08 10:15:37','2017-05-08 02:15:37'),(142,23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',21,'http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','Airing',NULL,'2017-05-08 18:08:17','2017-05-08 10:08:17'),(143,16,'http://om1ccbp21.bkt.clouddn.com/icon_20170508095030.jpg','要努力，保护自己的理想','15521337313',11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',NULL,'2017-05-08 19:03:08','2017-05-08 11:03:08'),(144,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',28,'http://om1ccbp21.bkt.clouddn.com/icon_20170506221509.jpg',NULL,'18711695918',NULL,'2017-05-09 11:32:48','2017-05-09 03:32:48'),(145,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-09 11:33:16','2017-05-09 03:33:16'),(146,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',NULL,'2017-05-09 11:33:25','2017-05-09 03:33:25'),(147,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',16,'http://om1ccbp21.bkt.clouddn.com/icon_20170508095030.jpg','要努力，保护自己的理想','15521337313',NULL,'2017-05-09 11:33:32','2017-05-09 03:33:32'),(148,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',21,'http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','Airing',NULL,'2017-05-09 12:38:09','2017-05-09 04:38:09'),(151,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',NULL,'2017-05-12 14:08:14','2017-05-12 06:08:14'),(153,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',34,'http://om1ccbp21.bkt.clouddn.com/icon_20170513162026.jpg',NULL,'18807557000',NULL,'2017-05-17 17:25:35','2017-05-17 09:25:35'),(154,23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-17 18:24:52','2017-05-17 10:24:52'),(155,11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',24,'http://om1ccbp21.bkt.clouddn.com/face_荒年_20170501123647909.jpg',NULL,'荒年',NULL,'2017-05-17 21:53:53','2017-05-17 13:53:53'),(156,38,'http://om1ccbp21.bkt.clouddn.com/icon_20170519052858.jpg',NULL,'13538640263',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',NULL,'2017-05-19 17:33:01','2017-05-19 09:33:01'),(157,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',38,'http://om1ccbp21.bkt.clouddn.com/icon_20170519052858.jpg',NULL,'13538640263',NULL,'2017-05-19 17:36:28','2017-05-19 09:36:28'),(158,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',27,'http://om1ccbp21.bkt.clouddn.com/icon_20170506011839.jpg',NULL,'18320734684',NULL,'2017-05-20 17:19:06','2017-05-20 09:19:06'),(159,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',24,'http://om1ccbp21.bkt.clouddn.com/face_荒年_20170501123647909.jpg',NULL,'荒年',NULL,'2017-05-20 17:56:51','2017-05-20 09:56:51'),(160,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','书林',31,'http://om1ccbp21.bkt.clouddn.com/icon_20170509143558.jpg',NULL,'15521319867',NULL,'2017-05-22 03:04:48','2017-05-21 19:04:48'),(161,11,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','18374911770',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-23 23:00:47','2017-05-23 15:00:47'),(163,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',40,'http://om1ccbp21.bkt.clouddn.com/face_520521_20170524233124799.jpg',NULL,'520521',NULL,'2017-05-24 23:55:20','2017-05-24 15:55:20'),(164,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',39,'http://om1ccbp21.bkt.clouddn.com/icon_20170523181922.jpg',NULL,'13670113566',NULL,'2017-05-24 23:58:00','2017-05-24 15:58:00'),(166,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',44,'http://om1ccbp21.bkt.clouddn.com/',NULL,'13535088091',NULL,'2017-05-25 13:35:19','2017-05-25 05:35:19'),(167,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',NULL,'2017-05-25 19:47:13','2017-05-25 11:47:13'),(170,10,'http://om1ccbp21.bkt.clouddn.com/face_sky7_20170417234450098.jpg','随遇而安','逗逼了吧',44,'http://om1ccbp21.bkt.clouddn.com/',NULL,'13535088091',NULL,'2017-05-26 16:11:49','2017-05-26 08:11:49'),(171,10,'http://om1ccbp21.bkt.clouddn.com/face_sky7_20170417234450098.jpg','随遇而安','逗逼了吧',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',NULL,'2017-05-26 16:46:50','2017-05-26 08:46:50'),(172,46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg',NULL,'yayiji',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-27 14:22:12','2017-05-27 06:22:12'),(173,46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg',NULL,'yayiji',21,'http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','Airing',NULL,'2017-05-27 14:22:28','2017-05-27 06:22:28'),(174,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',45,'http://om1ccbp21.bkt.clouddn.com/face_test_20170527140617050.jpg',NULL,'test',NULL,'2017-05-27 14:23:10','2017-05-27 06:23:10'),(175,46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg',NULL,'yayiji',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',NULL,'2017-05-27 14:23:51','2017-05-27 06:23:51'),(181,36,'http://om1ccbp21.bkt.clouddn.com/icon_20170519053015.jpg',NULL,'15521161391',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-05-30 16:52:44','2017-05-30 08:52:44'),(182,36,'http://om1ccbp21.bkt.clouddn.com/icon_20170519053015.jpg',NULL,'15521161391',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',NULL,'2017-05-30 16:54:07','2017-05-30 08:54:07'),(183,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg','哼哼唧唧','yayiji',NULL,'2017-05-31 10:46:19','2017-05-31 02:46:19'),(184,13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',47,'http://om1ccbp21.bkt.clouddn.com/icon_20170530231146.jpg',NULL,'15626164052',NULL,'2017-05-31 10:49:47','2017-05-31 02:49:47'),(185,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',23,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'伍海玮',NULL,'2017-06-04 16:39:40','2017-06-04 08:39:40'),(186,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',13,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','书林',NULL,'2017-06-04 16:40:45','2017-06-04 08:40:45'),(187,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',NULL,'2017-06-04 16:56:41','2017-06-04 08:56:41'),(188,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg','哼哼唧唧','yayiji',NULL,'2017-06-04 17:01:50','2017-06-04 09:01:50'),(189,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',31,'http://om1ccbp21.bkt.clouddn.com/icon_20170509143558.jpg',NULL,'15521319867',NULL,'2017-06-04 17:01:54','2017-06-04 09:01:54'),(190,33,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','BLACK HOUSE',21,'http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','Airing',NULL,'2017-06-04 17:02:15','2017-06-04 09:02:15'),(191,50,'http://om1ccbp21.bkt.clouddn.com/face_HelloKitty_20170603071507071.jpg',NULL,'lee_wan',46,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg','哼哼唧唧','yayiji',NULL,'2017-06-04 17:25:30','2017-06-04 09:25:30');
/*!40000 ALTER TABLE `t_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_follower`
--

DROP TABLE IF EXISTS `t_follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_follower` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '粉丝列表id',
  `fid` int(11) NOT NULL COMMENT '粉丝用户id',
  `tid` int(11) NOT NULL COMMENT '被粉用户id',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `nickname` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='粉丝列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_follower`
--

LOCK TABLES `t_follower` WRITE;
/*!40000 ALTER TABLE `t_follower` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_following`
--

DROP TABLE IF EXISTS `t_following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_following` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注列表id',
  `fid` int(11) NOT NULL COMMENT '粉丝id',
  `tid` int(11) DEFAULT NULL COMMENT '被粉用户id',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `nickname` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `remark` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '关注备注',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='关注列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_following`
--

LOCK TABLES `t_following` WRITE;
/*!40000 ALTER TABLE `t_following` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_following` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_moment`
--

DROP TABLE IF EXISTS `t_moment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_moment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '阅圈id',
  `user_id` int(11) NOT NULL COMMENT '阅圈拥有者的ID',
  `type` int(11) NOT NULL COMMENT '阅圈类型:0-纯文本，1-图文，2-视频文字',
  `cover` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频封面图url',
  `msg` text CHARACTER SET utf8 COMMENT '阅圈内容',
  `video` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频url',
  `thumbs` text COLLATE utf8_bin COMMENT '缩略图url',
  `pictures` text COLLATE utf8_bin COMMENT '图片url',
  `latitude` decimal(11,7) DEFAULT NULL COMMENT '用户发表阅圈地点的纬度',
  `longitude` decimal(11,7) DEFAULT NULL COMMENT '用户发表阅圈地点的经度',
  `created_at` datetime NOT NULL COMMENT '阅圈创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '阅圈修改时间',
  `moment_location` varchar(125) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='阅圈数据列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_moment`
--

LOCK TABLES `t_moment` WRITE;
/*!40000 ALTER TABLE `t_moment` DISABLE KEYS */;
INSERT INTO `t_moment` VALUES (175,11,0,NULL,'Djdkskdkk ',NULL,NULL,NULL,22.6465256,114.0369429,'2017-05-06 11:24:21','2017-05-06 03:24:21',''),(176,16,0,NULL,'看看',NULL,NULL,NULL,22.6461492,114.0376940,'2017-05-06 11:26:55','2017-05-06 03:26:55',''),(177,11,0,NULL,'I\'m just trying ',NULL,NULL,NULL,23.7858340,113.4064170,'2017-05-06 11:39:24','2017-05-06 03:39:24',''),(178,13,1,NULL,'老朋友',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506120553.jpg',23.0435512,113.3669378,'2017-05-06 12:06:11','2017-05-06 04:06:11','广州市.广州大学'),(179,13,1,NULL,'工作',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506013752.jpg',23.0404431,113.3695869,'2017-05-06 13:38:27','2017-05-06 05:38:27','广州市.广州大学-纯粹艺术空间'),(180,27,0,NULL,' \n\n\n\n慢慢',NULL,NULL,NULL,23.1448815,113.4061502,'2017-05-06 14:28:29','2017-05-06 06:28:29',''),(181,11,1,NULL,'医疗展',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506022923.jpg',22.5323247,114.0567351,'2017-05-06 14:29:27','2017-05-06 06:29:27',''),(182,23,1,NULL,'test',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506080854.jpg',23.0388752,113.3825139,'2017-05-06 20:09:02','2017-05-06 12:09:02','广州市.大学城中环西路'),(183,13,1,NULL,'阅主任',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506081659.jpg',22.6349758,113.7850183,'2017-05-06 20:17:14','2017-05-06 12:17:14','深圳市.深圳市宝安国际机场'),(184,13,1,NULL,'以后就用你啦',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506084659.jpg',22.5420664,113.9338888,'2017-05-06 20:47:21','2017-05-06 12:47:21','深圳市.深南大道'),(185,13,1,NULL,'拍了一下腾讯大厦',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506085307.jpg',22.5424147,113.9364312,'2017-05-06 20:53:33','2017-05-06 12:53:33','深圳市.深南大道'),(186,28,1,NULL,'呵呵',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506222011.jpg',23.0404281,113.2761047,'2017-05-06 22:20:33','2017-05-06 14:20:33','广州市.洛浦街道'),(187,23,1,NULL,'擦',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170507020237765_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170507020243868_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170507020252676_0.jpg',23.0411300,113.3943050,'2017-05-07 02:02:55','2017-05-06 18:02:55','广东省·广州市·番禺区·大学城中环西路'),(188,13,1,NULL,'赛格广场',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170507050837.jpg',22.5434303,114.0822311,'2017-05-07 17:08:51','2017-05-07 09:08:51','深圳市.中航中心大厦'),(189,16,0,NULL,'回去了',NULL,NULL,NULL,22.5424192,113.9412181,'2017-05-07 19:04:35','2017-05-07 11:04:35',''),(190,21,1,NULL,'你在哪儿？',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508094637.jpg',31.7509056,116.4908866,'2017-05-08 09:46:54','2017-05-08 01:46:54','六安市.黄大街'),(191,16,1,NULL,'我在大学城',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508095627.jpg',23.0390356,113.3828594,'2017-05-08 09:56:30','2017-05-08 01:56:30','广州市.大学城中环西路'),(192,13,1,NULL,'出发',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508102256.jpg',22.6036996,113.8424957,'2017-05-08 10:23:10','2017-05-08 02:23:10','深圳市.固戍二路16号'),(193,13,1,NULL,'东莞',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508114605.jpg',22.8098723,113.8183939,'2017-05-08 11:46:18','2017-05-08 03:46:18','东莞市.莞长路'),(194,13,1,NULL,'松山湖跟大学城一样美',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508124155.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170508124205.jpg',22.9070653,113.8594666,'2017-05-08 12:42:58','2017-05-08 04:42:58','东莞市.大岭山镇'),(195,23,0,NULL,'test',NULL,NULL,NULL,23.0415767,113.3897803,'2017-05-08 16:40:52','2017-05-08 08:40:52',''),(196,13,1,NULL,'热情的东莞人民',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508111123.jpg',22.8950146,113.9163118,'2017-05-08 23:11:52','2017-05-08 15:11:52','东莞市.松山湖管委会'),(197,11,1,NULL,'猜猜是什么',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509103029.jpg',22.6584251,114.0414364,'2017-05-09 10:30:54','2017-05-09 02:30:54','深圳市.龙华街道'),(198,13,1,NULL,'东莞理工大学创新创业学院',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509122939.jpg',22.9114307,113.8696475,'2017-05-09 12:30:26','2017-05-09 04:30:26','东莞市.北二路'),(199,31,0,NULL,'？',NULL,NULL,NULL,23.1345510,113.2693222,'2017-05-09 14:44:51','2017-05-09 06:44:51',''),(200,13,1,NULL,'东莞虎门轮渡\n\n营运至22：30',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509050837.jpg',22.8177080,113.6103816,'2017-05-09 17:09:38','2017-05-09 09:09:38','东莞市.轮渡路'),(201,13,1,NULL,'有点污浊',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509051638.jpg',22.8158276,113.5996380,'2017-05-09 17:16:58','2017-05-09 09:16:58',''),(202,31,0,NULL,'唉',NULL,NULL,NULL,23.1173573,113.2795886,'2017-05-09 17:18:57','2017-05-09 09:18:57',''),(203,13,1,NULL,'过河',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509051949.jpg',22.8123692,113.5935101,'2017-05-09 17:20:01','2017-05-09 09:20:01',''),(204,23,1,NULL,'test',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509052214.jpg',23.0507976,113.3635930,'2017-05-09 17:22:33','2017-05-09 09:22:33','广州市.S105南沙港快速'),(205,16,1,NULL,'嘿嘿',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509174303.jpg',22.5811780,113.9090348,'2017-05-09 17:43:09','2017-05-09 09:43:09',''),(206,13,1,NULL,'南沙',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509070353.jpg',22.8044119,113.5282020,'2017-05-09 19:04:09','2017-05-09 11:04:09',''),(207,16,1,NULL,'回到了',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509215450.jpg',23.0452505,113.3952727,'2017-05-09 21:55:06','2017-05-09 13:55:06','广州市.大学城中心南大街'),(208,16,1,NULL,'哈哈',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170510103334.jpg',22.5811544,113.9090878,'2017-05-10 10:33:53','2017-05-10 02:33:53',''),(209,13,0,NULL,'华工生活区',NULL,NULL,NULL,23.0539438,113.3987388,'2017-05-10 17:03:57','2017-05-10 09:03:57',''),(210,11,1,NULL,'游戏',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170511020530.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170511020544.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170511020559.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170511020617.jpg',22.6464225,114.0368354,'2017-05-11 02:06:31','2017-05-10 18:06:31','深圳市.油园新村北四巷'),(211,31,1,NULL,'開心',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170511121657.jpg',23.1151581,113.3225250,'2017-05-11 12:17:05','2017-05-11 04:17:05',''),(212,33,1,NULL,'纪念这一日',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_33_20170512005659682_0.jpg',23.0402740,113.3739370,'2017-05-12 00:57:19','2017-05-11 16:57:19','广东省·广州市·番禺区·大学城中环西路'),(213,13,1,NULL,'来往佛山',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170512020713.jpg',23.0564739,113.2617780,'2017-05-12 14:07:27','2017-05-12 06:07:27',''),(214,13,1,NULL,'广东院士高峰年会',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170512061806.jpg',22.9889465,113.3597583,'2017-05-12 18:18:28','2017-05-12 10:18:28',''),(215,28,1,NULL,'Qqqq',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513091226.jpg',23.0702708,113.2865576,'2017-05-13 09:12:37','2017-05-13 01:12:37',''),(216,11,1,NULL,'分享',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513022714.jpg',22.5210430,113.9338588,'2017-05-13 14:27:55','2017-05-13 06:27:55','深圳市.后海大道112号'),(217,34,0,NULL,'好',NULL,NULL,NULL,22.5945078,114.4699330,'2017-05-13 16:21:22','2017-05-13 08:21:22',''),(218,13,1,NULL,'约骑联盟',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513055050.jpg',22.5938556,114.4703589,'2017-05-13 17:51:36','2017-05-13 09:51:36',''),(219,16,0,NULL,'我在这……',NULL,NULL,NULL,23.2932532,113.2957496,'2017-05-13 17:57:07','2017-05-13 09:57:07',''),(220,16,1,NULL,'...........',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513180441.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170513180452.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170513180459.jpg',23.2932494,113.2956816,'2017-05-13 18:05:19','2017-05-13 10:05:19',''),(221,13,1,NULL,'白鹭单车汇',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513085643.jpg',22.5949793,114.4702155,'2017-05-13 20:56:50','2017-05-13 12:56:50',''),(222,13,1,NULL,'约骑联盟',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513090752.jpg',22.5923984,114.4723707,'2017-05-13 21:07:56','2017-05-13 13:07:56',''),(223,13,1,NULL,'漠客',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170515061111.jpg',22.7155739,113.8545848,'2017-05-15 18:11:29','2017-05-15 10:11:29',''),(224,34,1,NULL,'米骑',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170516174750.jpg',22.5473175,114.0771027,'2017-05-16 17:47:58','2017-05-16 09:47:58','深圳市.华富街道'),(225,23,1,NULL,'work',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170517182256983_0.jpg',23.0381840,113.3914640,'2017-05-17 18:23:05','2017-05-17 10:23:05','广东省·广州市·番禺区·南亭村大街'),(226,24,1,NULL,'积极',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_24_20170517215208079_0.jpg',23.1142380,113.3723060,'2017-05-17 21:52:21','2017-05-17 13:52:21','广东省·广州市·天河区·椰林路'),(227,35,0,NULL,'骑行中国，阅览天下',NULL,NULL,NULL,23.0402766,113.3695790,'2017-05-17 23:21:48','2017-05-17 15:21:48',''),(228,11,0,NULL,'路上',NULL,NULL,NULL,22.8966835,113.9232738,'2017-05-18 20:16:05','2017-05-18 12:16:05','广东省.阿里山路与G94珠三角环线高速交叉口'),(229,11,0,NULL,'签到',NULL,NULL,NULL,23.0496684,113.7647634,'2017-05-18 20:49:52','2017-05-18 12:49:52','广东省.砂炮街'),(230,11,0,NULL,'你就知道啦……',NULL,NULL,NULL,23.2832489,113.3558593,'2017-05-19 09:54:57','2017-05-19 01:54:57','广东省.鸣先乐器'),(231,31,1,NULL,'開會喔',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519111221.jpg',23.1180611,113.3256392,'2017-05-19 11:12:30','2017-05-19 03:12:30',''),(232,16,0,NULL,' 假的假的八点半还是',NULL,NULL,NULL,22.5811169,113.9090781,'2017-05-19 11:44:17','2017-05-19 03:44:17',''),(233,13,1,NULL,'翻倍黑马，帅气',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519053127.jpg',23.0484584,113.3689687,'2017-05-19 17:32:13','2017-05-19 09:32:13','广州市.华洲街道'),(234,38,0,NULL,'刷个圈',NULL,NULL,NULL,22.9312254,113.8861024,'2017-05-19 19:40:46','2017-05-19 11:40:46','东莞市.松山湖图书馆'),(235,38,1,NULL,'休闲骑',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519074800.jpg',22.9221266,113.8725374,'2017-05-19 19:48:51','2017-05-19 11:48:51','东莞市.鹭栖湖'),(236,13,1,NULL,'智库车间',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170520043134.jpg',23.1241302,113.3764195,'2017-05-20 16:31:49','2017-05-20 08:31:49',''),(237,11,0,NULL,'记录',NULL,NULL,NULL,28.9922004,113.2687448,'2017-05-21 13:27:16','2017-05-21 05:27:16','湖南省.西冲屋'),(238,23,1,NULL,'出发了',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170521142438394_0.jpg',23.0334480,113.3793480,'2017-05-21 14:24:39','2017-05-21 06:24:39','广东省·广州市·番禺区·S105南沙港快速'),(239,33,0,NULL,'好困=_=',NULL,NULL,NULL,23.0392350,113.3670290,'2017-05-22 10:06:10','2017-05-22 02:06:10','广东省·广州市·番禺区·大学城环校西路'),(240,23,1,NULL,'深圳',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170522130642928_0.jpg',22.7565660,113.8208380,'2017-05-22 13:07:07','2017-05-22 05:07:07','广东省·深圳市·宝安区·西兴里'),(241,23,1,NULL,'沙井站',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170522132101600_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170522132125822_1.jpg',22.7358230,113.8251910,'2017-05-22 13:21:48','2017-05-22 05:21:48','广东省·深圳市·宝安区·宝安大道'),(242,23,1,NULL,'深圳大学城',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170522213347032_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170522213352907_1.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170522213438349_2.jpg',22.5947980,114.0074050,'2017-05-22 21:35:10','2017-05-22 13:35:10','广东省·深圳市·南山区·留仙大道'),(243,23,1,NULL,'美国巨头之一',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170523131359838_0.jpg',22.5405660,113.9563440,'2017-05-23 13:14:18','2017-05-23 05:14:18','广东省·深圳市·南山区·深南大道'),(244,23,1,NULL,'擎天一柱',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170523132410528_0.jpg',22.5384670,113.9448700,'2017-05-23 13:24:31','2017-05-23 05:24:31','广东省·深圳市·南山区·科技南一路'),(245,39,1,NULL,'人生若只如初见……',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170524230503.jpg',22.5709784,114.0339589,'2017-05-24 23:05:37','2017-05-24 15:05:37',''),(246,40,1,NULL,'广州过来也是醉了！明天有人一起南澳吗？',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_40_20170524233755652_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_40_20170524233758809_1.jpg,http://om1ccbp21.bkt.clouddn.com/moment_40_20170524233805757_2.jpg',22.6295920,114.1824410,'2017-05-24 23:38:41','2017-05-24 15:38:41','广东省·深圳市·龙岗区·牛始埔路'),(247,21,0,NULL,'我在学校',NULL,NULL,NULL,23.0439576,113.3691158,'2017-05-24 23:44:24','2017-05-24 15:44:24',''),(248,21,1,NULL,'玩游戏',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170524234705.jpg',23.0437755,113.3692293,'2017-05-24 23:47:23','2017-05-24 15:47:23',''),(249,33,1,NULL,'每日工作到深夜，只为给你带来更好的骑阅',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_33_20170524235716198_0.jpg',23.0409670,113.3723940,'2017-05-24 23:58:05','2017-05-24 15:58:05','广东省·广州市·番禺区·大学城中环西路'),(250,44,1,NULL,'风景不错',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170525133400.jpg',23.0436973,113.3671295,'2017-05-25 13:34:06','2017-05-25 05:34:06',''),(251,23,1,NULL,'666',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170525212645698_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170525212715650_0.jpg',23.7237460,114.6968780,'2017-05-25 21:27:36','2017-05-25 13:27:36','广东省·河源市·源城区·大岭背路'),(252,13,1,NULL,'高仿机器人',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170526035929.jpg',23.1061592,113.3584529,'2017-05-26 15:59:53','2017-05-26 07:59:53',''),(253,45,0,NULL,'1233456',NULL,NULL,NULL,23.0414120,113.3723680,'2017-05-27 14:08:10','2017-05-27 06:08:10','广东省·广州市·番禺区·大学城中环西路'),(254,33,0,NULL,'开会',NULL,NULL,NULL,23.0410800,113.3727160,'2017-05-27 14:21:26','2017-05-27 06:21:26','广东省·广州市·番禺区·大学城中环西路'),(255,46,1,NULL,'众创',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_46_20170527142130001_0.jpg',23.0415930,113.3726800,'2017-05-27 14:21:47','2017-05-27 06:21:47','广东省·广州市·番禺区·红棉路'),(256,23,1,NULL,'江西人很热情',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170529210406599_0.jpg',24.8602090,115.1818730,'2017-05-29 21:04:24','2017-05-29 13:04:24','江西省·赣州市·定南县·龙留段'),(257,39,1,NULL,'面包蟹',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170529231559.jpg',22.5712013,114.0337598,'2017-05-29 23:16:31','2017-05-29 15:16:31',''),(258,47,0,NULL,'睡不着',NULL,NULL,NULL,23.0430646,113.3884148,'2017-05-31 01:22:23','2017-05-30 17:22:23',''),(259,13,1,NULL,'肯德基',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170531103436.jpg',23.1191678,113.3847915,'2017-05-31 10:34:38','2017-05-31 02:34:38',''),(260,46,1,NULL,'狼来了',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_46_20170531125921003_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_46_20170531125933651_0.jpg',23.1203130,113.3939420,'2017-05-31 12:59:32','2017-05-31 04:59:32','广东省·广州市·天河区·车陂大塘中街'),(261,46,1,NULL,'嗯',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_46_20170531130454769_0.jpg',23.1203070,113.3939440,'2017-05-31 13:04:52','2017-05-31 05:04:52','广东省·广州市·天河区·车陂大塘中街'),(262,13,1,NULL,'出发出发，一路签到井冈山',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170531013323.jpg',23.1716521,113.3215339,'2017-05-31 13:33:45','2017-05-31 05:33:45',''),(263,13,1,NULL,'快到韶关了',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170531040325.jpg',24.4849805,113.5415442,'2017-05-31 16:03:37','2017-05-31 08:03:37',''),(264,13,0,NULL,'重新来到赣州',NULL,NULL,NULL,25.9207465,114.7236883,'2017-05-31 19:23:36','2017-05-31 11:23:36','赣州市.十八塘乡'),(265,13,0,NULL,'刚吃晚饭',NULL,NULL,NULL,26.1265196,114.6656420,'2017-05-31 20:32:14','2017-05-31 12:32:14',''),(266,13,1,NULL,'第一次来井冈山',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170531103754.jpg',26.6951539,114.2589734,'2017-05-31 22:38:05','2017-05-31 14:38:05',''),(267,46,1,NULL,'鸡鸡',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_46_20170602231127380_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_46_20170602231140430_0.jpg',23.1204610,113.3939310,'2017-06-02 23:11:37','2017-06-02 15:11:37','广东省·广州市·天河区·车陂大塘中街'),(268,13,1,NULL,'返程，广东省江西省交界',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170603074056.jpg',25.4000587,114.3986643,'2017-06-03 19:41:08','2017-06-03 11:41:08',''),(269,13,1,NULL,'清远站',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170603113145.jpg',23.8199594,113.1677270,'2017-06-03 23:31:59','2017-06-03 15:31:59',''),(270,13,1,NULL,'花都',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170604121209.jpg',23.3962482,113.2712598,'2017-06-04 00:12:15','2017-06-03 16:12:15',''),(271,13,1,NULL,'花都',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170604121314.jpg',23.3808120,113.2727083,'2017-06-04 00:13:32','2017-06-03 16:13:32','广州市.G45大广高速'),(272,23,1,NULL,'住宿',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/moment_23_20170604003256431_0.jpg,http://om1ccbp21.bkt.clouddn.com/moment_23_20170604003309064_0.jpg',27.9097110,116.7701080,'2017-06-04 00:33:11','2017-06-03 16:33:11','江西省·抚州市·金溪县·秀谷中大道'),(273,33,0,NULL,'666666安卓版终于上架了',NULL,NULL,NULL,23.0403330,113.3739390,'2017-06-04 00:43:06','2017-06-03 16:43:06','广东省·广州市·番禺区·大学城中环西路'),(274,33,0,NULL,'讨论新版本开发',NULL,NULL,NULL,23.0402650,113.3737780,'2017-06-04 17:01:20','2017-06-04 09:01:20','广东省·广州市·番禺区·大学城中环西路'),(275,13,1,NULL,'版本设计',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170605022034.jpg',23.0426228,113.3684235,'2017-06-05 14:20:45','2017-06-05 06:20:45',''),(276,13,0,NULL,'插图',NULL,NULL,NULL,23.0426256,113.3684506,'2017-06-05 15:14:33','2017-06-05 07:14:33',''),(277,13,0,NULL,'插图',NULL,NULL,NULL,23.0426601,113.3683245,'2017-06-05 15:15:37','2017-06-05 07:15:37',''),(278,13,1,NULL,'这阵容',NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170605064856.jpg,http://om1ccbp21.bkt.clouddn.com/icon_20170605064905.jpg',23.0740639,113.3865038,'2017-06-05 18:49:09','2017-06-05 10:49:09','');
/*!40000 ALTER TABLE `t_moment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_read_circle`
--

DROP TABLE IF EXISTS `t_read_circle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_read_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `comment` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `cover` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `pictures` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `thumbs_up` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `video` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `thumbs` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `msg` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='��Ȧ��������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_read_circle`
--

LOCK TABLES `t_read_circle` WRITE;
/*!40000 ALTER TABLE `t_read_circle` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_read_circle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_read_picture`
--

DROP TABLE IF EXISTS `t_read_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_read_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '阅图表id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `latitude` decimal(7,3) NOT NULL COMMENT '登录地点的纬度',
  `longitude` decimal(7,3) NOT NULL COMMENT '登录地点的经度',
  `msg` text COMMENT '签到信息',
  `create_at` datetime NOT NULL COMMENT '签到时间',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_read_picture`
--

LOCK TABLES `t_read_picture` WRITE;
/*!40000 ALTER TABLE `t_read_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_read_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sms_code`
--

DROP TABLE IF EXISTS `t_sms_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sms_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phonenumber` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `rand_code` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '短信验证码',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='短信验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sms_code`
--

LOCK TABLES `t_sms_code` WRITE;
/*!40000 ALTER TABLE `t_sms_code` DISABLE KEYS */;
INSERT INTO `t_sms_code` VALUES (50,'15913139608','510154','2017-05-06 03:59:36'),(51,'18320734684','413305','2017-05-06 05:00:03'),(52,'18711695918','340310','2017-05-06 14:14:18'),(53,'15099977415','310304','2017-05-07 06:04:11'),(54,'15989003758','350302','2017-05-07 07:11:00'),(55,'13570238143','100401','2017-05-08 01:54:43'),(56,'18374911770','122124','2017-05-08 15:18:53'),(57,'18374611740','034003','2017-05-08 15:50:30'),(58,'18374911770','514454','2017-05-08 15:50:44'),(59,'18374911778','242034','2017-05-08 15:51:48'),(60,'18819479106','424451','2017-05-09 01:46:31'),(61,'18819479106','333132','2017-05-09 04:15:04'),(62,'15521319867','153131','2017-05-09 06:35:25'),(63,'18676928204','452231','2017-05-10 13:40:29'),(64,'18676928204','402144','2017-05-10 13:44:33'),(65,'18307529089','001055','2017-05-11 15:51:46'),(66,'18807557000','201250','2017-05-13 08:19:20'),(67,'15626123084','412342','2017-05-15 10:49:07'),(68,'13539778515','330515','2017-05-17 15:16:59'),(69,'15626123084','203022','2017-05-18 01:41:45'),(70,'13538640263','214520','2017-05-19 09:25:57'),(71,'15626123084','532114','2017-05-19 09:29:10'),(72,'15521161391','113522','2017-05-19 09:29:18'),(73,'18825159711','311515','2017-05-22 16:35:03'),(74,'13670113566','402235','2017-05-23 10:16:57'),(75,'18664692227','433140','2017-05-24 15:29:50'),(76,'13265357808','424223','2017-05-24 15:30:33'),(77,'13890108503','405505','2017-05-24 16:13:11'),(78,'18148939105','331230','2017-05-24 19:22:49'),(79,'13535088091','412415','2017-05-25 05:31:21'),(80,'15018776859','051452','2017-05-27 06:04:27'),(81,'18677300251','013323','2017-05-27 06:07:52'),(82,'15626207639','132402','2017-05-27 15:45:36'),(83,'18344094021','034512','2017-05-28 13:04:59'),(84,'15626164052','352332','2017-05-30 15:11:04'),(85,'18613103713','503425','2017-06-01 16:29:29'),(86,'18677300251','003030','2017-06-02 20:57:11'),(87,'18022220001','004505','2017-06-02 23:30:44'),(88,'18022220002','253200','2017-06-02 23:36:37'),(89,'18022220001','110345','2017-06-02 23:43:14'),(90,'18022220001','251554','2017-06-03 00:47:20'),(91,'15122828074','104505','2017-06-03 06:00:00'),(92,'15122828074','141114','2017-06-03 06:59:14'),(93,'15122828074','555053','2017-06-03 07:28:47'),(94,'13422352971','124502','2017-06-03 07:29:10'),(95,'17695420199','512233','2017-06-03 07:32:27'),(96,'13422352971','112132','2017-06-03 07:33:48'),(97,'18022220003','041344','2017-06-03 08:49:59'),(98,'18022220001','032000','2017-06-03 10:37:31'),(99,'18022220001','553351','2017-06-03 10:42:20'),(100,'15122828074','311533','2017-06-03 10:43:09'),(101,'17695420199','440054','2017-06-03 10:44:26'),(102,'18677300251','510012','2017-06-03 11:10:07'),(103,'13416190664','442012','2017-06-03 16:34:04'),(104,'13416190667','515504','2017-06-03 16:34:21'),(105,'13250340686','004123','2017-06-04 12:30:00');
/*!40000 ALTER TABLE `t_sms_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_thumbs_up`
--

DROP TABLE IF EXISTS `t_thumbs_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_thumbs_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `user_id` int(11) NOT NULL COMMENT '点赞者id',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '点赞者用户名',
  `moment_id` int(11) NOT NULL COMMENT '点赞的阅圈id',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `created_at` datetime NOT NULL COMMENT '点赞创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '点赞修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=430 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='点赞数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_thumbs_up`
--

LOCK TABLES `t_thumbs_up` WRITE;
/*!40000 ALTER TABLE `t_thumbs_up` DISABLE KEYS */;
INSERT INTO `t_thumbs_up` VALUES (348,16,'15521337313',175,'http://om1ccbp21.bkt.clouddn.com/icon_20170425100310.jpg','要努力，保护自己的理想','2017-05-06 11:25:41','2017-05-06 03:25:41'),(349,13,'书林',181,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-06 14:57:55','2017-05-06 06:57:55'),(350,11,'18374911770',181,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','2017-05-06 19:25:33','2017-05-06 11:25:33'),(351,23,'伍海玮',185,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'2017-05-07 01:58:29','2017-05-06 17:58:29'),(352,23,'伍海玮',181,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'2017-05-07 01:59:11','2017-05-06 17:59:11'),(354,29,'15099977415',183,'http://om1ccbp21.bkt.clouddn.com/icon_20170507140743.jpg',NULL,'2017-05-07 14:47:30','2017-05-07 06:47:30'),(355,13,'书林',198,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-09 12:30:40','2017-05-09 04:30:40'),(356,23,'伍海玮',206,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'2017-05-09 22:17:13','2017-05-09 14:17:13'),(357,33,'BLACK HOUSE',211,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-12 00:07:27','2017-05-11 16:07:27'),(358,33,'BLACK HOUSE',210,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-12 00:07:30','2017-05-11 16:07:30'),(359,33,'BLACK HOUSE',204,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-12 00:07:35','2017-05-11 16:07:35'),(362,11,'18374911770',210,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','2017-05-12 11:30:00','2017-05-12 03:30:00'),(363,31,'15521319867',231,'http://om1ccbp21.bkt.clouddn.com/icon_20170509143558.jpg',NULL,'2017-05-19 11:21:24','2017-05-19 03:21:24'),(364,13,'书林',231,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-20 16:33:05','2017-05-20 08:33:05'),(365,13,'书林',226,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-20 17:18:40','2017-05-20 09:18:40'),(367,13,'书林',180,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-20 17:19:00','2017-05-20 09:19:00'),(368,13,'书林',204,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-20 17:19:55','2017-05-20 09:19:55'),(369,13,'书林',225,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-20 17:58:34','2017-05-20 09:58:34'),(370,13,'书林',241,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-22 14:27:43','2017-05-22 06:27:43'),(371,13,'书林',240,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-22 14:27:58','2017-05-22 06:27:58'),(372,13,'书林',242,'http://om1ccbp21.bkt.clouddn.com/icon_20170414113234.jpg','由心，而生','2017-05-22 23:50:22','2017-05-22 15:50:22'),(374,13,'书林',244,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-23 19:36:39','2017-05-23 11:36:39'),(375,11,'18374911770',244,'http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','2017-05-23 23:08:08','2017-05-23 15:08:08'),(376,41,'Cy.Kop',246,'http://om1ccbp21.bkt.clouddn.com/face_Cy.Kop_20170524233234509.jpg',NULL,'2017-05-24 23:39:54','2017-05-24 15:39:54'),(378,41,'Cy.Kop',244,'http://om1ccbp21.bkt.clouddn.com/face_Cy.Kop_20170524233234509.jpg',NULL,'2017-05-24 23:40:01','2017-05-24 15:40:01'),(379,41,'Cy.Kop',243,'http://om1ccbp21.bkt.clouddn.com/face_Cy.Kop_20170524233234509.jpg',NULL,'2017-05-24 23:40:03','2017-05-24 15:40:03'),(381,33,'BLACK HOUSE',247,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-24 23:52:29','2017-05-24 15:52:29'),(382,33,'BLACK HOUSE',246,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-24 23:52:32','2017-05-24 15:52:32'),(383,33,'BLACK HOUSE',244,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-24 23:52:44','2017-05-24 15:52:44'),(384,33,'BLACK HOUSE',243,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-05-24 23:52:46','2017-05-24 15:52:46'),(385,13,'书林',246,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-24 23:54:41','2017-05-24 15:54:41'),(386,13,'书林',245,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-24 23:58:21','2017-05-24 15:58:21'),(387,13,'书林',243,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-24 23:58:39','2017-05-24 15:58:39'),(388,13,'书林',222,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-25 11:27:33','2017-05-25 03:27:33'),(389,13,'书林',234,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-25 12:29:44','2017-05-25 04:29:44'),(390,13,'书林',249,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-25 13:32:02','2017-05-25 05:32:02'),(391,13,'书林',248,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-25 13:33:45','2017-05-25 05:33:45'),(393,13,'书林',247,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-25 19:05:38','2017-05-25 11:05:38'),(394,13,'书林',251,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-26 15:52:48','2017-05-26 07:52:48'),(400,13,'书林',211,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-27 13:12:28','2017-05-27 05:12:28'),(406,45,'test',253,'http://om1ccbp21.bkt.clouddn.com/face_test_20170527140617050.jpg',NULL,'2017-05-27 14:14:33','2017-05-27 06:14:33'),(407,46,'yayiji',252,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg',NULL,'2017-05-27 14:16:37','2017-05-27 06:16:37'),(410,13,'书林',254,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-27 14:27:10','2017-05-27 06:27:10'),(411,13,'书林',233,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-27 23:59:03','2017-05-27 15:59:03'),(413,13,'书林',253,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-28 00:53:09','2017-05-27 16:53:09'),(415,23,'伍海玮',243,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,'2017-05-29 21:05:46','2017-05-29 13:05:46'),(416,13,'书林',255,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-29 23:02:57','2017-05-29 15:02:57'),(417,13,'书林',256,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-29 23:03:03','2017-05-29 15:03:03'),(418,13,'书林',257,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-30 20:15:29','2017-05-30 12:15:29'),(419,13,'书林',250,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-31 10:55:24','2017-05-31 02:55:24'),(420,13,'书林',261,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-05-31 13:34:18','2017-05-31 05:34:18'),(421,13,'书林',267,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-06-03 19:41:21','2017-06-03 11:41:21'),(422,13,'书林',260,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-06-03 19:41:42','2017-06-03 11:41:42'),(424,50,'lee_wan',267,'http://om1ccbp21.bkt.clouddn.com/face_HelloKitty_20170603071507071.jpg',NULL,'2017-06-03 22:55:12','2017-06-03 14:55:12'),(425,13,'书林',273,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-06-04 16:24:15','2017-06-04 08:24:15'),(426,33,'BLACK HOUSE',272,'http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','2017-06-04 16:39:32','2017-06-04 08:39:32'),(427,14,'请叫我，鱼干女',272,'http://om1ccbp21.bkt.clouddn.com/face_请叫我，鱼干女_20170415164823006.jpg',NULL,'2017-06-04 20:38:53','2017-06-04 12:38:53'),(428,46,'yayiji',267,'http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg','哼哼唧唧','2017-06-04 21:49:48','2017-06-04 13:49:48'),(429,13,'书林',277,'http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','2017-06-05 15:17:18','2017-06-05 07:17:18');
/*!40000 ALTER TABLE `t_thumbs_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `ride_read_id` varchar(45) COLLATE utf8_bin NOT NULL COMMENT 'ride_read_id',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(125) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `sex` int(11) DEFAULT NULL COMMENT '用户性别',
  `school` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户学校',
  `phonenumber` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户手机号',
  `follower` int(11) DEFAULT '0' COMMENT '粉丝数',
  `following` int(11) DEFAULT '0' COMMENT '关注数',
  `token` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户token',
  `hometown` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户老家',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `tags` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户标签',
  `location` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地区',
  `birthday` datetime DEFAULT NULL COMMENT '用户生日',
  `career` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户职业',
  `longitude` decimal(11,7) DEFAULT NULL COMMENT '用户登录地点的经度',
  `latitude` decimal(11,7) DEFAULT NULL COMMENT '用户登录地点的纬度',
  `created_at` datetime NOT NULL COMMENT '用户注册时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户最后一次登录时间',
  `is_login` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (4,'qimupiao2','启幕票4444','94627fe8bfe78ee5857842104d9137e84eb0fedd',NULL,NULL,'13203356721',0,0,NULL,NULL,'localhost:8080/rideread/users/registe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-05 15:35:19','2017-05-08 02:06:33',0),(5,'qimupiao22','启幕票4444','94627fe8bfe78ee5857842104d9137e84eb0fedd',NULL,NULL,'13203356722',0,0,NULL,NULL,'localhost:8080/rideread/users/registe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-05 16:06:04','2017-04-21 06:17:19',0),(6,'qimupiao3','启幕票4444','8611872123a6b667c9729e936b7358858f695cde',NULL,NULL,'13203356720',0,0,'0efdb0833c2f19e624d6663095d367b346b96739',NULL,'localhost:8080/rideread/users/registe','hhh','student,child',NULL,NULL,NULL,113.3723070,23.1142480,'2017-04-05 20:17:34','2017-06-03 15:18:00',1),(10,'sky7','逗逼了吧','4de4d95fc854e7883bec112a191c867c0678ca42',1,'长沙','18148919070',0,2,'020779541f8e13a7e393ab50286866afeee5dea0','广州','http://om1ccbp21.bkt.clouddn.com/face_sky7_20170417234450098.jpg','随遇而安','理想主义','郴州','1990-01-26 00:00:00','IT/互联网/通信',112.2241110,25.9006610,'2017-04-08 18:14:09','2017-05-26 08:46:50',1),(11,'18374911770','18374911770','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',1,'湖南理工学院','18374911770',2,4,'e8900b00f0b0d583d2d11fa65fa9b56a373d520c','怀化','http://om1ccbp21.bkt.clouddn.com/icon_20170429012322.jpg','First','帅到爆','怀化','1993-01-01 00:00:00','IT/互联网/通信',114.0433533,22.6790630,'2017-04-13 22:25:45','2017-06-03 06:00:27',1),(12,'airinggursb','13178859429','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'13178859429',0,0,'b783f1b8163254c0425f71b8f53f5d30629bd93d',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170414112230.jpg',NULL,NULL,NULL,NULL,NULL,116.4886374,31.7539435,'2017-04-14 11:22:41','2017-05-06 04:47:37',0),(13,'rideread','书林','a22debbf994c0369e9c51009ecc9b17e85fade5a',1,'广州大学','15626401466',7,18,'5639af40baacd88b1a49da6b91535412379ff769','湛江','http://om1ccbp21.bkt.clouddn.com/icon_20170523033950.jpg','由心，而生','理想主义热血','广州','1995-03-20 00:00:00','IT/互联网/通信',113.3674360,23.0430646,'2017-04-14 11:34:01','2017-06-04 08:40:45',1),(14,'请叫我，鱼干女','请叫我，鱼干女','905f5be67082f07751c7a5f83d6df351d2956e69',0,NULL,'13250340686',0,0,'0bb073413975e6ad5eac6daa1c9737365fc21bec',NULL,'http://om1ccbp21.bkt.clouddn.com/face_请叫我，鱼干女_20170415164823006.jpg',NULL,NULL,NULL,NULL,NULL,113.4054830,23.1554610,'2017-04-15 16:48:23','2017-06-04 12:34:30',1),(15,'Luxy','Luxy','4de4d95fc854e7883bec112a191c867c0678ca42',0,NULL,'17620013310',0,0,'45349ffdcf92a848d9ef73c9548d1e1a8ae5efeb',NULL,'http://om1ccbp21.bkt.clouddn.com/face_Luxy_20170420214125505.jpg',NULL,NULL,NULL,NULL,NULL,113.3089146,23.1394806,'2017-04-20 21:41:28','2017-05-26 06:31:54',1),(16,'15521337313','15521337313','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',1,'广东工业大学','15521337313',2,1,'48881a056e86c762f2f6750f6cc510d68a7aa984','阳江','http://om1ccbp21.bkt.clouddn.com/icon_20170508095030.jpg','要努力，保护自己的理想','随性,理想主义,安静','广州','1992-02-18 00:00:00','IT/互联网/通信',113.3674470,23.0430755,'2017-04-25 22:03:25','2017-05-27 06:22:41',1),(18,'yaya','yaya','a792766c0bd759863d2ec12d1bb3557566d34ce3',0,NULL,'15626207639',0,0,NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/face_yaya_20170426234034416.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-26 23:40:37','2017-04-26 15:40:37',0),(19,'skyy','skyy','4de4d95fc854e7883bec112a191c867c0678ca42',0,NULL,'18148919099',0,0,NULL,NULL,'http://om1ccbp21.bkt.clouddn.com/face_skyy_20170427141934073.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-04-27 14:19:30','2017-04-27 06:19:30',0),(20,'棋木漂','棋木漂','8611872123a6b667c9729e936b7358858f695cde',0,NULL,'15914381430',0,0,'fe4be37b5e3e3b910c8d14d3a64f0ab21d74cd5f',NULL,'http://om1ccbp21.bkt.clouddn.com/face_棋木漂_20170427231553338.jpg',NULL,NULL,NULL,NULL,NULL,113.3851960,23.3537350,'2017-04-27 23:15:56','2017-05-08 02:06:49',0),(21,'Airing','Airing','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',1,'广州大学','18154099269',4,1,'086517ba4649b1417ad9ed9e3e4d3ac664ef1eb0','六安','http://om1ccbp21.bkt.clouddn.com/icon_20170506124810.jpg','ursb.me','喜欢简单','广州','1995-06-30 00:00:00','学生',116.4884759,31.7541881,'2017-04-28 21:51:27','2017-06-04 09:02:15',1),(22,'kkkk','15920369715','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'15920369715',0,0,'abe9876f5323d2d99d693daf069b5e9e7662e4a4',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170428104534.jpg',NULL,NULL,NULL,NULL,NULL,113.0000000,28.0000000,'2017-04-28 22:45:43','2017-04-28 17:00:50',1),(23,'伍海玮','伍海玮','bf9a5d687707349ba16f36d22c90a04b44c61868',0,NULL,'15521267357',7,2,'a691e42f61dcf8bce8cdcb4068da784fe4d5f19d',NULL,'http://om1ccbp21.bkt.clouddn.com/face_伍海玮_20170430203815125.jpg',NULL,NULL,NULL,NULL,NULL,113.3833146,23.0396447,'2017-04-30 20:38:16','2017-06-04 08:39:40',0),(24,'荒年','荒年','8611872123a6b667c9729e936b7358858f695cde',0,NULL,'13539431592',2,0,'1da61a05f327a694b6af56196da13bb87f1c9fb9',NULL,'http://om1ccbp21.bkt.clouddn.com/face_荒年_20170501123647909.jpg',NULL,NULL,NULL,NULL,NULL,113.2814940,23.0375310,'2017-05-01 12:36:48','2017-05-20 09:56:51',1),(25,'f19','f19','d28b6b63707bc4cc205f2634c817e3d565261a13',0,NULL,'18229848329',0,0,'995f8a20210b263c3ece3c233a23699c31f0c255',NULL,'http://om1ccbp21.bkt.clouddn.com/face_f19_20170501145315890.jpg',NULL,NULL,NULL,NULL,NULL,112.9215080,28.2389700,'2017-05-01 14:53:18','2017-05-02 01:54:39',1),(26,'1234556','15913139608','a714bd5e269af17dc45bb7a9282cbd51546bbff5',0,NULL,'15913139608',0,0,'af991fccfd0109320902587480191244f7cf5e99',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506120123.jpg',NULL,NULL,NULL,NULL,NULL,113.3674037,23.0430755,'2017-05-06 12:01:57','2017-05-06 04:02:32',1),(27,'Zfh888','18320734684','853beb71f0f4479640616f6016584a0ab43bc580',0,NULL,'18320734684',1,0,'f06205866c29585ed6182ea84dc4ba582ba44ed9',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506011839.jpg',NULL,NULL,NULL,NULL,NULL,113.3696348,23.0403497,'2017-05-06 13:19:48','2017-05-20 09:19:06',1),(28,'Yang','18711695918','1dbfc026a8a4fa450d6a0990d5b42d5f68c77b5c',0,NULL,'18711695918',1,0,'99da1b8f948d4332afb434f0359daca94adfb4e0',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170506221509.jpg',NULL,NULL,NULL,NULL,NULL,113.2761553,23.0402859,'2017-05-06 22:17:12','2017-05-09 03:32:48',1),(29,'6324','15099977415','3d915c27c60150a1707998c7ff2edf0872eb4875',0,NULL,'15099977415',0,0,'66d3d2ea53bcf15d437f3b53c5c12a0bf8dd9b70',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170507140743.jpg',NULL,NULL,NULL,NULL,NULL,113.3767098,23.1689075,'2017-05-07 14:07:57','2017-05-08 02:06:55',1),(30,'shuaifage','13570238143','344fda1ad3d6cdd49ed126652aef27b76dac9bbe',0,NULL,'13570238143',0,0,'495f57146102af3ffdcc3f3d47728a99cc25b9e7',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170508095530.jpg',NULL,NULL,NULL,NULL,NULL,113.1726985,23.3809064,'2017-05-08 09:56:08','2017-05-08 02:06:57',1),(31,'Lun','15521319867','b1ecbf1d76a0dc93145f211dbce5b32258f8580c',0,NULL,'15521319867',2,0,'e7c91720fd8ce61a61b5eaa8fa645a565ea6f3e0',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170509143558.jpg',NULL,NULL,NULL,NULL,NULL,113.2693031,23.1345502,'2017-05-09 14:36:49','2017-06-04 09:01:54',1),(32,'寻觅','寻觅','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'18676928204',0,0,'c303197ec746181378412ffe47a31e7d0377208c',NULL,'http://om1ccbp21.bkt.clouddn.com/face_寻觅_20170510214510040.jpg',NULL,NULL,NULL,NULL,NULL,113.3140860,23.1365520,'2017-05-10 21:45:10','2017-05-10 13:45:10',1),(33,'iloveliaoxinting','BLACK HOUSE','625781c00b628d290ec7e43ba1ae08b1df01bd94',1,'广州大学','18307529089',2,6,'2e86de8bb497e05152afc60c793ab0b0c15eba72','惠州','http://om1ccbp21.bkt.clouddn.com/face_iloveliaoxinting_20170511235721527.jpg','黎明前的黑暗，终究黑暗变黎明','随性, 喜欢简单, 理想主义, 热血, 安静','广州','1997-03-04 00:00:00','学生',113.3752690,23.0373960,'2017-05-11 23:57:42','2017-06-04 09:02:15',1),(34,'lokpang','18807557000','6a3c87dc5d40809a77dc16551cba4665d270e197',0,NULL,'18807557000',1,0,'57d1f3d28a37cb6cf9b630be472d0fb64a7264d7',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170513162026.jpg',NULL,NULL,NULL,NULL,NULL,114.4700109,22.5945287,'2017-05-13 16:20:43','2017-05-17 09:25:35',1),(35,'gullyfeng','13539778515','d752ebd123f831083fa618d66d5ea0504e84bd51',0,NULL,'13539778515',0,0,'410ebad3efcdd2918310e0141860a933d6e8daf8',NULL,'http://om1ccbp21.bkt.clouddn.com/',NULL,NULL,NULL,NULL,NULL,113.3694904,23.0403134,'2017-05-17 23:19:18','2017-05-17 15:19:36',1),(36,'zhuweizhen','15521161391','f6526824bcd96333ca671a6180bc4ef8632ff9bc',0,NULL,'15521161391',0,2,'bdc866a3cb3ef172254fb50c252e6570071e56bd',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519053015.jpg',NULL,NULL,NULL,NULL,NULL,113.3685706,23.0486060,'2017-05-19 17:30:30','2017-05-30 08:54:07',1),(37,'zzw0','15626123084','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'15626123084',0,0,'89d8d80b9b330d4bf834149a06745a8537fe7da7',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519052932.jpg',NULL,NULL,NULL,NULL,NULL,113.3685361,23.0487619,'2017-05-19 17:30:40','2017-05-19 09:30:56',1),(38,'qiyuedgut','13538640263','469cc080cc647199a019a7f95bf9f3decdc8d80a',0,NULL,'13538640263',1,1,'d910139915f5c3181dd1a8a355f3924727e35c99',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170519052858.jpg',NULL,NULL,NULL,NULL,NULL,113.8695795,22.9107068,'2017-05-19 17:31:42','2017-05-19 09:36:28',1),(39,'chen','13670113566','340298fc959a1e270b5cf7c324bbca4249685191',0,NULL,'13670113566',1,0,'8badde659e09310a38d1588324d7f0e21ebcb871',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170523181922.jpg',NULL,NULL,NULL,NULL,NULL,114.0336154,22.5710545,'2017-05-23 18:25:56','2017-05-27 18:17:35',1),(40,'520521','520521','6865fd0d462f7544342b7aef86c151156c96f061',0,NULL,'13265357808',1,0,'29c6ecdec55972634bcd1e27e36f5caccee5c396',NULL,'http://om1ccbp21.bkt.clouddn.com/face_520521_20170524233124799.jpg',NULL,NULL,NULL,NULL,NULL,113.3140860,23.1365520,'2017-05-24 23:31:25','2017-05-24 16:30:41',1),(41,'Cy.Kop','Cy.Kop','d1f13ba673e125b27b4faef2f96697257954d027',0,NULL,'18664692227',0,0,'c1dc44b1b101439a6e12266f5c6c95f26fab22a0',NULL,'http://om1ccbp21.bkt.clouddn.com/face_Cy.Kop_20170524233234509.jpg',NULL,NULL,NULL,NULL,NULL,113.3140860,23.1365520,'2017-05-24 23:32:39','2017-05-24 15:46:13',1),(42,'骑单车走天涯','骑单车走天涯','2d199e032339e38fc0bcde1e2a24ecffdcd1575d',1,'某某某小学','13890108503',0,0,'9e44ff4fceb0fa4233a209e8bf5c4754b88ef410','滨州','http://om1ccbp21.bkt.clouddn.com/face_骑单车走天涯_20170525002110268.jpg','没有个性 何来签名','随性,  喜欢简单','绵阳','1986-01-27 00:00:00','其他',104.6755200,31.4395470,'2017-05-25 00:21:12','2017-05-24 16:30:41',1),(43,'Atlantis','18148939105','7e5f1d5f56fb55f7a33f9584cb33a2e8050b6df9',0,NULL,'18148939105',0,0,'1b358f3c0a04da68ec8d10f9dfbadf7845e4ba1c',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170525032335.jpg',NULL,NULL,NULL,NULL,NULL,113.4678253,23.2574291,'2017-05-25 03:23:46','2017-05-24 19:23:59',1),(44,'cdb','13535088091','235092e1616d2784d0e1ef5fb838dc612284daaf',0,NULL,'13535088091',2,0,'15ed317acc8748a851b3b07211f96dde019cd510',NULL,'http://om1ccbp21.bkt.clouddn.com/',NULL,NULL,NULL,NULL,NULL,113.3672307,23.0438895,'2017-05-25 13:31:49','2017-05-26 08:11:49',1),(45,'test','test','098f917105c9ff975d6ec0127ca3be568174d324',0,NULL,'15018776859',1,0,'cb0eb3cebabff0d9510644ea8d815a89962fd2c3',NULL,'http://om1ccbp21.bkt.clouddn.com/face_test_20170527140617050.jpg',NULL,NULL,NULL,NULL,NULL,113.3723680,23.0414120,'2017-05-27 14:06:45','2017-05-27 06:30:58',1),(46,'yayiji','yayiji','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',1,'哼哼唧唧','18677300251',3,3,'28f4eac9642dd8376a33f30df9657e7ddf382e0f','焦作','http://om1ccbp21.bkt.clouddn.com/face_yayiji_20170527140850450.jpg','哼哼唧唧','热血','焦作','1990-01-18 00:00:00','影视/娱乐',113.3140860,23.1365520,'2017-05-27 14:09:01','2017-06-05 00:10:38',1),(47,'mjw07542422','15626164052','98a901d1e8a0095091f9f7e907af62d4be4b05d8',0,NULL,'15626164052',1,0,'e507adf617a0af954fb30706ea78efc02b5f3ba8',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170530231146.jpg',NULL,NULL,NULL,NULL,NULL,113.3884152,23.0430646,'2017-05-30 23:12:48','2017-05-31 02:49:47',1),(48,'HelloWorld','HelloWorld','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'18022220001',0,0,'ea69665578500751f1a98a13db28b9d57420d319',NULL,'http://om1ccbp21.bkt.clouddn.com/face_HelloKitty_20170603071507071.jpg',NULL,NULL,NULL,NULL,NULL,113.3140860,23.1365520,'2017-06-03 07:49:51','2017-06-03 00:09:45',0),(49,'y002','y002','69c5fcebaa65b560eaf06c3fbeb481ae44b8d618',0,NULL,'18022220002',0,0,'8d30933fd1f8320b94163f2d80dba28357ba8a97',NULL,'http://om1ccbp21.bkt.clouddn.com/face_HelloKitty_20170603071507071.jpg',NULL,NULL,NULL,NULL,NULL,113.3939850,23.1203680,'2017-06-03 08:11:04','2017-06-03 00:43:17',0),(50,'lee_wan','lee_wan','75a2585df9b5c02785fd11eb489a26e38e0027ca',0,NULL,'13422352971',0,1,'4fbb31ff8162a474eab70aa1acb647fbf18cffb7',NULL,'http://om1ccbp21.bkt.clouddn.com/face_HelloKitty_20170603071507071.jpg',NULL,NULL,NULL,NULL,NULL,113.3140860,23.1365520,'2017-06-03 15:34:42','2017-06-04 09:25:30',1),(51,'zcc123456','17695420199','ad90c39309b684be5953103de3d7cf111f8bffc9',0,NULL,'17695420199',0,0,'d2b3cdf822514264967b59c5b6cb55e0a3b939ec',NULL,'http://om1ccbp21.bkt.clouddn.com/icon_20170603184535.jpg',NULL,NULL,NULL,NULL,NULL,116.2934357,40.0516182,'2017-06-03 18:45:37','2017-06-03 10:47:13',1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_version_number`
--

DROP TABLE IF EXISTS `t_version_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_version_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '版本号id',
  `version_type` int(1) NOT NULL COMMENT '版本号类型',
  `version` varchar(40) NOT NULL COMMENT '版本号',
  `version_url` varchar(120) DEFAULT NULL COMMENT '版本的下载地址',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本更新号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_version_number`
--

LOCK TABLES `t_version_number` WRITE;
/*!40000 ALTER TABLE `t_version_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_version_number` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-06 19:33:27

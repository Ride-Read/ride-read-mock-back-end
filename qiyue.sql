/*
Navicat MySQL Data Transfer

Source Server         : bos19
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : qiyue

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-08 16:54:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `msg` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `face_url` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `created_at` char(10) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `updated_at` char(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_Reference_3` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='评论数据';

-- ----------------------------
-- Table structure for moment
-- ----------------------------
DROP TABLE IF EXISTS `moment`;
CREATE TABLE `moment` (
  `userId` int(11) NOT NULL COMMENT '阅圈拥有者id',
  `type` int(11) DEFAULT NULL COMMENT '阅圈类型:0-纯文本，1-图文，2-视频文字',
  `cover` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频封面图url',
  `msg` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '评论内容',
  `video` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频url',
  `thumbs` binary(100) DEFAULT NULL COMMENT '缩略图url',
  `pictures` binary(100) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='阅圈数据';

-- ----------------------------
-- Table structure for t_follower
-- ----------------------------
DROP TABLE IF EXISTS `t_follower`;
CREATE TABLE `t_follower` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL COMMENT '用户id',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `nickname` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`fid`),
  KEY `FK_Reference_4` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='粉丝列表';

-- ----------------------------
-- Table structure for t_following
-- ----------------------------
DROP TABLE IF EXISTS `t_following`;
CREATE TABLE `t_following` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL COMMENT '用户id',
  `face_url` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`fid`),
  KEY `FK_Reference_5` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='关注列表';

-- ----------------------------
-- Table structure for t_read_circle
-- ----------------------------
DROP TABLE IF EXISTS `t_read_circle`;
CREATE TABLE `t_read_circle` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `comment` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `cover` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `pictures` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `thumbs_up` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `video` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `thumbs` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `msg` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`mid`),
  KEY `FK_Reference_1` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='阅圈基本数据';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT 'username',
  `sex` int(11) DEFAULT '0' COMMENT '用户性别',
  `school` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户学校',
  `phonenumber` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '用户手机号',
  `follower` int(11) DEFAULT '0' COMMENT '粉丝数',
  `following` int(11) DEFAULT '0' COMMENT '关注数',
  `token` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户token',
  `hometown` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户老家',
  `face_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `location` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地区',
  `birthday` datetime DEFAULT NULL COMMENT '用户生日',
  `career` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户职业',
  `nickname` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户最后一次登录时间',
  `created_at` datetime DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本数据';

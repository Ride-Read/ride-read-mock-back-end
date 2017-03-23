/*
Navicat MySQL Data Transfer

Source Server         : bos19
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : qiyue

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-23 17:58:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `user_id` int(11) NOT NULL COMMENT '�����˵�id',
  `nickname` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '�������ǳ�',
  `moment_id` int(11) NOT NULL COMMENT '��Ȧid',
  `msg` text COLLATE utf8_bin NOT NULL COMMENT '��������',
  `face_url` varchar(125) COLLATE utf8_bin NOT NULL COMMENT '������ͷ��',
  `created_at` datetime NOT NULL COMMENT '���۴���ʱ��',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='��������';

-- ----------------------------
-- Table structure for t_follower
-- ----------------------------
DROP TABLE IF EXISTS `t_follower`;
CREATE TABLE `t_follower` (
  `id` int(11) NOT NULL COMMENT '��˿�б�id',
  `fid` int(11) NOT NULL COMMENT '��˿�û�id',
  `tid` int(11) NOT NULL COMMENT '�����û�id',
  `face_url` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '�û�ͷ��url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '�û�ǩ��',
  `nickname` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '�û��ǳ�',
  `created_at` datetime NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='��˿�б�';

-- ----------------------------
-- Table structure for t_following
-- ----------------------------
DROP TABLE IF EXISTS `t_following`;
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

-- ----------------------------
-- Table structure for t_moment
-- ----------------------------
DROP TABLE IF EXISTS `t_moment`;
CREATE TABLE `t_moment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '阅圈id',
  `user_id` int(11) NOT NULL COMMENT '阅圈拥有者的ID',
  `type` int(11) NOT NULL COMMENT '阅圈类型:0-纯文本，1-图文，2-视频文字',
  `cover` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频封面图url',
  `msg` text COLLATE utf8_bin COMMENT '阅圈内容',
  `video` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '视频url',
  `thumbs` text COLLATE utf8_bin COMMENT '缩略图url',
  `pictures` text COLLATE utf8_bin COMMENT '图片url',
  `latitude` decimal(7,3) DEFAULT NULL COMMENT '用户发表阅圈地点的纬度',
  `longitude` decimal(7,3) DEFAULT NULL COMMENT '用户发表阅圈地点的经度',
  `created_at` datetime NOT NULL COMMENT '阅圈创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '阅圈修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='阅圈数据列表';

-- ----------------------------
-- Table structure for t_read_circle
-- ----------------------------
DROP TABLE IF EXISTS `t_read_circle`;
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

-- ----------------------------
-- Table structure for t_thumbs_up
-- ----------------------------
DROP TABLE IF EXISTS `t_thumbs_up`;
CREATE TABLE `t_thumbs_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `user_id` int(11) NOT NULL COMMENT '������id',
  `nickname` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '�������ǳ�',
  `moment_id` int(11) NOT NULL COMMENT '���޵���Ȧid',
  `created_at` datetime NOT NULL COMMENT '���޴���ʱ��',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�����޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='�������ݱ�';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(125) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `sex` int(11) DEFAULT NULL COMMENT '用户性别',
  `school` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户学校',
  `phonenumber` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户手机号',
  `follower` int(11) DEFAULT NULL COMMENT '粉丝数',
  `following` int(11) DEFAULT NULL COMMENT '关注数',
  `token` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户token',
  `hometown` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户老家',
  `face_url` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `signature` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户签名',
  `tags` varchar(125) COLLATE utf8_bin DEFAULT NULL COMMENT '用户标签',
  `location` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户地区',
  `birthday` datetime DEFAULT NULL COMMENT '用户生日',
  `career` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户职业',
  `nickname` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `longitude` decimal(7,3) DEFAULT NULL COMMENT '用户登录地点的经度',
  `latitude` decimal(7,3) DEFAULT NULL COMMENT '用户登录地点的纬度',
  `created_at` datetime NOT NULL COMMENT '用户注册时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户基本数据';

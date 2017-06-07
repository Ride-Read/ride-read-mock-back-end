--
-- Table structure for table `t_third_party`
--

DROP TABLE IF EXISTS `t_third_party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_third_party` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '第三方表id',
  `uid` int(11) NOT NULL COMMENT '用户表id',
  `third_party_code` varchar(125) NOT NULL COMMENT '第三方登录代号',
  `third_party_name` varchar(1024) NOT NULL COMMENT '第三方名称',
  `verify_content` varchar(1024) COMMENT '校验内容',
  `state` int(1) NOT NULL COMMENT '状态位 0: 有效；1：失效',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='第三方信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_third_party`
--

LOCK TABLES `t_third_party` WRITE;
/*!40000 ALTER TABLE `t_third_party` DISABLE KEYS */;
INSERT INTO `t_third_party` VALUES ('1','1','wechat','微信','this is wechat test','0', now(),now());
/*!40000 ALTER TABLE `t_third_party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--
-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: ec_1805
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Table structure for table `ec_article`
--

DROP TABLE IF EXISTS `ec_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec_article` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(200) DEFAULT NULL,
  `SUPPLIER` varchar(20) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `LOCALITY` varchar(300) DEFAULT NULL,
  `PUTAWAY_DATE` datetime DEFAULT NULL,
  `STORAGE` int(11) DEFAULT '100',
  `IMAGE` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `TYPE_CODE` varchar(100) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `DISABLED` tinyint(1) DEFAULT '0',
  `putawayDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ARTICLE_TYPE` (`TYPE_CODE`),
  CONSTRAINT `FK_ARTICLE_TYPE` FOREIGN KEY (`TYPE_CODE`) REFERENCES `ec_article_type` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec_article`
--

LOCK TABLES `ec_article` WRITE;
/*!40000 ALTER TABLE `ec_article` DISABLE KEYS */;
INSERT INTO `ec_article` VALUES (19,'疯狂Java讲义精粹(含CD光盘1张)，最畅销的Java书籍。','李刚',60.8,NULL,'电子工业出版社',NULL,100,'fkjava.jpg','《疯狂Java讲义精粹（含CD光盘1张）》是《疯狂Java讲义》的精粹版，　 本书同样保留了《疯狂Java讲义》的特性：　 本书基于Java 7完成，全面介绍了Java 7的新特性。','00010001','2012-06-11 00:00:00',0,NULL),(20,'疯狂Java讲义（附光盘 案例驱动 注解详细 李刚老师引爆编程激情）','李刚',79.2,NULL,'电子工业出版社',NULL,100,'java.jpg','疯狂源自梦想，技术成就辉煌','00010001','2012-06-11 00:00:00',0,NULL),(21,'轻量级Java EE企业应用实战——Struts 2＋Spring＋Hibernate整合开发（附光盘）','李刚',89,NULL,'电子工业出版社',NULL,100,'framework.jpg','本书是《轻量级J2EE企业应用实战》的第二版，同时还融合了《整合Struts+Hibernate+Spring应用开发详解》理论部分。实际上，本书凝结了前两本书的精华部分。','00010001','2012-06-11 00:00:00',0,NULL),(22,'经典Java EE企业应用实战——基于WebLogic/JBoss的JSF+EJB 3+JPA整合开发(含CD光盘1张)','李刚',59.2,NULL,'电子工业出版社',NULL,100,'javaee.jpg','本书介绍了Java EE规范的三大主要规范JSF、EJB 3和JPA，其中JSF是Sun公司提供的JSF RI；EJB 3部分则包含Session Bean、Message Driven Bean的详细介绍。','00010001','2012-06-11 00:00:00',0,NULL),(23,'Struts 2.1权威指南——基于WebWork核心的MVC开发（含光盘1张）','李刚',63.8,NULL,'电子工业出版社',NULL,100,'struts.jpg','本书是《Struts 2权威指南》的第二版，本书介绍的Struts 2是最新的Struts 2.1。','00010001','2012-06-11 00:00:00',0,NULL),(24,'疯狂Ajax讲义——Prototype/jQuery+DWR+Spring+Hibernate整合开发（含光盘一张）','李刚',50.3,NULL,'电子工业出版社',NULL,100,'ajax.jpg','全书主要分为三个部分。第一部分介绍了XHTML、CSS、JavaScript和DOM编程等内容。第二部分详细介绍了Prototype、jQuery、DWR、AjaxTags等四个最常用的Ajax框架的用法，并针对每个框架提供了一个实用案例。','00010001','2012-06-11 00:00:00',0,NULL),(25,'疯狂XML讲义(含光盘1张)，包括DTD、Schema等技术的深入讲解。','李刚',48.8,NULL,'电子工业出版社',NULL,100,'xml.jpg','本书主要以XML为核心，深入地介绍了XML的各种相关知识。','00010001','2012-06-11 00:00:00',0,NULL),(26,'疯狂Java：突破程序员基本功的16课(修订版)','李刚',44.2,NULL,'电子工业出版社',NULL,100,'basic.jpg','《疯狂Java：突破程序员基本功的16课(修订版)》是Java领域著名研究专家、Java语言培训导师、“疯狂Java”创始人李刚老师的又一倾心力作。','00010001','2012-06-11 00:00:00',0,NULL),(27,'疯狂Android讲义(含CD光盘1张)','李刚',60.6,NULL,'电子工业出版社',NULL,100,'android.jpg','　本书全面地介绍了Android应用开发的相关知识，全书内容覆盖了Android用户界面编程、Android四大组件、Android资源访问、图形/图像处理、事件处理机制、Android输入/输出处理、音频/视频多媒体应用开发、OpenGL与3D应用开发、网络通信编程、Android平台的Web Service、传感器应用开发、GPS应用开发、Google Map服务等。','00010001','2012-06-11 00:00:00',0,NULL),(28,'肖申克的救赎。本书是斯蒂芬·金最为人精精乐道的杰出代表作，收入了他的四部中篇小说。其英文版一经推出，即登上《纽约时报》畅销书排行榜的冠军之位，当年在美国狂销二十八万册。目前，这本书已经被翻译成三十一种语言，同时创下了收录的四篇小说中有三篇被改编成轰动一时的电影的记录。','（美）金 著，施寄青，赵永芬，齐若兰',19.9,NULL,'人民文学出版社',NULL,100,'9198692-1_l.jpg','本书是斯蒂芬·金最为人精精乐道的杰出代表作，收入了他的四部中篇小说。其英文版一经推出，即登上《纽约时报》畅销书排行榜的冠军之位，当年在美国狂销二十八万册。目前，这本书已经被翻译成三十一种语言，同时创下了收录的四篇小说中有三篇被改编成轰动一时的电影的记录。其中最为人精精乐道的便是曾获奥斯卡奖七项提名、被称为电影史上最完美影片的《肖申克救赎》（又译《刺激一九九五》）。','00020001','2012-06-11 00:00:00',0,NULL),(29,'达·芬奇密码。让人绞尽脑汁的密码，就隐藏在列昂纳多·达·芬奇的艺术作品当中；令人绝望的角逐，就在遍布欧洲的大教堂和城堡里展开；令人震惊的事实真相，在掩盖了数百年之后，终于被撩起了神秘的面纱。','（美）布朗 著，朱振武 等译',16.3,NULL,'人民文学出版社',NULL,100,'20559673-1_a.jpg','　《达·芬奇密码》引人入胜。对热爱探究历史的人、喜爱耍弄诡计的人、热衷猜谜的人以及任何醉心于离奇故事的人而言，它几近完美。','00020001','2012-06-11 00:00:00',0,NULL),(30,'计算机基础(一)','朱振武 等译',16.3,NULL,'人民文学出版社',NULL,100,'20970893-1_a.jpg','　诡计的人、几近完美。','00010003','2012-06-11 00:00:00',0,NULL),(31,'计算机基础(二)','朱振武 等译',16.3,NULL,'人民文学出版社',NULL,100,'20970893-1_a.jpg','　诡计的人、几近完美。','00010003','2012-06-11 00:00:00',0,NULL),(32,'计算机基础(三)','朱振武 等译',16.3,NULL,'人民文学出版社',NULL,100,'20970893-1_a.jpg','　诡计的人、几近完美。','00010003','2012-06-11 00:00:00',0,NULL),(33,'计算机基础(四)','朱振武 等译',16.3,NULL,'人民文学出版社',NULL,100,'20970893-1_a.jpg','　诡计的人、几近完美。','00010003','2012-06-11 00:00:00',0,NULL),(34,'计算机教材(一)','朱振武 等译',56.3,NULL,'人民文学出版社',NULL,100,'20915608-1_a.jpg','　诡计的人、几近完美。','00010004','2012-06-11 00:00:00',0,NULL),(35,'计算机教材(二)','朱振武 等译',46.3,NULL,'人民文学出版社',NULL,100,'22564594-1_a.jpg','　诡计的人、几近完美。','00010004','2012-06-11 00:00:00',0,NULL),(36,'计算机教材(三)','朱振武 等译',76.3,NULL,'人民文学出版社',NULL,100,'20915608-1_a.jpg','　诡计的人、几近完美。','00010004','2012-06-11 00:00:00',0,NULL),(37,'计算机教材(四)','朱振武 等译',56.3,NULL,'人民文学出版社',NULL,100,'22564594-1_a.jpg','　诡计的人、几近完美。','00010004','2012-06-11 00:00:00',0,NULL),(38,'计算机教材(五)','朱振武 等译',66.3,NULL,'人民文学出版社',NULL,100,'22564594-1_a.jpg','　诡计的人、几近完美。','00010004','2012-06-11 00:00:00',0,NULL),(39,'魔戒：插图珍藏版（200套限量编号版随机发送！）。天真无邪的哈比男孩佛罗多继承了一枚戒指，却发现它就是黑暗魔君索伦铸造的至尊魔戒，具有奴役全世界的力量。','(英国) J.R.R.托尔金 著',132.3,NULL,'译林出版社','2012-06-19 00:00:00',100,'22566493-1_b.jpg',' 天真无邪的哈比男孩佛罗多继承了一枚戒指，却发现它就是黑暗魔君索伦铸造的至尊魔戒，具有奴役全世界的力量。在甘道夫的指导下，佛罗多和精灵、矮人、哈比人、人类组成远征队，要将魔戒扔进末日火山口销毁。索伦已派出黑骑士四处搜寻这枚戒指，而魔戒又有强大的腐蚀力，会使佩戴者心灵扭曲；善的力量能否战胜恶的诱惑？这是一次异常艰险的远征……','00010002','2012-06-18 00:00:00',0,NULL),(40,'权威定本四大名著： 红楼梦 三国演义  水浒传  西游记 全国独家',NULL,145.8,NULL,'清华大学出版社',NULL,100,'20605371-1_a.jpg','权威定本四大名著： 红楼梦 三国演义  水浒传  西游记','00020001','2012-06-11 00:00:00',0,NULL),(41,'什么是什么》第一二合辑平装（全20册，一套来自德国的最权威、最畅销的少年儿童百科知识全书）','本社 编',147,NULL,'湖北教育出版社',NULL,100,'20588965-1_b.jpg','书中所有照片都是第一手照片。这种情况在国内及国外的其他少年儿童百科全书中是很难看到的。因为书中所有照片都是来自全世界的顶级权威研究机构，所有照片非常精美、清晰。《太空航行》一书中有30多张照片来自美国航天局，其他照片来自欧洲航天局、美国射电天文台等','00010002','2012-06-11 00:00:00',0,NULL),(42,'万物生光辉 ——邂逅最可爱的动物，感受最纯真的幽默。畅销全球30年自然写作经典，万物有灵且美系列自然小说之五',NULL,23.6,NULL,NULL,NULL,100,'22639083-1_a.jpg','描述','00020001','2012-06-11 00:00:00',0,NULL),(43,'爱你是最好的时光Ⅱ（终极大结局完结篇，当当网全国独家赠送匪我思存民国虐恋故事《兰烬》漫画版）',NULL,17.6,NULL,NULL,NULL,100,'22630101-1_a.jpg','描述','00020001','2012-06-11 00:00:00',0,NULL),(44,'曾国藩 （血祭 野焚 黑雨）全三册。曾国藩是中国近代史上有着巨大影响的人物。','唐浩明 著',65,NULL,'春风文艺出版社',NULL,100,'20500323-1_a.jpg','毛泽东对友人黎锦熙说：“愚于近人，独服曾文正，观其收拾洪杨一役，完满无缺。使以今人易其位，其能如彼之完满乎？”','00010002','2012-06-11 00:00:00',0,NULL),(45,'情感知识小说（男人帮+婚姻保卫战），《男人帮》这是一部情场知识喜剧小说。','唐浚，魏晓霞 著',48.1,NULL,'春风文艺出版社',NULL,100,'22561316-1_a.jpg','《男人帮》这是一部情场知识喜剧小说。','00010002','2012-06-11 00:00:00',0,NULL),(46,'林格伦作品选（9册/套）{最新精装典藏版}——长袜子皮皮之母林格伦作品精选集，累积销售1000多万册的儿童文学传世经典',NULL,179.6,NULL,NULL,NULL,100,'21094590-1_a.jpg','林格伦作品选（9册/套）{最新精装典藏版}——长袜子皮皮之母林格伦作品精选集，累积销售1000多万册的儿童文学传世经典','00020001','2012-06-11 00:00:00',0,NULL),(47,'白鹿原——中国首部当代名家名篇宣纸线装书，陈忠实先生亲笔签名签章限量珍藏版','陈忠实　著',360,NULL,'春风文艺出版社',NULL,100,'22541642-1_a.jpg','白嘉轩，活的有自己的原则，硬气，不随波逐流，?然有很多地方落后于时代，但那是时代的局限，不能怪罪于他，他作为一个地主、族长、父亲、丈夫还是相当合格的，至少比鹿子霖磊落许多倍。 ','00010002','2012-06-11 00:00:00',0,NULL),(48,'丁丁上学记套装（全二册，5000多位家长热评，小学生最佳学习方法原创读本，给你不一样的学习感觉！）','刘蕾',26.3,NULL,'湖北教育出版社',NULL,100,'21065915-1_b.jpg','《丁丁上学记》是一套国内难得一见的小学生学习方法原创读本','00020001','2012-06-11 00:00:00',0,NULL),(49,'最后一次说爱你（美国纯爱疗伤小说天王最新力作，《分手信》粉丝钟爱的新故事，一本让你哭，让你笑，让你喜，让你悲，但就是放不下的书），主题歌《在你的笑容里看见天堂》','（美）米尔尼　著，全克林　译',18.2,NULL,'译林出版社',NULL,100,'22504529-1_a.jpg','小说讲了一个相识、相爱、相扶、相守的爱情故事，也讲了人在面对生活中的伤痛时，释怀的诀窍……','00010002','2012-06-11 00:00:00',0,NULL),(50,'用美国小孩的方法学英文（图解英语训练营全两册）',NULL,48.1,NULL,NULL,NULL,100,'10.5','21035452-1_a.jpg','00020001','2012-06-11 00:00:00',0,NULL),(51,'《大秦帝国》（全六部11卷平)。大秦帝国作为时代精神汇集的帝国，集中地体现了那个时代中华民族的强势生存精神。中华民族的整个文明体系其所以能够绵延如大河奔涌，秦帝国时代开创奠定的强势生存传统起了决定性的作用。','孙皓晖 著',255,NULL,'河南文艺出版社',NULL,100,'20223971-1_a.jpg','《大秦帝国》是一部描述秦兴亡生灭过程的长卷历史小说。秦帝国崛起于铁血竞争的群雄列强之际，建立了一个强大统一的帝国，开创了一个全新的铁器文明。但她只有十五年生命，像流星一闪，轰鸣而逝。这巨大的历史落差与戏剧性的帝国命运中，隐藏了难以计数的神奇故事以及伟人名士的悲欢离合。','00010002','2012-06-11 00:00:00',0,NULL),(52,'霍金经典著作套装（全4册）：时间简史（插图本）/果壳中的宇宙/大设计/霍金传（当当独家火热发售）',NULL,108.8,NULL,NULL,NULL,100,'22564594-1_a.jpg','霍金经典著作套装（全4册）：时间简史（插图本）/果壳中的宇宙/大设计/霍金传（当当独家火热发售）','00010002','2012-06-11 00:00:00',0,NULL);
/*!40000 ALTER TABLE `ec_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ec_article_type`
--

DROP TABLE IF EXISTS `ec_article_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec_article_type` (
  `CODE` varchar(100) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`CODE`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec_article_type`
--

LOCK TABLES `ec_article_type` WRITE;
/*!40000 ALTER TABLE `ec_article_type` DISABLE KEYS */;
INSERT INTO `ec_article_type` VALUES ('0001','程序设计','程序设计'),('00010001','----程序设计','程序设计'),('00010002','----数据库','数据库'),('00010003','----计算机理论','计算机理论'),('00010004','----计算机教材','计算机教材'),('0002','小说','小说'),('00020001','----社会','社会'),('00020002','----言情','言情'),('00020003','----悬疑','悬疑'),('0003','文艺','文艺'),('00030001','----文学','文学'),('00030002','----传记','传记'),('0004','青春','青春'),('00040001','----青春文学','青春文学'),('00040002','----动漫','动漫'),('00040003','----幽默','幽默'),('0005','励志/成功','励志/成功'),('00050001','----修养','修养'),('00050002','----成功','成功'),('00050003','----职场','职场'),('00050004','----沟通','沟通'),('0006','少儿','少儿'),('00060001','----0-2','0-2岁'),('00060002','----3-6','3-6岁'),('00060003','----7-10','7-10岁'),('00060004','----11-14','11-14岁'),('00060005','----科学','科学'),('00060006','----图画书','图画书'),('0007','生活','生活'),('00070001','----两性','两性'),('00070002','----孕期','孕期'),('00070003','----育儿','育儿'),('00070004','----亲子关系','亲子关系'),('00070005','----保健','保健'),('00070006','----运动','运动'),('00070007','----美食','美食'),('0008','管理','管理'),('00080001','----经济','经济'),('00080002','----会计','会计'),('00080003','----人力资源','人力资源'),('00080004','----创业','创业'),('0009','教育','教育'),('00090001','----教材','教材'),('00090002','----外语','外语'),('00090003','----考试','考试'),('00090004','----中小学教辅','中小学教辅'),('0010','休闲/爱好','休闲/爱好'),('0011','旅游/地图','旅游/地图'),('0012','家庭/家居','家庭/家居'),('0013','亲子/家教','亲子/家教'),('0014','美食','美食'),('0015','政治/军事','政治/军事');
/*!40000 ALTER TABLE `ec_article_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ec_order`
--

DROP TABLE IF EXISTS `ec_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_CODE` varchar(20) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `SEND_DATE` datetime DEFAULT NULL,
  `STATUS` varchar(6) DEFAULT NULL,
  `AMOUNT` double DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ORDER_CODE` (`ORDER_CODE`),
  KEY `FK7vkcrr0kt0i373d414f363vl6` (`USER_ID`),
  CONSTRAINT `FK7vkcrr0kt0i373d414f363vl6` FOREIGN KEY (`USER_ID`) REFERENCES `id_user` (`id`),
  CONSTRAINT `FK_USER_ORDER` FOREIGN KEY (`USER_ID`) REFERENCES `id_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec_order`
--

LOCK TABLES `ec_order` WRITE;
/*!40000 ALTER TABLE `ec_order` DISABLE KEYS */;
INSERT INTO `ec_order` VALUES (1,'15384713279538543','2018-10-02 17:08:48',NULL,'新订单',0,3),(2,'15384715088843593','2018-10-02 17:11:49',NULL,'新订单',581.4000000000001,3),(3,'15392639080692916','2018-10-11 21:18:28',NULL,'新订单',NULL,3),(4,'15392653019549777','2018-10-11 21:41:42',NULL,'新订单',NULL,3);
/*!40000 ALTER TABLE `ec_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ec_order_item`
--

DROP TABLE IF EXISTS `ec_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec_order_item` (
  `ORDER_ID` int(11) NOT NULL,
  `ARTICLE_ID` int(11) NOT NULL,
  `ORDER_NUM` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `FKi647j52je8i0kr2v8a97mlowb` (`ARTICLE_ID`),
  KEY `FKqwvhn8r3oks7sx0pn9h0gd9yu` (`ORDER_ID`),
  CONSTRAINT `FKi647j52je8i0kr2v8a97mlowb` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `ec_article` (`ID`),
  CONSTRAINT `FKqwvhn8r3oks7sx0pn9h0gd9yu` FOREIGN KEY (`ORDER_ID`) REFERENCES `ec_order` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec_order_item`
--

LOCK TABLES `ec_order_item` WRITE;
/*!40000 ALTER TABLE `ec_order_item` DISABLE KEYS */;
INSERT INTO `ec_order_item` VALUES (1,19,3,1),(1,20,2,2),(2,21,2,3),(2,22,4,4),(3,20,3,5),(3,22,1,6),(4,20,1,7);
/*!40000 ALTER TABLE `ec_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_user`
--

DROP TABLE IF EXISTS `id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `active_code` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_user`
--

LOCK TABLES `id_user` WRITE;
/*!40000 ALTER TABLE `id_user` DISABLE KEYS */;
INSERT INTO `id_user` VALUES (3,'张三丰','tom','asdfasdf','tom@fkjava.com','617af2e2-9152-4d49-811d-8eae64b9e39d'),(4,'张无忌','wj','asdfasdf','wj@fkjava.org','eb5688af-a1b4-4160-a818-2ba781acc0b0'),(5,'小黑','hei','123456','hei@fkjava.org','ef51262b-6946-439d-9041-1ce2b74afd97'),(6,'aaaa','hei2','asdfasdf','hei2@fkjava.org','9c222034-257d-4e71-bc69-9495dd1b33dc'),(7,'罗文强','lwq','asdfasdf','luo_wenqiang@qq.com',NULL);
/*!40000 ALTER TABLE `id_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-18 22:02:06

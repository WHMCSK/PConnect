-- CREATE DATABASE  IF NOT EXISTS `pconnect` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
-- USE `pconnect`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: pconnect
-- ------------------------------------------------------
-- Server version	5.5.5-10.2.15-MariaDB-10.2.15+maria~jessie

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
-- Table structure for table `dm_comm_deal_with_definition`
--

DROP TABLE IF EXISTS `dm_comm_deal_with_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_comm_deal_with_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '对于dm_fnt_teleplay信息的处置方式枚举，从此定义一个规则，就是todo是最简单原始的代办列表，但是也有很多代办事项来源于某些信息本身，也就是说todo只是对某些领域的某些事件做一些处置，这时可定义一套固定模式：任何信息都可以有带有_deal_with结尾的信息结构，这种结构代表了这些信息需要被处置的种类描述。信息本身id加上deal_with的方式，记录到todo或者issue列表，则形成待办事项。\n\n将此表改成通用表，因为此设计类似状态机，所以大多数对象公用deal_with的状态机模式存储\n\n将此表再次改进，定义成状态图存储，只是作为状态的顶点不单独存储，置存储作为状态之间连接的边，此表就是存储边信息，边的两端状态名称一并作为存储。',
  `obj_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `deal_with_desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `pre_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `post_action_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_stamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_comm_deal_with_definition`
--

LOCK TABLES `dm_comm_deal_with_definition` WRITE;
/*!40000 ALTER TABLE `dm_comm_deal_with_definition` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_comm_deal_with_definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_comm_deal_with_entity`
--

DROP TABLE IF EXISTS `dm_comm_deal_with_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_comm_deal_with_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '此表记录entity的处理记录，由entity_id标示同一个entity',
  `entity_id` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_deal_with_schema` int(11) NOT NULL,
  `id_deal_with_current` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '这里关联到deal_with_definition的ID',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_comm_deal_with_entity`
--

LOCK TABLES `dm_comm_deal_with_entity` WRITE;
/*!40000 ALTER TABLE `dm_comm_deal_with_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_comm_deal_with_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_comm_deal_with_schema`
--

DROP TABLE IF EXISTS `dm_comm_deal_with_schema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_comm_deal_with_schema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schema_title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_comm_deal_with_schema`
--

LOCK TABLES `dm_comm_deal_with_schema` WRITE;
/*!40000 ALTER TABLE `dm_comm_deal_with_schema` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_comm_deal_with_schema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_dcsmk_agenda_item`
--

DROP TABLE IF EXISTS `dm_dcsmk_agenda_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_dcsmk_agenda_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '决策议案',
  `item_content` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `exec_explain` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `pid_presenter` int(11) NOT NULL,
  `id_conference` int(11) NOT NULL,
  `state` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '提出' COMMENT '提出（议案）、通过（决议案）、审核中',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_dcsmk_agenda_item`
--

LOCK TABLES `dm_dcsmk_agenda_item` WRITE;
/*!40000 ALTER TABLE `dm_dcsmk_agenda_item` DISABLE KEYS */;
INSERT INTO `dm_dcsmk_agenda_item` VALUES (1,'由金飞启动康师傅演示报表的制作。可以使用原架构组和项目组任何可用人力资源','风险：因为FineReport并不能随意设计表现，并且大家对其熟悉程度不一，时间紧张，会因为某个效果没有做过而花费大量时间去尝试实践，并且效果不一定满意；目标：至少做出任爱新和袁程祺指定要做的报表和效果；',1,1,'通过','2018-08-21 10:22:14','2018-08-21 10:33:51.112329'),(2,'开发独立可集成的SPC模块/系统，初步要求如下：\n1.SPC作为独立系统进行设计和开发，并提供出应有的集成接口\n2.尽可能使用FineReport，减少开发分期中的负担\n3.如果分期进行，每一期软件功能都必须是一个闭环\n4.SPC系统报表呈现这块一定是B/S模式，提供嵌入到WinForm的方法和接口\n5.SPC系统应该可以和其他系统进行权限集成\n6.要保证SPC实时报表和分析报表的性能和可用性\n7.主要参照盈飞Demo，另外可参照台湾的某家SPC demo\n8.任爱新确定设计、评审、开工标准\n9.金飞主导技术实现和系统设计、赵赛赛主导产品外观设计\n10.第一周（2018年8月20-8月24日）消化盈飞Demo，并提出概要设计\n11.整个系统研发完成时间为2018年9月31日','',2,2,'通过','2018-08-21 10:58:48','2018-08-24 07:17:21.321443');
/*!40000 ALTER TABLE `dm_dcsmk_agenda_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_dcsmk_conference`
--

DROP TABLE IF EXISTS `dm_dcsmk_conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_dcsmk_conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会议',
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '正式、非正式',
  `pids_participator` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_dcsmk_conference`
--

LOCK TABLES `dm_dcsmk_conference` WRITE;
/*!40000 ALTER TABLE `dm_dcsmk_conference` DISABLE KEYS */;
INSERT INTO `dm_dcsmk_conference` VALUES (1,'启动康师傅演示报表','为康师傅项目准备一些演示报表','非正式','1,3','2018-07-30 00:00:00','2018-07-30 00:00:00','2018-08-21 10:17:08','2018-08-21 10:43:33.295967'),(2,'启动SPC模块的研发','启动SPC模块的研发，明确研发思路','非正式','1,2,3,4','2018-08-20 10:30:00','2018-08-20 11:00:00','2018-08-21 10:42:59','2018-08-21 10:43:33.294900');
/*!40000 ALTER TABLE `dm_dcsmk_conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_dsgn_func`
--

DROP TABLE IF EXISTS `dm_dsgn_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_dsgn_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设计中心领域，功能设计',
  `func_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_dsgn_func`
--

LOCK TABLES `dm_dsgn_func` WRITE;
/*!40000 ALTER TABLE `dm_dsgn_func` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_dsgn_func` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_dsgn_report`
--

DROP TABLE IF EXISTS `dm_dsgn_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_dsgn_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `report_code` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '报表编码，用来给编程，或者其他需要唯一编码的地方提供编码名称',
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `category` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '分类',
  `domain` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属领域',
  `reference` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '参考来源',
  `target_use_role` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '受众',
  `priority` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'C',
  `need_demo` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `need_dev` tinyint(4) NOT NULL DEFAULT 0,
  `ids_dm_dcsmk_agenda_item` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_dsgn_report`
--

LOCK TABLES `dm_dsgn_report` WRITE;
/*!40000 ALTER TABLE `dm_dsgn_report` DISABLE KEYS */;
INSERT INTO `dm_dsgn_report` VALUES (1,'设备状态视图','','','设备管理','设备','HYDRA','中层','A','1',0,'1','2018-08-07 03:52:47','2018-08-21 10:39:40.821619'),(2,'设备状态分析','','','设备管理','设备','HYDRA','中层','A','1',0,'1','2018-08-07 03:52:47','2018-08-21 10:39:40.822689'),(3,'次要/主要停机分析','','','设备管理','设备','HYDRA','中层','A','1',0,'1','2018-08-07 03:52:48','2018-08-21 10:39:40.823516'),(4,'绩效报告','','','设备管理','设备','HYDRA','中高层','A','1',0,'1','2018-08-07 03:52:48','2018-08-21 10:39:40.824359'),(5,'生产资源账目视图','','','设备管理','设备','HYDRA','中高层','A','0',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.825238'),(6,'绩效视图','','','设备管理','设备','HYDRA','中高层','A','1',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.826020'),(7,'OEE报表','','','设备管理','设备','HYDRA','高层管理','A','1',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.826806'),(8,'ABC分析','','','设备管理','设备','HYDRA','中高层','A','0',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.827449'),(9,'资源日利用率报告','','','设备管理','设备','SAP','底层','B','0',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.828912'),(10,'资源利用率图表','','','设备管理','设备','SAP','底层','C','0',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.828234'),(11,'资源利用率汇总报告','','','设备管理','设备','SAP','中层','A','0',0,'1','2018-08-07 05:49:19','2018-08-21 10:39:40.829670'),(12,'正在加工工序','','','生产管理','生产管理','HYDRA','底层','B','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.830308'),(13,'已完成工序','','','生产管理','生产管理','HYDRA','底层','B','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.830972'),(14,'订单班次记录','','','生产管理','生产管理','HYDRA','底层','B','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.831635'),(15,'订单统计','','','生产管理','生产管理','HYDRA','中层','A','1',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.832357'),(16,'生产监视','','','生产管理','生产管理','HYDRA','参观','A','1',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.833736'),(17,'废品统计/操作产出率报告','','','生产管理','生产管理','HYDRA/SAP','中层','A','1',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.833018'),(18,'计划排产','','','生产管理','生产管理','HYDRA','中层','B','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.834423'),(19,'生产能力峰值','','','生产管理','生产管理','HYDRA','底层','B','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.835133'),(20,'生产能力负荷2','','','生产管理','生产管理','HYDRA','底层','A','0',0,'1','2018-08-07 05:58:53','2018-08-21 10:39:40.835779'),(30,'物料产出率报告','','','生产结果报告','生产结果报告','SAP','','','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.836452'),(31,'操作产出率报告','','','生产结果报告','生产结果报告','SAP','','','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.837099'),(32,'操作产出率报告（按物料）','','','生产结果报告','生产结果报告','SAP','参观','A','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.837790'),(33,'生产报告','','','生产结果报告','生产结果报告','SAP','中层','C','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.838601'),(34,'按操作统计的 SFC 平均周期时间报告','','','生产结果报告','生产结果报告','SAP','底层','C','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.839256'),(35,'SFC 平均周期时间报告（按工单排序）','','','生产结果报告','生产结果报告','SAP','底层','A','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.839918'),(36,'SFC 周期时间报告','','','生产结果报告','生产结果报告','SAP','底层','B','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.840534'),(37,'工单周期时间报告','','','生产结果报告','生产结果报告','SAP','中层','A','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.841255'),(38,'工作中心产出率报告','','','生产结果报告','生产结果报告','SAP','中层','A','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.842005'),(39,'执行操控板查看器','','','生产结果报告','生产结果报告','SAP','操作层','D','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.842780'),(40,'资源占用计划','','','资源分配和状态管理','资源分配和状态管理','HYDRA','底层','B','0',0,'1','2018-08-07 06:05:19','2018-08-21 10:39:40.843617'),(41,'控制图和直方图','','','质量报告','质量报告','HYDRA','高层管理','A','1',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.844394'),(42,'缺陷主因分析','','','质量报告','质量报告','HYDRA','中高层','A','1',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.845133'),(43,'DPMO 报告','','','质量报告','质量报告','SAP','中高层','A','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.845867'),(44,'NC 日志报告','','','质量报告','质量报告','SAP','操作层','D','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.846547'),(45,'按物料排序的 NC 汇总报告','','','质量报告','质量报告','SAP','中层','B','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.847153'),(46,'按 NC 代码排序的 NC 汇总报告','','','质量报告','质量报告','SAP','中层','B','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.847761'),(47,'打开的 NC 汇总报告','','','质量报告','质量报告','SAP','底层','C','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.848431'),(48,'维修循环作业报告','','','质量报告','质量报告','SAP','底层','C','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.849050'),(49,'数据收集定义报告','','','质量报告','质量报告','SAP','底层','C','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.849705'),(50,'数据收集结果报告','','','质量报告','质量报告','SAP','底层','C','0',0,'1','2018-08-07 06:09:33','2018-08-21 10:39:40.850375'),(51,'在制品概览','','','材料和生产物流','材料和生产物流','HYDRA','中层','A','0',0,'1','2018-08-07 06:10:41','2018-08-21 10:39:40.851057'),(52,'失效统计','','','材料和生产物流','材料和生产物流','HYDRA','中高层','A','0',0,'1','2018-08-07 06:10:41','2018-08-21 10:39:40.851725'),(53,'图形化批量和批次跟踪','','','产品跟踪和追溯','产品跟踪和追溯','HYDRA','底层','A','0',0,'1','2018-08-07 06:14:03','2018-08-21 10:39:40.852407'),(54,'作业日志报告（日报）','','','WIP报告','WIP报告','SAP','底层','A','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.852999'),(55,'SFC 报告','','','WIP报告','WIP报告','SAP','底层','B','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.853590'),(56,'车间作业控制组报告','','','WIP报告','WIP报告','SAP','操作层','D','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.854179'),(57,'验收报告','','','WIP报告','WIP报告','SAP','操作层','D','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.854780'),(58,'注释报告','','','WIP报告','WIP报告','SAP','操作层','D','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.855573'),(59,'保留报告','','','WIP报告','WIP报告','SAP','操作层','D','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.856270'),(60,'工单报告','','','WIP报告','WIP报告','SAP','底层','A','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.856855'),(61,'工单报告（按步骤）','','','WIP报告','WIP报告','SAP','底层','C','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.857463'),(62,'工单计划报告','','','WIP报告','WIP报告','SAP','底层','B','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.858072'),(63,'按物料排序的 WIP 报告','','','WIP报告','WIP报告','SAP','中层','B','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.858626'),(64,'按操作排序的 WIP 报告','','','WIP报告','WIP报告','SAP','中层','C','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.859257'),(65,'按工作中心排序的 WIP 报告','','','WIP报告','WIP报告','SAP','中层','B','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.859869'),(66,'ECO 详细信息报告','','','WIP报告','WIP报告','SAP','底层','B','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.860504'),(67,'ECO 汇总报告','','','WIP报告','WIP报告','SAP','底层','C','0',0,'1','2018-08-07 06:19:48','2018-08-21 10:39:40.861088'),(68,'帐龄报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.861666'),(69,'原建汇总报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.862280'),(70,'包装报告','','','结构报告','结构报告','SAP','操作层','D','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.862829'),(71,'资源设置报告','','','结构报告','结构报告','SAP','操作层','D','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.863419'),(72,'组件使用报告','','','结构报告','结构报告','SAP','操作层','D','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.864013'),(73,'设备历史报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.864579'),(74,'SFC 前进位置标识报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.865169'),(75,'SFC 位置标识作业报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.866275'),(76,'SFC 位置标识历史报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.865713'),(77,'SFC 位置标识状态报告','','','结构报告','结构报告','SAP','底层','C','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.866869'),(78,'ODS 组件使用报告','','','结构报告','结构报告','SAP','操作层','D','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.867413'),(79,'ODS 原建汇总报告','','','结构报告','结构报告','SAP','底层','D','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.867939'),(80,'车间库存报告','','','结构报告','结构报告','SAP','底层','B','0',0,'1','2018-08-07 06:24:46','2018-08-21 10:39:40.868463'),(81,'在岗报告','','','人工报告','人工报告','SAP','中层','B','0',0,'1','2018-08-07 06:26:17','2018-08-21 10:39:40.868982'),(82,'人工费用代码汇总报告','','','人工报告','人工报告','SAP','底层','B','0',0,'1','2018-08-07 06:26:17','2018-08-21 10:39:40.869504'),(83,'人员班次记录','','','人工报告','人工报告','HYDRA','底层','B','0',0,'1','2018-08-07 06:26:17','2018-08-21 10:39:40.870108'),(84,'审计日志报告','','','系统和主数据报告','系统和主数据报告','SAP','中高层','C','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.870739'),(85,'系统使用报告','','','系统和主数据报告','系统和主数据报告','SAP','IT','B','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.871421'),(86,'BOM 报告','','','系统和主数据报告','系统和主数据报告','SAP','中层','B','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.872120'),(87,'用户证明报告','','','系统和主数据报告','系统和主数据报告','SAP','IT','B','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.872734'),(88,'应有标准日志报告','','','系统和主数据报告','系统和主数据报告','SAP','底层','C','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.873324'),(89,'数据库诊断报告','','','系统和主数据报告','系统和主数据报告','SAP','IT','C','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.873931'),(90,'后安装验证报告','','','系统和主数据报告','系统和主数据报告','SAP','IT','C','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.874568'),(91,'事务日志维护','','','系统和主数据报告','系统和主数据报告','SAP','IT','B','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.875171'),(92,'事务日志报告','','','系统和主数据报告','系统和主数据报告','SAP','IT','C','0',0,'1','2018-08-07 06:30:56','2018-08-21 10:39:40.875731'),(93,'测试计划附件报告','','','设备集成报告','设备集成报告','SAP','中高层','B','0',0,'1','2018-08-07 06:39:12','2018-08-21 10:39:40.876263'),(94,'测试计划测量报告','','','设备集成报告','设备集成报告','SAP','中层','C','0',0,'1','2018-08-07 06:39:12','2018-08-21 10:39:40.876785'),(95,'测试计划结果报告','','','设备集成报告','设备集成报告','SAP','中高层','B','0',0,'1','2018-08-07 06:39:12','2018-08-21 10:39:40.877370'),(96,'CNC 程序附件报告','','','设备集成报告','设备集成报告','SAP','中层','C','0',0,'1','2018-08-07 06:39:12','2018-08-21 10:39:40.877967'),(97,'协作日志报告','','','其他','其他','SAP','IT','D','0',0,'1','2018-08-07 06:40:19','2018-08-21 10:39:40.878510'),(98,'设置点组报告','','','其他','其他','SAP','IT','D','0',0,'1','2018-08-07 06:40:19','2018-08-21 10:39:40.879079');
/*!40000 ALTER TABLE `dm_dsgn_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_dsgn_report_datasource_sql`
--

DROP TABLE IF EXISTS `dm_dsgn_report_datasource_sql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_dsgn_report_datasource_sql` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_report` int(11) NOT NULL,
  `datasource_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `sql_statement` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_dsgn_report_datasource_sql`
--

LOCK TABLES `dm_dsgn_report_datasource_sql` WRITE;
/*!40000 ALTER TABLE `dm_dsgn_report_datasource_sql` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_dsgn_report_datasource_sql` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_hr_fee_level`
--

DROP TABLE IF EXISTS `dm_hr_fee_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_hr_fee_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人力资源管理领域，薪资级别',
  `level` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fee_start` int(11) NOT NULL,
  `fee_end` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_hr_fee_level`
--

LOCK TABLES `dm_hr_fee_level` WRITE;
/*!40000 ALTER TABLE `dm_hr_fee_level` DISABLE KEYS */;
INSERT INTO `dm_hr_fee_level` VALUES (1,'初级',5000,7000,'2018-08-13 09:05:58','2018-08-13 09:05:58.881487'),(2,'中级',8000,16000,'2018-08-13 09:05:58','2018-08-13 09:05:58.882249'),(3,'高级',16000,20000,'2018-08-13 09:05:58','2018-08-13 09:05:58.883011'),(4,'资深',20000,35000,'2018-08-13 09:05:58','2018-08-13 09:05:58.883818');
/*!40000 ALTER TABLE `dm_hr_fee_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_it_prdct_platform_app`
--

DROP TABLE IF EXISTS `dm_it_prdct_platform_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_it_prdct_platform_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'it技术管理领域的产品平台app',
  `app_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `app_use_scope` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'App使用范围',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_it_prdct_platform_app`
--

LOCK TABLES `dm_it_prdct_platform_app` WRITE;
/*!40000 ALTER TABLE `dm_it_prdct_platform_app` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_it_prdct_platform_app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_it_system`
--

DROP TABLE IF EXISTS `dm_it_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_it_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_it_system`
--

LOCK TABLES `dm_it_system` WRITE;
/*!40000 ALTER TABLE `dm_it_system` DISABLE KEYS */;
INSERT INTO `dm_it_system` VALUES (1,'质量管理SPC','2018-08-24 07:20:13','2018-08-24 07:20:13.838323');
/*!40000 ALTER TABLE `dm_it_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_it_thirdparty_software`
--

DROP TABLE IF EXISTS `dm_it_thirdparty_software`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_it_thirdparty_software` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '软件管理中心领域，第三方软件信息',
  `software_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `current_software_version` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `software_author` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_it_thirdparty_software`
--

LOCK TABLES `dm_it_thirdparty_software` WRITE;
/*!40000 ALTER TABLE `dm_it_thirdparty_software` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_it_thirdparty_software` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_it_thirdparty_software_activation`
--

DROP TABLE IF EXISTS `dm_it_thirdparty_software_activation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_it_thirdparty_software_activation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '第三方软件激活信息',
  `id_thirdparty_software` int(11) NOT NULL,
  `prd_software_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `version` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `download_url` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `activation_code` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_it_thirdparty_software_activation`
--

LOCK TABLES `dm_it_thirdparty_software_activation` WRITE;
/*!40000 ALTER TABLE `dm_it_thirdparty_software_activation` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_it_thirdparty_software_activation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_article_collect`
--

DROP TABLE IF EXISTS `dm_knw_article_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_article_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `article_url` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `article_brief_desc` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `article_content` text COLLATE utf8_unicode_ci NOT NULL,
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `tag_wait_for_done` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '等待去做的标签，例如“出口成章”、“熟记脉络”、“参照练习”、“反复训练”、“有待应证”、“应用到实际”、“加入文集”，“加入创作（整编到自己写的书籍或者系列文章）”、“加入PPT（自己把多方面知识和资料、想法整理成各个不同方面的PPT、以便今后工作、教学培训、售前售后使用）”，此字段值为枚举值。',
  `author` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `word_write_time` datetime NOT NULL COMMENT '文章字面编写时间，可能不是原创编写时间',
  `origin_write_time` datetime NOT NULL COMMENT '原创编写时间',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_article_collect`
--

--
-- Table structure for table `dm_knw_category`
--

DROP TABLE IF EXISTS `dm_knw_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识分类',
  `category` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_category`
--

LOCK TABLES `dm_knw_category` WRITE;
/*!40000 ALTER TABLE `dm_knw_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_knw_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_choice_answer`
--

DROP TABLE IF EXISTS `dm_knw_choice_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_choice_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '(知识中心领域)选择题的答案可选项',
  `id_choice_question` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `one_answer` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `is_right_answer` bit(1) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_choice_answer`
--

LOCK TABLES `dm_knw_choice_answer` WRITE;
/*!40000 ALTER TABLE `dm_knw_choice_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_knw_choice_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_choice_question`
--

DROP TABLE IF EXISTS `dm_knw_choice_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_choice_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '（知识中心领域）选择题的题目',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `study_card_content` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '学习卡片的内容',
  `urls` varchar(600) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '知识关联的URL',
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `audit_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'waiting',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_choice_question`
--

LOCK TABLES `dm_knw_choice_question` WRITE;
/*!40000 ALTER TABLE `dm_knw_choice_question` DISABLE KEYS */;
INSERT INTO `dm_knw_choice_question` VALUES (2,'Chocolatey是Windows的包管理工具？','巧克力在很多文化里都代表了美好的事物。如果你是Windows的使用者，可能Windows复杂、丑陋的包管理正在把你折腾的很惨。*NIX平台上的各种优雅的包管理工具这时让人非常眼馋。直到有一天Chocolatey出现了，让我看到了Windows平台上的包管理朝着正确的方向前进了。看来，和巧克力沾边的东西都能让人产生幸福的感觉。','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.746848'),(3,'Chocolatey的命令简写是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.747598'),(4,'NSwag和Swashbuckle的区别，如下几个方面正确的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.748199'),(5,'以下是微服务框架的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.748787'),(6,'以下哪些是.net微服务框架？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.749344'),(7,'以下哪些是跨平台开发框架？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.749889'),(8,'对于http2.0和http1.0以下说法不正确的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.750391'),(9,'对于.net core和.net 4.0以下说法正确的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.750881'),(10,'对于.net core、.net standard、.net 4.0以下错误的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.751376'),(11,'TypeScript有哪些新特性？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.751958'),(12,'关于Maven构建工作流，如下说法错误的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.752440'),(13,'命令“docker exec -it 238 ls /”，如下说法错误的是？','','','','waiting','2018-08-01 11:25:04','2018-08-01 11:25:04.752933'),(14,'Indicator','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.562860'),(15,'Chocolatey','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.562179'),(16,'recommended','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.561553'),(17,'consists','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.560965'),(18,'approaches','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.560312'),(19,'expose','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.559645'),(20,'Swashbuckle','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.559056'),(21,'Swagger','','','EnglishWord','waiting','2018-08-01 11:25:04','2018-08-02 05:32:51.558319'),(22,'artifacts','','','EnglishWord','waiting','2018-08-02 03:59:51','2018-08-02 05:32:51.557607'),(23,'treat ','There are two sections to the configuration. An array of ReRoutes and a GlobalConfiguration. The ReRoutes are the objects that tell Ocelot how to treat an upstream request. The Global configuration is a bit hacky and allows overrides of ReRoute specific settings. It’s useful if you don’t want to manage lots of ReRoute specific settings.','http://ocelot.readthedocs.io/en/latest/features/configuration.html','EnglishWord','waiting','2018-08-02 05:29:55','2018-08-02 05:32:51.556801'),(24,'MacOS的防火墙有几个，分别叫什么名字？','','','MacOS','waiting','2018-08-05 13:32:33','2018-08-07 15:18:10.741624'),(25,'以下关于Ocelot的说法错误的是？','','','微服务','waiting','2018-08-05 13:33:25','2018-08-05 13:34:35.208562'),(26,'Mysql里面如何备份表？','create table [tablename] (select * from [tablename]);','','MySql','waiting','2018-08-05 14:14:57','2018-08-05 14:15:26.231987'),(27,'如下对于alpine linux说法正确的是？','','','Docker','waiting','2018-08-07 15:17:40','2018-08-07 15:18:10.740827'),(28,'命令：sed \'1a hello world\' test.txt 是什么含义？','','','linux基础','waiting','2018-08-07 15:45:31','2018-08-07 15:45:31.853937'),(29,'如下对于TiDB说法正确的是？','','http://www.phpchina.com/portal.php?mod=view&aid=40292','TiDB,NewSQL','waiting','2018-08-09 05:36:47','2018-08-09 05:59:49.654852'),(30,'如下对于Spanner/F1说法正确的是？','','','NewSQL','waiting','2018-08-09 05:39:25','2018-08-09 05:39:25.577231'),(31,'智慧生命文明进程等级下列说法正确的是？','第一个等级：能掌握行星能源，包括行星自身能源，外界进入行星的能源和外围恒星能源；第二个等级：脱离行星，进入外太空寻找新的行星能源、利用恒心能源；第三个等级：掌握整个星系的能源；第四个等级：掌握整个宇宙能源；第五个等级：能利用多重宇宙的能源；第六个等级：能够掌握时间和空间；第七个等级：是现在人类所无法理解的等级','','宇宙','waiting','2018-08-09 15:39:25','2018-08-09 15:39:25.505928'),(32,'下列关于RBAC模型说法正确的是？','基于角色的权限访问控制（Role-Based Access Control）作为传统访问控制（自主访问，强制访问）的有前景的代替受到广泛的关注。在RBAC中，权限与角色相关联，用户通过成为适当角色的成员而得到这些角色的权限。这就极大地简化了权限的管理。在一个组织中，角色是为了完成各种工作而创造，用户则依据它的责任和资格来被指派相应的角色，用户可以很容易地从一个角色被指派到另一个角色。角色可依新的需求和系统的合并而赋予新的权限，而权限也可根据需要而从某角色中回收。角色与角色的关系可以建立起来以囊括更广泛的客观情况。','https://baike.baidu.com/item/RBAC/1328788?fr=aladdin','通用业务设计','waiting','2018-08-14 10:19:02','2018-08-14 10:21:14.156649'),(33,'关于Sql Server中with...as下列说法错误的是？','','','Sql Server','waiting','2018-08-17 10:38:11','2018-08-17 10:38:11.498344');
/*!40000 ALTER TABLE `dm_knw_choice_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_clipping`
--

DROP TABLE IF EXISTS `dm_knw_clipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_clipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '剪报',
  `id_user` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_clipping`
--

LOCK TABLES `dm_knw_clipping` WRITE;
/*!40000 ALTER TABLE `dm_knw_clipping` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_knw_clipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_compare`
--

DROP TABLE IF EXISTS `dm_knw_compare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_compare` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识对比，这是一个基础的数据结构，所以用来承载所有对比文本数据',
  `id_dm_knw_compare_title` int(11) NOT NULL COMMENT '对比主题id',
  `difference_point` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '区别点',
  `reference_obj` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '对比对象',
  `current_state` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '对比对象的现状',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_compare`
--

LOCK TABLES `dm_knw_compare` WRITE;
/*!40000 ALTER TABLE `dm_knw_compare` DISABLE KEYS */;
INSERT INTO `dm_knw_compare` VALUES (1,1,'过程以前的状态','分析用控制图','未知','2018-08-28 05:51:12','2018-08-28 05:51:12.342149'),(2,1,'过程以前的状态','控制用控制图','已知','2018-08-28 05:51:12','2018-08-28 05:51:12.343390'),(3,1,'作图需要子组数','分析用控制图','每次20组到25组','2018-08-28 05:51:12','2018-08-28 05:51:12.344489'),(4,1,'作图需要子组数','控制用控制图','每次一组','2018-08-28 05:51:12','2018-08-28 05:51:12.345330'),(5,1,'控制图的界限','分析用控制图','需计算','2018-08-28 05:51:12','2018-08-28 05:51:12.346134'),(6,1,'控制图的界限','控制用控制图','延长前控制限','2018-08-28 05:51:12','2018-08-28 05:51:12.347027'),(7,1,'使用目的','分析用控制图','了解过程','2018-08-28 05:51:12','2018-08-28 05:51:12.347858'),(8,1,'使用目的','控制用控制图','控制过程','2018-08-28 05:51:12','2018-08-28 05:51:12.348776'),(9,1,'使用人员','分析用控制图','工艺、质管','2018-08-28 05:51:12','2018-08-28 05:51:12.349470'),(10,1,'使用人员','控制用控制图','现场操作和管理人员','2018-08-28 05:51:12','2018-08-28 05:51:12.350144');
/*!40000 ALTER TABLE `dm_knw_compare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_compare_title`
--

DROP TABLE IF EXISTS `dm_knw_compare_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_compare_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '对比的主题',
  `title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `division_tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '区分的标记，比如对比的轮次，对比的数据来源等等',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_compare_title`
--

LOCK TABLES `dm_knw_compare_title` WRITE;
/*!40000 ALTER TABLE `dm_knw_compare_title` DISABLE KEYS */;
INSERT INTO `dm_knw_compare_title` VALUES (1,'控制用控制图与分析用控制图的主要区别','来源于《SPC实战》，由文放怀主编，广东经济出版社出版','2018-08-28 05:47:14','2018-08-28 05:47:14.920840');
/*!40000 ALTER TABLE `dm_knw_compare_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_enum_tag_wait_for_done_for_article`
--

DROP TABLE IF EXISTS `dm_knw_enum_tag_wait_for_done_for_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_enum_tag_wait_for_done_for_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用来存储枚举，标记等待处理文章的处理方式枚举',
  `item_tag_wait_for_done` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_enum_tag_wait_for_done_for_article`
--


--
-- Table structure for table `dm_knw_image_album`
--

DROP TABLE IF EXISTS `dm_knw_image_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_image_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片专辑',
  `album_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_image_album`
--

LOCK TABLES `dm_knw_image_album` WRITE;
/*!40000 ALTER TABLE `dm_knw_image_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_knw_image_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_teleplay`
--

DROP TABLE IF EXISTS `dm_knw_teleplay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_teleplay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '影视领域，电视剧',
  `teleplay_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `watch_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '未看' COMMENT '未看、未看完、正在看、已看完',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_teleplay`
--

LOCK TABLES `dm_knw_teleplay` WRITE;
/*!40000 ALTER TABLE `dm_knw_teleplay` DISABLE KEYS */;
INSERT INTO `dm_knw_teleplay` VALUES (1,'沙海','老九门','未看','2018-08-14 05:06:00','2018-08-14 05:07:19.639861'),(2,'三体','三体','未看','2018-08-21 08:41:35','2018-08-21 08:41:35.646926'),(3,'妖神记','妖神记','未看完','2018-08-21 08:48:01','2018-08-21 08:48:01.514330'),(4,'超神学院','超神学院','未看完','2018-08-21 08:48:17','2018-08-21 08:48:17.591674');
/*!40000 ALTER TABLE `dm_knw_teleplay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_url_collect`
--

DROP TABLE IF EXISTS `dm_knw_url_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_url_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '网站收藏',
  `url` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `url_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(45) COLLATE utf8_unicode_ci DEFAULT '',
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `id_user` int(11) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_url_collect`
--

LOCK TABLES `dm_knw_url_collect` WRITE;
/*!40000 ALTER TABLE `dm_knw_url_collect` DISABLE KEYS */;
INSERT INTO `dm_knw_url_collect` VALUES (1,'https://hkjss.xyz/','黑科技网络','','vpn',1,'2018-08-07 02:57:48','2018-08-07 03:10:05.986179'),(2,'https://www.cnblogs.com/andyfengzp/p/6434287.html','学习Spring-Session+Redis实现session共享','','Spring Cloud',1,'2018-09-06 10:45:41','2018-09-06 10:45:41.394744');
/*!40000 ALTER TABLE `dm_knw_url_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_knw_video_album`
--

DROP TABLE IF EXISTS `dm_knw_video_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_knw_video_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频专辑',
  `album_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_knw_video_album`
--

LOCK TABLES `dm_knw_video_album` WRITE;
/*!40000 ALTER TABLE `dm_knw_video_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_knw_video_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_prdct_product`
--

DROP TABLE IF EXISTS `dm_prdct_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prdct_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prdct_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `prdct_category` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_what_app_in` int(11) NOT NULL COMMENT '承载app的产品，可能是多个app承载一个产品，到这个时候需要修改表结构',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prdct_product`
--

LOCK TABLES `dm_prdct_product` WRITE;
/*!40000 ALTER TABLE `dm_prdct_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_prdct_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_prj_issue`
--

DROP TABLE IF EXISTS `dm_prj_issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prj_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '（项目领域）',
  `issue_title` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `issue_desc` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `crate_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prj_issue`
--


--
-- Table structure for table `dm_prj_issue_convert_from_todo_records`
--

DROP TABLE IF EXISTS `dm_prj_issue_convert_from_todo_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prj_issue_convert_from_todo_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '这是todo转成issue的记录表，遵循谁使用谁记录的原则（旁置设计原则，谁使用，谁负责记录，转换源头不承担额外数据管理结构，所以这里是项目领域要使用用户中心领域的todo，则由项目领域或者其子系统负责管理附加数据管理结构。当然由领域或者子系统管理这些附加的数据结构，但是不由源或者目标对象管理，尽量保持对象存粹，避免把对象设计成excel表而破坏对象的单一性和纯粹性，保证可阅读性，啥都记录）',
  `id_todo` int(11) NOT NULL COMMENT '一条记录关联todoid和issueid，可以多对多，足够自由便利，一个todo可能关联到一个或者多个issue，同时一个issue也可以关联到多个todo',
  `id_issue` int(11) NOT NULL COMMENT '一条记录关联todoid和issueid，可以多对多，足够自由便利，一个todo可能关联到一个或者多个issue，同时一个issue也可以关联到多个todo',
  `id_operator` int(11) NOT NULL COMMENT '操作人id',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prj_issue_convert_from_todo_records`
--

LOCK TABLES `dm_prj_issue_convert_from_todo_records` WRITE;
/*!40000 ALTER TABLE `dm_prj_issue_convert_from_todo_records` DISABLE KEYS */;
INSERT INTO `dm_prj_issue_convert_from_todo_records` VALUES (1,21,1,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878'),(2,33,2,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878'),(3,36,3,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878'),(4,39,4,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878'),(5,57,5,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878'),(6,58,6,1,'2018-08-13 05:57:42','2018-08-13 05:57:42.879878');
/*!40000 ALTER TABLE `dm_prj_issue_convert_from_todo_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_prj_issue_in_sprint`
--

DROP TABLE IF EXISTS `dm_prj_issue_in_sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prj_issue_in_sprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_issue` int(11) NOT NULL,
  `id_sprint` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prj_issue_in_sprint`
--

LOCK TABLES `dm_prj_issue_in_sprint` WRITE;
/*!40000 ALTER TABLE `dm_prj_issue_in_sprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_prj_issue_in_sprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_prj_project`
--

DROP TABLE IF EXISTS `dm_prj_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prj_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prj_project`
--

LOCK TABLES `dm_prj_project` WRITE;
/*!40000 ALTER TABLE `dm_prj_project` DISABLE KEYS */;
INSERT INTO `dm_prj_project` VALUES (1,'质量管理SPC','2018-08-24 07:18:29','2018-08-24 07:18:29.133863'),(2,'PConnect','2018-08-24 07:23:05','2018-08-24 07:23:05.458460');
/*!40000 ALTER TABLE `dm_prj_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_prj_sprint`
--

DROP TABLE IF EXISTS `dm_prj_sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_prj_sprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目管理领域的冲刺',
  `sprint_title` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `sprint_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pending',
  `bosshead_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_prj_sprint`
--

LOCK TABLES `dm_prj_sprint` WRITE;
/*!40000 ALTER TABLE `dm_prj_sprint` DISABLE KEYS */;
INSERT INTO `dm_prj_sprint` VALUES (1,'完成基于我的macbookpro的研发网络部署和运维管理流程',NULL,NULL,1,'pending',1,'2018-08-13 03:05:01','2018-08-13 03:05:44.039356'),(2,'完成基于todo功能的混合云基础架构和实现',NULL,NULL,2,'pending',1,'2018-08-13 03:06:33','2018-08-13 03:06:33.503605'),(3,'进一步完成Ocelot的微服务框架template',NULL,NULL,3,'pending',1,'2018-08-13 03:09:10','2018-08-13 03:10:08.070014'),(4,'完成基本的SpringCloud微服务框架template',NULL,NULL,4,'pending',1,'2018-08-13 03:09:10','2018-08-13 03:10:08.069230'),(5,'完成完整的Ocelot的微服务框架template',NULL,NULL,5,'pending',1,'2018-08-13 03:09:10','2018-08-13 03:10:08.068385'),(6,'完成完整的SpringCloud微服务框架template',NULL,NULL,6,'pending',1,'2018-08-13 03:09:10','2018-08-13 03:10:08.067225');
/*!40000 ALTER TABLE `dm_prj_sprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_artifact`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_artifact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_artifact` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参选对象',
  `artifact_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `artifact_author` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `official_website` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `replacing_official_website` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `newest_version` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `participation_in_version` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '参选版本',
  `version_chosen_reason` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `deployment_desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `how_to_get` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_artifact`
--

LOCK TABLES `dm_rd_technology_selection_artifact` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_artifact` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_artifact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_contrast_data`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_contrast_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_contrast_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_contrast_parameter` int(11) NOT NULL,
  `id_artifact` int(11) NOT NULL,
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `pass_conclusion` bit(1) NOT NULL DEFAULT b'0',
  `is_into_secondary_contrast` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否排入第二轮对比，情况一：有时候第一轮对比完后分不出高低，这时候可能需要第二轮对比。情况二：有时候第一轮对比后，结果出乎大家意料，这时候除了对第一轮对比进行排查之外，可对选入第二轮对比的对比参数进行第二轮对比。',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_contrast_data`
--

LOCK TABLES `dm_rd_technology_selection_contrast_data` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_contrast_data_secondary`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_contrast_data_secondary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_contrast_data_secondary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '第二轮对比',
  `id_contrast_parameter` int(11) NOT NULL,
  `id_artifact` int(11) NOT NULL,
  `remark` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `pass_conclusion` bit(1) NOT NULL DEFAULT b'0',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_contrast_data_secondary`
--

LOCK TABLES `dm_rd_technology_selection_contrast_data_secondary` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_data_secondary` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_data_secondary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_contrast_parameters`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_contrast_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_contrast_parameters` (
  `id` int(11) NOT NULL COMMENT '参选参数',
  `term_parameter` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `is_principal` bit(1) NOT NULL DEFAULT b'0',
  `id_relating_decision_specification` int(11) DEFAULT 0,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_contrast_parameters`
--

LOCK TABLES `dm_rd_technology_selection_contrast_parameters` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_parameters` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_contrast_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_decision`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_decision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_decision` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术选型决策',
  `id_artifact_successful_bid` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `contrast_conclustion` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `rate_for_vote` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '这里用json存储全部artifact投票比例',
  `countersignature` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '会签，这里使用json存储会签的签章文件',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_decision`
--

LOCK TABLES `dm_rd_technology_selection_decision` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_decision_discuss_record`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_decision_discuss_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_decision_discuss_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术选型讨论记录',
  `id_selection_project` int(11) NOT NULL,
  `discuss_date` date NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `conclusion` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `rate_for_vote` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '这里用json存储全部artifact投票比例',
  `undetermined` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `is_final_pass` bit(1) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_decision_discuss_record`
--

LOCK TABLES `dm_rd_technology_selection_decision_discuss_record` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision_discuss_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision_discuss_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_decision_specification_in_project`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_decision_specification_in_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_decision_specification_in_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术选型决策规范，这里主要是决策关键点，以及其他规范要求，并不是全部的对比参数要求',
  `id_tech_selection_project` int(11) NOT NULL,
  `term` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `id_proposer_user` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_decision_specification_in_project`
--

LOCK TABLES `dm_rd_technology_selection_decision_specification_in_project` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision_specification_in_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_rd_technology_selection_decision_specification_in_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_establishment`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_establishment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_establishment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_selection_project` int(11) NOT NULL,
  `selection_project_title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_bosshead_user` int(11) NOT NULL,
  `risk_desc` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `aim` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `min_aim_annotation` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `spend_period` int(11) NOT NULL COMMENT '单位为周',
  `spend_period_unit` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'week',
  `spend_hr` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `spend_other_resource` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `check_point_desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `countersignature` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '存储json格式的会签印章',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_establishment`
--

LOCK TABLES `dm_rd_technology_selection_establishment` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_establishment` DISABLE KEYS */;
INSERT INTO `dm_rd_technology_selection_establishment` VALUES (1,1,'选择一个思维导图的第三方web前端插件',1,'未限制工时','选择一个合适的思维导图web前端插件，将自己的知识体系以思维导图的方式呈现出来。','选择一个合适的思维导图web前端插件，将自己的知识体系以思维导图的方式呈现出来。',2,'day','1','无','无','{userid:1,signature:\"123\"}','2018-08-15 11:42:46','2018-08-15 11:42:46.385970');
/*!40000 ALTER TABLE `dm_rd_technology_selection_establishment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_rd_technology_selection_project`
--

DROP TABLE IF EXISTS `dm_rd_technology_selection_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_rd_technology_selection_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术选型课题',
  `project_title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `project_background` varchar(150) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `project_aim` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `id_bosshead_user` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_rd_technology_selection_project`
--

LOCK TABLES `dm_rd_technology_selection_project` WRITE;
/*!40000 ALTER TABLE `dm_rd_technology_selection_project` DISABLE KEYS */;
INSERT INTO `dm_rd_technology_selection_project` VALUES (1,'选择一个思维导图的第三方web前端插件','因为要给自己的知识体系做知识图谱记录和分析，以便自己能够更好的整体掌握知识脉络；这也是记助系列的基础架构之一。','选择一个合适的思维导图web前端插件，将自己的知识体系以思维导图的方式呈现出来。',1,'2018-08-15 10:45:54','2018-08-15 10:45:54.268380'),(2,'对比出了帆软之外的其他报表系统，找出一到两款帆软报表的替代品','因为帆软报表也不是完美的，并且帆软报表价格太贵，整合方面也不能说完美，所以在面对项目的时候需要报表系统有多项选择','至少选择一个帆软报表的替代品',1,'2018-08-15 11:47:06','2018-08-15 11:47:06.741024'),(3,'选择一款家庭版的CMS','由于家庭中生活和学习有整理、搜索、消费自产或者从外界获得的多媒体资料的需要，所以需要寻找一款强大、简单和合适的内容管理系统','至少选一个比cnodejs强的',1,'2018-08-18 12:43:00','2018-08-17 16:43:07.869872'),(4,'康师傅报表演示','不明，只知道大概是为了康师傅的某个项目，可能是MES项目，做了大量的伪数据演示报表。应为并没有明确的需求说明，所以这次预研和选型都是按照任爱新提出的方案并直接使用FineReport报表工具进行报表制作，并由袁程祺进行最后选定','至少做出任爱新和袁程祺指定要做的报表和效果',1,'2018-08-21 09:38:49','2018-08-21 09:38:49.541545');
/*!40000 ALTER TABLE `dm_rd_technology_selection_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_surcd_snippets`
--

DROP TABLE IF EXISTS `dm_surcd_snippets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_surcd_snippets` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '源码管理领域，代码块管理',
  `snippets_title` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '代码段标题，供搜索查询阅读使用',
  `snippets_desc` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '代码段描述，这里可以描述背景、用途、相关知识等',
  `code_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '代码段名称，这个字段可选，用来命名这段代码，可能用于在开发工具中快速快捷录入',
  `code_file` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '代码块',
  `id_owner_user` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_surcd_snippets`
--

--
-- Table structure for table `dm_tm_schedule`
--

DROP TABLE IF EXISTS `dm_tm_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_tm_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `owner_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_tm_schedule`
--

LOCK TABLES `dm_tm_schedule` WRITE;
/*!40000 ALTER TABLE `dm_tm_schedule` DISABLE KEYS */;
INSERT INTO `dm_tm_schedule` VALUES (1,'提醒还建设银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:58.055250'),(2,'提醒还民生银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.793760'),(3,'提醒还中信银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.794456'),(4,'提醒还平安银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.795359'),(5,'提醒还华夏银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.795978'),(6,'提醒还工商银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.796616'),(7,'提醒还农业银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.797232'),(8,'提醒还花旗银行信用卡','',1,'2018-08-05 14:40:28','2018-08-05 14:40:28.797920');
/*!40000 ALTER TABLE `dm_tm_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_tm_schedule_rule`
--

DROP TABLE IF EXISTS `dm_tm_schedule_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_tm_schedule_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_tm_schedule_rule`
--

LOCK TABLES `dm_tm_schedule_rule` WRITE;
/*!40000 ALTER TABLE `dm_tm_schedule_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_tm_schedule_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_choice_question_answer_record`
--

DROP TABLE IF EXISTS `dm_usr_choice_question_answer_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_choice_question_answer_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '（用户中心领域）选择题的答题记录',
  `id_choice_question` int(11) NOT NULL,
  `id_choice_answer` int(11) NOT NULL,
  `id_right_choice_answer` int(11) NOT NULL,
  `result` int(11) NOT NULL COMMENT '这个答案采取记分方式，满分是5分，单选题对了直接得5分，多选题正确的选项不超过5个，对1题得1分，全对得5分',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_choice_question_answer_record`
--

LOCK TABLES `dm_usr_choice_question_answer_record` WRITE;
/*!40000 ALTER TABLE `dm_usr_choice_question_answer_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_choice_question_answer_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_choice_question_tag_by_user`
--

DROP TABLE IF EXISTS `dm_usr_choice_question_tag_by_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_choice_question_tag_by_user` (
  `id` int(11) NOT NULL COMMENT '(用户中心领域)用户给选择题标记的tag',
  `id_choice_question` int(11) NOT NULL,
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_choice_question_tag_by_user`
--

LOCK TABLES `dm_usr_choice_question_tag_by_user` WRITE;
/*!40000 ALTER TABLE `dm_usr_choice_question_tag_by_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_choice_question_tag_by_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_diary`
--

DROP TABLE IF EXISTS `dm_usr_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_diary` (
  `id` int(11) NOT NULL COMMENT '（用户领域）所有当天数据汇编成当天日记',
  `diary_date` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_diary`
--

LOCK TABLES `dm_usr_diary` WRITE;
/*!40000 ALTER TABLE `dm_usr_diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_schedule_emmit_todo_record`
--

DROP TABLE IF EXISTS `dm_usr_schedule_emmit_todo_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_schedule_emmit_todo_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '计划产生todo的产生记录表id.',
  `schedule_id` int(11) NOT NULL COMMENT '计划的id',
  `todoes_id` int(11) NOT NULL COMMENT 'todo的id',
  `schedule_emmit_todo_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '计划产生出todo的时间',
  `schedule_time` datetime NOT NULL COMMENT 'Todo计划执行的时间',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_schedule_emmit_todo_record`
--

LOCK TABLES `dm_usr_schedule_emmit_todo_record` WRITE;
/*!40000 ALTER TABLE `dm_usr_schedule_emmit_todo_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_schedule_emmit_todo_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_study_day_duration_aim`
--

DROP TABLE IF EXISTS `dm_usr_study_day_duration_aim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_study_day_duration_aim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `date_aim` date NOT NULL,
  `aim_duration` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '7200',
  `aim_duration_unit` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Second',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_study_day_duration_aim`
--

LOCK TABLES `dm_usr_study_day_duration_aim` WRITE;
/*!40000 ALTER TABLE `dm_usr_study_day_duration_aim` DISABLE KEYS */;
INSERT INTO `dm_usr_study_day_duration_aim` VALUES (1,1,'2000-01-01','7200','Second'),(2,1,'2000-01-02','7200','Second'),(3,1,'2000-01-03','7200','Second'),(4,1,'2000-01-04','7200','Second'),(5,1,'2000-01-05','7200','Second'),(6,1,'2000-01-06','7200','Second');
/*!40000 ALTER TABLE `dm_usr_study_day_duration_aim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_study_record`
--

DROP TABLE IF EXISTS `dm_usr_study_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_study_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `study_duration` int(11) NOT NULL,
  `study_duration_unit` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Second',
  `study_product` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `study_product_phase` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_study_record`
--

LOCK TABLES `dm_usr_study_record` WRITE;
/*!40000 ALTER TABLE `dm_usr_study_record` DISABLE KEYS */;
INSERT INTO `dm_usr_study_record` VALUES (1,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-01 10:00:00','2000-01-01 10:10:00','2018-08-07 09:04:31','2018-08-07 09:04:31.793384'),(2,1,1200,'Second','再就业培训系统','金蝶财务','2000-01-01 11:00:00','2000-01-01 11:20:00','2018-08-07 09:04:31','2018-08-07 09:17:15.833541'),(3,1,600,'Second','再就业培训系统','走近华尔街','2000-01-01 11:40:00','2000-01-01 11:50:00','2018-08-07 09:04:31','2018-08-07 09:17:15.844277'),(4,1,2400,'Second','再就业培训系统','学会投资','2000-01-01 15:00:00','2000-01-01 15:40:00','2018-08-07 09:04:31','2018-08-07 09:17:15.837501'),(5,1,1800,'Second','再就业培训系统','金蝶财务','2000-01-01 16:00:00','2000-01-01 16:30:00','2018-08-07 09:04:31','2018-08-07 09:17:15.834597'),(6,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-01 16:50:00','2000-01-01 17:00:00','2018-08-07 09:04:31','2018-08-07 09:11:34.720725'),(7,1,1800,'Second','再就业培训系统','走近华尔街','2000-01-02 10:00:00','2000-01-02 10:20:00','2018-08-07 09:04:31','2018-08-07 09:17:15.841090'),(8,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-02 10:30:00','2000-01-02 10:40:00','2018-08-07 09:04:31','2018-08-07 09:15:18.193796'),(9,1,2400,'Second','再就业培训系统','走近华尔街','2000-01-02 15:00:00','2000-01-02 15:40:00','2018-08-07 09:04:31','2018-08-07 09:17:15.841861'),(10,1,600,'Second','再就业培训系统','学会投资','2000-01-03 10:00:00','2000-01-03 10:10:00','2018-08-07 09:04:31','2018-08-07 09:17:15.838442'),(11,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-03 11:00:00','2000-01-03 11:10:00','2018-08-07 09:04:31','2018-08-07 09:15:18.196878'),(12,1,1200,'Second','再就业培训系统','金蝶财务','2000-01-03 15:00:00','2000-01-03 15:20:00','2018-08-07 09:04:31','2018-08-07 09:17:15.835801'),(13,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-03 17:00:00','2000-01-03 17:10:00','2018-08-07 09:04:31','2018-08-07 09:15:18.198654'),(14,1,2400,'Second','再就业培训系统','学会投资','2000-01-04 10:00:00','2000-01-04 10:40:00','2018-08-07 09:04:31','2018-08-07 09:17:15.839395'),(15,1,600,'Second','再就业培训系统','走近华尔街','2000-01-04 11:00:00','2000-01-04 11:10:00','2018-08-07 09:04:31','2018-08-07 09:17:15.842796'),(16,1,1800,'Second','再就业培训系统','Excel高级操作','2000-01-04 14:00:00','2000-01-04 14:30:00','2018-08-07 09:04:31','2018-08-07 09:15:18.201238'),(17,1,600,'Second','再就业培训系统','金蝶财务','2000-01-04 16:00:00','2000-01-04 16:10:00','2018-08-07 09:04:31','2018-08-07 09:17:15.836684'),(18,1,600,'Second','再就业培训系统','Excel高级操作','2000-01-05 10:00:00','2000-01-05 10:10:00','2018-08-07 09:04:31','2018-08-07 09:15:18.202934'),(19,1,2400,'Second','再就业培训系统','走近华尔街','2000-01-05 14:00:00','2000-01-05 14:40:00','2018-08-07 09:04:31','2018-08-07 09:17:15.843567'),(20,1,4800,'Second','再就业培训系统','学会投资','2000-01-05 15:00:00','2000-01-05 16:20:00','2018-08-07 09:04:31','2018-08-07 11:33:54.927168');
/*!40000 ALTER TABLE `dm_usr_study_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_study_record_groupby_day`
--

DROP TABLE IF EXISTS `dm_usr_study_record_groupby_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_study_record_groupby_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `study_duration` int(11) NOT NULL,
  `study_duration_unit` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Second',
  `occur_day` date NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_study_record_groupby_day`
--

LOCK TABLES `dm_usr_study_record_groupby_day` WRITE;
/*!40000 ALTER TABLE `dm_usr_study_record_groupby_day` DISABLE KEYS */;
INSERT INTO `dm_usr_study_record_groupby_day` VALUES (43,1,7200,'Second','2000-01-01','2018-08-07 11:33:57','2018-08-07 11:33:57.678443'),(44,1,4800,'Second','2000-01-02','2018-08-07 11:33:57','2018-08-07 11:33:57.678443'),(45,1,3000,'Second','2000-01-03','2018-08-07 11:33:57','2018-08-07 11:33:57.678443'),(46,1,5400,'Second','2000-01-04','2018-08-07 11:33:57','2018-08-07 11:33:57.678443'),(47,1,7800,'Second','2000-01-05','2018-08-07 11:33:57','2018-08-07 11:33:57.678443');
/*!40000 ALTER TABLE `dm_usr_study_record_groupby_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_todoes`
--

DROP TABLE IF EXISTS `dm_usr_todoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `todo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `todo_date` datetime NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT 'todo实际执行的时间',
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pedding',
  `priority` int(11) NOT NULL DEFAULT 5,
  `period_limit` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'TheDay' COMMENT '历时限制，主要是区分长期和当日任务，这里设置标准（长期，冲刺期间，当日）',
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `owner_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes`
--

--
-- Table structure for table `dm_usr_todoes_201808051002`
--

DROP TABLE IF EXISTS `dm_usr_todoes_201808051002`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_201808051002` (
  `id` int(11) NOT NULL DEFAULT 0,
  `todo` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `todo_date` datetime NOT NULL DEFAULT '1990-01-01 00:00:00',
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'pedding',
  `priority` int(11) NOT NULL DEFAULT 5,
  `tag` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `owner_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_201808051002`
--

--
-- Table structure for table `dm_usr_todoes_change_history`
--

DROP TABLE IF EXISTS `dm_usr_todoes_change_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_change_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_change_history`
--

LOCK TABLES `dm_usr_todoes_change_history` WRITE;
/*!40000 ALTER TABLE `dm_usr_todoes_change_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_todoes_change_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_todoes_dm_knw_url_collect`
--

DROP TABLE IF EXISTS `dm_usr_todoes_dm_knw_url_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_dm_knw_url_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_todoes` int(11) NOT NULL,
  `id_url_collect` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_dm_knw_url_collect`
--

LOCK TABLES `dm_usr_todoes_dm_knw_url_collect` WRITE;
/*!40000 ALTER TABLE `dm_usr_todoes_dm_knw_url_collect` DISABLE KEYS */;
INSERT INTO `dm_usr_todoes_dm_knw_url_collect` VALUES (1,98,2,'2018-09-06 10:48:48','2018-09-06 10:48:48.870040');
/*!40000 ALTER TABLE `dm_usr_todoes_dm_knw_url_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_todoes_do_record`
--

DROP TABLE IF EXISTS `dm_usr_todoes_do_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_do_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `todoesid` int(11) NOT NULL,
  `pre_status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `current_status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_time` datetime NOT NULL DEFAULT current_timestamp(),
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_do_record`
--


--
-- Table structure for table `dm_usr_todoes_do_record_201808052158`
--

DROP TABLE IF EXISTS `dm_usr_todoes_do_record_201808052158`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_do_record_201808052158` (
  `id` int(11) NOT NULL DEFAULT 0,
  `todoesid` int(11) NOT NULL,
  `pre_status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `current_status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_time` datetime NOT NULL DEFAULT current_timestamp(),
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_do_record_201808052158`
--


--
-- Table structure for table `dm_usr_todoes_records_of_report_to`
--

DROP TABLE IF EXISTS `dm_usr_todoes_records_of_report_to`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_records_of_report_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '这是todo的汇报记录表，汇报一个todo就多一条数据',
  `todoes_id` int(11) NOT NULL COMMENT '要汇报的todo的id',
  `to_user_id` int(11) NOT NULL COMMENT '汇报的上级对象',
  `report_content` varchar(4000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '汇报的内容采取json格式，将汇报当时完整的todo和todoes_do_record记录到这个字段',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_records_of_report_to`
--

LOCK TABLES `dm_usr_todoes_records_of_report_to` WRITE;
/*!40000 ALTER TABLE `dm_usr_todoes_records_of_report_to` DISABLE KEYS */;
/*!40000 ALTER TABLE `dm_usr_todoes_records_of_report_to` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_todoes_status_enum`
--

DROP TABLE IF EXISTS `dm_usr_todoes_status_enum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_todoes_status_enum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_todoes_status_enum`
--

LOCK TABLES `dm_usr_todoes_status_enum` WRITE;
/*!40000 ALTER TABLE `dm_usr_todoes_status_enum` DISABLE KEYS */;
INSERT INTO `dm_usr_todoes_status_enum` VALUES (1,'pending','','2018-07-30 14:25:38','2018-08-05 14:04:44.173718'),(2,'working','','2018-07-30 14:25:38','2018-07-30 14:25:38.383036'),(3,'done','','2018-07-30 14:25:38','2018-07-30 14:25:38.384455');
/*!40000 ALTER TABLE `dm_usr_todoes_status_enum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm_usr_users`
--

DROP TABLE IF EXISTS `dm_usr_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dm_usr_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `born_date` date NOT NULL DEFAULT '1800-01-01' COMMENT '出生日期',
  `source` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '通行证注册' COMMENT '用户来源，在自有平台注册的称为“通行证注册”，因为是自己玩的系统，所以有很多测试，通过数据产生器产生的称为“generator”',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30705 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_usr_users`
--

--
-- Table structure for table `enum_common_audit_state`
--

DROP TABLE IF EXISTS `enum_common_audit_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enum_common_audit_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `audit_state` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enum_common_audit_state`
--

LOCK TABLES `enum_common_audit_state` WRITE;
/*!40000 ALTER TABLE `enum_common_audit_state` DISABLE KEYS */;
INSERT INTO `enum_common_audit_state` VALUES (1,'waiting','2018-08-01 11:05:59','2018-08-01 11:05:59.573048'),(2,'auditing','2018-08-01 11:07:17','2018-08-01 11:07:17.941026'),(3,'audited','2018-08-01 11:07:17','2018-08-01 11:07:17.942069');
/*!40000 ALTER TABLE `enum_common_audit_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enum_common_period_limit`
--

DROP TABLE IF EXISTS `enum_common_period_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enum_common_period_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `limit_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enum_common_period_limit`
--

LOCK TABLES `enum_common_period_limit` WRITE;
/*!40000 ALTER TABLE `enum_common_period_limit` DISABLE KEYS */;
INSERT INTO `enum_common_period_limit` VALUES (1,'InTheDay','','2018-08-05 14:22:54','2018-08-05 14:27:24.749107'),(2,'BetweenSprint','','2018-08-05 14:22:54','2018-08-05 14:27:49.504984'),(3,'LongTerm','','2018-08-05 14:22:54','2018-08-05 14:24:25.728281');
/*!40000 ALTER TABLE `enum_common_period_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glossary`
--

DROP TABLE IF EXISTS `glossary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glossary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `id_it_system` int(11) NOT NULL,
  `id_prj_project` int(11) NOT NULL,
  `urls` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `is_deprecate` int(11) NOT NULL DEFAULT 0 COMMENT '是否过时（0：为过时，1:过时的）',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glossary`
--

LOCK TABLES `glossary` WRITE;
/*!40000 ALTER TABLE `glossary` DISABLE KEYS */;
INSERT INTO `glossary` VALUES (1,'dm','domain','系统',0,2,'',0,'2018-08-07 03:39:14','2018-08-24 07:24:09.536853'),(2,'knw','knowledge','系统',0,2,'',0,'2018-08-07 03:39:31','2018-08-24 07:24:09.536853'),(3,'usr','user','系统',0,2,'',0,'2018-08-07 03:39:39','2018-08-24 07:24:09.536853'),(4,'dm_knw','知识中心领域','系统',0,2,'',0,'2018-08-07 03:41:09','2018-08-24 07:24:09.536853'),(5,'dm_usr','用户中心领域','系统',0,2,'',0,'2018-08-07 03:41:09','2018-08-24 07:24:09.536853'),(6,'dm_prj','项目管理领域','系统',0,2,'',0,'2018-08-07 03:42:23','2018-08-24 07:24:09.536853'),(7,'prj','project','系统',0,2,'',0,'2018-08-07 03:42:34','2018-08-24 07:24:09.536853'),(8,'dsgn','design','系统',0,2,'',0,'2018-08-07 03:45:29','2018-08-24 07:24:09.536853'),(9,'dm_dsgn','设计中心领域','系统',0,2,'',0,'2018-08-07 03:45:29','2018-08-24 07:24:09.536853'),(10,'tm','time','系统',0,2,'',0,'2018-08-07 03:46:57','2018-08-24 07:24:09.536853'),(11,'dm_tm','时间管理领域','系统',0,2,'',0,'2018-08-07 03:46:57','2018-08-24 07:24:09.536853'),(12,'rd','research and development','系统',0,2,'',0,'2018-08-07 15:53:17','2018-08-24 07:24:09.536853'),(13,'dm_rd','研发管理领域','系统',0,2,'',0,'2018-08-07 15:53:17','2018-08-24 07:24:09.536853'),(14,'prdct','product','系统',0,2,'',0,'2018-08-09 10:33:29','2018-08-24 07:24:09.536853'),(15,'dm_prdct','产品管理领域','系统',0,2,'',0,'2018-08-09 10:33:29','2018-08-24 07:24:09.536853'),(16,'it','it技术','系统',0,2,'',0,'2018-08-09 10:40:25','2018-08-24 07:24:09.536853'),(17,'dm_it','it技术管理领域','系统',0,2,'',0,'2018-08-09 10:40:44','2018-08-24 07:24:09.536853'),(18,'fnt','film and television','系统',0,2,'',1,'2018-08-14 05:08:27','2018-08-24 07:24:09.536853'),(19,'dm_fnt','影视领域','系统',0,2,'',1,'2018-08-14 05:08:27','2018-08-24 07:24:09.536853'),(20,'_deal_with','对于dm_fnt_teleplay信息的处置方式枚举，从此定义一个规则，就是todo是最简单原始的代办列表，但是也有很多代办事项来源于某些信息本身，也就是说todo只是对某些领域的某些事件做一些处置，这时可定义一套固定模式：任何信息都可以有带有_deal_with结尾的信息结构，这种结构代表了这些信息需要被处置的种类描述。信息本身id加上deal_with的方式，记录到todo或者issue列表，则形成待办事项。','系统',0,2,'',0,'2018-08-14 05:15:01','2018-08-24 07:24:09.536853'),(21,'_surcd','source code','系统',0,2,'',0,'2018-08-16 10:51:29','2018-08-24 07:24:09.536853'),(22,'dm_surcscd','源代码管理领域','系统',0,2,'',0,'2018-08-16 10:51:29','2018-08-24 07:24:09.536853'),(23,'5M1E','人（Man/Manpower）、机器（Machine）、材料（Material）、方法（Method）、测量（Measurement）、环境（Environment）。造成产品质量的波动的原因主要有6个因素的总称\na) 人（Man/Manpower）： 操作者对质量的认识、技术熟练程度、身体状况等；\nb) 机器（Machine）： 机器设备、工夹具的精度和维护保养状况等；\nc) 材料（Material）： 材料的成分、物理性能和化学性能等；\nd) 方法（Method）： 这里包括加工工艺、工装选择、操作规程等；\ne)测量（Measurement）：测量时采取的方法是否标准、正确；\nf) 环境（Environment） 工作地的温度、湿度、照明和清洁条件等；','工厂生产',1,1,'https://baike.baidu.com/item/5M1E分析法/4791234',0,'2018-08-21 08:25:32','2018-08-24 07:24:37.905311'),(24,'SPC','统计过程控制（Statistical Process Control）是一种借助数理统计方法的过程控制工具。它对生产过程进行分析评价，根据反馈信息及时发现系统性因素出现的征兆，并采取措施消除其影响，使过程维持在仅受随机性因素影响的受控状态，以达到控制质量的目的。\nSPC控制图是由美国贝尔实验室的休哈特（Walter Shewhart）博士于1924年首先提出的。\n他有三句名言勾画出了SPC控制图的基本原理：\n1.”在一切质量过程中所呈现出的变异有两个分量。第一个分量是过程内部引起的稳定分量，即正常变异；第二个分量是可查明原因的间断变异，即异常变异。“\n2.“那些可查明原因的变异可用有效方法加以发现，并可被剔去，但正常变异不会消失，除非改变基本过程。”\n3.“基于3σ限的SPC控制图可以把正常变异与异常变异区分开来”','工厂生产',1,1,'https://baike.baidu.com/item/SPC/722?fr=aladdin',0,'2018-08-21 08:28:57','2018-08-24 08:37:39.297562'),(25,'_dcsmk','decision-making','系统',0,2,'',0,'2018-08-21 09:54:59','2018-08-24 07:24:09.536853'),(26,'dm_dcsmk','决策领域','系统',0,2,'',0,'2018-08-21 09:54:59','2018-08-24 07:24:09.536853'),(27,'_comm','common','系统',0,2,'',0,'2018-08-21 10:07:36','2018-08-24 07:24:09.536853'),(28,'dm_comm','通用领域','系统',0,2,'',0,'2018-08-21 10:07:36','2018-08-24 07:24:09.536853'),(29,'pid','people id,一般作为前缀，比如pid_participator参与人id','系统',0,2,'',0,'2018-08-21 10:25:09','2018-08-24 07:24:09.536853'),(30,'lid','login id，一般作为前缀，比如lid_participator参与的登录账号id','系统',0,2,'',0,'2018-08-21 10:25:09','2018-08-24 07:24:09.536853'),(45,'Walter Shewhart','SPC控制图是由美国贝尔实验室的休哈特（Walter Shewhart）博士于1924年首先提出的。\n他有三句名言勾画出了SPC控制图的基本原理：\n1.”在一切质量过程中所呈现出的变异有两个分量。第一个分量是过程内部引起的稳定分量，即正常变异；第二个分量是可查明原因的间断变异，即异常变异。“\n2.“那些可查明原因的变异可用有效方法加以发现，并可被剔去，但正常变异不会消失，除非改变基本过程。”\n3.“基于3σ限的SPC控制图可以把正常变异与异常变异区分开来”','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.291175'),(46,'SQC','统计质量控制','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.292564'),(47,'3σ原理','即三倍的标准差作为控制线。休哈特建议用界限μ±3σ作为控制界限来管理过程。这意味着：在1000个产品中若有不超过2.7个不合格品出，就认为该过程的变异属正常变异；若有更多的不合格品出现，就认为该过程的变异属异常变异。3σ是指对应统计原理中的μ±3σ，其中μ为正态均值，σ为标准差。可以参照正态分布Ν(μ,σ²)的概率特性表','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-27 03:30:50.833869'),(48,'过程','质量管理的所有工作是通过过程来完成的。所以过程是提高质量的基层单位。\nΙSO 9000认为过程是将各项输入资源（人、设备、材料、方法、环境）按一定要求组合起来，并能转化为输出（中间）产品及其质量特性的活动。一道工序、一个工段、一项操作等都可看作一个过程。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.293913'),(49,'反馈系统','带有反馈系统的过程称为过程控制系统。反馈系统有三项功能，他们是：\n（1）收集信息，对资源组合进行观察并对（中间）产品测量其质量特性。\n（2）统计加工，对收集的信息用各种统计工具结合工程经验进行加工。判断过程是否正常，从中发现问题。\n（3）采取行动，对过程中的一些不正常现象采取纠正措施。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.294475'),(50,'变异','产品间的差异是由于质量特性变异而引起的。质量特性的变异是随机的，它时隐时现，时大时小，时正时负。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.295044'),(51,'概率密度曲线和分布','在二维坐标系中，我们大量随机的取产品的质量特性Χ作为横轴，为了图形得以稳定，我们把单位长度上X出现的频率作为Υ轴。由于频率的稳定性，被测质量特性Χ越多，，这个图形越稳定，其外形呈现出一条光滑的曲线，称为概率密度曲线，相应的概率表达式f(X)称为概率密度函数（又简称为分布），它就是隐藏在质量特性Χ随机取值后面的统计规律性。\n','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.295516'),(52,'过程受控（Ιn Control）','理论和实践证明：在正常变异出现时，个别测量值总是不同的，但一组测量值总呈现正太分布。这个分布不会随时间而变，从而是可以预测的。这时称该过程受控（Ιn Control）.','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.296126'),(53,'过程失控（Out of Control）','理论和实践证明：在异常变异出现时，较强的变异源（一个或几个）时隐时现，随时在改变过程输出的分量。过程输出的分布是不稳定的，也是不可预测的。这时称该过程失控（Out of Control）。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:37:39.296608'),(54,'正常变异（Common Variation）','一个过程的变异若具有以下特征，则称为正常变异（Common Variation）：\n（1）过程内有许多变异源。\n（2）每个变异源的出现是随机的。\n（3）每个变异源对质量特性（过程的输出）的影响都是很微小的，不易识别，但其总和是可度量的，呈正太分布。\n正常变异和异常变异源的差别仅在于是否在过程中有一个或几个较强的变异源出现，这可以从过程输出分布是否连续出现正态分布来区分。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:40:24.878435'),(55,'异常变异（Special Variation）','一个过程的变异若具有以下特征，则称为异常变异（Special Variation）： \n（1）过程内有许多变异源。 \n（2）每个变异源的出现是随机的。 \n（3）有一个（或几个）变异源对质量特性（过程输出）的影响较强，其他均很小。其总和所呈现的分布是随时间而变的。\n正常变异和异常变异源的差别仅在于是否在过程中有一个或几个较强的变异源出现，这可以从过程输出分布是否连续出现正态分布来区分。','工厂生产',1,1,'',0,'2018-08-24 08:37:39','2018-08-24 08:40:24.880669'),(56,'标准差（Standard Deviation）','通常用σ表示','工厂生产',1,1,'',0,'2018-08-27 02:00:44','2018-08-27 03:30:28.598834'),(57,'中心极限定理','中心极限定理：设X1,X2,...Xn为从某总体抽取的样本，其总体分布未知，但其均值μ和方差σ²都存在，则如下结论：当总体为正态分布时，样本均值`X精确服从正态分布Ν(μ,σ²/n)，当总体为非正太分布时，样本均值`Χ近似服从正态分布Ν(μ,σ²/n)，且样本量n越大，近似程度越好。\n这个定理表明：无论总体是什么分布（离散分布或连续分布，正态分布或非正态分布），`X总是呈现正态分布或近似呈现正态分布。这说明平均值运算可以从非正态分布获得正态分布。','工厂生产',1,1,'',0,'2018-08-27 03:28:44','2018-08-27 03:28:44.354419'),(58,'方差','通常用σ²表示','工厂生产',1,1,'',0,'2018-08-27 03:30:28','2018-08-27 03:30:28.599897'),(59,'第一类错误/虚发警报','当所涉及的过程处于受控状态时，但某点由于偶然原因落在控制线之外，这时按照规则判断过程失败。这个判断是错误的，这种错误称为第一类错误，其发生的概率通常记为α。第一类错误又称为虚发警报，虚发警报会引起白费功夫去寻找根本不存在的异常变异而造成损失。','工厂生产',1,1,'',0,'2018-08-27 03:57:00','2018-08-27 03:57:00.416143'),(60,'第二类错误/漏发警报','当所涉及的过程失控时，其产生的点由于偶然原因会落在控制限内，这时按规则判断过程受控。这个判断也是错误的，这类错误称为第二类错误，其发生概率通常记为β。第二类错误又称为漏发警报，这时由于未检测出不合格品而造成损失。','工厂生产',1,1,'',0,'2018-08-27 03:57:00','2018-08-27 03:57:00.417269'),(61,'SPC过程控制的四种状态','这四种状态记为Ⅰ、Ⅱ、Ⅲ、Ⅳ。状态Ⅰ：技术满足（In Spec）（符合规格）过程受控（In Control）（稳定）；状态Ⅱ：技术不满足（Out Spec）（超规格）过程受控（In Control）（稳定）；状态Ⅲ：技术满足（In Spec）（符合规格）过程受控（In Control）（稳定）过程不受控（Out Control）（不稳定）；状态Ⅳ：技术不满足,过程不受控','工厂生产',1,1,'',0,'2018-08-27 04:03:07','2018-08-27 04:03:07.681730'),(62,'中心线（Control Line,CL）','对应均值μ','工厂生产',1,1,'',0,'2018-08-27 04:49:17','2018-08-27 04:49:17.675982'),(63,'上控制限（Upper Control Limit,UCL）','对应μ+3σ','工厂生产',1,1,'',0,'2018-08-27 04:49:17','2018-08-27 04:49:17.677093'),(64,'下控制线（Lower Control Limit,LCL）','对应μ-3σ','工厂生产',1,1,'',0,'2018-08-27 04:49:17','2018-08-27 04:49:17.677975'),(65,'样本的大小','继休哈特之后（休哈特实验，即矩形三角形实验），很多专家做了类似的实验，发现无论总体服从什么样的分布，只要样本是n>=5，实验次数足够多，样本`Χ（样本均值）的分布总是趋于正态分布。所以一般样本大小在5到10个之间。但是如果两类错误都很重要，不能轻视，这时可考虑增加每次取样的样本量n，如n=10、15甚至20。','工厂生产',1,1,'',0,'2018-08-27 05:52:44','2018-08-29 03:02:19.717328'),(66,'3σ SPC控制图','休哈特建议用界限μ±3σ作为控制界限来管理过程。这意味着：在1000个产品中若有不超过2.7个不合格品出，就认为该过程的变异属正常变异；若有更多的不合格品出现，就认为该过程的变异属异常变异。3σ是指对应统计原理中的μ±3σ，其中μ为正态均值，σ为标准差。\n为了方便在生产现场使用和及时记录质量变异情况，休哈特还建议把正态分布图及其控制限μ±3σ同时转90度并以横轴为时间或编号，以纵轴为过程参数（均值、标准差），并在μ±3σ处引出两条水平线（用虚线表示），这样就形成一张SPC控制图。图上三条水平线各有一个名称：中心线（CL）对应μ、上控制限（UCL）对应μ+3σ、下控制限（LCL）对应μ-3σ。\n在现场使用时，先规定一个时间间隔（如1小时、2小时等），然后按时抽取一个样本，测量样本中每个样品的质量特性，计算其平均值。最后把计算结果点在SPC控制图上，如此不断重复，累计到一定数量后就可以对过程有无异常变异做出判断。若无异常变异，可认为过程受控；若有异常变异，则认为过程失控；这时要查找原因，采取适当行动，及时纠正，使过程恢复受控状态。\n','工厂生产',1,1,'',0,'2018-08-27 06:26:48','2018-08-27 06:26:48.451812'),(67,'三条判稳原则','','工厂生产',1,1,'',0,'2018-08-27 06:32:16','2018-08-27 06:32:26.195960'),(68,'八条判异原则','','工厂生产',1,1,'',0,'2018-08-27 06:32:16','2018-08-27 06:32:26.196881'),(69,'SPC控制图判读原则','','工厂生产',1,1,'',0,'2018-08-27 06:32:16','2018-08-27 06:32:26.197704'),(70,'SPC抽样原则（合理子组原则）','SPC抽样原则，一般采用合理子组抽样原则。用公式表示是：SS(T)=SS(W)+SS(B)，即：总变差=组内变差+组间变差。 合理子组原则是休哈特提出的控制图的理论基础之一。它是指在抽取样本时要使：（1）组内波动仅由正常原因引起的；（2）组间波动仅由异常原因引起的（加入有异常波动的话）。 休哈特称这样得到的样本为“子组”。由于子组内无异常活动，故用其作为标准差可得到较为精确的估计，数值一般较小。当σ较小时，如±3σ，控制限的宽度也不大，从而对检出异常波动就较为灵敏。\n为了实现“合理子组原则”，一个最简单的方法是在短时间内把一个子组全部抽取，或者对连续生产产品进行“块抽样”。由于抽样时间短，就可避免异常因素进入子组。','工厂生产',1,1,'',0,'2018-08-28 05:03:46','2018-08-28 05:03:46.925729'),(71,'计量SPC控制图','计量SPC控制图共有四对：（1）均值——极差控制图（`X - R 图）：精度尚可，使用方便，n=2 ~ 9。 （2）均值——标准差控制图（`X - S 图）：精度高，计算量大，n>=2。 （3）中位数——极差控制图（Me - R图）：精度较差，计算量小，n = 2 ~ 9。 （4）单值——移动极差控制图（X - MR图）：不得已时使用， n = 1。 计量SPC控制图的背景时正态分布N(μ,σ²)，它含有两个独立参数：均值μ与标准差σ。因此要控制计量值的变异需要两张SPC控制图：一张用于控制μ，另一张用于控制σ。根据样本量的大小和用于估计μ与σ的统计量的不同，计量SPC控制图共有四对。','工厂生产',1,1,'',0,'2018-08-28 05:11:06','2018-08-28 05:16:32.334150'),(72,'计数SPC控制图','','工厂生产',1,1,'',0,'2018-08-28 05:16:32','2018-08-28 05:16:32.333030'),(73,'推荐均值——极差控制图的样本数据量','子组容量n：一般以4～5个为宜；子组个数K：一般以20～25个为宜；子组间隔：没有统一规定。譬如每个小时生产10个以下产品，子组间隔可定为8小时；若每小时产量在10～19个之内，子组间隔可定为4小时；若每小时产量在20～49个之内，子组间隔可定为2小时；若每小时产量在50个以上，子组间隔可定为1小时。','工厂生产',1,1,'',0,'2018-08-28 07:14:05','2018-08-28 07:14:05.887660'),(74,'推荐均值——标准差控制图的样本数据量','子组容量：n>=10，一般取10，子组个数K：以20～25个为宜。','工厂生产',1,1,'',0,'2018-08-28 07:18:55','2018-08-28 07:18:55.537388'),(75,'BD','Business Developement . 业务开发。','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.112657'),(76,'ICFS','内部文件存储（Intranet Cloud File Storage）提供了可扩展的共享文件存储服务','系统',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.113603'),(77,'protograph','原型图','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.114530'),(78,'RD','R&D Research and development研发部门','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.115417'),(79,'标准UI','标准UI是我司开发的一套针对工业企业信息系统应用的标准人机交互界面元素。它包含标准页面、标准区块、标准控件等。其中标准页面目前为A|B系列。','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.116190'),(80,'标准模板页面','是指使用元数据,根据元数据描述生成的应用了标准页面的页面.','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.116928'),(81,'标准页面','标准UI中的页面,是指页面效果或者静态页面','常规',0,2,'',0,'2018-09-04 06:41:57','2018-09-04 06:41:57.117616'),(82,'T','技术要求规格中线','工厂生产',1,1,'',0,'2018-09-10 05:45:30','2018-09-10 05:45:30.942403'),(83,'LSL','技术要求(客户要求)规格下限','工厂生产',1,1,'',0,'2018-09-10 05:45:30','2018-09-10 06:15:50.907234'),(84,'USL','技术要求（客户要求）规格上限','工厂生产',1,1,'',0,'2018-09-10 05:45:30','2018-09-10 06:15:50.908401');
/*!40000 ALTER TABLE `glossary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glossary_changelog`
--

DROP TABLE IF EXISTS `glossary_changelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glossary_changelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pre_changelog` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `current` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glossary_changelog`
--

LOCK TABLES `glossary_changelog` WRITE;
/*!40000 ALTER TABLE `glossary_changelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `glossary_changelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `last_timestamp` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (3,'金飞','2018-08-21 10:23:13','2018-08-21 10:23:13.341712'),(4,'赵赛赛','2018-08-21 10:43:16','2018-08-21 10:43:16.283123');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pconnect'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `event_generator_user` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8 */ ;;
/*!50003 SET character_set_results = utf8 */ ;;
/*!50003 SET collation_connection  = utf8_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`%`*/ /*!50106 EVENT `event_generator_user` ON SCHEDULE EVERY 5 SECOND STARTS '2018-08-09 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO begin
	start transaction;
	set @timenow=now();
    set @maxid = (select max(id) from dm_usr_users);
    set @firstname = concat('飞', @maxid) ;
    set @lastname = concat('金',@maxid);
    set @year = round(1955 + rand()*1000000000%60);
    set @month = round(rand()*1000000000%13);
    set @day = round(rand()*1000000000%28);
    set @born_date = concat(@year,'-',@month,'-',@day);
    insert into dm_usr_users (first_name, last_name, born_date, source)  values(@firstname,@lastname,@born_date,'generator');
	commit;
end */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'pconnect'
--
/*!50003 DROP PROCEDURE IF EXISTS `proc_setupdata_dm_usr_study_record_groupby_day` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `proc_setupdata_dm_usr_study_record_groupby_day`()
BEGIN
SET SQL_SAFE_UPDATES = 0;
DELETE from dm_usr_study_record_groupby_day;

INSERT INTO dm_usr_study_record_groupby_day (id_user,study_duration,study_duration_unit,occur_day)
SELECT id_user,sum(study_duration) study_duration,study_duration_unit,DATE_FORMAT(start_time,'%y-%m-%d') occur_day
FROM dm_usr_study_record
group by id_user,study_duration_unit,occur_day;
SET SQL_SAFE_UPDATES = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_update_dm_usr_users_born_date` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `proc_update_dm_usr_users_born_date`()
BEGIN
declare year int;
declare month int;
declare day int;
declare born_date varchar(50);
declare start_id int default 1;
set start_id = 1;

WHILE (start_id < 1300)
do
set year = round(1955 + rand()*1000000000%60);
set month = round(rand()*1000000000%11)+1;
set day = round(rand()*1000000000%27)+1;
set born_date = concat(year,'-',month,'-',day);
UPDATE dm_usr_users set born_date = born_date WHERE id = start_id;
set start_id = start_id + 1;
end while;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-12 18:11:58

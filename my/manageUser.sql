-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 192.168.84.99    Database: zifuji
-- ------------------------------------------------------
-- Server version	5.7.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `z_manage_menu`
--

DROP TABLE IF EXISTS `z_manage_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_menu` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `parent_id` bigint(20) NOT NULL,
  `label` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `path` varchar(45) NOT NULL,
  `component` varchar(45) NOT NULL,
  `show_flag` bit(1) NOT NULL,
  `icon` varchar(45) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='z_manage_menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_permission`
--

DROP TABLE IF EXISTS `z_manage_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_permission` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `per_name` varchar(45) NOT NULL,
  `code_sys` varchar(45) NOT NULL,
  `code_module` varchar(45) NOT NULL,
  `code_method` varchar(45) NOT NULL,
  `per_description` varchar(45) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='z_manage_permission';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_role`
--

DROP TABLE IF EXISTS `z_manage_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_role` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `role_code` varchar(45) NOT NULL COMMENT '角色编码',
  `role_name` varchar(45) NOT NULL COMMENT '角色名称',
  `role_description` varchar(100) NOT NULL COMMENT '描述',
  PRIMARY KEY (`table_id`),
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='这是';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_role_menu`
--

DROP TABLE IF EXISTS `z_manage_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_role_menu` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='z_manage_role_menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_role_permission`
--

DROP TABLE IF EXISTS `z_manage_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_role_permission` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `role_id` varchar(45) NOT NULL,
  `permission_id` varchar(45) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='z_manage_role_permission';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_user`
--

DROP TABLE IF EXISTS `z_manage_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_user` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `user_name` varchar(45) NOT NULL COMMENT '账户',
  `pass_word` varchar(100) NOT NULL COMMENT '密码',
  `short_name` varchar(45) NOT NULL COMMENT '昵称',
  PRIMARY KEY (`table_id`),
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='这是demo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `z_manage_user_role`
--

DROP TABLE IF EXISTS `z_manage_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `z_manage_user_role` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `role_id` varchar(45) NOT NULL COMMENT 'role_id',
  PRIMARY KEY (`table_id`),
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='z_manage_user_role';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-14  9:38:55

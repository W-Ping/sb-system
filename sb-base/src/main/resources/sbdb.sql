/*
Navicat MySQL Data Transfer

Source Server         : vm_dev
Source Server Version : 50721
Source Host           : 192.168.16.16:3306
Source Database       : sbdb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-23 18:20:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for budget_info
-- ----------------------------
DROP TABLE IF EXISTS `budget_info`;
CREATE TABLE `budget_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `budget_name` varchar(100) DEFAULT NULL,
  `budget_code` varchar(100) NOT NULL,
  `mobile_phone` varchar(100) NOT NULL,
  `min_budget_amount` decimal(8,2) DEFAULT '0.00' COMMENT '最小预算金额',
  `max_budget_amount` decimal(8,2) DEFAULT '0.00' COMMENT '最大预算金额',
  `min_cost` decimal(8,2) DEFAULT '0.00' COMMENT '最小成本',
  `max_cost` decimal(8,2) DEFAULT '0.00' COMMENT '最大成本',
  `actual_cost` decimal(8,2) DEFAULT '0.00' COMMENT '实际成本',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='预算表';

-- ----------------------------
-- Table structure for house_budget_info
-- ----------------------------
DROP TABLE IF EXISTS `house_budget_info`;
CREATE TABLE `house_budget_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_code` varchar(100) NOT NULL,
  `house_detail_code` varchar(100) NOT NULL,
  `budget_code` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房屋装修预算表';

-- ----------------------------
-- Table structure for house_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `house_detail_info`;
CREATE TABLE `house_detail_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `house_code` varchar(100) NOT NULL,
  `house_detail_code` varchar(100) NOT NULL,
  `house_detail_name` varchar(100) DEFAULT NULL,
  `room_type` int(11) DEFAULT NULL COMMENT '房间类型【0：卧室，1：客厅，2：厨房，3：卫生间，4：阳台】',
  `floor_area_size` decimal(8,2) DEFAULT NULL COMMENT '顶面积',
  `wall_area_size` decimal(8,2) DEFAULT NULL COMMENT '墙面积',
  `ceil_area_size` decimal(8,2) DEFAULT NULL COMMENT '地板面积',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='房屋信息明细表';

-- ----------------------------
-- Table structure for house_info
-- ----------------------------
DROP TABLE IF EXISTS `house_info`;
CREATE TABLE `house_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `house_code` varchar(255) DEFAULT NULL,
  `house_area_size` decimal(8,2) DEFAULT NULL COMMENT '房屋面积',
  `house_budget_amount` decimal(8,2) DEFAULT NULL COMMENT '装修合计预算',
  `mobile_phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `house_type` int(11) NOT NULL DEFAULT '0' COMMENT '类型【0：新房，1：旧房子翻新】',
  `bedroom` int(11) DEFAULT '0' COMMENT '卧室',
  `livingroom` int(11) DEFAULT '0' COMMENT '客厅',
  `kitchen` int(11) DEFAULT '0' COMMENT '厨房',
  `toilet` int(11) DEFAULT '0' COMMENT '卫生间',
  `balcony` int(11) DEFAULT '0' COMMENT '阳台',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`mobile_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='房屋信息表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `user_code` varchar(100) DEFAULT NULL,
  `mobile_phone` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for worker_node
-- ----------------------------
DROP TABLE IF EXISTS `worker_node`;
CREATE TABLE `worker_node` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `HOST_NAME` varchar(64) NOT NULL COMMENT 'host name',
  `PORT` varchar(64) NOT NULL COMMENT 'port',
  `TYPE` int(11) NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
  `LAUNCH_DATE` date NOT NULL COMMENT 'launch date',
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modified time',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created time',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='DB WorkerID Assigner for UID Generator';



/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : 127.0.0.1:3306
Source Database       : sbdb

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-09 17:55:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for search_key_info
-- ----------------------------
DROP TABLE IF EXISTS `search_key_info`;
CREATE TABLE `search_key_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile_phone` varchar(100)  NOT NULL,
  `group_code` varchar(100)  NOT NULL,
  `code` varchar(100)  NOT NULL,
  `search_key` varchar(100)  NOT NULL,
  `search_name` varchar(100)  NOT NULL,
  `order_num` int(11) NOT NULL DEFAULT '1' COMMENT '排列顺序',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态【0：有效；1：无效】',
  `remark` varchar(255)  DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of search_key_info
-- ----------------------------
INSERT INTO `search_key_info` VALUES ('1', '13012881773', 'SBU001', 'classifyCode', 'HCF608377990409093123', '瓷砖', '1', '0', null, '0', '2019-08-09 13:57:32', '2019-08-09 13:56:33');
INSERT INTO `search_key_info` VALUES ('2', '13012881773', 'SBU001', 'classifyCode', 'HCF608429682433458185', '门面', '2', '0', null, '0', '2019-08-09 13:58:35', '2019-08-09 13:58:20');


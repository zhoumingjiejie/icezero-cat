/*
Navicat MySQL Data Transfer

Source Server         : 本地工厂
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : universal_test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-04-19 15:34:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for leaf_alloc
-- ----------------------------
DROP TABLE IF EXISTS `leaf_alloc`;
CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `max_id` bigint(20) NOT NULL DEFAULT '1',
  `step` int(11) NOT NULL,
  `description` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of leaf_alloc
-- ----------------------------
INSERT INTO `leaf_alloc` VALUES ('leaf-segment-test', '1', '2000', 'Test leaf Segment Mode Get Id', '2020-04-19 15:20:33');

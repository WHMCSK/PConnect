/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : pconnect-auth

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-11-30 13:38:27
*/
-- CREATE SCHEMA IF NOT EXISTS `pconnect_auth` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
--
-- USE pconnect_auth;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_id` int(11) DEFAULT NULL,
  `authority_type` varchar(255) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3089 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('2868', '1', 'group', '13', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2869', '1', 'group', '5', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2870', '1', 'group', '1', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2871', '1', 'group', '6', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2872', '1', 'group', '7', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2873', '1', 'group', '8', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2874', '1', 'group', '21', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2875', '1', 'group', '14', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2876', '1', 'group', '33', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2877', '1', 'group', '34', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2878', '1', 'group', '35', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('2879', '1', 'group', '3', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2880', '1', 'group', '4', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2881', '1', 'group', '5', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2882', '1', 'group', '23', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2883', '1', 'group', '10', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2884', '1', 'group', '11', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2885', '1', 'group', '12', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2886', '1', 'group', '13', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2887', '1', 'group', '14', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2888', '1', 'group', '15', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2889', '1', 'group', '24', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2890', '1', 'group', '27', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2891', '1', 'group', '16', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2892', '1', 'group', '17', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2893', '1', 'group', '18', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2894', '1', 'group', '19', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2895', '1', 'group', '20', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2896', '1', 'group', '21', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2897', '1', 'group', '22', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2898', '1', 'group', '28', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2899', '1', 'group', '32', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2900', '1', 'group', '33', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2901', '1', 'group', '34', 'element', null, null);
INSERT INTO `t_authority` VALUES ('2902', '1', 'group', '35', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3009', '2', 'group', '13', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3010', '2', 'group', '5', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3011', '2', 'group', '1', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3012', '2', 'group', '6', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3013', '2', 'group', '7', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3014', '2', 'group', '8', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3015', '2', 'group', '21', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3016', '2', 'group', '36', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3017', '2', 'group', '14', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3018', '2', 'group', '33', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3019', '2', 'group', '4', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3020', '2', 'group', '5', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3021', '2', 'group', '3', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3022', '2', 'group', '23', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3023', '2', 'group', '10', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3024', '2', 'group', '11', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3025', '2', 'group', '12', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3026', '2', 'group', '13', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3027', '2', 'group', '14', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3028', '2', 'group', '15', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3029', '2', 'group', '24', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3030', '2', 'group', '27', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3031', '2', 'group', '20', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3032', '2', 'group', '28', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3033', '2', 'group', '16', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3034', '2', 'group', '17', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3035', '2', 'group', '18', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3036', '2', 'group', '19', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3037', '2', 'group', '21', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3038', '2', 'group', '22', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3039', '2', 'group', '32', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3040', '2', 'group', '33', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3041', '2', 'group', '34', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3042', '2', 'group', '35', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3043', '2', 'group', '36', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3044', '2', 'group', '37', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3045', '2', 'group', '38', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3046', '2', 'group', '39', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3047', '2', 'group', '40', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3048', '9', 'group', '13', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3049', '9', 'group', '5', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3050', '9', 'group', '1', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3051', '9', 'group', '6', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3052', '9', 'group', '7', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3053', '9', 'group', '8', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3054', '9', 'group', '21', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3055', '9', 'group', '36', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3056', '9', 'group', '14', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3057', '9', 'group', '33', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3058', '9', 'group', '34', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3059', '9', 'group', '35', 'menu', null, null);
INSERT INTO `t_authority` VALUES ('3060', '9', 'group', '3', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3061', '9', 'group', '4', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3062', '9', 'group', '5', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3063', '9', 'group', '23', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3064', '9', 'group', '10', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3065', '9', 'group', '11', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3066', '9', 'group', '12', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3067', '9', 'group', '13', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3068', '9', 'group', '14', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3069', '9', 'group', '15', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3070', '9', 'group', '24', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3071', '9', 'group', '27', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3072', '9', 'group', '16', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3073', '9', 'group', '17', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3074', '9', 'group', '18', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3075', '9', 'group', '19', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3076', '9', 'group', '20', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3077', '9', 'group', '21', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3078', '9', 'group', '22', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3079', '9', 'group', '28', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3080', '9', 'group', '32', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3081', '9', 'group', '33', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3082', '9', 'group', '34', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3083', '9', 'group', '35', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3084', '9', 'group', '36', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3085', '9', 'group', '37', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3086', '9', 'group', '38', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3087', '9', 'group', '39', 'element', null, null);
INSERT INTO `t_authority` VALUES ('3088', '9', 'group', '40', 'element', null, null);

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', 'user-sex', '女', 'F', '1', '2017-11-28 12:30:49', '2017-11-28 12:30:49');
INSERT INTO `t_dict` VALUES ('2', 'user-sex', '男', 'M', '2', '2017-11-28 12:22:01', '2017-11-28 12:22:01');
INSERT INTO `t_dict` VALUES ('3', 'user-sex', '保密', 'S', '3', '2017-11-28 12:31:29', '2017-11-28 12:31:29');

-- ----------------------------
-- Table structure for t_element
-- ----------------------------
DROP TABLE IF EXISTS `t_element`;
CREATE TABLE `t_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_element
-- ----------------------------
INSERT INTO `t_element` VALUES ('3', 'userManager:btn_add', 'button', '新增', '/pconnect-user/v1/tUser/**', '1', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('4', 'userManager:btn_edit', 'button', '编辑', '/pconnect-user/v1/tUser/**', '1', null, null, 'PUT', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('5', 'userManager:btn_del', 'button', '删除', '/pconnect-user/v1/tUser/**', '1', null, null, 'DELETE', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('10', 'menuManager:btn_add', 'button', '新增', '/pconnect-user/v1/tMenu/**', '6', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('11', 'menuManager:btn_edit', 'button', '编辑', '/pconnect-user/v1/tMenu/**', '6', null, null, 'PUT', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('12', 'menuManager:btn_del', 'button', '删除', '/pconnect-user/v1/tMenu/**', '6', null, null, 'DELETE', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('13', 'menuManager:btn_element_add', 'button', '新增元素', '/pconnect-user/v1/tElement/**', '6', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('14', 'menuManager:btn_element_edit', 'button', '编辑元素', '/pconnect-user/v1/tElement/**', '6', null, null, 'PUT', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('15', 'menuManager:btn_element_del', 'button', '删除元素', '/pconnect-user/v1/tElement/**', '6', null, null, 'DELETE', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('16', 'groupManager:btn_add', 'button', '新增', '/pconnect-user/v1/tGroup/**', '7', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('17', 'groupManager:btn_edit', 'button', '编辑', '/pconnect-user/v1/tGroup/**', '7', null, null, 'PUT', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('18', 'groupManager:btn_del', 'button', '删除', '/pconnect-user/v1/tGroup/**', '7', null, null, 'DELETE', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('19', 'groupManager:btn_userManager', 'button', '分配用户', '/pconnect-user/v1/tUserGroup/**', '7', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('20', 'groupManager:btn_resourceManager', 'button', '分配权限', '/pconnect-user/v1/tAuthority/**', '7', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('21', 'groupManager:menu', 'uri', '分配菜单', '/pconnect-user/v1/tAuthority/**', '7', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('22', 'groupManager:element', 'uri', '分配元素', '/pconnect-user/v1/tAuthority/**', '7', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('23', 'userManager:view', 'uri', '查看', '/pconnect-user/v1/tUser/**', '1', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('24', 'menuManager:view', 'uri', '查看', '/pconnect-user/v1/tMenu/**', '6', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('27', 'menuManager:element_view', 'uri', '查看元素', '/pconnect-user/v1/tElement/**', '6', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('28', 'groupManager:view', 'uri', '查看', '/pconnect-user/v1/tGroup/**', '7', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('32', 'groupTypeManager:view', 'uri', '查看', '/pconnect-user/v1/tGroupType/**', '8', null, null, 'GET', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('33', 'groupTypeManager:btn_add', 'button', '新增', '/pconnect-user/v1/tGroupType/**', '8', null, null, 'POST', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('34', 'groupTypeManager:btn_edit', 'button', '编辑', '/pconnect-user/v1/tGroupType/**', '8', null, null, 'PUT', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('35', 'groupTypeManager:btn_del', 'button', '删除', '/pconnect-user/v1/tGroupType/**', '8', null, null, 'DELETE', '2017-10-06 15:24:15', '2017-10-06 15:24:15');
INSERT INTO `t_element` VALUES ('36', 'dictManager:view', 'uri', '查看', '/pconnect-user/v1/tDict/**', '21', null, null, 'GET', '2017-11-28 13:55:23', '2017-11-28 13:55:23');
INSERT INTO `t_element` VALUES ('37', 'dictManager:btn_add', 'button', '新增', '/pconnect-user/v1/tDict/**', '21', null, null, 'POST', '2017-11-28 13:55:23', '2017-11-28 13:55:23');
INSERT INTO `t_element` VALUES ('38', 'dictManager:btn_edit', 'button', '编辑', '/pconnect-user/v1/tDict/**', '21', null, null, 'PUT', '2017-11-28 13:59:23', '2017-11-28 13:59:23');
INSERT INTO `t_element` VALUES ('39', 'dictManager:btn_del', 'button', '删除', '/pconnect-user/v1/tDict/**', '21', null, null, 'DELETE', '2017-11-28 13:59:56', '2017-11-28 13:59:56');
INSERT INTO `t_element` VALUES ('40', 'userLogManager:view', 'uri', '查看', '/pconnect-user/v1/tUserLog/**', '36', null, null, 'GET', '2017-11-28 23:16:00', '2017-11-28 23:16:00');

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `group_type_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', 'role', '角色', '-1', '/role', '1', '2017-10-12 13:14:13', '2017-10-12 13:14:13');
INSERT INTO `t_group` VALUES ('2', 'ADMIN', '管理员', '1', '/role/admin', '1', '2017-10-06 21:29:40', '2017-10-06 21:29:40');
INSERT INTO `t_group` VALUES ('4', 'tourist', '游客', '1', '/role/tourist', '1', '2017-10-06 21:29:40', '2017-10-06 21:29:40');
INSERT INTO `t_group` VALUES ('6', 'company', '深圳华为技术有限公司', '-1', '/company', '2', '2017-10-06 21:29:40', '2017-10-06 21:29:40');
INSERT INTO `t_group` VALUES ('7', 'financeDepart', '财务部', '6', '/company/financeDepart', '2', '2017-10-06 21:29:40', '2017-10-06 21:29:40');
INSERT INTO `t_group` VALUES ('8', 'hrDepart', '人力资源部', '6', '/company/hrDepart', '2', '2017-10-06 21:29:40', '2017-10-06 21:29:40');
INSERT INTO `t_group` VALUES ('9', 'USER', '普通用户', '1', '/role/user', '1', '2017-11-30 09:32:52', '2017-11-30 09:32:52');

-- ----------------------------
-- Table structure for t_group_type
-- ----------------------------
DROP TABLE IF EXISTS `t_group_type`;
CREATE TABLE `t_group_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_type
-- ----------------------------
INSERT INTO `t_group_type` VALUES ('1', 'role', '角色类型', '2017-10-06 14:49:11', '2017-10-06 14:49:11');
INSERT INTO `t_group_type` VALUES ('2', 'depart', '部门类型', '2017-10-06 14:49:11', '2017-10-06 14:49:11');
INSERT INTO `t_group_type` VALUES ('3', 'custom', '自定义类型', '2017-10-06 14:49:11', '2017-10-06 14:49:11');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `order_num` int(11) NOT NULL DEFAULT '0',
  `path` varchar(500) DEFAULT NULL,
  `enabled` char(1) DEFAULT 'Y',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', 'userManager', '用户管理', '5', '/privilege/tUserList', 'fa fa-user', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('5', 'baseManager', '基础配置', '13', '/', 'fa fa-cog', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('6', 'menuManager', '菜单管理', '5', '/privilege/tMenuList', 'fa fa-list', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('7', 'groupManager', '组织架构管理', '5', '/privilege/tGroupList', 'fa fa-users', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('8', 'groupTypeManager', '组织类型管理', '5', '/privilege/tGroupTypeList', 'fa fa-object-group', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('13', 'adminSys', '权限管理系统', '-1', '/', 'fa fa-terminal', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('14', 'contentSys', '区域管理系统', '-1', '/', 'fa-newspaper-o', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('21', 'dictManager', '数据字典', '5', '/privilege/tDictList', 'fa fa-book', '0', null, 'Y', '2017-10-06 15:36:15', '2017-10-06 15:36:15');
INSERT INTO `t_menu` VALUES ('33', 'areaManager', '区域管理', '14', '/', 'fa fa-map-o', '0', null, 'Y', '2017-10-17 21:44:03', '2017-10-17 21:44:03');
INSERT INTO `t_menu` VALUES ('34', 'country', '国家', '33', '/privilege/tDictList', 'fa fa-clone', '0', null, 'Y', '2017-10-17 21:46:21', '2017-10-17 21:46:21');
INSERT INTO `t_menu` VALUES ('35', 'province', '省会', '33', '/privilege/tDictList', 'fa  fa-film', '0', null, 'Y', '2017-10-17 21:49:49', '2017-10-17 21:49:49');
INSERT INTO `t_menu` VALUES ('36', 'user-log', '用户操作日志', '5', '/privilege/tUserLogList', 'fa fa-book', '0', null, 'Y', '2017-11-28 23:14:35', '2017-11-28 23:14:35');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) NOT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sex` char(1) DEFAULT 'S' COMMENT '''F''-女，''M''-男，''S''-保密',
  `status` char(1) DEFAULT 'Y' COMMENT '''Y''-激活，''N''-未激活，''D''-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '小刘', '1991-02-11', '湖北省武汉市江夏区金口街道', '13025442101', '07997287924', 'xiaoliu@163.com', 'M', 'Y', '2017-10-06 14:40:36', '2017-10-06 14:40:36');
INSERT INTO `t_user` VALUES ('2', 'aki', '123456', '阿沂', '1989-02-11', '湖北省武汉市江夏区大桥新区', '13430932112', '07997287923', 'aki@163.com', 'F', 'Y', '2017-10-06 14:40:36', '2017-10-06 14:40:36');
INSERT INTO `t_user` VALUES ('3', 'xiaoxiong', '123456', '小熊', '1995-02-11', '湖北省武汉市江夏区纸坊街', '13225442101', '07997287922', 'xiaoxiong@163.com', 'M', 'Y', '2017-10-06 14:40:36', '2017-10-06 14:40:36'); 

-- ----------------------------
-- Table structure for t_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(255) DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'member' COMMENT '''member''-成员，''leader''-领导',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('38', '1', '5', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('39', '1', '3', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('40', '1', '5', 'member', null, null);
INSERT INTO `t_user_group` VALUES ('41', '1', '7', 'member', null, null);
INSERT INTO `t_user_group` VALUES ('43', '1', '1', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('44', '2', '1', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('45', '2', '2', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('46', '2', '3', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('47', '2', '4', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('48', '2', '5', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('49', '2', '7', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('50', '1', '2', 'leader', null, null);
INSERT INTO `t_user_group` VALUES ('51', '9', '9', 'member', null, null);
INSERT INTO `t_user_group` VALUES ('52', '9', '10', 'member', null, null);

-- ----------------------------
-- Table structure for t_user_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_log`;
CREATE TABLE `t_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `session_id` varchar(255) DEFAULT NULL COMMENT '用户sessionId',
  `ip` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL COMMENT '操作',
  `method` varchar(255) DEFAULT NULL COMMENT '访问的方法',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '数据库创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8;
 

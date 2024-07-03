/*
 Navicat Premium Data Transfer

 Source Server         : ss_content
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : localhost:3306
 Source Schema         : scgltest

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 25/04/2023 14:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_role
-- ----------------------------
DROP TABLE IF EXISTS `all_role`;
CREATE TABLE `all_role`  (
  `auth` int(2) NOT NULL AUTO_INCREMENT COMMENT '用户权限号码',
  `user_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限号对应的具体权限',
  `prod_status_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应具体产品状态',
  `task_status_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体任务状态',
  `examin_status_role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体检验状态',
  PRIMARY KEY (`auth`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of all_role
-- ----------------------------
INSERT INTO `all_role` VALUES (1, '超级管理员', '未实施', '未实施', '未检验');
INSERT INTO `all_role` VALUES (2, '计划管理员', '实施中', '实施中', '检验合格');
INSERT INTO `all_role` VALUES (3, '操作员（组长）', '已完成', '已完成', '检验不合格');
INSERT INTO `all_role` VALUES (4, '三方检验员', '延期', '延期', '检验中');
INSERT INTO `all_role` VALUES (5, '操作员（组员）', '产品停工', '检验中', NULL);
INSERT INTO `all_role` VALUES (6, NULL, NULL, '任务终止', NULL);

-- ----------------------------
-- Table structure for prod_task_user
-- ----------------------------
DROP TABLE IF EXISTS `prod_task_user`;
CREATE TABLE `prod_task_user`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `prod_id` int(7) NULL DEFAULT NULL COMMENT '产品主键id',
  `task_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `user_id` int(6) NULL DEFAULT NULL COMMENT '执行人员',
  `user_auth` int(2) NULL DEFAULT NULL COMMENT '执行人员权限',
  `task_order` int(4) NULL DEFAULT NULL COMMENT '任务执行顺序',
  `teamleaderId` int(6) NULL DEFAULT NULL COMMENT '派发任务的组长id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `prod_id`(`prod_id`, `task_name`, `user_id`, `user_auth`) USING BTREE,
  INDEX `prod_task_user_ibfk_1`(`prod_id`) USING BTREE,
  INDEX `task_name`(`task_name`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `user_auth`(`user_auth`) USING BTREE,
  CONSTRAINT `prod_task_user_ibfk_1` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prod_task_user_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prod_task_user_ibfk_4` FOREIGN KEY (`user_auth`) REFERENCES `all_role` (`auth`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prod_task_user_ibfk_5` FOREIGN KEY (`task_name`) REFERENCES `task` (`task_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of prod_task_user
-- ----------------------------
INSERT INTO `prod_task_user` VALUES (1, 96, 'P0正样件整管制造1', 29, 3, 1, NULL);
INSERT INTO `prod_task_user` VALUES (2, 96, 'P0正样件整管制造1', 42, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (3, 96, 'P0正样件整管制造1', 43, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (4, 96, 'P1正样件调试1', 33, 3, 2, NULL);
INSERT INTO `prod_task_user` VALUES (5, 96, 'P1正样件调试1', 42, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (6, 96, 'P1正样件调试1', 43, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (7, 96, 'P2正样件调试', 35, 3, 3, NULL);
INSERT INTO `prod_task_user` VALUES (8, 96, 'P2正样件调试', 42, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (9, 96, 'P2正样件调试', 43, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (10, 96, 'P3正样件老炼', 40, 3, 4, NULL);
INSERT INTO `prod_task_user` VALUES (11, 96, 'P3正样件老炼', 42, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (12, 96, 'P3正样件老炼', 43, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (13, 96, 'P4正样件老炼后测试', 40, 3, 5, NULL);
INSERT INTO `prod_task_user` VALUES (14, 96, 'P4正样件老炼后测试', 42, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (15, 96, 'P4正样件老炼后测试', 43, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (16, 96, 'P5正样件前期包装', 33, 3, 6, NULL);
INSERT INTO `prod_task_user` VALUES (17, 96, 'P5正样件前期包装', 42, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (18, 96, 'P5正样件前期包装', 43, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (19, 96, 'P6正样件老化', 35, 3, 7, NULL);
INSERT INTO `prod_task_user` VALUES (20, 96, 'P6正样件老化', 42, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (21, 96, 'P6正样件老化', 43, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (22, 96, 'P7正样件老化', 40, 3, 8, NULL);
INSERT INTO `prod_task_user` VALUES (23, 96, 'P7正样件老化', 42, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (24, 96, 'P7正样件老化', 43, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (25, 96, 'P8正样件老化', 40, 3, 9, NULL);
INSERT INTO `prod_task_user` VALUES (26, 96, 'P8正样件老化', 42, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (27, 96, 'P8正样件老化', 43, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (28, 96, 'P9正样件后期包装', 33, 3, 10, NULL);
INSERT INTO `prod_task_user` VALUES (29, 96, 'P9正样件后期包装', 42, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (30, 96, 'P9正样件后期包装', 43, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (31, 96, 'P10-1正样件电性能测试', 30, 3, 11, NULL);
INSERT INTO `prod_task_user` VALUES (32, 96, 'P10-1正样件电性能测试', 42, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (33, 96, 'P10-1正样件电性能测试', 43, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (34, 96, 'P10-2正样件力学试验', 30, 3, 12, NULL);
INSERT INTO `prod_task_user` VALUES (35, 96, 'P10-2正样件力学试验', 42, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (36, 96, 'P10-2正样件力学试验', 43, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (37, 96, 'P10-3正样件电性能测试', 30, 3, 13, NULL);
INSERT INTO `prod_task_user` VALUES (38, 96, 'P10-3正样件电性能测试', 42, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (39, 96, 'P10-3正样件电性能测试', 43, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (40, 96, 'P10-4正样件热循环试验', 35, 3, 14, NULL);
INSERT INTO `prod_task_user` VALUES (41, 96, 'P10-4正样件热循环试验', 42, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (42, 96, 'P10-4正样件热循环试验', 43, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (43, 96, 'P10-5正样件热真空试验', 40, 3, 15, NULL);
INSERT INTO `prod_task_user` VALUES (44, 96, 'P10-5正样件热真空试验', 42, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (45, 96, 'P10-5正样件热真空试验', 43, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (46, 96, 'P10-6正样件电性能终测', 35, 3, 16, NULL);
INSERT INTO `prod_task_user` VALUES (47, 96, 'P10-6正样件电性能终测', 42, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (48, 96, 'P10-6正样件电性能终测', 43, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (49, 96, 'P11正样件物理外观检验', 33, 3, 17, NULL);
INSERT INTO `prod_task_user` VALUES (50, 96, 'P11正样件物理外观检验', 42, 4, 17, NULL);
INSERT INTO `prod_task_user` VALUES (51, 96, 'P11正样件物理外观检验', 43, 4, 17, NULL);
INSERT INTO `prod_task_user` VALUES (52, 97, 'P0正样件整管制造1', 29, 3, 1, NULL);
INSERT INTO `prod_task_user` VALUES (53, 97, 'P0正样件整管制造1', 42, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (54, 97, 'P0正样件整管制造1', 43, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (55, 97, 'P1正样件调试1', 33, 3, 2, NULL);
INSERT INTO `prod_task_user` VALUES (56, 97, 'P1正样件调试1', 42, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (57, 97, 'P1正样件调试1', 43, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (58, 97, 'P2正样件调试', 35, 3, 3, NULL);
INSERT INTO `prod_task_user` VALUES (59, 97, 'P2正样件调试', 42, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (60, 97, 'P2正样件调试', 43, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (61, 97, 'P3正样件老炼', 40, 3, 4, NULL);
INSERT INTO `prod_task_user` VALUES (62, 97, 'P3正样件老炼', 42, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (63, 97, 'P3正样件老炼', 43, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (64, 97, 'P4正样件老炼后测试', 40, 3, 5, NULL);
INSERT INTO `prod_task_user` VALUES (65, 97, 'P4正样件老炼后测试', 42, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (66, 97, 'P4正样件老炼后测试', 43, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (67, 97, 'P5正样件前期包装', 33, 3, 6, NULL);
INSERT INTO `prod_task_user` VALUES (68, 97, 'P5正样件前期包装', 42, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (69, 97, 'P5正样件前期包装', 43, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (70, 97, 'P6正样件老化', 35, 3, 7, NULL);
INSERT INTO `prod_task_user` VALUES (71, 97, 'P6正样件老化', 42, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (72, 97, 'P6正样件老化', 43, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (73, 97, 'P7正样件老化', 40, 3, 8, NULL);
INSERT INTO `prod_task_user` VALUES (74, 97, 'P7正样件老化', 42, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (75, 97, 'P7正样件老化', 43, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (76, 97, 'P8正样件老化', 40, 3, 9, NULL);
INSERT INTO `prod_task_user` VALUES (77, 97, 'P8正样件老化', 42, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (78, 97, 'P8正样件老化', 43, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (79, 97, 'P9正样件后期包装', 33, 3, 10, NULL);
INSERT INTO `prod_task_user` VALUES (80, 97, 'P9正样件后期包装', 42, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (81, 97, 'P9正样件后期包装', 43, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (82, 97, 'P10-1正样件电性能测试', 30, 3, 11, NULL);
INSERT INTO `prod_task_user` VALUES (83, 97, 'P10-1正样件电性能测试', 42, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (84, 97, 'P10-1正样件电性能测试', 43, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (85, 97, 'P10-2正样件力学试验', 30, 3, 12, NULL);
INSERT INTO `prod_task_user` VALUES (86, 97, 'P10-2正样件力学试验', 42, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (87, 97, 'P10-2正样件力学试验', 43, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (88, 97, 'P10-3正样件电性能测试', 30, 3, 13, NULL);
INSERT INTO `prod_task_user` VALUES (89, 97, 'P10-3正样件电性能测试', 42, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (90, 97, 'P10-3正样件电性能测试', 43, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (91, 97, 'P10-4正样件热循环试验', 35, 3, 14, NULL);
INSERT INTO `prod_task_user` VALUES (92, 97, 'P10-4正样件热循环试验', 42, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (93, 97, 'P10-4正样件热循环试验', 43, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (94, 97, 'P10-5正样件热真空试验', 40, 3, 15, NULL);
INSERT INTO `prod_task_user` VALUES (95, 97, 'P10-5正样件热真空试验', 42, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (96, 97, 'P10-5正样件热真空试验', 43, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (97, 97, 'P10-6正样件电性能终测', 35, 3, 16, NULL);
INSERT INTO `prod_task_user` VALUES (98, 97, 'P10-6正样件电性能终测', 42, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (99, 97, 'P10-6正样件电性能终测', 43, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (100, 97, 'P11正样件物理外观检验', 33, 3, 17, NULL);
INSERT INTO `prod_task_user` VALUES (101, 97, 'P11正样件物理外观检验', 42, 4, 17, NULL);
INSERT INTO `prod_task_user` VALUES (102, 97, 'P11正样件物理外观检验', 43, 4, 17, NULL);
INSERT INTO `prod_task_user` VALUES (103, 97, 'P0正样件整管制造1', 38, 5, 1, 29);
INSERT INTO `prod_task_user` VALUES (104, 98, 'P0正样件整管制造1', 29, 3, 1, NULL);
INSERT INTO `prod_task_user` VALUES (105, 98, 'P0正样件整管制造1', 42, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (106, 98, 'P0正样件整管制造1', 43, 4, 1, NULL);
INSERT INTO `prod_task_user` VALUES (107, 98, 'P1正样件调试1', 33, 3, 2, NULL);
INSERT INTO `prod_task_user` VALUES (108, 98, 'P1正样件调试1', 42, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (109, 98, 'P1正样件调试1', 43, 4, 2, NULL);
INSERT INTO `prod_task_user` VALUES (110, 98, 'P2正样件调试', 35, 3, 3, NULL);
INSERT INTO `prod_task_user` VALUES (111, 98, 'P2正样件调试', 42, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (112, 98, 'P2正样件调试', 43, 4, 3, NULL);
INSERT INTO `prod_task_user` VALUES (113, 98, 'P3正样件老炼', 40, 3, 4, NULL);
INSERT INTO `prod_task_user` VALUES (114, 98, 'P3正样件老炼', 42, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (115, 98, 'P3正样件老炼', 43, 4, 4, NULL);
INSERT INTO `prod_task_user` VALUES (116, 98, 'P4正样件老炼后测试', 40, 3, 5, NULL);
INSERT INTO `prod_task_user` VALUES (117, 98, 'P4正样件老炼后测试', 42, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (118, 98, 'P4正样件老炼后测试', 43, 4, 5, NULL);
INSERT INTO `prod_task_user` VALUES (119, 98, 'P5正样件前期包装', 33, 3, 6, NULL);
INSERT INTO `prod_task_user` VALUES (120, 98, 'P5正样件前期包装', 42, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (121, 98, 'P5正样件前期包装', 43, 4, 6, NULL);
INSERT INTO `prod_task_user` VALUES (122, 98, 'P6正样件老化', 35, 3, 7, NULL);
INSERT INTO `prod_task_user` VALUES (123, 98, 'P6正样件老化', 42, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (124, 98, 'P6正样件老化', 43, 4, 7, NULL);
INSERT INTO `prod_task_user` VALUES (125, 98, 'P7正样件老化', 40, 3, 8, NULL);
INSERT INTO `prod_task_user` VALUES (126, 98, 'P7正样件老化', 42, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (127, 98, 'P7正样件老化', 43, 4, 8, NULL);
INSERT INTO `prod_task_user` VALUES (128, 98, 'P8正样件老化', 40, 3, 9, NULL);
INSERT INTO `prod_task_user` VALUES (129, 98, 'P8正样件老化', 42, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (130, 98, 'P8正样件老化', 43, 4, 9, NULL);
INSERT INTO `prod_task_user` VALUES (131, 98, 'P9正样件后期包装', 33, 3, 10, NULL);
INSERT INTO `prod_task_user` VALUES (132, 98, 'P9正样件后期包装', 42, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (133, 98, 'P9正样件后期包装', 43, 4, 10, NULL);
INSERT INTO `prod_task_user` VALUES (134, 98, 'P10-1正样件电性能测试', 30, 3, 11, NULL);
INSERT INTO `prod_task_user` VALUES (135, 98, 'P10-1正样件电性能测试', 42, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (136, 98, 'P10-1正样件电性能测试', 43, 4, 11, NULL);
INSERT INTO `prod_task_user` VALUES (137, 98, 'P10-2正样件力学试验', 30, 3, 12, NULL);
INSERT INTO `prod_task_user` VALUES (138, 98, 'P10-2正样件力学试验', 42, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (139, 98, 'P10-2正样件力学试验', 43, 4, 12, NULL);
INSERT INTO `prod_task_user` VALUES (140, 98, 'P10-3正样件电性能测试', 30, 3, 13, NULL);
INSERT INTO `prod_task_user` VALUES (141, 98, 'P10-3正样件电性能测试', 42, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (142, 98, 'P10-3正样件电性能测试', 43, 4, 13, NULL);
INSERT INTO `prod_task_user` VALUES (143, 98, 'P10-4正样件热循环试验', 35, 3, 14, NULL);
INSERT INTO `prod_task_user` VALUES (144, 98, 'P10-4正样件热循环试验', 42, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (145, 98, 'P10-4正样件热循环试验', 43, 4, 14, NULL);
INSERT INTO `prod_task_user` VALUES (146, 98, 'P10-5正样件热真空试验', 40, 3, 15, NULL);
INSERT INTO `prod_task_user` VALUES (147, 98, 'P10-5正样件热真空试验', 42, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (148, 98, 'P10-5正样件热真空试验', 43, 4, 15, NULL);
INSERT INTO `prod_task_user` VALUES (149, 98, 'P10-6正样件电性能终测', 35, 3, 16, NULL);
INSERT INTO `prod_task_user` VALUES (150, 98, 'P10-6正样件电性能终测', 42, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (151, 98, 'P10-6正样件电性能终测', 43, 4, 16, NULL);
INSERT INTO `prod_task_user` VALUES (152, 98, 'P11正样件物理外观检验', 33, 3, 17, NULL);
INSERT INTO `prod_task_user` VALUES (153, 98, 'P11正样件物理外观检验', 42, 4, 17, NULL);
INSERT INTO `prod_task_user` VALUES (154, 98, 'P11正样件物理外观检验', 43, 4, 17, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `prod_id` int(7) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `prod_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `prod_stage` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品阶段',
  `prod_director` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `prod_status` int(1) NOT NULL DEFAULT 1 COMMENT '产品状态：1、未实施；2、实施中；3、已完成；4、延期',
  `prod_creatday` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `prod_updateday` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改日期',
  `ifsend` int(1) NOT NULL DEFAULT 0 COMMENT '是否下发： 1下发，默认0未下发 ',
  `creator_id` int(6) NULL DEFAULT NULL COMMENT '创建者 id',
  `prod_order` int(6) NULL DEFAULT NULL COMMENT '产品批次',
  `ifdelete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除 1删除，默认0',
  PRIMARY KEY (`prod_id`) USING BTREE,
  INDEX `product_ibfk_1`(`creator_id`) USING BTREE,
  INDEX `prod_model`(`prod_model`) USING BTREE,
  INDEX `product_ibfk_2`(`prod_order`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`prod_order`) REFERENCES `task_order` (`prod_order`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (96, '111', '22', 'ws', 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', 1, 28, NULL, 0);
INSERT INTO `product` VALUES (97, 'sss', 'ss', 'sssss', 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', 1, 28, NULL, 0);
INSERT INTO `product` VALUES (98, 'aaa', 'aa', 'a', 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', 1, 28, NULL, 0);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` int(8) NOT NULL AUTO_INCREMENT,
  `prod_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `task_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `planday` int(3) NULL DEFAULT NULL COMMENT '任务计划完成天数',
  `realday` int(3) NULL DEFAULT NULL COMMENT '实际耗时天数',
  `task_status` int(1) NOT NULL DEFAULT 1 COMMENT '任务状态 默认1 未实施',
  `task_creatday` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '任务创建日期',
  `task_updateday` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '任务修改日期',
  `operator_id` int(6) NULL DEFAULT NULL COMMENT '任务实际操作员id',
  `if_operator_sub` int(1) NOT NULL DEFAULT 0 COMMENT '操作员是否提交 0、未提交 1、已提交 默认0',
  `examiner_id` int(6) NULL DEFAULT NULL COMMENT '任务实际检验员id',
  `examin_status` int(2) NOT NULL DEFAULT 1 COMMENT '检验状态 默认0 未检验',
  `if_now_task` int(1) NOT NULL DEFAULT 0 COMMENT '是否当前任务 0、否,1、是 默认0',
  `if_check_card` int(1) NOT NULL DEFAULT 0 COMMENT '是否出具任务检验单 0、否,1、是  默认0 未出具',
  `ifdelete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0：未删除；  1：已删除； 默认0',
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `prod_model`(`prod_model`) USING BTREE,
  INDEX `operatorid`(`operator_id`) USING BTREE,
  INDEX `examinerid`(`examiner_id`) USING BTREE,
  INDEX `task_name`(`task_name`) USING BTREE,
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`prod_model`) REFERENCES `product` (`prod_model`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_3` FOREIGN KEY (`examiner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 492 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (441, '111', 'P0正样件整管制造1', 3, NULL, 3, '2023-04-20 19:45:09', '2023-04-20 19:46:14', 29, 1, 43, 2, 0, 0, 0);
INSERT INTO `task` VALUES (442, '111', 'P1正样件调试1', 2, NULL, 3, '2023-04-20 19:45:09', '2023-04-20 19:58:02', 33, 1, 43, 2, 0, 0, 0);
INSERT INTO `task` VALUES (443, '111', 'P2正样件调试', 1, NULL, 3, '2023-04-20 19:45:09', '2023-04-20 20:47:02', 35, 1, 43, 3, 1, 0, 0);
INSERT INTO `task` VALUES (444, '111', 'P3正样件老炼', 2, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (445, '111', 'P4正样件老炼后测试', 3, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (446, '111', 'P5正样件前期包装', 4, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (447, '111', 'P6正样件老化', 5, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (448, '111', 'P7正样件老化', 6, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (449, '111', 'P8正样件老化', 7, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (450, '111', 'P9正样件后期包装', 8, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (451, '111', 'P10-1正样件电性能测试', 9, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (452, '111', 'P10-2正样件力学试验', 10, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (453, '111', 'P10-3正样件电性能测试', 11, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (454, '111', 'P10-4正样件热循环试验', 12, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (455, '111', 'P10-5正样件热真空试验', 13, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (456, '111', 'P10-6正样件电性能终测', 14, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (457, '111', 'P11正样件物理外观检验', 15, NULL, 2, '2023-04-20 19:45:09', '2023-04-20 19:45:14', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (458, 'sss', 'P0正样件整管制造1', 3, NULL, 5, '2023-04-20 20:04:53', '2023-04-20 20:42:09', 29, 1, NULL, 4, 1, 0, 0);
INSERT INTO `task` VALUES (459, 'sss', 'P1正样件调试1', 2, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (460, 'sss', 'P2正样件调试', 1, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (461, 'sss', 'P3正样件老炼', 2, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (462, 'sss', 'P4正样件老炼后测试', 3, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (463, 'sss', 'P5正样件前期包装', 4, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (464, 'sss', 'P6正样件老化', 5, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (465, 'sss', 'P7正样件老化', 6, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (466, 'sss', 'P8正样件老化', 7, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (467, 'sss', 'P9正样件后期包装', 8, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (468, 'sss', 'P10-1正样件电性能测试', 9, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (469, 'sss', 'P10-2正样件力学试验', 10, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (470, 'sss', 'P10-3正样件电性能测试', 11, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (471, 'sss', 'P10-4正样件热循环试验', 12, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (472, 'sss', 'P10-5正样件热真空试验', 13, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (473, 'sss', 'P10-6正样件电性能终测', 14, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (474, 'sss', 'P11正样件物理外观检验', 15, NULL, 2, '2023-04-20 20:04:53', '2023-04-20 20:04:57', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (475, 'aaa', 'P0正样件整管制造1', 3, NULL, 3, '2023-04-20 20:45:48', '2023-04-20 20:48:59', 29, 1, 43, 2, 0, 0, 0);
INSERT INTO `task` VALUES (476, 'aaa', 'P1正样件调试1', 2, NULL, 5, '2023-04-20 20:45:48', '2023-04-20 20:49:50', 33, 1, NULL, 4, 1, 0, 0);
INSERT INTO `task` VALUES (477, 'aaa', 'P2正样件调试', 1, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (478, 'aaa', 'P3正样件老炼', 2, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (479, 'aaa', 'P4正样件老炼后测试', 3, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (480, 'aaa', 'P5正样件前期包装', 4, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (481, 'aaa', 'P6正样件老化', 5, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (482, 'aaa', 'P7正样件老化', 6, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (483, 'aaa', 'P8正样件老化', 7, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (484, 'aaa', 'P9正样件后期包装', 8, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (485, 'aaa', 'P10-1正样件电性能测试', 9, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (486, 'aaa', 'P10-2正样件力学试验', 10, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (487, 'aaa', 'P10-3正样件电性能测试', 11, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (488, 'aaa', 'P10-4正样件热循环试验', 12, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (489, 'aaa', 'P10-5正样件热真空试验', 13, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (490, 'aaa', 'P10-6正样件电性能终测', 14, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);
INSERT INTO `task` VALUES (491, 'aaa', 'P11正样件物理外观检验', 15, NULL, 2, '2023-04-20 20:45:48', '2023-04-20 20:45:52', NULL, 0, NULL, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for task_order
-- ----------------------------
DROP TABLE IF EXISTS `task_order`;
CREATE TABLE `task_order`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `prod_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `prod_order` int(6) NULL DEFAULT NULL COMMENT '产品批次',
  `xx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序',
  `xxxx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工序状态',
  `task_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `prod_order`(`prod_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of task_order
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(510) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123' COMMENT '用户密码 密文',
  `auth` int(2) NULL DEFAULT NULL COMMENT '用户权限 1、超级管理员；2、计划管理员；3、操作员；4、三方检测员',
  `nick_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `ifdelete` int(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除 0未删除1删除，默认0',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `auth`(`auth`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`auth`) REFERENCES `all_role` (`auth`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (27, 'admin', '123', 1, NULL, NULL, 0);
INSERT INTO `user` VALUES (28, '梁晓峰', '123', 2, NULL, NULL, 0);
INSERT INTO `user` VALUES (29, '尚艳华', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (30, '李莎莎', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (31, '肖彤', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (32, '霍达', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (33, '梁文龙', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (34, '刘冲', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (35, '崔海超', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (36, '柳宝才', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (37, '张斯硕', '123', 5, NULL, NULL, 0);
INSERT INTO `user` VALUES (38, '李文婷', '123', 5, NULL, NULL, 0);
INSERT INTO `user` VALUES (39, '谢永强', '123', 5, NULL, NULL, 0);
INSERT INTO `user` VALUES (40, '吕涛', '123', 3, NULL, NULL, 0);
INSERT INTO `user` VALUES (41, '常园海', '123', 3, NULL, NULL, 1);
INSERT INTO `user` VALUES (42, '李焕章', '123', 4, NULL, NULL, 0);
INSERT INTO `user` VALUES (43, '路宽', '123', 4, NULL, NULL, 0);
INSERT INTO `user` VALUES (44, '常远海', '123', 3, NULL, NULL, 1);
INSERT INTO `user` VALUES (45, '常园海', '123', 3, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;

/*
Navicat MySQL Data Transfer

Source Server         : first
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : joes

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-07-15 22:12:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` varchar(32) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', '软日1208');
INSERT INTO `t_class` VALUES ('2', '软日1218');

-- ----------------------------
-- Table structure for `t_exam_struct`
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_struct`;
CREATE TABLE `t_exam_struct` (
  `status` int(1) NOT NULL,
  `id` varchar(32) NOT NULL,
  `time` varchar(32) NOT NULL,
  `structure` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_exam_struct
-- ----------------------------
INSERT INTO `t_exam_struct` VALUES ('0', '4028811c49128ebc0149128f62ac0001', '2014-10-15-14-47', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/cecece.xml', 'cecece');
INSERT INTO `t_exam_struct` VALUES ('0', '4028811c49128ebc0149128fe69f0002', '2014-10-15-14-47', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/测试试题.xml', '测试试题');
INSERT INTO `t_exam_struct` VALUES ('1', '402881e44bf3f5a1014bf3fc71c30001', '2015-03-07-19-26', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/hkhkjhhkj.xml', 'hkhkjhhkj');
INSERT INTO `t_exam_struct` VALUES ('0', '402881fd48ee51490148ee5233480001', '2014-10-08-13-53', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/中期考试.xml', '中期考试');
INSERT INTO `t_exam_struct` VALUES ('0', '402881fd48ee51490148ee6f56bd0002', '2014-10-08-14-25', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/期末考试.xml', '期末考试');
INSERT INTO `t_exam_struct` VALUES ('0', '4028ad67494aee8c01494af099dd0001', '2014-10-26-13-32', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/ceshi.xml', 'ceshi');
INSERT INTO `t_exam_struct` VALUES ('0', '4028ae3e48f94def0148f95d887b0001', '2014-10-10-17-22', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/测试考试.xml', '测试考试');
INSERT INTO `t_exam_struct` VALUES ('0', 'ff80808148f9b60f0148f9c141d60001', '2014-10-10-19-11', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/测试试题1.xml', '测试试题1');
INSERT INTO `t_exam_struct` VALUES ('0', 'ff808081491288b00149128a7b3e0001', '2014-10-15-14-41', 'F:\\data\\Code\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\japanese-OES\\resource/exam/ 测试.xml', ' 测试');

-- ----------------------------
-- Table structure for `t_question`
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `type` varchar(10) NOT NULL,
  `url` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `resource` varchar(255) DEFAULT NULL,
  `uploaddate` varchar(32) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f7940001', '2014-09-26-14-45-0.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-0.mp3', '3番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70002', '2014-09-26-14-45-1.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-1.mp3', '2番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70003', '2014-09-26-14-45-2.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-2.mp3', '1番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70004', '2014-09-26-14-45-3.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-3.mp3', '8番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70005', '2014-09-26-14-45-4.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-4.mp3', '7番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70006', '2014-09-26-14-45-5.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-5.mp3', '6番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b70007', '2014-09-26-14-45-6.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-6.mp3', '5番.mp3', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b4f8b80008', '2014-09-26-14-45-7.wav', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-45-7.wav', '4番.wav', null, '2014-09-26-14-45', '仕事部屋探し');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b5ace10009', '2014-09-26-14-46-0.mp3', 'IMG', '/japanese-OES/question/imgQuestion/', '作业音频/5番　?内.mp3', null, '2014-09-26-14-46', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b5ad0c000a', '2014-09-26-14-46-1.mp3', 'IMG', '/japanese-OES/question/imgQuestion/', '作业音频/4番　?内.mp3', null, '2014-09-26-14-46', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b5ad0c000b', '2014-09-26-14-46-2.mp3', 'IMG', '/japanese-OES/question/imgQuestion/', '作业音频/3番　交通.mp3', null, '2014-09-26-14-46', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b5ad0c000c', '2014-09-26-14-46-3.mp3', 'IMG', '/japanese-OES/question/imgQuestion/', '作业音频/2番　交通.mp3', null, '2014-09-26-14-46', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b5ad0c000d', '2014-09-26-14-46-4.mp3', 'IMG', '/japanese-OES/question/imgQuestion/', '作业音频/1番　交通.mp3', null, '2014-09-26-14-46', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b9a9b9000e', '2014-09-26-14-49-0.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-49-0.mp3', '作业音频/5番　?内.mp3', null, '2014-09-26-14-49', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b9a9bb000f', '2014-09-26-14-50-1.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-50-1.mp3', '作业音频/4番　?内.mp3', null, '2014-09-26-14-50', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b9a9bc0010', '2014-09-26-14-50-2.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-50-2.mp3', '作业音频/3番　交通.mp3', null, '2014-09-26-14-50', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b9a9bc0011', '2014-09-26-14-50-3.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-50-3.mp3', '作业音频/2番　交通.mp3', null, '2014-09-26-14-50', '作业音频');
INSERT INTO `t_question` VALUES ('8a9f79d448b0b4b10148b0b9a9bc0012', '2014-09-26-14-50-4.mp3', 'GENERAL', '/japanese-OES/question/generalQuestion/2014-09-26-14-50-4.mp3', '作业音频/1番　交通.mp3', null, '2014-09-26-14-50', '作业音频');

-- ----------------------------
-- Table structure for `t_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` varchar(32) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `userId` varchar(9) DEFAULT NULL,
  `questionId` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `score` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('402881fd48fa1b8a0148fa1c26260001', '/japanese-OES/answer/201293222/-2014-10-10-20-50.wmv', '201293222', '8a9f79d448b0b4b10148b0b4f8b70003', '1', '200');
INSERT INTO `t_record` VALUES ('4028ae374902e92e014902e9f36b0001', '/japanese-OES/answer/201293222/-2014-10-12-13-52.wmv', '201293222', '8a9f79d448b0b4b10148b0b5ad0c000c', '1', '100');

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `teacherId` varchar(32) NOT NULL,
  `teacherName` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('201293018', '来看看 ', 'a5df1d69b97c03bf67a3e916094177b8');
INSERT INTO `t_teacher` VALUES ('201293222', '韩兰灵', '5f101e5f38a2bd7a25c4edcf08c2ca29');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` varchar(32) NOT NULL,
  `userName` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `classNumber` varchar(32) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('201292060.333', 'yijun1171', '3c8fd17f354942859c90fb89f7725988', '');
INSERT INTO `t_user` VALUES ('201292162.333', '邹善军', '1faa4a14e9e4d833b1d0f67290fbdb64', '软日1218');
INSERT INTO `t_user` VALUES ('201292264.333', '韩兰灵', '7e8d243e7e1ac830f7eb31c74ad619b7', '软日1217');
INSERT INTO `t_user` VALUES ('201292366.333', '曲成芳', '1827b67c17328207979c9fe719104a64', '软日1216');
INSERT INTO `t_user` VALUES ('201292468.333', '邹善军', '7d29c77b9dc21ca0545867f36e6571e1', '软日1215');
INSERT INTO `t_user` VALUES ('201292570.333', '韩兰灵', '22a75a39e27ef3773c62793af529357e', '软日1214');
INSERT INTO `t_user` VALUES ('201292672.333', '曲成芳', 'b7e08c0703eb14a22536c4fe2e6e4833', '软日1213');
INSERT INTO `t_user` VALUES ('201292774.333', '邹善军', 'b48c3613714f99bd61215f68d5d2ecee', '软日1212');
INSERT INTO `t_user` VALUES ('201292876.333', '韩兰灵', 'b742ba6723b22bbdbe5e821cbe882246', '软日1211');
INSERT INTO `t_user` VALUES ('201293001', '邹善军', 'f8fe65d526c4ef58a85cd26d4deaf0c9', '软日1208');
INSERT INTO `t_user` VALUES ('201293018', '曲成芳', 'a5df1d69b97c03bf67a3e916094177b8', '软日1208');
INSERT INTO `t_user` VALUES ('201293222', '韩兰灵', '5f101e5f38a2bd7a25c4edcf08c2ca29', '软日1208');

-- ----------------------------
-- init
-- ----------------------------
set character set utf8;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- DATABASE hunting
-- ----------------------------
drop database hunting;
create database hunting;
use hunting;

-- ----------------------------
-- Table structure for areas
-- ----------------------------
DROP TABLE IF EXISTS areas;
CREATE TABLE areas (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  code varchar(20) DEFAULT NULL COMMENT '代号',
  name varchar(100) DEFAULT NULL COMMENT '区域名称',
  parent_id bigint(20) DEFAULT NULL COMMENT '上级区域ID，一级区域为0',
  acreage bigint(20) DEFAULT NULL COMMENT '面积，单位：平方公里',
  population bigint(20) DEFAULT NULL COMMENT '人口，单位：万人',
  households bigint(20) DEFAULT NULL COMMENT '户数',
  notes varchar(1000) DEFAULT NULL COMMENT '说明',
  order_num int(11) DEFAULT NULL COMMENT '排序',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='区域管理';

-- ----------------------------
-- Records of areas
-- ----------------------------
INSERT INTO areas VALUES ('1', 'sh', '上海', 0, null,null,null,null,null,'admin', '2018-09-23 19:35:22', null, null, '0');
INSERT INTO areas VALUES ('2', 'mh', '闵行区', 1, null,null,null,null,null,'admin', '2018-09-23 19:35:55', null, null, '0');
INSERT INTO areas VALUES ('3', 'zz', '紫竹科技园', 2,null,null,null,null,null, 'admin', '2018-09-23 19:36:24', null, null, '0');

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS departments;
CREATE TABLE departments (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  code varchar(20) DEFAULT NULL COMMENT '代号',
  name varchar(100) DEFAULT NULL COMMENT '机构名称',
  parent_id bigint(20) DEFAULT NULL COMMENT '上级机构ID，一级机构为0',
  department_type varchar(20) DEFAULT 'department' COMMENT '类型，company公司，department部门，office-room科室，group小组，other其他',
  principal varchar(50) DEFAULT NULL COMMENT '负责人',
  department_level varchar(50) DEFAULT NULL COMMENT '级别',
  address varchar(500) DEFAULT NULL COMMENT '地址',
  telephone varchar(50) DEFAULT NULL COMMENT '电话',
  fax varchar(50) DEFAULT NULL COMMENT '传真',
  postcode varchar(50) DEFAULT NULL COMMENT '邮编',
  notes varchar(1000) DEFAULT NULL COMMENT '说明',
  order_num int(11) DEFAULT NULL COMMENT '排序',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id(id),
  UNIQUE KEY uk_code (code)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='机构管理';

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO departments VALUES ('17', 'yjt', '第一集团', null,null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:35:22', null, null, '0');
INSERT INTO departments VALUES ('18', 'ejt', '第二集团', null,null,null,null,null,null,null,null,null, '1', 'admin', '2018-09-23 19:35:55', null, null, '0');
INSERT INTO departments VALUES ('19', 'sjt', '第三集团', null,null,null,null,null,null,null,null,null, '2', 'admin', '2018-09-23 19:36:24', null, null, '0');
INSERT INTO departments VALUES ('21', 'sh', '上海分公司', '18',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:37:03', null, null, '0');
INSERT INTO departments VALUES ('22', 'bj', '北京分公司', '17',null,null,null,null,null,null,null,null, '1', 'admin', '2018-09-23 19:37:17', null, null, '0');
INSERT INTO departments VALUES ('23', 'gz', '广州分公司', '18',null,null,null,null,null,null,null,null, '1', 'admin', '2018-09-23 19:37:28', null, null, '0');
INSERT INTO departments VALUES ('25', 'rj', '软件研发部', '22',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:38:00', null, null, '0');
INSERT INTO departments VALUES ('26', 'yj', '硬件研发部', '21',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:38:10', null, null, '0');
INSERT INTO departments VALUES ('27', 'yw', '交付运维部', '23',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:38:17', null, null, '0');
INSERT INTO departments VALUES ('29', 'yx', '营销部', '22',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:38:45', null, null, '0');
INSERT INTO departments VALUES ('30', 'sc', '市场部', '23',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:39:01', null, null, '0');
INSERT INTO departments VALUES ('33', 'xt', '系统组', '19',null,null,null,null,null,null,null,null, '0', 'admin', '2018-09-23 19:40:42', null, null, '0');
INSERT INTO departments VALUES ('34', 'kf', '开发组', '19',null,null,null,null,null,null,null,null, '1', 'admin', '2018-09-23 19:40:54', null, null, '0');
INSERT INTO departments VALUES ('35', 'cs', '测试组', '19',null,null,null,null,null,null,null,null, '2', 'admin', '2018-09-23 19:41:04', null, null, '0');

-- ----------------------------
-- Table structure for area_dept
-- ----------------------------
DROP TABLE IF EXISTS area_dept;
CREATE TABLE area_dept (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  area_id bigint(20) DEFAULT NULL COMMENT '区域ID',
  dept_id bigint(20) DEFAULT NULL COMMENT '机构ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域机构关系表，备用';

-- ----------------------------
-- Records of area_dept
-- ----------------------------

-- ----------------------------
-- Table structure for dictionaries
-- ----------------------------
DROP TABLE IF EXISTS dictionaries;
CREATE TABLE dictionaries (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  value varchar(100) NOT NULL COMMENT '数据值',
  label varchar(100) NOT NULL COMMENT '标签名',
  type varchar(100) NOT NULL COMMENT '类型',
  description varchar(100) NOT NULL COMMENT '描述',
  sort decimal(10,0) NOT NULL COMMENT '排序（升序）',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  notes varchar(255) DEFAULT NULL COMMENT '说明',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of dictionaries
-- ----------------------------
INSERT INTO dictionaries VALUES ('3', 'male', '男', 'sex', '性别', '0', 'admin', '2018-09-23 19:52:54', null, null, '性别', '0');
INSERT INTO dictionaries VALUES ('4', 'female', '女', 'sex', '性别', '1', 'admin', '2018-09-23 19:53:17', null, null, '性别', '0');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS logs;
CREATE TABLE logs (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_name varchar(50) DEFAULT NULL COMMENT '用户名',
  operation varchar(50) DEFAULT NULL COMMENT '用户操作',
  method varchar(200) DEFAULT NULL COMMENT '请求方法',
  params varchar(5000) DEFAULT NULL COMMENT '请求参数',
  execute_time bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  ip varchar(64) DEFAULT NULL COMMENT 'IP地址',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=1754 DEFAULT CHARSET=utf8 COMMENT='系统日志';



-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS resources;
CREATE TABLE resources (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  name varchar(50) DEFAULT NULL COMMENT '资源名称',
  parent_id bigint(20) DEFAULT NULL COMMENT '父资源ID，一级资源为0',
  url varchar(200) DEFAULT NULL COMMENT '资源URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址), 4.表记录，使用rec:{tablename}/{id}，其中多个tablename使用逗号分隔，多个id使用逗号分隔',
  perms varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  resource_type int(11) DEFAULT NULL COMMENT '类型   0：目录/元数据   1：菜单   2：按钮 3：信息项/数据记录',
  icon varchar(50) DEFAULT NULL COMMENT '图标',
  order_num int(11) DEFAULT NULL COMMENT '排序',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO resources VALUES ('1', '系统管理', '0', null, null, '0', 'el-icon-setting', '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('2', '用户管理', '1', '/sys/user', null, '1', 'el-icon-service', '1', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('3', '机构管理', '1', '/sys/dept', null, '1', 'el-icon-news', '2', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('4', '角色管理', '1', '/sys/role', null, '1', 'el-icon-view', '4', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('5', '资源管理', '1', '/sys/menu', null, '1', 'el-icon-menu', '5', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('6', '数据监控', '43', 'http://localhost:8001/druid/login.html', null, '2', 'el-icon-warning', '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('7', '字典管理', '1', '/sys/dict', null, '1', 'el-icon-edit-outline', '7', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('8', '系统日志', '1', '/sys/log', 'sys:log:view', '1', 'el-icon-info', '8', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('9', '查看', '2', null, 'sys:user:view', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('10', '新增', '2', null, 'sys:user:add', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('11', '修改', '2', null, 'sys:user:edit', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('12', '删除', '2', null, 'sys:user:delete', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('13', '查看', '3', null, 'sys:dept:view', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('14', '新增', '3', null, 'sys:dept:add', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('15', '修改', '3', null, 'sys:dept:edit', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('16', '删除', '3', null, 'sys:dept:delete', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('17', '查看', '4', null, 'sys:role:view', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('18', '新增', '4', null, 'sys:role:add', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('19', '修改', '4', null, 'sys:role:edit', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('20', '删除', '4', null, 'sys:role:delete', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('21', '查看', '5', null, 'sys:menu:view', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('22', '新增', '5', null, 'sys:menu:add', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('23', '修改', '5', null, 'sys:menu:edit', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('24', '删除', '5', null, 'sys:menu:delete', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('28', '使用案例', '0', null, null, '0', 'el-icon-picture-outline', '6', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('29', '国际化', '28', '/demo/i18n', null, '1', 'el-icon-edit', '1', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('30', '换皮肤', '28', '/demo/theme', null, '1', 'el-icon-picture', '2', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('31', '查看', '7', null, 'sys:dict:view', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('32', '新增', '7', null, 'sys:dict:add', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('33', '修改', '7', null, 'sys:dict:edit', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('34', '删除', '7', null, 'sys:dict:delete', '2', null, '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('35', '接口文档', '0', 'http://localhost:8001/swagger-ui.html', null, '1', 'el-icon-document', '3', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('38', '服务监控', '43', 'http://localhost:8000/', '', '1', 'el-icon-view', '1', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('41', '注册中心', '44', 'http://localhost:8500', '', '1', ' el-icon-view', '0', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('42', '代码生成', '0', '/generator/generator', '', '1', 'el-icon-star-on', '5', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('43', '系统监控', '0', '', '', '0', 'el-icon-info', '1', 'admin', now(), null, null, '0');
INSERT INTO resources VALUES ('44', '服务治理', '0', '', '', '0', 'el-icon-service', '2', 'admin', now(), null, null, '0');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  code varchar(20) DEFAULT NULL COMMENT '角色代号',
  name varchar(100) DEFAULT NULL COMMENT '角色名称',
  parent_id bigint(20) DEFAULT NULL COMMENT '父ID，一级为0',
  role_type varchar(20) DEFAULT NULL COMMENT '职责/类型',
  notes varchar(1000) DEFAULT NULL COMMENT '说明',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO roles VALUES ('1', 'admin', '超级管理员', 0, null,null,'admin', '2018-08-14 11:11:11', 'admin', '2018-09-23 19:07:18', '0');
INSERT INTO roles VALUES ('2', 'dev', '开发人员', 0, null,null,'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO roles VALUES ('3', 'test', '测试人员', 0, null,null,'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO roles VALUES ('8', 'mng', '部门经理',0, null,null, 'admin', '2018-09-23 19:09:52', null, null, '0');

-- ----------------------------
-- Table structure for role_dept
-- ----------------------------
DROP TABLE IF EXISTS role_dept;
CREATE TABLE role_dept (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  role_id bigint(20) DEFAULT NULL COMMENT '角色ID',
  dept_id bigint(20) DEFAULT NULL COMMENT '机构ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色机构关系表，备用';

-- ----------------------------
-- Records of role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for acls
-- ----------------------------
DROP TABLE IF EXISTS acls;
CREATE TABLE acls (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  role_id bigint(20) DEFAULT NULL COMMENT '角色ID',
  resource_id bigint(20) DEFAULT NULL COMMENT '资源ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=445 DEFAULT CHARSET=utf8 COMMENT='访问控制列表';

-- ----------------------------
-- Records of acls
-- ----------------------------
INSERT INTO acls VALUES ('224', '4', '1', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('225', '4', '2', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('226', '4', '9', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('227', '4', '3', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('228', '4', '13', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('229', '4', '4', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('230', '4', '17', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('231', '4', '5', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('232', '4', '21', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('233', '4', '6', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('234', '4', '7', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('235', '4', '31', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('236', '4', '8', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('237', '4', '25', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('238', '4', '26', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('239', '4', '27', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('240', '4', '28', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('241', '4', '29', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('242', '4', '30', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('243', '4', '35', 'admin', '2018-09-23 16:22:41', null, null);
INSERT INTO acls VALUES ('388', '2', '1', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('389', '2', '2', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('390', '2', '9', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('391', '2', '3', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('392', '2', '13', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('393', '2', '17', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('394', '2', '5', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('395', '2', '21', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('396', '2', '7', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('397', '2', '31', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('398', '2', '8', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('399', '2', '6', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('400', '2', '35', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('401', '2', '28', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('402', '2', '29', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('403', '2', '30', 'admin', '2018-09-23 19:51:53', null, null);
INSERT INTO acls VALUES ('404', '3', '1', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('405', '3', '2', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('406', '3', '9', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('407', '3', '3', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('408', '3', '13', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('409', '3', '8', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('410', '3', '6', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('411', '3', '28', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('412', '3', '29', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('413', '3', '30', 'admin', '2018-09-23 19:51:55', null, null);
INSERT INTO acls VALUES ('431', '8', '1', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('432', '8', '2', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('433', '8', '9', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('434', '8', '3', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('435', '8', '13', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('436', '8', '4', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('437', '8', '17', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('438', '8', '5', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('439', '8', '21', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('440', '8', '7', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('441', '8', '31', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('442', '8', '8', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('443', '8', '6', 'admin', '2018-09-23 19:55:08', null, null);
INSERT INTO acls VALUES ('444', '8', '35', 'admin', '2018-09-23 19:55:08', null, null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  name varchar(50) NOT NULL COMMENT '用户名',

  user_type varchar(20) DEFAULT NULL COMMENT '岗位/类型',
  gender varchar(10) DEFAULT NULL COMMENT '性别，male男，female女，other其他',

  age tinyint(4) DEFAULT NULL COMMENT '年龄',
  address varchar(500) DEFAULT NULL COMMENT '地址',
  fax varchar(50) DEFAULT NULL COMMENT '传真',
  postcode varchar(50) DEFAULT NULL COMMENT '邮编',
  notes varchar(1000) DEFAULT NULL COMMENT '说明',

  password varchar(100) DEFAULT NULL COMMENT '密码，备用，应关联认证管理',
  salt varchar(40) DEFAULT NULL COMMENT '盐',
  email varchar(100) DEFAULT NULL COMMENT '邮箱',
  telephone varchar(50) DEFAULT NULL COMMENT '电话，多个电话分号分隔',
  status tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',

  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  del_flag tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY pk_id (id),
  UNIQUE KEY uk_name (name)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES ('1' , 'admin', null, null, null, null, null, null, null,  'bd1718f058d8a02468134432b8656a86', 'YzcmCZNvbXocrsz9dm8e', 'admin@qq.com', '13612345678', '1', 'admin', '2018-08-14 11:11:11', 'admin', '2018-08-14 11:11:11', '0');
INSERT INTO users VALUES ('22', '张三',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:43:00', 'admin', '2018-11-04 10:12:45', '0');
INSERT INTO users VALUES ('23', '李四',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:43:44', 'admin', '2018-09-23 19:43:52', '0');
INSERT INTO users VALUES ('24', '王五',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:44:23', 'admin', '2018-09-23 19:44:29', '0');
INSERT INTO users VALUES ('25', '周六',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:45:32', 'admin', '2018-09-23 19:45:37', '0');
INSERT INTO users VALUES ('26', '孙七',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:45:48', 'admin', '2018-09-23 19:45:57', '0');
INSERT INTO users VALUES ('27', '陆八',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:46:09', 'admin', '2018-09-23 19:46:17', '0');
INSERT INTO users VALUES ('28', '黄九',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:46:38', 'admin', '2018-11-04 15:33:17', '0');
INSERT INTO users VALUES ('29', '冯十',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:46:54', 'admin', '2018-09-23 19:47:03', '0');
INSERT INTO users VALUES ('30', '十一太保',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:47:28', 'admin', '2018-09-23 19:48:04', '0');
INSERT INTO users VALUES ('31', '十二太保',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:47:44', 'admin', '2018-09-23 19:47:58', '0');
INSERT INTO users VALUES ('32', '十三太保',  null, null, null, null, null, null, null,  'fd80ebd493a655608dc893a9f897d845', 'YzcmCZNvbXocrsz9dm8e', 'test@qq.com', '13889700023', '1', 'admin', '2018-09-23 19:48:38', 'admin', '2018-09-23 19:49:02', '0');

-- ----------------------------
-- Table structure for user_dept
-- ----------------------------
DROP TABLE IF EXISTS user_dept;
CREATE TABLE user_dept (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  dept_id bigint(20) DEFAULT NULL COMMENT '机构ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户机构关系表';

-- ----------------------------
-- Records of role_dept
-- ----------------------------
insert into user_dept values(null, '1' ,  '4', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '22', '34', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '23', '34', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '24', '34', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '25', '33', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '26', '33', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '27', '33', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '28', '33', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '29', '35', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '30', '35', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '31', '35', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');
insert into user_dept values(null, '32', '35', 'admin', '2018-09-23 23:00:00', 'admin', '2018-09-23 23:00:00');

-- ----------------------------
-- Table structure for user_area
-- ----------------------------
DROP TABLE IF EXISTS user_area;
CREATE TABLE user_area (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  area_id bigint(20) DEFAULT NULL COMMENT '区域ID',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域用户机构关系表';

-- ----------------------------
-- Records of user_area
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  role_id bigint(20) DEFAULT NULL COMMENT '角色ID',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO user_role VALUES ('1', '1', '1', null, null, null, null);
INSERT INTO user_role VALUES ('2', '2', '1', null, null, null, null);
INSERT INTO user_role VALUES ('26', '5', '3', null, null, null, null);
INSERT INTO user_role VALUES ('33', '6', '2', null, null, null, null);
INSERT INTO user_role VALUES ('34', '4', '2', null, null, null, null);
INSERT INTO user_role VALUES ('35', '9', '2', null, null, null, null);
INSERT INTO user_role VALUES ('36', '10', '3', null, null, null, null);
INSERT INTO user_role VALUES ('37', '11', '2', null, null, null, null);
INSERT INTO user_role VALUES ('38', '12', '3', null, null, null, null);
INSERT INTO user_role VALUES ('39', '15', '2', null, null, null, null);
INSERT INTO user_role VALUES ('41', '16', '3', null, null, null, null);
INSERT INTO user_role VALUES ('42', '8', '2', null, null, null, null);
INSERT INTO user_role VALUES ('43', '7', '4', null, null, null, null);
INSERT INTO user_role VALUES ('45', '18', '2', null, null, null, null);
INSERT INTO user_role VALUES ('46', '17', '3', null, null, null, null);
INSERT INTO user_role VALUES ('47', '3', '4', null, null, null, null);
INSERT INTO user_role VALUES ('48', '21', '2', null, null, null, null);
INSERT INTO user_role VALUES ('50', '23', '2', null, null, null, null);
INSERT INTO user_role VALUES ('51', '24', '3', null, null, null, null);
INSERT INTO user_role VALUES ('52', '25', '8', null, null, null, null);
INSERT INTO user_role VALUES ('53', '26', '2', null, null, null, null);
INSERT INTO user_role VALUES ('54', '27', '2', null, null, null, null);
INSERT INTO user_role VALUES ('56', '29', '8', null, null, null, null);
INSERT INTO user_role VALUES ('57', '31', '2', null, null, null, null);
INSERT INTO user_role VALUES ('58', '30', '2', null, null, null, null);
INSERT INTO user_role VALUES ('59', '32', '3', null, null, null, null);
INSERT INTO user_role VALUES ('68', '33', '2', null, null, null, null);
INSERT INTO user_role VALUES ('69', '22', '8', null, null, null, null);
INSERT INTO user_role VALUES ('70', '22', '2', null, null, null, null);
INSERT INTO user_role VALUES ('71', '28', '2', null, null, null, null);


-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS user_token;
CREATE TABLE user_token (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  user_id bigint(20) NOT NULL COMMENT '用户ID',
  token varchar(100) NOT NULL COMMENT '令牌',
  expire_time datetime DEFAULT NULL COMMENT '过期时间',
  create_by varchar(50) DEFAULT NULL COMMENT '创建人',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  last_update_by varchar(50) DEFAULT NULL COMMENT '更新人',
  last_update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY pk_id(id),
  UNIQUE KEY uk_token (token)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO user_token VALUES ('1', '1', 'ee02ead2c1e3a113f82accafaf878b69', '2018-12-27 23:08:41', null, null, 'admin', '2018-12-27 11:08:41');
INSERT INTO user_token VALUES ('2', '17', '3d32077ccddb6eb2c4302feb93765cd0', '2018-09-24 05:11:17', null, null, null, '2018-09-23 17:11:17');
INSERT INTO user_token VALUES ('3', '18', 'a939ac41fd309ca785485b4135b8baad', '2018-09-24 05:10:36', null, null, null, '2018-09-23 17:10:36');
INSERT INTO user_token VALUES ('4', '33', '605dbcfa2277cbca3b2a124974816080', '2018-11-04 21:42:49', null, null, null, '2018-11-04 09:42:49');

CREATE TABLE `t_demo` (
  `table_id` bigint(20) NOT NULL COMMENT '表的主键',
  `create_by` bigint(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` datetime NOT NULL COMMENT '创建记录的时间',
  `update_by` bigint(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` datetime NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` int(11) NOT NULL COMMENT '用来排序的字段',
  `del_flag` bit(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`table_id`),
  KEY `index_create_by` (`create_by`) USING BTREE,
  KEY `index_create_time` (`create_time`) USING BTREE,
  KEY `index_update_by` (`update_by`) USING BTREE,
  KEY `index_update_time` (`update_time`) USING BTREE,
  KEY `index_sort_num` (`sort_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='这是demo';

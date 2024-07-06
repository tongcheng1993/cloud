CREATE TABLE `t_demo` (
  `table_id` BIGINT(20) NOT NULL COMMENT '表的主键',
  `create_by` BIGINT(20) NOT NULL COMMENT '创建人账户的主键',
  `create_time` DATETIME NOT NULL COMMENT '创建记录的时间',
  `update_by` BIGINT(20) NOT NULL COMMENT '最后一次修改的人账户的主键',
  `update_time` DATETIME NOT NULL COMMENT '最后一次修改记录的时间',
  `sort_num` INT NOT NULL COMMENT '用来排序的字段',
  `del_flag` BIT(1) NOT NULL COMMENT '用来标记是否删除的字段',
  `name` VARCHAR(45) NOT NULL COMMENT '名字',
  PRIMARY KEY (`table_id`),
  INDEX `index_create_by` USING BTREE (`create_by`),
  INDEX `index_create_time` USING BTREE (`create_time`),
  INDEX `index_sort_num` USING BTREE (`sort_num`),
  INDEX `index_update_by` USING BTREE (`update_by`),
  INDEX `index_update_time` USING BTREE (`update_time`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '这是demo';

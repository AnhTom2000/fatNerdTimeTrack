CREATE TABLE tb_anniversary(
`anniversary_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '纪念日主键',
`user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户主键',
`anniversary_title` VARCHAR (30) NOT NULL '纪念日标题',
`anniversary_time`  TIMESTAMP NOT NULL '纪念日日期',
`anniversary_description` VARCHAR (255) NOT NULL COMMENT '纪念日描述',
)ENGINE=InnoDB AUTO_INCREMENT=2584419330884243457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件表';
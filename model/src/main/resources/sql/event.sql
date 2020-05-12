CREATE TABLE tb_event(
`event_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '事件表主键',
`event_title` VARCHAR(100) NOT NULL COMMENT '事件标题',
`event_description` VARCHAR(255)  COMMENT '事件描述,0表示没有描述',
`user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户表主键',
`priority_id` TINYINT(1) UNSIGNED NOT NULL COMMENT '优先级表主键,代表事件选择了哪个优先级',
`finished` BIT  NOT NULL  COMMENT '是否已完成',
`date` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '事件开始时间，可以没有时间',
`endDate` TIMESTAMP  COMMENT  '事件完成时间',
PRIMARY KEY(`event_id`)
)ENGINE=InnoDB AUTO_INCREMENT=2584419330884243457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件表';
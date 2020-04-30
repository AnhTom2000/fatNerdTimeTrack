CREATE TABLE tb_event_tag_middle(
`event_tag_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '事件标签表主键',
`event_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '事件表主键',
`tag_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '标签表主键',
PRIMARY KEY (`event_tag_id`)
)ENGINE=InnoDB AUTO_INCREMENT=2584419330884243457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='事件标签中间表，因为事件和标签的关系属于多对多，所以需要一个中间表进行解耦';
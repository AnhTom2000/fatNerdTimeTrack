CREATE TABLE tb_feedback(
`feedback_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '反馈主键',
`feedback_title` VARCHAR(40) NOT NULL COMMENT '反馈标题',
`feedback_content` VARCHAR(255) NOT NULL COMMENT '反馈内容',
`user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id，是哪个用户反馈的',
`type`  BIT UNSIGEND NOT NULL COMMENT '反馈类型，0表示建议，1表示bug',
`isReply` BIT UNSIGNED NOT NULL COMMENT '是否已经回复',
`reply_conent` VARCHAR(255) NOT NULL DEFAULT '0' COMMENT '回复内容，0表示没有回复'
PRIMARY KEY (`feedback_id`)
)ENGINE=InnoDB AUTO_INCREMENT=2584419330884243457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='反馈表'
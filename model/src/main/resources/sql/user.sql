CREATE TABLE tb_user(
`userid` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户主键',
`username` VARCHAR(30) NOT NULL COMMENT '用户名',
`password` VARCHAR(75) NOT NULL COMMENT '用户密码',
`salt` VARCHAR(75) NOT NULL COMMENT 'password salt',
`avatar_url` VARCHAR(255) NOT NULL  DEFAULT 'https://weleness-1300955279.cos.ap-guangzhou.myqcloud.com/cdn/imgs/photo.jpg'  COMMENT'用户头像地址长度',
`event_count` INT(11) UNSIGNED NOT NULL COMMENT '用户事件数量',
`tag_cournt` INT(11) UNSIGNED NOT NULL COMMENT '用户标签数量',
`event_finishedCount` INT(11) UNSIGNED NOT NULL COMMENT '用户事件完成数',
`email` VARCHAR(11) NOT NULL COMMENT '用户绑定的邮箱',
`description` VARCHAR(255) NOT NULL COMMENT '用户描述',
`createTime` TIMESTAMP NOT NULL COMMENT '账号创建时间',
 PRIMARY KEY(`userid`)
)ENGINE=InnoDB AUTO_INCREMENT=2584419330884243457 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'
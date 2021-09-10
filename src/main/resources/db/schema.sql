CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户是否可用',
  `roles` text COMMENT '用户角色,多个角色之间用逗号隔开',
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
)
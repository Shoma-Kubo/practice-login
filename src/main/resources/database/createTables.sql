DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
`id` varchar(22) NOT NULL,
`account_name` varchar(16) NOT NULL,
`password` varchar(100) NOT NULL,
`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `session_ids`;
CREATE TABLE `session_ids` (
`session_id` varchar(22) NOT NULL,
`user_id` varchar(22) NOT NULL,
`expire_at` datetime NOT NULL,
`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
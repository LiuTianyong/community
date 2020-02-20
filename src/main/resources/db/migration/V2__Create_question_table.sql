CREATE TABLE `question` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(255) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  `creator` bigint(255) DEFAULT NULL,
  `comment_count` int(255) DEFAULT '0',
  `view_count` int(255) DEFAULT '0',
  `like_count` int(255) DEFAULT '0',
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8



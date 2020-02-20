CREATE TABLE `comment`
(
    `id`           bigint(20)                    NOT NULL AUTO_INCREMENT,
    `parent_id`    bigint(20)                    NOT NULL COMMENT '父类id',
    `type`         int(11)                       NOT NULL,
    `commentator`  bigint(255)                      NOT NULL COMMENT '评论人id',
    `gmt_create`   bigint(255)                   NOT NULL COMMENT '评论时间',
    `gmt_modified` bigint(255)                   NOT NULL COMMENT '更新时间',
    `like_count`   bigint(255) unsigned zerofill NOT NULL COMMENT '点赞数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8



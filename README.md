
#spring boot开始了

加油加油加油



#脚本
##创建user表
```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
```

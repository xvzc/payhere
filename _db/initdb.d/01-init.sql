CREATE DATABASE IF NOT EXISTS `payhere`;
USE `payhere`;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `receipt`;
DROP TABLE IF EXISTS `tag`;

CREATE TABLE IF NOT EXISTS `user` (
    `id`       BIGINT NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(191) NOT NULL,
    `password` VARCHAR(512) NOT NULL,
    `created`  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE (`email`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `tag` (
    `id`      BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `name`    VARCHAR(191) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    UNIQUE (`name`),
    INDEX index_name (`user_id`,`name`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `receipt` (
     `id`          BIGINT NOT NULL AUTO_INCREMENT,
     `user_id`     BIGINT NOT NULL,
     `tag_id`      BIGINT NOT NULL,
     `amount`      INT NOT NULL,
     `description` varchar(512),
     `created`     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `deleted`     varchar(1) NOT NULL DEFAULT 'N',

     PRIMARY KEY (`id`),
     FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
     FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
     INDEX index_name (`user_id`,`created`)
) ENGINE = InnoDB;

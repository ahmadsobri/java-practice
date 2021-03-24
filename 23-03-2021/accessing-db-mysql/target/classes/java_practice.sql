CREATE DATABASE
    IF NOT EXISTS `java_practice` CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `java_practice`;
CREATE TABLE IF NOT EXISTS `java_practice`.`author`
(
    `id`        INT NOT NULL AUTO_INCREMENT
    , `full_name` VARCHAR(255)
    , `age`       INT
    , PRIMARY KEY (`id`)
)
    ENGINE = INNODB
;

CREATE TABLE IF NOT EXISTS `java_practice`.`article`
(
    `id`          INT NOT NULL AUTO_INCREMENT
    , `title`       VARCHAR(255)
    , `description` TEXT
    , `created`     DATE NOT NULL
    , `author_id`   INT NOT NULL
    , PRIMARY KEY (`id`)
    , FOREIGN KEY (`author_id`) REFERENCES author(id)
)
    ENGINE = INNODB
;

CREATE TABLE IF NOT EXISTS `java_practice`.`comment`
(
    `id`          INT NOT NULL AUTO_INCREMENT
    , `name`       VARCHAR(255)
    , `message` TEXT
    , `created`     DATE NOT NULL
    , `is_show` BOOLEAN
    , `article_id`   INT NOT NULL
    , PRIMARY KEY (`id`)
    , FOREIGN KEY (`article_id`) REFERENCES article(id)
)
    ENGINE = INNODB
;
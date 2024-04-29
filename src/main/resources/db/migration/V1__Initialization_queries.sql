
CREATE DATABASE IF NOT EXISTS usermanagement;


CREATE TABLE user (
  `user_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email_id` VARCHAR(100) NOT NULL,
  `country_code` VARCHAR(5) NOT NULL,
  `mobile_number` BIGINT(12) NOT NULL,
  `password` LONGTEXT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);
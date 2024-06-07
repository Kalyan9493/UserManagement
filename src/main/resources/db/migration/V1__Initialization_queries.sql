
CREATE DATABASE IF NOT EXISTS usermanagement;


CREATE TABLE user (
  `user_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `email_id` VARCHAR(100) NOT NULL,
  `country_code` VARCHAR(5) NOT NULL,
  `mobile_number` BIGINT(12) NOT NULL,
  `password` LONGTEXT NOT NULL,
  `created_by` VARCHAR(100) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_by` VARCHAR(100) NULL,
  `updated_at` TIMESTAMP NULL,
  `is_deleted` CHAR(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);



CREATE TABLE role (
  `role_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`));

INSERT INTO role (`role_id`, `name`) VALUES ('1', 'USER');
INSERT INTO role (`role_id`, `name`) VALUES ('2', 'ADMIN');



CREATE TABLE `user_role` (
  `user_role_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NULL,
  `role_id` BIGINT(10) NULL,
   PRIMARY KEY (`user_role_id`),
   UNIQUE INDEX `user_role_id_UNIQUE` (`user_role_id` ASC) VISIBLE);
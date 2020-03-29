CREATE TABLE `hibernate_sequence` (
  `next_val` BIGINT DEFAULT NULL
);

CREATE TABLE `member` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) DEFAULT NULL,
	`cpf` VARCHAR(11) NOT NULL UNIQUE,
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `topic` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `session` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`voting_start` DATETIME NOT NULL,
	`voting_end` DATETIME NOT NULL,
	`topic_id` BIGINT NOT NULL UNIQUE,
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `session_fk0` (`topic_id`),
  CONSTRAINT `session_fk0` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
);

CREATE TABLE `vote` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`member_id` BIGINT NOT NULL UNIQUE,
	`topic_id` BIGINT NOT NULL UNIQUE,
  `result` bit(1) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vote_fk0` (`member_id`),
  KEY `vote_fk1` (`topic_id`),
  CONSTRAINT `vote_fk0` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `vote_fk1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
);

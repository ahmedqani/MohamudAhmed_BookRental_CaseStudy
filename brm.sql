
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Creating the BRM Schema
CREATE DATABASE IF NOT EXISTS `brm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `brm`;

-- Creating Book Table
CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `available` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `rented_by_id` bigint(20) DEFAULT NULL,
  `add_by_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  KEY `FK9mp2gwuyc2kcsiq6twpw7qpvh` (`rented_by_id`),
  KEY `FKo4tudv8vxx550hbqr98ldrt72` (`add_by_id`),
  CONSTRAINT `FK9mp2gwuyc2kcsiq6twpw7qpvh` FOREIGN KEY (`rented_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKo4tudv8vxx550hbqr98ldrt72` FOREIGN KEY (`add_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Add some Dummy Books to the DB
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `author`, `available`, `description`, `name`, `category_id`, `rented_by_id`, `add_by_id`) VALUES
	(1, 'Jamal ', b'1', 'lorem', 'Begening C++', 7, NULL, NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Creating Book-Rent Table
CREATE TABLE IF NOT EXISTS `book_rent` (
  `id` bigint(20) NOT NULL,
  `rent_date` datetime(6) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `free_date` datetime(6) DEFAULT NULL,
  `stil_rented` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ltc4gb931wt03s9h60am4i28` (`book_id`),
  KEY `FK69x5w3fiohmtvy4ui9bgy9wid` (`user_id`),
  CONSTRAINT `FK1ltc4gb931wt03s9h60am4i28` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK69x5w3fiohmtvy4ui9bgy9wid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- -- Creating Category Table
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL,
  `cat_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Adding Dummy Category
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `cat_name`, `name`) VALUES
	(1, 'Science', 'Science'),
	(2, 'Math', 'Math'),
	(7, 'Computer', 'Computer science'),
	(8, 'Phylosophy', 'Phylosophy');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Creating Role Table
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Adding Both Admin and User Roles
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `code`, `description`) VALUES
	(1, 'ROLE_ADMIN', 'ADMIN'),
	(2, 'ROLE_USER', 'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Creating the User Table
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `confirm_ppassword` varchar(255) DEFAULT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Adding Test Users
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `enabled`, `name`, `password`, `username`, `role_id`, `confirm_ppassword`, `confirm_password`) VALUES
	(1, 'admin@gmail.com', b'1', 'admin', '$2a$10$fIo1/l1XVFyONpg2CyYP8.svJk3PfBEfw57gaa0fCVGkLJkpXFWB6', 'admin@gmail.com', 1, NULL, NULL),
	(2, 'testuser@gmail.com', b'1', 'testUser', '$2a$10$by40MrcX1EOSNVeT3USl7uBlKUL9gix22zh1AKmAaSM5e3Ix49m5y', 'testuser@gmail.com', 2, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

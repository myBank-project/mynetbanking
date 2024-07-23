CREATE DATABASE  IF NOT EXISTS `mybanknetbanking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mybanknetbanking`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mybanknetbanking
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `account_number` int DEFAULT NULL,
  `account_type` enum('SAVINGS','CURRENT','LOAN','SALARY','BUSINESS') DEFAULT NULL,
  `customer_address` text,
  `nominee_name` varchar(255) DEFAULT NULL,
  `balance` int NOT NULL DEFAULT '0',
  `account_open_date` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_email` (`email`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1000,'Aman','yadav','aman.2024csit1089@kiet.edu',10006,'SAVINGS','matora','Sunil',0,NULL),(1002,'Abhay','yadav','amanyadav@gmail.com',NULL,'SAVINGS','matora','Sunil',0,NULL),(1003,'aryan','yadav','aryan@email.com',NULL,'SAVINGS',NULL,'sunil',0,NULL),(1004,'aman','aman','aman@email.com',NULL,'SAVINGS',NULL,'sumanth',0,NULL),(1005,'Aman','Yadav','abc@gmail.com',NULL,'SAVINGS',NULL,'sumanth',0,NULL),(1006,'abc','abc','abc@abc',NULL,'SAVINGS',NULL,'abc',0,NULL),(1008,'abc','abc','abcd@abc',NULL,'SAVINGS',NULL,'abc',0,NULL),(1009,'customerId','customerId','customerId@customerId',NULL,'SAVINGS',NULL,'customerId',0,NULL),(1011,'sunil','sunil','sunil@email.com',NULL,'SAVINGS',NULL,'sunil',0,NULL),(1012,'sunil','sunil','sunil1@email.com',NULL,'SAVINGS',NULL,'sunil',0,NULL),(1013,'sunil','sunil','sunil2@email.com',NULL,'SAVINGS',NULL,'sunil',0,NULL),(1014,'jyoti','jyoti','jyoti@gmail.com',NULL,'SAVINGS',NULL,'jyoti',0,NULL),(1015,'jyoti','jyoti','jyoti1@gmail.com',NULL,'SAVINGS',NULL,'jyoti',0,NULL),(1016,'jyoti','jyoti','jyoti2@gmail.com',NULL,'SAVINGS',NULL,'jyoti',0,NULL),(1017,'tara','tara','tara@tara',NULL,'SAVINGS',NULL,'tara',0,NULL),(1018,'tara','tara','tara3@tara',NULL,'SAVINGS',NULL,'tara',0,NULL),(1019,'aman','yadav','tara4@tara',10011,'SAVINGS','meerut','tara',99111,NULL),(1020,'mandir','mandir','mandir@email.com',10012,'BUSINESS',NULL,'shiva',0,NULL),(1021,'Aman','Yadav','abab@gmail.com',10013,'SAVINGS',NULL,'sumanth',0,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-23 16:19:02

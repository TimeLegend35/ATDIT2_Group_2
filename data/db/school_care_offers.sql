-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `care_offers`
--

DROP TABLE IF EXISTS `care_offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `care_offers` (
  `care_offer_id` int NOT NULL,
  `supervisor_employee_number` int DEFAULT NULL,
  `oldest_class_level` int DEFAULT NULL,
  `youngest_class_level` int DEFAULT NULL,
  `care_offer_name` varchar(255) DEFAULT NULL,
  `description` text,
  `places_available` int DEFAULT NULL,
  PRIMARY KEY (`care_offer_id`),
  KEY `supervisor_employee_number` (`supervisor_employee_number`),
  KEY `oldest_class_level` (`oldest_class_level`),
  KEY `youngest_class_level` (`youngest_class_level`),
  CONSTRAINT `care_offers_ibfk_1` FOREIGN KEY (`supervisor_employee_number`) REFERENCES `employees` (`employee_number`),
  CONSTRAINT `care_offers_ibfk_2` FOREIGN KEY (`oldest_class_level`) REFERENCES `class_levels` (`class_level`),
  CONSTRAINT `care_offers_ibfk_3` FOREIGN KEY (`youngest_class_level`) REFERENCES `class_levels` (`class_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `care_offers`
--

LOCK TABLES `care_offers` WRITE;
/*!40000 ALTER TABLE `care_offers` DISABLE KEYS */;
/*!40000 ALTER TABLE `care_offers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-13 18:57:02

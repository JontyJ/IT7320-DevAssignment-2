-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: parking_space
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fines`
--

DROP TABLE IF EXISTS `fines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fines` (
  `fine_id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) DEFAULT NULL,
  `number_plate` varchar(6) DEFAULT NULL,
  `timestamp_start` timestamp NULL DEFAULT NULL,
  `photo_start` longblob,
  `fineable_timestamp` timestamp NULL DEFAULT NULL,
  `fineable_evidence` longblob,
  `fine_amount` double DEFAULT NULL,
  PRIMARY KEY (`fine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fines`
--

LOCK TABLES `fines` WRITE;
/*!40000 ALTER TABLE `fines` DISABLE KEYS */;
/*!40000 ALTER TABLE `fines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_log`
--

DROP TABLE IF EXISTS `parking_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_space` varchar(4) DEFAULT NULL,
  `registration_number` varchar(6) DEFAULT NULL,
  `picture_arrival` longblob,
  `arrival_time` timestamp NULL DEFAULT NULL,
  `picture_left` longblob,
  `departure_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `log_id_UNIQUE` (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_log`
--

LOCK TABLES `parking_log` WRITE;
/*!40000 ALTER TABLE `parking_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_1`
--

DROP TABLE IF EXISTS `space_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_1` (
  `parking_space_id` varchar(4) NOT NULL,
  `registration_number` varchar(6) NOT NULL,
  `picture_arrival` longblob NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `picture_left` longblob,
  `departure_time` timestamp NULL DEFAULT NULL,
  `occupied` tinyint(1) NOT NULL,
  PRIMARY KEY (`parking_space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_1`
--

LOCK TABLES `space_1` WRITE;
/*!40000 ALTER TABLE `space_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `space_1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-14 15:25:19

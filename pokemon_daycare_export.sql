-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: pokemon_daycare
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon` (
  `pokemon_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `trainer_name` varchar(50) DEFAULT NULL,
  `days_stayed` int DEFAULT NULL,
  PRIMARY KEY (`pokemon_id`),
  CONSTRAINT `pokemon_chk_1` CHECK ((`level` between 1 and 100)),
  CONSTRAINT `pokemon_chk_2` CHECK ((`days_stayed` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (1,'Vulpix','Fire',5,'F','Tiffany',5),(2,'Togekiss','Fairy/Flying',25,'M','Henry',1),(3,'Snubbull','Fairy',3,'F','Nancy',4),(4,'Eevee','Normal',50,'M','Dan',10),(5,'Sizzlipede','Fire/Bug',40,'F','Ray',100),(6,'Espeon','Psychic',6,'M','Conner',10),(7,'Jigglypuff','Normal/Fairy',12,'F','Sarah',3),(8,'Lucario','Fighting/Steel',45,'M','Brent',8),(9,'Dratini','Dragon',30,'F','Leo',15),(10,'Meowth','Normal',9,'M','Cassie',2),(11,'Umbreon','Dark',55,'M','Claire',6),(12,'Leafeon','Grass',20,'F','Oliver',4),(13,'Gengar','Ghost/Poison',60,'M','Jake',12),(14,'Mawile','Steel/Fairy',28,'F','Kira',5),(15,'Sylveon','Fairy',35,'F','Naomi',9),(16,'Charizard','Fire/Flying',70,'M','Aiden',7),(17,'Altaria','Dragon/Flying',48,'F','Ruby',11),(18,'Flareon','Fire',25,'F','Lucas',3),(19,'Glaceon','Ice',33,'F','Paige',6),(20,'Ditto','Normal',10,'M','Steve',1);
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-04 12:34:44

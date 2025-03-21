-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projeto_contas
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
-- Table structure for table `parcela`
--

DROP TABLE IF EXISTS `parcela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcela` (
  `idparcela` int NOT NULL AUTO_INCREMENT,
  `data_vencimento` date NOT NULL,
  `numero_parcela` int NOT NULL,
  `valor_parcela` double NOT NULL,
  `idcontas` int NOT NULL,
  `status_parcela` varchar(45) NOT NULL,
  PRIMARY KEY (`idparcela`),
  KEY `fk_idcontas_contas` (`idcontas`),
  CONSTRAINT `fk_idcontas_contas` FOREIGN KEY (`idcontas`) REFERENCES `contas` (`idcontas`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcela`
--

LOCK TABLES `parcela` WRITE;
/*!40000 ALTER TABLE `parcela` DISABLE KEYS */;
INSERT INTO `parcela` VALUES (6,'2025-03-05',1,120,3,''),(7,'2025-03-20',1,300,4,''),(8,'2025-03-25',1,40,5,''),(9,'2025-03-12',1,200,6,''),(29,'2025-07-30',1,100,24,'pendente'),(30,'2025-08-30',2,100,24,'pendente'),(31,'2025-09-30',3,100,24,'pendente'),(32,'2025-10-30',4,100,24,'pendente'),(33,'2025-07-30',1,200,24,'pendente'),(34,'2025-08-30',2,200,24,'pendente'),(35,'2025-03-05',1,120,3,''),(36,'2025-05-31',2,727.5,24,'pendente'),(39,'2025-03-05',1,120,3,''),(41,'2025-03-05',1,120,3,''),(42,'2025-03-05',1,120,3,''),(43,'2025-07-31',1,242.5,25,'PENDENTE'),(44,'2025-08-30',2,242.5,25,'PENDENTE'),(45,'2025-09-30',3,242.5,25,'PENDENTE'),(46,'2025-04-26',1,30.75,26,'PENDENTE'),(47,'2025-05-26',2,30.75,26,'Pendente'),(48,'2025-06-26',3,30.75,26,'Pendente'),(49,'2025-07-26',4,30.75,26,'Pendente'),(50,'2025-05-28',1,6166.5,27,'PENDENTE'),(51,'2025-06-28',2,6166.5,27,'PAGO');
/*!40000 ALTER TABLE `parcela` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-21 20:18:58

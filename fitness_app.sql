-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: fitness_app
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `fitness_app`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `fitness_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fitness_app`;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
                            `id` int NOT NULL,
                            `description` varchar(255) NOT NULL,
                            `equipment` varchar(255) DEFAULT NULL,
                            `gifurl` varchar(255) DEFAULT NULL,
                            `name` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'Some Description',NULL,NULL,'New Exercise'),(3,'Fuck You','Fuck Your Equipment','https://buffer.com/cdn-cgi/image/w=1000,fit=contain,q=90,f=auto/library/content/images/size/w1200/2023/10/free-images.jpg','LOL Exercise!');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_image`
--

DROP TABLE IF EXISTS `exercise_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_image` (
                                  `exercise_id` int NOT NULL,
                                  `image` varchar(255) DEFAULT NULL,
                                  KEY `FKb0coppb3ht52jqljob0xmidiy` (`exercise_id`),
                                  CONSTRAINT `FKb0coppb3ht52jqljob0xmidiy` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_image`
--

LOCK TABLES `exercise_image` WRITE;
/*!40000 ALTER TABLE `exercise_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_seq`
--

DROP TABLE IF EXISTS `exercise_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_seq`
--

LOCK TABLES `exercise_seq` WRITE;
/*!40000 ALTER TABLE `exercise_seq` DISABLE KEYS */;
INSERT INTO `exercise_seq` VALUES (51);
/*!40000 ALTER TABLE `exercise_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_target_muscle`
--

DROP TABLE IF EXISTS `exercise_target_muscle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_target_muscle` (
                                          `exercise_id` int NOT NULL,
                                          `target_muscle` varchar(255) DEFAULT NULL,
                                          KEY `FK9g95qn3tsf53f8labrp8svjoq` (`exercise_id`),
                                          CONSTRAINT `FK9g95qn3tsf53f8labrp8svjoq` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_target_muscle`
--

LOCK TABLES `exercise_target_muscle` WRITE;
/*!40000 ALTER TABLE `exercise_target_muscle` DISABLE KEYS */;
INSERT INTO `exercise_target_muscle` VALUES (1,'SomeTargetMuscle');
/*!40000 ALTER TABLE `exercise_target_muscle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_user_review`
--

DROP TABLE IF EXISTS `exercise_user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_user_review` (
                                        `comment` varchar(255) DEFAULT NULL,
                                        `created_date_time` date NOT NULL,
                                        `rating` int NOT NULL,
                                        `user_id` int NOT NULL,
                                        `exercise_id` int NOT NULL,
                                        PRIMARY KEY (`exercise_id`,`user_id`),
                                        KEY `FKcpa0l83x1dgts716vtfh4qglb` (`user_id`),
                                        CONSTRAINT `FKcpa0l83x1dgts716vtfh4qglb` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                                        CONSTRAINT `FKoqvl5b9j55mc023sq4xhjck5y` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_user_review`
--

LOCK TABLES `exercise_user_review` WRITE;
/*!40000 ALTER TABLE `exercise_user_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `exercise_user_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal`
--

DROP TABLE IF EXISTS `goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal` (
                        `id` int NOT NULL,
                        `achievement_value` float NOT NULL,
                        `type` tinyint NOT NULL,
                        `followed_by_id` int NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK9pu7kr741x4f0i8sr7cngmdh7` (`followed_by_id`),
                        CONSTRAINT `FK9pu7kr741x4f0i8sr7cngmdh7` FOREIGN KEY (`followed_by_id`) REFERENCES `user` (`id`),
                        CONSTRAINT `goal_chk_1` CHECK ((`type` between 0 and 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal`
--

LOCK TABLES `goal` WRITE;
/*!40000 ALTER TABLE `goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_daily_progress`
--

DROP TABLE IF EXISTS `goal_daily_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal_daily_progress` (
                                       `date` date NOT NULL,
                                       `achieved_value` float NOT NULL,
                                       `goal_id` int NOT NULL,
                                       PRIMARY KEY (`date`,`goal_id`),
                                       KEY `FKn208j076677om2xshdf4wbwvh` (`goal_id`),
                                       CONSTRAINT `FKn208j076677om2xshdf4wbwvh` FOREIGN KEY (`goal_id`) REFERENCES `goal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_daily_progress`
--

LOCK TABLES `goal_daily_progress` WRITE;
/*!40000 ALTER TABLE `goal_daily_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `goal_daily_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal_seq`
--

DROP TABLE IF EXISTS `goal_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal_seq`
--

LOCK TABLES `goal_seq` WRITE;
/*!40000 ALTER TABLE `goal_seq` DISABLE KEYS */;
INSERT INTO `goal_seq` VALUES (1);
/*!40000 ALTER TABLE `goal_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
                         `id` int NOT NULL,
                         `created_date_time` datetime(6) NOT NULL,
                         `type` tinyint NOT NULL,
                         `created_by_id` int NOT NULL,
                         `goal_id` int DEFAULT NULL,
                         `workout_plan_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK4crf926w6lru2o2y53hah75eu` (`created_by_id`),
                         KEY `FKlwweee8uix7sgiso3j16s3fu6` (`goal_id`),
                         KEY `FK84b061cp2uw0qy04sc8vgdq97` (`workout_plan_id`),
                         CONSTRAINT `FK4crf926w6lru2o2y53hah75eu` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
                         CONSTRAINT `FK84b061cp2uw0qy04sc8vgdq97` FOREIGN KEY (`workout_plan_id`) REFERENCES `workout_plan` (`id`),
                         CONSTRAINT `FKlwweee8uix7sgiso3j16s3fu6` FOREIGN KEY (`goal_id`) REFERENCES `goal` (`id`),
                         CONSTRAINT `issue_chk_1` CHECK ((`type` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_seq`
--

DROP TABLE IF EXISTS `issue_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_seq`
--

LOCK TABLES `issue_seq` WRITE;
/*!40000 ALTER TABLE `issue_seq` DISABLE KEYS */;
INSERT INTO `issue_seq` VALUES (1);
/*!40000 ALTER TABLE `issue_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
                                `id` int NOT NULL,
                                `created_date_time` datetime(6) NOT NULL,
                                `message` varchar(255) NOT NULL,
                                `received_date_time` datetime(6) DEFAULT NULL,
                                `type` tinyint NOT NULL,
                                `goal_id` int DEFAULT NULL,
                                `receiver_id` int NOT NULL,
                                `workout_plan_id` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FKng3vol2nnt0i4ufnwu69rfchs` (`goal_id`),
                                KEY `FKmlidwdldgmdw67l7pbrval0un` (`receiver_id`),
                                KEY `FKmkcmjp3lvux7ckxjcelr6i61o` (`workout_plan_id`),
                                CONSTRAINT `FKmkcmjp3lvux7ckxjcelr6i61o` FOREIGN KEY (`workout_plan_id`) REFERENCES `workout_plan` (`id`),
                                CONSTRAINT `FKmlidwdldgmdw67l7pbrval0un` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
                                CONSTRAINT `FKng3vol2nnt0i4ufnwu69rfchs` FOREIGN KEY (`goal_id`) REFERENCES `goal` (`id`),
                                CONSTRAINT `notification_chk_1` CHECK ((`type` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_seq`
--

DROP TABLE IF EXISTS `notification_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_seq`
--

LOCK TABLES `notification_seq` WRITE;
/*!40000 ALTER TABLE `notification_seq` DISABLE KEYS */;
INSERT INTO `notification_seq` VALUES (1);
/*!40000 ALTER TABLE `notification_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL,
                        `address` varchar(255) DEFAULT NULL,
                        `blood_group` varchar(255) DEFAULT NULL,
                        `dob` date NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `emergency_contact` varchar(255) NOT NULL,
                        `first_name` varchar(255) NOT NULL,
                        `goal_notification` bit(1) NOT NULL,
                        `health_issues` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) NOT NULL,
                        `other_notification` bit(1) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `telephone_no` varchar(255) NOT NULL,
                        `type` tinyint NOT NULL,
                        `weight` float NOT NULL,
                        `workout_plan_notification` bit(1) NOT NULL,
                        `following_workout_plan_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
                        KEY `FKk8dry75m3lpbpf1ds0am725ks` (`following_workout_plan_id`),
                        CONSTRAINT `FKk8dry75m3lpbpf1ds0am725ks` FOREIGN KEY (`following_workout_plan_id`) REFERENCES `workout_plan` (`id`),
                        CONSTRAINT `user_chk_1` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Damunupola,Kegalle','B+','1998-06-06','buddhima@email.com','3847340','LOL',_binary '','No','Tharaka',_binary '','pubududf','076549232',1,67.6,_binary '',NULL),(152,'Homeless',NULL,'2024-02-05','buddhimakaveeshwara@gmail.com','NONO','Buddhima',_binary '',NULL,'Zoysa',_binary '','ABCD','No Contact',1,0,_binary '',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_exercise_log`
--

DROP TABLE IF EXISTS `user_exercise_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_exercise_log` (
                                     `date_time` datetime(6) NOT NULL,
                                     `rep_count` int NOT NULL,
                                     `set_count` int NOT NULL,
                                     `user_id` int NOT NULL,
                                     `exercise_id` int NOT NULL,
                                     PRIMARY KEY (`date_time`,`exercise_id`,`user_id`),
                                     KEY `FK7ssvwhvfpq1atd9dgb8kyxgqa` (`exercise_id`),
                                     KEY `FKccmblk3u984y91r216x5wyeg2` (`user_id`),
                                     CONSTRAINT `FK7ssvwhvfpq1atd9dgb8kyxgqa` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
                                     CONSTRAINT `FKccmblk3u984y91r216x5wyeg2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_exercise_log`
--

LOCK TABLES `user_exercise_log` WRITE;
/*!40000 ALTER TABLE `user_exercise_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_exercise_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_logging_log`
--

DROP TABLE IF EXISTS `user_logging_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_logging_log` (
                                    `logged_in_date_time` datetime(6) NOT NULL,
                                    `logged_out_date_time` datetime(6) DEFAULT NULL,
                                    `user_id` int NOT NULL,
                                    PRIMARY KEY (`logged_in_date_time`,`user_id`),
                                    KEY `FKhjcbbdfptjuqrv2a0riwwh2k0` (`user_id`),
                                    CONSTRAINT `FKhjcbbdfptjuqrv2a0riwwh2k0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logging_log`
--

LOCK TABLES `user_logging_log` WRITE;
/*!40000 ALTER TABLE `user_logging_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_logging_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (251);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_exercise`
--

DROP TABLE IF EXISTS `workout_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_exercise` (
                                    `rep_count` int NOT NULL,
                                    `set_count` int NOT NULL,
                                    `exercise_id` int NOT NULL,
                                    `workout_plan_id` int NOT NULL,
                                    PRIMARY KEY (`exercise_id`,`workout_plan_id`),
                                    KEY `FKin3pytqteeypwglaax1wh30gq` (`workout_plan_id`),
                                    CONSTRAINT `FKalytxvdcpsg2e2oo8ihk55dm2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
                                    CONSTRAINT `FKin3pytqteeypwglaax1wh30gq` FOREIGN KEY (`workout_plan_id`) REFERENCES `workout_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_exercise`
--

LOCK TABLES `workout_exercise` WRITE;
/*!40000 ALTER TABLE `workout_exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `workout_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_exercise_schedule`
--

DROP TABLE IF EXISTS `workout_exercise_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_exercise_schedule` (
                                             `workout_exercise_exercise_id` int NOT NULL,
                                             `workout_exercise_workout_plan_id` int NOT NULL,
                                             `schedule` int DEFAULT NULL,
                                             KEY `FKrkuheprmtlnv6vfjek6omxh3x` (`workout_exercise_exercise_id`,`workout_exercise_workout_plan_id`),
                                             CONSTRAINT `FKrkuheprmtlnv6vfjek6omxh3x` FOREIGN KEY (`workout_exercise_exercise_id`, `workout_exercise_workout_plan_id`) REFERENCES `workout_exercise` (`exercise_id`, `workout_plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_exercise_schedule`
--

LOCK TABLES `workout_exercise_schedule` WRITE;
/*!40000 ALTER TABLE `workout_exercise_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `workout_exercise_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_plan`
--

DROP TABLE IF EXISTS `workout_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_plan` (
                                `id` int NOT NULL,
                                `created_date` date NOT NULL,
                                `duration` int NOT NULL,
                                `is_public` bit(1) NOT NULL,
                                `name` varchar(255) NOT NULL,
                                `created_by_id` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FK1q8at57p2ftcdaf64x1ld4ghu` (`created_by_id`),
                                CONSTRAINT `FK1q8at57p2ftcdaf64x1ld4ghu` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_plan`
--

LOCK TABLES `workout_plan` WRITE;
/*!40000 ALTER TABLE `workout_plan` DISABLE KEYS */;
INSERT INTO `workout_plan` VALUES (0,'2024-02-01',0,_binary '','Test Workout',NULL);
/*!40000 ALTER TABLE `workout_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_plan_seq`
--

DROP TABLE IF EXISTS `workout_plan_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_plan_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_plan_seq`
--

LOCK TABLES `workout_plan_seq` WRITE;
/*!40000 ALTER TABLE `workout_plan_seq` DISABLE KEYS */;
INSERT INTO `workout_plan_seq` VALUES (1);
/*!40000 ALTER TABLE `workout_plan_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_plan_user_review`
--

DROP TABLE IF EXISTS `workout_plan_user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_plan_user_review` (
                                            `comment` varchar(255) DEFAULT NULL,
                                            `created_date_time` datetime(6) NOT NULL,
                                            `rating` int NOT NULL,
                                            `user_id` int NOT NULL,
                                            `workout_plan_id` int NOT NULL,
                                            PRIMARY KEY (`user_id`,`workout_plan_id`),
                                            KEY `FKiffqpdfxyoo880jmlmhudiias` (`workout_plan_id`),
                                            CONSTRAINT `FKiffqpdfxyoo880jmlmhudiias` FOREIGN KEY (`workout_plan_id`) REFERENCES `workout_plan` (`id`),
                                            CONSTRAINT `FKn5wsdwhoxlrl817roksm3iopq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_plan_user_review`
--

LOCK TABLES `workout_plan_user_review` WRITE;
/*!40000 ALTER TABLE `workout_plan_user_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `workout_plan_user_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 11:14:55

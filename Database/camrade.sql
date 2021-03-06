-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: camrade
-- ------------------------------------------------------
-- Server version	5.5.41-log

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
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio` (
  `audio_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `audio_path` varchar(1000) NOT NULL,
  `permitted_persons_id` varchar(20000) NOT NULL,
  `added_date` datetime NOT NULL,
  PRIMARY KEY (`audio_id`),
  KEY `audio_owner_id_idx` (`owner_id`),
  CONSTRAINT `audio_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES (1,20,'audio_path','10,12','2017-02-12 00:00:00'),(2,23,'audio_path','10,12','2017-02-12 00:00:00');
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_from` int(11) NOT NULL,
  `chat_to` int(11) NOT NULL,
  `message` mediumtext,
  PRIMARY KEY (`chat_id`),
  KEY `chat_chat_from_idx` (`chat_from`),
  KEY `chat_chat_to_idx` (`chat_to`),
  CONSTRAINT `chat_chat_from` FOREIGN KEY (`chat_from`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `chat_chat_to` FOREIGN KEY (`chat_to`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `document_path` varchar(1000) NOT NULL,
  `permitted_persons_id` varchar(20000) NOT NULL,
  `added_date` datetime NOT NULL,
  PRIMARY KEY (`document_id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `document_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,20,'document_path','10,12','2017-02-12 00:00:00'),(2,23,'document_path','10,12','2017-02-12 00:00:00');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_from` int(11) NOT NULL,
  `request_to` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `request_request_from_idx` (`request_from`),
  KEY `request_request_to_idx` (`request_to`),
  CONSTRAINT `request_request_from` FOREIGN KEY (`request_from`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `request_request_to` FOREIGN KEY (`request_to`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
INSERT INTO `friend_request` VALUES (22,21,22,'pending');
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `friend_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `friend_of` int(11) NOT NULL,
  PRIMARY KEY (`friend_id`),
  KEY `friend_user_id_idx` (`user_id`),
  KEY `friend_friend_of_idx` (`friend_of`),
  CONSTRAINT `friend_friend_of` FOREIGN KEY (`friend_of`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `friend_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (11,22,23),(12,22,24),(13,22,25),(14,25,23),(15,27,23),(16,23,24),(17,23,21);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `image_path` varchar(1000) NOT NULL,
  `permitted_persons_id` varchar(20000) NOT NULL,
  `added_date` datetime NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,20,'image_path','10,12','2017-02-12 00:00:00'),(2,23,'image_path','10,12','2017-02-12 00:00:00');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `notify_from` int(11) NOT NULL,
  `notify_to` int(11) NOT NULL,
  `notify_message` varchar(1000) NOT NULL,
  `added_date` datetime NOT NULL,
  `status` varchar(10) NOT NULL,
  `notification_from` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `notify_from_idx` (`notify_from`),
  KEY `notify_to_idx` (`notify_to`),
  CONSTRAINT `notification_from` FOREIGN KEY (`notify_from`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `notify_to` FOREIGN KEY (`notify_to`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (2,22,23,'suresh suresh accepted your friend request','2017-07-20 12:29:04','unseen',NULL),(3,22,24,'suresh suresh accepted your friend request','2017-07-20 12:29:08','unseen',NULL),(4,22,25,'suresh suresh accepted your friend request','2017-07-20 12:29:11','unseen',NULL),(5,25,23,'Suresh Kani accepted your friend request','2017-07-20 12:31:56','unseen',NULL),(6,27,23,'Suresh Raj accepted your friend request','2017-07-20 12:32:08','unseen',NULL),(7,23,24,'Suresh Kumar S accepted your friend request','2017-07-20 12:36:32','unseen',NULL),(8,23,21,'Suresh Kumar S accepted your friend request','2017-07-20 12:38:11','unseen',NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `alter_name` varchar(45) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `phone_no` varchar(12) NOT NULL,
  `birth_date` datetime NOT NULL,
  `gender` varchar(6) NOT NULL,
  `profile_picture` varchar(1000) DEFAULT NULL,
  `cover_image` varchar(1000) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `quotes` varchar(2000) DEFAULT NULL,
  `about` varchar(2000) DEFAULT NULL,
  `work` varchar(150) DEFAULT NULL,
  `school_name` varchar(150) DEFAULT NULL,
  `college_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'www','www','wwwwww','','wwwwww','ww@ww.ww','1111111111','2017-07-13 00:00:00','male','20/Profile_picture.jpg',NULL,'','','','','',''),(21,'sssss','sssss','ssssss','','ssssss','sss@ss.ss','9999999999','2017-07-12 00:00:00','male','21/Profile_picture.jpg','21/Cover_image.jpg','','','','','',''),(22,'suresh','suresh','111111','iamsuresh','111111','11@aa.aaa','1111111111','2017-07-19 00:00:00','male','22/Profile_picture.jpg','22/Cover_image.jpg','','','','','',''),(23,'Suresh','Kumar S','suresh33','SKARPS','qqqqqq','mailtosureshkdm@gmail.co','9876543210','2017-07-20 00:00:00','male','23/Profile_picture.jpg','23/Cover_image.jpg','Test address','My quotes will be here1q','I am a good boy..','test office','test school','testcollege'),(24,'Ram','Suresh','ramsuresh','','111111','klnknnnnnlnl@jk.sds','9876543210','2017-07-14 00:00:00','male','24/Profile_picture.jpg','24/Cover_image.jpg','','','','','',''),(25,'Suresh','Kani','sureshkani','I am kani','111111','dwd@jbj.ww','9876543210','2017-07-20 00:00:00','male','25/Profile_picture.jpg','25/Cover_image.jpg','','','','','',''),(26,'Kani','Suresh','kanisuresh','KaniSuresh','111111','bjb@hjb.jgjg','9876543210','2017-07-21 00:00:00','male','26/Profile_picture.jpg','26/Cover_image.jpg','','','','','',''),(27,'Suresh','Raj','sureshraj','','111111','dw@hvjh.jf','9876543210','2017-07-19 00:00:00','male','27/Profile_picture.jpg','27/Cover_image.jpg','','','','','',''),(28,'Suresh','Kumar','sureshkumar','','111111','jkjn@jhj.jh','9876543210','2017-07-13 00:00:00','male','28/Profile_picture.jpg','28/Cover_image.jpg','','','','','','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `video_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `video_path` varchar(1000) NOT NULL,
  `permitted_persons_id` varchar(20000) NOT NULL,
  `added_date` datetime NOT NULL,
  PRIMARY KEY (`video_id`),
  KEY `video_owner_id_idx` (`owner_id`),
  CONSTRAINT `video_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,20,'video_path','10,12','2017-02-12 00:00:00'),(2,23,'video_path','10,12','2017-02-12 00:00:00');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-24 12:39:05

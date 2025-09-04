use smts_baza;

/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.8.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: smts_baza
-- ------------------------------------------------------
-- Server version	11.8.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Temporary table structure for view `Detalji_Lansiranja`
--

DROP TABLE IF EXISTS `Detalji_Lansiranja`;
/*!50001 DROP VIEW IF EXISTS `Detalji_Lansiranja`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Detalji_Lansiranja` AS SELECT
 1 AS `satelit`,
  1 AS `vrijeme_lansiranja`,
  1 AS `raketa_nosac`,
  1 AS `misija`,
  1 AS `mjesto_lansiranja` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Komunikacija`
--

DROP TABLE IF EXISTS `Komunikacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Komunikacija` (
  `komunikacija_id` int(11) NOT NULL AUTO_INCREMENT,
  `satelit_id` int(11) DEFAULT NULL,
  `stanica_id` int(11) DEFAULT NULL,
  `datum_komunikacije` date DEFAULT NULL,
  `tip_komunikacije` varchar(100) DEFAULT NULL,
  `sadrzaj_poruke` text DEFAULT NULL,
  PRIMARY KEY (`komunikacija_id`),
  KEY `satelit_id` (`satelit_id`),
  KEY `stanica_id` (`stanica_id`),
  CONSTRAINT `Komunikacija_ibfk_1` FOREIGN KEY (`satelit_id`) REFERENCES `Satelit` (`satelit_id`),
  CONSTRAINT `Komunikacija_ibfk_2` FOREIGN KEY (`stanica_id`) REFERENCES `Zemaljska_Stanica` (`stanica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Komunikacija`
--

LOCK TABLES `Komunikacija` WRITE;
/*!40000 ALTER TABLE `Komunikacija` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Komunikacija` VALUES
(1,2,1,'2025-08-10','Telemetry','Slanje telemetrijskih podataka. Satelit u dobrom stanju.'),
(2,2,3,'2025-08-10','Scientific Data','Slanje slika i naučnih podataka s teleskopa.'),
(3,1,1,'2025-08-29','Health Check','Provjera sistema. Sve funkcionira u redu.'),
(4,3,2,'2025-08-29','Orbital Maneuver Command','Izvršavanje naredbe za korekciju orbite.'),
(5,4,3,'2025-08-29','Scientific Data','Slanje novih podataka o magnetskom polju Zemlje.'),
(6,5,4,'2025-08-29','Telemetry','Prijenos telemetrije. Potrošnja energije unutar normalnih parametara.'),
(7,6,5,'2025-08-29','Software Update','Pokretanje procedure za ažuriranje softvera na satelitu.'),
(8,12,6,'2025-08-29','Scientific Data','Slanje fotografija sa površine Mjeseca.'),
(9,13,1,'2025-08-30','Health Check','Rutinska provjera instrumenata. Sve ispravno.'),
(10,14,2,'2025-08-30','Scientific Data','Prijenos podataka sa orbite oko Mjeseca.'),
(11,15,3,'2025-08-30','Telemetry','Slanje podataka o temperaturi i pritisku.'),
(12,16,4,'2025-08-30','Orbital Maneuver Command','Ažuriranje putanje za bolju navigaciju.'),
(13,17,5,'2025-08-30','Health Check','Provjera komunikacijskih antena. Status: stabilno.'),
(14,18,6,'2025-08-30','Scientific Data','Slanje UV spektra sa Merkura.'),
(15,19,1,'2025-08-30','Telemetry','Prijenos podataka iz međuzvjezdanog prostora.'),
(16,20,2,'2025-08-30','Orbital Maneuver Command','Naredba za promjenu pozicije. Uspješno izvršena.'),
(17,21,3,'2025-08-30','Health Check','Provjera solarnih panela. Proizvodnja energije normalna.'),
(18,22,4,'2025-08-30','Scientific Data','Slanje novih, oštrih slika galaksija.'),
(19,23,5,'2025-08-31','Telemetry','Prijenos podataka o atmosferskim uvjetima na Marsu.'),
(20,24,6,'2025-08-31','Orbital Maneuver Command','Korekcija putanje rovera.'),
(21,25,1,'2025-08-31','Health Check','Provjera navigacijskog sistema. GPS signali jaki.'),
(22,26,2,'2025-08-31','Scientific Data','Slanje panoramskih slika sa površine Marsa.'),
(23,27,3,'2025-08-31','Telemetry','Prijenos podataka o meteorološkim uvjetima.'),
(24,28,4,'2025-09-01','Orbital Maneuver Command','Naredba za ulazak u nižu orbitu.'),
(25,29,5,'2025-09-01','Health Check','Rutinska provjera senzora. Nema anomalija.'),
(26,30,6,'2025-09-01','Scientific Data','Slanje infracrvenih podataka.'),
(27,31,1,'2025-09-01','Telemetry','Prijenos dijagnostičkih podataka.'),
(28,32,2,'2025-09-01','Orbital Maneuver Command','Komanda za paljenje motora. Uspješno.'),
(29,33,3,'2025-09-02','Health Check','Svi podsistemi su online i rade u nominalnom režimu.'),
(30,34,4,'2025-09-02','Scientific Data','Prijenos podataka sa orbite oko Jupitera.'),
(31,35,5,'2025-09-02','Telemetry','Slanje podataka o potrošnji pogonskog goriva.'),
(32,36,6,'2025-09-02','Orbital Maneuver Command','Naredba za usporavanje satelita.'),
(33,1,1,'2025-08-30','Software Update','Potrebno azuriranje softvera.'),
(34,1,4,'2025-09-03','Scientific Data','asdjaKDHkad');
/*!40000 ALTER TABLE `Komunikacija` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Temporary table structure for view `Komunikacija_Stanica_Satelit`
--

DROP TABLE IF EXISTS `Komunikacija_Stanica_Satelit`;
/*!50001 DROP VIEW IF EXISTS `Komunikacija_Stanica_Satelit`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Komunikacija_Stanica_Satelit` AS select `k`.`komunikacija_id` AS `komunikacija_id`,`zs`.`naziv` AS `naziv_stanice`,`zs`.`lokacija` AS `lokacija_stanice`,`s`.`naziv` AS `naziv_satelita`,`k`.`datum_komunikacije` AS `datum_komunikacije`,`k`.`tip_komunikacije` AS `tip_komunikacije`,`k`.`sadrzaj_poruke` AS `sadrzaj_poruke` from ((`Komunikacija` `k` join `Zemaljska_Stanica` `zs` on(`k`.`stanica_id` = `zs`.`stanica_id`)) join `Satelit` `s` on(`k`.`satelit_id` = `s`.`satelit_id`)) */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Lansiranja_Po_Proizvodjacu`
--

DROP TABLE IF EXISTS `Lansiranja_Po_Proizvodjacu`;
/*!50001 DROP VIEW IF EXISTS `Lansiranja_Po_Proizvodjacu`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Lansiranja_Po_Proizvodjacu` AS SELECT
 1 AS `proizvodjac`,
  1 AS `raketa_nosac`,
  1 AS `broj_lansiranja` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Lansiranje`
--

DROP TABLE IF EXISTS `Lansiranje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lansiranje` (
  `lansiranje_id` int(11) NOT NULL AUTO_INCREMENT,
  `vrijeme_lansiranja` datetime DEFAULT NULL,
  `raketa_id` int(11) DEFAULT NULL,
  `misija_id` int(11) DEFAULT NULL,
  `satelit_id` int(11) DEFAULT NULL,
  `mjesto_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lansiranje_id`),
  KEY `raketa_id` (`raketa_id`),
  KEY `misija_id` (`misija_id`),
  KEY `satelit_id` (`satelit_id`),
  KEY `mjesto_id` (`mjesto_id`),
  CONSTRAINT `Lansiranje_ibfk_1` FOREIGN KEY (`raketa_id`) REFERENCES `Raketa_Nosac` (`raketa_id`),
  CONSTRAINT `Lansiranje_ibfk_2` FOREIGN KEY (`misija_id`) REFERENCES `Misija` (`misija_id`),
  CONSTRAINT `Lansiranje_ibfk_3` FOREIGN KEY (`satelit_id`) REFERENCES `Satelit` (`satelit_id`),
  CONSTRAINT `Lansiranje_ibfk_4` FOREIGN KEY (`mjesto_id`) REFERENCES `Mjesto_Lansiranja` (`mjesto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lansiranje`
--

LOCK TABLES `Lansiranje` WRITE;
/*!40000 ALTER TABLE `Lansiranje` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Lansiranje` VALUES
(1,'2019-05-24 02:30:00',1,3,1,1),
(2,'2022-11-16 06:47:00',3,1,3,1),
(3,'2025-10-26 12:00:00',4,1,5,4),
(4,'2026-01-15 10:00:00',1,3,6,1),
(5,'2023-03-10 14:45:00',2,4,12,2),
(6,'2024-06-21 08:20:00',5,5,13,3),
(7,'2021-09-05 18:15:00',3,6,14,1),
(8,'2020-01-20 03:00:00',1,7,15,4),
(9,'2025-05-18 22:10:00',4,8,16,2),
(10,'2023-11-30 09:40:00',2,9,17,5),
(11,'2022-07-12 11:55:00',1,10,18,1),
(12,'2024-02-02 07:05:00',5,11,19,3),
(13,'2021-04-19 16:30:00',3,12,20,4),
(14,'2020-08-08 01:25:00',4,4,21,2),
(15,'2025-09-01 10:50:00',1,5,22,1),
(16,'2023-01-14 13:00:00',2,6,23,5),
(17,'2024-10-25 20:20:00',5,7,24,3),
(18,'2021-05-17 06:10:00',3,8,25,4),
(19,'2022-08-09 15:45:00',4,9,26,2),
(26,'2025-11-05 18:00:00',3,13,13,4),
(27,'2026-02-12 09:30:00',4,14,14,5),
(28,'2027-04-22 15:45:00',5,15,15,1),
(29,'2028-08-01 03:00:00',2,16,16,2),
(30,'2029-01-08 11:20:00',1,17,17,3),
(31,'2030-03-19 19:15:00',6,18,18,4),
(32,'2031-06-25 08:50:00',3,19,19,5),
(33,'2032-09-02 21:05:00',4,20,20,1),
(34,'2033-11-14 06:40:00',5,13,21,2),
(35,'2034-02-19 14:00:00',2,14,22,3),
(36,'2035-05-27 10:10:00',1,15,23,4),
(37,'2036-07-31 04:30:00',6,16,24,5),
(38,'2037-10-10 12:00:00',3,17,25,1),
(39,'2038-12-18 09:00:00',4,18,26,2),
(40,'2039-03-22 17:25:00',5,19,27,3),
(41,'2040-06-08 16:45:00',2,20,28,4),
(42,'2041-09-15 05:00:00',1,13,29,5),
(43,'2042-12-01 13:30:00',6,14,30,1),
(44,'2043-04-04 11:00:00',3,15,31,2),
(45,'2044-07-09 22:50:00',4,16,32,3),
(46,'1972-12-07 12:33:00',1,21,21,1),
(47,'1973-07-28 11:10:00',1,22,22,1),
(48,'1975-06-08 02:38:00',4,23,23,5),
(49,'1973-11-03 01:45:00',2,24,24,2),
(50,'1982-04-19 19:45:00',3,25,25,5),
(51,'2004-03-02 07:17:00',5,26,26,3),
(52,'2006-01-19 14:00:00',6,27,27,4),
(53,'2007-09-27 11:20:00',6,28,28,2),
(54,'2009-03-07 03:49:00',4,29,29,5),
(55,'2003-05-09 04:30:00',3,30,30,1),
(58,'2025-09-03 10:30:00',1,30,1,1);
/*!40000 ALTER TABLE `Lansiranje` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_uca1400_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`smts`@`%`*/ /*!50003 TRIGGER nakon_lansiranja_azuriraj_status
AFTER INSERT ON Lansiranje
FOR EACH ROW
BEGIN
    UPDATE Misija
    SET status = 'Aktivna'
    WHERE misija_id = NEW.misija_id AND status = 'Planirana';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Misija`
--

DROP TABLE IF EXISTS `Misija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Misija` (
  `misija_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `datum_pocetka` date DEFAULT NULL,
  `datum_kraja` date DEFAULT NULL,
  `cilj_misije` text DEFAULT NULL,
  `status` enum('Aktivna','Završena','Planirana') DEFAULT NULL,
  `broj_satelita` int(11) DEFAULT 0,
  PRIMARY KEY (`misija_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Misija`
--

LOCK TABLES `Misija` WRITE;
/*!40000 ALTER TABLE `Misija` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Misija` VALUES
(1,'Artemis 1','2022-11-16','2022-12-11','Testiranje Orion svemirske letjelice.','Završena',5),
(2,'Hubble','1990-04-24',NULL,'Snimanje svemira i slanje slika na Zemlju.','Aktivna',4),
(3,'Starlink 1','2019-05-24','2020-01-01','Uspostavljanje globalne mreže satelita za internet.','Završena',3),
(4,'Apollo 11','1969-07-16','1969-07-24','Prva misija s ljudskom posadom na Mjesec.','Završena',3),
(5,'James Webb','2021-12-25',NULL,'Astronomski teleskop nasljednik Hubble-a.','Aktivna',4),
(6,'Chandrayaan-2','2019-07-22','2019-09-07','Indijska misija za istraživanje Mjeseca.','Završena',3),
(7,'Mars Perseverance','2020-07-30',NULL,'Istraživanje Marsa roverom Perseverance.','Aktivna',3),
(8,'Galileo','2011-10-21',NULL,'Evropski navigacioni sistem.','Aktivna',3),
(9,'Tianwen-1','2020-07-23',NULL,'Kineska orbiter-rover misija na Mars.','Aktivna',3),
(10,'BepiColombo','2018-10-20',NULL,'ESA-JAXA misija za Merkur.','Aktivna',3),
(11,'Voyager 1','1977-09-05',NULL,'Istraživanje Sunčevog sistema i međuzvjezdanog prostora.','Aktivna',3),
(12,'GPS III SV01','2018-12-23',NULL,'Modernizacija GPS satelita.','Aktivna',3),
(13,'Juno','2011-08-05',NULL,'Istraživanje Jupiterovih polarnih regija, atmosfere i magnetosfere.','Aktivna',1),
(14,'Cassini-Huygens','1997-10-15','2017-09-15','Istraživanje Saturna i njegovih prstenova te Titana.','Završena',1),
(15,'Parker Solar Probe','2018-08-12',NULL,'Istraživanje vanjske korone Sunca.','Aktivna',1),
(16,'InSight','2018-05-05','2022-12-19','Istraživanje unutrašnjosti Marsa i potresa na Marsu.','Završena',1),
(17,'Chang`e 4','2018-12-07',NULL,'Kineska misija za istraživanje daleke strane Mjeseca.','Aktivna',2),
(18,'ExoMars Trace Gas Orbiter','2016-03-14',NULL,'Traženje tragova gasova u Marsovoj atmosferi.','Aktivna',1),
(19,'TESS','2018-04-18',NULL,'Traženje egzoplaneta metodom tranzita.','Aktivna',1),
(20,'Transiting Exoplanet Survey Satellite','2018-04-18',NULL,'Traženje egzoplaneta','Aktivna',1),
(21,'Apollo 17','1972-12-07','1972-12-19','Posljednja misija na Mjesec s ljudskom posadom.','Završena',3),
(22,'Skylab 3','1973-07-28','1973-09-25','Druga misija s posadom na svemirsku stanicu Skylab.','Završena',1),
(23,'Venera 9','1975-06-08','1975-10-22','Proučavanje Venere, uključujući slijetanje i slanje prvih slika s njene površine.','Završena',1),
(24,'Mariner 10','1973-11-03','1975-03-24','Istraživanje Merkura i Venere.','Završena',1),
(25,'Salyut 7','1982-04-19','1991-02-07','Sovjetska orbitalna stanica za istraživanje i testiranje.','Završena',1),
(26,'Rosetta','2004-03-02','2016-09-30','Proučavanje komete 67P i spuštanje lendera Philae na njenu površinu.','Završena',2),
(27,'New Horizons','2006-01-19','2015-07-14','Prvo istraživanje Plutona i Kuiperovog pojasa.','Završena',1),
(28,'Dawn','2007-09-27','2018-11-01','Istraživanje Veste i Ceresa, dva najveća objekta u asteroidnom pojasu.','Završena',1),
(29,'Kepler','2009-03-07','2018-11-15','Otkrivanje egzoplaneta u Mliječnoj stazi.','Završena',1),
(30,'Hayabusa','2003-05-09','2010-06-13','Misija vraćanja uzoraka s asteroida Itokawa.','Aktivna',1);
/*!40000 ALTER TABLE `Misija` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Temporary table structure for view `Misije_Detalji_Partnera`
--

DROP TABLE IF EXISTS `Misije_Detalji_Partnera`;
/*!50001 DROP VIEW IF EXISTS `Misije_Detalji_Partnera`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Misije_Detalji_Partnera` AS SELECT
 1 AS `naziv_misije`,
  1 AS `status_misije`,
  1 AS `zemlja`,
  1 AS `operater`,
  1 AS `uloga_operatera` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Misije_Po_Statusu`
--

DROP TABLE IF EXISTS `Misije_Po_Statusu`;
/*!50001 DROP VIEW IF EXISTS `Misije_Po_Statusu`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Misije_Po_Statusu` AS SELECT
 1 AS `misija_id`,
  1 AS `naziv_misije`,
  1 AS `datum_pocetka`,
  1 AS `datum_kraja`,
  1 AS `cilj_misije`,
  1 AS `status`,
  1 AS `status_detalji` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Misije_Po_Zemlji`
--

DROP TABLE IF EXISTS `Misije_Po_Zemlji`;
/*!50001 DROP VIEW IF EXISTS `Misije_Po_Zemlji`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Misije_Po_Zemlji` AS SELECT
 1 AS `status`,
  1 AS `zemlja`,
  1 AS `broj_misija` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Mjesto_Lansiranja`
--

DROP TABLE IF EXISTS `Mjesto_Lansiranja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Mjesto_Lansiranja` (
  `mjesto_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `država` varchar(100) DEFAULT NULL,
  `geografska_duzina` decimal(9,6) DEFAULT NULL,
  `geografska_sirina` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`mjesto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mjesto_Lansiranja`
--

LOCK TABLES `Mjesto_Lansiranja` WRITE;
/*!40000 ALTER TABLE `Mjesto_Lansiranja` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Mjesto_Lansiranja` VALUES
(1,'Cape Canaveral','SAD',-80.604000,28.562000),
(2,'Bajkonur','Kazahstan',63.342000,45.965000),
(3,'Xichang','Kina',102.029000,28.246000),
(4,'Kourou','Francuska Gvajana',-52.768000,5.234000),
(5,'Tanegashima Space Center','Japan',130.966000,30.401000),
(6,'Satish Dhawan Space Centre','Indija',80.230000,13.733000),
(7,'Vandenberg Space Force Base','SAD',-120.572000,34.742000),
(8,'Wenchang Space Launch Site','Kina',110.951000,19.614000),
(9,'Esrange Space Center','Švedska',21.104000,67.890000),
(10,'Mahia Peninsula','Novi Zeland',177.864000,-39.262000);
/*!40000 ALTER TABLE `Mjesto_Lansiranja` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Operater`
--

DROP TABLE IF EXISTS `Operater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Operater` (
  `operater_id` int(11) NOT NULL AUTO_INCREMENT,
  `ime_operatera` varchar(255) NOT NULL,
  `tip_organizacije` varchar(100) DEFAULT NULL,
  `zemlja_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`operater_id`),
  KEY `zemlja_id` (`zemlja_id`),
  CONSTRAINT `Operater_ibfk_1` FOREIGN KEY (`zemlja_id`) REFERENCES `Zemlja` (`zemlja_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Operater`
--

LOCK TABLES `Operater` WRITE;
/*!40000 ALTER TABLE `Operater` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Operater` VALUES
(1,'NASA','Vladina',1),
(2,'Roscosmos','Vladina',2),
(3,'SpaceX','Komercijalna',1),
(4,'JAXA','Vladina',5),
(5,'ISRO','Vladina',6),
(6,'Roscosmos',NULL,NULL),
(7,'JAXA',NULL,NULL),
(8,'UK Space Agency',NULL,NULL),
(9,'ESA',NULL,NULL),
(10,'CSA',NULL,NULL);
/*!40000 ALTER TABLE `Operater` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Orbita`
--

DROP TABLE IF EXISTS `Orbita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orbita` (
  `orbita_id` int(11) NOT NULL AUTO_INCREMENT,
  `visina_km` int(11) DEFAULT NULL,
  `inklinacija_stepeni` decimal(5,2) DEFAULT NULL,
  `period_min` int(11) DEFAULT NULL,
  `datum_pocetka` date DEFAULT NULL,
  `datum_kraja` date DEFAULT NULL,
  `satelit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`orbita_id`),
  KEY `satelit_id` (`satelit_id`),
  CONSTRAINT `Orbita_ibfk_1` FOREIGN KEY (`satelit_id`) REFERENCES `Satelit` (`satelit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orbita`
--

LOCK TABLES `Orbita` WRITE;
/*!40000 ALTER TABLE `Orbita` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Orbita` VALUES
(1,550,53.00,95,'2019-05-24','2020-01-01',1),
(2,540,52.00,94,'2020-01-02',NULL,1),
(3,540,28.50,96,'1990-04-24',NULL,2),
(4,540,28.50,96,'1990-04-24',NULL,2),
(5,2200,28.50,128,'2022-11-16','2025-09-03',3),
(6,600,97.80,97,'2023-01-10','2025-09-03',4),
(7,35786,0.00,1440,'2025-10-26',NULL,5),
(8,550,53.00,95,'2026-01-15','2025-09-03',6),
(11,550,53.00,95,'2019-05-24','2020-01-01',1),
(12,540,52.00,94,'2020-01-02',NULL,1),
(13,550,53.00,95,'2019-05-24','2020-01-01',39),
(14,540,52.00,94,'2020-01-02',NULL,39),
(15,540,28.50,96,'1990-04-24',NULL,2),
(16,540,28.50,96,'1990-04-24',NULL,40),
(17,2200,28.50,128,'2022-11-16','2025-09-03',3),
(18,2200,28.50,128,'2022-11-16',NULL,41),
(19,600,97.80,97,'2023-01-10','2025-09-03',4),
(20,600,97.80,97,'2023-01-10',NULL,42),
(21,35786,0.00,1440,'2025-10-26',NULL,5),
(22,550,53.00,95,'2026-01-15','2025-09-03',6),
(23,110,0.00,107,'1969-07-20','1969-07-24',12),
(24,110,0.00,107,'1969-07-20','1969-07-24',21),
(25,110,0.00,107,'1969-07-20','1969-07-24',30),
(26,1500000,0.00,525600,'2021-12-25',NULL,13),
(27,1500000,0.00,525600,'2021-12-25',NULL,22),
(28,1500000,0.00,525600,'2021-12-25',NULL,31),
(29,100,90.00,118,'2019-09-02',NULL,14),
(30,100,90.00,118,'2019-09-02',NULL,23),
(31,100,90.00,118,'2019-09-02',NULL,32),
(32,0,0.00,0,'2020-07-30',NULL,15),
(33,0,0.00,0,'2020-07-30',NULL,24),
(34,0,0.00,0,'2020-07-30',NULL,33),
(35,23222,56.00,858,'2014-08-22',NULL,16),
(36,23222,56.00,858,'2014-08-22',NULL,25),
(37,23222,56.00,858,'2014-08-22',NULL,34),
(38,400,24.50,90,'2020-07-23',NULL,17),
(39,400,24.50,90,'2020-07-23',NULL,26),
(40,400,24.50,90,'2020-07-23',NULL,35),
(41,9000,0.00,180,'2018-10-20',NULL,18),
(42,9000,0.00,180,'2018-10-20',NULL,27),
(43,9000,0.00,180,'2018-10-20',NULL,36),
(44,0,0.00,0,'1977-09-05',NULL,19),
(45,0,0.00,0,'1977-09-05',NULL,28),
(46,0,0.00,0,'1977-09-05',NULL,37),
(47,20200,55.00,720,'2018-12-23',NULL,20),
(48,20200,55.00,720,'2018-12-23',NULL,29),
(49,20200,55.00,720,'2018-12-23',NULL,38),
(50,25000,56.00,NULL,'2025-09-03',NULL,6),
(51,25000,56.00,NULL,'2025-09-03',NULL,4),
(52,500,52.00,NULL,'2025-09-03',NULL,3);
/*!40000 ALTER TABLE `Orbita` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Raketa_Nosac`
--

DROP TABLE IF EXISTS `Raketa_Nosac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Raketa_Nosac` (
  `raketa_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `proizvodjac` varchar(100) DEFAULT NULL,
  `kapacitet_tovar_kg` int(11) DEFAULT NULL,
  PRIMARY KEY (`raketa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Raketa_Nosac`
--

LOCK TABLES `Raketa_Nosac` WRITE;
/*!40000 ALTER TABLE `Raketa_Nosac` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Raketa_Nosac` VALUES
(1,'Falcon 9','SpaceX',22800),
(2,'Soyuz-2','Roscosmos',8200),
(3,'Long March 5','CASC',25000),
(4,'Ariane 5','Arianespace',21000),
(5,'Falcon Heavy','SpaceX',63800),
(6,'Saturn V','NASA',140000),
(7,'Atlas V','ULA',18850),
(8,'Delta IV Heavy','ULA',28690),
(9,'Vega','Arianespace',1500),
(10,'H-IIA','Mitsubishi Heavy Industries',15000),
(11,'PSLV','ISRO',3800),
(12,'GSLV Mk III','ISRO',10000),
(13,'Electron','Rocket Lab',300),
(14,'New Shepard','Blue Origin',100),
(15,'New Glenn','Blue Origin',45000);
/*!40000 ALTER TABLE `Raketa_Nosac` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Satelit`
--

DROP TABLE IF EXISTS `Satelit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Satelit` (
  `satelit_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `zemlja_proizvodnje` varchar(100) DEFAULT NULL,
  `masa_kg` int(11) DEFAULT NULL,
  `misija_id` int(11) DEFAULT NULL,
  `tip_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`satelit_id`),
  KEY `misija_id` (`misija_id`),
  KEY `tip_id` (`tip_id`),
  CONSTRAINT `Satelit_ibfk_1` FOREIGN KEY (`misija_id`) REFERENCES `Misija` (`misija_id`),
  CONSTRAINT `Satelit_ibfk_2` FOREIGN KEY (`tip_id`) REFERENCES `Tip_Satelita` (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Satelit`
--

LOCK TABLES `Satelit` WRITE;
/*!40000 ALTER TABLE `Satelit` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Satelit` VALUES
(1,'Starlink-1','SAD',260,3,1),
(2,'Hubble Telescope','SAD',11110,2,4),
(3,'Orion','SAD',10432,1,4),
(4,'Gagarin','Rusija',7500,2,2),
(5,'Satelit-5','Evropska unija',4500,1,1),
(6,'Starlink-100','SAD',260,3,1),
(12,'Apollo Lunar Module','SAD',15103,4,4),
(13,'James Webb Telescope','SAD/ESA',6200,5,4),
(14,'Chandrayaan-2 Orbiter','Indija',2379,6,2),
(15,'Perseverance Rover','SAD',1025,7,4),
(16,'Galileo-FOC FM1','Evropska unija',715,8,3),
(17,'Tianwen-1 Rover','Kina',240,9,4),
(18,'BepiColombo MMO','Evropa/Japan',2550,10,4),
(19,'Voyager 1 Probe','SAD',722,11,4),
(20,'GPS III SV01','SAD',3880,12,3),
(21,'Apollo Lunar Module','SAD',15103,4,4),
(22,'James Webb Telescope','SAD/ESA',6200,5,4),
(23,'Chandrayaan-2 Orbiter','Indija',2379,6,2),
(24,'Perseverance Rover','SAD',1025,7,4),
(25,'Galileo-FOC FM1','Evropska unija',715,8,3),
(26,'Tianwen-1 Rover','Kina',240,9,4),
(27,'BepiColombo MMO','Evropa/Japan',2550,10,4),
(28,'Voyager 1 Probe','SAD',722,11,4),
(29,'GPS III SV01','SAD',3880,12,3),
(30,'Apollo Lunar Module','SAD',15103,4,4),
(31,'James Webb Telescope','SAD/ESA',6200,5,4),
(32,'Chandrayaan-2 Orbiter','Indija',2379,6,2),
(33,'Perseverance Rover','SAD',1025,7,4),
(34,'Galileo-FOC FM1','Evropska unija',715,8,3),
(35,'Tianwen-1 Rover','Kina',240,9,4),
(36,'BepiColombo MMO','Evropa/Japan',2550,10,4),
(37,'Voyager 1 Probe','SAD',722,11,4),
(38,'GPS III SV01','SAD',3880,12,3),
(39,'Starlink-1','SAD',260,3,1),
(40,'Hubble Telescope','SAD',11110,2,4),
(41,'Orion','SAD',10432,1,4),
(42,'Gagarin','Rusija',7500,2,2),
(47,'CubeSat-9','SAD',10,1,1),
(48,'CubeSat-9','SAD',10,1,1);
/*!40000 ALTER TABLE `Satelit` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_uca1400_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`smts`@`%`*/ /*!50003 TRIGGER AfterInsertSatelit
AFTER INSERT ON Satelit
FOR EACH ROW
BEGIN
    UPDATE Misija
    SET broj_satelita = broj_satelita + 1
    WHERE misija_id = NEW.misija_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_uca1400_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`smts`@`%`*/ /*!50003 TRIGGER nakon_dodavanja_satelita_azuriraj_broj
AFTER INSERT ON Satelit
FOR EACH ROW
BEGIN
    UPDATE Misija
    SET broj_satelita = (SELECT COUNT(*) FROM Satelit WHERE misija_id = NEW.misija_id)
    WHERE misija_id = NEW.misija_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary table structure for view `Sateliti_i_Orbite`
--

DROP TABLE IF EXISTS `Sateliti_i_Orbite`;
/*!50001 DROP VIEW IF EXISTS `Sateliti_i_Orbite`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Sateliti_i_Orbite` AS SELECT
 1 AS `naziv_satelita`,
  1 AS `tip_satelita`,
  1 AS `naziv_misije`,
  1 AS `visina_orbite_km`,
  1 AS `inklinacija_orbite` */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Svi_Sateliti_Detalji`
--

DROP TABLE IF EXISTS `Svi_Sateliti_Detalji`;
/*!50001 DROP VIEW IF EXISTS `Svi_Sateliti_Detalji`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `Svi_Sateliti_Detalji` AS SELECT
 1 AS `satelit_id`,
  1 AS `naziv_satelita`,
  1 AS `zemlja_proizvodnje`,
  1 AS `masa_kg`,
  1 AS `tip_satelita`,
  1 AS `naziv_misije` */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Tip_Satelita`
--

DROP TABLE IF EXISTS `Tip_Satelita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tip_Satelita` (
  `tip_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_tipa` varchar(100) NOT NULL,
  `opis_tipa` text DEFAULT NULL,
  PRIMARY KEY (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tip_Satelita`
--

LOCK TABLES `Tip_Satelita` WRITE;
/*!40000 ALTER TABLE `Tip_Satelita` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Tip_Satelita` VALUES
(1,'Komunikacijski','Satelit za telekomunikacije i prenos podataka.'),
(2,'Osmatrački','Satelit za posmatranje Zemlje, meteorologiju i kartiranje.'),
(3,'Navigacijski','Dio globalnih navigacijskih sistema poput GPS-a.'),
(4,'Naučni','Satelit za naučna istraživanja, astronomiju i fiziku.');
/*!40000 ALTER TABLE `Tip_Satelita` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Ucestvovanje`
--

DROP TABLE IF EXISTS `Ucestvovanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ucestvovanje` (
  `ucestvovanje_id` int(11) NOT NULL AUTO_INCREMENT,
  `misija_id` int(11) DEFAULT NULL,
  `operater_id` int(11) DEFAULT NULL,
  `uloga_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ucestvovanje_id`),
  KEY `misija_id` (`misija_id`),
  KEY `operater_id` (`operater_id`),
  KEY `uloga_id` (`uloga_id`),
  CONSTRAINT `Ucestvovanje_ibfk_1` FOREIGN KEY (`misija_id`) REFERENCES `Misija` (`misija_id`),
  CONSTRAINT `Ucestvovanje_ibfk_2` FOREIGN KEY (`operater_id`) REFERENCES `Operater` (`operater_id`),
  CONSTRAINT `Ucestvovanje_ibfk_3` FOREIGN KEY (`uloga_id`) REFERENCES `Uloga_Operatera` (`uloga_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ucestvovanje`
--

LOCK TABLES `Ucestvovanje` WRITE;
/*!40000 ALTER TABLE `Ucestvovanje` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Ucestvovanje` VALUES
(5,1,1,1),
(6,1,1,2),
(7,2,2,3),
(8,3,3,1),
(9,2,6,7),
(10,5,9,6),
(11,7,8,5),
(12,10,7,7);
/*!40000 ALTER TABLE `Ucestvovanje` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Uloga_Operatera`
--

DROP TABLE IF EXISTS `Uloga_Operatera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Uloga_Operatera` (
  `uloga_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_uloge` varchar(100) NOT NULL,
  `opis_uloge` text DEFAULT NULL,
  PRIMARY KEY (`uloga_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Uloga_Operatera`
--

LOCK TABLES `Uloga_Operatera` WRITE;
/*!40000 ALTER TABLE `Uloga_Operatera` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Uloga_Operatera` VALUES
(1,'Vođa projekta','Odgovoran za cjelokupno vođenje misije.'),
(2,'Inženjer','Tehnička podrška i održavanje sistema.'),
(3,'Analitičar podataka','Analizira podatke prikupljene sa satelita.'),
(5,'Naučnik',NULL),
(6,'Menadžer projekta',NULL),
(7,'Glavni inženjer',NULL),
(8,'Finansijski partner',NULL);
/*!40000 ALTER TABLE `Uloga_Operatera` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Zemaljska_Stanica`
--

DROP TABLE IF EXISTS `Zemaljska_Stanica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Zemaljska_Stanica` (
  `stanica_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `lokacija` varchar(255) DEFAULT NULL,
  `zemlja_stanice` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`stanica_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zemaljska_Stanica`
--

LOCK TABLES `Zemaljska_Stanica` WRITE;
/*!40000 ALTER TABLE `Zemaljska_Stanica` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Zemaljska_Stanica` VALUES
(1,'Kennedy Space Center','Florida, SAD','SAD'),
(2,'Plesetsk Cosmodrome','Arkhangelsk, Rusija','Rusija'),
(3,'ESA Ground Station','Fucino, Italija','Evropska unija'),
(4,'Deep Space Network','Canberra, Australija','Australija'),
(5,'Goldstone Complex','Mojave, SAD','SAD'),
(6,'Madrid Complex','Madrid, Španija','Španija');
/*!40000 ALTER TABLE `Zemaljska_Stanica` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Zemlja`
--

DROP TABLE IF EXISTS `Zemlja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Zemlja` (
  `zemlja_id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_zemlje` varchar(100) NOT NULL,
  `kontinent` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`zemlja_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zemlja`
--

LOCK TABLES `Zemlja` WRITE;
/*!40000 ALTER TABLE `Zemlja` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Zemlja` VALUES
(1,'SAD','Sjeverna Amerika'),
(2,'Rusija','Evropa'),
(3,'Kina','Azija'),
(4,'Evropska unija','Evropa'),
(5,'Japan','Azija'),
(6,'Indija','Azija'),
(7,'Ujedinjeno Kraljevstvo','Evropa'),
(8,'Francuska','Evropa'),
(9,'Njemačka','Evropa'),
(10,'Italija','Evropa'),
(11,'Kanada','Sjeverna Amerika');
/*!40000 ALTER TABLE `Zemlja` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Zemlja_u_Misiji`
--

DROP TABLE IF EXISTS `Zemlja_u_Misiji`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Zemlja_u_Misiji` (
  `zemlja_u_misiji_id` int(11) NOT NULL AUTO_INCREMENT,
  `misija_id` int(11) DEFAULT NULL,
  `zemlja_id` int(11) DEFAULT NULL,
  `doprinos` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`zemlja_u_misiji_id`),
  KEY `misija_id` (`misija_id`),
  KEY `zemlja_id` (`zemlja_id`),
  CONSTRAINT `Zemlja_u_Misiji_ibfk_1` FOREIGN KEY (`misija_id`) REFERENCES `Misija` (`misija_id`),
  CONSTRAINT `Zemlja_u_Misiji_ibfk_2` FOREIGN KEY (`zemlja_id`) REFERENCES `Zemlja` (`zemlja_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Zemlja_u_Misiji`
--

LOCK TABLES `Zemlja_u_Misiji` WRITE;
/*!40000 ALTER TABLE `Zemlja_u_Misiji` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Zemlja_u_Misiji` VALUES
(1,1,1,'Lansiranje, vođenje misije'),
(2,2,1,'Lansiranje, financiranje'),
(3,3,1,'Lansiranje, proizvodnja, vođenje misije'),
(4,4,1,'Lansiranje, vođenje misije (NASA)'),
(5,5,8,'Zajednička misija (ESA)'),
(6,5,1,'Zajednička misija (NASA)'),
(7,5,5,'Zajednička misija (JAXA)'),
(8,5,7,'Zajednička misija (UKSA)'),
(9,6,6,'Lansiranje, vođenje misije (ISRO)'),
(10,7,1,'Lansiranje, vođenje misije (NASA)'),
(11,8,4,'Lansiranje, vođenje misije (ESA)'),
(12,9,3,'Lansiranje, vođenje misije (CNSA)'),
(13,10,5,'Zajednička misija (JAXA)'),
(14,10,4,'Zajednička misija (ESA)'),
(15,11,1,'Lansiranje, vođenje misije (NASA)'),
(16,12,1,'Lansiranje, vođenje misije (Svemirski snage SAD)'),
(17,13,1,'Lansiranje, vođenje misije (NASA)'),
(18,14,1,'Lansiranje, vođenje misije (NASA)'),
(19,14,4,'Lender Huygens, naučna podrška (ESA)'),
(20,15,1,'Lansiranje, vođenje misije (NASA)'),
(21,16,1,'Lansiranje, vođenje misije (NASA)'),
(22,16,8,'Naučni instrument SEIS'),
(23,16,9,'Naučni instrument HP3'),
(24,17,3,'Lansiranje, vođenje misije (CNSA)'),
(25,18,4,'Zajednička misija (ESA)'),
(26,18,2,'Zajednička misija (Roscosmos)'),
(27,19,1,'Lansiranje, vođenje misije (NASA)'),
(28,20,1,'Lansiranje, vođenje misije (NASA)'),
(29,21,1,'Lansiranje, vođenje misije (NASA)'),
(30,22,1,'Lansiranje, vođenje misije (NASA)'),
(31,23,2,'Lansiranje, vođenje misije (Sovjetski Savez)'),
(32,24,1,'Lansiranje, vođenje misije (NASA)'),
(33,25,2,'Lansiranje, vođenje misije (Sovjetski Savez)'),
(34,26,4,'Lansiranje, vođenje misije (ESA)'),
(35,27,1,'Lansiranje, vođenje misije (NASA)'),
(36,28,1,'Lansiranje, vođenje misije (NASA)'),
(37,29,1,'Lansiranje, vođenje misije (NASA)'),
(38,30,5,'Lansiranje, vođenje misije (JAXA)');
/*!40000 ALTER TABLE `Zemlja_u_Misiji` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Final view structure for view `Detalji_Lansiranja`
--

/*!50001 DROP VIEW IF EXISTS `Detalji_Lansiranja`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Detalji_Lansiranja` AS select `s`.`naziv` AS `satelit`,`l`.`vrijeme_lansiranja` AS `vrijeme_lansiranja`,`rn`.`naziv` AS `raketa_nosac`,`m`.`naziv` AS `misija`,`ml`.`naziv` AS `mjesto_lansiranja` from ((((`Lansiranje` `l` join `Satelit` `s` on(`l`.`satelit_id` = `s`.`satelit_id`)) join `Raketa_Nosac` `rn` on(`l`.`raketa_id` = `rn`.`raketa_id`)) join `Misija` `m` on(`l`.`misija_id` = `m`.`misija_id`)) join `Mjesto_Lansiranja` `ml` on(`l`.`mjesto_id` = `ml`.`mjesto_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Komunikacija_Stanica_Satelit`
--

/*!50001 DROP TABLE IF EXISTS `Komunikacija_Stanica_Satelit`*/;
/*!50001 DROP VIEW IF EXISTS `Komunikacija_Stanica_Satelit`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Komunikacija_Stanica_Satelit` AS select `k`.`komunikacija_id` AS `komunikacija_id`,`zs`.`naziv` AS `naziv_stanice`,`zs`.`lokacija` AS `lokacija_stanice`,`s`.`naziv` AS `naziv_satelita`,`k`.`datum_komunikacije` AS `datum_komunikacije`,`k`.`tip_komunikacije` AS `tip_komunikacije`,`k`.`sadrzaj_poruke` AS `sadrzaj_poruke` from ((`Komunikacija` `k` join `Zemaljska_Stanica` `zs` on(`k`.`stanica_id` = `zs`.`stanica_id`)) join `Satelit` `s` on(`k`.`satelit_id` = `s`.`satelit_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;


--
-- Final view structure for view `Lansiranja_Po_Proizvodjacu`
--

/*!50001 DROP VIEW IF EXISTS `Lansiranja_Po_Proizvodjacu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Lansiranja_Po_Proizvodjacu` AS select `rn`.`proizvodjac` AS `proizvodjac`,`rn`.`naziv` AS `raketa_nosac`,count(`l`.`lansiranje_id`) AS `broj_lansiranja` from (`Raketa_Nosac` `rn` join `Lansiranje` `l` on(`rn`.`raketa_id` = `l`.`raketa_id`)) group by `rn`.`proizvodjac`,`rn`.`naziv` order by count(`l`.`lansiranje_id`) desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Misije_Detalji_Partnera`
--

/*!50001 DROP VIEW IF EXISTS `Misije_Detalji_Partnera`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Misije_Detalji_Partnera` AS select `m`.`naziv` AS `naziv_misije`,`m`.`status` AS `status_misije`,`z`.`naziv_zemlje` AS `zemlja`,`o`.`ime_operatera` AS `operater`,`u`.`naziv_uloge` AS `uloga_operatera` from (((((`Misija` `m` join `Zemlja_u_Misiji` `zum` on(`m`.`misija_id` = `zum`.`misija_id`)) join `Zemlja` `z` on(`zum`.`zemlja_id` = `z`.`zemlja_id`)) join `Ucestvovanje` `uc` on(`m`.`misija_id` = `uc`.`misija_id`)) join `Operater` `o` on(`uc`.`operater_id` = `o`.`operater_id`)) join `Uloga_Operatera` `u` on(`uc`.`uloga_id` = `u`.`uloga_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Misije_Po_Statusu`
--

/*!50001 DROP VIEW IF EXISTS `Misije_Po_Statusu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Misije_Po_Statusu` AS select `m`.`misija_id` AS `misija_id`,`m`.`naziv` AS `naziv_misije`,`m`.`datum_pocetka` AS `datum_pocetka`,`m`.`datum_kraja` AS `datum_kraja`,`m`.`cilj_misije` AS `cilj_misije`,`m`.`status` AS `status`,case when `m`.`datum_kraja` is null then 'Aktivna' else 'Završena' end AS `status_detalji` from `Misija` `m` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Misije_Po_Zemlji`
--

/*!50001 DROP VIEW IF EXISTS `Misije_Po_Zemlji`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Misije_Po_Zemlji` AS select `m`.`status` AS `status`,`z`.`naziv_zemlje` AS `zemlja`,count(distinct `m`.`misija_id`) AS `broj_misija` from ((`Misija` `m` join `Zemlja_u_Misiji` `zum` on(`m`.`misija_id` = `zum`.`misija_id`)) join `Zemlja` `z` on(`zum`.`zemlja_id` = `z`.`zemlja_id`)) group by `m`.`status`,`z`.`naziv_zemlje` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Sateliti_i_Orbite`
--

/*!50001 DROP VIEW IF EXISTS `Sateliti_i_Orbite`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Sateliti_i_Orbite` AS select `s`.`naziv` AS `naziv_satelita`,`ts`.`naziv_tipa` AS `tip_satelita`,`m`.`naziv` AS `naziv_misije`,`o`.`visina_km` AS `visina_orbite_km`,`o`.`inklinacija_stepeni` AS `inklinacija_orbite` from (((`Satelit` `s` join `Misija` `m` on(`s`.`misija_id` = `m`.`misija_id`)) join `Tip_Satelita` `ts` on(`s`.`tip_id` = `ts`.`tip_id`)) left join `Orbita` `o` on(`s`.`satelit_id` = `o`.`satelit_id` and `o`.`datum_kraja` is null)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Svi_Sateliti_Detalji`
--

/*!50001 DROP VIEW IF EXISTS `Svi_Sateliti_Detalji`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_uca1400_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`smts`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `Svi_Sateliti_Detalji` AS select `s`.`satelit_id` AS `satelit_id`,`s`.`naziv` AS `naziv_satelita`,`s`.`zemlja_proizvodnje` AS `zemlja_proizvodnje`,`s`.`masa_kg` AS `masa_kg`,`ts`.`naziv_tipa` AS `tip_satelita`,`m`.`naziv` AS `naziv_misije` from ((`Satelit` `s` left join `Tip_Satelita` `ts` on(`s`.`tip_id` = `ts`.`tip_id`)) left join `Misija` `m` on(`s`.`misija_id` = `m`.`misija_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-09-04  1:21:46

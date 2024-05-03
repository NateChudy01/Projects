-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: localhost    Database: red_foxes_database
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `AccountNumber` int NOT NULL,
  `Savings` int NOT NULL,
  `Checking` int NOT NULL,
  PRIMARY KEY (`AccountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES (1,200,300),(2,40,1005),(3,234,900),(4,300,150),(5,450,830),(6,424,90),(7,1000,2723),(8,253,2002),(9,0,900),(10,10000,0);
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Account_SSN`
--

DROP TABLE IF EXISTS `Account_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account_SSN` (
  `AccountID` int NOT NULL,
  `SSN` varchar(9) NOT NULL,
  KEY `Account_idx` (`AccountID`),
  KEY `SSN_idx` (`SSN`),
  CONSTRAINT `Account` FOREIGN KEY (`AccountID`) REFERENCES `Account` (`AccountNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SSN` FOREIGN KEY (`SSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account_SSN`
--

LOCK TABLES `Account_SSN` WRITE;
/*!40000 ALTER TABLE `Account_SSN` DISABLE KEYS */;
INSERT INTO `Account_SSN` VALUES (1,'915212234'),(2,'142351423'),(3,'231571524'),(4,'882420192'),(5,'920259182'),(6,'162369245'),(7,'738360129'),(8,'253591582'),(9,'902840912'),(10,'349018519');
/*!40000 ALTER TABLE `Account_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bank`
--

DROP TABLE IF EXISTS `Bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bank` (
  `BankCode` int NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Address` varchar(60) NOT NULL,
  `Owner` varchar(45) NOT NULL,
  `OpeningYear` int NOT NULL,
  PRIMARY KEY (`BankCode`,`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bank`
--

LOCK TABLES `Bank` WRITE;
/*!40000 ALTER TABLE `Bank` DISABLE KEYS */;
INSERT INTO `Bank` VALUES (1012,'Key Bank','2031 Doubleday Avenue','Nithin Reddy Baddam',1994),(1121,'JP Morgan Chase','41 Chestnut Street','Luca Gristina',2000),(1422,'Bank of America','124 Arrowhead Road','Jack Young',1999),(1423,'Marist Bank','3399 North Road','Nate Chudy',2024),(1921,'Saratoga Bank','1 College Street','Michael Fairly',2007),(2653,'Poughkeepsie Bank','13 Brodaway Street','Andrew Patnode',2021),(3242,'Citi Bank','302 Tyler Avenue','Sam Lowe',1812),(5323,'TD Bank','413 Sunset Avenue','Ahkil Gangishetti',1852),(5463,'Goldman Sachs Bank','5 Dorthy Nolan Avenue','Sam Hogan',1789),(9637,'Pnc Bank','2 Caroline Street','Brendan Owens',2023);
/*!40000 ALTER TABLE `Bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bank_Employee_Record`
--

DROP TABLE IF EXISTS `Bank_Employee_Record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bank_Employee_Record` (
  `BankCode` int NOT NULL,
  `EmployeeSSN` varchar(9) NOT NULL,
  KEY `BankCode_Table_To_Bank_idx` (`BankCode`),
  KEY `EmployeeSSN_Table_To_Employee_idx` (`EmployeeSSN`),
  KEY `EmployeeSSN_Table_To_SSN_idx` (`EmployeeSSN`),
  CONSTRAINT `BankCode_Table_To_Bank` FOREIGN KEY (`BankCode`) REFERENCES `Bank` (`BankCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `EmployeeRecord_SSNTable` FOREIGN KEY (`EmployeeSSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bank_Employee_Record`
--

LOCK TABLES `Bank_Employee_Record` WRITE;
/*!40000 ALTER TABLE `Bank_Employee_Record` DISABLE KEYS */;
INSERT INTO `Bank_Employee_Record` VALUES (1012,'162369245'),(1012,'349018519'),(1121,'738360129'),(1423,'902840912'),(1423,'973472353'),(1423,'915212234'),(3242,'882420192'),(3242,'973472353'),(5323,'142351423'),(1921,'253591582');
/*!40000 ALTER TABLE `Bank_Employee_Record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `CustomerID` int NOT NULL,
  `Occupation` varchar(45) NOT NULL,
  `CreditScore` int NOT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `Occupation_idx` (`Occupation`),
  CONSTRAINT `Occupation` FOREIGN KEY (`Occupation`) REFERENCES `Occupation` (`Occupation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Janitor',730),(2,'Software Engineer',800),(3,'Cashier',560),(4,'MLB Player',810),(5,'Unemployeed',600),(6,'Artist',850),(7,'Professor',800),(8,'High School Teacher',790),(9,'Lawyer',800),(10,'Engineer',675);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_BankCode`
--

DROP TABLE IF EXISTS `Customer_BankCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_BankCode` (
  `CustomerID` int NOT NULL,
  `BankCode` int NOT NULL,
  KEY `Customer_idx` (`CustomerID`),
  KEY `Bank_idx` (`BankCode`),
  CONSTRAINT `Bank` FOREIGN KEY (`BankCode`) REFERENCES `Bank` (`BankCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Customer` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_BankCode`
--

LOCK TABLES `Customer_BankCode` WRITE;
/*!40000 ALTER TABLE `Customer_BankCode` DISABLE KEYS */;
INSERT INTO `Customer_BankCode` VALUES (1,1012),(2,1422),(3,1121),(4,3242),(5,5323),(6,1423),(7,3242),(8,2653),(9,5463),(10,1921);
/*!40000 ALTER TABLE `Customer_BankCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer_SSN`
--

DROP TABLE IF EXISTS `Customer_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer_SSN` (
  `CustomerID` int NOT NULL,
  `SSN` varchar(9) NOT NULL,
  KEY `SSN_idx` (`SSN`),
  KEY `CustomerID_idx` (`CustomerID`),
  CONSTRAINT `Customer_SSN` FOREIGN KEY (`SSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CustomerID` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer_SSN`
--

LOCK TABLES `Customer_SSN` WRITE;
/*!40000 ALTER TABLE `Customer_SSN` DISABLE KEYS */;
INSERT INTO `Customer_SSN` VALUES (1,'915212234'),(2,'142351423'),(3,'231571524'),(4,'882420192'),(5,'920259182'),(6,'349018519'),(7,'247819280'),(8,'253591582'),(9,'738360129'),(10,'973472353');
/*!40000 ALTER TABLE `Customer_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `customerinfo`
--

DROP TABLE IF EXISTS `customerinfo`;
/*!50001 DROP VIEW IF EXISTS `customerinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `customerinfo` AS SELECT 
 1 AS `CustomerID`,
 1 AS `CustomerSSN`,
 1 AS `CustomerFirstName`,
 1 AS `CustomerLastName`,
 1 AS `Occupation`,
 1 AS `LoanID`,
 1 AS `LoanTerms`,
 1 AS `LeaseID`,
 1 AS `LeaseSignDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `EmployeeID` int NOT NULL,
  `BirthDate` varchar(45) NOT NULL,
  `Occupation` varchar(45) NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `Occupation_idx` (`Occupation`),
  CONSTRAINT `Employee_Occupation` FOREIGN KEY (`Occupation`) REFERENCES `Occupation` (`Occupation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'06/08/1988','Carpenter'),(2,'11/01/1992','Interior Designer'),(3,'05/05/1987','Plumber'),(4,'01/02/2001','Electrician'),(5,'04/14/2000','Carpenter'),(6,'07/14/2001','Janitor'),(7,'11/12/1998','Janitor'),(8,'12/25/1989','Bank Teller'),(9,'02/02/2002','Bank Teller'),(10,'05/18/1989','Security Guard');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee_SSN`
--

DROP TABLE IF EXISTS `Employee_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee_SSN` (
  `EmployeeID` int NOT NULL,
  `SSN` varchar(9) NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `Employee_SSN_idx` (`SSN`),
  CONSTRAINT `Employee_SSN` FOREIGN KEY (`SSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `EmployeeID` FOREIGN KEY (`EmployeeID`) REFERENCES `Employee` (`EmployeeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee_SSN`
--

LOCK TABLES `Employee_SSN` WRITE;
/*!40000 ALTER TABLE `Employee_SSN` DISABLE KEYS */;
INSERT INTO `Employee_SSN` VALUES (6,'142351423'),(7,'152445098'),(1,'162369245'),(8,'231571524'),(9,'247819280'),(2,'349018519'),(3,'738360129'),(10,'882420192'),(4,'902840912'),(5,'973472353');
/*!40000 ALTER TABLE `Employee_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `employeeinfo`
--

DROP TABLE IF EXISTS `employeeinfo`;
/*!50001 DROP VIEW IF EXISTS `employeeinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `employeeinfo` AS SELECT 
 1 AS `EmployeeID`,
 1 AS `EmployeeSSN`,
 1 AS `EmployeeFirstName`,
 1 AS `EmployeeLastName`,
 1 AS `BirthDate`,
 1 AS `Occupation`,
 1 AS `Salary`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Landlord`
--

DROP TABLE IF EXISTS `Landlord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Landlord` (
  `LandlordID` int NOT NULL,
  `Handyman` enum('Yes','No') NOT NULL,
  `UtilitiesCost` int NOT NULL,
  `Salary` int NOT NULL,
  PRIMARY KEY (`LandlordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Landlord`
--

LOCK TABLES `Landlord` WRITE;
/*!40000 ALTER TABLE `Landlord` DISABLE KEYS */;
INSERT INTO `Landlord` VALUES (1,'No',0,95000),(2,'Yes',0,100000),(3,'Yes',120,75000),(4,'No',45,123000),(5,'Yes',0,110000),(6,'No',35,87000),(7,'Yes',100,80000),(8,'Yes',0,79000),(9,'No',25,100000),(10,'No',150,45000);
/*!40000 ALTER TABLE `Landlord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Landlord_Address`
--

DROP TABLE IF EXISTS `Landlord_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Landlord_Address` (
  `LandlordID` int NOT NULL,
  `Address` varchar(45) NOT NULL,
  KEY `LandlordID_idx` (`LandlordID`),
  KEY `Landlord_Address_idx` (`Address`),
  CONSTRAINT `Landlord_Address` FOREIGN KEY (`Address`) REFERENCES `Properties` (`Address`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LandlordID` FOREIGN KEY (`LandlordID`) REFERENCES `Landlord` (`LandlordID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Landlord_Address`
--

LOCK TABLES `Landlord_Address` WRITE;
/*!40000 ALTER TABLE `Landlord_Address` DISABLE KEYS */;
INSERT INTO `Landlord_Address` VALUES (1,'75 Magnolia Drive'),(2,'1 West Avenue'),(3,'97 College Street'),(4,'15 Clay Street'),(5,'235 Estero Drive'),(6,'24 West Avenue'),(7,'10 Boyden Street'),(8,'10 Dutton Street'),(9,'100 Chestnut Street'),(10,'5 College Street');
/*!40000 ALTER TABLE `Landlord_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Landlord_SSN`
--

DROP TABLE IF EXISTS `Landlord_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Landlord_SSN` (
  `LandlordID` int NOT NULL,
  `SSN` varchar(9) NOT NULL,
  PRIMARY KEY (`LandlordID`),
  KEY `Landlord_SSN_idx` (`SSN`),
  CONSTRAINT `Landlord_ID_SSN` FOREIGN KEY (`LandlordID`) REFERENCES `Landlord` (`LandlordID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Landlord_SSN` FOREIGN KEY (`SSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Landlord_SSN`
--

LOCK TABLES `Landlord_SSN` WRITE;
/*!40000 ALTER TABLE `Landlord_SSN` DISABLE KEYS */;
INSERT INTO `Landlord_SSN` VALUES (7,'142352309'),(1,'152445098'),(2,'231571524'),(3,'253591582'),(8,'477295029'),(10,'536362738'),(9,'683950294'),(4,'868241234'),(5,'902419283'),(6,'925828129');
/*!40000 ALTER TABLE `Landlord_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lease`
--

DROP TABLE IF EXISTS `Lease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lease` (
  `LeaseNumber` int NOT NULL,
  `Duration` varchar(30) NOT NULL,
  `SignDate` varchar(45) NOT NULL,
  `NumPeopleOnLease` int NOT NULL,
  `PaymentDate` varchar(45) NOT NULL,
  `SpecialTerms` varchar(45) NOT NULL,
  PRIMARY KEY (`LeaseNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lease`
--

LOCK TABLES `Lease` WRITE;
/*!40000 ALTER TABLE `Lease` DISABLE KEYS */;
INSERT INTO `Lease` VALUES (1,'1 year','01/02/2024',2,'January 2nd','No Pets'),(2,'1 year','02/23/2024',3,'February 23rd','None'),(3,'3 years','06/12/2022',1,'June 12th','No Pets'),(4,'3 years','12/11/2021',5,'December 11th','None'),(5,'6 months','03/01/2024',2,'March 1st','None'),(6,'1 year','01/01/2024',1,'January 1st','No Pets'),(7,'2 years','01/15/2023',4,'January 1st','No Pets'),(8,'6 months','03/17/2024',3,'March 17th','None'),(9,'3 years','12/27/2023',2,'December 27th','None'),(10,'1 year','01/01/2024',5,'January 1st','None');
/*!40000 ALTER TABLE `Lease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lease_SSN`
--

DROP TABLE IF EXISTS `Lease_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Lease_SSN` (
  `LeaseID` int NOT NULL,
  `HeadSigneeSSN` varchar(9) NOT NULL,
  PRIMARY KEY (`LeaseID`),
  KEY `Lease_SSN_idx` (`HeadSigneeSSN`),
  CONSTRAINT `Lease_SSN` FOREIGN KEY (`HeadSigneeSSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LeaseID` FOREIGN KEY (`LeaseID`) REFERENCES `Lease` (`LeaseNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lease_SSN`
--

LOCK TABLES `Lease_SSN` WRITE;
/*!40000 ALTER TABLE `Lease_SSN` DISABLE KEYS */;
INSERT INTO `Lease_SSN` VALUES (1,'142351423'),(2,'162369245'),(6,'231571524'),(7,'247819280'),(8,'253591582'),(4,'349018519'),(9,'477295029'),(10,'536362738'),(3,'738360129'),(5,'915212234');
/*!40000 ALTER TABLE `Lease_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Loan`
--

DROP TABLE IF EXISTS `Loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan` (
  `LoanNumber` int NOT NULL,
  `LoanDuration` varchar(45) NOT NULL,
  `InterestRate(%)` varchar(30) NOT NULL,
  `Terms` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`LoanNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan`
--

LOCK TABLES `Loan` WRITE;
/*!40000 ALTER TABLE `Loan` DISABLE KEYS */;
INSERT INTO `Loan` VALUES (1,'1 year','3.5','Fixed-Rate'),(2,'1 year','10','Adjustable-Rate'),(3,'2 years','2.4','Fixed-Rate'),(4,'6 months','5.5','Fixed-Rate'),(5,'3 years','11','Adjustable-Rate'),(6,'1 year','5','Fixed-Rate'),(7,'2 years','1.5','Adjustable-Rate'),(8,'1 year','3.5','Adjustable-Rate'),(9,'2 years','20','Fixed-Rate'),(10,'3 years','5.7','Adjustable-Rate');
/*!40000 ALTER TABLE `Loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Loan_SSN`
--

DROP TABLE IF EXISTS `Loan_SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Loan_SSN` (
  `LoanID` int NOT NULL,
  `SSN` varchar(9) NOT NULL,
  KEY `LoanID_idx` (`LoanID`),
  KEY `Loan_SSN_idx` (`SSN`),
  CONSTRAINT `Loan_SSN` FOREIGN KEY (`SSN`) REFERENCES `SSN` (`SSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `LoanID` FOREIGN KEY (`LoanID`) REFERENCES `Loan` (`LoanNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Loan_SSN`
--

LOCK TABLES `Loan_SSN` WRITE;
/*!40000 ALTER TABLE `Loan_SSN` DISABLE KEYS */;
INSERT INTO `Loan_SSN` VALUES (1,'142351423'),(2,'162369245'),(3,'738360129'),(4,'349018519'),(5,'915212234'),(6,'231571524'),(7,'247819280'),(8,'253591582'),(9,'477295029'),(10,'536362738');
/*!40000 ALTER TABLE `Loan_SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Occupation`
--

DROP TABLE IF EXISTS `Occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Occupation` (
  `Occupation` varchar(45) NOT NULL,
  `Salary` int NOT NULL,
  PRIMARY KEY (`Occupation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Occupation`
--

LOCK TABLES `Occupation` WRITE;
/*!40000 ALTER TABLE `Occupation` DISABLE KEYS */;
INSERT INTO `Occupation` VALUES ('Artist',20000),('Bank Teller',45000),('Carpenter',70000),('Cashier',40000),('Electrician',65000),('Engineer',275000),('High School Teacher',30000),('Interior Designer',80000),('Janitor',35000),('Lawyer',250000),('MLB Player',10000000),('Plumber',85000),('Professor',35000),('Security Guard',35000),('Software Engineer',125000),('Unemployeed',0);
/*!40000 ALTER TABLE `Occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Properties`
--

DROP TABLE IF EXISTS `Properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Properties` (
  `Address` varchar(45) NOT NULL,
  `BuildingMaterial` varchar(45) NOT NULL,
  `NumberOfRooms` int NOT NULL,
  `Type` mediumtext NOT NULL,
  `Price` int NOT NULL,
  `Amenities` mediumtext NOT NULL,
  `Occupants` varchar(45) NOT NULL,
  PRIMARY KEY (`Address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Properties`
--

LOCK TABLES `Properties` WRITE;
/*!40000 ALTER TABLE `Properties` DISABLE KEYS */;
INSERT INTO `Properties` VALUES ('1 West Avenue','Concrete',10,'House',650000,'No','9'),('10 Boyden Street','Stone',5,'House',500000,'No','5'),('10 Dutton Street','Wood',6,'House',250000,'No','6'),('100 Chestnut Street','Concrete',8,'House',450000,'No','8'),('15 Clay Street','Wood',10,'House',750000,'No','8'),('235 Estero Drive','Steel',40,'Apartment Building',1000000,'Yes','35'),('24 West Avenue','Brick',30,'Apartment Building',1500000,'Yes','15'),('5 College Street','Brick',3,'House',350000,'No','3'),('75 Magnolia Drive','Stone',5,'House',600000,'No','5'),('97 College Street','Masonry',25,'Apartment Building',1000000,'Yes','25');
/*!40000 ALTER TABLE `Properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RealEstate_To_Address`
--

DROP TABLE IF EXISTS `RealEstate_To_Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RealEstate_To_Address` (
  `RealEstateID` int NOT NULL,
  `PropertyAddress` varchar(45) NOT NULL,
  KEY `RealEstateID_Table_To_RealEstate_idx` (`RealEstateID`),
  KEY `Address_Table_To_Properties_idx` (`PropertyAddress`),
  CONSTRAINT `Address_Table_To_Properties` FOREIGN KEY (`PropertyAddress`) REFERENCES `Properties` (`Address`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RealEstateID_Table_To_RealEstate` FOREIGN KEY (`RealEstateID`) REFERENCES `RealEstateCompany` (`RealEstateID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RealEstate_To_Address`
--

LOCK TABLES `RealEstate_To_Address` WRITE;
/*!40000 ALTER TABLE `RealEstate_To_Address` DISABLE KEYS */;
INSERT INTO `RealEstate_To_Address` VALUES (1,'1 West Avenue'),(2,'235 Estero Drive'),(3,'75 Magnolia Drive'),(4,'15 Clay Street'),(5,'24 West Avenue'),(10,'97 College Street'),(6,'10 Boyden Street'),(8,'10 Dutton Street'),(7,'100 Chestnut Street'),(9,'5 College Street');
/*!40000 ALTER TABLE `RealEstate_To_Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RealEstateCompany`
--

DROP TABLE IF EXISTS `RealEstateCompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RealEstateCompany` (
  `RealEstateID` int NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Owner` varchar(30) NOT NULL,
  `FoundingYear` int NOT NULL,
  `NumberOfEmployees` int NOT NULL,
  PRIMARY KEY (`RealEstateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RealEstateCompany`
--

LOCK TABLES `RealEstateCompany` WRITE;
/*!40000 ALTER TABLE `RealEstateCompany` DISABLE KEYS */;
INSERT INTO `RealEstateCompany` VALUES (1,'Dream Homes','Jon Jalon',2014,120),(2,'Empire Properties','Justin Kislowski',2004,80),(3,'Marist Realestate','John Adams',2011,25),(4,'Poughkeepsie Realestate','Joey Johns',2024,15),(5,'Prestige Properties','Harry Hogben',1998,150),(6,'Altitude Realty','Joey Jalon',1982,20),(7,'CenterPoint Properties','Ford Pines',2001,40),(8,'Prestige WorldWide','John Irons',2023,50),(9,'Ambrose Properties','Alex Henderson',2020,120),(10,'Urbancove Properties','Joey Laurer',2009,190);
/*!40000 ALTER TABLE `RealEstateCompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SSN`
--

DROP TABLE IF EXISTS `SSN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SSN` (
  `SSN` varchar(9) NOT NULL,
  `Fname` varchar(20) NOT NULL,
  `Lname` varchar(20) NOT NULL,
  PRIMARY KEY (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SSN`
--

LOCK TABLES `SSN` WRITE;
/*!40000 ALTER TABLE `SSN` DISABLE KEYS */;
INSERT INTO `SSN` VALUES ('142351423','John','Smith'),('142352309','Andre','Patnode'),('152445098','Andrew','Johnson'),('162369245','Colin','Carver'),('231571524','Brian','Moshier'),('247819280','Brendan','Romer'),('253591582','Ben','Talbot'),('349018519','Maddie','May'),('477295029','Sammie','Abate'),('536362738','Ben','McOsker'),('683950294','Sam','Schnell'),('738360129','Steve','Mazza'),('868241234','Jordan','James'),('882420192','Nate','Lincoln'),('902419283','Robin','Wilk'),('902840912','Jacqueline','Smith'),('915212234','Ty','Hot'),('920259182','Sam','Johnson'),('925828129','Jake','Minter'),('973472353','Brian','Yetter');
/*!40000 ALTER TABLE `SSN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Unit`
--

DROP TABLE IF EXISTS `Unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Unit` (
  `UnitNumber` int NOT NULL,
  `Cost` int NOT NULL,
  `Washer/Dryer` enum('Yes','No') NOT NULL,
  `Size(SquareFeet)` int NOT NULL,
  `RentersUtilities` varchar(45) NOT NULL,
  PRIMARY KEY (`UnitNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Unit`
--

LOCK TABLES `Unit` WRITE;
/*!40000 ALTER TABLE `Unit` DISABLE KEYS */;
INSERT INTO `Unit` VALUES (101,750,'Yes',800,'112'),(102,1000,'Yes',1000,'120'),(103,1000,'Yes',1000,'100'),(104,700,'No',875,'106'),(105,750,'No',1000,'110'),(106,1200,'Yes',1200,'100'),(107,1000,'Yes',1000,'95'),(108,1250,'Yes',1500,'120'),(109,1150,'Yes',840,'115'),(110,1100,'Yes',750,'142');
/*!40000 ALTER TABLE `Unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Unit_LeaseID`
--

DROP TABLE IF EXISTS `Unit_LeaseID`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Unit_LeaseID` (
  `UnitNumber` int NOT NULL,
  `LeaseID` int NOT NULL,
  KEY `UnitNumber_idx` (`UnitNumber`),
  KEY `LeaseID_idx` (`LeaseID`),
  CONSTRAINT `Lease_Unit` FOREIGN KEY (`LeaseID`) REFERENCES `Lease` (`LeaseNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `UnitNumber` FOREIGN KEY (`UnitNumber`) REFERENCES `Unit` (`UnitNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Unit_LeaseID`
--

LOCK TABLES `Unit_LeaseID` WRITE;
/*!40000 ALTER TABLE `Unit_LeaseID` DISABLE KEYS */;
INSERT INTO `Unit_LeaseID` VALUES (101,1),(102,5),(103,2),(104,4),(105,3),(106,6),(107,9),(108,7),(109,8),(110,10);
/*!40000 ALTER TABLE `Unit_LeaseID` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `customerinfo`
--

/*!50001 DROP VIEW IF EXISTS `customerinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `customerinfo` AS select `c`.`CustomerID` AS `CustomerID`,`cs`.`SSN` AS `CustomerSSN`,`s`.`Fname` AS `CustomerFirstName`,`s`.`Lname` AS `CustomerLastName`,`c`.`Occupation` AS `Occupation`,`lo`.`LoanID` AS `LoanID`,`l`.`Terms` AS `LoanTerms`,`le`.`LeaseID` AS `LeaseID`,`lea`.`SignDate` AS `LeaseSignDate` from ((((((`customer` `c` join `customer_ssn` `cs` on((`c`.`CustomerID` = `cs`.`CustomerID`))) join `ssn` `s` on((`cs`.`SSN` = `s`.`SSN`))) join `loan_ssn` `lo` on((`cs`.`SSN` = `lo`.`SSN`))) join `loan` `l` on((`lo`.`LoanID` = `l`.`LoanNumber`))) join `lease_ssn` `le` on((`cs`.`SSN` = `le`.`HeadSigneeSSN`))) join `lease` `lea` on((`le`.`LeaseID` = `lea`.`LeaseNumber`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `employeeinfo`
--

/*!50001 DROP VIEW IF EXISTS `employeeinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `employeeinfo` AS select `e`.`EmployeeID` AS `EmployeeID`,`es`.`SSN` AS `EmployeeSSN`,`s`.`Fname` AS `EmployeeFirstName`,`s`.`Lname` AS `EmployeeLastName`,`e`.`BirthDate` AS `BirthDate`,`e`.`Occupation` AS `Occupation`,`o`.`Salary` AS `Salary` from (((`employee` `e` join `employee_ssn` `es` on((`e`.`EmployeeID` = `es`.`EmployeeID`))) join `ssn` `s` on((`es`.`SSN` = `s`.`SSN`))) join `occupation` `o` on((`e`.`Occupation` = `o`.`Occupation`))) */;
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
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-15 14:19:59

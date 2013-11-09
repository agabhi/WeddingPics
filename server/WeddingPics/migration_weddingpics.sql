CREATE DATABASE `weddingpics`; 
USE `weddinpics`; 
SHOW DATABASES; 

CREATE TABLE `weddingpics`.`user`(  
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(200) NOT NULL,
  `fullName` varchar(200) DEFAULT NULL,
  `emailId` varchar(200) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `modifyDttm` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

CREATE TABLE `weddingpics`.`album`(  
  `albumId` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NOT NULL,
  `weddingId` VARCHAR(200) NOT NULL,
  `weddingdate` DATETIME,
  `firstUser` VARCHAR(200),
  `firstUserType` TINYINT(1),
  `secondUser` VARCHAR(200),
  `secondUserType` TINYINT(1),
  `modifyDttm` DATETIME,
  PRIMARY KEY (`albumId`)
);

CREATE TABLE `weddingpics`.`picture`(  
 `pictureId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `albumId` int(11) DEFAULT NULL,
  `imageType` tinyint(1) DEFAULT NULL,
  `pictureTitle` varchar(200) DEFAULT NULL,
  `url` text,
  `pictureDate` datetime DEFAULT NULL,
  PRIMARY KEY (`pictureId`)
) 




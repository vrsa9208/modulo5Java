CREATE DATABASE  IF NOT EXISTS `pixup` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pixup`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: pixup
-- ------------------------------------------------------
-- Server version	5.5.30

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
-- Table structure for table `artista`
--

DROP TABLE IF EXISTS `artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_artistico` varchar(40) NOT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_artistico` (`nombre_artistico`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artista`
--

LOCK TABLES `artista` WRITE;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` VALUES (1001,'Juan Gabriel','Catante, autor y compositor'),(1002,'Rita','Vocalista de Café Tacuba'),(1003,'Haash','Dos carnalitas'),(1004,'Enrique Bunbury','Rockero'),(1005,'Paul MacCartney','Vocalista de The Beatles'),(1006,'Pedro Piedra','????');
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero_musical`
--

DROP TABLE IF EXISTS `genero_musical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero_musical` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero_musical`
--

LOCK TABLES `genero_musical` WRITE;
/*!40000 ALTER TABLE `genero_musical` DISABLE KEYS */;
INSERT INTO `genero_musical` VALUES (5,'Merengue'),(1,'Rock'),(2,'Rock Pesado'),(3,'Salsa');
/*!40000 ALTER TABLE `genero_musical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rfc` char(13) NOT NULL,
  `razon_social` varchar(80) NOT NULL,
  `factura_xml` blob,
  `factura_pdf` blob,
  `id_venta_total` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_venta_total` (`id_venta_total`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_venta_total`) REFERENCES `venta_total` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disquera`
--

DROP TABLE IF EXISTS `disquera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disquera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(35) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disquera`
--

LOCK TABLES `disquera` WRITE;
/*!40000 ALTER TABLE `disquera` DISABLE KEYS */;
INSERT INTO `disquera` VALUES (5,'Culebra Records'),(8,'EMI Records'),(9,'Sonidera Azcapochalco'),(7,'Warner Bros. Music');
/*!40000 ALTER TABLE `disquera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idioma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES (1,'Español'),(3,'Francés'),(2,'Japonés');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta_total`
--

DROP TABLE IF EXISTS `venta_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_venta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total` double(15,2) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_usuario` (`id_usuario`,`fecha_venta`),
  CONSTRAINT `venta_total_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_total`
--

LOCK TABLES `venta_total` WRITE;
/*!40000 ALTER TABLE `venta_total` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta_total` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad_articulos` int(11) NOT NULL,
  `precio` float(6,2) NOT NULL,
  `descuento` double(15,2) NOT NULL DEFAULT '0.00',
  `id_venta_total` int(11) NOT NULL,
  `id_disco` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_venta_total` (`id_venta_total`,`id_disco`),
  KEY `id_disco` (`id_disco`),
  CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_venta_total`) REFERENCES `venta_total` (`id`) ON DELETE CASCADE,
  CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(35) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (3,'Canada'),(4,'Francia'),(2,'Japón'),(1,'México');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `duracion` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancion`
--

LOCK TABLES `cancion` WRITE;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` VALUES (123,'Bar tacuba','00:03:10'),(311,'Música Ligera','00:04:01');
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(40) NOT NULL,
  `numero_interior` char(15) DEFAULT NULL,
  `numero_exterior` char(15) NOT NULL,
  `id_colonia` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tipo_direccion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_usuario` (`id_usuario`,`id_tipo_direccion`),
  KEY `id_colonia` (`id_colonia`),
  KEY `id_tipo_direccion` (`id_tipo_direccion`),
  CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`id_colonia`) REFERENCES `colonia` (`id`),
  CONSTRAINT `direccion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `direccion_ibfk_3` FOREIGN KEY (`id_tipo_direccion`) REFERENCES `tipo_direccion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (1,'Naranjo',NULL,'S/N',1,1,1),(2,'Fresno','A201','102',1,1,2);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (30,'Baja California Sur'),(1,'Distrito Federal'),(2,'Jalisco'),(3,'Nuevo León'),(20,'Tlaxcala');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_estado` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`,`id_estado`),
  KEY `id_estado` (`id_estado`),
  CONSTRAINT `municipio_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (3,'Cuauhtémoc',1),(5,'Gustavo A. Madero',1),(101,'Huamantla',20),(71,'La Paz',30),(73,'Loreto',30),(72,'Los Cabos',30),(6,'Miguel Hidalgo',1),(7,'Monterrey',3),(74,'Mulegé',30),(102,'Nativitas',20),(8,'San Nicolás de los Garza',3),(103,'Tenancingo',20),(105,'Tetla',20),(104,'Totolac',20);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iva`
--

DROP TABLE IF EXISTS `iva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `porcentaje` float(5,2) NOT NULL,
  `vigente` tinyint(1) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `porcentaje` (`porcentaje`,`fecha_inicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iva`
--

LOCK TABLES `iva` WRITE;
/*!40000 ALTER TABLE `iva` DISABLE KEYS */;
INSERT INTO `iva` VALUES (1,16.00,1,'2015-11-21',NULL);
/*!40000 ALTER TABLE `iva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disco_cancion`
--

DROP TABLE IF EXISTS `disco_cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disco_cancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_disco` int(11) NOT NULL,
  `id_cancion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_disco` (`id_disco`,`id_cancion`),
  KEY `id_cancion` (`id_cancion`),
  CONSTRAINT `disco_cancion_ibfk_1` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`) ON DELETE CASCADE,
  CONSTRAINT `disco_cancion_ibfk_2` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disco_cancion`
--

LOCK TABLES `disco_cancion` WRITE;
/*!40000 ALTER TABLE `disco_cancion` DISABLE KEYS */;
/*!40000 ALTER TABLE `disco_cancion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fecha_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nombre` varchar(30) NOT NULL,
  `porcentaje_descuento` float(5,2) NOT NULL,
  `vigente` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colonia`
--

DROP TABLE IF EXISTS `colonia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colonia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(33) NOT NULL,
  `codigo_postal` char(5) NOT NULL,
  `id_municipio` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`,`id_municipio`),
  KEY `id_municipio` (`id_municipio`),
  CONSTRAINT `colonia_ibfk_1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colonia`
--

LOCK TABLES `colonia` WRITE;
/*!40000 ALTER TABLE `colonia` DISABLE KEYS */;
INSERT INTO `colonia` VALUES (1,'Santa María la Ribera','06400',3),(2,'San Rafael','06200',3),(3,'Tabacalera','06000',3);
/*!40000 ALTER TABLE `colonia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `apellido_paterno` varchar(40) NOT NULL,
  `apellido_materno` varchar(40) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` char(64) NOT NULL,
  `genero` char(1) DEFAULT NULL,
  `numero_telefonico` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Víctor Orlando','Santiago','Sánchez','1992-08-31','vrsa9208@gmail','12345','M',NULL),(2,'John','Doe',NULL,'1970-01-01','john.doe@gmail.com','12345','M','1234567890');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disco`
--

DROP TABLE IF EXISTS `disco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(40) NOT NULL,
  `fecha_lanzamiento` date NOT NULL,
  `precio` float(6,2) NOT NULL,
  `cantidad_disponible` int(10) unsigned NOT NULL DEFAULT '0',
  `id_idioma` int(11) NOT NULL,
  `id_pais` int(11) NOT NULL,
  `id_disquera` int(11) NOT NULL,
  `id_genero_musical` int(11) NOT NULL,
  `id_promocion` int(11) DEFAULT NULL,
  `id_iva` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idioma` (`id_idioma`),
  KEY `id_pais` (`id_pais`),
  KEY `id_disquera` (`id_disquera`),
  KEY `id_genero_musical` (`id_genero_musical`),
  KEY `id_promocion` (`id_promocion`),
  KEY `id_iva` (`id_iva`),
  KEY `titulo` (`titulo`),
  CONSTRAINT `disco_ibfk_1` FOREIGN KEY (`id_idioma`) REFERENCES `idioma` (`id`),
  CONSTRAINT `disco_ibfk_2` FOREIGN KEY (`id_pais`) REFERENCES `pais` (`id`),
  CONSTRAINT `disco_ibfk_3` FOREIGN KEY (`id_disquera`) REFERENCES `disquera` (`id`),
  CONSTRAINT `disco_ibfk_4` FOREIGN KEY (`id_genero_musical`) REFERENCES `genero_musical` (`id`),
  CONSTRAINT `disco_ibfk_5` FOREIGN KEY (`id_promocion`) REFERENCES `promocion` (`id`) ON DELETE SET NULL,
  CONSTRAINT `disco_ibfk_6` FOREIGN KEY (`id_iva`) REFERENCES `iva` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disco`
--

LOCK TABLES `disco` WRITE;
/*!40000 ALTER TABLE `disco` DISABLE KEYS */;
/*!40000 ALTER TABLE `disco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disco_artista`
--

DROP TABLE IF EXISTS `disco_artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disco_artista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_disco` int(11) NOT NULL,
  `id_artista` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_disco` (`id_disco`,`id_artista`),
  KEY `id_artista` (`id_artista`),
  CONSTRAINT `disco_artista_ibfk_1` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`),
  CONSTRAINT `disco_artista_ibfk_2` FOREIGN KEY (`id_artista`) REFERENCES `artista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disco_artista`
--

LOCK TABLES `disco_artista` WRITE;
/*!40000 ALTER TABLE `disco_artista` DISABLE KEYS */;
/*!40000 ALTER TABLE `disco_artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_direccion`
--

DROP TABLE IF EXISTS `tipo_direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` char(13) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_direccion`
--

LOCK TABLES `tipo_direccion` WRITE;
/*!40000 ALTER TABLE `tipo_direccion` DISABLE KEYS */;
INSERT INTO `tipo_direccion` VALUES (1,'Entrega'),(2,'Facturación'),(3,'Oficina');
/*!40000 ALTER TABLE `tipo_direccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-06 13:01:25

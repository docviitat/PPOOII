SCHEMA BASES DE DATOS PPOOII

sudo mysql -u root

CREATE DATABASE IF NOT EXISTS ppooii;
ALTER USER 'root'@'localhost' IDENTIFIED VIA mariadb_native_password USING PASSWORD('root');
GRANT ALL PRIVILEGES ON ppooii.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

mysql -u root -proot ppooii / root


MariaDB [ppooii]> SHOW CREATE TABLE documentos\G
*************************** 1. row ***************************
       Table: documentos
Create Table: CREATE TABLE `documentos` (
  `id_documento` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_documento` varchar(20) NOT NULL,
  `nombre_documento` varchar(100) NOT NULL,
  `tipos_vehiculo` varchar(2) NOT NULL,
  `obligatoriedad` varchar(2) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  UNIQUE KEY `codigo_documento` (`codigo_documento`),
  CONSTRAINT `chk_tipos_vehiculo` CHECK (`tipos_vehiculo` in ('A','M','AM')),
  CONSTRAINT `chk_obligatoriedad` CHECK (`obligatoriedad` in ('RA','RM','RR'))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
1 row in set (0.001 sec)

MariaDB [ppooii]> SHOW CREATE TABLE vehiculo_documentos\G
*************************** 1. row ***************************
       Table: vehiculo_documentos
Create Table: CREATE TABLE `vehiculo_documentos` (
  `id_vehiculo_documento` int(11) NOT NULL AUTO_INCREMENT,
  `id_vehiculo` int(11) NOT NULL,
  `id_documento` int(11) NOT NULL,
  `fecha_expedicion` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `estado_documento` varchar(20) NOT NULL DEFAULT 'En Verificacion',
  PRIMARY KEY (`id_vehiculo_documento`),
  UNIQUE KEY `uq_vehiculo_documento` (`id_vehiculo`,`id_documento`),
  KEY `fk_vehiculo_documento_documento` (`id_documento`),
  CONSTRAINT `fk_vehiculo_documento_documento` FOREIGN KEY (`id_documento`) REFERENCES `documentos` (`id_documento`) ON UPDATE CASCADE,
  CONSTRAINT `fk_vehiculo_documento_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chk_estado_documento` CHECK (`estado_documento` in ('Habilitado','Vencido','En Verificacion')),
  CONSTRAINT `chk_fechas` CHECK (`fecha_vencimiento` >= `fecha_expedicion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
1 row in set (0.001 sec)

MariaDB [ppooii]> SHOW CREATE TABLE vehiculos\G
*************************** 1. row ***************************
       Table: vehiculos
Create Table: CREATE TABLE `vehiculos` (
  `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_vehiculo` varchar(20) NOT NULL,
  `placa` varchar(6) NOT NULL,
  `tipo_servicio` varchar(2) NOT NULL,
  `tipo_combustible` varchar(20) NOT NULL,
  `capacidad_pasajeros` int(11) NOT NULL,
  `color` varchar(7) NOT NULL,
  `modelo` int(11) NOT NULL,
  `marca` varchar(100) NOT NULL,
  `linea` varchar(100) NOT NULL,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `placa` (`placa`),
  CONSTRAINT `chk_tipo_vehiculo` CHECK (`tipo_vehiculo` in ('Automovil','Motocicleta')),
  CONSTRAINT `chk_tipo_servicio` CHECK (`tipo_servicio` in ('Pu','Pr')),
  CONSTRAINT `chk_tipo_combustible` CHECK (`tipo_combustible` in ('Gasolina','Gas','Disel')),
  CONSTRAINT `chk_capacidad` CHECK (`capacidad_pasajeros` >= 0),
  CONSTRAINT `chk_modelo` CHECK (`modelo` >= 0),
  CONSTRAINT `chk_color` CHECK (`color` regexp '^#[0-9A-Fa-f]{6}$'),
  CONSTRAINT `chk_placa` CHECK (`tipo_vehiculo` = 'Automovil' and `placa` regexp '^[A-Za-z]{3}[0-9]{3}$' or `tipo_vehiculo` = 'Motocicleta' and `placa` regexp '^[A-Za-z]{3}[0-9]{2}[A-Za-z]$')
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
1 row in set (0.001 sec)


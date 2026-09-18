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

-- 1. Tabla: persona
CREATE TABLE persona (
    id_persona INT(11) NOT NULL AUTO_INCREMENT,
    identificacion VARCHAR(20) NOT NULL,
    tipo_identificacion VARCHAR(2) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL,
    tipo_persona VARCHAR(1) NOT NULL,
    PRIMARY KEY (id_persona),
    UNIQUE KEY uq_persona_identificacion (identificacion),
    UNIQUE KEY uq_persona_correo (correo_electronico),
    CONSTRAINT chk_tipo_identificacion CHECK (tipo_identificacion IN ('CC')),
    CONSTRAINT chk_tipo_persona CHECK (tipo_persona IN ('C', 'A'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Tabla: usuario
-- Nota: Clave primaria compuesta (id_persona, login) para garantizar 1:1 con administrativos.
CREATE TABLE usuario (
    id_persona INT(11) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    apikey VARCHAR(64) NOT NULL DEFAULT UUID(),
    PRIMARY KEY (id_persona, login),
    UNIQUE KEY uq_usuario_id_persona (id_persona),
    UNIQUE KEY uq_usuario_login (login),
    CONSTRAINT fk_usuario_persona FOREIGN KEY (id_persona) REFERENCES persona (id_persona) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tabla: vehiculo_persona (Relación M:N entre Vehículo y Conductor)
CREATE TABLE vehiculo_persona (
    id_vehiculo_persona INT(11) NOT NULL AUTO_INCREMENT,
    id_vehiculo INT(11) NOT NULL,
    id_persona INT(11) NOT NULL,
    fecha_asociacion DATE NOT NULL DEFAULT CURRENT_DATE(),
    estado_conductor VARCHAR(2) NOT NULL DEFAULT 'EA',
    PRIMARY KEY (id_vehiculo_persona),
    UNIQUE KEY uq_vehiculo_conductor (id_vehiculo, id_persona),
    KEY fk_vehiculo_persona_persona (id_persona),
    CONSTRAINT fk_vehiculo_persona_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculos (id_vehiculo) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_vehiculo_persona_persona FOREIGN KEY (id_persona) REFERENCES persona (id_persona) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT chk_estado_conductor CHECK (estado_conductor IN ('PO', 'EA', 'RO'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
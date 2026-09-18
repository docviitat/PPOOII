-- Drop tables in reverse order of dependencies if re-creating
DROP TABLE IF EXISTS vehiculo_conductor;
DROP TABLE IF EXISTS vehiculo_persona;
DROP TABLE IF EXISTS vehiculo_documento;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS documentos;
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS vehiculos;

-- 1. Base Table: persona
CREATE TABLE IF NOT EXISTS persona (
  id BIGINT NOT NULL AUTO_INCREMENT,
  identificacion VARCHAR(20) NOT NULL,
  tipo_identificacion VARCHAR(5) NOT NULL CHECK (tipo_identificacion IN ('CC','CE','PAS')),
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  tipo_persona VARCHAR(1) NOT NULL CHECK (tipo_persona IN ('C','A')),
  PRIMARY KEY (id),
  UNIQUE KEY identificacion (identificacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Base Table: vehiculos
CREATE TABLE IF NOT EXISTS vehiculos (
  id_vehiculo INT NOT NULL AUTO_INCREMENT,
  tipo_vehiculo VARCHAR(20) NOT NULL,
  placa VARCHAR(6) NOT NULL,
  tipo_servicio VARCHAR(2) NOT NULL CHECK (tipo_servicio IN ('A','M','AM')),
  tipo_combustible VARCHAR(20) NOT NULL,
  capacidad_pasajeros INT NOT NULL,
  color VARCHAR(7) NOT NULL,
  modelo INT NOT NULL,
  marca VARCHAR(100) NOT NULL,
  linea VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_vehiculo),
  UNIQUE KEY placa (placa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Base Table: documentos
CREATE TABLE IF NOT EXISTS documentos (
  id_documento INT NOT NULL AUTO_INCREMENT,
  codigo_documento VARCHAR(20) NOT NULL,
  nombre_documento VARCHAR(100) NOT NULL,
  tipos_vehiculo VARCHAR(2) NOT NULL,
  obligatoriedad VARCHAR(2) NOT NULL,
  descripcion VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (id_documento),
  UNIQUE KEY codigo_documento (codigo_documento),
  CONSTRAINT chk_tipos_vehiculo CHECK (tipos_vehiculo IN ('A','M','AM')),
  CONSTRAINT chk_obligatoriedad CHECK (obligatoriedad IN ('RA','RM','RR'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Dependent Table: usuario
CREATE TABLE IF NOT EXISTS usuario (
  login VARCHAR(100) NOT NULL,
  idpersona BIGINT NOT NULL,
  password VARCHAR(100) NOT NULL,
  apikey VARCHAR(100) NOT NULL,
  PRIMARY KEY (login, idpersona),
  KEY fk_usuario_persona (idpersona),
  CONSTRAINT fk_usuario_persona FOREIGN KEY (idpersona) REFERENCES persona (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. Junction Table: vehiculo_conductor
CREATE TABLE IF NOT EXISTS vehiculo_conductor (
  id BIGINT NOT NULL AUTO_INCREMENT,
  estado VARCHAR(2) NOT NULL,
  fecha_asociacion DATE NOT NULL,
  persona_id BIGINT NOT NULL,
  vehiculo_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY FKbndhle29qvq74qkp2pffxfwy9 (persona_id),
  KEY FKai18v148tmnnl0ivxei0ft5es (vehiculo_id),
  CONSTRAINT FKai18v148tmnnl0ivxei0ft5es FOREIGN KEY (vehiculo_id) REFERENCES vehiculos (id_vehiculo),
  CONSTRAINT FKbndhle29qvq74qkp2pffxfwy9 FOREIGN KEY (persona_id) REFERENCES persona (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. Junction Table: vehiculo_documento
CREATE TABLE IF NOT EXISTS vehiculo_documento (
  id BIGINT NOT NULL AUTO_INCREMENT,
  id_vehiculo INT NOT NULL,
  tipo_documento VARCHAR(50) NOT NULL,
  numero_documento VARCHAR(50) NOT NULL,
  fecha_expedicion DATE NOT NULL,
  fecha_vencimiento DATE NOT NULL,
  documento_pdf LONGBLOB NOT NULL,
  estado_documento VARCHAR(20) DEFAULT NULL,
  id_documento BIGINT NOT NULL,
  PRIMARY KEY (id),
  KEY fk_documento_vehiculo (id_vehiculo),
  CONSTRAINT fk_documento_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculos (id_vehiculo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. Junction Table: vehiculo_persona
CREATE TABLE IF NOT EXISTS vehiculo_persona (
  id_vehiculo INT NOT NULL,
  id_persona BIGINT NOT NULL,
  fecha_asociacion DATETIME DEFAULT CURRENT_TIMESTAMP(),
  estado VARCHAR(2) NOT NULL CHECK (estado IN ('PO','EA','RO')),
  PRIMARY KEY (id_vehiculo, id_persona),
  KEY fk_vp_persona (id_persona),
  CONSTRAINT fk_vp_persona FOREIGN KEY (id_persona) REFERENCES persona (id) ON DELETE CASCADE,
  CONSTRAINT fk_vp_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculos (id_vehiculo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
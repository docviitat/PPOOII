-- Seed Base Data: persona
INSERT INTO persona (id, identificacion, tipo_identificacion, nombres, apellidos, correo, tipo_persona)
VALUES 
(1, '1001234567', 'CC', 'Juan', 'Perez', 'juan.perez@example.com', 'C'),
(2, '1007654321', 'CE', 'Maria', 'Gomez', 'maria.gomez@example.com', 'A');

-- Seed Base Data: vehiculos
INSERT INTO vehiculos (id_vehiculo, tipo_vehiculo, placa, tipo_servicio, tipo_combustible, capacidad_pasajeros, color, modelo, marca, linea)
VALUES 
(1, 'Automovil', 'ABC123', 'A', 'Gasolina', 5, 'Negro', 2022, 'Toyota', 'Corolla'),
(2, 'Motocicleta', 'XYZ789', 'M', 'Gasolina', 2, 'Rojo', 2021, 'Yamaha', 'FZ');

-- Seed Base Data: documentos
INSERT INTO documentos (id_documento, codigo_documento, nombre_documento, tipos_vehiculo, obligatoriedad, descripcion)
VALUES 
(1, 'SOAT', 'Seguro Obligatorio de Accidentes de Transito', 'AM', 'RA', 'Seguro obligatorio para todos los vehiculos'),
(2, 'TM', 'Tecno Mecanica', 'AM', 'RA', 'Revision tecnico-mecanica y de gases');

-- Seed Dependent Data: usuario
-- FIX: passwords in PLAINTEXT because JWTAuthenticationConfig compares with .equals() (the app
-- never hashes passwords anywhere - PersonaServiceImpl stores the auto-generated one as-is too).
-- 'mgomez' is your BOOTSTRAP ADMIN for testing: login=mgomez / password=admin123
INSERT INTO usuario (login, idpersona, password, apikey)
VALUES 
('jperez', 1, 'conductor123', 'api_key_12345'),
('mgomez', 2, 'admin123', 'api_key_67890');

-- Seed Relationship Data: vehiculo_conductor
-- FIX: estado was 'AC', which is not one of the valid values enforced by the new CHECK
-- constraint (PO, EA, RO). Changed to 'PO' (Puede Operar).
INSERT INTO vehiculo_conductor (id, estado, fecha_asociacion, persona_id, vehiculo_id)
VALUES 
(1, 'PO', '2026-01-15', 1, 1);

-- NOTE: the old "vehiculo_persona" seed insert was removed - that table no longer exists,
-- it was a duplicate of vehiculo_conductor (see schema.sql fix notes).
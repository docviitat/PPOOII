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
INSERT INTO usuario (login, idpersona, password, apikey)
VALUES 
('jperez', 1, '$2a$10$e8R6.Q4a4K92U.zQO3pY3eE0K0bS/oYd6V5w8G.H1', 'api_key_12345'),
('mgomez', 2, 'cGFzc3dvcmQ=', 'api_key_67890');

-- Seed Relationship Data: vehiculo_conductor
INSERT INTO vehiculo_conductor (id, estado, fecha_asociacion, persona_id, vehiculo_id)
VALUES 
(1, 'AC', '2026-01-15', 1, 1);

-- Seed Relationship Data: vehiculo_persona
INSERT INTO vehiculo_persona (id_vehiculo, id_persona, fecha_asociacion, estado)
VALUES 
(1, 1, '2026-01-10 10:00:00', 'PO');
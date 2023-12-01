-- Crear la base de datos fastrack si no existe
CREATE DATABASE IF NOT EXISTS fastrack;

-- Usar la base de datos fastrack
USE fastrack;

-- Crear la tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    idcliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion VARCHAR(255),
    numerotelefono VARCHAR(20),
    correoelectronico VARCHAR(255)
);

-- Crear la tabla tipoestadosorden
CREATE TABLE IF NOT EXISTS tipoestadosorden (
    idtipoestadoorden INT AUTO_INCREMENT PRIMARY KEY,
    nombreestado VARCHAR(50)
);

-- Crear la tabla vehiculos
CREATE TABLE IF NOT EXISTS vehiculos (
    idvehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20),
    marca VARCHAR(50),
    modelo VARCHAR(50),
    capacidadpeso DECIMAL(10, 2),
    capacidadvolumen DECIMAL(10, 2)
);

-- Crear la tabla rutasentrega
CREATE TABLE IF NOT EXISTS rutasentrega (
    idruta INT AUTO_INCREMENT PRIMARY KEY,
    nombreruta VARCHAR(255),
    descripcionruta TEXT,
    puntosentrega TEXT,
    distanciatotal DECIMAL(10, 2),
    duracionestimada INT,
    costoruta DECIMAL(10, 2)
);

-- Crear la tabla preciosporkilometro
CREATE TABLE IF NOT EXISTS preciosporkilometro (
    idprecioporkilometro INT AUTO_INCREMENT PRIMARY KEY,
    tiposervicio VARCHAR(255),
    tarifaporkilometro DECIMAL(10, 2),
    fechainicio DATE,
    fechafin DATE
);

-- Crear la tabla ordenes
CREATE TABLE IF NOT EXISTS ordenes (
    idorden INT AUTO_INCREMENT PRIMARY KEY,
    idcliente INT,
    fechacreacion DATE,
    idestadoorden INT,
    quienrecepciona VARCHAR(255),
    track VARCHAR(255), -- Número de seguimiento único
    idvehiculo INT, -- ID del vehículo asignado
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente),
    FOREIGN KEY (idestadoorden) REFERENCES tipoestadosorden(idtipoestadoorden),
    FOREIGN KEY (idvehiculo) REFERENCES vehiculos(idvehiculo)
);

-- Crear la tabla detallesordenes
CREATE TABLE IF NOT EXISTS detallesordenes (
    iddetalleorden INT AUTO_INCREMENT PRIMARY KEY,
    idorden INT,
    productopaquete VARCHAR(255),
    cantidad INT,
    peso DECIMAL(10, 2),
    valor DECIMAL(10, 2),
    descripcion TEXT,
    idruta INT, -- ID de la ruta de entrega
    idprecioporkilometro INT, -- ID del precio por kilómetro
    FOREIGN KEY (idorden) REFERENCES ordenes(idorden),
    FOREIGN KEY (idruta) REFERENCES rutasentrega(idruta),
    FOREIGN KEY (idprecioporkilometro) REFERENCES preciosporkilometro(idprecioporkilometro)
);

-- Crear la tabla envios
CREATE TABLE IF NOT EXISTS envios (
    idenvio INT AUTO_INCREMENT PRIMARY KEY,
    idorden INT,
    fechaenvio DATE,
    metodoenvio VARCHAR(50),
    numeroseguimiento VARCHAR(255),
    direccionentrega VARCHAR(255),
    FOREIGN KEY (idorden) REFERENCES ordenes(idorden)
);

-- Crear la tabla tiposempleados
CREATE TABLE IF NOT EXISTS tiposempleados (
    idtipoempleado INT AUTO_INCREMENT PRIMARY KEY,
    nombretipoempleado VARCHAR(50)
);

-- Crear la tabla empleados
CREATE TABLE IF NOT EXISTS empleados (
    idempleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    idtipoempleado INT,
    numerotelefono VARCHAR(20),
    correoelectronico VARCHAR(255),
    FOREIGN KEY (idtipoempleado) REFERENCES tiposempleados(idtipoempleado)
);

-- Crear la tabla historialpagos
CREATE TABLE IF NOT EXISTS historialpagos (
    idpago INT AUTO_INCREMENT PRIMARY KEY,
    fechapago DATE,
    montopagado DECIMAL(10, 2),
    metodopago VARCHAR(50),
    estadopago VARCHAR(50),
    idcliente INT,
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente)
);

-- Crear la tabla locales
CREATE TABLE IF NOT EXISTS locales (
    idlocal INT AUTO_INCREMENT PRIMARY KEY,
    nombrelocal VARCHAR(255),
    direccionlocal VARCHAR(255),
    ciudadlocal VARCHAR(100),
    codigopostal VARCHAR(10),
    telefonolocal VARCHAR(20),
    idempleado INT,
    FOREIGN KEY (idempleado) REFERENCES empleados(idempleado)
);

-- Crear la tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nombreusuario VARCHAR(50),
    contra VARCHAR(255),
    idempleado INT,
    FOREIGN KEY (idempleado) REFERENCES empleados(idempleado)
);

-- ¡Fin del script!


-- Insertar datos en la tabla clientes
INSERT INTO clientes (nombre, direccion, numerotelefono, correoelectronico)
VALUES
    ('Cliente 1', 'Dirección 1', '123-456-7890', 'cliente1@example.com'),
    ('Cliente 2', 'Dirección 2', '987-654-3210', 'cliente2@example.com'),
    ('Cliente 3', 'Dirección 3', '111-222-3333', 'cliente3@example.com');

-- Insertar datos en la tabla tipoestadosorden
INSERT INTO tipoestadosorden (nombreestado)
VALUES
    ('Estado 1'),
    ('Estado 2'),
    ('Estado 3');

-- Insertar datos en la tabla vehiculos
INSERT INTO vehiculos (placa, marca, modelo, capacidadpeso, capacidadvolumen)
VALUES
    ('ABC123', 'Marca 1', 'Modelo 1', 1000.00, 10.00),
    ('XYZ789', 'Marca 2', 'Modelo 2', 800.00, 8.00),
    ('DEF456', 'Marca 3', 'Modelo 3', 1200.00, 12.00);

-- Insertar datos en la tabla rutasentrega
INSERT INTO rutasentrega (nombreruta, descripcionruta, puntosentrega, distanciatotal, duracionestimada, costoruta)
VALUES
    ('Ruta 1', 'Descripción de Ruta 1', 'Punto 1, Punto 2, Punto 3', 50.00, 120, 500.00),
    ('Ruta 2', 'Descripción de Ruta 2', 'Punto 4, Punto 5, Punto 6', 70.00, 150, 700.00),
    ('Ruta 3', 'Descripción de Ruta 3', 'Punto 7, Punto 8, Punto 9', 90.00, 180, 900.00);

-- Insertar datos en la tabla preciosporkilometro
INSERT INTO preciosporkilometro (tiposervicio, tarifaporkilometro, fechainicio, fechafin)
VALUES
    ('Servicio A', 0.10, '2023-01-01', '2023-12-31'),
    ('Servicio B', 0.08, '2023-01-01', '2023-12-31'),
    ('Servicio C', 0.12, '2023-01-01', '2023-12-31');

-- Insertar datos en la tabla ordenes
INSERT INTO ordenes (idcliente, fechacreacion, idestadoorden, quienrecepciona, track, idvehiculo)
VALUES
    (1, '2023-09-29', 1, 'Recepcionista 1', 'TRACK123', 1),
    (2, '2023-09-30', 2, 'Recepcionista 2', 'TRACK456', 2),
    (3, '2023-10-01', 3, 'Recepcionista 3', 'TRACK789', 3);

-- Insertar datos en la tabla detallesordenes
INSERT INTO detallesordenes (idorden, productopaquete, cantidad, peso, valor, descripcion, idruta, idprecioporkilometro)
VALUES
    (1, 'Producto 1', 5, 10.00, 50.00, 'Descripción Producto 1', 1, 1),
    (2, 'Producto 2', 3, 8.00, 24.00, 'Descripción Producto 2', 2, 2),
    (3, 'Producto 3', 4, 12.00, 48.00, 'Descripción Producto 3', 3, 3);

-- Insertar datos en la tabla envios
INSERT INTO envios (idorden, fechaenvio, metodoenvio, numeroseguimiento, direccionentrega)
VALUES
    (1, '2023-09-30', 'Método 1', 'SEGUIMIENTO123', 'Dirección de Entrega 1'),
    (2, '2023-10-01', 'Método 2', 'SEGUIMIENTO456', 'Dirección de Entrega 2'),
    (3, '2023-10-02', 'Método 3', 'SEGUIMIENTO789', 'Dirección de Entrega 3');

-- Insertar datos en la tabla tiposempleados
INSERT INTO tiposempleados (nombretipoempleado)
VALUES
    ('Tipo 1'),
    ('Tipo 2'),
    ('Tipo 3');

-- Insertar datos en la tabla empleados
INSERT INTO empleados (nombre, idtipoempleado, numerotelefono, correoelectronico)
VALUES
    ('Empleado 1', 1, '111-111-1111', 'empleado1@example.com'),
    ('Empleado 2', 2, '222-222-2222', 'empleado2@example.com'),
    ('Empleado 3', 3, '333-333-3333', 'empleado3@example.com');

-- Insertar datos en la tabla historialpagos
INSERT INTO historialpagos (fechapago, montopagado, metodopago, estadopago, idcliente)
VALUES
    ('2023-09-30', 100.00, 'Pago 1', 'Aprobado', 1),
    ('2023-10-01', 75.00, 'Pago 2', 'Aprobado', 2),
    ('2023-10-02', 120.00, 'Pago 3', 'Aprobado', 3);

-- Insertar datos en la tabla locales
INSERT INTO locales (nombrelocal, direccionlocal, ciudadlocal, codigopostal, telefonolocal, idempleado)
VALUES
    ('Local 1', 'Dirección Local 1', 'Ciudad 1', 'CP12345', '555-111-1111', 1),
    ('Local 2', 'Dirección Local 2', 'Ciudad 2', 'CP54321', '555-222-2222', 2),
    ('Local 3', 'Dirección Local 3', 'Ciudad 3', 'CP67890', '555-333-3333', 3);

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (nombreusuario, contra, idempleado)
VALUES
    ('Usuario1', 'Contraseña1', 1),
    ('Usuario2', 'Contraseña2', 2),
    ('Usuario3', 'Contraseña3', 3);

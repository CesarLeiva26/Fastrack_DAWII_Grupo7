CREATE TABLE IF NOT EXISTS Rol (
    idrol INT AUTO_INCREMENT PRIMARY KEY,
    nomrol VARCHAR(100) NOT NULL
);

insert into rol (idrol, nomrol) values
  (1,'ROLE_ADMIN'),
  (2,'ROLE_USER');

CREATE TABLE IF NOT EXISTS Usuario (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    email VARCHAR(200),
    nomusuario VARCHAR(100) NOT NULL,
    password VARCHAR(300) NOT NULL,
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS Usuario_Rol (
    idusuario INT,
    idrol INT,
    PRIMARY KEY (idusuario, idrol),
    FOREIGN KEY (idusuario) REFERENCES Usuario (idusuario),
    FOREIGN KEY (idrol) REFERENCES Rol (idrol)
);

CREATE TABLE IF NOT EXISTS clientes (
    idcliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    numerotelefono VARCHAR(20),
    correoelectronico VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS historialpagos (
    idpago INT AUTO_INCREMENT PRIMARY KEY,
    fechapago DATE,
    montopagado DECIMAL(10, 2),
    metodopago VARCHAR(50),
    estadopago VARCHAR(50),
    idcliente INT,
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente)
);

CREATE TABLE IF NOT EXISTS tipoestadosorden (
    idestadoorden INT AUTO_INCREMENT PRIMARY KEY,
    nombreestado VARCHAR(50) NOT NULL
);

INSERT INTO tipoestadosorden (nombreestado)
VALUES
    ('En Proceso'),
    ('Completada'),
    ('Cancelada'),
    ('En Espera de Recolección'),
    ('En Ruta de Entrega'),
    ('Reprogramada'),
    ('Entrega Fallida'),
    ('En Inspección'),
    ('En Almacén'),
    ('En Retorno');

CREATE TABLE IF NOT EXISTS vehiculos (
    idvehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    capacidadpeso DECIMAL(10, 2) NOT NULL,
    capacidadvolumen DECIMAL(10, 2) NOT NULL
);

INSERT INTO vehiculos (placa, marca, modelo, capacidadpeso, capacidadvolumen)
VALUES
    ('GHI789', 'Kenworth', 'W990', 22000.00, 90.0),
    ('JKL012', 'Peterbilt', '389', 21000.00, 88.0),
    ('MNO345', 'Freightliner', 'Cascadia', 19000.00, 82.0),
    ('PQR678', 'Mack', 'Anthem', 20000.00, 85.5),
    ('STU901', 'International', 'LT Series', 23000.00, 95.0),
    ('VWX234', 'Western Star', '5700XE', 21000.00, 87.5),
    ('YZA567', 'Hino', 'XL Series', 18000.00, 78.0);

CREATE TABLE IF NOT EXISTS rutasentrega (
    idruta INT AUTO_INCREMENT PRIMARY KEY,
    nombreruta VARCHAR(255) NOT NULL,
    descripcionruta TEXT,
    distanciatotal DECIMAL(10, 2) NOT NULL,
    duracionestimada INT NOT NULL,
    costoruta DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS preciosporkilometro (
    idprecioporkilometro INT AUTO_INCREMENT PRIMARY KEY,
    tiposervicio VARCHAR(255) NOT NULL,
    tarifaporkilometro DECIMAL(10, 2) NOT NULL,
    fechainicio DATE NOT NULL,
    fechafin DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS ordenes (
    idorden INT AUTO_INCREMENT PRIMARY KEY,
    idcliente INT,
    fechacreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idestadoorden INT,
    quienrecepciona VARCHAR(255),
    track VARCHAR(255),
    idvehiculo INT,
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente),
    FOREIGN KEY (idestadoorden) REFERENCES tipoestadosorden(idestadoorden),
    FOREIGN KEY (idvehiculo) REFERENCES vehiculos(idvehiculo)
);

CREATE TABLE IF NOT EXISTS detallesordenes (
    iddetalleorden INT AUTO_INCREMENT PRIMARY KEY,
    idorden INT,
    productopaquete VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    idruta INT,
    idprecioporkilometro INT,
    FOREIGN KEY (idorden) REFERENCES ordenes(idorden),
    FOREIGN KEY (idruta) REFERENCES rutasentrega(idruta),
    FOREIGN KEY (idprecioporkilometro) REFERENCES preciosporkilometro(idprecioporkilometro)
);

CREATE TABLE IF NOT EXISTS envios (
    idenvio INT AUTO_INCREMENT PRIMARY KEY,
    idorden INT,
    fechaenvio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    metodoenvio VARCHAR(50) NOT NULL,
    numeroseguimiento VARCHAR(255) UNIQUE,
    direccionentrega VARCHAR(255) NOT NULL,
    FOREIGN KEY (idorden) REFERENCES ordenes(idorden)
);

CREATE TABLE IF NOT EXISTS tiposempleados (
    idtipoempleado INT AUTO_INCREMENT PRIMARY KEY,
    nombretipoempleado VARCHAR(50) NOT NULL
);

INSERT INTO tiposempleados (nombretipoempleado) VALUES
    ('Gerente de Logística'),
    ('Empleado de Almacén'),
    ('Supervisor de Envíos');

CREATE TABLE IF NOT EXISTS empleados (
    idempleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    idtipoempleado INT,
    numerotelefono VARCHAR(20),
    correoelectronico VARCHAR(255) UNIQUE,
    FOREIGN KEY (idtipoempleado) REFERENCES tiposempleados(idtipoempleado)
);

insert into empleados (idempleado, nombre, idtipoempleado, numerotelefono, correoelectronico)
values  (1, 'César Leiva',   1, '999-944-584', 'cesar.leiva@enviosexpress.com')	,
		(2,	'Rafael Rios',   1, '974-878-892', 'rafael.rios@enviosexpress.com'),
		(3,	'Bruno Aguilar', 1, '123-456-2322', 'bruno.aguilar@enviosexpress.com');


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

INSERT INTO locales (idlocal ,nombrelocal, direccionlocal, ciudadlocal, codigopostal, telefonolocal, idempleado)
VALUES
    (1, 'Local LA', 'Av. Principal 123', 'Lima', '15001', '577-788-878', 1),
    (2, 'Local AA', 'Jr. Comercial 456', 'Arequipa', '04001', '054-987-6543', 2),
    (3, 'Local TO', 'Calle Central 789', 'Trujillo', '13003', '044-123-4567', 3),
    (4, 'Local CO', 'Av. Norte 567', 'Cusco', '08001', '084-765-4321', 2),
    (5, 'Local IA', 'Calle Sur 890', 'Ica', '11001', '056-234-5678', 1),
    (6, 'Local TA', 'Av. Este 456', 'Tacna', '23001', '123-456-2322', 3);
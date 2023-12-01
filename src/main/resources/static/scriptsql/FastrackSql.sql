CREATE TABLE IF NOT EXISTS clientes (
    idcliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    numerotelefono VARCHAR(20),
    correoelectronico VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS tipoestadosorden (
    idtipoestadoorden INT AUTO_INCREMENT PRIMARY KEY,
    nombreestado VARCHAR(50) NOT NULL
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

CREATE TABLE IF NOT EXISTS vehiculos (
    idvehiculo INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    capacidadpeso DECIMAL(10, 2) NOT NULL,
    capacidadvolumen DECIMAL(10, 2) NOT NULL
);

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
    track VARCHAR(255) UNIQUE,
    idvehiculo INT,
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente),
    FOREIGN KEY (idestadoorden) REFERENCES tipoestadosorden(idtipoestadoorden),
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
    ('Chofer de Entregas'),
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

select * from locales

CREATE TABLE IF NOT EXISTS Rol (
    idrol INT AUTO_INCREMENT PRIMARY KEY,
    nomrol VARCHAR(100) NOT NULL
);

insert into rol (idrol, nomrol) values
  (1, 'ROLE_ADMIN'),
  (2,'ROLE_USER');


 select * from rol

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

select  * from clientes c join historialpagos h on c.idcliente = h.idcliente
select * from usuario
select * from Rol
select * from empleados
SELECT clientes.idcliente, clientes.nombre, clientes.direccion, clientes.numerotelefono, clientes.correoelectronico, historialpagos.idpago, historialpagos.fechapago, historialpagos.montopagado, historialpagos.metodopago, historialpagos.estadopago
FROM clientes
INNER JOIN historialpagos ON clientes.idcliente = historialpagos.idcliente;

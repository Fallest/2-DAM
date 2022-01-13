CREATE TABLE cliente (
	codigo INTEGER,
	nombre VARCHAR2(30),
	contrasena VARCHAR2(30),
	ultimoAcceso TIMESTAMP,
    foto VARCHAR2(20),
	PRIMARY KEY (codigo)
);

CREATE TABLE cuenta (
    numero INTEGER,
    fecha DATE,
    saldo NUMERIC(8,2),
    saldoMinimo NUMERIC(8,2),
    interes NUMERIC(4,2),
    PRIMARY KEY (numero),
    cliCodigo INTEGER CONSTRAINT cuenta_FK_cliente REFERENCES cliente
);

CREATE TABLE movimiento (
    numero INTEGER,
    cueNumero INTEGER CONSTRAINT movimiento_FK_cuenta REFERENCES cuenta,
    fecha DATE,
    importe NUMERIC(8,2),
    concepto VARCHAR2(30),
    PRIMARY KEY (numero, cueNumero)
);

INSERT INTO cliente VALUES (101,'Ana','Ana','2021-09-01 10:39:58.376','1001.jpg');
INSERT INTO cliente VALUES (102,'Juan','Juan','2021-10-02 12:59:59.999','1002.jpg');
INSERT INTO cliente VALUES (103,'Sara','Sara','2021-10-03 15:39:18.000','1003.jpg');
INSERT INTO cliente VALUES (104,'Manuel','Manuel','2021-11-04 19:11:23.655','1004.jpg');
INSERT INTO cliente VALUES (105,'Rosa','Rosa','2021-11-05 23:45:08.791','1005.jpg');

INSERT INTO cuenta VALUES (1001,'2002-12-18',11.72,0.0,1.75,101);
INSERT INTO cuenta VALUES (1002,'2009-11-2',15.94,0.0,0.55,102);
INSERT INTO cuenta VALUES (1003,'2000-12-8',43.27,25.99,0.75,103);
INSERT INTO cuenta VALUES (1004,'2014-11-25',79.93,50.75,0.25,104);
INSERT INTO cuenta VALUES (1005,'2003-12-21',0.0,-100.0,1.25,101);
INSERT INTO cuenta VALUES (1006,'2008-11-10',20.99,0.0,1.50,101);
INSERT INTO cuenta VALUES (1007,'2007-12-13',145.02,-100.0,1.25,102);
INSERT INTO cuenta VALUES (1008,'2021-11-1',830.79,0.0,1.00,103);
INSERT INTO cuenta VALUES (1009,'2002-12-21',13.80,-50.0,0.75,104);
INSERT INTO cuenta VALUES (1010,'2005-11-17',0.51,0.0,0.50,105);

INSERT INTO movimiento VALUES (1,1001,'2010-4-18',+92.74,'apertura');
INSERT INTO movimiento VALUES (2,1001,'2021-5-23',+106.41,'ingreso');
INSERT INTO movimiento VALUES (3,1001,'2010-1-7',-23.12,'domiciliacion');
INSERT INTO movimiento VALUES (4,1001,'2024-12-10',-52.77,'cajero');
INSERT INTO movimiento VALUES (5,1001,'2020-4-10',-51.91,'compra');
INSERT INTO movimiento VALUES (6,1001,'2013-12-25',-59.63,'domiciliacion');
INSERT INTO movimiento VALUES (1,1002,'2018-10-5',+117.69,'apertura');
INSERT INTO movimiento VALUES (2,1002,'2019-11-9',-71.9,'compra');
INSERT INTO movimiento VALUES (3,1002,'2023-3-9',-105.35,'cajero');
INSERT INTO movimiento VALUES (4,1002,'2017-4-2',+75.5,'ingreso');
INSERT INTO movimiento VALUES (1,1003,'2012-5-25',+43.27,'apertura');
INSERT INTO movimiento VALUES (1,1004,'2023-11-21',+79.93,'apertura');
INSERT INTO movimiento VALUES (1,1006,'2024-4-7',+52.24,'apertura');
INSERT INTO movimiento VALUES (2,1006,'2024-10-26',-22.29,'cajero');
INSERT INTO movimiento VALUES (3,1006,'2018-6-1',+40.59,'ingreso');
INSERT INTO movimiento VALUES (4,1006,'2016-9-18',-49.55,'domiciliacion');
INSERT INTO movimiento VALUES (1,1007,'2014-6-5',+1003.41,'apertura');
INSERT INTO movimiento VALUES (2,1007,'2020-2-20',-35.7,'compra');
INSERT INTO movimiento VALUES (3,1007,'2023-8-25',-60.34,'domiciliacion');
INSERT INTO movimiento VALUES (4,1007,'2018-7-1',-130.67,'compra');
INSERT INTO movimiento VALUES (5,1007,'2024-10-3',+85.32,'ingreso');
INSERT INTO movimiento VALUES (6,1007,'2019-3-12',-64.52,'compra');
INSERT INTO movimiento VALUES (7,1007,'2012-1-5',-48.73,'domiciliacion');
INSERT INTO movimiento VALUES (8,1007,'2011-4-26',-309.48,'compra');
INSERT INTO movimiento VALUES (9,1007,'2017-10-16',-98.24,'cajero');
INSERT INTO movimiento VALUES (10,1007,'2010-8-1',+12.17,'ingreso');
INSERT INTO movimiento VALUES (11,1007,'2021-2-20',-297.78,'compra');
INSERT INTO movimiento VALUES (12,1007,'2010-5-8',+186.67,'ingreso');
INSERT INTO movimiento VALUES (13,1007,'2012-2-9',+520.14,'ingreso');
INSERT INTO movimiento VALUES (14,1007,'2023-12-2',-134.67,'domiciliacion');
INSERT INTO movimiento VALUES (15,1007,'2022-6-4',-482.56,'cajero');
INSERT INTO movimiento VALUES (1,1008,'2016-2-23',+830.79,'apertura');
INSERT INTO movimiento VALUES (1,1009,'2018-4-19',+85.13,'apertura');
INSERT INTO movimiento VALUES (2,1009,'2010-1-1',+504.51,'ingreso');
INSERT INTO movimiento VALUES (3,1009,'2011-5-7',-91.97,'domiciliacion');
INSERT INTO movimiento VALUES (4,1009,'2023-9-25',-438.17,'compra');
INSERT INTO movimiento VALUES (5,1009,'2013-2-15',-45.70,'cajero');
INSERT INTO movimiento VALUES (1,1010,'2012-3-7',+51.46,'apertura');
INSERT INTO movimiento VALUES (2,1010,'2011-12-1',-50.95,'domiciliacion');
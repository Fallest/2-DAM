CREATE TABLE empleado (
      numero		INTEGER,
	  nombre		VARCHAR(20),
	  apellido		VARCHAR(20),
	  foto 	        VARCHAR(20),
	  sueldoBase	NUMERIC(6,2), 
	  sueldoMaximo	NUMERIC(6,2), 
	  sueldoExtra	NUMERIC(6,2), 
	  sueldoReal	NUMERIC(6,2),
	  jefeNumero    INTEGER CONSTRAINT empleadoFKempleadoJefe REFERENCES empleado,
	  PRIMARY KEY (numero)
	);
	
CREATE TABLE trabajo(
      numero		INTEGER,
	  descripcion	VARCHAR(30),
	  valor         NUMERIC(6,2),
	  fecha         DATE, 
	  empNumero		INTEGER CONSTRAINT trabajoFKempleado REFERENCES empleado,
	  PRIMARY KEY (numero, empNumero)	  
	);

INSERT INTO empleado VALUES (1001,'Luna','RUANO','1001.jpg',1500,2100,300,1800,1001);
INSERT INTO empleado VALUES (1002,'Antonio','PRADO','1002.jpg',1000,2500,300,1800,1002);
INSERT INTO empleado VALUES (1003,'Gonzalo','GONZALEZ','1003.jpg',900,1300,0,0,1001);
INSERT INTO empleado VALUES (1004,'Sebastian','SASTRE','1004.jpg',1200,2000,0,0,1001);
INSERT INTO empleado VALUES (1005,'Amalia','PAZ','1005.jpg',1100,1700,200,1800,1001);
INSERT INTO empleado VALUES (1006,'Manuel','DOMINGUEZ','1006.jpg',1900,2300,0,0,1001);
INSERT INTO empleado VALUES (1007,'Sabrina','GARCIA','1007.jpg',800,1900,0,0,1002);
INSERT INTO empleado VALUES (1008,'Pascual','LOPEZ','1008.jpg',1200,1900,0,0,1002);
INSERT INTO empleado VALUES (1009,'Manuel','PEREZ','1009.jpg',1500,2900,0,0,1002);
INSERT INTO empleado VALUES (1010,'Maria','SALAZAR','1010.jpg',600,1500,300,900,1002);
INSERT INTO empleado VALUES (1011,'Nuria','MORENO','1011.jpg',1900,2300,0,0,1001);

INSERT INTO trabajo VALUES (1,'Soldadura',172,'02/24/2021',1005);
INSERT INTO trabajo VALUES (2,'Relleno',122,'01/30/2022',1005);
INSERT INTO trabajo VALUES (1,'Montaje',236,'12/9/2021',1006);
INSERT INTO trabajo VALUES (1,'Soldadura',50,'01/25/2022',1004);
INSERT INTO trabajo VALUES (1,'Despliegue',230,'02/17/2021',1007);
INSERT INTO trabajo VALUES (1,'Instalación',107,'02/20/2022',1010);
INSERT INTO trabajo VALUES (3,'Configuración',112,'02/26/2022',1005);
INSERT INTO trabajo VALUES (2,'Maquetado',156,'02/12/2022',1010);
INSERT INTO trabajo VALUES (2,'Linkado',107,'02/15/2022',1006);
INSERT INTO trabajo VALUES (2,'Actualización',173,'10/17/2021',1007);
INSERT INTO trabajo VALUES (1,'Desinstalación',177,'02/2/2022',1003);
INSERT INTO trabajo VALUES (1,'Conexión',240,'10/8/2021',1002);
INSERT INTO trabajo VALUES (3,'Despliegue',57,'02/20/2021',1007);
INSERT INTO trabajo VALUES (3,'Instalación',100,'12/27/2021',1010);
INSERT INTO trabajo VALUES (2,'Configuración',133,'02/14/2022',1002);
INSERT INTO trabajo VALUES (4,'Configuración',129,'10/12/2021',1005);
INSERT INTO trabajo VALUES (1,'Maquetado',110,'02/3/2021',1009);
INSERT INTO trabajo VALUES (5,'Linkado',57,'02/12/2022',1005);
INSERT INTO trabajo VALUES (1,'Actualización',145,'02/1/2022',1008);
INSERT INTO trabajo VALUES (2,'Desinstalación',227,'10/4/2021',1008);
INSERT INTO trabajo VALUES (3,'Montaje',59,'02/2/2021',1006);
INSERT INTO trabajo VALUES (3,'Soldadura',108,'10/5/2021',1002);
INSERT INTO trabajo VALUES (4,'Relleno',68,'10/25/2021',1010);
INSERT INTO trabajo VALUES (4,'Montaje',132,'10/28/2021',1007);
INSERT INTO trabajo VALUES (6,'Despliegue',104,'02/11/2022',1005);
INSERT INTO trabajo VALUES (3,'Instalación',53,'02/24/2021',1008);
INSERT INTO trabajo VALUES (2,'Configuración',124,'02/3/2022',1003);
INSERT INTO trabajo VALUES (5,'Despliegue',180,'02/7/2022',1010);
INSERT INTO trabajo VALUES (7,'Instalación',107,'12/1/2021',1005);
INSERT INTO trabajo VALUES (4,'Configuración',217,'02/4/2022',1002);
INSERT INTO trabajo VALUES (8,'Maquetado',148,'02/29/2022',1005);
INSERT INTO trabajo VALUES (1,'Linkado',230,'02/13/2022',1001);
INSERT INTO trabajo VALUES (2,'Actualización',88,'02/3/2022',1004);
INSERT INTO trabajo VALUES (2,'Desinstalación',133,'02/8/2021',1009);
INSERT INTO trabajo VALUES (9,'Despliegue',230,'12/7/2021',1005);
INSERT INTO trabajo VALUES (2,'Instalación',68,'02/19/2021',1001);
INSERT INTO trabajo VALUES (3,'Configuración',191,'10/27/2021',1009);
INSERT INTO trabajo VALUES (4,'Relleno',91,'02/2/2022',1009);
INSERT INTO trabajo VALUES (6,'Montaje',174,'10/1/2021',1010);
INSERT INTO trabajo VALUES (3,'Soldadura',248,'12/12/2022',1004);
INSERT INTO trabajo VALUES (4,'Relleno',214,'10/19/2021',1006);
INSERT INTO trabajo VALUES (5,'Montaje',101,'02/2/2022',1002);
INSERT INTO trabajo VALUES (5,'Soldadura',237,'12/20/2021',1006);
INSERT INTO trabajo VALUES (6,'Soldadura',222,'02/9/2022',1002);
INSERT INTO trabajo VALUES (7,'Despliegue',151,'02/28/2022',1002);
INSERT INTO trabajo VALUES (4,'Instalación',119,'02/3/2022',1008);
INSERT INTO trabajo VALUES (5,'Configuración',101,'12/20/2021',1007);
INSERT INTO trabajo VALUES (3,'Despliegue',211,'12/30/2021',1001);
INSERT INTO trabajo VALUES (3,'Instalación',127,'12/15/2022',1003);
INSERT INTO trabajo VALUES (4,'Configuración',196,'02/20/2022',1004);
INSERT INTO trabajo VALUES (5,'Desinstalación',188,'10/4/2021',1008);
INSERT INTO trabajo VALUES (6,'Soldadura',136,'02/16/2022',1008);
INSERT INTO trabajo VALUES (6,'Despliegue',53,'02/25/2022',1006);
INSERT INTO trabajo VALUES (7,'Instalación',206,'02/29/2021',1008);
INSERT INTO trabajo VALUES (5,'Configuración',219,'02/14/2022',1004);
INSERT INTO trabajo VALUES (4,'Relleno',108,'12/5/2021',1001);
INSERT INTO trabajo VALUES (7,'Montaje',210,'02/22/2022',1006);
INSERT INTO trabajo VALUES (6,'Soldadura',92,'02/16/2022',1007);
INSERT INTO trabajo VALUES (5,'Soldadura',238,'02/12/2022',1001);
INSERT INTO trabajo VALUES (7,'Despliegue',66,'02/23/2021',1010);
INSERT INTO trabajo VALUES (5,'Instalación',58,'10/19/2021',1009);
INSERT INTO trabajo VALUES (8,'Configuración',135,'10/11/2022',1006);
INSERT INTO trabajo VALUES (8,'Maquetado',246,'10/5/2022',1010);
INSERT INTO trabajo VALUES (4,'Linkado',97,'10/22/2021',1003);
INSERT INTO trabajo VALUES (10,'Configuración',219,'02/19/2022',1005);
INSERT INTO trabajo VALUES (6,'Maquetado',226,'10/12/2021',1004);
INSERT INTO trabajo VALUES (5,'Linkado',115,'12/11/2021',1003);
INSERT INTO trabajo VALUES (6,'Relleno',159,'12/16/2022',1003);
INSERT INTO trabajo VALUES (7,'Montaje',66,'10/14/2021',1003);
INSERT INTO trabajo VALUES (8,'Soldadura',240,'12/11/2021',1003);
INSERT INTO trabajo VALUES (7,'Relleno',156,'02/28/2022',1007);
INSERT INTO trabajo VALUES (8,'Montaje',231,'02/3/2021',1008);
INSERT INTO trabajo VALUES (7,'Soldadura',90,'02/12/2022',1004);
INSERT INTO trabajo VALUES (8,'Relleno',142,'02/17/2022',1002);
INSERT INTO trabajo VALUES (9,'Configuración',203,'10/11/2021',1010);
INSERT INTO trabajo VALUES (9,'Maquetado',191,'12/10/2022',1006);
INSERT INTO trabajo VALUES (6,'Linkado',212,'12/19/2022',1009);
INSERT INTO trabajo VALUES (10,'Despliegue',53,'12/30/2021',1006);
INSERT INTO trabajo VALUES (11,'Instalación',152,'02/10/2022',1006);
INSERT INTO trabajo VALUES (9,'Configuración',88,'12/4/2021',1002);
INSERT INTO trabajo VALUES (10,'Maquetado',199,'02/20/2022',1010);
INSERT INTO trabajo VALUES (8,'Linkado',89,'12/13/2021',1004);
INSERT INTO trabajo VALUES (7,'Actualización',175,'10/19/2021',1009);
INSERT INTO trabajo VALUES (12,'Desinstalación',203,'02/15/2022',1006);
INSERT INTO trabajo VALUES (10,'Soldadura',246,'02/11/2022',1002);
INSERT INTO trabajo VALUES (9,'Relleno',143,'12/2/2021',1003);
INSERT INTO trabajo VALUES (8,'Montaje',60,'02/19/2022',1007);
INSERT INTO trabajo VALUES (9,'Soldadura',206,'02/3/2022',1007);
INSERT INTO trabajo VALUES (8,'Despliegue',185,'02/18/2022',1009);
INSERT INTO trabajo VALUES (10,'Instalación',248,'02/1/2022',1007);
INSERT INTO trabajo VALUES (11,'Configuración',222,'12/25/2022',1007);
INSERT INTO trabajo VALUES (9,'Maquetado',194,'12/7/2021',1008);
INSERT INTO trabajo VALUES (10,'Linkado',133,'10/11/2021',1003);
INSERT INTO trabajo VALUES (12,'Actualización',218,'12/30/2021',1007);
INSERT INTO trabajo VALUES (11,'Despliegue',241,'10/28/2021',1010);
INSERT INTO trabajo VALUES (13,'Configuración',101,'12/29/2021',1007);
INSERT INTO trabajo VALUES (6,'Maquetado',193,'02/12/2022',1001);
INSERT INTO trabajo VALUES (11,'Configuración',106,'10/9/2021',1002);
INSERT INTO trabajo VALUES (9,'Maquetado',210,'02/14/2022',1009);
INSERT INTO trabajo VALUES (11,'Linkado',210,'02/26/2022',1003);

CREATE TABLE empleado (
    numero    		INTEGER not null primary key,
    nombre	    	VARCHAR(20),
    apellido    	VARCHAR(20),
    foto  	        VARCHAR(20),
    sueldo	    	NUMERIC(6,2), 
    sueldoMaximo  	NUMERIC(6,2) check ("sueldoMaximo" >= 1000), 
    fechaAlta  		DATE 
);

INSERT INTO empleado VALUES (1001,'Manuel','PEREZ','1001.jpg',1500,2900,'10/17/2018');
INSERT INTO empleado VALUES (1002,'Antonio','PRADO','1002.jpg',1000,2500,'11/01/2019');
INSERT INTO empleado VALUES (1003,'Gonzalo','GONZALEZ','default.jpg',900,1300,'01/05/2019');
INSERT INTO empleado VALUES (1004,'Sebastian','SASTRE','1004.jpg',1200,2000,'08/14/2019');
INSERT INTO empleado VALUES (1005,'Amalia','PAZ','1005.jpg',1100,1700,'06/20/2021');
INSERT INTO empleado VALUES (1006,'Manuel','DOMINGUEZ','default.jpg',1900,2300,'05/28/2021');
INSERT INTO empleado VALUES (1007,'Sabrina','GARCIA','1007.jpg',800,1900,'03/10/2019');
INSERT INTO empleado VALUES (1008,'Pascual','LOPEZ','1008.jpg',1200,1900,'07/11/2020');
INSERT INTO empleado VALUES (1009,'Luna','RUANO','1009.jpg',1500,2100,'06/12/2020');
INSERT INTO empleado VALUES (1010,'Maria','SALAZAR','1010.jpg',600,1500,'11/21/2021');
INSERT INTO empleado VALUES (1011,'Sara','SANCHEZ','1011.jpg',900,1800,'10/27/2021');
INSERT INTO empleado VALUES (1012,'Marcos','MORENO','1012.jpg',1200,2500,'10/03/2017');
INSERT INTO empleado VALUES (1013,'Juan','PEREZ','1013.jpg',950,1600,'02/01/2014');
INSERT INTO empleado VALUES (1014,'Alfonso','RUIZ','default.jpg',1600,2000,'03/24/2019');
INSERT INTO empleado VALUES (1015,'Ana','HERNANDEZ','1015.jpg',1700,1800,'04/10/2021');
INSERT INTO empleado VALUES (1016,'Teo','GOMEZ','default.jpg',1550,2400,'03/18/2021');
INSERT INTO empleado VALUES (1017,'Marta','GARCIA','1017.jpg',950,1200,'08/20/2019');
INSERT INTO empleado VALUES (1018,'Francisco','LOPEZ','1018.jpg',1300,1500,'09/21/2020');
INSERT INTO empleado VALUES (1019,'Mar','GARCIA','1019.jpg',1300,2100,'07/11/2020');
INSERT INTO empleado VALUES (1020,'Julia','MORENO','1020.jpg',1600,2500,'10/11/2021');
INSERT INTO empleado VALUES (1021,'Maria','SANCHEZ','1021.jpg',900,1800,'11/23/2021');
INSERT INTO empleado VALUES (1022,'Marcos','MORENO','1022.jpg',1200,2500,'09/04/2017');
INSERT INTO empleado VALUES (1023,'Amalia','GONZALEZ','default.jpg',950,1600,'02/02/2018');
INSERT INTO empleado VALUES (1024,'Manuel','GARCIA','default.jpg',1600,2000,'04/25/2019');
INSERT INTO empleado VALUES (1025,'Ana','GONZALEZ','1025.jpg',1700,1800,'09/11/2021');
INSERT INTO empleado VALUES (1026,'Amalia','GOMEZ','default.jpg',1550,2400,'10/15/2021');
INSERT INTO empleado VALUES (1027,'Manuel','GARCIA','default.jpg',950,1200,'07/25/2019');
INSERT INTO empleado VALUES (1028,'Francisco','LOPEZ','1028.jpg',1300,1500,'06/29/2020');
INSERT INTO empleado VALUES (1029,'Maria','GARCIA','1029.jpg',1300,2100,'07/15/2020');
INSERT INTO empleado VALUES (1030,'Sara','MORENO','1030.jpg',1600,2500,'10/15/2021');



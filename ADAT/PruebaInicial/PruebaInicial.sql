	
	Descripción de las tablas:
	==========================

HABILIDADES
-----------
# COD_HA	CHAR(5)		Código Habilidad
  DESC_HA	VARCHAR2(30)	Descripción de Habilidad (O) (U)

create table Habilidades (
	cod_ha char(5),
	desc_ha varchar(30) not null unique
	constraint pk_habilidades primary key (cod_ha)
);

CENTROS
-------
# COD_CE	CHAR(4)		Código del Centro
* DIRECTOR_CE	NUMBER(6)	Director del Centro
  NOMB_CE	VARCHAR2(30)	Nombre del Centro (O)
  DIRECC_CE	VARCHAR2(50)	Dirección del Centro (O)
  POBLAC_CE	VARCHAR2(15)	Población del Centro (O)

create table Centros (
	cod_ce char(4),
	director_ce number(6),
	nomb_ce varchar2(30) not null,
	direcc_ce varchar2(50) not null,
	poblac_ce varchar2(15) not null
	constraint pk_centros primary key (cod_ce)
);

DEPARTAMENTOS
-------------
# COD_DE	CHAR(5)		Código del Departamento
* DIRECTOR_DE	NUMBER(6)	Director del Departamento
* DEPTJEFE_DE	CHAR(5)		Departamento del que depende
* CENTRO_DE	CHAR(4)		Centro trabajo (O)
  NOMB_DE	VARCHAR2(40)	Nombre del Departamento (O)
  PRESUP_DE	NUMBER(11)	Presupuesto del Departamento (O)
  TIPODIR_DE	CHAR(1)		Tipo de Director del Departamento (O)

create table Departamentos (
	cod_de char(5),
	director_de number(6),
	deptjefe_de char(5),
	centro_de char(4) not null,
	nomb_de varchar2(40) not null,
	presup_de number(11) not null,
	tipodir_de char(1) not null,
	constraint pk_dep primary key (cod_de)
);

EMPLEADOS
---------
# COD_EM	NUMBER(6)	Código del Empleado
* DEPT_EM	CHAR(5)		Departamento del Empleado (O)
  EXTTEL_EM	CHAR(9)		Extensión telefónica
  FECINC_EM	DATE		Fecha de incorporación del Empleado (O)
  FECNAC_EM	DATE		Fecha de nacimiento del Empleado (O)
  DNI_EM	VARCHAR2(9)	DNI del Empleado (U)
  NOMB_EM	VARCHAR2(40)	Nombre del Empleado (O)
  NUMHIJ_EM	NUMBER(2)	Número de hijos del Empleado (O)
  SALARIO_EM	NUMBER(9)	Salario Anual del Empleado (O)

create table Empleados (
	cod_em number(6),
	dept_em char(5) not null,
	exttel_em char(9),
	fecinc_em date not null,
	fecnac_em date not null,
	dni_em varchar2(9) unique,
	nomb_em varchar2(40) not null,
	numhij_em number(2) not null,
	salario_em number(9) not null,
	constraint pk_emp primary key (cod_em)
);

HIJOS
-----
#*PADRE_HI	NUMBER(6)	Código del Empleado
# NUMHIJ_HI	NUMBER(2)	Número del hijo del Empleado
  FECNAC_HI	DATE		Fecha de nacimiento del Hijo (O)
  NOMB_HI	VARCHAR2(40)	Nombre del Hijo (O)

create table Hijos (
	padre_hi number(6),
	numhij_hi number(2),
	fecnac_hi date not null,
	nomb_hi varchar2(40) not null,
	constraint pk_hijos primary key (padre_hi, numhij_hi)
);

HABIEMPL
-----------
#*CODHA_HE	CHAR(5)		Código de la Habilidad
#*CODEM_HE	NUMBER(6)	Código del Empleado

create table Habiempl (
	codha_he char(5),
	codem_he number(6),
	constraint pk_habiempl primary key (codha_he, codem_he)
);

Nota: 
	# PRIMARY KEY
	* FOREIGN KEY
	(O) Obligatorio
	(U) Único

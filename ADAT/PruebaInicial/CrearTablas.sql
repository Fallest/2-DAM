create table Habilidades (
	cod_ha char(5),
	desc_ha varchar(30) not null unique,
	constraint pk_habilidades primary key (cod_ha)
);
/*--------------------------------------------------------*/
create table Centros (
	cod_ce char(4),
	director_ce number(6),
	nomb_ce varchar2(30) not null,
	direcc_ce varchar2(50) not null,
	poblac_ce varchar2(15) not null,
	constraint pk_centros primary key (cod_ce)
);
/*--------------------------------------------------------*/
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
/*--------------------------------------------------------*/
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
/*--------------------------------------------------------*/
create table Hijos (
	padre_hi number(6),
	numhij_hi number(2),
	fecnac_hi date not null,
	nomb_hi varchar2(40) not null,
	constraint pk_hijos primary key (padre_hi, numhij_hi)
);
/*--------------------------------------------------------*/
create table Habiempl (
	codha_he char(5),
	codem_he number(6),
	constraint pk_habiempl primary key (codha_he, codem_he)
);
<
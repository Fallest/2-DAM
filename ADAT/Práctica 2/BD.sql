ALTER SESSION SET NLS_DATE_FORMAT='dd/MM/yyyy';

/* Creación de los objetos y sus respectivas tablas */
---------------------------------
-- Objeto Pasajero
create type OPasajero as object (
    NIF         varchar2(9),
    nombre      varchar2(20),
    apellidos   varchar2(20),
    edad        number(3),
    equipaje    number(3)
);
-- Tabla y constraints
create table Pasajeros of OPasajero;
alter table Pasajeros add               constraint pk_pasajeros             primary key(NIF);
alter table Pasajeros modify nombre     constraint nom_nonulo_pasajeros     not null;
alter table Pasajeros modify apellidos  constraint ape_nonulo_pasajeros     not null;
alter table Pasajeros modify edad       constraint edad_nonulo_pasajeros    not null;
alter table Pasajeros modify equipaje                                       default 50;
alter table Pasajeros add               constraint ck_edad_pasajeros        check(0 < edad and edad < 120);
alter table Pasajeros add               constraint ck_equipaje_pasajeros    check(0 <= equipaje and equipaje <= 100);
-------------------------------
-- Objeto Ciudad
create type OCiudad as object (
    cod         number(3),
    nombre      varchar2(20),
    pais        varchar2(20)
);
-- Tabla y constraints
create table Ciudades of OCiudad;
alter table Ciudades add                constraint pk_ciudades              primary key(cod);
alter table Ciudades modify nombre      constraint nom_nonulo_ciudades      not null;
alter table Ciudades modify pais        constraint pais_nonulo_ciudades     not null;
----------------------------------
-- Objeto Aerolínea
create type OAerolinea as object (
    cod         number(2),
    nombre      varchar2(20)
);
-- Tabla y constraints
create table Aerolineas of OAerolinea;
alter table Aerolineas add              constraint pk_aerolineas            primary key(cod);
alter table Aerolineas modify nombre    constraint nom_nonulo_aerolineas    not null;
------------------------------
-- Objeto Avión ---A PARTIR DE AQUI HAY QUE EJECUTAR
create type OAvion as object (
    id          number(3),
    aerolinea   ref Aerolineas,
    origen      ref Ciudades,
    destino     ref Ciudades
);
-- Tabla y constraints
create table Aviones of OAvion;
alter table Aviones add                 constraint pk_aviones               primary key(id);
alter table Aviones modify aerolinea    constraint alinea_nonulo_aviones    not null;
alter table Aviones modify origen       constraint origen_nonulo_aviones    not null;
alter table Aviones modify destino      constraint destino_nonulo_aviones   not null;
--------------------------------
-- Objeto Billete
create type OBillete as object (
    cod         number(10),
    aerolinea   ref Aerolineas,
    avion       ref Aviones,
    origen      ref Ciudades,
    destino     ref Ciudades,
    fecha_vuelo date
);
-- Tabla y constraints
create table Billetes of OBillete;
alter table Billetes add                constraint pk_billetes              primary key(cod);
alter table Billetes modify aerolinea   constraint alinea_nonulo_billetes   not null;
alter table Billetes modify avion       constraint avion_nonulo_billetes    not null;
alter table Billetes modify origen      constraint origen_nonulo_billetes   not null;
alter table Billetes modify destino     constraint destino_nonulo_billetes  not null;
alter table Billetes modify fecha_vuelo constraint fecha_nonulo_billetes    not null;
alter table Billetes add                constraint ck_origen_billetes       check(origen = avion.origen);
alter table Billetes add                constraint ck_destino_billetes      check(destino = avion.destino);
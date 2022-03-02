ALTER SESSION SET NLS_DATE_FORMAT='dd/MM/yyyy';

/* Creación de los objetos y sus respectivas tablas */
-- Objeto Cliente
create type OCliente as object (
    NIF         varchar2(9),
    nombre      varchar2(20),
    apellidos   varchar2(20),
    edad        number(3)       -- El interés de cada cuenta del cliente varía con la edad.
);
-- Tabla y constraints
create table Clientes of OCliente;
alter table Clientes add                constraint pk_clientes              primary key(NIF);
alter table Clientes modify nombre      constraint nom_nonulo_clientes      not null;
alter table Clientes modify apellidos   constraint ape_nonulo_clientes      not null;
alter table Clientes modify edad        constraint edad_nonulo_clientes     not null;   
-----------------------
-- Objeto Sucursal
create type OBanco as object (
    ID          number(8),  -- Los 8 dígitos que identifican al banco.
    nombre      varchar2(20),
    int_base    number(4, 2) -- Interés base del banco, aplicado a todas las cuentas.
);
-- Tabla y constraints
create table Bancos of OBanco;
alter table Bancos add                  constraint pk_bancos                primary key(ID);
alter table Bancos modify nombre        constraint nom_nonulo_bancos        not null;
alter table Bancos add                  constraint ck_int_base_bancos       check(int_base >= 0);
---------------------
-- Objeto Cuenta
create type OCuenta as object (
    ID          varchar2(24),
    interes     number(4, 2),
    cliente     ref OCliente,
    banco       ref OBanco    
);
-- Tabla y constraints
create table Cuentas of Ocuenta;
alter table Cuentas add                 constraint pk_cuentas               primary key(ID);
alter table Cuentas add                 constraint ck_interes_cuentas       check(interes >= 0);
alter table Cuentas modify cliente      constraint cliente_nonulo_cuentas   not null;
alter table Cuentas modify banco        constraint banco_nonulo_cuentas     not null;
--alter table Cuentas add                 constraint ck_id_banco_cuentas      check(substr(ID, 5, 8) = banco.ID);
-------------------------
-- Objeto Movimiento
create type OMovimiento as object (
    NID         integer,
    cuenta      ref OCuenta,
    fecha       date,
    importe     number(8, 2),
    concepto    varchar2(30)
);
-- Tabla y constraints
create table Movimientos of OMovimiento;
alter table Movimientos add             constraint pk_movimientos           primary key(NID);
alter table Movimientos modify cuenta   constraint cuenta_nonulo_mov        not null;
alter table Movimientos modify fecha                                        default sysdate;
alter table Movimientos modify importe  constraint importe_nonulo_mov       not null;
alter table Movimientos modify concepto                                     default '';
----------------------------
-- Objeto Transferencia
create type OTransferencia as object (
    NID         integer,
    origen      ref OCuenta,
    destino     ref OCuenta,
    fecha       date,
    importe     number(8, 2),
    concepto    varchar2(30)
);
-- Tabla y constraints
create table Transferencias of OTransferencia;
alter table Transferencias add              constraint pk_transferencias        primary key(NID);
alter table Transferencias modify origen    constraint origen_nonulo_trans      not null;
alter table Transferencias modify destino   constraint destino_nonulo_trans     not null;
alter table Transferencias modify fecha                                         default sysdate;
alter table Transferencias modify importe   constraint importe_nonulo_trans     not null;
alter table Transferencias modify concepto                                      default '';
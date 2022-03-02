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
/*----------------------------------------------------------------------------------------------------*/
/* Creación de los triggers */
------------------------------------------
/* 
Comprobar que el ID del banco al crear la cuenta existe.
El ID de la cuenta consiste en 24 dígitos, de los cuales los 8 dígitos después del IBAN
determinan la entidad financiera a la que pertenece la cuenta.
Hay que comprobar que estos 8 dígitos se corresponden con -alguna- de las entidades financieras
existentes en la tabla Bancos.
*/
create or replace trigger comprobarIDBanco
before insert or update on Cuentas
for each row
declare
    id_banco number(8);
    existe_id integer default 0; -- 0 si no existe, 1 si sí existe.
begin
    if inserting or updating('ID') then
        -- Primero extraemos los 8 dígitos del ID de la cuenta
        select to_number(substr(:new.ID, 5, 8)) into id_banco
        from dual;

        -- Luego comprobamos si existe
        for b in (select * from Bancos) loop
            if b.ID = id_banco then
                -- Existe el banco
                existe_id := 1;
            end if;
        end loop;

        -- Si no existe el ID, mostramos una excepción
        if existe_id = 0 then
            raise_application_error(-20501, 'ERROR: No existe esa ID Bancaria. Compruebe su ID de cuenta.');
        else
            commit;
        end if;
    end if;

    exception
      when others then
        dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en el trigger comprobarIDBanco.');
end comprobarIDBanco;

/*
Comprobar que, al realizar una nueva transferencia, las cuentas origen y destino no son las mismas.
*/
create or replace trigger comprobarTransferencia
before insert or update on Transferencias
for each row
declare

begin
    if inserting or updating('origen') or updating('destino') then
        
    end if;
end;
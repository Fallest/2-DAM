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
-- Objeto Banco
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
/* Creación de un paquete para facilitar el trabajo de los triggers y evitar las tablas mutantes */
create or replace package tablas
as
    type ti_clientes is table of OCliente index by binary_integer;
    type ti_bancos is table of OBanco index by binary_integer;
    type ti_cuentas is table of OCuenta index by binary_integer;
    type ti_movimientos is table of OMovimiento index by binary_integer;
    type ti_transferencias is table of OTransferencia index by binary_integer;

    t_clientes ti_clientes;
    t_bancos ti_bancos;
    t_cuentas ti_cuentas;
    t_movimientos ti_movimientos;
    t_transferencias ti_transferencias;

    procedure rellenar_tablas;
    procedure rellenar_clientes;
    procedure rellenar_bancos;
    procedure rellenar_cuentas;
    procedure rellenar_movimientos;
    procedure rellenar_transferencias;

end tablas;

create or replace package body tablas
as
    -- Rellenar de datos todas las tablas
    procedure rellenar_tablas
    as
        cursor c_clientes is select * from Clientes;
        cursor c_bancos is select * from Bancos;
        cursor c_cuentas is select * from Cuentas;
        cursor c_mov is select * from Movimientos;
        cursor c_trans is select * from Transferencias;

        numeros_nif number(8);
        numero_cuenta number(10);
    begin
        tablas.t_clientes.delete;
        tablas.t_bancos.delete;
        tablas.t_cuentas.delete;
        tablas.t_movimientos.delete;
        tablas.t_transferencias.delete;

        for c in c_clientes loop
            select to_number(substr(c.NIF, 0, 8)) into numeros_nif from dual;
            tablas.t_clientes(numeros_nif) := c;
        end loop;

        for b in c_bancos loop
            tablas.t_bancos(b.ID) := b;
        end loop;

        for c in c_cuentas loop
            select to_number(substr(c.ID, 16)) into numero_cuenta from dual;
            tablas.t_cuentas(numero_cuenta) := c;
        end loop;

        for m in c_mov loop
            tablas.t_movimientos(m.NID) := m;
        end loop;

        for t in c_trans loop
            tablas.t_transferencias(t.NID) := t;
        end loop;
    end;

    -- Rellenar datos de clientes
    procedure rellenar_clientes
    as
        cursor c_clientes is select * from Clientes;
        numeros_nif number(8);
    begin
        tablas.t_clientes.delete;
        for c in c_clientes loop
            select to_number(substr(c.NIF, 0, 8)) into numeros_nif from dual;
            tablas.t_clientes(numeros_nif) := c;
        end loop;
    end;

    -- Rellenar datos de bancos
    procedure rellenar_bancos
    as
        cursor c_bancos is select * from Bancos;
    begin
        tablas.t_bancos.delete;
        for b in c_bancos loop
            tablas.t_bancos(b.ID) := b;
        end loop;
    end;

    -- Rellenar datos de cuentas
    procedure rellenar_cuentas
    as
        cursor c_cuentas is select * from Cuentas;
        numero_cuenta number(10);
    begin
        tablas.t_cuentas.delete;
        for c in c_cuentas loop
            select to_number(substr(c.ID, 16)) into numero_cuenta from dual;
            tablas.t_cuentas(numero_cuenta) := c;
        end loop;
    end;

    -- Rellenar datos de movimientos
    procedure rellenar_movimientos
    as
        cursor c_mov is select * from Movimientos;
    begin
        tablas.t_movimientos.delete;
        for m in c_mov loop
            tablas.t_movimientos(m.NID) := m;
        end loop;    
    end;

    -- Rellenar datos de transferencias
    procedure rellenar_transferencias
    as
        cursor c_trans is select * from Transferencias;
    begin
        tablas.t_transferencias.delete;
        for t in c_trans loop
            tablas.t_transferencias(t.NID) := t;
        end loop;
    end;
end;
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
        end if;
    end if;
end comprobarIDBanco;

/*----------------------------------------------------------------------------------------------------*/
/* Inserción de datos */
-- Clientes
insert into Clientes values('83741928N', 'Felicia', 'Ramírez', 20);
insert into Clientes values('77362542K', 'Rocío', 'Méndez', 23);
insert into Clientes values('89765342P', 'Jaime', 'Santos', 45);
insert into Clientes values('13248897F', 'Miguel', 'Delgado', 61);
insert into Clientes values('36274589Q', 'Sandra', 'Pérez', 34);

select * from Clientes;
exec tablas.rellenar_clientes;

-- Bancos
insert into Bancos values(38239567, 'SyuroBank', 1.51);
insert into Bancos values(88743654, 'Catcouch Financials', 0.32);

select * from Bancos;
exec tablas.rellenar_bancos;

-- Cuentas
--insert into Cuentas select ID, interes, cliente, banco from Clientes c, Bancos b;
insert into Cuentas 
select 'ES2438239567111122224444', 1.51, ref(c), ref(b) from Clientes c, Bancos b
where c.NIF = '83741928N' and b.ID = 38239567;

insert into Cuentas 
select 'ES2438239567777755551111', 1.51, ref(c), ref(b) from Clientes c, Bancos b
where c.NIF = '77362542K' and b.ID = 38239567;

insert into Cuentas 
select 'ES2438239567788790096776', 1.51, ref(c), ref(b) from Clientes c, Bancos b
where c.NIF = '89765342P' and b.ID = 38239567;

insert into Cuentas 
select 'ES2488743654273654273645', 0.32, ref(c), ref(b) from Clientes c, Bancos b
where c.NIF = '13248897F' and b.ID = 88743654;

insert into Cuentas 
select 'ES2488743654262635451726', 0.32, ref(c), ref(b) from Clientes c, Bancos b
where c.NIF = '36274589Q' and b.ID = 88743654;

select ID, interes, deref(c.cliente), deref(c.banco) from Cuentas c;
exec tablas.rellenar_cuentas;

-- Movimientos
-- insert into Movimientos select NID, cuenta, 'dd/MM/yyyy', importe, concepto --from Cuentas c;
insert into Movimientos 
select 1, ref(c), '23/02/2022', 234.25, 'Factura Luz' from Cuentas c
where c.ID = 'ES2438239567111122224444';

insert into Movimientos 
select 2, ref(c), '12/01/2022', 670.98, 'Piezas Ordenador' from Cuentas c
where c.ID = 'ES2438239567777755551111';

insert into Movimientos 
select 3, ref(c), '07/01/2022', 154.23, 'Regalos Reyes' from Cuentas c
where c.ID = 'ES2438239567788790096776';

insert into Movimientos 
select 4, ref(c), '01/03/2022', 178.01, 'Factura Gas' from Cuentas c
where c.ID = 'ES2488743654273654273645';

insert into Movimientos 
select 5, ref(c), '23/02/2022', 167.23, 'Compra del mes' from Cuentas c
where c.ID = 'ES2488743654262635451726';

select NID, m.cuenta.ID "ID Cuenta", m.cuenta.cliente.NIF "NIF Cli", fecha, importe, concepto from Movimientos m;
exec tablas.rellenar_movimientos;

-- Transferencias
-- insert into Transferencias select NID, origen, destino, fecha, importe, -- concepto from Cuentas c1, Cuentas c2;
insert into Transferencias 
select 1, ref(c1), ref(c2), '08/01/2022', 77.0, 'Mitad Regalos Reyes' from Cuentas c1, Cuentas c2
where c1.ID = 'ES2488743654262635451726' and c2.ID = 'ES2438239567111122224444';

insert into Transferencias 
select 2, ref(c1), ref(c2), '24/02/2022', 83.50, 'Mitad Compra Mes' from Cuentas c1, Cuentas c2
where c1.ID = 'ES2438239567111122224444' and c2.ID = 'ES2488743654262635451726';

select NID, t.origen.ID, t.destino.ID, fecha, importe, concepto from Transferencias t;
exec tablas.rellenar_transferencias;

/*----------------------------------------------------------------------------------------------------*/
/*
Creación de un paquete para la gestión de Clientes, sus cuentas y sus movimientos
*/

create or replace package gestor as
    -- Procedimientos de alta, baja, modificación y consulta
    procedure insertar_Cliente(PNIF varchar2, Pnombre varchar2, Papellidos varchar2, Pedad number);
    procedure insertar_Banco(PID number, Pnombre varchar2, Pint_base number);
    procedure insertar_Cuenta(PID varchar2, Pinteres number, PNifCliente Clientes.NIF%type, PNombreBanco Bancos.nombre%type);
    procedure insertar_Movimiento(PNID integer, Pcuenta ref OCuenta, Pfecha date, Pimporte number, Pconcepto varchar2);
    procedure insertar_Transferencia(PNID integer, Porigen ref OCuenta, Pdestino ref OCuenta, Pfecha date, Pimporte number, Pconcepto varchar2);

    procedure eliminar_Cliente(PNIF varchar2);
    procedure eliminar_Banco(PID number);
    procedure eliminar_Cuenta(PID varchar2);
    procedure eliminar_Movimiento(PNID integer);
    procedure eliminar_Transferencia(PNID integer);

    procedure modificar_Nombre_Cliente(PNIF varchar2, Pnombre varchar2);
    procedure modificar_Nombre_Banco(PID number, Pnombre varchar2);
    procedure modificar_Interes_Cuenta(PID varchar2, Pinteres number);
    procedure modificar_Concepto_Mov(PNID integer, Pconcepto varchar2);
    procedure modificar_Concepto_Trans(PNID integer, Pconcepto varchar2);

    procedure ver_Clientes;
    procedure ver_Bancos;
    procedure ver_Cuentas;
    procedure ver_Cuentas_de_Banco(PID number);
    procedure ver_Cuentas_de_Cli(PNIF varchar2);
    procedure ver_Movimientos;
    procedure ver_Movimientos_de_Cli(PNIF varchar2);
    procedure ver_Transferencias;
    procedure ver_Transferencias_de_Cli(PNIF varchar2);
end gestor;


create or replace package body gestor as
    -- Procedimientos de alta
    --- Insertar cliente
    procedure insertar_Cliente(PNIF varchar2, Pnombre varchar2, Papellidos varchar2, Pedad number)
    as
    begin
        insert into Clientes values (PNIF, Pnombre, Papellidos, Pedad);
        dbms_output.put_line('Nuevo empleado insertado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.insertar_Cliente.');
            rollback;
    end insertar_Cliente;
    --- Insertar Banco
    procedure insertar_Banco(PID number, Pnombre varchar2, Pint_base number)
    as
    begin
        insert into Bancos values (PID, Pnombre, Pint_base);
        dbms_output.put_line('Nuevo banco insertado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.insertar_Banco.');
            rollback;
    end insertar_Banco;
    --- Insertar cuenta
    procedure insertar_Cuenta(PID varchar2, Pinteres number, PNifCliente Clientes.NIF%type,
        PNombreBanco Bancos.nombre%type)
    as
        cli ref OCliente;
        ban ref OBanco;
    begin
    
        select ref(Cl) into cli from Clientes Cl where NIF = PNifCliente;
        select ref(Ba) into ban from Bancos Ba where nombre = PNombreBanco;
        insert into Cuentas values (PID, Pinteres, cli, ban);
        dbms_output.put_line('Nueva cuenta insertada.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.insertar_Cuenta.');
            rollback;
    end insertar_Cuenta;
    --- Insertar movimiento
    procedure insertar_Movimiento(PNID integer, Pcuenta ref OCuenta, Pfecha date, Pimporte number, Pconcepto varchar2)
    as
    begin
        insert into Movimientos values (PNID, Pcuenta, Pfecha, Pimporte, Pconcepto);
        dbms_output.put_line('Nuevo movimiento insertado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.insertar_Movimiento.');
            rollback;
    end;
    --- Insertar transferencia
    procedure insertar_Transferencia(PNID integer, Porigen ref OCuenta, Pdestino ref OCuenta, Pfecha date, Pimporte number, Pconcepto varchar2)
    as
    begin
        insert into Transferencias values (PNID, Porigen, Pdestino, Pfecha, Pimporte, Pconcepto);
        dbms_output.put_line('Nueva transferencia insertada.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.insertar_Transferencia.');
            rollback;
    end;

    -- Procedimientos de baja
    --- Eliminar un cliente
    procedure eliminar_Cliente(PNIF varchar2)
    as
        -- Cursor con los movimientos del cliente que vamos a eliminar
        cursor mov_cliente is
            select * from Movimientos m
            where m.cuenta.cliente.NIF = PNIF;

        -- Cursor con las transferencias del cliente que vamos a eliminar
        cursor trans_cliente is
            select * from Transferencias t
            where t.origen.cliente.NIF = PNIF
            or t.destino.cliente.NIF = PNIF;

        -- Cursor con las cuentas del cliente que vamos a eliminar
        cursor cuentas_cliente is
            select * from Cuentas c
            where c.cliente.NIF = PNIF;
    begin
        -- Al eliminar un cliente hay que eliminar todas las cuentas, movimientos y transferencias pertenecientes a este.
        -- Primero vamos a eliminar las transferencias y movimientos
        for t in trans_cliente loop
            execute immediate 'delete from Transferencias where NID = ' || t.NID;
        end loop;

        for m in mov_cliente loop
            execute immediate 'delete from Movimientos where NID = ' || m.NID;
        end loop;

        -- Luego podemos eliminar las cuentas del cliente
        for c in cuentas_cliente loop
            execute immediate 'delete from Cuentas where ID = ' || c.ID;
        end loop;

        -- Una vez ya no quedan mas referencias al cliente, podemos eliminarlo
        delete from Clientes where NIF = PNIF;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.eliminar_Cliente.');
            rollback;
    end;
    --- Eliminar un banco
    procedure eliminar_Banco(PID number)
    as
        -- Cursor con los movimientos del banco que vamos a eliminar
        cursor mov_banco is
            select * from Movimientos m
            where m.cuenta.banco.ID = PID;

        -- Cursor con las transferencias del banco que vamos a eliminar
        cursor trans_banco is
            select * from Transferencias t
            where t.origen.banco.ID = PID
            or t.destino.banco.ID = PID;

        -- Cursor con las cuentas del banco que vamos a eliminar
        cursor cuentas_banco is
            select * from Cuentas c
            where c.banco.ID = PID;
    begin
        -- Para eliminar un banco primero hay que eliminar todas las cuentas, movimientos y transferencias asociadas a este.
        -- Primero vamos a eliminar las transferencias y movimientos
        for t in trans_banco loop
            execute immediate 'delete from Transferencias where NID = ' || t.NID;
        end loop;

        for m in mov_banco loop
            execute immediate 'delete from Movimientos where NID = ' || m.NID;
        end loop;

        -- Luego podemos eliminar las cuentas del cliente
        for c in cuentas_banco loop
            execute immediate 'delete from Cuentas where ID = ' || c.ID;
        end loop;

        -- Al final podemos eliminar el banco
        delete from Bancos where ID = PID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.eliminar_Banco.');
            rollback;
    end;
    --- Eliminar una cuenta
    procedure eliminar_Cuenta(PID varchar2)
    as
        -- Cursor con los movimientos de la cuenta que vamos a eliminar
        cursor mov_cuenta is
            select * from Movimientos m
            where m.cuenta.ID = PID;

        -- Cursor con las transferencias de la cuenta que vamos a eliminar
        cursor trans_cuenta is
            select * from Transferencias t
            where t.origen.ID = PID
            or t.destino.ID = PID;
    begin
        -- Para eliminar una cuenta primero hay que eliminar todos los movimientos y transferencias relacionadas a ella.
        for t in trans_cuenta loop
            execute immediate 'delete from Transferencias where NID = ' || t.NID;
        end loop;

        for m in mov_cuenta loop
            execute immediate 'delete from Movimientos where NID = ' || m.NID;
        end loop;

        -- Luego podemos eliminar la cuenta
        delete from Cuentas where ID = PID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.eliminar_Cuenta.');
            rollback;
    end;
    --- Eliminar un movimiento
    procedure eliminar_Movimiento(PNID integer)
    as
    begin
        -- Los movimientos se pueden eliminar directamente
        delete from Movimientos where NID = PNID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.eliminar_Movimiento.');
            rollback;
    end;
    --- Eliminar una transferencia
    procedure eliminar_Transferencia(PNID integer)
    as
    begin
        -- Las transferencias se pueden eliminar directamente
        delete from Transferencias where NID = PNID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.eliminar_Transferencia.');
            rollback;
    end;

    -- Procedimientos para modificar datos
    --- Modificar el nombre de un cliente
    procedure modificar_Nombre_Cliente(PNIF varchar2, Pnombre varchar2)
    as
    begin
        -- Se puede modificar directamente
        update Clientes set nombre = pnombre where NIF = PNIF;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.modificar_Nombre_Cliente.');
            rollback;
    end;
    --- Modificar el nombre de un banco
    procedure modificar_Nombre_Banco(PID number, Pnombre varchar2)
    as
    begin
        -- Se puede modificar directamente
        update Bancos set nombre = pnombre where ID = PID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.modificar_Nombre_Banco.');
    end;
    --- Modificar el interés de una cuenta
    procedure modificar_Interes_Cuenta(PID varchar2, Pinteres number)
    as
    begin
        -- Se puede modificar directamente
        update Cuentas set interes = Pinteres where ID = PID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.modificar_Interes_Cuenta.');
    end;
    --- Modificar el concepto de un movimiento
    procedure modificar_Concepto_Mov(PNID integer, Pconcepto varchar2)
    as
    begin
        -- Se puede modificar directamente
        update Movimientos set concepto = Pconcepto where NID = PNID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.modificar_Concepto_Mov.');
    end;
    --- Modificar el concepto de una transferencia
    procedure modificar_Concepto_Trans(PNID integer, Pconcepto varchar2)
    as
    begin
        -- Se puede modificar directamente
        update Transferencias set concepto = Pconcepto where NID = PNID;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.modificar_Concepto_Trans.');
    end;

    -- Procedimientos de consulta
    --- Consulta de clientes
    procedure ver_Clientes
    as
        cursor c_clientes is
            select * from clientes;
    begin
        -- Muestra los datos de todos los clientes
        for c in c_clientes loop
            dbms_output.put_line('NIF:         ' || c.NIF);
            dbms_output.put_line('Nombre:      ' || c.nombre);
            dbms_output.put_line('Apellidos:   ' || c.apellidos);
            dbms_output.put_line('Edad:        ' || c.edad);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Clientes.');
    end;
    --- Consulta de bancos
    procedure ver_Bancos
    as
    begin
        -- Muestra los datos de todos los bancos
        for c in (select * from Bancos) loop
            dbms_output.put_line('ID:              ' || c.ID);
            dbms_output.put_line('Nombre:          ' || c.nombre);
            dbms_output.put_line('Interés base:    ' || c.int_base);            
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Bancos.');
    end;
    --- Consulta de cuentas
    procedure ver_Cuentas
    as
        cli Clientes.NIF%type;
        ban Bancos.nombre%type;
    begin
        -- Muestra los datos de todas las cuentas
        for c in (select * from Cuentas) loop
            select Cl.NIF into cli
            from Clientes Cl
            where ref(Cl) = c.cliente;
            select Ba.nombre into ban 
            from Clientes Cl, Bancos Ba
            where ref(Ba) = c.banco;

            dbms_output.put_line('ID:         ' || c.ID);   
            dbms_output.put_line('Interés:    ' || c.interes);   
            dbms_output.put_line('Cliente:    ' || cli);   
            dbms_output.put_line('Banco:      ' || ban);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Cuentas.');
    end;
    --- Consulta de cuentas de un banco
    procedure ver_Cuentas_de_Banco(PID number)
    as
        cli Clientes.NIF%type;
        banNom Bancos.nombre%type;
        banID Bancos.ID%type;
    begin
        -- Muestra los datos de las cuentas de un determinado banco
        select ID, nombre into banID, banNom from Bancos where ID = PID;
        dbms_output.put_line('-- Banco: ' || banID || ' * ' || banNom);

        for c in (select * from Cuentas c where c.banco.ID = PID) loop
            select Cl.NIF into cli
            from Clientes Cl
            where ref(Cl) = c.cliente;
            select Ba.nombre into banNom 
            from Clientes Cl, Bancos Ba
            where ref(Ba) = c.banco;
            dbms_output.put_line('ID:         ' || c.ID);   
            dbms_output.put_line('Interés:    ' || c.interes);   
            dbms_output.put_line('Cliente:    ' || cli);
            dbms_output.put_line('Banco:      ' || banNom);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Cuentas_de_Banco.');
    end;
    --- Consulta de cuentas de un cliente
    procedure ver_Cuentas_de_Cli(PNIF varchar2)
    as
        cliNif Clientes.NIF%type;
        cliNom Clientes.nombre%type;
        ban Bancos.nombre%type;
    begin
        -- Muestra las cuentas de un determinado cliente
        select NIF, nombre into cliNif, cliNom from Clientes where NIF = PNIF;
        dbms_output.put_line('-- Cliente: ' || cliNif || ' * ' || cliNom);

        for c in (select * from Cuentas c where c.cliente.NIF = PNIF) loop
            select Cl.NIF into cliNif
            from Clientes Cl
            where ref(Cl) = c.cliente;
            select Ba.nombre into ban 
            from Clientes Cl, Bancos Ba
            where ref(Ba) = c.banco;
            dbms_output.put_line('ID:         ' || c.ID);   
            dbms_output.put_line('Interés:    ' || c.interes);   
            dbms_output.put_line('Cliente:    ' || cliNif);   
            dbms_output.put_line('Banco:      ' || ban);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Cuentas_de_Clis.');
    end;
    --- Consulta de movimientos
    procedure ver_Movimientos
    as
        aux Cuentas.ID%type;
    begin
        -- Muestra todos los movimientos
        for c in (select * from Movimientos) loop
            select Cu.ID into aux
            from Cuentas Cu
            where ref(Cu) = c.cuenta;
            dbms_output.put_line('NID:        ' || c.NID);
            dbms_output.put_line('Cuenta:     ' || aux);
            dbms_output.put_line('Fecha:      ' || c.fecha);
            dbms_output.put_line('Importe:    ' || c.importe);
            dbms_output.put_line('Concepto:   ' || c.concepto);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Movimientos.');
    end;
    --- Consulta de movimientos de un cliente
    procedure ver_Movimientos_de_Cli(PNIF varchar2)
    as
        cliNif Clientes.NIF%type;
        cliNom Clientes.nombre%type;
        aux Cuentas.ID%type;
    begin
        -- Muestra los movimientos de un determinado cliente
        select NIF, nombre into cliNif, cliNom from Clientes where NIF = PNIF;
        dbms_output.put_line('-- Cliente: ' || cliNif || ' * ' || cliNom);

        for c in (select * from Movimientos m where m.cuenta.cliente.NIF = PNIF) loop
            select Cu.ID into aux
            from Cuentas Cu
            where ref(Cu) = c.cuenta;
            dbms_output.put_line('NID:        ' || c.NID);
            dbms_output.put_line('Cuenta:     ' || aux);
            dbms_output.put_line('Fecha:      ' || c.fecha);
            dbms_output.put_line('Importe:    ' || c.importe);
            dbms_output.put_line('Concepto:   ' || c.concepto);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Movimientos_de_Cli.');
    end;
    --- Consulta de transferencias
    procedure ver_Transferencias
    as
        ori Cuentas.ID%type;
        des Cuentas.ID%type;
    begin
        -- Muestra los datos de todas las transferencias
        for c in (select * from Transferencias) loop
            select Cu.ID into ori
            from Cuentas Cu
            where ref(Cu) = c.origen;
            select Cu.ID into des
            from Cuentas Cu
            where ref(Cu) = c.destino;
            dbms_output.put_line('NID:        ' || c.NID);
            dbms_output.put_line('Origen:     ' || ori);
            dbms_output.put_line('Destino:    ' || des);
            dbms_output.put_line('Fecha:      ' || c.fecha);
            dbms_output.put_line('Importe:    ' || c.importe);
            dbms_output.put_line('Concepto:   ' || c.concepto);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Transferencias.');
    end;
    --- Consulta de transferencias de clientes
    procedure ver_Transferencias_de_Cli(PNIF varchar2)
    as
        ori Cuentas.ID%type;
        des Cuentas.ID%type;
        cliNif Clientes.NIF%type;
        cliNom Clientes.nombre%type;
    begin
        -- Muestra los datos de las transferencias de un determinado cliente
        select NIF, nombre into cliNif, cliNom from Clientes where NIF = PNIF;
        dbms_output.put_line('-- Cliente: ' || cliNif || ' * ' || cliNom);

        for c in (select * from Transferencias) loop
            select Cu.ID into ori
            from Cuentas Cu
            where ref(Cu) = c.origen;
            select Cu.ID into des
            from Cuentas Cu
            where ref(Cu) = c.destino;
            dbms_output.put_line('NID:        ' || c.NID);
            dbms_output.put_line('Origen:     ' || ori);
            dbms_output.put_line('Destino:    ' || des);
            dbms_output.put_line('Fecha:      ' || c.fecha);
            dbms_output.put_line('Importe:    ' || c.importe);
            dbms_output.put_line('Concepto:   ' || c.concepto);
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error no identificado en gestor.ver_Transferencias_de_Cli.');
    end;
    
end gestor;
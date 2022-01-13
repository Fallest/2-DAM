set serveroutput on size 999999;

/* Alta de datos */
create or replace procedure añadirCliente(
    cod number,
	nom varchar2,
	pw varchar2,
    foto varchar2)
as
    cod_existe exception;
    cursor clientes is
        select * from cliente;
begin
    -- Comprobamos que el código no existe
    for cli in clientes loop
        if cli.codigo = cod then
            raise cod_existe;
        end if;
    end loop;

    -- Si el código no existe, lo creamos
    insert into cliente values(cod, nom, pw, foto);
    commit;

    exception
        when cod_existe then
            dbms_output.put_line('ERROR: El cliente ya existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;

end;

create or replace procedure añadirCuenta(
    pnum number,
    pfecha date,
    psaldo number,
    psaldoMinimo number,
    pinteres number,
    pcliCodigo number
)
as
    cod_no_existe exception;
    num_existe exception;

    existe_cod integer default 0;

    cursor clientes is
        select * from cliente;
    cursor cuentas is
        select * from cuenta;
begin
    -- Comprobamos que el código de cliente existe
    for cli in clientes loop
        if cli.codigo = pcliCodigo then
            existe_cod := 1;
        end if;
    end loop;

    if existe_cod <> 1 then
        raise cod_no_existe;
    end if;

    -- Comprobamos que no existe ningúna cuenta con ese número
    for c in cuentas loop
        if c.numero = pnum then
            raise num_existe;
        end if;
    end loop;

    -- Insertamos el valor
    insert into cuenta values(pnum, pfecha, psaldo, psaldoMinimo, pinteres, pcliCodigo);
    commit;

    exception
        when cod_no_existe then
            dbms_output.put_line('ERROR: El cliente no existe.');
            rollback;
        when num_existe then
            dbms_output.put_line('ERROR: La cuenta ya existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;

create or replace procedure añadirMovimiento(
    pcueNumero number,
    pfecha date,
    pimporte number,
    pconcepto varchar2
)
as
    existe_cuenta integer default 0;
    cuenta_no_existe exception;
    pk_exception exception; -- Necesita un numero de cuenta y un numero de movimiento
    sin_saldo exception;

    numero_mov integer;

    cursor cuentas is
        select * from cuenta;

    cursor movimientos is
        select * from movimiento;

begin
    
    -- Tenemos que comprobar que el numero de cuenta no es null
    if pcueNumero is null then
        raise pk_exception;
    end if;

    -- Comprobamos que la cuenta a la que se va a añadir el movimiento existe
    for c in cuentas loop 
        if c.numero = pcueNumero then
            existe_cuenta := 1;
        end if;
    end loop;

    if existe_cuenta <> 1 then
        raise cuenta_no_existe;
    end if;

    -- Comprobamos que la cuenta tiene saldo para realizar el movimiento
    for c in cuentas loop 
        if c.numero = pcueNumero and c.saldo + pimporte < 0 then
            raise sin_saldo;
        end if;
    end loop;

    -- En cualquier otro caso creamos un nuevo movimiento
    --  Primero obtenemos el número de movimiento que se le va a asignar
    select max(numero) into numero_mov 
    from cuenta
    where numero = pcueNumero;
    --  Luego creamos el movimiento
    insert into movimiento values(numero_mov+1, pcueNumero, pfecha, pimporte, pconcepto);
    commit;

    exception
        when pk_exception then
            dbms_output.put_line('ERROR: Los campos "Número de cuenta" y "Número de movimiento" son obligatorios.');
            rollback;
        when cuenta_no_existe then
            dbms_output.put_line('ERROR: El número de cuenta no existe.');
            rollback;
        when sin_saldo then
            dbms_output.put_line('ERROR: La cuenta no tiene suficiente saldo.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;


/*--------------------------------------------------------------------------*/
/* Baja de datos */
create or replace procedure eliminarCliente(pcod number)
as
    cod_no_existe exception;
    existe_cod integer;
    cursor clientes is
        select * from cliente;
begin
    -- Comprobamos que el código existe
    for cli in clientes loop
        if cli.codigo = pcod then
            existe_cod := 1;
        end if;
    end loop;
     
    if existe_cod <> 1 then
        raise cod_no_existe;
    end if;

    -- Si el código existe, lo eliminamos
    delete from cliente where codigo = pcod;
    commit;

    exception
        when cod_no_existe then
            dbms_output.put_line('ERROR: El cliente no existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;

create or replace procedure eliminarCuenta(pnum number)
as
    num_no_existe exception;
    num_existe integer default 0;

    cursor cuentas is
        select * from cuenta;
begin
    -- Comprobamos que existe alguna cuenta con ese número
    for c in cuentas loop
        if c.numero = pnum then
            num_existe := 1;
        end if;
    end loop;

    if num_existe <> 1 then
        raise num_no_existe;
    end if;

    -- Eliminamos el valor
    delete from cuenta where numero = pnum;
    commit;

    exception
        when num_no_existe then
            dbms_output.put_line('ERROR: La cuenta no existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;

create or replace procedure eliminarMovimiento(pnumMov integer, pnumCue integer)
as
    existe_cuenta integer default 0;
    existe_mov integer default 0;
    cuenta_no_existe exception;
    mov_no_existe exception;

    cursor cuentas is
        select * from cuenta;

    cursor movimientos is
        select * from movimiento;

begin
    -- Comprobamos que la cuenta existe
    for c in cuentas loop 
        if c.numero = pnumCue then
            existe_cuenta := 1;
        end if;
    end loop;

    if existe_cuenta <> 1 then
        raise cuenta_no_existe;
    end if;
    
    -- Comprobamos que el movimiento existe
    for m in movimientos loop 
        if m.numero = pnumMov then
            existe_mov := 1;
        end if;
    end loop;

    if existe_mov <> 1 then
        raise mov_no_existe;
    end if;

    --  Luego eliminamos el movimiento
    delete from movimiento where numero = pnumMov and cueNumero = pnumCue;
    commit;

    exception
        when cuenta_no_existe then
            dbms_output.put_line('ERROR: El número de cuenta no existe.');
            rollback;
        when mov_no_existe then
            dbms_output.put_line('ERROR: El número de movimiento no existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;


/*--------------------------------------------------------------------------*/
/* Modificación de datos */
create or replace procedure modificarCliente(
    pCod integer,
    pNuevoNom cliente.nombre%type,
    pNuevaPw cliente.contrasena%type,
    pNuevaFoto cliente.foto%type
    )
as
    -- Cursor para recorrer los clientes
    cursor clientes is
        select * from cliente;

    -- Variables para las excepciones
    existe_cod integer default 0;
    cod_no_existe exception;
begin
    -- Primero comprobamos que existe el cliente y si el nuevo código ya existe
    for c in clientes loop
        if c.codigo = pCod then
            existe_cod := 1;
        end if;

    end loop;

    if existe_cod <> 1 then
        raise cod_no_existe;
    end if;
    

    -- Actualizamos los datos del cliente
    update cliente 
    set nombre = pNuevoNom, 
        contrasena = pNuevaPw,
        foto = pNuevaFoto
    where codigo = pcod;

    commit;

    exception
        when cod_no_existe then
            dbms_output.put_line('ERROR: El cliente no existe.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;

end;

create or replace procedure modificarCuenta(
    pNum integer,
    pNFecha date,
    pNsaldo cuenta.saldo%type,
    pNsaldoMinimo cuenta.saldoMinimo%type,
    pNInteres cuenta.interes%type,
    pNCliCod cuenta.cliCodigo%type
)
as
    -- Variables para las excepciones
    existe_cue integer default 0;
    no_existe_cuenta exception; 

    existe_cli integer default 0;
    no_existe_cliente exception;

    -- Cursor para recorrer los clientes y las cuentas
    cursor clientes is
        select * from cliente;

    cursor cuentas is
        select * from cuenta;
begin
    -- Primero comprobamos que existe la cuenta
    for cue in cuentas loop
        if cue.numero = pNum then
            existe_cue := 1;
        end if;
    end loop;

    if existe_cue <> 1 then
        raise no_existe_cuenta;
    end if;

    -- Luego comprobamos que existe el cliente que queremos asignar
    for cli in clientes loop
        if cli.codigo = pNCliCod then
            existe_cli := 1;
        end if;
    end loop;

    if existe_cli <> 1 then
        raise no_existe_cliente;
    end if;

    -- Hacemos la modificación
    update cuenta 
    set saldo = pNsaldo,
        saldoMinimo = pNsaldoMinimo,
        interes = pNInteres,
        cliCodigo = pNCliCod
    where numero = pNum;    

    commit;  

    -- Control de excepciones
    exception
        when no_existe_cuenta then
            dbms_output.put_line('ERROR: No existe la cuenta indicada.');
            rollback;
        when no_existe_cliente then
            dbms_output.put_line('ERROR: No existe el cliente indicado.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;

create or replace procedure modificarMovimiento(
    pNum integer,
    pCueNum integer,
    pNFecha date,
    pNimporte movimiento.importe%type,
    pNconcepto movimiento.concepto%type
)
as
    -- Variables para las excepciones
    existe_cue integer default 0;
    no_existe_cuenta exception; 

    existe_mov integer default 0;
    no_existe_mov exception;

    -- Cursor para recorrer los movimiento y las cuentas
    cursor movimientos is
        select * from movimiento;

    cursor cuentas is
        select * from cuenta;
begin
    -- Primero comprobamos que existe la cuenta
    for cue in cuentas loop
        if cue.numero = pCueNum then
            existe_cue := 1;
        end if;
    end loop;

    if existe_cue <> 1 then
        raise no_existe_cuenta;
    end if;

    -- Luego comprobamos que existe el movimiento que queremos modificar
    for mov in movimientos loop
        if mov.numero = pNum then
            existe_mov := 1;
        end if;
    end loop;

    if existe_mov <> 1 then
        raise no_existe_mov;
    end if;

    -- Hacemos la modificación
    update movimiento 
    set fecha = pNFecha,
        importe = pNimporte,
        concepto = pNconcepto
    where numero = pNum and cueNumero = pCueNum;

    commit;

    -- Control de excepciones
    exception
        when no_existe_cuenta then
            dbms_output.put_line('ERROR: No existe la cuenta indicada.');
            rollback;
        when no_existe_mov then
            dbms_output.put_line('ERROR: No existe el movimiento indicado.');
            rollback;
        when others then
            dbms_output.put_line('ERROR: Error en la transacción.');
            rollback;
end;
/*--------------------------------------------------------------------------*/
/* Consulta de los datos */
create or replace procedure consultarCliente(pCod integer)
as
    cursor clientes(cod integer) is    
    select * from cliente where codigo = cod;
begin
    /* Muestra los datos de un cliente concreto */
    for cli in clientes(pCod) loop
        dbms_output.put_line('CLIENTE: ' || cli.codigo);
        dbms_output.put_line('  -Nombre: ' || cli.nombre);
        dbms_output.put_line('  -Contraseña: ' || cli.contrasena);
        dbms_output.put_line('  -Foto: ' || cli.foto);
    end loop;

    -- Control de excepciones
    exception
        when no_data_found then
            dbms_output.put_line('ERROR: No se ha encontrado al cliente indicado.');
end;

create or replace procedure consultarCuenta(pCodCli integer)
as
    -- Cursor para recorrer los datos
    cursor cuentas is
        select * from cuenta
        where cliCodigo = pCodCli;
begin
    /* Muestra los datos de las cuentas de un cliente */
    
    dbms_output.put_line('CLIENTE: ' || pCodCli);
    for c in cuentas loop
        verMovimientosDeCuenta(c.numero);
    end loop;

    -- Control de excepciones
    exception
        when no_data_found then
            dbms_output.put_line('ERROR: No se han encontrado cuentas para el cliente indicado.');
end;

create or replace procedure consultarMovimiento(numCue integer, numMov integer)
as
    -- Cursor para recorrer los movimientos
    vnumMov integer;
    vnumCue integer;
    cursor movimientos is
        select * from movimiento
        where numero = vnumMov and cueNumero = vnumCue; -- Variables de acoplamiento
begin
    /* Muestra los datos de un movimiento indicado */
    vnumMov := numMov;
    vnumCue := numCue;

    dbms_output.put_line('**** Movimiento: ' || vnumMov);
    for m in movimientos loop
        dbms_output.put_line('      Fecha:.........' || to_char(m.fecha));
        dbms_output.put_line('      Importe:.......' || m.importe);
        dbms_output.put_line('      Concepto: ' || m.concepto);
    end loop;

    -- Control de excepciones
    exception
        when no_data_found then
            -- Si no existen datos
            dbms_output.put_line('ERROR: No existe el movimiento indicado.');

end;

create or replace procedure verMovimientosDeCuenta(numCue integer)
as
    -- Cursor para recorrer los movimientos
    vnumCue integer;
    cursor movimientos is
        select * from movimiento
        where cueNumero = vnumCue; -- Variable de acoplamiento
begin
    /* Muestra los movimientos de una cuenta indicada */
    vnumCue := numCue;

    dbms_output.put_line('** CUENTA: ' || vnumCue);
    for m in movimientos loop
        consultarMovimiento(vnumCue, m.numero);
    end loop;

    -- Control de excepciones
    exception
        when no_data_found then
            -- Si no existe la cuenta
            dbms_output.put_line('ERROR: No existe la cuenta indicada.');

end;
/*--------------------------------------------------------------------------*/
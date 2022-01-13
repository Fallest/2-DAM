set serveroutput on size 1000000;

/* Ejercicio 1 */

create or replace procedure crearCuenta(
	num number,
	saldo number,
	dniCli number,
	nomSucur varchar2
)
as
	-- Excepciones de usuario
	noExisteCliente exception;
	noExisteSucursal exception;
	cuentaRepetida exception;
	
	-- Flags para comprobar los posibles errores
	compExisteCli number(1) default 0;
	compExisteSucur number(1) default 0;
	
	-- Cursor para recorrer los DNIs de los clientes
	cursor clientes is
		select dni 
		from cliente;
		
	-- Cursor para recorrer los nombres de las sucursales
	cursor sucursales is 
		select nombre 
		from sucursal;
		
	-- Cursor para recorrer los números de las cuentas
	cursor cuentas is
		select numero
		from cuenta;
begin
/*
	Hay que comprobar que existe:
	-El dni del cliente
	-El nombre de sucursal
	-Si el numero de cuenta ya existe
	-Si el saldo es NULL
*/

	-- Primero vamos a comprobar que la cuenta no está repetida
	for cuenta in cuentas loop
		if num = cuenta.numero then
			raise cuentaRepetida;
		end if;
	end loop;
	
	-- Comprobamos que existe el cliente
	for cliente in clientes loop
		if dniCli = cliente.dni then
			compExisteCli := 1;
		end if;
	end loop;
	if compExisteCli = 0 then
		raise noExisteCliente;
	end if;
	
	-- Comprobamos que existe la sucursal
	for sucursal in sucursales loop
		if nomSucur = sucursal.nombre then
			compExisteSucur := 1;
		end if;
	end loop;
	if compExisteSucur = 0 then
		raise noExisteSucursal;
	end if;
	
	-- Comprobamos si el saldo es NULL
	if saldo is NULL then
		raise_application_error(-20010, 'ERROR: El saldo no puede ser nulo.');
	end if;
	
	-- Si no se activa ninguno de los errores, se completa la transacción
	insert into cuenta values(num, saldo, dniCli, nomSucur);
	dbms_output.put_line('Se ha creado una nueva cuenta.');
	
	-- Control de excepciones
	exception
		when cuentaRepetida then
			dbms_output.put_line('ERROR: La cuenta ya existe.');
		when noExisteCliente then
			dbms_output.put_line('ERROR: El cliente indicado no existe.');
		when noExisteSucursal then
			dbms_output.put_line('ERROR: La sucursal indicada no existe.');	
end;

/* Comprobaciones 
exec crearCuenta(16, 10, 1000000, 'SE1'); Crea una nueva cuenta
exec crearCuenta(16, 10, 1000000, 'SE123') No existe la sucursal
exec crearCuenta(1, 10, 10000000, 'SE1')  No existe el cliente
exec crearCuenta(1, NULL, 1000000, 'SE1') Saldo nulo
*/

/*----------------------------------------------------------------*/
/* Ejercicio 2 */

create or replace procedure actualizarCuentas
as
	-- Cursor para recorrer los movimientos
	cursor mov is
		select importe, cuenumero
		from movimiento
		order by cuenumero;
	
	-- Variable para saber cuándo cambiamos de cuenta
	cuentaAnt movimiento.cuenumero%type;
	-- Variable para almacenar el resultado de las sumas
	saldoSuma movimiento.importe%type default 0;
begin
/*
	Para actualizar todas las cuentas tenemos que acceder a la 
	tabla Movimientos, y para cada número de cuenta, sumar todos
	los importes que aparezcan en una variable.
	Una vez sumados todos los importes, se hará un update
	para actualizar el campo saldo de la cuenta en concreto.
*/
	for m in mov loop
		-- Si estamos en la primera iteración asignamos la cuentaAnt
		if mov%rowcount = 1 then
			cuentaAnt := m.cuenumero;
		end if;
		
		-- Si nos hemos topado con una nueva cuenta:
		if cuentaAnt <> m.cuenumero then
			-- Actualizamos la cuenta
			update cuenta set saldo = saldoSuma
			where numero = cuentaAnt;
			
			-- Actualizamos variables
			cuentaAnt := m.cuenumero;
			saldoSuma := 0;
		end if;
		
		-- Mientras sigamos en la misma cuenta
		saldoSuma := saldoSuma + m.importe;
	end loop;
	-- Por la naturaleza del for loop, no se computará la actualización
	-- de la última fila a no ser que la hagamos tras el for.
	update cuenta set saldo = saldoSuma
	where numero = cuentaAnt;
	
	commit;
	
	dbms_output.put_line('Saldos actualizados.');
	
	-- Control de errores
	exception
		when others then
			dbms_output.put_line('ERROR: Haciendo rollback...');
			rollback;
end;

/* Comprobaciones
select numero, saldo from cuenta;
exec actualizarCuentas;
select numero, saldo from cuenta;
*/

/*----------------------------------------------------------------*/
/* Ejercicio 3 */

create or replace function mostrarSaldo(
	nomCli varchar2,
	apeCli varchar2
)
return number
as
	-- Cursor para iterar por las cuentas
	cursor cuentas is
		select saldo, clidni
		from cuenta
		order by clidni;
	
	-- Variable para la suma de saldos
	saldoTotal cuenta.saldo%type default 0;
	-- Variable para saber qué cliente es
	vDniCli cliente.dni%type;
begin 
/*
	Esta función va a localizar el DNI del cliente dado mediante
	su nombre y apellidos.
	Una vez se tenga el DNI, se van a localizar todas las
	cuentas pertenecientes a ese cliente y se va a mostrar
	la suma de todos los saldos.
*/
	-- Obtenemos el dni del cliente
	select dni into vDniCli
	from cliente
	where nombre = nomCli
	and apellido = apeCli;
	
	-- Obtenemos el total de sus cuentas
	for cuenta in cuentas loop
		-- Si encontramos una cuenta del cliente:
		if cuenta.clidni = vDniCli then
			saldoTotal := saldoTotal + cuenta.saldo;
		end if;
	end loop;
	
	-- Devolvemos el valor
	dbms_output.put_line(saldoTotal);
	return saldoTotal;
	
	exception 
		when others then
			dbms_output.put_line('Ha ocurrido un error');
	
end;

/* Comprobaciones
select mostrarSaldo('Inma', 'Gonzalez') from dual;
select mostrarSaldo('Lucía', 'López') from dual;
select mostrarSaldo('Rafael', 'Honrad') from dual;
*/

/*----------------------------------------------------------------*/
/* Ejercicio 4 */









create or replace procedure informeSucursales
as
	-- Cursor con los datos necesarios
	cursor datos is
		select S.nombre nomSucur, 
				Cli.dni dniCli, Cli.nombre nomCli, Cli.apellido apeCli,
				Cu.numero numCu, Cu.saldo saldo
		from sucursal S, cliente Cli, cuenta Cu
		where S.nombre = Cu.sucnombre
		and Cli.dni = Cu.clidni (+) --"...de todas las cuentas..."
		order by S.nombre, Cli.dni, Cu.numero;
		
	-- Variables para los totales
	totalCli cuenta.saldo%type default 0;
	totalSucur number(10, 2) default 0;
	contSucur number(2) default 0;
	
	-- Variables para saber cuándo hacer una ruptura
	cliAnt cliente.dni%type;
	sucurAnt sucursal.nombre%type;
	
	-- Variable para el nombre del director
	dirNom empleado.nombre%type;
	
	-- Variable para el último cliente
	ultCliNom varchar2(41);
begin 
/*
	Tenemos que hacer un informe de cuentas con rupturas por cliente
	y sucursal.
	Necesitaremos variables para saber cuándo cambiamos de cliente, 
	cuándo cambiamos de sucursal, y cuántas sucursales llevamos.
	Para ello tendremos un cursor con toda la información necesaria,
	ordenada por sucursal, cliente, y cuenta.
*/
	for dato in datos loop
		-- Si estamos en la primera línea hacemos las primeras asignaciones
		if datos%rowcount = 1 then
			cliAnt := dato.dniCli;
			sucurAnt := dato.nomSucur;
		end if;
		
		-- Ruptura por cliente
		if cliAnt <> dato.dniCli then
			-- Tenemos que mostrar los datos del cliente
			dbms_output.put_line('----DNI: ' || cliAnt
			|| ' | Nombre: ' || ultCliNom
			|| ' | Saldo total: ' || totalCli);
			dbms_output.put_line('-');
			-- Reiniciamos el total de cliente
			totalCli := 0;
			-- Pasamos al siguiente cliente
			cliAnt := dato.dniCli;
		end if;
		
		-- Ruptura por sucursal
		if sucurAnt <> dato.nomSucur then
			--Tenemos que obtener el nombre del director
			select empleado.nombre into dirNom
			from empleado, sucursal
			where numemp = empdirector
			and sucursal.nombre = sucurAnt;
			
			-- Luego mostramos los datos de la sucursal
			dbms_output.put_line('********Sucursal: ' || sucurAnt
			|| ' | Director: ' || dirNom 
			|| ' | Saldo Global: ' || totalSucur || '€');
			dbms_output.put_line('*');
			dbms_output.put_line('*');
			-- Reiniciamos el total de sucursal
			totalSucur := 0;
			-- Incrementamos el contador
			contSucur := contSucur + 1;
			-- Pasamos a la siguiente sucursal
			sucurAnt := dato.nomSucur;
		end if;
		
		-- Mostrar los datos de la cuenta actual
		dbms_output.put_line('Cuenta: ' 
							|| dato.numCu
							|| ' ......... '
							|| dato.saldo
							|| '€');
		
		-- Actualizamos los totales
		totalCli := totalCli + dato.saldo;
		totalSucur := totalSucur + dato.saldo;
		ultCliNom := dato.nomCli || ' ' || dato.apeCli;
	end loop;
	-- Por la propia naturaleza del bucle for, debemos mostrar las
	-- últimas rupturas tras el for.
	
	---------------------------------------------------
	dbms_output.put_line('----DNI: ' || cliAnt
	|| ' | Nombre: ' || ultCliNom
	|| ' | Saldo total: ' || totalCli);
	dbms_output.put_line('-');
	
	--------
	--Tenemos que obtener el nombre del director
	select empleado.nombre into dirNom
	from empleado, sucursal
	where numemp = empdirector
	and sucursal.nombre = sucurAnt;
	
	-- Luego mostramos los datos de la sucursal
	dbms_output.put_line('********Sucursal: ' || sucurAnt
	|| ' | Director: ' || dirNom 
	|| ' | Saldo Global: ' || totalSucur);
	dbms_output.put_line('*');
	dbms_output.put_line('*');
	---------------------------------------------------
	-- Mostramos el total de sucursales procesadas
	dbms_output.put_line('----Sucursales procesadas: ' || contSucur);
	
	exception
		when others then
			dbms_output.put_line('ERROR: Algo ha salido mal.');
			rollback;
end;

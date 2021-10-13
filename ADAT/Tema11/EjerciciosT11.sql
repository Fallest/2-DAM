/* Casos prácticos */
/* 1 */

declare
	empleado_no number(4,0);
	cantEmple number(2);
	aumento number(7) default 0;
	ofic varchar2(10);
begin 
	empleado_no := &vt_empno;
	
	select oficio into ofic from emple
	where emp_no = empleado_no;

	if ofic = 'PRESIDENTE' then
		aumento := 30;
	end if;

	select count(*) into cantEmple from emple
	where dir = empleado_no;
	
	if cantEmple = 0 then
		aumento := aumento + 50;
	elsif cantEmple = 1 then
		aumento := aumento + 80;
	elsif cantEmple = 2 then
		aumento := aumento + 100;
	else 
		aumento := aumento + 110;
	end if;

	update emple set salario = salario + aumento
	where emp_no = empleado_no;

	dbms_output.put_line(aumento);
end;

/*------------------------------------------------------*/
/* 2 */

/*2.1*/
declare
	vApellidos varchar2(25);
	v1Apel varchar2(25);
	vCaracter char;
	vPosicion integer := 1;

begin
	vApellidos := '&vsApellidos';
	vCaracter := substr(vApellidos, vPosicion, 1);
	while vCaracter between 'A' and 'Z' loop
		v1Apel := v1Apel || vCaracter;
		vPosicion := vPosicion +1;
		vCaracter := substr(vApellidos, vPosicion, 1);
	end loop;
	dbms_output.put_line('1er apellido: ' || v1Apel);
end;

/*2.2*/
declare
	vApellidos varchar2(25);
	v1Apel varchar2(25);
	vCaracter char;
	vPosicion integer := 1;

begin
	vApellidos := '&vsApellidos';
	loop
		vCaracter := substr(vApellidos, vPosicion, 1);
		exit when vCaracter not between 'A' and 'Z';
		v1Apel := v1Apel || vCaracter;
		vPosicion := vPosicion + 1;
	end loop;
	dbms_output.put_line('1er apellido: ' || v1Apel);
end;

/*------------------------------------------------------*/
/* 3 */

/*3.1*/
declare 
	rCadena varchar2(10);
begin
  	for i in reverse 1...length('HOLA') loop
		rCadena := rCadena || substr('HOLA', i, 1);
  	end loop;
	dbms_output.put_line(rCadena);
end;
/*3.2*/
declare 
	rCadena varchar2(10);
	i binary_integer;
begin
  	i := length('HOLA');
    while i >= 1 loop
		rCadena := rCadena ||substr('HOLA', i, 1);
		i := i - 1;
	end loop
	dbms_output.put_line(rCadena);
end;

/*------------------------------------------------------*/
/* 4 */

create or replace procedure cambiar_oficio (num_empleado number, nuevo_oficio varchar2)
as
	v_anterior_oficio emple.oficio%TYPE;
begin
  select oficio into v_anterior_oficio from emple
  where emp_no = num_empleado;

  update emple set oficio = nuevo_oficio
  where emp_no = num_empleado;

  dbms_output.put_line(num_empleado || '*Oficio anterior: ' || v_anterior_oficio || '*Oficio nuevo: ' || nuevo_oficio);
end cambiar_oficio;

/*------------------------------------------------------*/
/* 5 */

create or replace procedure cambiar_divisas(
	cantidadEuros in number,
	cambioActual in number, 
	cantidadComision in out number,
	cantidadDivisas out number)
as
	pctComision constant number(3,2) := 0.2;
	minimoComision constant number(6) default 3;

begin
  if cantidadComision is null then
	cantidadComision := greatest(cantidadEuros / 100 * pctComision, minimoComision);
  end if;
  cantidadDivisas := (cantidadEuros - cantidadComision) * cambioActual;
end;

create or replace procedure mostrarCambioDivisas(
	eur number,
	cambio number)
as
	vComision number(9);
	vDivisas number(9);
begin
	cambiar_divisas(eur, cambio, vComision, vDivisas);
	dbms_output.put_line('Euros             : ' || to_char(eur, '999,999,999.999'));
	dbms_output.put_line('Divisas x 1 euro  : ' || to_char(cambio, '999,999,999.999'));
	dbms_output.put_line('Euros comisión    : ' || to_char(vComision, '999,999,999.999'));
	dbms_output.put_line('Cantidad divisas  : ' || to_char(vDivisas, '999,999,999.999'));
end;

/*------------------------------------------------------*/
/* Actividades propuestas */
/* 1 */

declare
	num1 number(8,2) := 0 << Falta ";"
	num2 number(8,2) not null := 0;
	num3 number(8,2) not null; << no puede declararse como NOT NULL y no tener ningún dato
	cantidad integer(3); << no se le puede indicar el tamaño a integer.
	precio, descuento number(6); << no se pueden declarar dos variables en la misma orden.
	num4 num1%ROWTYPE; <<incompatible, tiene que set %TYPE. num1 no es una tabla ni una vista.
	dto constant integer; << las constantes tienen que tener un valor.
begin
	...
end;

/*------------------------------------------------------*/
/* 2 */

declare 
	rCadena varchar2(10);
	i binary_integer;
begin
  	i := length('HOLA');
    loop
		exit when i < 1;
		rCadena := rCadena ||substr('HOLA', i, 1);
		i := i - 1;
	end loop
	dbms_output.put_line(rCadena);
end;

/*------------------------------------------------------*/
/* 3 */

create or replace procedure cambiar_depart (num_empleado number, nuevo_depart number)
as
	v_anterior_depart depart.dept_no%TYPE;
begin
  select depart.dept_no into v_anterior_depart from emple, depart
  where emp_no = num_empleado
  and emple.dept_no = depart.emp_no;

  update emple set dept_no = nuevo_depart
  where emp_no = num_empleado;

  dbms_output.put_line(num_empleado || '*Departamento anterior: ' || v_anterior_depart || '*Departamento nuevo: ' || nuevo_depart);
end cambiar_depart;

/*------------------------------------------------------*/
/* 4 */

create or replace procedure crear_depart(
	vNumDept depart.dept_no%TYPE,
	vDNombre depart.dnombre%TYPE default 'PROVISIONAL',
	vLoc depart.loc%TYPE default 'PROVISIONAL')
is
begin
  insert into depart
  values (vNumDept, vDNombre, vLoc);
end;

crear_depart;
-- Incorrecta: tiene que tener al menos el parámetro del número del departamento
crear_depart(50);
--Incorrecta: debe de darse valores nulos a los otros dos parámetros: crear_depart(50, '', '');
crear_depart('COMPRAS');
--Incorrecta: falta el parámetro para el número del departamento y la localización: crear_depart(num, 'COMPRAS', '')
crear_depart(50, 'COMPRAS');
--Incorrecta, falta el parámetro para la localización: crear_depart(50, 'COMPRAS', '')
crear_depart('COMPRAS', 50);
--Incorrecta: el orden de los parámetros es el incorrecto y falta la localización: crear_depart(50, 'COMPRAS', '')
crear_depart('COMPRAS', 'VALENCIA');
--Incorrecta: es necesario el parámetro para el número del departamento.
crear_depart(50, 'COMPARS', 'VALENCIA');
--Correcta
crear_depart('COMPRAS', 50, 'VALENCIA');
--Incorrecta: orden incorrecto de parámetros: crear_depart (50, 'COMPRAS', 'VALENCIA')
crear_depart('VALENCIA', 'COMPRAS');
--Incorrecta: orden incorrecto de parámetros y falta el parámetro para el número de departamento.
crear_depart('VALENCIA', 50);
--Incorrecta: orden incorrecto: crear_depart(50, NULL, 'VALENCIA')

/*------------------------------------------------------*/
/* Actividades complementarias */
/* 1 */

create or replace procedure sumar(num1 number, num2 number)
as
begin
  dbms_output.put_line(num1 + num2);
end;

/*------------------------------------------------------*/
/* 2 */

create or replace procedure reversi(cadena varchar2)
is
	rCadena varchar2(20);
	i binary_integer;
begin
  	i := length(cadena);
    loop
		exit when i < 1;
		rCadena := rCadena ||substr(cadena, i, 1);
		i := i - 1;
	end loop
	dbms_output.put_line(rCadena);
end;

/*------------------------------------------------------*/
/* 3 */

create or replace function sumarF(num1 number, num2 number)
return number
as
begin
  return (num1 + num2);
end;

create or replace function reversiF(cadena varchar2)
return varchar2
is
	rCadena varchar2(20);
	i binary_integer;
begin
  	i := length(cadena);
    loop
		exit when i < 1;
		rCadena := rCadena ||substr(cadena, i, 1);
		i := i - 1;
	end loop
	return (rCadena);
end;

/*------------------------------------------------------*/
/* 4 */

create or replace function extraerAnyo(fecha date)
return number
as
begin
  return to_char(fecha, 'yyyy');
end;

/*------------------------------------------------------*/
/* 5 */

declare
	fecha date := sysdate;
begin
	dbms_output.put_line('Estamos en el año ' || extraerAnyo(fecha));
end;

/*------------------------------------------------------*/
/* 6 */

create or replace function contarAnyos(fecha1 date, fecha2 date)
return number
is
	cantAnyos number(4);
begin
	cantAnyos := trunc(months_between(fecha1, fecha2) / 12);
	return (cantAnyos);
end;

/*------------------------------------------------------*/
/* 7 */

create or replace function contarTrenios(fecha1 date, fecha2 date)
return number
is
	cantTrienios number(4);
begin
	cantTrienios := trunc(contarAnyos(fecha1, fecha2) / 3);
	return (cantTrienios);
end;

/*------------------------------------------------------*/
/* 8 */

create or replace procedure sumarLista(
	num1 number default 0,
	num2 number default 0,
	num3 number default 0,
	num4 number default 0,
	num5 number default 0)
as
	suma number(6);
begin
	suma := num1 + num2 + num3 + num4 + num5;
	dbms_output.put_line('Suma total: ' || suma);
end;

/*------------------------------------------------------*/
/* 9 */

create or replace function convertirAlfa(cadena varchar2)
return varchar2
as
	i binary_integer;
	longitud binary_integer;
	rCadena varchar2(40);
begin
  	longitud := length(cadena);
    loop
		exit when i >= longitud;
		if substr(cadena, i, 1) between 'a' and 'z' then
		  rCadena := rCadena ||substr(cadena, i, 1);
		else
		  rCadena := rCadena || ' ';
		end if;
		
		i := i + 1;
	end loop
	return (rCadena);
end;

/*------------------------------------------------------*/
/* 10 */

create or replace procedure eliminarEmple(numEmple number)
as
begin
  delete emple where emp_no = numEmple;
end;

/*------------------------------------------------------*/
/* 11 */

create or replace procedure cambiarLoc (numDepart number, nuevaLoc varchar2)
as
begin
  update depart set loc = nuevaLoc
  where dept_no = numDepart;
end cambiar_depart;

/*------------------------------------------------------*/
/* 12 */

select object_name, status from user_objects
where object_type = 'PROCEDURE';
/* Ejercicio 1:
Dada la vista Vnotas( Nombrealumno,NombreAsignatura,Nota), diseñar
disparadores para:
-Insertar una nueva nota.
-Modificar la nota de un alumno
-Borrar una nota.
-Borrar todas las notas de un alumno.
*/

create view vnotas as
select apenom, nombre, nota from alumnos a, asignaturas g, notas n
where  a.dni = n.dni and n.cod=g.cod;

create or replace trigger ejercicio1_insert
instead of insert on vnotas
for each row
declare
	Ndni notas.dni%type;
	Ncod notas.cod%type;
begin
	select dni into Ndni from alumnos where apenom = :new.apenom;
	select cod into Ncod from asignaturas where nombre = :new.nombre;
	insert into notas
	values(Ndni, Ncod, :new.nota);
end;

create or replace trigger ejercicio1_update
instead of update on vnotas
for each row
declare
	Ndni notas.dni%type;
begin
	select dni into Ndni from alumnos where apenom = :old.apenom;
	if updating('nota') then
		update notas
		set nota = :new.nota
		where dni = Ndni;
	end if;
end;

create or replace trigger ejercicio1_delete_1
instead of delete on vnotas
for each row
declare
	Ndni notas.dni%type;
	Ncod notas.cod%type;
begin
	select dni into Ndni from alumnos where apenom = :old.apenom;
	select cod into Ncod from asignaturas where nombre = :old.nombre;

	delete from notas
	where dni = Ndni
	and cod = Ncod;
end;

create or replace trigger ejercicio1_delete_2
instead of delete on vnotas
for each row
declare
	Ndni notas.dni%type;
begin
	select dni into Ndni from alumnos where apenom = :old.apenom;

	delete from notas
	where dni = Ndni;
end;

/* Ejercicio 2
Crear una vista, en la que aparezca el apellido del empleado, el
nombre de su departamento, y el apellido de su jefe.
Diseñar disparadores que permitan:
- Insertar un nuevo empleado.
- Cambiar el departamento del empleado.
- Cambiar el jefe del empleado.
- Borrar un empleado. (Puede que sea jefe de otro)
*/

create view vemp as
select e.apellido "ApeEmple", dnombre, j.apellido "ApeJefe"
from emple e, depart d, emple j
where e.dept_no=d.dept_no and e.dir=j.emp_no;

create or replace trigger ejercicio2_insert
instead of insert on vemp
for each row
declare
	Nemp_no emple.emp_no%type;
	Ndir emple.dir%type;
	Ndept_no emple.dept_no%type;
begin
	select emp_no, dept_no, dir
	into Nemp_no, Ndept_no, Ndir
	from emple
	where apellido = :new.ApeEmple;

	insert into emple(emp_no, apellido, dir, dept_no)
	values(Nemp_no, :new.ApeEmple, :new.dir, Ndept_no);
end;

create or replace trigger ejercicio2_update_1
instead of update on vemp
for each row
declare
	Ndept_no emple.dept_no%type;
	Nemp_no emple.emp_no%type;
begin
	if updating('dnombre') then
		select emp_no, dept_no
		into Nemp_no, Ndept_no
		from emple
		where apellido = :new.ApeEmple;

		update emple
		set dept_no = Ndept_no
		where emp_no = Nemp_no;
	end if;
end;

create or replace trigger ejercicio2_update_2
instead of update on vemp
for each row
declare
    Ndept_no emple.dept_no%type;
	Nemp_no emple.emp_no%type;
begin
	if updating('dir') then
		update emple
		set dir = :new.dir
		where apellido = :old.ApeEmple;
	end if;
end;

create or replace trigger ejercicio2_delete
instead of delete on vemp
for each row
declare
    Ndir emple.dir%type;
begin
    -- Borramos el empleado indicado teniendo en cuenta que puede ser el jefe de otro
    -- Seleccionamos el número de empleado de los empleados que puedan tener como jefe al que queremos borrar
    select empl_no into Ndir
    from emple
    where apellido = :old.ApeEmple;

    update emple
    set dir = null
    where dir = :Ndir;

    delete on emple
    where apellido = :old.ApeEmple;
end;

/* Ejercicio 3
Dadas las tablas, Clientes,Productos,Ventas, crear una vista que
muestre:
Nombre del cliente,Descripción del producto, Fecha de
venta,unidades,PrecioUnitario, Subtotal(precio*unidades)

Diseñar disparadores para:
- Insertar una venta.
- Borrar una venta.
- Modificar las unidades de una venta.
- Borrar todas las ventas de un cliente.
- Borrar todas las ventas de un producto.
*/

create view vcliente as
select nombre, descripcion, fecha, unidades, precio_uni, (precio_uni*unidades) "subtotal"
from clientes c, productos p, ventas v
where c.nif = v.nif
and p.cod_producto = v.cod_producto;

create or replace trigger ejercicio3_insert
instead of insert on vcliente
for each row
declare
    Nnif ventas.nif%type;
    Ncod_producto ventas.cod_producto%type;
begin
    select nit into Nnif from clientes where nombre = :new.nombre;
    select cod_producto into Ncod_producto
    from productos
    where descripcion = :new.descripcion;

    insert into ventas
    values(Nnif, Ncod_producto, :new.fecha, :new.unidades);
end;

create or replace trigger ejercicio3_delete_1
instead of delete on vcliente
for each row
declare
    Nnif ventas.nif%type;
    Ncod_producto ventas.cod_producto%type;
begin
    select nit into Nnif from clientes where nombre = :new.nombre;
    select cod_producto into Ncod_producto
    from productos
    where descripcion = :new.descripcion;

    delete from ventas
    where nif = Nnif
    and cod_producto = Ncod_producto;
end;

create or replace trigger ejercicio3_update
instead of update on vcliente
for each row
declare
    Nnif ventas.nif%type;
    Ncod_producto ventas.cod_producto%type;
begin
	if updating('unidades') then
	    select nit into Nnif from clientes where nombre = :new.nombre;
        select cod_producto into Ncod_producto
        from productos
        where descripcion = :new.descripcion;

		update ventas
		set unidades = :new.unidades
		where nif = Nnif
	    and cod_producto = Ncod_producto;
	end if;
end;

create or replace trigger ejercicio3_delete_2
instead of delete on vcliente
for each row
declare
    Nnif ventas.nif%type;
begin
    select nit into Nnif from clientes where nombre = :new.nombre;
    delete from ventas
    where nif = Nnif;
end;

create or replace trigger ejercicio3_delete_3
instead of delete on vcliente
for each row
declare
    Ncod_producto ventas.cod_producto%type;
begin
    select cod_producto into Ncod_producto
    from productos
    where descripcion = :new.descripcion;

    delete from ventas
    where cod_producto = Ncod_producto;
end;

/* Ejercicio 4
Crear un trigger de sistema que inserte una fila en una tabla de
registros, creada previamente, con la siguiente información:
- Nombre del usuario, hora, 'Entrada' si se conecta a la BD
- Nombre del usuario, hora, 'Salida' si finaliza la conexión.
*/

create table registros
(
nomb 	varchar2(20),
hora 	timestamp,
entsal	varchar2(10)
);

create or replace trigger ejercicio4
before logon or logoff on database
begin
	insert into registros
	values(ora_login_user, systimestamp, ora_sysevent);
end;
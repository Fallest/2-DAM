/* Casos prácticos */
/* 1 */

declare
  numEmple number(2);
begin
  insert into depart values (99, 'PROVISIONAL', null);
  update emple set dept_no = 99
    where dept_no = 20;
  numEmple := SQL%ROWCOUNT;
  delete from depart
    where dept_no = 20;
  dbms_output.put_line(numEmple || ' empleados asignados a PROVISIONAL.');
exception
  when others then
    raise_application_error(-2000, 'Error en la aplicación.');
end;
/*----------------------------------------------------------*/
/* 2 */

declare
  ape varchar2(10);
  ofi varchar2(10);
begin
  select apellido, oficio into ape, ofi
  from emple
  where emp_no = 7900;
  dbms_output.put_line(ape || '*' || ofi);
end;
/*----------------------------------------------------------*/
/* 3 */

declare
  ape varchar2(10);
  ofi varchar2(10);
begin
  select apellido, oficio into ape, ofi
  from emple
  where emp_no = 7900;
  dbms_output.put_line(ape || '*' || ofi);
  
  exception
    when no_data_found then
      raise_application_error(-20000, 'Error: No hay datos.');
    when too_many_rows then
      raise_application_error(-20000, 'Error: Demasiados datos.');
    when others then
      raise_application_error(-20000, 'Error: Error en la aplicación.');
end;
/*----------------------------------------------------------*/
/* 4 */

create or replace trigger audit_borrado_emple
	before delete
	on emple
  for each row
begin
  dbms_output.put_line('Borrado el empleado ' || '*' ||:old.emp_no
	|| '*' ||:old.apellido);
end;
/*----------------------------------------------------------*/
/* 5 */

declare
  nom clientes.nombre%type; 
begin
  select nombre into nom
    from clientes
    where cliente_no = &vn_cli;
  dbms_output.put_line(nom);
end;

/*----------------------------------------------------------*/
/* 6 */

create or replace
procedure ver_depart (numdepart number)
as
	v_dnombre varchar2(14);
	v_localidad varchar2(14);
begin

	select dnombre, loc into v_dnombre, v_localidad
	from depart
	where dept_no = numdepart;
	dbms_output.put_line('Num depart:' || numdepart
				|| ' * Nombre dep:' || v_dnombre
				|| ' * Localidad:' || v_localidad);

exception
	when no_data_found then
		dbms_output.put_line('no encontrado el departamento.');

end ver_depart;
/*----------------------------------------------------------*/
/* 7 */
/*7.1*/
create or replace
procedure ver_precio (v_num_producto number)
as
	v_precio number;
begin
	select precio_actual into v_precio
	from productos
	where producto_no = v_num_producto;
	dbms_output.put_line('Precio = ' || v_precio);
end;
/*7.2*/
create or replace
procedure modificar_precio_producto
	(numproducto number, nuevoprecio number)
as
	v_precioant number(5);
begin
	
	select precio_actual into v_precioant
	from productos
	where producto_no = numproducto;

	if(v_precioant * 0.20) > (nuevoprecio - v_precioant) then
		update productos set precio_actual = nuevoprecio
		where producto_no = numproducto;
	else
		dbms_output.put_line('Error, modificación supera el 20%.')
	end if;

exception
	with no_data_found then
		dbms_output.put_line('No encontrado el producto ' || numproducto);

end modificar_precio_producto;
 
end;
/*----------------------------------------------------------*/
/* Actividades propuestas */
/* 1 */

/*----------------------------------------------------------*/
/* Actividades complementarias */
/* 1 */

declare 

begin
	dbms_output.put_line('Hola');
end;
/*----------------------------------------------------------*/
/* 2 */

declare procedure contarProductos
as
	v_num number;
begin
	select count(*) into v_num
	from productos;
	dbms_output.put_line(v_num);
end;


/*Muestra la cantidad de elementos que hay en la tabla Productos.*/
/*----------------------------------------------------------*/
/* 3 */

save 'ActCompl2';
/*----------------------------------------------------------*/
/* 4 */

select count(*)
	from productos;

  COUNT(*)
----------
         9
/*----------------------------------------------------------*/
/* 5 */

@'ActCompl2';

9

Procedimiento PL/SQL terminado correctamente.
/*----------------------------------------------------------*/
/* 6 */

ver_depart(5);
ver_depart(20);
ver_depart(40);
ver_depart(56);

/*----------------------------------------------------------*/
/* 7 */

create or replace
--    		┌-nombre del procedimiento
--		↓	↓-parámetros del procedimiento
procedure ver_precio (v_num_producto number) --<<< Cabecera
as
	-- sección declarativa
	v_precio number; --<<< variables locales
begin --<<< comienzo del bloque PL/SQLg
	-- sección ejecutable
	select precio_actual into v_precio
	from productos
	where producto_no = v_num_producto;
	dbms_output.put_line('Precio = ' || v_precio);
--<<< sección de control de excepciones (no hay)
end; --<<< final del bloque PL/SQL

// La cláusula INTO guarda la información rescatada de un SELECT en una variable
// local.
// WHEN NO_DATA_FOUND filtra la excepción, y si es del tipo NO_DATA_FOUND se 
// ejecuta lo que haya en esa sección.
// En vez de DECLARE tiene la cláusula AS. Es así porque estamos usando
// CREATE or REPLACE para crear el procedimiento.
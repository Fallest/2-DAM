create or replace trigger t1
before update on emple
begin
	dbms_output.put_line('Se ha hecho un update en emple.');
end;


select trigger_name from user_triggers;

create or replace trigger t2
before update or delete on emple for each row
begin
  if updating then
	dbms_output.put_line('El empleado ' || :old.emp_no || ' va a ser modificado.');
  else
	dbms_output.put_line('El empleado ' || :old.emp_no || ' va a ser eliminado.');
  end if;
end;

update emple set salario = 1000 where emp_no = 7876;

update emple set salario = 1800 where dept_no = 10;

delete emple where emp_no = 7876;

create or replace trigger t2
before update on emple for each row
declare
	n number;
begin
  select count(*) into n
  from emple;

  dbms_output.put_line('Se va a modificar el empleado ' || :old.emp_no);
end;

--------------------

/* REGISTROS */
declare
  type reg_persona is record(
    nombre varchar2(20),
    edad number(2)
  ); 
  p1 reg_persona;

  

begin
  p1.nombre := 'Ana';
  p1.edad := 21;
  dbms_output.put_line(p1.nombre || p1.edad);

end;

/* ARRAYS */

create or replace procedure ejemplo1
declare
  type t_persona is varray (4) of reg_persona;

  v1 t_persona:= t_persona(null, null, null, null);
begin
  v1(1).nombre := 'Ana';
  v1(1).edad := 21;

  for i in 1..v1.count loop
    dbms_output.put_line(v1(i).nombre || v1(i).edad);
  end loop;
end;

create or replace procedure ejemplo2 
declare
  type t_persona is TABLE of reg_persona;

  v2 t_persona:= t_persona();
begin
  -- reserva memoria para 3 elementos
  v2.extend(3);
  v2(1).nombre := 'Ana';
  v2(1).edad := 21;

  for i in 1..v2.count loop
    dbms_output.put_line(v2(i).nombre || v2(i).edad);
  end loop;
end;

---------------------------
/* Tabla indexada */

create or replace procedure ejemplo3 
declare
  type ti_persona is TABLE of reg_persona index by binary_integer;
-- Una tabla indexada no se inicializa ni se reserva memoria para ella
  v3 ti_persona;
  indice number;
begin
  -- Le podemos dar el índice que queramos
  v3(4).nombre := 'Ana';
  v3(4).edad := 21;
  indice := v3.first;
  while indice is not null loop
    dbms_output.put_line(v3(indice).nombre || v3(indice).edad);
    indice := v3.next(indice);
  end loop;
end;

-- Creación de un paquete con tipos de variables y procedimientos
create or replace package pk1 as 
-- Cabecera del paquete
  type reg_persona is record(
    nombre varchar2(20),
    edad number(2)
  );

  procedure mostrar(r1 reg_persona);

end;

create or replace package body pk1 as
  procedure mostrar (r1 reg_persona) as
  begin
    dbms_output.put_line(r1.nombre || r1.edad);
  end;
end;

-- Bloque anonimo para probarlo
declare
  v1 pk1.reg_persona;
begin
  v1.nombre := 'PACO';
  v1.edad := 40;

  pk1.mostrar(v1);
end;
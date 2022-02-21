/*Ejemplo 1*/
create or replace procedure ejsqldin
(instruccion varchar2)
as
  id_cursor integer;
  v_dummy integer,
begin
  id_cursor := dbms_sql.open_cursor;
  dbms_sql.parse(id_cursor, instruccion, dbms_sql.v7);

  v_dummy := dbms_sql.execute(id_cursor);
  dbms_sql.close_cursor(id_cursor);

  exception
    when others then
      dbms_sql.close_cursor(id_cursor);
      raise;
end;

grant create user to system;
execute ejsqldin('create user dumm1 identified by dumm1');

execute ejsqldin('create table pr1 (c1 char)');

execute ejsqldin('alter table pr1 add comentario varchar2(20)');

describe pr1;

/*Ejemplo 2*/
create or replace procedure creartabla
(
  nombretabla varchar2,
  nombrecol varchar2,
  longitudcol positive
)
as
  id_cursor integer;
  v_comando varchar2(2000);
begin
  id_cursor := dbms_sql.open_cursor;
  v_comando := 'create table ' || nombretabla || ' ('
    || nombrecol || ' varchar2(' ||longitudcol || '))';

    dbms_sql.parse(id_cursor, v_comando, dbms_sql.native);
    dbms_sql.close_cursor(id_cursor);

    exception
      when others then
        dbms_sql.close_cursor(id_cursor);
        raise;
end;

/*Ejempolo 3*/
create or replace procedure introducir_fila
(nombretabla varchar2,
valor varchar2)
as
  id_cursor integer;
  v_comando varchar2(2000);
  v_filas integer;
begin
  id_cursor := dbms_sql.open_cursor;
  v_comando = 'insert into ' || nombretabla || ' values (:val_1)';
  dbms_sql.parse(id_cursor, v_comando, dbms_sql.native);
  dbms_sql.bind_variable(id_cursor, ':val_1', valor);

  v_filas := dbms_sql.execute(id_cursor);
  dbms_sql.close_cursor(id_cursor);
  dbms_output.put_line('Filas introducidas: ' ||v_filas);

  exception
    when others then
      dbms_sql.close_cursor(id_cursor);
      raise;
end;

execute introducir_fila('mitabla', 'HOLA MUNDO');

select * from mitabla;

/*Ejemplo 4*/
create or replace procedure consultar_emple
(
  condicion varchar2,
  valor varchar2
)
as
  id_cursor integer;
  v_comando varchar2(2000);
  v_dummy number;
  v_emp_no emple.emp_no%type;
  v_apellido emple.apellido%type;
  v_oficio emple.oficio%type;
begin
  id_cursor := dbms_sql.open_cursor;
  v_comando := 'select emp_no, apellido, oficio from emple where ' || condicion || ':val1';

  dbms_output.put_line(v_comando);
  dbms_sql.parse(id_cursor, v_comando, dbms_sql.native);
  dbms_sql.bind_variable(id_cursor, ':val1', valor);

  dbms_sql.define_column (id_cursor, 1, v_emp_no);
  dbms_sql.define_column (id_cursor, 2, v_apellido, 14);
  dbms_sql.define_column (id_cursor, 3, v_oficio, 14);
  v_dummy := dbms_sql.execute(id_cursor);

  while dbms_sql.fetch_rows(id_cursor) > 0 loop
    dbms_sql.column_value(id_cursor, 1, v_emp_no);
    dbms_sql.column_value(id_cursor, 2, v_apellido);
    dbms_sql.column_value(id_cursor, 3, v_oficio);
    dbms_output.put_line(v_emp_no ||'*' || v_apellido || '*' || v_oficio));
  end loop;
  dbms_sql.close_cursor(id_cursor);

  exception
    when others then
      dbms_sql.close_cursor(id_cursor);
      raise;
end;

execute consultar_emple('salario > ', 3850);

/*Ejemplo 5*/
create or replace procedure ejsqldin_nds
(instruccion varchar2)
as
begin
  execute immediate instruccion;
end;

/*Ejemplo 6*/
create or replace procedure borrar
(
  nombre_tabla varchar2, 
  columnaref varchar2,
  valor varchar2
)
as
  instruccion_borrado varchar2(2000);
begin
  instruccion_borrado := 'delete from ' || nombretabla ||
    ' where ' || columnaref || ' = ' || valor;
  execute immediate instruccion_borrado;
end;

execute borrar('emple', 'emp_no', 7902);

/*Ejemplo 7*/
create or replace procedure borrar
(
  nombre_tabla varchar2,
  columnaref varchar2,
  valor varchar2
)
as
  instruccion_borrado varchar2(2000);
begin
  instruccion_borrado := 'delete from ' || nombre_tabla || ' where ' || columnaref || ' = :vt1';
  execute immediate instruccion_borrado using valor;
end;

/*
Ejercicio 5 Actividades complementarias: 
Consultar la tabla DEPART.
*/
create or replace procedure consultar_depart(
  columna varchar2,
  condicion varchar2
)
as
  idcur number;
  comando varchar2(100);
  v_dummy number;

  v_dept_no depart.dept_no%type;
  v_dnombre depart.dnombre%type;
  v_loc depart.loc%type;
begin
  -- Iniciamos el cursor
  idcur := dbms_sql.open_cursor
  -- Creamos el comando
  comando := 'select * from depart where :val_1 = :val_2';
  dbms_sql.parse(idcur, comando, dmbs_sql.native);
  dbms_sql.bind_variable(idcur, ':val_1', columna);
  dbms_sql.bind_variable(idcur, ':val_2', condicion);
  -- Definimos las columnas y ejecutamos el comando
  dbms_sql.define_column (idcur, 1, v_dept_no);
  dbms_sql.define_column (idcur, 2, v_dnombre, 14);
  dbms_sql.define_column (idcur, 3, v_loc, 14);
  v_dummy := dbms_sql.execute(idcur);
  -- Bucle para mostrar la consulta
  while dbms_sql.fetch_rows(idcur) > 0 loop
    dbms_sql.column_value(idcur, 1, v_dept_no);
    dbms_sql.column_value(idcur, 2, v_dnombre);
    dbms_sql.column_value(idcur, 3, v_loc);
    dbms_output.put_line(v_dept_no ||'*' || v_dnombre || '*' || v_loc));
  end loop;
  -- Cerramos el cursor
  dmbs_sql.close_cursor(idcur);

  exception
    when others then
      dbms_sql.close_cursor(idcur);
      raise;
end;
/* Casos prácticos */
/* 1 */

create view emplead as select emp_no, apellido, oficio, dnombre, loc
from emple, depart
where emple.dept_no = depart.dept_no;

create or replace trigger t_ges_emplead
instead of delete or insert or update
on emplead
for each row
declare
    v_dept depart.dept_no%type;
begin
  if deleting then
    delete from emple where emp_no = :old.emp_no;
  elsif inserting then
    select dept_no into v_dept from depart
    where depart.dnombre = :new.dnombre
    and loc = :new.loc;
    
    insert into emple (emp_no, apellido, oficio, dept_no)
    values (:new.emp_no, :new.apellido, :new.oficio, v_dept);
  elsif updating('dnombre') then
    select dept_no into v_dept from depart
    where dnombre = :new.dnombre;

    update emple set dept_no = v_dept
    where emp_no = :old.emp_no;

  elsif updating('oficio') then
    update emple set oficio = :new.oficio
    where emp_no = :old.emp_no;

  else
    raise_application_error(-20500, 'Error en la actualización');
  end if;
end;

/*------------------------------------------------------------------------------*/
/* 2 */

create table control_conexion (
    usuario varchar2(20),
    momento timestamp,
    evento varchar2(20)
);

create table control_eventos (
    usuario varchar2(20),
    momento timestamp,
    evento varchar2(20)
);

create or replace trigger ctrl_conexiones
after logon
on database
begin
  insert into control_conexiones (usuario, momento, evento)
  values (ora_login_user, systimestamp, ora_sysevent);
end;

create or replace trigger ctrl_eventos
after ddl
on database
begin
  insert into control_eventos (usuario, momento, evento)
  values (user, systimestamp, ora_sysevent || '*' || ora_dict_obj_name)
end;

/*------------------------------------------------------------------------------*/
/* 3 */

declare
    cursor c_depar is
        select dnombre, count(emp_ no) numemple
        from depart, emple
        where depart.dept_no = emple.dept_no
        group by depart.dept_no, dnombre;
    
    type tr_depto is record (
        nombredep depart.dnombre%type,
        numemple integer
    );

    type tv_depto is varray (6) of tr_depto;

    va_departamentos tv_depto := tv_depto(null, null, null, null, null, null);
    n integer := 0;
begin
  for vc in c_depar loop
    n := c_depar%rowcount;
    va_departamentos(n) := vc;
  end loop;

  for i in 1..n loop
    dbms_output.put_line(' * Dnombre:' || va_departamentos(i).nombredep ||
    ' * NºEmpleados: ' || va_departamentos(i).numemple);
  end loop;
end;

/*------------------------------------------------------------------------------*/
/* 4 */

declare 
    cursor c_depar is
        select depart.dept_no, fnombre, count(emp_no) numemple
        from depart, emple
        where depart.dept_no = emple.dept_no
        group by depart.dept_no, dnombre;

    type tr_depto is record (
        nombredep depart.dnombre%type,
        numemple integer
    );

    type ti_depto is table of tr_depto index by pls_integer;

    va_departamentos ti_depto;

    n pls_integer := 0;
begin
    for vc in c_depar loop
      va_departamentos(vc.dept_no).nombredep := vc.dnombre;
      va_departamentos(vc.dept_no).numemple := vc.numemple;
    end loop;
    
    n := va_departamentos.FIRST;
    while va_departamentos.exists(n) loop
      dbms_output.put_line(' * Dep Nº :' || n || 
        ' * Dnombre:' || va_departamentos(n).nombredep ||
        ' * NºEmpleados: ' || va_departamentos(n).numemple);
      n := va_departamentos.next(n);
    end loop;
end;


/*------------------------------------------------------------------------------*/
/* Actividades propuestas */
/* 1 
Escribe un trigger que inserte en la tabla auditoriaemple (col1 varchar(200)) cualquier
cambio que supere el 5% del salario del empleado indicando la fecha y hora, el empleado,
y el salario anterior y posterior.
*/

/*------------------------------------------------------------------------------*/
/* 3 
Reescribe el bloque PL/SQL del caso práctico del epígrafe anterior usando una tabla 
anidada en lugar de un VARRAY. Debemos tener presente que no es necesario inicializar
a null varios elementos, sino inicializar con una lista vacía y, después, podemos 
crear nuevos elementos en el bucle de carga usando el método EXTEND.
*/

/*------------------------------------------------------------------------------*/
/* Actividades complementarias */
/* 1 */

/*------------------------------------------------------------------------------*/
/* 2 */

/*------------------------------------------------------------------------------*/
/* 3 */

/*------------------------------------------------------------------------------*/
/* 4 */

/*------------------------------------------------------------------------------*/
/* 5 */

/*------------------------------------------------------------------------------*/
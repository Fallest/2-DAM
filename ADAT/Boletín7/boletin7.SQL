/* Ejercicio 1:
Se desea anotar en la tabla auditoria cualquier hecho ocurrido
(insertar, borrar o modificar) y el número de filas afectadas sobre
la tabla emple. (Ej: "Se han borrado 5 filas")
Resolver utilizando Triggers y Paquetes.
*/

-- Tabla auditoría
create table auditoria (
    descripcion varchar2(200)
);
-- Paquete que contiene el contador a modificar
create or replace package ej1_pk as
    contador number := 0;
end;
-- Antes de realizar alguna operación, reseteamos el valor del contador
create or replace trigger t0
before insert or update or delete on emple
begin
    ej1_pk.contador := 0;
end;
-- Después, contamos las líneas que se van a actualizar
create or replace trigger ej1_t1
after insert or update or delete on emple
for each row
begin
    ej1_pk.contador := ej1_pk.contador + 1;
end;
-- Insertamos el mensaje en la tabla auditoría
create or replace trigger ej1_t2 
after insert or update or delete on emple
begin

    if inserting then
        insert into auditoria values (ej1_pk.contador||' filas insertadas.');
    elsif deleting then
        insert into auditoria values (ej1_pk.contador||' filas eliminadas.');
    elsif updating then
        insert into auditoria values (pej1_pkk1.contador||' filas modificadas.');
    end if;

end;

/* Ejercicio 2:
Se quiere conseguir la siguiente funcionalidad:
Al borrar un empleado, el campo dir se pondrá igual a nulo en todos
los empleados que dependan de él.
Resolver utilizando Triggers y Paquetes.
*/

-- Paquete con una tabla para los números de empleado.
create or replace package delete_dir as
    type tabla is table of emple.emp_no%type
    index by binary_integer;

    tabla_dir tabla;
end;
-- Antes de hacer alguna acción en emple, vamos a vaciar la tabla del paquete.
create or replace trigger ej2_t0
before delete on emple
begin
    delete_dir.tabla_dir.delete;
end;
-- Después creamos una entrada en la tabla indexada para cada empleado eliminado.
create or replace trigger ej2_t1
after delete on emple
for each row
begin
    delete_dir.tabla_dir(:old.emp_no) := :old.emp_no;
end;
-- Después actualizamos los dir de cada empleado para el cual dir era uno de los eliminados.
create or replace trigger ej2_t2 
after delete on emple
declare 
    i emple.emp_no%type;
begin
    i := delete_dir.tabla_dir.first;
    while i is not null loop
        update emple
        set dir = null
        where dir = i;

        i := delete_dir.tabla_dir.next(i);
    end loop;
end;

/* Ejercicio 3:
Retomamos los ejercicios 2 y 3 del boletin 4. Cargamos los dos y
modificamos el código del departamento 10. Su nuevo valor será 11.
Resolver utilizando Triggers y Paquetes.
*/
-- Ejercicio 2 del boletín 4: Modifica los empleados del departamento modificado
create or replace package ej3_pk1
as
    newDeptNo depart.dept_no%type;
    oldDeptNo depart.dept_no%type;
end;

create or replace trigger ej3_t1
before update on depart
for each row
begin
    ej3_pk1.newDeptNo := :new.dept_no;
    ej3_pk1.oldDeptNo := :old.dept_no;
end;

create or replace trigger ej3_t2
after update on depart
for each row
begin
    dbms_output.put_line('Modificando todos los empleados del departamento ' || ej3_pk1.oldDeptNo);
    update emple
    set dept_no = ej3_pk1.newDeptNo
    where dept_no = ej3_pk1.oldDeptNo;
end;

-- Ejercicio 3 del boletín 4: Un empleado no puede pertenecer a un departamento que no existe
create or replace package ej3_pk2
as
    existe_dept depart.dept_no%type;

    procedure comprobarDepart(deptNo depart.dept_no%type);
end;

create or replace package body ej3_pk2 as
    procedure comprobarDepart(deptNo depart.dept_no%type); as 
    begin
        select dept_no into ej3_pk2.existe_dept 
        from depart
        where dept_no = deptNo;
    end;
end;

create or replace trigger ej3_t3
before update or insert on emple
begin
    ej3_pk2.comprobarDepart(:new.dept_no);
    if ej3_pk2.existe_dept != null then
        dbms_output.put_line('Comprobación sin errores.');
    else
        dbms_output.put_line('El departamento indicado no existe.');
        rollback;
    end if;
end;

/* Ejercicio 4:
Añadir un campo nuevo a la tabla depart llamado media_salario que
almacene la media de salarios de cada departamento.
Se quiere que se actualice su valor al insertar, borrar y modificar
la tabla emple. Resolver utilizando Triggers y Paquetes.
*/

alter table depart add(media_salario number(6,2) default 0);

create or replace package ej4_pk1
as
    media_salarios depart.media_salario%type;

    procedure calcularMedia(deptNo depart.dept_no%type);
end;

create or replace package body ej4_pk1 as
    procedure calcularMedia(deptNo depart.dept_no%type); as 
    begin
        select avg(salario) into ej4_pk1.media_salarios
        from emple
        where dept_no = deptNo;
    end;
end;

create or replace trigger ej4_t1
after insert or delete or update on emple
for each row
begin
    ej4_pk1.calcularMedia(:new.dept_no);

    update depart
    set media_salario = ej4_pk1.media_salarios
    where dept_no = :new.dept_no;
end;
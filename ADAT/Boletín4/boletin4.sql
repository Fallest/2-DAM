/* 1 - Crear un trigger que simule un borrado en cascada, de modo que al
borrar un departamento, borre todos los empleados de ese
departamento.
*/

create or replace trigger ejercicio1
before delete on depart for each row
begin
    dbms_output.put_line('Eliminando todos los empleados del departamento ' || :old.dept_no);
    delete emple where dept_no = :old.dept_no;
end;

/*------------------------------------------------------------------------------*/
/* 2 - Crear un trigger que simule una modificación en cascada, de modo
que al modificar un departamento, actualice su valor para todos sus
empleados.
*/

create or replace trigger ejercicio2
before update on depart for each row
begin
    dbms_output.put_line('Modificando todos los empleados del departamento ' || :old.dept_no);
    update emple set dept_no = :new.dept_no where dept_no = :old.dept_no;
end;

/*------------------------------------------------------------------------------*/
/* 3 - Crear un trigger que impida que un empleado pertenezca a un
departamento inexistente.
*/

create or replace trigger ejercicio3
before update or insert on emple for each row
declare 
    existe_dept integer;
begin
    dbms_output.put_line('Comprobando que el departamento existe...');
    select dept_no into existe_dept 
    from depart
    where dept_no = :new.dept_no;

    dbms_output.put_line('Comprobación sin errores.');

    exception
        when no_data_found then
            dbms_output.put_line('El departamento indicado no existe.');
            rollback;
end;

/*------------------------------------------------------------------------------*/
/* 4 - Crear un trigger para impedir que se aumente el salario de un
empleado en más de un 20%.
*/

create or replace trigger ejercicio4
before update on emple for each row
declare 
    comp_salario integer;
begin
    dbms_output.put_line('Comprobando que el salario no se ha incrementado más de un 20%...');
    comp_salario := (:new.salario * 100 / :old.salario) - (100);

    if comp_salario >= 20 then
        dbms_output.put_line('El nuevo salario supera el 120% del salario anterior.');
        rollback;
    else
        dbms_output.put_line('Comprobación sin errores.');
    end if;
end;

/*------------------------------------------------------------------------------*/
/* 5 - Escribir un disparador de base de datos que haga fallar cualquier
operación de modificación del apellido o del número de un empleado, o
que suponga una subida de sueldo superior al 20%.
*/

create or replace trigger ejercicio5
before update on emple for each row
declare 
    comp_salario integer;
begin
    dbms_output.put_line('Comprobando que el salario no se ha incrementado más de un 20%...');
    comp_salario := (:new.salario * 100 / :old.salario) - (100);

    if comp_salario >= 20 then
        dbms_output.put_line('El nuevo salario supera el 120% del salario anterior.');
        rollback;
    end if;

    dbms_output.put_line('Comprobando que el apellido no se ha modificado...');
    if :old.apellido <> :new.apellido then
        dbms_output.put_line('No se puede modificar el apellido.');
        rollback;
    end if;

    dbms_output.put_line('Comprobando que el número de empleado no se ha modificado...');
    if :old.emp_no <> :new.emp_no then
        dbms_output.put_line('No se puede modificar el número de empleado.');
        rollback;
    end if;

    dbms_output.put_line('Comprobación sin errores.');
end;

/*------------------------------------------------------------------------------*/
/* 6 - Crear un trigger que garantice que la comisión de los nuevos
empleados sea del 1% de su salario.
*/

create or replace trigger ejercicio6
before insert on emple for each row
begin
    dbms_output.put_line('Comprobando que la comisión es del 1% del salario...');
    if :new.comision < (:new.salario * (1 / 100)) then
        dbms_output.put_line('La comisión no es suficiente.');
        rollback;
    end if;

    dbms_output.put_line('Comprobación sin errores.');
end;

/*------------------------------------------------------------------------------*/
/* 7 - Dadas las tablas Centros, Personal y Profesores, crear un trigger
que al insertar o borrar un profesor en la tabla Personal mantenga
actualizada la tabla Profesores.
*/

create or replace trigger ejercicio7
before insert or delete on Personal for each row
begin
  if :old.FUNCION = 'PROFESOR' then
    if inserting then 
        dbms_output.put_line('Insertando el profesor en la tabla Profesores.');
        insert into Profesores values(:old.cod_centro, :old.dni, :old.apellidos, 'N/A');
    else
        dbms_output.put_line('Eliminando el profesor de la tabla Profesores.');
        delete Profesores where dni = :old.dni;
    end if;
  end if;
end;

/*------------------------------------------------------------------------------*/
/* 8 - Diseñar un trigger que mantenga actualizado el contenido de la
tabla estadísticas.
LIBROS(isbn,genero,titulo,autor)
ESTADISTICAS(genero,total_libros)
*/

create or replace trigger ejercicio8
before insert or update or delete on LIBROS for each row
begin

    if inserting then
        dbms_output.put_line('Incrementando la cantidad de libros del género ' || :new.genero || ' en 1.');
        update estadisticas
        set total_libros = total_libros + 1
        where genero = :new.genero;

    elsif deleting then
        dbms_output.put_line('Decrementando la cantidad de libros del género ' || :old.genero || ' en 1.');
        update estadisticas
        set total_libros = total_libros - 1
        where genero = :old.genero;

    elsif updating then
        if :old.genero <> :new.genero then
            dbms_output.put_line('Incrementando la cantidad de libros del género ' || :new.genero || ' en 1.');
            update estadisticas
            set total_libros = total_libros + 1
            where genero = :new.genero;
            dbms_output.put_line('Decrementando la cantidad de libros del género ' || :old.genero || ' en 1.');
            update estadisticas
            set total_libros = total_libros - 1
            where genero = :old.genero;
        end if;
    end if;
end;

/*------------------------------------------------------------------------------*/
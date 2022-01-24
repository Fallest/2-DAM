/* 1 - Crear un trigger para impedir que ningún departamento tenga más de 7 
empleados.
*/

create or replace trigger boletin5ejercicio1 
before insert on emple for each row
declare
    cant_emple integer;
begin
    -- Contamos los empleados del departamento
    select count(*) into cant_emple
    from emple
    where dept_no = :new.dept_no;
    -- Comprobamos que aún no hay 7
    if cant_emple >= 7 then
        dbms_output.put_line('ERROR: Ya hay 7 empleados en ese departamento.');
        rollback;
    else
        dbms_output.put_line('El departamento ahora contiene ' || cant_emple+1 || ' empleados.');
    end if;
    

    exception
        when no_data_found then
            dbms_output.put_line('LOG: El departamento no tiene empleados.');
end;


/*------------------------------------------------------------------------------*/
/* 2 - Crear trigger para impedir que el salario total por departamento sea 
superior a 1500000 euros.
*/

create or replace trigger boletin5ejercicio2 
before insert or update on emple for each row
declare
    suma_sal integer;
begin
    -- Obtenemos la suma de salarios del departamento
    select sum(salario) into suma_sal
    from emple
    where dept_no = :new.dept_no;

    if suma_sal + :new.salario > 1500000 then
        dbms_output.put_line('ERROR: El salario total del departamento supera 1500000 euros.');
        rollback;
    else
        dbms_output.put_line('Salario actual del departamento: ' || suma_sal + :new.salario);
    end if;
    

    exception
        when no_data_found then
            dbms_output.put_line('LOG: El departamento no tiene empleados.');
end;

/*------------------------------------------------------------------------------*/
/* 3 - Crear un trigger sobre la tabla empleados para que no se permita que 
un empleado sea jefe de más de cinco empleados. 
*/

create or replace trigger boletin5ejercicio3
before insert or update on emple for each row
declare
    cont_emple integer;
begin
    -- Contamos los empleados supervisados por el actual
    select count(*) into cont_emple
    from emple
    where dir = :new.emp_no;

    if cont_emple > 5 then
        dbms_output.put_line('ERROR: El empleado actual tiene más de 5 empleados a su cargo.');
        rollback;
    else
        dbms_output.put_line('Empleados a cargo: ' || cont_emple);
    end if;
    

    exception
        when no_data_found then
            dbms_output.put_line('LOG: El empleado no es jefe de nadie.');
end;

/*------------------------------------------------------------------------------*/
/* 4 - Crear un trigger para asegurar que ningún empleado pueda cobrar más 
que su jefe.
*/

create or replace trigger boletin5ejercicio4
before insert or update on emple for each row
declare
    sal_jefe integer;
begin
    -- Primero extraemos el salario del jefe
    select salario into sal_jefe
    from emple
    where emp_no = :new.dir;

    if sal_jefe < :new.salario then
        dbms_output.put_line('ERROR: El empleado no puede tener un salario mayor que su jefe.');
        rollback;
    end if;
    

    exception
        when no_data_found then
            dbms_output.put_line('LOG: El empleado no tiene jefe.');
end;

/*------------------------------------------------------------------------------*/
/* 5 - Crear un trigger para impedir que un empleado y su jefe pertenezcan a 
departamentos distintos.
*/

create or replace trigger boletin5ejercicio5
before insert or update on emple for each row
declare
    dep_jefe integer;
begin
    -- Extraemos el departamento del jefe
    select dept_no into dep_jefe
    from emple
    where emp_no = :new.dir;
    -- Lo comparamos con el departamento del empleado nuevo
    if dep_jefe <> :new.dir then
        -- Si no son iguales, rollback
        dbms_output.put_line('ERROR: El departamento del jefe y del empleado no coinciden.');
        rollback;
    end if;


    exception
        when no_data_found then
            dbms_output.put_line('LOG: El empleado no tiene jefe.');
end;

/*------------------------------------------------------------------------------*/
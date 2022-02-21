/*
El paquete se llamará gest_emple e incluirá, al menos los siguientes subprogramas:

-insertar_nuevo_emple
-borrar_emple: Cuando se borra un empleado todos los empleados que
dependían de él pasarán a depender del director del empleado borrado.
-modificar_oficio_emple
-modificar_dept_emple
-modificar_dir_emple
-modificar_salario_emple
-modificar_comision_emple
-visualizar_datos_emple: También se incluirá una versión sobrecargada del
procedimiento que recibirá el apellido del empleado.
-buscar_emple_por_apellido. Función local que recibe el apellido y devuelve
el número.

Todos los procedimientos recibirán el número del empleado seguido de los demás
datos necesarios.
También se incluirá en el paquete un cursor, que será utilizado en los
siguientes procedimientos que afectarán a todos los empleados:

-subida_salario_pct: incrementará el salario de todos los empleados el
porcentaje indicado en la llamada que no podrá ser superior al 25%.
-subida_salario_imp: sumará al salario de todos los empleados el importe
indicado en la llamada. Antes de proceder a incrementar los salarios se
comprobará que el importe indicado no supera el 25% del salario medio.
*/

create or replace package gest_emple as
    type empleado is record (
        empNo number(4), 
        apellido varchar2(10),
        oficio varchar2(10), 
        dir number(4), 
        fechaAlta date, 
        salario number(10), 
        comision number(10), 
        deptNo number(2)
    );

    cursor empleados is 
        select * from emple;

    procedure insertar_nuevo_emple(PempNo number, Papellido varchar2, Poficio varchar2, 
        Pdir number, PfechaAlta date, Psalario number, Pcomision number, PdeptNo number);
    procedure borrar_emple(PempNo number);
    procedure modificar_oficio_emple(PempNo number, PnuevoOficio varchar2);
    procedure modificar_dept_emple(PempNo number, PnuevoDept number);
    procedure modificar_dir_emple(PempNo number, PnuedoDir number);
    procedure modificar_salario_emple(PempNo number, PnuevoSal number);
    procedure modificar_comision_emple(PempNo number, PnuevaCom number);
    procedure visualizar_datos_emple(Papellido varchar2);
    procedure visualizar_datos_emple();
    function buscar_emple_por_apellido(Papellido varchar2);
    procedure subida_salario_pct(pct number);
    procedure subida_salario_imp(imp number);
end;

create or replace package body gest_emple as

    -- Insertar nuevo empleado
    procedure insertar_nuevo_emple(PempNo number, Papellido varchar2, Poficio varchar2, 
        Pdir number, PfechaAlta date, Psalario number, Pcomision number, PdeptNo number) as
    
    begin
        insert into emple values (PempNo, Papellido, Poficio, Pdir, PfechaAlta, Psalario, Pcomision, PdeptNo);
        dbms_output.put_line('Nuevo empleado insertado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al insertar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Borrar un empleado
    procedure borrar_emple(PempNo number) as
    begin
        delete from emple where emp_no = PempNo;

        dbms_output.put_line('Empleado eliminado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al eliminar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Modiciar oficio
    procedure modificar_oficio_emple(PempNo number, PnuevoOficio varchar2) as
    begin
        update emple set oficio=PnuevoOficio
        where emp_no = PempNo;

        dbms_output.put_line('Empleado modificado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al modificar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Modificar departamento
    procedure modificar_dept_emple(PempNo number, PnuevoDept number) as
    begin
        update emple set dept_no=PnuevoDept
        where emp_no = PempNo;

        dbms_output.put_line('Empleado modificado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al modificar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Modificar el jefe
    procedure modificar_dir_emple(PempNo number, PnuedoDir number) as
    begin
        update emple set dir=PnuevoDir
        where emp_no = PempNo;

        dbms_output.put_line('Empleado modificado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al modificar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Modificar el salario
    procedure modificar_salario_emple(PempNo number, PnuevoSal number) as
    begin
        update emple set salario=PnuevoSal
        where emp_no = PempNo;

        dbms_output.put_line('Empleado modificado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al modificar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Modificar la comisión
    procedure modificar_comision_emple(PempNo number, PnuevaCom number) as
    begin
        update emple set comision=PnuevaCom
        where emp_no = PempNo;

        dbms_output.put_line('Empleado modificado.');

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al modificar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Ver los datos del empleado indicado
    procedure visualizar_datos_emple(Papellido varchar2) as
        emp empleado;
    begin
        select * into emp
        from emple where apellido = Papellido; 

        -- Mostrar los datos
        dbms_output.put_line('Número: ' || emp.empNo 
            || ' * Apellido: ' || emp.apellido
            || ' * Oficio: ' || emp.oficio
            || ' * Dir: ' || emp.dir
            || ' * Fecha Alta: ' || emp.fechaAlta
            || ' * Salario: ' || emp.salario
            || ' * Comision: ' || emp.comision
            || ' * Departamento: ' || emp.deptNo);

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al mostrar el empleado. Haciendo rollback...');
            rollback;
    end;

    -- Ver los datos de todos los empleados
    procedure visualizar_datos_emple() as
    begin
        for emp in empleados loop
            dbms_output.put_line('Número: ' || emp.emp_No 
            || ' * Apellido: ' || emp.apellido
            || ' * Oficio: ' || emp.oficio
            || ' * Dir: ' || emp.dir
            || ' * Fecha Alta: ' || emp.fecha_Alta
            || ' * Salario: ' || emp.salario
            || ' * Comision: ' || emp.comision
            || ' * Departamento: ' || emp.dept_No
            || '********************************');
        end loop;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al mostrar los empleados. Haciendo rollback...');
            rollback;
    end;

    -- Función buscar un empleados
    function buscar_emple_por_apellido(Papellido varchar2)
    return number
    as
        emp empleado;
    begin
        select * into emp
        from emple
        where apellido = Papellido;

        return emp.empNo;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al buscar al empleado. Haciendo rollback...');
            rollback;
    end;

    -- Subir el salario un porcentaje
    procedure subida_salario_pct(pct number) as
    begin
        -- Comprobar que el porcentaje no es más del 25%.
        if pct > 25 then
            raise_application_error(-25001, 'El porcentaje no puede ser mayor que 25%.');
        else
            for emp in empleados loop
                update emple set salario = salario*(1 + (pct/100))
                where emp_no = emp.emp_no;
            end loop;
        end if;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al actualizar los salarios. Haciendo rollback...');
            rollback;
    end;

    -- Subir el salario una cantidad
    procedure subida_salario_imp(imp number) as
        salMedio number;
    begin
        -- Calcular el salario medio
        select avg(salario) into salMedio from emple;

        -- Comprobar que el importe no es más del 25% del salario medio.
        if imp > (salMedio * 0,25) then
            raise_application_error(-25001, 'El importe no puede ser mayor al 25% del salario medio.');
        else
            for emp in empleados loop
                update emple set salario = salario + imp
                where emp_no = emp.emp_no;
            end loop;
        end if;

        exception
          when others then
            dbms_output.put_line('ERROR: Ha ocurrido un error al actualizar los salarios. Haciendo rollback...');
            rollback;
    end;
end;
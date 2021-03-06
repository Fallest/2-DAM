/* Ejercicio 1 */

create or replace procedure datosEmple(numEmple number)
as
  vApellido emple.apellido%TYPE;
  vSalario emple.salario%TYPE;
  vNomDep depart.dnombre%TYPE;

begin
 
  select apellido, salario, dnombre into vApellido, vSalario, vNomDep
  from emple, depart
  where emple.dept_no = depart.dept_no
  and emp_no = numEmple;

  dbms_output.put_line(	vApellido || '*' || vSalario || '*' || vNomDep);

  exception
    when NO_DATA_FOUND then
      raise_application_error(-2000, 'Error: No existe ese empleado.');
end;
 
/*----------------------------------------------------------*/
/* Ejercicio 2 */

create or replace procedure eliminaEmple(numEmple number)
as
  vEmpno emple.emp_no%TYPE;
begin
  select emp_no into vEmpno from emple where emp_no = numEmple;
  delete emple where emp_no = numEmple;

  exception
    when no_data_found then
      raise_application_error(-2000, 'Error: Empleado no encontrado.');
      
end;

create or replace procedure cambiarLoc (numDepart number, nuevaLoc varchar2)
as
  vDeptno depart.dept_no%TYPE;
begin
  select dept_no into vDeptno from depart where dept_no = numDepart;
  update depart set loc = nuevaLoc
  where dept_no = numDepart;
  
  exception
    when no_data_found then
      raise_application_error(-2000, 'Error: Departamento no encontrado.');
end cambiarLoc;
/*----------------------------------------------------------*/
/* Ejercicio 3 */

create or replace procedure eliminaEmple(numEmple number)
as
  numFilas number;
  vEmpno emple.emp_no%TYPE;
begin
  select emp_no into vEmpno from emple where emp_no = numEmple;
  numFilas := SQL%ROWCOUNT;
  if numFilas = 0 then
    raise no_data_found;
  end if;

  delete emple where emp_no = numEmple;  

  exception
    when no_data_found then
      raise_application_error(-2000, 'Error: Empleado no encontrado.');
      
end;

create or replace procedure cambiarLoc (numDepart number, nuevaLoc varchar2)
as
  numFilas number;
  vDeptno depart.dept_no%TYPE;
begin
  select dept_no into vDeptno from depart where dept_no = numDepart;

  numFilas := SQL%ROWCOUNT;
  if numFilas = 0 then
    raise no_data_found;
  end if;

  update depart set loc = nuevaLoc
  where dept_no = numDepart;

  exception
    when no_data_found then
      raise_application_error(-2000, 'Error: Departamento no encontrado.');
end cambiarLoc;

/*----------------------------------------------------------*/
/* Ejercicio 4 */

create or replace procedure cambiar_depart (num_empleado number, nuevo_depart number)
as
  vEmpno emple.emp_no%TYPE;
  vDeptno depart.dept_no%TYPE;
  vNumfilas number;
  v_anterior_depart depart.dept_no%TYPE;
begin

  begin
    --Comprobar el empleado
    select emp_no into vEmpno from emple where emp_no = num_empleado;
    
    exception
      when no_data_found then
        raise_application_error(-20001, 'Error: No existe el empleado');
  end;
  begin
    --Comprobar el departamento
    select dept_no into vDeptno from depart where dept_no = nuevo_depart;
    
    exception
      when no_data_found then
        raise_application_error(-20002, 'Error: No existe el departamento');
  end;

  select depart.dept_no into v_anterior_depart from emple, depart
  where emp_no = num_empleado
  and emple.dept_no = depart.dept_no;

  update emple set dept_no = nuevo_depart
  where emp_no = num_empleado;

  dbms_output.put_line(num_empleado || '*Departamento anterior: ' ||     v_anterior_depart || '*Departamento nuevo: ' || nuevo_depart);

end cambiar_depart;

/*----------------------------------------------------------*/
/* Ejercicio 5 */

create or replace procedure cambiar_depart (num_empleado number, nuevo_depart number)
as
  vEmpno emple.emp_no%TYPE;
  vDeptno depart.dept_no%TYPE;
  vbandera number;
  v_anterior_depart depart.dept_no%TYPE;
begin

  --Comprobar el empleado
  vbandera := 1;
  select emp_no into vEmpno from emple where emp_no = num_empleado;    
  --Comprobar el departamento
  vbandera := 2;
  select dept_no into vDeptno from depart where dept_no = nuevo_depart;

  vbandera := 3;
  select depart.dept_no into v_anterior_depart from emple, depart
  where emp_no = num_empleado
  and emple.dept_no = depart.dept_no;

  update emple set dept_no = nuevo_depart
  where emp_no = num_empleado;

  dbms_output.put_line(num_empleado || '*Departamento anterior: ' ||     v_anterior_depart || '*Departamento nuevo: ' || nuevo_depart);

  exception
    when no_data_found then
      if vbandera = 1 then
        raise_application_error(-20001, 'Error: No existe el empleado');
      elsif vbandera = 2 then
        raise_application_error(-20001, 'Error: No existe el departamento');
      end if;

end cambiar_depart;

/*----------------------------------------------------------*/
/* Ejercicio 6 */

create or replace function buscar_emple(num_empleado number)
return number
is
  vEmpno emple.emp_no%TYPE;
begin
  --Comprobar el empleado
  select emp_no into vEmpno from emple where emp_no = num_empleado;

  return 0;

  exception
    when no_data_found then
      return -1;
end buscar_emple;

create or replace function buscar_departamento(num_depart number)
return number
is
  vDeptno depart.dept_no%TYPE;
begin
  --Comprobar el departamento
  select dept_no into vDeptno from depart where dept_no = nuevo_depart;

  return 0;
  
  exception
    when no_data_found then
      return -1;
end buscar_departamento;

create or replace procedure cambiar_depart (num_empleado number, nuevo_depart number)
as
  vExisteEmp number;
  vExisteDepart number;
  v_anterior_depart depart.dept_no%TYPE;
begin
  select buscar_emple(num_empleado) into vExisteEmp from dual;
  select buscar_departamento(nuevo_depart) into vExisteDepart from dual;

  if vExisteEmp = -1 then
    raise_application_error(-20001, 'Error: No existe el empleado');
  elsif vExisteDepart = -1 then
    raise_application_error(-20002, 'Error: No existe el departamento');
  end if;

  select depart.dept_no into v_anterior_depart from emple, depart
  where emp_no = num_empleado
  and emple.dept_no = depart.dept_no;

  update emple set dept_no = nuevo_depart
  where emp_no = num_empleado;

  dbms_output.put_line(num_empleado || '*Departamento anterior: ' ||     v_anterior_depart || '*Departamento nuevo: ' || nuevo_depart);

end cambiar_depart;
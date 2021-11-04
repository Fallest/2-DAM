/*
Hacer CP 3, 4, 5; AP 3, 4, 5.
*/
/* ---------------------- Casos Prácticos --------------------- */
/* Caso Práctico 1: */

declare
    cursor c1 is
    select apellido from emple where dept_no = 20;

    vApellido varchar2(10);

begin
  open c1;
  loop
    fetch c1 into vApellido;
    dbms_output.put_line(to_char(c1%ROWCOUNT, '99.') || vApellido);
    exit when c1%NOTFOUND;
  end loop;
  close c1;
end;

/*--------------------------------------------------------------*/
/* Caso Práctico 2: */

create or replace procedure verEmplePorDept(dep varchar2)
as
    vDept number(2);
    cursor c1 is
        select apellido from emple where dept_no = vDept;
    vApellido varchar2(10);

begin
  vDept := dep;
  open c1;
  fetch c1 into vApellido;

  while c1%found loop
    dbms_output.put_line(vApellido);
    fetch c1 into vApellido;
  end loop;

  close c1;

end;

/*--------------------------------------------------------------*/
/* Caso Práctico 3: */

/*3.1*/
declare 
  cursor cEmple is
    select apellido, fecha_alt
    from emple
    order by fecha_alt;
begin
  for v_reg_emp in cEmple loop
    dbms_output.put_line(v_reg_emp.apellido||'*'||v_reg_emp.fecha_alt);
  end loop
end;
/*3.2*/
declare 
  cursor cEmple is
    select apellido, fecha_alt
    from emple
    order by fecha_alt;
  v_reg_emple c_emple%ROWTYPE;
begin
  open cEmple;
  fetch cEmple into v_reg_emp;
  while c_emple%found loop
    dbms_output.put_line(v_reg_emp.apellido||'*'||v_reg_emp.fecha_alt);
    fetch cEmple into v_reg_emp;
  end loop
  close cEmple;
end;

/*--------------------------------------------------------------*/
/* Caso Práctico 4: */

create or replace procedure listarEmple
as
  cursor c1 is
    select apellido, salario, dept_no
    from emple
    order by dept_no, apellido;
  vrEmp c1%ROWTYPE;
  depAnt emple.dept_no%TYPE default 0;
  contEmple number(4) default 0;
  sumSal number (9,2) default 0;
  totEmple number(4) default 0;
  totSal number(10,2) default 0;

begin
  open c1;
  loop
    fetch c1 into vrEmp;
    if c1%ROWCOUNT = 1 then
      depAnt := vrEmp.dept_no;
    end if;

    if depAnt <> vrEmp.dept_no or c1%NOTFOUND then
      dbms_output.put_line('*** DEPTO: ' || depAnt || ' NUM. EMPLEADOS: ' ||
        contEmple || ' SUM. SALARIOS:  ' || sumSal);
      depAnt := vrEmp.dept_no;
      totEmple := totEmple + contEmple;
      totSal := totSal + sumSal;
      contEmple := 0;
      sumSal := 0;
    end if;

    exit when c1%NOTFOUND;

    dbms_output.put_line(rpad(vrEmp.apellido, 10) || ' * ' 
      || lpad(to_char(vrEmp.salario, '999,999'), 12));
    contEmple := contEmple + 1;
    sumSal := sumSal + vrEmp.salario;

  end loop;

  close c1;
end;

/*--------------------------------------------------------------*/
/* Caso Práctico 5: */



/*--------------------------------------------------------------*/
/* Caso Práctico 6: */

/*--------------------------------------------------------------*/
/* Caso Práctico 7: */


/*--------------------------------------------------------------*/
/* ------------------ Actividades Propuestas ------------------ */
/* Actividades propuestas 1: */

-- Versión con LOOP-EXIT WHEN:
declare
    cursor c1 is
    select apellido from emple where dept_no = 20;

    vApellido varchar2(10);

begin
  open c1;
  fetch c1 into vApellido;
  loop
    dbms_output.put_line(to_char(c1%ROWCOUNT, '99.') || vApellido);
    fetch c1 into vApellido;
    exit when c1%NOTFOUND;
  end loop;
  close c1;
end;

--Versión con WHILE LOOP:
declare
    cursor c1 is
    select apellido from emple where dept_no = 20;

    vApellido varchar2(10);

begin
  open c1;
  fetch c1 into vApellido;
  while c1%found loop
    dbms_output.put_line(to_char(c1%ROWCOUNT, '99.') || vApellido);
    fetch c1 into vApellido;
  end loop;
  close c1;
end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 2: */

create or replace procedure empleContieneCad(cad varchar2)
as
    vCad emple.apellido%type;
    cursor c1 is
        select apellido, emp_no 
        from emple 
        where 0 not in(
            select instr(apellido, vCad, 1, 1)
            from dual
        );
    vApellido varchar2(10);
    vEmp_no emple.emp_no%type;

begin
  vCad := cad;
  open c1;
  fetch c1 into vApellido, vEmp_no;
  
  while c1%found loop
    dbms_output.put_line(vApellido || '*' || vEmp_no);
    fetch c1 into vApellido, vEmp_no;
  end loop;

  dbms_output.put_line('Empleados encontrados: ' || c1%ROWCOUNT);

  close c1;

end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 3: */

create or replace procedure empleContieneCad(cad varchar2)
as
    vCad emple.apellido%type;
    cursor c1 is
        select apellido, emp_no 
        from emple 
        where 0 not in(
            select instr(apellido, vCad, 1, 1)
            from dual
        );
    cont integer;

begin
  vCad := cad;
  cont := 0;

  for reg in c1 loop
    dbms_output.put_line(reg.apellido || '*' || reg.emp_no);
    cont := cont + 1;
  end loop;
  
  dbms_output.put_line('Empleados encontrados: ' || cont);
end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 4: */


create or replace procedure listarEmple
as
  cursor c1 is
    select apellido, salario, dept_no, oficio
    from emple
    order by dept_no, oficio;
  vrEmp c1%ROWTYPE;
  depAnt emple.dept_no%TYPE default 0;
  oficAnt emple.oficio%TYPE;
  contEmple number(4) default 0;
  contEmpleOfic number(4) default 0;
  sumSal number (9,2) default 0;
  totEmple number(4) default 0;
  totSal number(10,2) default 0;

begin
  for vrEmp in c1 loop
    -- Comprobamos si estamos en el primer registro.
    if c1%ROWCOUNT = 1 then
      depAnt := vrEmp.dept_no;
      oficAnt := vrEmp.oficio;
    end if;

    --Cada vez que avancemos registro, debemos comprobar dos cosas:
    -- 1. Si hemos pasado de un oficio a otro.
    -- 2. Si hemos pasado de un departamento a otro.
    
    if oficAnt <> vrEmp.oficio then
      if contEmpleOfic <> 0 then
        dbms_output.put_line('-- OFICIO: ' || oficAnt || ' - NUM. EMPLEADOS: ' ||
          contEmpleOfic);
      end if;
      oficAnt := vrEmp.oficio;
      contEmpleOfic := 0;
    end if;

    if depAnt <> vrEmp.dept_no then
      if contEmpleOfic <> 0 then
        dbms_output.put_line('-- OFICIO: ' || oficAnt || ' - NUM. EMPLEADOS: ' ||
          contEmpleOfic);
      end if;
      dbms_output.put_line('*** DEPTO: ' || depAnt || ' - NUM. EMPLEADOS: ' ||
        contEmple || ' SUM. SALARIOS:  ' || sumSal);
      dbms_output.put_line('---------------------------------------');
      depAnt := vrEmp.dept_no;
      oficAnt := vrEmp.oficio;
      totEmple := totEmple + contEmple;
      totSal := totSal + sumSal;
      contEmple := 0;
      contEmpleOfic := 0;
      sumSal := 0;
    end if;

    dbms_output.put_line(rpad(vrEmp.apellido, 10) || ' * ' 
      || lpad(to_char(vrEmp.salario, '999,999'), 12));
    contEmple := contEmple + 1;
    contEmpleOfic := contEmpleOfic + 1;
    sumSal := sumSal + vrEmp.salario;

  end loop;

  if contEmpleOfic <> 0 then
    dbms_output.put_line('-- OFICIO: ' || oficAnt || ' - NUM. EMPLEADOS: ' ||
      contEmpleOfic);
  end if;
  dbms_output.put_line('*** DEPTO: ' || depAnt || ' - NUM. EMPLEADOS: ' ||
    contEmple || ' SUM. SALARIOS:  ' || sumSal);
end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 5: */

create or replace procedure subirSalarioDept(
  parNumDept number,
  parPlus number)
as
  cursor curEmp is 
    select oficio, salario, rowid
    from emple where dept_no = parNumDept;

  vcRegEmp curEmp%ROWTYPE;
  cont number(4) default 0;
begin
  open curEmp;
  fetch curEmp into vcRegEmp;

  while curEmp%FOUND loop

    update emple
    set salario = salario + parPlus
    where rowid = vcRegEmp.rowid;
    cont := cont + 1;
    fetch curEmp into vcRegEmp;
  end loop;

  dbms_output.put_line('Empleados actualizados: ' || cont);

  close curEmp;
end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 6: */

/*--------------------------------------------------------------*/
/* Actividades propuestas 7: */


/*--------------------------------------------------------------*/
/* --------------- Actividades Complementarias ---------------- */
/* Actividades complementarias 1: */

declare
    cursor cur is 
        select apellido, fecha_alt
        from emple
        order by apellido;
    
    vApe emple.apellido%type;
    vFecha emple.fecha_alt%type;
begin
  open cur;
  fetch cur into vApe, vFecha;

  while cur%found loop
    dbms_output.put_line(vApe || '*' || to_char(vFecha, 'dd/MM/yyyy'));
    fetch cur into vApe, vFecha;
  end loop;

  close cur;
end;


/*--------------------------------------------------------------*/
/* Actividades complementarias 2: */

declare
    cursor cur is 
        select dnombre, count(emp_no)
        from emple, depart
        where emple.dept_no = depart.dept_no
        group by dnombre;
    
    vNom depart.dnombre%type;
    vCont integer;
begin
  open cur;
  fetch cur into vNom, vCont;

  while cur%found loop
    dbms_output.put_line(vNom || ' - Cantidad de empleados: ' || to_char(vCont, '99'));
    fetch cur into vNom, vCont;
  end loop;

  close cur;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 3: */

declare
    cursor cur is 
        select apellido, salario
        from emple
        order by salario;
    
    vApe emple.apellido%type;
    vSal emple.salario%type;
    cont integer;
begin
  open cur;
  fetch cur into vApe, vSal;
  cont := 0;

  while cont < 5 loop
    dbms_output.put_line(vApe || '*' || vSal);
    fetch cur into vApe, vSal;
    cont := cont + 1;

    if cur%NOTFOUND then
        raise_application_error(-20001, 'No se encontraron más de 5 datos.');
    end if;
  end loop;

  close cur;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 4: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 5: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 6: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 7: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 8: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 9: */

/*--------------------------------------------------------------*/
/* Actividades complementarias 10: */

/*--------------------------------------------------------------*/
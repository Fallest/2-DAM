/*
Hacer CP 1, 2; AP 1, 2; AC 1, 2, 3.
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

/*--------------------------------------------------------------*/
/* Caso Práctico 4: */

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

/*--------------------------------------------------------------*/
/* Actividades propuestas 4: */

/*--------------------------------------------------------------*/
/* Actividades propuestas 5: */

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
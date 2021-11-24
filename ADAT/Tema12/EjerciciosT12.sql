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

create or replace procedure subirSalario(
  numEmpleado integer,
  incremento real
)
is 
  salarioActual real;
  salarioNulo exception;
begin
  select salario into salarioActual
  from emple 
  where emp_no = numEmpleado;

  if salarioActual is NULL then
    raise salarioNulo;    
  end if;

  update emple
  set salario = salario + incremento
  where emp_no = numEmpleado;

  exception
    when no_data_found then
      dbms_output.put_line(numEmpleado || '*Err. No encontrado');
    when salarioNulo then
      dbms_output.put_line(numEmpleado|| '*Err. Salario nulo');

end subirSalario;

/*--------------------------------------------------------------*/
/* Caso Práctico 6: */

declare
  codErr number(6);
  vnif varchar2(10);
  vnom varchar2(15);
  errBlancos exception;
  noHayEspacio exception;
  pragma exception_init(noHayEspacio, -1547);
begin
  select col1, col2 into vnif, vnom from temp2;
  if substr(vnom, 1, 1) <= ' ' then
    raise errBlancos;
  end if;
  update clientes set nombre = vnom where nif = vnif;
  
  exception
    when errBlancos then
      insert into temp2(col1) values ('ERR blancos');
    when noHayEspacio then
      insert into temp2(col1) values ('ERR tablespace');
    when no_data_found then
      insert into temp2(col1) values ('ERR no habia datos');
    when others then
      codErr := SQLCODE;
      insert into temp2(col1) values ('ERR codErr');
end;

/*--------------------------------------------------------------*/
/* Caso Práctico 7: */

create or replace procedure subirSueldo(
  numEmpleado number, incremento number
)
is
  salarioActual number;
begin
  select salario into salarioActual
  from emple
  where emp_no = numEmpleado;

  if salarioActual is NULL then
    raise_application_error(-20010, ' Salario nulo');
  else
    update emple set salario = salarioActual + incremento
    where emp_no = numEmpleado;
  end if;
end subirSueldo;

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

create or replace procedure
crearEmple(
  PempNo emple.emp_no%TYPE,
  Papellido emple.apellido%TYPE,
  Poficio emple.oficio%TYPE,
  Pdir emple.dir%TYPE,
  PfechaAlt date,
  Psalario emple.salario%TYPE,
  Pcomision emple.comision%TYPE,
  PdeptNo emple.dept_no%TYPE
)
is
  --Hay que controlar el deptNo, el dir, el empNo, el salario y others.
  noExisteDept exception;
  noExisteDir exception;
  numEmpDuplicado exception;
  codErr number(6);
  msgErr varchar2(32000);

  cursor departamentos is
  select dept_no from depart;
  cursor codigosEmp is
  select emp_no from emple;
  
  vAuxDepNo depart.dept_no%TYPE;
  vAuxEmpNo emple.emp_no%TYPE;
  existeDept number(1) default 0;
  existeDir number(1) default 0;
  existeEmple number(1) default 0;

begin

  -- Bloque para saber si existe el departamento.
  for vAuxDepNo in departamentos loop
    if (vAuxDepNo = PdeptNo) then
      existeDept:=1;
    end if;
  end loop;

  if existeDept <> 1 then
    raise noExisteDept;
  end if;
  --
  --Bloque para saber si existe el director o si el código ya existe.
  for vAuxEmpNo in codigosEmp loop
    if (vAuxEmpNo = Pdir) then
      existeDir:=1;
    end if;
    if vAuxEmpNo = PempNo then
      existeEmple:=1;
    end if;
  end loop;

  if existeDir <> 1 then
    raise noExisteDir;
  end if;

  if existeEmple = 1 then
    raise numEmpDuplicado;
  end if;  
  --
  -- Bloque para comprobar que el salario no es nulo
  if Psalario is NULL then
    raise_application_error(-20010, 'ERROR - Salario nulo');
  end if;
  --

  --Inserción del empleado
  insert into emple values(
    PempNo, Papellido, Poficio, 
    Pdir, PfechaAlt, Psalario, 
    Pcomision, PdeptNo
    );
    
  exception
    when noExisteDept then
      dbms_output.put_line('ERROR - No existe el departamento indicado');
    when noExisteDir then
      dbms_output.put_line('ERROR - No existe el director indicado');
    when numEmpDuplicado then
      dbms_output.put_line('ERROR - Ese número de empleado ya existe');
    when others then
      codErr := SQLCODE;
      msgErr := SQLERRM;
      dbms_output.put_line('ERROR ' || codErr || ': ' || msgErr);
end;

/*--------------------------------------------------------------*/
/* Actividades propuestas 7: */

create table temp1 (
  col1 varchar2(40) not null,
  constraint pk_temp1 primary key (col1)
)

create or replace procedure prueba_savepoint(
  numfilas positive
)
as
begin
  savepoint ninguna;
  insert into temp1 (col1) values ('PRIMERA FILA');
  savepoint una;
  insert into temp1 (col1) values ('SEGUNDA FILA');
  savepoint dos;
  if numfilas = 1 then
    rollback to una;
  elsif numfilas = 2 then
    rollback to dos;
  else 
    rollback to ninguna;
  end if;
  commit;

  exception
    when others then
      rollback;
end;

exec prueba_savepoint(0);
exec prueba_savepoint(1);
exec prueba_savepoint(3);
exec prueba_savepoint(2);

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

create or replace procedure ActCompl4
as
  cursor c1 is
    select apellido, oficio, salario, rowid from emple
    order by oficio, salario desc;
  vcRegEmp c1%ROWTYPE;
  oficAnt emple.oficio%TYPE;
  cont number(4) default 0;
begin
  DBMS_OUTPUT.ENABLE(1000000);
  open c1;
  fetch c1 into vcRegEmp;
  while c1%found loop
    if c1%ROWCOUNT = 1 then
      oficAnt := vcRegEmp.oficio;
      dbms_output.put_line('OFICIO: ' || oficAnt);
      while cont < 2 and c1%found and oficAnt = vcRegEmp.oficio loop
        dbms_output.put_line('Empleado: ' || vcRegEmp.apellido 
        || ' - Salario: ' || vcRegEmp.salario);
        cont := cont + 1;
        fetch c1 into vcRegEmp;
      end loop;
      cont := 0;
    end if;

    if oficAnt <> vcRegEmp.oficio then

      oficAnt := vcRegEmp.oficio;
      dbms_output.put_line('--------------------');
      dbms_output.put_line('OFICIO: ' || oficAnt);
      while cont < 2 and c1%found and oficAnt = vcRegEmp.oficio loop
        dbms_output.put_line('Empleado: ' || vcRegEmp.apellido 
        || ' - Salario: ' || vcRegEmp.salario);
        cont := cont + 1;
        fetch c1 into vcRegEmp;
      end loop;
      cont := 0;
    end if;

    fetch c1 into vcRegEmp;
  end loop;

  close c1;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 5: */

create or replace procedure crearDepart(
  nombre varchar2,
  localidad varchar2
)
as
  decMax number;
begin
  --Primero tenemos que encontrar la decena máxima
  select max(dept_no)
  into decMax
  from depart;

  --Luego insertamos una nueva fila
  insert into depart
  values (decMax+10, nombre, localidad);

  /*
   * Errores a tener en cuenta:
   * -No hay datos al buscar el número de departamento.
   */
  exception
    when no_data_found then
      raise_application_error(-20001, 'ERROR: No se encontraron datos.');
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 6: */

create or replace procedure subirSueldo(
  numDep number,
  importe number,
  porcentaje number
)
as
  cursor c1 is
    select salario, emp_no from emple
    where dept_no = numDep;
  vSalario number(10);
  vEmpno integer;
  salarioMasImporte number(10,2);
  salarioMasPorcentaje number(10,2);
begin
  /*
   * Hay que comprobar, para cada empleado, si su sueldo es mayor al
   * sumar el importe o aumentarlo en un porcentaje.
   */
  open c1;
  fetch c1 into vSalario, vEmpno;

  while c1%found loop
    salarioMasImporte := vSalario + importe;
    salarioMasPorcentaje := vSalario * (1 + porcentaje);
    if ( salarioMasImporte > salarioMasPorcentaje ) then
      update emple
      set salario = salarioMasImporte
      where emp_no = vEmpno;
    else
      update emple
      set salario = salarioMasPorcentaje
      where emp_no = vEmpno;
    end if;

    fetch c1 into vSalario, vEmpno;
  end loop;
  
  close c1;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 7: */

/*
Subirle el salario a los empleados que tengan un salario menor que la
media de salarios de su oficio.
La cantidad es la mitad de la diferencia entre el salario del empleado
y la media.
El procedimiento no puede quedarse a la mitad.
*/

declare
  cursor empleados is
  select rowid, oficio, salario from emple
  order by oficio, salario;

  media emple.salario%type;
  oficAnt emple.oficio%type;
  regEmp empleados%rowtype;
begin

  
  -- Iterar por todos los empleados ordenados por oficio
  for regEmp in empleados loop
    if empleados%rowcount = 0 or oficAnt <> regEmp.oficio then
      oficAnt := regEmp.oficio;
      -- Obtener la media del oficio actual
      select avg(salario) into media
      from emple
      where oficio = oficAnt;
    end if;

    -- Comprobar que el salario es menor que la media
    if regEmp.salario < media then
      -- Si es menor, lo incrementamos
      update emple
      set salario = salario + (media / 2)
      where rowid = regEmp.rowid;
    end if;
  end loop;

  commit;

  exception
    when others then
      rollback;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 8: */

create or replace procedure liquidacion
is
  cursor empleados is
    select emp_no, apellido, oficio, fecha_alt, salario, comision, dept_no
    from emple
    order by apellido, dept_no, oficio, salario;
  
  regEmp empleados%rowtype;
  total integer default 0;
  trienios integer default 0;
  comResp integer default 0;
  cantEmple integer default 0;
begin
  -- Iteramos por todos los empleados
  for regEmp in empleados loop
    dbms_output.put_line('**************************************');
    dbms_output.put_line('Liquidación del empleado      :' || regEmp.apellido);
    dbms_output.put_line('Departamento                  :' || regEmp.dept_no);
    dbms_output.put_line('Oficio                        :' || regEmp.oficio);
    dbms_output.put_line('Salario                       :' || regEmp.salario);
    trienios := (trunc(trunc(months_between(sysdate, regEmp.fecha_alt)) / 12) * 50);
    dbms_output.put_line('Trienios                      :' || trienios);
    -- Complemento de responsabilidad
    select count(*) into cantEmple from emple
    where dir = regEmp.emp_no;
    comResp := 100 * (cantEmple);
    dbms_output.put_line('Comp. Responsabilidad         :' || comResp);

    dbms_output.put_line('Comisión                      :' || nvl(regEmp.comision, 0));
    dbms_output.put_line('*******************************-------');
    total := regEmp.salario + trienios + comResp + nvl(regEmp.comision, 0);
    dbms_output.put_line('Total                         :' || total);
    dbms_output.put_line('**************************************');
  end loop;
end;

/*--------------------------------------------------------------*/
/* Actividades complementarias 9: */

create table t_liquidacion (
  apellido emple.apellido%type not null,
  departamento emple.dept_no%type,
  oficio emple.oficio%type,
  salario emple.salario%type,
  trienios number(5),
  comp_responsabilidad number(5),
  comision emple.comision%type,
  total number(8),
  constraint pk_t_liquidacion primary key (apellido)
)

create or replace procedure liquidacion_insert
is
  cursor empleados is
    select emp_no, apellido, oficio, fecha_alt, salario, comision, dept_no
    from emple
    order by apellido, dept_no, oficio, salario;
  
  regEmp empleados%rowtype;
  total integer default 0;
  trienios integer default 0;
  comResp integer default 0;
  cantEmple integer default 0;
begin
  -- Iteramos por todos los empleados
  for regEmp in empleados loop
    trienios := (trunc(trunc(months_between(sysdate, regEmp.fecha_alt)) / 12) * 50);
    select count(*) into cantEmple from emple 
    where dir = regEmp.emp_no;
    comResp := 100 * (cantEmple);
    total := regEmp.salario + trienios + comResp + nvl(regEmp.comision, 0);

    insert into t_liquidacion values (
      regEmp.apellido,
      regEmp.dept_no,
      regEmp.oficio,
      regEmp.salario,
      trienios,
      comResp,
      nvl(regEmp.comision, 0),
      total
    )
  end loop;
end;


/*--------------------------------------------------------------*/
/* Actividades complementarias 10: */

/*--------------------------------------------------------------*/
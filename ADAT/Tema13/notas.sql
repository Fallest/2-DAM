create or replace trigger t1
before update on emple
begin
	dbms_output.put_line('Se ha hecho un update en emple.');
end;


select trigger_name from user_triggers;

create or replace trigger t2
before update or delete on emple for each row
begin
  if updating then
	dbms_output.put_line('El empleado ' || :old.emp_no || ' va a ser modificado.');
  else
	dbms_output.put_line('El empleado ' || :old.emp_no || ' va a ser eliminado.');
  end if;
end;

update emple set salario = 1000 where emp_no = 7876;

update emple set salario = 1800 where dept_no = 10;

delete emple where emp_no = 7876;

create or replace trigger t2
before update on emple for each row
declare
	n number;
begin
  select count(*) into n
  from emple;

  dbms_output.put_line('Se va a modificar el empleado ' || :old.emp_no);
end;
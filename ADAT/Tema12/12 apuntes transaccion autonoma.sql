
/*El cambio autonomo no se hace rollback cuando sucede alguna excepcion no tratada*/

declare
	vdept depart.dept_no%type;
begin
	insert into depart(dept_no) values(50);
	ejemplo;
	select dept_no into vdept from depart
	where dept_no = 60;
end;

create or replace procedure
ejemplo as
pragma autonomous_transaction;
begin
	insert into depart(dept_no) values(70);
	commit;
end;
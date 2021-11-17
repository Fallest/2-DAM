/* Actividad Propuesta 4 (con rupturas de secuencia, con cursores con parámetros) */

create or replace procedure listarEmple
as
	cursor c1 is 
		select distinct dept_no from emple
		order by dept_no;
	
	cursor c2(pdep depart.dept_no%type) is 
		select apellido, salario from emple
		where dept_no = pdep;
	contador number;
	tot_sal number(10,2) default 0;
	sum_sal number(9,2) default 0;
	tot_emple number(4) default 0;
	cont_emple number(4) default 0;
begin	
	for v1 in c1 loop
		
		contador := 0;
		
		for v2 in c2(v1.dept_no) loop
			dbms_output.put_line(chr(9)||v2.apellido||'*'||v2.salario);
			contador := contador + 1;
			sum_sal := sum_sal + v2.salario;
		end loop;
		
		cont_emple := contador;
		tot_sal := tot_sal + sum_sal;
		tot_emple := tot_emple + cont_emple;
		
		dbms_output.put_line(chr(10)||v1.dept_no||'*'||'numero de empleados: '||contador||'*'||'suma de salarios: '||sum_sal);
		
		cont_emple := 0;
		sum_sal := 0;
		
	end loop;
	
	dbms_output.put_line(' ****** numero total de empleados: '||tot_emple||' total salarios: '||tot_sal);
	
end listar_emple;

/*---------------------------------------------------------------------------------------------------------*/
/* Actividad Complementaria 4 (con rupturas de secuencia con cursores con parámetros) */

create or replace procedure mostrarEmpleado
as
	cursor c1 is 
		select distinct oficio from emple
		order by oficio;
	cursor c2(pdep emple.oficio%type) is
		select apellido, salario from emple
		where oficio = pdep
		order by salario;
	i number;
begin
	for v1 in c1 loop
		
		dbms_output.put_line(chr(10)||v1.oficio);
		
		i := 1;
		for v2 in c2(v1.oficio) loop
			if i <= 2 then
				dbms_output.put_line(chr(9)||v2.apellido||'*'||v2.salario);
			end if;	
			i := i + 1;
		end loop;
	end loop;
end mostrarempleado;
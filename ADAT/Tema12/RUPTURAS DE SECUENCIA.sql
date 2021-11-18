-- Rupturas de secuencia con variables de acoplamiento
create or replace procedure emple1
as
	v_dept depart.dept_no%type;
	cursor c1 is select distinct dept_no from emple;
	
	cursor c2 is select apellido from emple
		where dept_no = v_dept;
begin
	for v1 in c1 loop
		dbms_output.put_line(chr(10)||v1.dept_no);
		v_dept := v1.dept_no;
		for v2 in c2 loop
			dbms_output.put_line(chr(9)||v2.apellido);
		end loop;
	end loop;
end;


-- Ruptura de secuencia con cursores con parametros
create or replace procedure emple2
as
	cont number;
	cursor c1 
		is select distinct dept_no from emple;
	cursor c2(pdep depart.dept_no%type) 
		is select apellido from emple
		where dept_no = pdep;
begin
	for v1 in c1 loop
		cont:=0;
		dbms_output.put_line(chr(10)||v1.dept_no);
		for v2 in c2(v1.dept_no) loop
			dbms_output.put_line(chr(9)||v2.apellido);
			cont := cont+1;
		end loop;
		dbms_output.put_line('Numero de empleados: '||cont);
	end loop;
end;

/*ESTOS SON LOS EJERCICIOS A HACER QUE SUPONGO QUE EN POCO DIRA QUE SE LOS MANDEMOS*/

/*ACTIVIDAD PROPUESTA 4 (RUPTURAS DE SECUENCIA)*/

CREATE OR REPLACE PROCEDURE listar_emple
AS
	CURSOR C1 IS SELECT DISTINCT DEPT_NO FROM EMPLE
						ORDER BY DEPT_NO;
	
	CURSOR C2(pdep DEPART.DEPT_NO%TYPE) IS SELECT APELLIDO, SALARIO FROM EMPLE
											WHERE DEPT_NO = pdep;
	contador NUMBER;
	tot_sal NUMBER(10,2) DEFAULT 0;
	sum_sal NUMBER(9,2) DEFAULT 0;
	tot_emple NUMBER(4) DEFAULT 0;
	cont_emple NUMBER(4) DEFAULT 0;
BEGIN	
	FOR V1 IN C1 LOOP
		
		contador := 0;
		
		FOR V2 IN C2(V1.DEPT_NO) LOOP
			DBMS_OUTPUT.PUT_LINE(CHR(9)||V2.APELLIDO||'*'||V2.SALARIO);
			contador := contador + 1;
			sum_sal := sum_sal + V2.SALARIO;
		END LOOP;
		
		cont_emple := contador;
		tot_sal := tot_sal + sum_sal;
		tot_emple := tot_emple + cont_emple;
		
		DBMS_OUTPUT.PUT_LINE(CHR(10)||V1.DEPT_NO||'*'||'NUMERO DE EMPLEADOS: '||contador||'*'||'SUMA DE SALARIOS: '||sum_sal);
		
		cont_emple := 0;
		sum_sal := 0;
		
	END LOOP;
	
	DBMS_OUTPUT.PUT_LINE(' ****** NUMERO TOTAL DE EMPLEADOS: '||tot_emple||' TOTAL SALARIOS: '||tot_sal);
	
END listar_emple;

---------------------------------------------------------------------------------------------------------

EXEC listar_emple;

---------------------------------------------------------------------------------------------------------

/*ACTIVIDAD COMPLEMENTARIA 4 (RUPTURAS DE SECUENCIA)*/

CREATE OR REPLACE PROCEDURE mostrarEmpleado
AS
	CURSOR C1 IS SELECT DISTINCT oficio FROM emple
	ORDER BY oficio;
	CURSOR C2(pdep EMPLE.OFICIO%TYPE) IS SELECT APELLIDO, SALARIO FROM EMPLE
											WHERE OFICIO = pdep
											ORDER BY SALARIO;
	i NUMBER;
BEGIN
	FOR V1 IN C1 LOOP
		
		DBMS_OUTPUT.PUT_LINE(CHR(10)||V1.OFICIO);
		
		i := 1;
		FOR V2 IN C2(V1.OFICIO) LOOP
			IF i <= 2 THEN
				DBMS_OUTPUT.PUT_LINE(CHR(9)||V2.APELLIDO||'*'||V2.SALARIO);
			END IF;	
			i := i + 1;
		END LOOP;
	END LOOP;
END mostrarEmpleado;

---------------------------------------------------------------------------------------------------------

EXEC mostrarEmpleado;

---------------------------------------------------------------------------------------------------------
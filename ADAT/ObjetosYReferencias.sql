-- Creación de un objeto
create type t_depart as
object(
	dept_no number(4),
	dnombre varchar2(15)
);

--  Tabla tipada
create table depart of t_depart;

-- Inserción de datos en la tabla
insert into depart values(10, 'CONTABILIDAD');
insert into depart values(20, 'INVESTIGACIÓN');

-- Consulta de datos
select * from depart;

-- Creación de la tabla de empleados
create type t_emple as
object(
	emp_no number(2),
	apellido varchar2(15),
	pdep ref t_depart -- Puntero de referencia
);
-- El atributo tdep apunta al objeto t_depart al 
-- que pertenece el empleado.

-- Tabla de empleados
create table emple of t_emple;

/* Como obtener una referencia a un objeto:
Debemos usar un alias de la tabla para obtener 
la referencia.
*/
select ref(d) from depart d where dept_no = 10;

-- Insert-select usando una referencia:
insert into emple
select 1, 'SANCHEZ', ref(d) 
from depart d
where dept_no = 10;

insert into emple
select 2, 'LOPEZ', ref(d) 
from depart d
where dept_no = 20;

/*
Podemos consultar los atributos de un objeto
cuya referencia está guardada en la tabla.
*/
select e.emp_no, e.apellido, e.pdep.dnombre
from emple e;

/* 
Con deref(referencia) podemos obtener el objeto
al que hace referencia.
*/
select deref(pdep) from emple;
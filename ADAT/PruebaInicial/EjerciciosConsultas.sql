
1.- Listar el código y nombre de los empleados cuyo código sea distinto de 1, 4, 6, 8 o 10.

SQL>
select cod_em, nomb_em 
from empleados
where cod_em not in (1, 4, 6, 8);

RESULTADO:  
  COD_EM   NOMB_EM
---------- ----------------------------------------
         5 ALADA VERAZ, JUANA
         7 FORZADO LÓPEZ, JUAN
         9 MANDO CORREA, ROSA
         2 MANRIQUE BACTERIO, LUISA
         3 MONFORTE CID, ROLDÁN
        10 PÉREZ MUÑOZ, ALFONSO

************************************************************************
2.- Listar el nombre de los empleados que no tienen extensión telefónica.

SQL>
select nomb_em
from empleados
where exttel_em = null;

RESULTADO:
ninguna fila seleccionada

************************************************************************
3.- Listado del código, nombre y presupuesto de los departamentos ordenado por criterio descendente de presupuesto anual.

SQL>
select cod_de, nomb_de, presup_de
from departamentos
order by presup_de desc;


RESULTADO:
COD_D NOMB_DE                                   PRESUP_DE
----- ---------------------------------------- ----------
PROZS PRODUCCIÓN ZONA SUR                       108000000
DIRGE DIRECCIÓN GENERAL                          26000000
INYDI INVESTIGACIÓN Y DISEÑO                     25000000
ADMZS ADMINISTRACIÓN ZONA SUR                    14000000
VENZS VENTAS ZONA SUR                            13500000
JEFZS JEFATURA FÁBRICA ZONA SUR                   6200000

************************************************************************
4.- Listar el nombre del empleado y el nombre y fecha de nacimiento de su hijo/a para aquellos empleados con un único hijo. Ordenar por fecha de nacimiento de los hijos.

SQL>
select nomb_em, nomb_hi, fecnac_hi
from empleados, hijos
where empleados.cod_em = hijos.padre_hi
and numhij_hi = 1
order by fecnac_hi;

RESULTADO:
NOMB_EM
----------------------------------------
NOMB_HI                                  FECNAC_H
---------------------------------------- --------
ALADA VERAZ, JUANA
PASTORA ALADA, MATEO                     06/03/82

MANDO CORREA, ROSA
LEÓN MANDO, ELVIRA                       28/02/88

RUIZ DE LOPERA, MANUEL
RUIZ DENIL, SON                          07/06/89

MONFORTE CID, ROLDÁN
MONFORTE LEMOS, JESÚS                    12/09/90

MASCULLAS ALTO, ELOISA
FUERTE MASCULLAS, ANACLETO               14/03/94

************************************************************************
5.- Listar el nombre de los departamentos y del departamento del que dependen (sólo para los departamentos dependientes).

 = SQL>
select Dep.nomb_de, DepSub.nomb_de 
from departamentos Dep, departamentos DepSub
where DepSub.deptjefe_de = Dep.cod_de;

RESULTADO:
NOMB_DE
----------------------------------------
NOMB_DE
----------------------------------------
DIRECCIÓN GENERAL
INVESTIGACIÓN Y DISEÑO

JEFATURA FÁBRICA ZONA SUR
PRODUCCIÓN ZONA SUR

ADMINISTRACIÓN ZONA SUR
VENTAS ZONA SUR

************************************************************************
6.- Listar el NIF, nombre del empleado y el nombre del dpto. al que se encuentra asignado ordenado por dpto. y dentro de cada dpto por el nombre de empleado.

SQL>
select dni_em, nomb_em, nomb_de
from departamentos, empleados
where dept_em = cod_de
order by nomb_de, nomb_de;

RESULTADO:
DNI_EM    NOMB_EM
--------- ----------------------------------------
NOMB_DE
----------------------------------------
38223822T ALADA VERAZ, JUANA
ADMINISTRACIÓN ZONA SUR

21452145V RUIZ DE LOPERA, MANUEL
DIRECCIÓN GENERAL

21232123K MANRIQUE BACTERIO, LUISA
INVESTIGACIÓN Y DISEÑO

26452645D GOZQUE ALTANERO, CARLOS
JEFATURA FÁBRICA ZONA SUR

47124712D FORZADO LÓPEZ, JUAN
PRODUCCIÓN ZONA SUR

32933293D PÉREZ MUÑOZ, ALFONSO
PRODUCCIÓN ZONA SUR

11311131D MANDO CORREA, ROSA
PRODUCCIÓN ZONA SUR

32133213H MASCULLAS ALTO, ELOISA
PRODUCCIÓN ZONA SUR

23822382D MONFORTE CID, ROLDÁN
VENTAS ZONA SUR

38293829L TOPAZ ILLÁN, CARLOS
VENTAS ZONA SUR

************************************************************************
7.- Listar el salario mínimo, máximo y medio para cada dpto. indicando el código de dpto. al que pertenece el dato.

SQL>
select dept_em, min(salario_em), max(salario_em), avg(salario_em)
from empleados
group by dept_em;

RESULTADO:
DEPT_ MIN(SALARIO_EM) MAX(SALARIO_EM) AVG(SALARIO_EM)
----- --------------- --------------- ---------------
ADMZS         6200000         6200000         6200000
DIRGE         7200000         7200000         7200000
INYDI         4500000         4500000         4500000
JEFZS         5000000         5000000         5000000
PROZS         1300000         3100000         1900000
VENZS         3200000         5200000         4200000

************************************************************************
8.- Listar el salario promedio de los empleados.

SQL>
select avg(salario_em)
from empleados;

RESULTADO:
AVG(SALARIO_EM)
---------------
        3890000

************************************************************************
9.- Listar el nombre de los hijos del empleado que se apellida 'Correa'.

SQL>select nomb_hi
from hijos
where padre_hi = (
  select cod_em
  from empleados
  where nomb_em like '%CORREA%'
);

RESULTADO:
NOMB_HI
----------------------------------------
LEÓN MANDO, ELVIRA
LEÓN MANDO, PLÁCIDO

************************************************************************
10.- Listar el nombre de los departamentos en los que la suma de los sueldos es igual o mayor al 25% del presupuesto.

SQL>
select nomb_de
from departamentos
where 0.25*presup_de <= (
  select sum(salario_em) from empleados
  where dept_em = cod_de
);

RESULTADO:
NOMB_DE
----------------------------------------
ADMINISTRACIÓN ZONA SUR
DIRECCIÓN GENERAL
JEFATURA FÁBRICA ZONA SUR
VENTAS ZONA SUR

************************************************************************
11.- Listar los departamentos que tengan algún empleado que gane más de 500.000 ptas al mes. (Recuerda que el salario es anual).

SQL>
select nomb_de
from departamentos
where 1 <= (
  select count(*)
  from empleados
  where salario_em / 12 > 500000
);

RESULTADO:
NOMB_DE
----------------------------------------
ADMINISTRACIÓN ZONA SUR
DIRECCIÓN GENERAL
INVESTIGACIÓN Y DISEÑO
JEFATURA FÁBRICA ZONA SUR
PRODUCCIÓN ZONA SUR
VENTAS ZONA SUR

************************************************************************
12.- Para cada extensión telefónica, hallar cuántos empleados la usan y el salario medio de éstos.

SQL>
select exttel_em, count(*), avg(salario_em)
from Empleados
group by exttel_em;

RESULTADO:
EXTTEL_EM   COUNT(*) AVG(SALARIO_EM)
--------- ---------- ---------------
1111               1         7200000
12124              1         3100000
1239               1         6200000
2133               1         5200000
2233               1         4500000
23838              1         5000000
3838               1         3200000
                   3         1500000

************************************************************************
13.- Hallar el salario medio por departamento para aquellos departamentos cuyo salario máximo es inferior al salario medio de todos los empleados.

SQL>
select nomb_de, avg(salario_em)
from Empleados, Departamentos
where dept_em = cod_de
group by nomb_de
having max(salario_em) < (
  select avg(E.salario_em)
  from Empleados E
);

RESULTADO:
NOMB_DE                                  AVG(SALARIO_EM)
---------------------------------------- ---------------
PRODUCCIÓN ZONA SUR                              1900000

************************************************************************
14.- Mostrar la habilidad que no tiene ningún empleado.

SQL>
select cod_ha 
from Habilidades, Habiempl
where codha_he (+) = cod_ha
minus
select cod_ha 
from Habilidades, Habiempl
where codha_he = cod_ha;

RESULTADO:
COD_H
-----
MECAN
TELEF

************************************************************************
15.- Mostrar los nombres propios de los empleados que tienen al menos dos habilidades.

SQL>
select nomb_em 
from Empleados, Habiempl
where cod_em = codem_he
group by nomb_em
having 2 <= count(*);

RESULTADO:
NOMB_EM
----------------------------------------
ALADA VERAZ, JUANA
RUIZ DE LOPERA, MANUEL

************************************************************************
16.- Realiza un informe que muestre el nombre de cada departamento, nombre y salario de cada empleado y salario medio por departamento.

SQL>
set headsep |
ttitle "ACTIVIDAD 16"

column departamento heading 'Nombre Departamento'
column empleado heading 'Nombre de Empleado'
column salario_empleado heading 'Salario Empleado'

set linesize 80
set pagesize 100
set newpage 1

break on departamento skip 1
compute avg label "-Media" of salario_empleado on departamento

select nomb_de "departamento", nomb_em "empleado", salario_em 

"salario_empleado" 
from empleados, departamentos
where dept_em = cod_de
order by nomb_de;

ttitle off;

RESULTADO:
Mié Sep 29                                                           página    2
                                  ACTIVIDAD 16

Nombre Departamento
----------------------------------------
Nombre de Empleado                       Salario Empleado
---------------------------------------- ----------------


PRODUCCIÓN ZONA SUR
FORZADO LÓPEZ, JUAN                               1600000


PÉREZ MUÑOZ, ALFONSO                              1300000


MANDO CORREA, ROSA                                3100000


MASCULLAS ALTO, ELOISA                            1600000

****************************************
                                         ----------------

-Media
                                                  1900000



VENTAS ZONA SUR
MONFORTE CID, ROLDÁN                              5200000


TOPAZ ILLÁN, CARLOS                               3200000

****************************************
                                         ----------------

-Media
                                                  4200000




10 filas seleccionadas.


Mié Sep 29                                                           página    1
                                  ACTIVIDAD 16

Nombre Departamento
----------------------------------------
Nombre de Empleado                       Salario Empleado
---------------------------------------- ----------------
ADMINISTRACIÓN ZONA SUR
ALADA VERAZ, JUANA                                6200000

****************************************
                                         ----------------

-Media
                                                  6200000


DIRECCIÓN GENERAL
RUIZ DE LOPERA, MANUEL                            7200000

****************************************
                                         ----------------

-Media
                                                  7200000


INVESTIGACIÓN Y DISEÑO
MANRIQUE BACTERIO, LUISA                          4500000

****************************************
                                         ----------------

-Media
                                                  4500000


JEFATURA FÁBRICA ZONA SUR
GOZQUE ALTANERO, CARLOS                           5000000

****************************************
                                         ----------------

-Media
                                                  5000000


PRODUCCIÓN ZONA SUR
FORZADO LÓPEZ, JUAN                               1600000


PÉREZ MUÑOZ, ALFONSO                              1300000


MANDO CORREA, ROSA                                3100000


MASCULLAS ALTO, ELOISA                            1600000

****************************************
                                         ----------------

-Media
                                                  1900000


VENTAS ZONA SUR
MONFORTE CID, ROLDÁN                              5200000


TOPAZ ILLÁN, CARLOS                               3200000

****************************************
                                         ----------------

-Media
                                                  4200000

************************************************************************
17.- Crear la tabla TEMP(CODEMP, NOMDEPT, NOMEMP, SALEMP) cuyas columnas tienen el mismo tipo y tamaño las similares existentes en la BD. Insertar en dicha tabla el código de empleado, nombre de dpto, nombre de empleado y salario de los empleados de los centros de MURCIA.

SQL>
create table Temp (codemp, nomdept, nomemp, salemp)
as select cod_em, nomb_de, nomb_em, salario_em
from Empleados, Departamentos, Centros
where dept_em = cod_de
and cod_ce = centro_de
and poblac_ce = 'MURCIA';

RESULTADO:
CODEMP NOMDEPT
---------- ----------------------------------------
NOMEMP                                       SALEMP
---------------------------------------- ----------
         2 INVESTIGACIÓN Y DISEÑO
MANRIQUE BACTERIO, LUISA                    4500000

         1 DIRECCIÓN GENERAL
RUIZ DE LOPERA, MANUEL                      7200000


************************************************************************
18.- Incrementar en un 10% los salarios de los empleados que ganen menos de 5.000.000 de ptas.

SQL>
update Empleados
set salario_em = salario_em*1.10
where salario_em < 5000000;
RESULTADO:
6 filas actualizadas.

************************************************************************
19.- Deshacer la operación anterior.

SQL>
rollback;
RESULTADO:
Rollback terminado.
************************************************************************
20.- Borrar la tabla TEMP y todas las anteriores.

SQL>
drop table Temp cascade constraints;
drop table Departamentos cascade constraints;
drop table Empleados cascade constraints;
drop table Centros cascade constraints;
drop table Habilidades cascade constraints;
drop table Habiempl cascade constraints;
drop table Hijos cascade constraints;

RESULTADO:
Tabla borrada.

Tabla borrada.

Tabla borrada.

Tabla borrada.

Tabla borrada.

Tabla borrada.

Tabla borrada.

************************************************************************

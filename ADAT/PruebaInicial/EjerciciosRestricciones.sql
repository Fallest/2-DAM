/* 1. Añade las siguientes restricciones a la tabla departamentos:
	- Todos los campos numéricos de la tabla son positivos.
	- El Tipo de Director es 'F' o 'P'.
 Si no pudieras añadir alguna de las restricciones anteriores modifica los valores de las columnas correspondientes para añadirlas.
*/

alter table Departamentos add constraint chk_nums check((director_de > 0) and (presup_de > 0));
alter table Departamentos add constraint chk_dir check(tipodir_de in ('F', 'P'));

/*---------------------------------------------------------------------*/
/* 2. Añade las siguientes restricciones a la tabla empleados:
	- Todos los nombres de los empleados deben estar en mayúsculas.
	- Todos los empleados eran mayores de edad cuando se incorporaron.
 Si no pudieras añadir alguna de las restricciones anteriores modifica los valores de las columnas correspondientes para añadirlas.
*/

alter table Empleados add constraint chk_nom_mayus check(nomb_em = upper(nomb_em));

update Empleados set fecinc_em=add_months(fecinc_em, 12) where trunc(months_between(fecnac_em, fecinc_em) / 12) < 18;
alter table Empleados add constraint chk_emp_adulto check(trunc(months_between(fecnac_em, fecinc_em) / 12) >= 18);

/*---------------------------------------------------------------------*/
/* 3. Añade la columna NIVEL_HE NUMBER(2), a la tabla HABIEMPL con las siguientes restricciones:
	
	- Por defecto vale 5.
	- Valores válidos entre 1 y 10.
*/




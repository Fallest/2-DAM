/* Ejercicio 1 */
/*Crear una función que devuelva la diferencia de precio entre el producto más caro y el más 
barato.*/
create or replace function getDiferencia()
return number
as
  diferencia number;
  precMasBarato number;
  precMasCaro number;
begin
  --Primero tenemos que saber cuál es el producto más caro y el más barato.
  select min(precio_uni), max(precio_uni) 
  into precMasBarato, precMasCaro
  from productos;

  diferencia := (precMasCaro - precMasBarato);

  return diferencia;
end;
 
/*----------------------------------------------------------*/
/* Ejercicio 2 */
/*Crear un procedimiento que muestre los datos del cliente con domicilio en  Las Rozas que 
todavía no ha realizado ninguna compra*/
create or replace procedure getClienteRozas
as
  nifCli varchar2(10);
  nomCli varchar2(15);
  domCli varchar2(15);
begin
  /*
  Primero tenemos que localizar al cliente de las rozas
  Luego tenemos que comprobar si no ha realizado ninguna compra
  Tiene que ser todo en el mismo select, puesto que asumimos que 
  existen varios clientes de las rozas.
  */
  select c0.nif, c0.nombre, c0.domicilio
  into nifCli, nomCli, domCli
  from clientes c0
  where c0.nif = (
    select c1.nif 
    from clientes c1
    where c1.domicilio = 'LAS ROZAS'
    minus
    select c2.nif
    from ventas v2, clientes c2
    where v2.nif = c2.nif
    and c2.domicilio = 'LAS ROZAS'
  );

  dbms_output.put_line(nifCli || '*' || nomCli || '*' || domCli);
end;

/*----------------------------------------------------------*/
/* Ejercicio 3 */
/*Crear un procedimiento que dado un producto (cod_producto) inserte una venta de 10 unidades 
con fecha de hoy para todos los clientes. Dar un mensaje de error apropiado en caso de que no 
exista el producto. */

create or replace procedure crearVentas(codProd number)
as
  existe number;
begin
  /*
  Primero comprobamos que existe el producto.
  Si existe, creamos una entrada en ventas por cada cliente que exista,
  con un pedido de 10 unidades de dicho producto.
  */
  select cod_producto into existe
  from productos
  where cod_producto = codProd;

  insert into ventas
  select c.nif, codProd, sysdate, 10
  from clientes c;

  exception
    when no_data_found then
      raise_application_error(-20001, 'Error: El producto indicado no existe.');
end;

/*----------------------------------------------------------*/
/* Ejercicio 4 */
/*Crear un procedimiento que muestre los datos del producto con mayor número de ventas.*/
create or replace procedure productoMasVendido
as
  codProd number;
  descProd varchar2(15);
  lineaProd varchar2(6);
  precioProd number;
  stockProd number;
begin
  select * 
  into codProd, descProd, lineaProd, precioProd, stockProd
  from productos p1
  where p1.cod_producto = (
    select p2.cod_producto
    from productos p2, ventas v2
    where p2.cod_producto = v2.cod_producto
    group by p2.cod_producto
    having count(*) = (
      select max(count(*))
      from productos p3, ventas v3
      where p3.cod_producto = v3.cod_producto
      group by p3.cod_producto
    )
  );

  dbms_output.put_line(codProd || '*' || descProd || '*' || lineaProd || '*' || precioProd || '*' || stockProd);
end;

/*----------------------------------------------------------*/
/* Ejercicio 5 */
/*Crear una función que tenga como argumento el código de un empleado y devuelva la diferencia 
entre su salario y el máximo salario de su departamento. La función devolverá -1 si no existe ese 
empleado.*/

create or replace function getDiferenciaSalario(numEmp number)
return number
as
  diferencia number;
  salarioEmple number;
  salarioMaximo number;
  existe number;
  departamento number;
begin
  --Primero tenemos que comprobar que el empleado existe.
  select emp_no 
  into existe
  from emple
  where emp_no = numEmp;

  --Luego tenemos que saber cuál es el salario del empleado.
  select salario 
  into salarioEmple
  from emple
  where emp_no = numEmp;

  --Extraemos su departamento
  select dept_no
  into departamento
  from emple
  where emp_no = numEmp;

  --Obtenemos el salario máximo de su departamento
  select max(e1.salario)
  into salarioMaximo
  from emple e1, depart d1
  where e1.dept_no = d1.dept_no
  and d1.dept_no = departamento;

  --Por último, calculamos la diferencia
  diferencia := (salarioMaximo - salarioEmple);

  return diferencia;

  exception
    when no_data_found then
      raise_application_error(-20001, 'Error: No existe ese empleado.');

end;

/*----------------------------------------------------------*/
/* Ejercicio 6 */
/*Crear un procedimiento que reciba un número n y calcule, en una variable de salida, el porcentaje 
que suponen estos n empleados respecto al total de empleados de la base de datos.*/
create or replace procedure calcularPorcentaje(
  n in number, 
  porcentaje out number
)
as
  numTotal number;
begin
  select count(*)
  into numTotal
  from emple;

  porcentaje := trunc( ((n / numTotal) * 100), 2 );
end;
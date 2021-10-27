/* Ejercicio 1 */
/*
Nos dan un código de producto.
Con ese código tenemos que comprobar que existe.
Si existe, debemos ver si hay una entrada en la tabla ventas con ese código.
Si existe una entrada en ventas, sumamos en una variable el total de unidades vendidas.

Si el producto no existe, devuelve -1.
Si el producto existe pero no tiene ventas, devuelve 0.
En otro caso devuelve la suma total de unidades vendidas.
*/

create or replace function getVentas(codProd number)
return number
as
  sumaUnidades number;
  existe integer;
begin
  begin
    -- Comprobamos que existe el producgto, si no, devolvemos -1.
    select cod_producto 
    into existe 
    from productos 
    where cod_producto = codProd;

    exception
      when no_data_found then
        return -1;
  end;
  begin
    -- Comprobamos que existen ventas del producto, si no, devolvemos 0.
    select p.cod_producto 
    into existe
    from productos p, ventas v
    where p.cod_producto = codProd
    and p.cod_producto = v.cod_producto
    group by p.cod_producto;

    exception
      when no_data_found then
        return 0;
  end;

  --Tenemos que sumar todas las unidades vendidas
  select sum(unidades), cod_producto 
  into sumaUnidades, existe
  from ventas
  where cod_producto = codProd
  group by cod_producto;

  dbms_output.put_line('Unidades vendidas del producto ' || codProd || ': ' || sumaUnidades);
  return sumaUnidades;
end;
 
/*----------------------------------------------------------*/
/* Ejercicio 2 */

create or replace procedure getVentasP(codProd in number, sumaUnidades out number)
as
  existe integer;
begin
  begin
    -- Comprobamos que existe el producto, si no, devolvemos -1.
    select cod_producto 
    into existe 
    from productos 
    where cod_producto = codProd;

    exception
      when no_data_found then
        sumaUnidades := -1;
  end;
  begin
    -- Comprobamos que existen ventas del producto, si no, devolvemos 0.
    select p.cod_producto 
    into existe
    from productos p, ventas v
    where p.cod_producto = codProd
    and p.cod_producto = v.cod_producto
    group by p.cod_producto;

    exception
      when no_data_found then
        sumaUnidades := 0;
  end;

  --Tenemos que sumar todas las unidades vendidas
  select sum(unidades), cod_producto 
  into sumaUnidades, existe
  from ventas
  where cod_producto = codProd
  group by cod_producto;
end;

/*----------------------------------------------------------*/
/* Ejercicio 3 */

create or replace procedure getVenta(nifCli varchar2, fechaVenta date)
as
  existe varchar2(10);
  nombreCli varchar2(15);
  descripProducto varchar2(15);
  unidadesVendidas number;
begin
  begin
    --Comprobar que existe el cliente
    select nif 
    into existe
    from clientes
    where nif = nifCli;

    exception
      when no_data_found then
        raise_application_error(-2000, 'Error: No existe el cliente.');
  end;
  begin
    --Comprobar que existe una venta ese día
    select count(*)
    into existe
    from ventas
    where nif = nifCli
    and fecha = fechaVenta;

    exception
      when no_data_found then
        raise_application_error(-2000, 'Error: No existen ventas ese día.');
  end;

  -- Mostrar los datos de las ventas
  --el nombre del cliente al que se le hizo la venta, la descripción del producto y las unidades vendidas
  select cli.nombre, pro.descripcion, ven.unidades
  into nombreCli, descripProducto, unidadesVendidas
  from clientes cli, productos pro, ventas ven
  where cli.nif = nifCli
  and ven.fecha = fechaVenta
  and cli.nif = ven.nif
  and ven.cod_producto = pro.cod_producto;

  dbms_output.put_line(nombreCli || '*' || descripProducto || '*' || unidadesVendidas);
end;

/*----------------------------------------------------------*/
/* Ejercicio 4 */
/*Crear una función que reciba un NIF y devuelva la cantidad de ventas que dicho cliente ha 
realizado. Si el cliente no ha hecho ninguna venta devolverá 0. Si el cliente no existe devolverá -1.*/


create or replace function getVentasCliente(nifCli varchar2)
return number
as
  sumaVentas number;
  existe varchar2(15);
begin
  begin
    -- Comprobamos que existe el cliente, si no, devolvemos -1.
    select nif 
    into existe 
    from clientes 
    where nif = nifCli;

    exception
      when no_data_found then
        return -1;
  end;
  begin
    -- Comprobamos que existen ventas a nombre del cliente, si no, devolvemos 0.
    select v.nif
    into existe
    from clientes c, ventas v
    where c.nif = nifCli
    and v.nif = c.nif
    group by v.nif;

    exception
      when no_data_found then
        return 0;
  end;

  --Tenemos que calcular la cantidad de ventas a este cliente
  select count(*), v.nif
  into sumaVentas, existe
  from ventas v
  where v.nif = nifCli
  group by v.nif;

  dbms_output.put_line('Ventas a nombre del cliente ' || existe || ': ' || sumaVentas);
  return sumaVentas;
end;


/*----------------------------------------------------------*/
/* Ejercicio 5 */
/*Crear una función que reciba un nombre de cliente y devuelva la cantidad de ventas que dicho 
cliente ha realizado. Utiliza una llamada a la función anterior. Si el cliente no existe devolverá -1.*/
create or replace function getVentasClienteNombre(nomCli varchar2)
return number
as
  sumaVentas number;
  nifCli varchar2(15);
  existe varchar2(15);
begin
  begin
    -- Comprobamos que existe el cliente, si no, devolvemos -1.
    select nombre
    into existe 
    from clientes 
    where nombre = nomCli;

    exception
      when no_data_found then
        return -1;
  end;

  --Tenemos que calcular la cantidad de ventas a este cliente
  --Primero tenemos que saber cuál es su nif
  select nif into nifCli
  from clientes
  where nombre = nomCli;

  select getVentasCliente(nifCli)
  into sumaVentas
  from dual;

  dbms_output.put_line('Ventas a nombre del cliente ' || nifCli || ' con nombre ' || nomCli || ': ' || sumaVentas);
  return sumaVentas;
end;
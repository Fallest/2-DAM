/* - - - - EJEMPLOS CON DBMS - - - - */
/* Crear tabla de manera dinamica. */
CREATE OR REPLACE PROCEDURE crear_tabla_1(
    tabla VARCHAR2,
    columna VARCHAR2,
    longitud NUMBER
)
AS
    id_cursor NUMBER;
    comando VARCHAR2(100);
BEGIN
    id_cursor := dbms_sql.open_cursor;
    comando := 'CREATE TABLE '||tabla||' ('||columna||' VARCHAR2('||longitud||'))';

    dbms_sql.parse(id_cursor, comando, dbms_sql.native);
    dbms_sql.close_cursor(id_cursor);
END;

/* Insertar fila de manera dinamica. */
CREATE OR REPLACE PROCEDURE insertar_fila_1(
    tabla VARCHAR2,
    dato VARCHAR2
)
AS
    id_cursor NUMBER;
    comando VARCHAR2(100);
    v1 NUMBER;
BEGIN
    id_cursor := dbms_sql.open_cursor;
    comando := 'INSERT INTO '||tabla||' VALUES(:val_1)';

    dbms_output.put_line(comando);
    dbms_sql.parse(id_cursor, comando, dbms_sql.native);
    dbms_sql.bind_variable(id_cursor, 'val_1', dato);

    -- dbms_sql.execute devuelve el numero de filas que ha insertado y que guardamos en v1.
    v1 := dbms_sql.execute(id_cursor);
    dbms_sql.close_cursor(id_cursor);
END;


/* - - - - EJEMPLOS CON DNS - - - - */
/* Crear tabla de manera dinamica. */
CREATE OR REPLACE PROCEDURE crear_tabla_2(
    tabla VARCHAR2,
    columna VARCHAR2,
    longitud NUMBER
)
AS
    comando VARCHAR2(100);
BEGIN
    comando := 'CREATE TABLE '||tabla||' ('||columna||' VARCHAR2('||longitud||'))';
    execute immediate comando;
END;

/* Insertar fila de manera dinamica. */
CREATE OR REPLACE PROCEDURE insertar_fila_2(
    tabla VARCHAR2,
    dato VARCHAR2
)
AS
    comando VARCHAR2(100);
BEGIN
    comando := 'INSERT INTO '||tabla||' VALUES(:val_1)';
    execute immediate comando using dato;
END;
"""
    Conectar a una DB.
    Crear una tabla.
    Insertar algunos registros.
    Mostrar por consola los registros de una tabla.
    Eliminar algunos registros y volver a mostrar por consola los restantes.
"""

def ask():
    print("Conector 1: pg8000")
    print("Conector 2: py-postgresql")
    print("Conector 3: sqlite3")

    ans = input("¿Qué conector quieres usar?: ")

    if ans == "1":
        execPg8000()
    elif ans == "2":
        execPostgresql()
    elif ans == "3":
        execSqlite3()
    else:
        ask()


def execPg8000():
    import pg8000.native
    print("Conectando a la base de datos...")
    con = pg8000.native.Connection(user="postgres", password="david", host="localhost")

    print("\nCreando una tabla temporal de juegos: \"david\"")
    con.run("CREATE TEMPORARY TABLE david (id SERIAL, title TEXT, genre TEXT, published DATE, price INTEGER)")

    print("\nLlenando la tabla con datos: ")
    con.run(
        "INSERT INTO david (title, genre, published, price) VALUES ('League of legends', 'MOBA, Strategy', '2009-10-27', 0)")
    con.run(
        "INSERT INTO david (title, genre, published, price) VALUES ('Minecraft', 'Sandbox, Survival', '2011-11-18', 24)")
    con.run(
        "INSERT INTO david (title, genre, published, price) VALUES ('Dying Light 2', 'Action, Role-playing, Survival Horror', '2022-02-04', 59.99)")

    print("\nDatos en la tabla actualmente: ")
    for row in con.run("SELECT * FROM david"):
        print(row)

    print("\nEliminando registros: ")
    con.run("DELETE FROM david WHERE title = :title", title='Minecraft')

    print("\nRegistros restantes: ")
    for row in con.run("SELECT * FROM david"):
        print(row)

    con.run("DROP TABLE david")

def execPostgresql():
    import postgresql

    print("Conectando a la base de datos...")
    db = postgresql.open(user='postgres', password='david', host='localhost', port=5432)
    print("\nCreando una tabla de juegos: \"david\"")
    db.execute("CREATE TEMPORARY TABLE david (id SERIAL, title TEXT, genre TEXT, published DATE, price INTEGER)")

    print("\nLlenando la tabla con datos: ")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('League of legends', 'MOBA, Strategy', '2009-10-27', 0)")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('Minecraft', 'Sandbox, Survival', '2011-11-18', 24)")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('Dying Light 2', 'Action, Role-playing, Survival Horror', '2022-02-04', 59.99)")

    print("\nDatos en la tabla actualmente: ")
    for row in db.query("SELECT * FROM david"):
        print(row)

    print("\nEliminando registros: ")
    deleteGame = db.prepare("DELETE FROM david WHERE title = $1")
    deleteGame("Minecraft")

    print("\nRegistros restantes: ")
    for row in db.query("SELECT * FROM david"):
        print(row)

def execSqlite3():
    import sqlite3

    print("Conectando a la base de datos...")
    con = sqlite3.connect('dbase.db')
    db = con.cursor()

    print("\nCreando una tabla de juegos: \"david\"")
    db.execute("CREATE TEMPORARY TABLE david (id SERIAL, title TEXT, genre TEXT, published DATE, price INTEGER)")

    print("\nLlenando la tabla con datos: ")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('League of legends', 'MOBA, Strategy', '2009-10-27', 0)")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('Minecraft', 'Sandbox, Survival', '2011-11-18', 24)")
    db.execute(
        "INSERT INTO david (title, genre, published, price) VALUES ('Dying Light 2', 'Action, Role-playing, Survival Horror', '2022-02-04', 59.99)")

    print("\nDatos en la tabla actualmente: ")
    for row in db.execute("SELECT * FROM david"):
        print(row)

    print("\nEliminando registros: ")
    db.execute("DELETE FROM david WHERE title = 'Minecraft'")

    print("\nRegistros restantes: ")
    for row in db.execute("SELECT * FROM david"):
        print(row)

    con.close()
#-------------------------
ask()
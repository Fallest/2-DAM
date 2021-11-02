"""
Programa que lee registros de un archivo csv, y permite al usuario listar
los registros según el orden original, o según la columna que elija el
usuario.
La estructura del archivo se compondrá al menos de las siguientes
columnas: Nombre, Sexo, Edad, Salario, Profesión.
SUGERENCIA: revisar las funciones de python split(), join(), y
especialmente sort() y sorted().
"""
import csv


def Main():
    Mostrar(Leer(Ruta()), Orden())

def Mostrar(datos, orden):
    if orden == 1:
        print(datos.sort("Nombre"))
    elif orden == 2:
        print(datos.sort("Sexo"))
    elif orden == 3:
        print(datos.sort("Edad"))
    elif orden == 4:
        print(datos.sort("Salario"))
    elif orden == 5:
        print(datos.sort("Profesion"))
    elif orden == 6:
        print(datos.sort())

def Leer(ruta):
    """
    Lee los datos de un archivo .csv.
    Devuelve una lista de tuplas con los datos.
    """
    lista = []

    with open(ruta, encoding="utf8") as f:
        lector = csv.reader(f, delimiter=";")
        for nombre, sexo, edad, salario, profesion in lector:
            lista.append((nombre, sexo, edad, salario, profesion))

    return lista

def Ruta():
    return input("Introduzca la ruta del archivo: ")

def Orden():
    # Mostramos un pequeño menú
    print("\t1 - Nombre")
    print("\t2 - Sexo")
    print("\t3 - Edad")
    print("\t4 - Salario")
    print("\t5 - Profesión")
    print("\t6 - Orden original")

    while (selec := int(input("Por qué columna quieres ordenar?"))) not in range(1, 7):
        continue

    return selec

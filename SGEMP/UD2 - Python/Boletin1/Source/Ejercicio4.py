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


def main():
    mostrar(leer(ruta()), orden())

def mostrar(datos, orden):
    if orden == 1:
        datos.sort(key=(lambda item: item[0]))
    elif orden == 2:
        datos.sort(key=(lambda item: item[1]))
    elif orden == 3:
        datos.sort(key=(lambda item: item[2]))
    elif orden == 4:
        datos.sort(key=(lambda item: item[3]))
    elif orden == 5:
        datos.sort(key=(lambda item: item[4]))
    elif orden == 6:
        datos.sort

    for item in datos:
        print(item[0], " - ", item[1], " - ", item[2], " - ", item[3], " - ", item[4])

def leer(ruta):
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

def ruta():
    return input("Introduzca la ruta del archivo: ")

def orden():
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

main()
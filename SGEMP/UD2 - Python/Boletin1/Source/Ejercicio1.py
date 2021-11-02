"""Programa que tome por teclado una cadena y dos caracteres distintos.
Posteriormente reemplazará todas las ocurrencias en la cadena del 1er
carácter, por las del 2º."""

def Ejercicio1():
    cadena = input("Introduzca la cadena principal: ")
    caracterA = pedirCaracter("A")
    caracterB = pedirCaracter("B")

    print("\n\nCadena original: ", cadena)
    cadena = cadena.replace(caracterA, caracterB)
    print("\n\nCadena cambiada: ", cadena)

def pedirCaracter(tipo):
    while True:
        if tipo == "A":
            cadena = input("Introduce el carácter A: ")
            if len(cadena) == 1:
                return cadena
            else:
                print("Error: No es un sólo carácter\n")

        elif tipo == "B":
            cadena = input("Introduce el carácter B: ")
            if len(cadena) == 1:
                return cadena
            else:
                print("Error: No es un sólo carácter\n")

        else: continue

Ejercicio1()
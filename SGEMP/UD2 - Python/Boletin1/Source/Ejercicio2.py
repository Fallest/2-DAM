"""Programa que toma 10 números por teclado, realiza su media aritmética,
y almacena los números y la media en un archivo de texto"""

def Ejercicio2():
    numeros = PedirNumeros()


def PedirNumeros():
    numeros = []
    cont = 0
    while cont < 10:
        numeros[cont] = int(input(cont, "    Introduce un número: "))

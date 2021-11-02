"""
Programa que cuenta las ocurrencias de cada carácter de una cadena de
texto, que puede ser introducida desde el teclado o cargada desde un
archivo de texto.
Ejemplo: “Programando en python”
a->2 d->1 e->1 g->1 ...o→3...
SUGERENCIA: revisar las funciones lower(), capitalize(), strip(), lstrip(),
rstrip().
"""
import collections

def Main():
    if Preguntar():
        Mostrar(ContarOcurrencias(LeerArchivo()))
    else:
        Mostrar(ContarOcurrencias(LeerCadena()))

def Preguntar():
    while (res := input("Quiere leer la cadena desde un archivo (s/n)?: ")) not in ["s", "n"]:
        continue

    return res == "s"

def ContarOcurrencias(cadena):
    """
    Va a contar las ocurrencias de cada letra en una cadena.
    Va a devolver un diccionario, cuyas keys van a ser letras
    y sus values las veces que aparecen.
    """
    return collections.Counter(cadena.lower())

def LeerCadena():
    return input("Introduzca una cadena: ")

def LeerArchivo():
    archivo = input("Introduzca la ruta del archivo: ")
    res = ""

    try:
        with open(archivo, "r", encoding="utf8") as f:
            for line in f:
                res += line
    except FileNotFoundError:
        print("No existe dicho archivo.")
    except EOFError:
        print("Datos extraidos.")

    return res

def Mostrar(diccionario):
    print("\t\tConteo de caracteres: ")
    for pair in diccionario.items():
        print(pair[0], ' -> ', pair[1])

Main()
"""
Testeo de la calculadora.
"""

from pywinauto import Desktop, Application
from time import sleep

def runCalc():
    app = Application(backend="uia").start('calc.exe')
    dlg = Desktop(backend="uia").Calculator
    dlg.print_control_identifiers()

    log = open('log.txt', 'w', encoding='utf-8')

    # Comprobación de la suma
    suma = "Sumando 23 + 39 => Resultado esperado: 62 | Resultado obtenido: "
    dlg.type_keys('23{+}39=')
    suma += dlg.Static3.texts()[0].split(' ')[3] + "\n"
    print(suma)

    # Comprobación de la resta
    resta = "Restando 23 - 39 => Resultado esperado: -16 | Resultado obtenido: "
    dlg.type_keys('23{-}39=')
    resta += dlg.Static3.texts()[0].split(' ')[3] + "\n"
    print(resta)

    # Comprobación de la multiplicación
    mult = "Multiplicando 23 * 39 => Resultado esperado: 897 | Resultado obtenido: "
    dlg.type_keys('23{*}39=')
    mult += dlg.Static3.texts()[0].split(' ')[3] + "\n"
    print(mult)

    # Comprobación de la división
    div = "dividiendo 23 / 39 => Resultado esperado: 0,589743... | Resultado obtenido: "
    dlg.type_keys('23{/}39=')
    div += dlg.Static3.texts()[0].split(' ')[3] + "\n"
    print(div)

    log.writelines([suma, resta, mult, div])

    log.close()

runCalc()
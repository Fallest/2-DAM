"""
Testeo de la calculadora.
"""

from pywinauto import Desktop, Application
from time import sleep

def runCalc():
    app = Application(backend="uia").start('calc.exe')
    dlg = Desktop(backend="uia").Calculator

    log = open('log.txt', 'w', encoding='utf-8')

    # Comprobación de la suma
    suma = "Sumando 23 + 39 => Resultado esperado: 62 | Resultado obtenido: "
    dlg.type_keys('23{+}39=')
    resSuma = int(dlg.Static3.texts()[0].split(' ')[3])
    suma += str(resSuma) + " => CORRECTO\n" if 62 == resSuma else " => INCORRECTO\n"
    print(suma)
    sleep(1)

    # Comprobación de la resta
    resta = "Restando 23 - 39 => Resultado esperado: -16 | Resultado obtenido: "
    dlg.type_keys('23{-}39=')
    resResta = int(dlg.Static3.texts()[0].split(' ')[3])
    resta += str(resResta) + " => CORRECTO\n" if -16 == resResta else " => INCORRECTO\n"
    print(resta)
    sleep(1)

    # Comprobación de la multiplicación
    mult = "Multiplicando 23 * 39 => Resultado esperado: 897 | Resultado obtenido: "
    dlg.type_keys('23{*}39=')
    resMult = int(dlg.Static3.texts()[0].split(' ')[3])
    mult += str(resMult) + " => CORRECTO\n" if 897 == resMult else " => INCORRECTO\n"
    print(mult)
    sleep(1)

    # Comprobación de la división
    div = "Dividiendo 23 / 39 => Resultado esperado: 0,589743 | Resultado obtenido: "
    dlg.type_keys('23{/}39=')
    resDiv = float(dlg.Static3.texts()[0].split(' ')[3].replace(',', '.')[0:8])
    div += str(resDiv) + " => CORRECTO\n" if 0.589743 == resDiv else " => INCORRECTO\n"
    print(div)
    sleep(1)

    # Comprobación de la división por 0
    div0 = "Dividiendo 23 / 0 => Resultado esperado: No se puede dividir entre cero | Resultado obtenido: "
    dlg.type_keys('23{/}0=')
    resDiv0 = ' '.join(dlg.Static3.texts()[0].split(' ')[3:])
    div0 += str(resDiv0) +\
            " => CORRECTO\n" if "No se puede dividir entre cero" == resDiv0 else " => INCORRECTO\n"
    print(div0)
    sleep(1)

    log.writelines([suma, resta, mult, div, div0])

    log.close()

runCalc()
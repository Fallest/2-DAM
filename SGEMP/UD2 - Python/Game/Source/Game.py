"""
Archivo con el bucle principal de ejecución.
Desde aquí se llama a las funciones del título principal, la pausa, y la pantalla de muerte.

"""

import pygame, System
import MainTitle, PauseScreen, DeathScreen

def run():
    """
    Bucle principal de ejecución.

    Condición de salida:
    Se sale del bucle cuando se cambia el estado de System.running a False.
    El valor de esta variable se cambiará al pulsar el botón X de la ventana,
    o al pulsar el botón "Salir" en cualquiera de las pantallas principal, pausa, o muerte.

    Alternancia entre pantallas:
    Al iniciar se ejecuta MainTitle.run(). En MainTitle pueden ocurrir dos cosas:
        -Salir: Se llama a exit()
        -Jugar: Se ejecuta Game.start(dificultad, new)

    En Game.start(d, n) pueden ocurrir dos cosas:
        -Se presiona ESC: Se llama a PauseScreen.run().
        -Se termina la partida cuando la HP llega a 0: Se llama a DeathScreen.run().

    En PauseScreen.run() pueden ocurrir 3 cosas:
        -Menú Principal: Se llama a Game.endGame().
        -Continuar: Se llama a Game.continue().
        -Salir: Se llama a Game.exit().

    En DeathScreen.run() pueden ocurrir 3 cosas:
        -Menú principal: Se llama a Game.endGame().
        -Volver a empezar: Se llama a Game.newGame().
        -Salir: Se llama a Game.exit().



    :return:
    """
    while System.running:
        pass

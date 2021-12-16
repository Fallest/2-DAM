"""
Archivo con el bucle principal de ejecución.
Desde aquí se llama a las funciones del título principal, la pausa, y la pantalla de muerte.

"""
from turtledemo.minimal_hanoi import play

import pygame, Config
import MainTitle, PauseScreen, DeathScreen


def run():
    """
    Bucle principal de ejecución.

    Condición de salida:
    Se sale del bucle cuando se cambia el estado de Config.running a False.
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
    # Iniciamos el juego
    pygame.init()
    # Creamos la ventana
    Config.screen = pygame.display.set_mode((Config.width, Config.height), pygame.RESIZABLE)
    # Título e icono
    pygame.display.set_caption("D0DCH!")
    #pygame.display.set_icon(pygame.image.load(Config.gameIcon))

    while Config.running:
        playState = MainTitle.run()
        if playState == 1:
            # Se ha seleccionado jugar
            playState = start(Config.gameDifficulty)
            if playState == 0:
                # Si al jugar se ha seleccionado Pausa > Salir o Perder > Salir
                break;
        elif playState == 0:
            # Se ha seleccionado salir
            break;
        # En cualquier otro caso se vuelve a ejecutar el bucle

def start(dif):
    """
    Bucle para el juego.
    Se para cuando se pulsa ESC o se muere.

    Si se pulsa ESC, y se selecciona "Salir", esta función devuelve 0.
    Si se muere y se selecciona "Salir", esta función devuelve 0.
    :return:
    """
    Config.gameRunning = True

    while Config.gameRunning:
        pass


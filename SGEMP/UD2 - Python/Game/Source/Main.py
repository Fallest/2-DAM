"""
Ejecución principal del programa.
"""

"""
    TO-DO
    Afinar movimiento del jugador,
    Audio (música de fondo y efectos),
    Rotación del sprite del proyectil, 
    Afinar las dificultades,
    Mejorar rendimiento del juego.
"""

import Game

def start():
    print("Ejecutando...")
    # El bucle de ejecución de encuentra en Game
    Game.run()
    print("Finalizando la ejecución...")


#--------------------------------------------------------------
if __name__ == '__main__':
    start()
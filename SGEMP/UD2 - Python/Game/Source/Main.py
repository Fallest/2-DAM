"""
Ejecución principal del programa.
"""

"""
    TO-DO
    Audio (música de fondo y efectos),
    Afinar las dificultades,
    Tabla de puntuaciones,
    Pantalla de créditos.
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
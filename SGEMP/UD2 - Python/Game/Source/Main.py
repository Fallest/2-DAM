"""
Ejecución principal del programa.
"""

import Game, Config

def start():
    print("Ejecutando...")
    # El bucle de ejecución de encuentra en Game
    Game.run()
    print("Finalizando la ejecución...")


#--------------------------------------------------------------
if __name__ == '__main__':
    start()
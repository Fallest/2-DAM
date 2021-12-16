"""
Archivo con el bucle principal de ejecución.
Desde aquí se llama a las funciones del título principal, la pausa, y la pantalla de muerte.

"""
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
    """
    # Iniciamos el juego
    pygame.init()
    # Creamos la ventana
    Config.screen = pygame.display.set_mode((Config.width, Config.height), pygame.FULLSCREEN)
    # Título e icono
    pygame.display.set_caption("D0DCH!")
    #pygame.display.set_icon(pygame.image.load(Config.gameIcon))

    while Config.running:
        playState = MainTitle.run()
        if playState == 1:
            # Se ha seleccionado jugar.
            playState = start(Config.gameDifficulty)

            # Si al jugar se ha seleccionado Pausa > Salir o Perder > Salir.
            if playState == 0:
                break

            # Si al jugar se ha seleccionado "Menú principal".
            if playState == 1:
                continue

        # Si se ha seleccionado salir.
        elif playState == 0:
            break

        # En cualquier otro caso se vuelve a ejecutar el bucle
        else:
            continue

def start(dif):
    """
    Bucle para el juego.
    Se para cuando se pulsa ESC o se muere.

    Si se pulsa ESC, y se selecciona "Salir", esta función devuelve 0.
    Si se muere y se selecciona "Salir", esta función devuelve 0.
    """
    Config.gameRunning = True
    # Asignación de la velocidad de los proyectiles
    projectileSpeed = assignProjectileSpeed()

    while Config.gameRunning:

        # Control de eventos
        for event in pygame.event.get():
            # Si se ha salido con el botón X de la ventana.
            if event.type == pygame.QUIT:
                Config.running = False
                break

            # Si se ha presionado una tecla.
            if event.type == pygame.KEYDOWN:
                """
                Si se ha presionado ESC se va a la pantalla de pausa.
                """
                if event.key == pygame.K_ESCAPE:
                    # Nos vamos a la pantalla de pausa.
                    keepRunning = PauseScreen.run()
                    # Si en la pantalla de pausa se ha seleccionado "Salir", devuelve 0.
                    if keepRunning == 0:
                        return 0
                    # Si en la pantalla de pausa se ha seleccionado "Menú principal", devuelve 1.
                    # El 1 no tiene ningún significado, es para salir de la función.
                    if keepRunning == 1:
                        return 1
                    # En cualquier otro caso, el juego sigue ejecutando
                    else:
                        continue

                """
                CONTROL DE MOVIMIENTO DEL JUGADOR
                """


def assignProjectileSpeed():
    # Velocidad base
    speed = 20
    # Modificadores según la dificultad
    if Config.gameDifficulty == 0:
        return speed * 1
    if Config.gameDifficulty == 1:
        return speed * 3
    if Config.gameDifficulty == 2:
        return speed * 9
    if Config.gameDifficulty == 3:
        return speed * 27
    if Config.gameDifficulty == 4:
        return speed * 81
"""
Archivo con el bucle principal de ejecución.
Desde aquí se llama a las funciones del título principal, la pausa, y la pantalla de muerte.

"""
import random
import time

import pygame, Config
import MainTitle, PauseScreen, DeathScreen
from random import randrange


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
    print("Iniciando Game.run()...")
    # Iniciamos el juego
    pygame.init()
    # Creamos la ventana
    Config.screen = pygame.display.set_mode((Config.width, Config.height), pygame.FULLSCREEN, 32)
    # Título e icono
    pygame.display.set_caption("D0DCH!")
    #pygame.display.set_icon(pygame.image.load(Config.gameIcon))

    # Variable para controlar cuándo se ha seleccionado volver a jugar desde la pantalla de muerte
    playAgain = False

    while Config.running:
        if not playAgain:
            playState = MainTitle.run()
        if playState >= 1:
            # Se ha seleccionado jugar.
            playState = start(Config.gameDifficulty)

            # Si al jugar se ha seleccionado Pausa > Salir o Perder > Salir.
            if playState == 0:
                break

            # Si al jugar se ha seleccionado "Menú principal".
            if playState == 1:
                playAgain = False
                continue

            # Si al jugar y morir se ha seleccionado "Volver a jugar"
            if playState == 2:
                playAgain = True

        # Si se ha seleccionado salir.
        elif playState == 0:
            break

        # En cualquier otro caso se vuelve a ejecutar el bucle
        else:
            playAgain = False
            continue


def start(dif):
    """
    Bucle para el juego.
    Se para cuando se pulsa ESC o se muere.

    Si se pulsa ESC, y se selecciona "Salir", esta función devuelve 0.
    Si se muere y se selecciona "Salir", esta función devuelve 0.
    """
    print("Iniciando Game.start()...")

    # Asignación de la velocidad de los proyectiles y del jugador
    playerSpeed = assignPlayerSpeed()
    projectileSpeed = assignProjectileSpeed()
    # Coloreamos la pantalla de negro
    Config.screen.fill(Config.black)
    # Creamos nuestro objeto Player
    player = Config.Player()
    # Variables para la colisón y la vida perdida por colisión
    collision = False
    hitHP = assignHitHP()
    # Variable para controlar la existencia de un proyectil
    p = None

    Config.gameRunning = True
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
                CONTROL DE CAMBIO DEL ESTADO DEL JUEGO
                """
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
                        Config.screen.fill(Config.black)
                        continue

                """
                Si se presiona Q el jugador muere.
                """
                if event.key == pygame.K_q:
                    player.die()

                """
                CONTROL DE MOVIMIENTO DEL JUGADOR
                """
                # Mover el jugador hacia arriba
                if event.key == pygame.K_UP:
                    player.moveUp(playerSpeed)
                # Mover el jugador hacia abajo
                if event.key == pygame.K_DOWN:
                    player.moveDown(playerSpeed)
                # Mover el jugador hacia la izquierda
                if event.key == pygame.K_LEFT:
                    player.moveLeft(playerSpeed)
                # Mover el jugador hacia la derecha
                if event.key == pygame.K_RIGHT:
                    player.moveRight(playerSpeed)

            # Al levantar las teclas dejamos de mover al jugador en esa dirección
            if event.type == pygame.KEYUP:
                if event.key == pygame.K_UP:
                    player.moveUp(-playerSpeed)
                if event.key == pygame.K_DOWN:
                    player.moveDown(-playerSpeed)
                if event.key == pygame.K_LEFT:
                    player.moveLeft(-playerSpeed)
                if event.key == pygame.K_RIGHT:
                    player.moveRight(-playerSpeed)

        # Actualizamos la posición del jugador
        player.updatePos()
        # Si el jugador está parado, regenera energía
        if player.isStopped():
            player.updateSP(1)
        """
        Fin del control de movimiento del jugador
        """

        # Final del control de eventos

        """
        GENERACIÓN DE PROYECTILES
        """
        if p is None:
            p = Config.generateProjectile(projectileSpeed)
        if p is not None:
            p = p.updatePos()

            if p is not None:
                # Comprobamos si el jugador colisiona con el proyectil
                collision = p.checkCollision(player.getRect())

                if collision:
                    # Si ha habido una colisión eliminamos el proyectil
                    p = None

        """
        CONSECUENCIAS DE COLISIÓN
        """
        # Si el jugador ha sido golpeado.
        if collision:
            player.updateHP(-hitHP)
        # Si el jugador ha muerto, escogerá si vuelve al Menú principal (1), vuelve a jugar (2), o sale (0).
        if not player.isAlive():
            return DeathScreen.run()

        time.sleep(0.5)
        pygame.display.update()

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

def assignPlayerSpeed():
    # Velocidad base
    speed = 50
    # Modificadores según la dificultad
    if Config.gameDifficulty == 0:
        return speed
    if Config.gameDifficulty == 1:
        return speed // 2
    if Config.gameDifficulty == 2:
        return speed // 3
    if Config.gameDifficulty == 3:
        return speed // 5
    if Config.gameDifficulty == 4:
        return speed // 10

def assignHitHP():
    # Modificadores según la dificultad
    if Config.gameDifficulty == 0:
        return 100 // 1
    if Config.gameDifficulty == 1:
        return 100 // 10
    if Config.gameDifficulty == 2:
        return 100 // 5
    if Config.gameDifficulty == 3:
        return 100 // 3
    if Config.gameDifficulty == 4:
        return 100 // 99
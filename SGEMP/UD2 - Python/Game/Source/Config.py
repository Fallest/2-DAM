import random
from typing import Tuple

import pygame
from screeninfo import get_monitors

"""
Archivo para las variables y funciones globales.
"""

"""     Control del funcionamiento     """
# Variable para controlar el funcionamiento del programa.
running = True
# Variable para controlar el funcionamiento del audio.
audio = False
# Variable para controlar si el juego está en funcionamiento.
gameRunning = False

"""     Dificultad del juego     """
# Nivel de dificultad del juego
gameDifficulty = 2 # 0 - Baby Mode, 1 - Fácil, 2 - Normal, 3 - Difícil, 4 - Bullet Hell
# Contadores para las dificultades especiales
babyModeCounter = 0
bulletHellCounter = 0

"""     Rutas de archivos     """
# Localización del array con las puntuaciones.
scoresFile = "../Data/scores.txt"
# Localización de la imagen de fondo del MainTitle.
bgMainTitle = pygame.image.load("../Data/Images/main_bg.jpg")
# Localización de la imagen del icono de la ventana
#gameIcon = pygame.image.load("../Data/Images/icon.png")
# Localización de la imagen de fondo de PauseScreen
bgPauseScreen = pygame.image.load("../Data/Images/pause_bg.png")

"""     Ventana     """
# Tamaño de la ventana
width, height = get_monitors()[0].width, get_monitors()[0].height
# Ventana
screen = pygame.display
# Colores
white = (255, 255, 255)
black = (0, 0, 0)
fadedWhite = (255, 255, 255, 100)


def loadScores():
    """
    Carga los datos guardados en el archivo de puntuación
    :return: Lista de tuplas con los datos de cada puntuación (nombre del jugador, dificultad, y puntuación).
    """
    scores = []

    with open(scoresFile, 'r', encoding='utf-8') as s:
        for line in s:
            tupla = (line.split(';')[0], line.split(';')[1], line.split(';')[2])
            scores.append(tupla)

    return scores


def decreaseDif():
    """
    Decrementa la dificultad.
    Si se llama 5 veces consecutivas a esta función cuando la dificultad ya está en 1,
    se pone la dificultad Baby Mode :) .
    """
    global gameDifficulty
    global babyModeCounter

    # Si la dificultad no es fácil (1)
    if gameDifficulty > 1:
        # Decrementamos la dificultad
        gameDifficulty -= 1
        # Reseteamos el contador de babyMode
        babyModeCounter = 0

    # Si la dificultad ya está en fácil
    elif gameDifficulty == 1:
        # Se incrementa el babyModeCounter
        babyModeCounter += 1
        # Si el contador ya está en 5
        if babyModeCounter == 5:
            # Se actualiza la dificultad a babyMode
            gameDifficulty = 0



def increaseDif():
    """
    Incrementa la dificultad.
    Si se llama 5 veces consecutivas a esta función cuando la dificultad ya está en 3,
    se pone la dificultad Bullet Hell :D.
    """
    global gameDifficulty
    global bulletHellCounter

    # Si la dificultad no es difícil (3)
    if gameDifficulty < 3:
        # Incrementamos la dificultad
        gameDifficulty += 1
        # Reseteamos el contador de bulletHell
        bulletHellCounter = 0

    # Si la dificultad ya está en difícil
    elif gameDifficulty == 3:
        # Se incrementa el bulletHellCounter
        bulletHellCounter += 1
        # Si el contador ya está en 5
        if bulletHellCounter == 5:
            # Se actualiza la dificultad a bullet hell
            gameDifficulty = 4

class Player:
    """
    Clase para el objeto Jugador.
    Tendrá vida, energía, una posición y una velocidad.
    Al inicio, el jugador aparecerá en el centro de la pantalla, y tendrá velocidad 0.
    """
    health: int
    stamina: int
    pos: list[int, int]
    speed: list[int, int]
    alive: bool

    # La hitbox del jugador es un área de 1/20 de la longitud de la pantalla
    playerHitBox = pygame.Surface(((1/20)*width, (1/20)*width))

    def __init__(self):
        self.health = 100
        self.stamina = 100
        self.pos = [width // 2, height // 2]
        self.speed = [0, 0]
        self.alive = True

    def isAlive(self):
        return self.alive

    def die(self):
        self.alive = False

    def updateHP(self, q):
        if self.alive:
            self.health += q
            if self.health <= 0:
                self.stop()
                self.alive = False
            if self.health > 100:
                self.health = 100

    def updateSP(self, q):
        if self.alive:
            self.stamina += q
            if self.stamina < 0:
                self.stamina = 0
            if self.stamina > 100:
                self.stamina = 100
            print("SP: ", self.stamina)

    def moveUp(self, v):
        if self.alive and self.stamina > 0:
            self.speed[1] += v
    def moveDown(self, v):
        if self.alive and self.stamina > 0:
            self.speed[1] -= v
    def moveLeft(self, v):
        if self.alive and self.stamina > 0:
            self.speed[0] -= v * (width // height) # Aplicamos el ratio de la pantalla
    def moveRight(self, v):
        if self.alive and self.stamina > 0:
            self.speed[0] += v * (width // height) # Aplicamos el ratio de la pantalla

    def stop(self):
        self.speed = [0, 0]

    def isStopped(self):
        return self.speed == [0, 0]

    def updatePos(self):
        if self.alive:
            newXpos = self.pos[0] + self.speed[0]
            newYpos = self.pos[1] + self.speed[1]
            # Aplicamos una barrera horizontal y vertical para el movimiento del jugador
            if newXpos > width - ((1/20) * width):
                newXpos = width - ((1/20) * width)
            elif newXpos < ((1/20) * width):
                newXpos = ((1/20) * width)
            if newYpos > height - ((1/20) * height):
                newYpos = height - ((1/20) * height)
            elif newYpos < ((1/20) * height):
                newYpos = ((1 / 20) * height)

            if not self.isStopped():
                self.updateSP(-1)
            self.pos = [newXpos, newYpos]
            print(self.pos)
    def getPos(self):
        return self.pos

class Projectile:
    """
    Clase para el objeto Proyectil.
    Tendrá una posición y una velocidad.

    Localización de las "puertas" o zonas de spawn:
            1
        8       2
    7              3
        6       4
            5

    Las direcciónes serán:
        1- 45º desde la normal de la puerta con el borde de la zona jugable.
        2- normal de la puerta con el borde de la zona jugable.
        3- -45º desde la normal de la puerta con el borde de la zona jugable.

    Los proyectiles aparecen desde una de las 8 zonas de spawn que tienen. Además, cada zona tiene 3 direcciones.
    Al crear el proyectil, se le asignará de forma aleatoria una zona y una dirección.
    """
    pos: list[int, int]
    speed: list[int, int]
    gate: int
    dir: int

    # La hitbox del proyectil es un área de 1/30 de la longitud de la pantalla
    projectileHitBox = pygame.Surface(((1 / 30) * width, (1 / 30) * width))

    def __init__(self, projSpeed):
        self.gate = random.randint(1, 8)
        self.dir = random.randint(1, 3)
        self.speed = self.assignSpeed(projSpeed)

    # sqrt(x**2 + y**2) = projSpeed
    def assignSpeed(self, projSpeed):
        # Se asume que la velocidad dada es siempre positiva
        # Puerta 1
        if self.gate == 1:
            if self.dir == 1:
                return [(projSpeed // 2), -(projSpeed // 2)]
            if self.dir == 2:
                return [0, -projSpeed]
            if self.dir == 3:
                return [-(projSpeed // 2), -(projSpeed // 2)]
        # Puerta 2
        elif self.gate == 2:
            if self.dir == 1:
                return [0, -projSpeed]
            if self.dir == 2:
                return [-(projSpeed // 2), -(projSpeed // 2)]
            if self.dir == 3:
                return [-projSpeed, 0]
        # Puerta 3
        elif self.gate == 1:
            if self.dir == 1:
                return [-(projSpeed // 2), -(projSpeed // 2)]
            if self.dir == 2:
                return [-projSpeed, 0]
            if self.dir == 3:
                return [-(projSpeed // 2), (projSpeed // 2)]
        # Puerta 4
        elif self.gate == 1:
            if self.dir == 1:
                return [-projSpeed, 0]
            if self.dir == 2:
                return [-(projSpeed // 2), (projSpeed // 2)]
            if self.dir == 3:
                return [0, projSpeed]
        # Puerta 5
        elif self.gate == 1:
            if self.dir == 1:
                return [-(projSpeed // 2), (projSpeed // 2)]
            if self.dir == 2:
                return [0, projSpeed]
            if self.dir == 3:
                return [(projSpeed // 2), (projSpeed // 2)]
        # Puerta 6
        elif self.gate == 1:
            if self.dir == 1:
                return [0, projSpeed]
            if self.dir == 2:
                return [(projSpeed // 2), (projSpeed // 2)]
            if self.dir == 3:
                return [projSpeed, 0]
        # Puerta 7
        elif self.gate == 1:
            if self.dir == 1:
                return [(projSpeed // 2), (projSpeed // 2)]
            if self.dir == 2:
                return [projSpeed, 0]
            if self.dir == 3:
                return [(projSpeed // 2), -(projSpeed // 2)]
        # Puerta 8
        elif self.gate == 1:
            if self.dir == 1:
                return [projSpeed, 0]
            if self.dir == 2:
                return [(projSpeed // 2), -(projSpeed // 2)]
            if self.dir == 3:
                return [0, -projSpeed]


def generateProjectile(projSpeed):
    while running:
        yield Projectile(projSpeed)
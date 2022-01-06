import math
import random
from typing import Union

import pygame
from pygame import Rect
from pygame.surface import Surface, SurfaceType
from screeninfo import get_monitors

"""
Archivo para las variables y funciones globales.
"""

"""     Control del funcionamiento     """
# Variable para controlar el funcionamiento del programa.
running = True
# Variable para controlar el funcionamiento del audio.
audio = True
# Variable para controlar si el juego está en funcionamiento.
gameRunning = False
# Reloj
clock = pygame.time.Clock()

"""     Dificultad del juego     """
# Nivel de dificultad del juego
gameDifficulty = 2 # 0 - Baby Mode, 1 - Fácil, 2 - Normal, 3 - Difícil, 4 - Bullet Hell
gameDifficultyName = "< Normal >"
# Contadores para las dificultades especiales
babyModeCounter = 0
bulletHellCounter = 0

"""     Rutas de archivos     """
# Localización del array con las puntuaciones.
scoresFile = "../Data/scores.txt"
# Localización de la imagen de fondo del MainTitle.
bgMainTitle = pygame.image.load("../Data/Images/game_bg.png")
# Localización de la imagen de fondo del Game.
bgGame = pygame.image.load("../Data/Images/game_bg.png")
# Localización de la imagen del icono de la ventana
gameIcon = pygame.image.load("../Data/Images/icon.png")
# Localización de la imagen de fondo de PauseScreen
bgPauseScreen = pygame.image.load("../Data/Images/pause_bg.png")
# Localizaciones de los sprites del jugador
playerStill = pygame.image.load("../Data/Images/playerStill.png")
playerMoveLeft = pygame.image.load("../Data/Images/playerMove.png")
playerMoveRight = pygame.transform.flip(playerMoveLeft, True, False)
playerHit = pygame.image.load("../Data/Images/playerHit.png")
playerDeath = pygame.image.load("../Data/Images/playerDeath.png")
# Localizaciones de los sprites de los proyectiles
projectileImg = pygame.image.load("../Data/Images/projectile.png")
projectileDestImg = pygame.image.load("../Data/Images/projectileDestruction.png")

"""     Apartado gráfico     """
# Tamaño de la ventana
width, height = get_monitors()[0].width, get_monitors()[0].height
# Ventana
screen: pygame.Surface = pygame.display
# Colores y superficies para los valores alpha
white = (255, 255, 255)
black = (0, 0, 0)
red = (255, 0, 0)
blue = (84, 137, 206)
yellow = (244, 252, 15)
fadedWhite = (255, 255, 255, 100)
fadedRed = (142, 0, 0, 100)

alpha_surf = pygame.Surface((width, height), pygame.SRCALPHA)
alpha_surf.fill(fadedWhite)

# Ajustamos la imagen de fondo al tamaño del monitor
bgMainTitle = pygame.transform.scale(bgMainTitle, (width, height))
bgGame = pygame.transform.scale(bgGame, (width, height))
# Ajustamos los sprites al tamaño del monitor
playerStill = pygame.transform.scale(playerStill, (width // 20, width // 20))
playerMoveLeft = pygame.transform.scale(playerMoveLeft, (width // 20, width // 20))
playerMoveRight = pygame.transform.scale(playerMoveRight, (width // 20, width // 20))
playerHit = pygame.transform.scale(playerHit, (width // 20, width // 20))
playerDeath = pygame.transform.scale(playerDeath, (width // 20, width // 20))
projectileImg = pygame.transform.scale(projectileImg, (width // 20, width // 40))
projectileDestImg = pygame.transform.scale(projectileDestImg, (width // 20, width // 40))

# Se necesitan 8 variaciones del sprite del proyectil (1 para cada dirección)
projSprite0 = projectileImg
projSprite45 = pygame.transform.rotate(projSprite0, 45)
projSprite90 = pygame.transform.rotate(projSprite0, 90)
projSprite135 = pygame.transform.rotate(projSprite0, 135)
projSprite180 = pygame.transform.rotate(projSprite0, 180)
projSprite225 = pygame.transform.rotate(projSprite0, 225)
projSprite270 = pygame.transform.rotate(projSprite0, 270)
projSprite315 = pygame.transform.rotate(projSprite0, 315)

projDestSprite0 = projectileDestImg
projDestSprite45 = pygame.transform.rotate(projDestSprite0, 45)
projDestSprite90 = pygame.transform.rotate(projDestSprite0, 90)
projDestSprite135 = pygame.transform.rotate(projDestSprite0, 135)
projDestSprite180 = pygame.transform.rotate(projDestSprite0, 180)
projDestSprite225 = pygame.transform.rotate(projDestSprite0, 225)
projDestSprite270 = pygame.transform.rotate(projDestSprite0, 270)
projDestSprite315 = pygame.transform.rotate(projDestSprite0, 315)

# Colores para los botones
buttonBg = (206, 163, 84)
buttonBorder = (206, 200, 84)
selectedButtonBg = (84, 137, 206)
selectedButtonBorder = (84, 184, 206)
audioOff = (252, 55, 68)
audioOn = (41, 173, 50)

# Rectángulos para los botones
playRect = pygame.Rect((width * 4 // 6, height * 2 // 6), (width * 2 // 6 + 15, height * 1 // 9))
diffRect = pygame.Rect((width * 4 // 6, height * 3 // 6), (width * 2 // 6 + 15, height * 1 // 9))
audioRect = pygame.Rect((width * 4 // 6, height * 4 // 6), (width * 2 // 6 + 15, height * 1 // 9))
exitRect = pygame.Rect((width * 4 // 6, height * 5 // 6), (width * 2 // 6 + 15, height * 1 // 9))

# Rectángulos para la vida y la energía
"""
Tendrán altura = 1/10 de la altura del monitor
Tendrán anchura = 1/6 de la anchura del monitor
"""
# hpBar = pygame.Rect((width // 20, height // 40), (width // 6, height // 10))
# spBar = pygame.Rect((width // 20, height * 3 // 20), (width // 6, height // 10))

# Rectángulos para crear el polígono para el área no jugable.
# pygame.Rect((x, y), (width, height))
rect1 = pygame.Rect((0, 0), (width // 6, height)) # Sexto vertical izquierdo de la pantalla
rect2 = pygame.Rect((width * 5 // 6, 0), (width // 6, height)) # Sexto vertical derecho de la pantalla
rect3 = pygame.Rect((0, 0), (width, height // 6)) # Sexto horizontal superior de la pantalla
rect4 = pygame.Rect((0, height * 5 // 6), (width, height // 6)) # Sexto horizontal inferior de la pantalla
rect5 = pygame.Rect((width // 6, height // 6), (width // 12, height // 6)) # Esquina superior izquierda, rectángulo vertical
rect6 = pygame.Rect((width * 3 // 12, height // 6), (width // 12, height // 12)) # Esquina superior izquierda, rectángulo horizontal
rect7 = pygame.Rect((width * 9 // 12, height // 6), (width // 12, height // 6)) # Esquina superior derecha, rectángulo vertical
rect8 = pygame.Rect((width * 4 // 6, height // 6), (width // 12, height // 12)) # Esquina superior derecha, cuadrado
rect9 = pygame.Rect((width // 6, height * 9 // 12), (width // 12, height // 12)) # Esquina inferior izquierda
rect10 = pygame.Rect((width * 9 // 12, height * 9 // 12), (width // 12, height // 12)) # Esquina inferior derecha
border = [rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10] # Uníon de los rectángulos

# Fuentes para el texto
pygame.font.init()
titleFont = pygame.font.Font("../Data/font.ttf", height // 3)
titleFontSurface = titleFont.render("D0DCH!", False, blue)
pauseFontSurface = titleFont.render("PAUSA", False, blue)
deathFontSurface = titleFont.render("HAS MUERTO", False, red)
buttonFont = pygame.font.Font("../Data/font.ttf", height // 12)

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

"""
CONTROL DE LA DIFICULTAD
"""
def decreaseDif():
    """
    Decrementa la dificultad.
    Si se llama 5 veces consecutivas a esta función cuando la dificultad ya está en 1,
    se pone la dificultad Baby Mode :) .
    """
    global gameDifficulty
    global gameDifficultyName
    global babyModeCounter

    # Si la dificultad no es fácil (1)
    if gameDifficulty > 1:
        # Decrementamos la dificultad
        gameDifficulty -= 1
        # Reseteamos el contador de babyMode
        babyModeCounter = 0
        # Ajustamos el nombre de la dificultad
        assignDiffName()

    # Si la dificultad ya está en fácil
    elif gameDifficulty == 1:
        # Se incrementa el babyModeCounter
        babyModeCounter += 1
        # Si el contador ya está en 5
        if babyModeCounter == 5:
            # Se actualiza la dificultad a babyMode
            gameDifficulty = 0
            # Ajustamos el nombre de la dificultad
            assignDiffName()

def increaseDif():
    """
    Incrementa la dificultad.
    Si se llama 5 veces consecutivas a esta función cuando la dificultad ya está en 3,
    se pone la dificultad Bullet Hell :D.
    """
    global gameDifficulty
    global gameDifficultyName
    global bulletHellCounter

    # Si la dificultad no es difícil (3)
    if gameDifficulty < 3:
        # Incrementamos la dificultad
        gameDifficulty += 1
        # Reseteamos el contador de bulletHell
        bulletHellCounter = 0
        # Ajustamos el nombre de la dificultad
        assignDiffName()

    # Si la dificultad ya está en difícil
    elif gameDifficulty == 3:
        # Se incrementa el bulletHellCounter
        bulletHellCounter += 1
        # Si el contador ya está en 5
        if bulletHellCounter == 5:
            # Se actualiza la dificultad a bullet hell
            gameDifficulty = 4
            # Ajustamos el nombre de la dificultad
            assignDiffName()

def assignDiffName():
    global gameDifficultyName

    if gameDifficulty == 0:
        gameDifficultyName = "  Baby Mode >"
    if gameDifficulty == 1:
        gameDifficultyName = "  Fácil >"
    if gameDifficulty == 2:
        gameDifficultyName = "< Normal >"
    if gameDifficulty == 3:
        gameDifficultyName = " < Difícil  "
    if gameDifficulty == 4:
        gameDifficultyName = "< Bullet Hell"

"""
CONTROL DE LOS BOTONES
"""
def drawButtons(first="Jugar", diff=True, second="", third="Audio: On", fourth="Salir", death=False):
    # Jugar
    pygame.draw.rect(screen, buttonBg, playRect, 0, border_top_left_radius=playRect.height//6,
                     border_bottom_left_radius=playRect.height//6)
    pygame.draw.rect(screen, buttonBorder, playRect, 15, border_top_left_radius=playRect.height//6,
                     border_bottom_left_radius=playRect.height//6)
    playButtonFontSurface = buttonFont.render(first, False, white)
    screen.blit(playButtonFontSurface, (playRect.x + playRect.height*2//6, playRect.y + playRect.height//6))

    # Dificultad
    pygame.draw.rect(screen, buttonBg, diffRect, 0, border_top_left_radius=diffRect.height//6,
                     border_bottom_left_radius=diffRect.height//6)
    pygame.draw.rect(screen, buttonBorder, diffRect, 15, border_top_left_radius=diffRect.height//6,
                     border_bottom_left_radius=diffRect.height//6)
    diffButtonFontSurface = buttonFont.render(gameDifficultyName if diff else second, False, white)
    screen.blit(diffButtonFontSurface, (diffRect.x + diffRect.height*2//6, diffRect.y + diffRect.height//6))

    if not death:
        # Audio
        pygame.draw.rect(screen, buttonBg, audioRect, 0, border_top_left_radius=audioRect.height//6,
                         border_bottom_left_radius=audioRect.height//6)
        pygame.draw.rect(screen, audioOn if audio else audioOff, audioRect, 15,
                         border_top_left_radius=audioRect.height//6, border_bottom_left_radius=audioRect.height//6)
        audioButtonFontSurface = buttonFont.render(third, False, white)
        screen.blit(audioButtonFontSurface, (audioRect.x + audioRect.height*2//6, audioRect.y + audioRect.height//6))

        # Salir
        pygame.draw.rect(screen, buttonBg, exitRect, 0, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        pygame.draw.rect(screen, buttonBorder, exitRect, 15, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        exitButtonFontSurface = buttonFont.render(fourth, False, white)
        screen.blit(exitButtonFontSurface, (exitRect.x + exitRect.height*2//6, exitRect.y + exitRect.height//6))
    else:
        # Salir
        pygame.draw.rect(screen, buttonBg, audioRect, 0, border_top_left_radius=audioRect.height // 6,
                         border_bottom_left_radius=audioRect.height // 6)
        pygame.draw.rect(screen, buttonBorder, audioRect, 15, border_top_left_radius=audioRect.height // 6,
                         border_bottom_left_radius=audioRect.height // 6)
        exitButtonFontSurface = buttonFont.render(fourth, False, white)
        screen.blit(exitButtonFontSurface, (audioRect.x + audioRect.height * 2 // 6, audioRect.y + audioRect.height // 6))

def drawSelectedButton(selection = 1, first="Jugar", diff=True, second="", third=False):
    # Se dibuja el rectángulo como un botón seleccionado
    if selection == 1:
        pygame.draw.rect(screen, selectedButtonBg, playRect, 0, border_top_left_radius=playRect.height//6,
                         border_bottom_left_radius=playRect.height//6)
        pygame.draw.rect(screen, selectedButtonBorder, playRect, 15, border_top_left_radius=playRect.height//6,
                         border_bottom_left_radius=playRect.height//6)
        playButtonFontSurface = buttonFont.render(first, False, yellow)
        screen.blit(playButtonFontSurface, (playRect.x + playRect.height*2//6, playRect.y + playRect.height//6))

    if selection == 2:
        pygame.draw.rect(screen, selectedButtonBg, diffRect, 0, border_top_left_radius=diffRect.height//6,
                         border_bottom_left_radius=diffRect.height//6)
        pygame.draw.rect(screen, selectedButtonBorder, diffRect, 15, border_top_left_radius=diffRect.height//6,
                         border_bottom_left_radius=diffRect.height//6)
        diffButtonFontSurface = buttonFont.render(gameDifficultyName if diff else second, False, yellow)
        screen.blit(diffButtonFontSurface, (diffRect.x + diffRect.height*2//6, diffRect.y + diffRect.height//6))

    if selection == 3:
        if not third:
            pygame.draw.rect(screen, selectedButtonBg, audioRect, 0, border_top_left_radius=audioRect.height//6,
                             border_bottom_left_radius=audioRect.height//6)
            pygame.draw.rect(screen, audioOn if audio else audioOff, audioRect, 15,
                             border_top_left_radius=audioRect.height//6, border_bottom_left_radius=audioRect.height//6)
            audioButtonFontSurface = buttonFont.render("Audio: On" if audio else "Audio: Off", False, yellow)
            screen.blit(audioButtonFontSurface, (audioRect.x + audioRect.height*2//6, audioRect.y + audioRect.height//6))
        else:
            pygame.draw.rect(screen, selectedButtonBg, audioRect, 0, border_top_left_radius=audioRect.height // 6,
                             border_bottom_left_radius=audioRect.height // 6)
            pygame.draw.rect(screen, selectedButtonBorder, audioRect, 15, border_top_left_radius=audioRect.height // 6,
                             border_bottom_left_radius=audioRect.height // 6)
            exitButtonFontSurface = buttonFont.render("Salir", False, yellow)
            screen.blit(exitButtonFontSurface,
                        (audioRect.x + audioRect.height * 2 // 6, audioRect.y + audioRect.height // 6))

    if selection == 4:
        pygame.draw.rect(screen, selectedButtonBg, exitRect, 0, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        pygame.draw.rect(screen, selectedButtonBorder, exitRect, 15, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        exitButtonFontSurface = buttonFont.render("Salir", False, yellow)
        screen.blit(exitButtonFontSurface, (exitRect.x + exitRect.height*2//6, exitRect.y + exitRect.height//6))

def drawUnselectedButton(unselected, first="Jugar", diff=True, second="", third=False):
    # Se restaura el rectángulo a un botón no seleccionado
    # De nuevo, se distingue el botón del audio
    if unselected == 1:
        pygame.draw.rect(screen, buttonBg, playRect, 0, border_top_left_radius=playRect.height//6,
                         border_bottom_left_radius=playRect.height//6)
        pygame.draw.rect(screen, buttonBorder, playRect, 15, border_top_left_radius=playRect.height//6,
                         border_bottom_left_radius=playRect.height//6)
        playButtonFontSurface = buttonFont.render(first, False, white)
        screen.blit(playButtonFontSurface, (playRect.x + playRect.height*2//6, playRect.y + playRect.height//6))
    if unselected == 2:
        pygame.draw.rect(screen, buttonBg, diffRect, 0, border_top_left_radius=diffRect.height//6,
                         border_bottom_left_radius=diffRect.height//6)
        pygame.draw.rect(screen, buttonBorder, diffRect, 15, border_top_left_radius=diffRect.height//6,
                         border_bottom_left_radius=diffRect.height//6)
        diffButtonFontSurface = buttonFont.render(gameDifficultyName if diff else second, False, white)
        screen.blit(diffButtonFontSurface, (diffRect.x + diffRect.height*2//6, diffRect.y + diffRect.height//6))
    if unselected == 3:
        if not third:
            pygame.draw.rect(screen, buttonBg, audioRect, 0, border_top_left_radius=audioRect.height//6,
                             border_bottom_left_radius=audioRect.height//6)
            pygame.draw.rect(screen, audioOn if audio else audioOff, audioRect, 15,
                             border_top_left_radius=audioRect.height//6, border_bottom_left_radius=audioRect.height//6)
            audioButtonFontSurface = buttonFont.render("Audio: On" if audio else "Audio: Off", False, white)
            screen.blit(audioButtonFontSurface, (audioRect.x + audioRect.height*2//6, audioRect.y + audioRect.height//6))
        else:
            pygame.draw.rect(screen, buttonBg, audioRect, 0, border_top_left_radius=audioRect.height // 6,
                             border_bottom_left_radius=audioRect.height // 6)
            pygame.draw.rect(screen, buttonBorder, audioRect, 15, border_top_left_radius=audioRect.height // 6,
                             border_bottom_left_radius=audioRect.height // 6)
            exitButtonFontSurface = buttonFont.render("Salir", False, white)
            screen.blit(exitButtonFontSurface,
                        (audioRect.x + audioRect.height * 2 // 6, audioRect.y + audioRect.height // 6))
    if unselected == 4:
        pygame.draw.rect(screen, buttonBg, exitRect, 0, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        pygame.draw.rect(screen, buttonBorder, exitRect, 15, border_top_left_radius=exitRect.height//6,
                         border_bottom_left_radius=exitRect.height//6)
        exitButtonFontSurface = buttonFont.render("Salir", False, white)
        screen.blit(exitButtonFontSurface, (exitRect.x + exitRect.height*2//6, exitRect.y + exitRect.height//6))

"""
CLASE JUGADOR
"""
class Player:
    """
    Clase para el objeto Jugador.
    Tendrá vida, energía, una posición y una velocidad.
    Al inicio, el jugador aparecerá en el centro de la pantalla, y tendrá velocidad 0.
    """
    playerHitBox: Rect
    health: int
    stamina: int
    speed: tuple[int, int]
    alive: bool
    global playerStill, playerMove, playerDeath

    def __init__(self):
        self.health = 100
        self.stamina = 100
        self.speed = (0, 0)
        self.alive = True
        # La hitbox del jugador es un rectángulo de 1/20 de la longitud de la pantalla
        # En un principio la hitbox está colocada en el centro de la pantalla

        self.playerHitBox = pygame.Rect((width // 2-(((1/20)*width) // 2)),
                               (height // 2-(((1/20)*width) // 2)),
                               (1/20)*width,
                               (1/20)*width)


    def isAlive(self):
        return self.alive

    def die(self):
        self.alive = False

    def  updateHP(self, q):
        if self.alive:
            self.animateHit()
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


    def moveUp(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0], self.speed[1] - v)
    def moveDown(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0], self.speed[1] + v)
    def moveLeft(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0] - (v * (width // height)), self.speed[1]) # Aplicamos el ratio de la pantalla
    def moveRight(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0] + (v * (width // height)), self.speed[1]) # Aplicamos el ratio de la pantalla

    def stop(self):
        self.speed = (0, 0)

    def isStopped(self):
        return self.speed == (0, 0)

    def updatePos(self, speed: tuple[int, int]):
        """
        Actualiza la posición del jugador.
        """
        if self.alive:
            self.speed = speed
            # Si la stamina es mayor que 0, el jugador se mueve.
            if self.stamina >= 0:
                # Primero comprobamos que nos podemos mover
                if checkBounds(pygame.Rect((self.playerHitBox.x + speed[0], self.playerHitBox.y + speed[1]), (self.playerHitBox.width, self.playerHitBox.height))):
                    # Si no vamos a colisionar con el área no jugable, comprobamos si vamos a mover el personaje.
                    if not self.isStopped():
                        # Si la velocidad no es (0,0), nos movemos y gastamos 1 de SP.
                        self.updateSP(-1)
                        self.playerHitBox.move_ip(self.speed[0], self.speed[1])
                    else:
                        # Si estamos quietos, regeneramos 1 de MP.
                        self.updateSP(1)
                else:
                    # Si el personaje colisiona con el límite, tenemos que moverlo hacía atrás para que no siga colisionando.
                    self.playerHitBox.move_ip(-self.speed[0]*2, -self.speed[1]*2)


            # Animamos el movimiento del jugador
            self.animate()
            print("HP: ", self.health)
            print("SP: ", self.stamina)
            print("Jugador: ", self.playerHitBox.centerx, ", ", self.playerHitBox.centery)
            print("Velocidad: ", self.speed)


    def animate(self):
        if self.isStopped():
            # Si el jugador está quieto
            screen.blit(playerStill, self.playerHitBox)
        elif self.speed[0] < 0:
            # Si el jugador se está moviendo hacia la izquierda
            screen.blit(playerMoveLeft, self.playerHitBox)
        elif self.speed[0] > 0:
            # Si el jugador se está moviendo hacia la derecha
            screen.blit(playerMoveRight, self.playerHitBox)
        else:
            # Si el jugador se está moviendo en cualquier otra dirección
            screen.blit(playerMoveLeft, self.playerHitBox)

    def animateHit(self):
        if self.isAlive():
            # Si el jugador ha recibido daño pero sigue vivo
            screen.blit(playerHit, self.playerHitBox)
            self.drawHPBar()
            self.drawSPBar()
            pygame.display.update()
            pygame.time.wait(500)

        else:
            # Si el jugador ha recibido daño fatal
            screen.blit(playerDeath, self.playerHitBox)
            pygame.display.update()
            pygame.time.wait(1000)

    def drawHPBar(self):
        """
        Dibuja un rectángulo para la vida, cuyo tamaño estará basado en la vida actual del personaje.
        :return: None
        """
        hpBar = pygame.Rect((width // 10, height // 40), ((width // 6) * self.health // 100, height // 10))
        fullhpBar = pygame.Rect((width // 10 - hpBar.height // 6, height // 40), ((width // 6) + hpBar.height // 6, height // 10))
        pygame.draw.rect(screen, red, fullhpBar, width= hpBar.height // 6, border_radius=hpBar.height // 6)
        pygame.draw.rect(screen, red, hpBar, border_radius=hpBar.height // 6)
        hpText = buttonFont.render("HP", False, white)
        screen.blit(hpText, (hpBar.x + hpBar.height * 2 // 6, hpBar.y + hpBar.height // 6))


    def drawSPBar(self):
        """
        Dibuja un rectángulo para la energía, cuyo tamaño estará basado en la energía actual del personaje.
        :return: None
        """
        spBar = pygame.Rect((width // 10, height * 3 // 20), ((width // 6) * self.stamina // 100, height // 10))
        fullspBar = pygame.Rect((width // 10 - spBar.height // 6, height * 3 // 20), ((width // 6) + spBar.height // 6, height // 10))
        pygame.draw.rect(screen, blue, fullspBar, width= spBar.height // 6, border_radius=spBar.height // 6)
        pygame.draw.rect(screen, blue, spBar, border_radius=spBar.height // 6)
        spText = buttonFont.render("SP", False, white)
        screen.blit(spText, (spBar.x + spBar.height * 2 // 6, spBar.y + spBar.height // 6))

    def getRect(self):
        return self.playerHitBox

"""
CLASE PROYECTIL Y GENERADOR DE PROYECTILES
"""
class Projectile:
    """
    Clase para el objeto Proyectil.
    Tendrá una posición y una velocidad.

    Localización de las "puertas" o zonas de spawn:
    _________________________
    |                       |
    |            1          |
    |        8       2      |
    |    7              3   |
    |       6       4       |
    |           5           |
    _________________________

    Las direcciónes serán:
        1- 45º desde la normal de la puerta con el borde de la zona jugable.
        2- normal de la puerta con el borde de la zona jugable.
        3- -45º desde la normal de la puerta con el borde de la zona jugable.

    Los proyectiles aparecen desde una de las 8 zonas de spawn que tienen. Además, cada zona tiene 3 direcciones.
    Al crear el proyectil, se le asignará de forma aleatoria una zona y una dirección.

    """
    projectileImg: Union[Surface, SurfaceType]
    projectileHitBox: Rect
    speed: tuple[int, int]
    gate: int
    dir: int

    # La altura y anchura del rectángulo que formará la hitbox del proyectil.
    pWidth = (1 / 20) * width
    pHeight = (1 / 40) * width

    def __init__(self, projSpeed):
        self.gate = random.randint(1, 8)
        self.dir = random.randint(1, 3)
        self.speed = self.assignSpeed(projSpeed)
        self.projectileHitBox = self.assignInitialPos()
        self.projectileImg = projectileImg
        self.projectileDestImg = projectileDestImg

    # sqrt(x**2 + y**2) = projSpeed -> x**2 + y**2 = projSpeed**2
    def assignSpeed(self, projSpeed):
        # La velocidad dada es siempre positiva, puesto que viene de la función Game.assignProjectileSpeed().
        # Hay que tener en cuenta que el movimiento de los proyectiles hacia abajo es POSITIVO en términos de coordenadas.
        # Puerta 1
        if self.gate == 1:
            if self.dir == 1:
                return ((projSpeed // 2), (projSpeed // 2))
            if self.dir == 2:
                return (0, projSpeed)
            if self.dir == 3:
                return (-(projSpeed // 2), (projSpeed // 2))
        # Puerta 2
        elif self.gate == 2:
            if self.dir == 1:
                return (0, projSpeed)
            if self.dir == 2:
                return (-(projSpeed // 2), (projSpeed // 2))
            if self.dir == 3:
                return (-projSpeed, 0)
        # Puerta 3
        elif self.gate == 3:
            if self.dir == 1:
                return (-(projSpeed // 2), (projSpeed // 2))
            if self.dir == 2:
                return (-projSpeed, 0)
            if self.dir == 3:
                return (-(projSpeed // 2), -(projSpeed // 2))
        # Puerta 4
        elif self.gate == 4:
            if self.dir == 1:
                return (-projSpeed, 0)
            if self.dir == 2:
                return (-(projSpeed // 2), -(projSpeed // 2))
            if self.dir == 3:
                return (0, -projSpeed)
        # Puerta 5
        elif self.gate == 5:
            if self.dir == 1:
                return (-(projSpeed // 2), -(projSpeed // 2))
            if self.dir == 2:
                return (0, -projSpeed)
            if self.dir == 3:
                return ((projSpeed // 2), -(projSpeed // 2))
        # Puerta 6
        elif self.gate == 6:
            if self.dir == 1:
                return (0, -projSpeed)
            if self.dir == 2:
                return ((projSpeed // 2), -(projSpeed // 2))
            if self.dir == 3:
                return (projSpeed, 0)
        # Puerta 7
        elif self.gate == 7:
            if self.dir == 1:
                return ((projSpeed // 2), -(projSpeed // 2))
            if self.dir == 2:
                return (projSpeed, 0)
            if self.dir == 3:
                return ((projSpeed // 2), (projSpeed // 2))
        # Puerta 8
        elif self.gate == 8:
            if self.dir == 1:
                return (projSpeed, 0)
            if self.dir == 2:
                return ((projSpeed // 2), (projSpeed // 2))
            if self.dir == 3:
                return (0, (projSpeed))

    def updatePos(self):
        """
        Actualiza la posición del proyectil.
        """
        self.projectileHitBox.move_ip(self.speed[0], self.speed[1])
        self.animate()
        print("Proyectil: ", self.projectileHitBox.centerx, ", ", self.projectileHitBox.centery)

        # Aplicamos un límite a los projectiles
        if not checkBounds(self.projectileHitBox, self.gate):
            self.destroy()
            return None
        else:
            return self

    def checkCollision(self, playerRect):
        """
        Comprueba si el rectángulo del jugador colisiona con el del proyectil.
        :param playerRect: Rectángulo del jugador.
        :return: Si colisiona o no.
        """
        return self.projectileHitBox.colliderect(playerRect)

    def assignInitialPos(self):
        """
        Las coordenadas de aparición dependen de la puerta en la que se genere el proyectil.
        Puerta 1:  (longitud de la pantalla * 3 / 6 - anchura del proyectil / 2, altura de la pantalla * 1 / 6 - altura del proyectil)
        Puerta 2:  (longitud de la pantalla * 4 / 6 + anchura del proyectil / 2, altura de la pantalla * 2 / 6 - altura del proyectil / 2)
        Puerta 3:  (longitud de la pantalla * 5 / 6 + anchura del proyectil, altura de la pantalla  * 3 / 6 - altura del proyectil / 2)
        Puerta 4:  (longitud de la pantalla * 4 / 6 + anchura del proyectil / 2, altura de la pantalla * 4 / 6 + altura del proyectil / 2)
        Puerta 5:  (longitud de la pantalla * 3 / 6 - anchura del proyectil / 2, altura de la pantalla * 5 / 6 + altura del proyectil)
        Puerta 6:  (longitud de la pantalla * 2 / 6 - anchura del proyectil / 2, altura de la pantalla * 4 / 6 + altura del proyectil / 2)
        Puerta 7:  (longitud de la pantalla * 1 / 6 - anchura del proyectil, altura de la pantalla  * 3 / 6 - altura del proyectil / 2)
        Puerta 8:  (longitud de la pantalla * 2 / 6 - anchura del proyectil / 2, altura de la pantalla * 2 / 6 - altura del proyectil / 2)

        La altura y anchura del proyectil es de 1/40 de la anchura de la pantalla.
        """
        # Puerta 1
        if self.gate == 1:
            return Rect(( (width * 3 // 6) - (self.pWidth // 2), (height * 1 // 6) - (self.pHeight) ),
                        (self.pWidth, self.pHeight))
        # Puerta 2
        elif self.gate == 2:
            return Rect(( (width * 4 // 6) + (self.pWidth // 2), (height * 2 // 6) - (self.pHeight // 2) ),
                        (self.pWidth, self.pHeight))
        # Puerta 3
        elif self.gate == 3:
            return Rect(( (width * 5 // 6) + (self.pWidth), (height * 3 // 6) - (self.pHeight // 2) ),
                        (self.pWidth, self.pHeight))
        # Puerta 4
        elif self.gate == 4:
            return Rect(((width * 4 // 6) + (self.pWidth // 2), (height * 4 // 6) + (self.pHeight // 2)),
                        (self.pWidth, self.pHeight))
        # Puerta 5
        elif self.gate == 5:
            return Rect(( (width * 3 // 6) - (self.pWidth // 2), (height * 5 // 6) + (self.pHeight) ),
                        (self.pWidth, self.pHeight))
        # Puerta 6
        elif self.gate == 6:
            return Rect(((width * 2 // 6) - (self.pWidth // 2), (height * 4 // 6) + (self.pHeight // 2)),
                        (self.pWidth, self.pHeight))
        # Puerta 7
        elif self.gate == 7:
            return Rect(( (width * 1 // 6) - (self.pWidth), (height * 3 // 6) - (self.pHeight // 2) ),
                        (self.pWidth, self.pHeight))
        # Puerta 8
        elif self.gate == 8:
            return Rect(((width * 2 // 6) - (self.pWidth // 2), (height * 2 // 6) - (self.pHeight // 2)),
                        (self.pWidth, self.pHeight))

    def animate(self):
        dir = (self.gate, self.dir)
        if dir == (6, 3) or dir == (7, 2) or dir == (8, 1):
            # Si el proyectil va hacia la derecha
            screen.blit(projSprite0, self.projectileHitBox)
        if dir == (5, 3) or dir == (6, 2) or dir == (7, 1):
            # Si el proyectil va hacia arriba derecha
            screen.blit(projSprite45, self.projectileHitBox)
        if dir == (4, 3) or dir == (5, 2) or dir == (6, 1):
            # Si el proyectil va hacia arriba
            screen.blit(projSprite90, self.projectileHitBox)
        if dir == (3, 3) or dir == (4, 2) or dir == (5, 1):
            # Si el proyectil va hacia arriba izquierda
            screen.blit(projSprite135, self.projectileHitBox)
        if dir == (2, 3) or dir == (3, 2) or dir == (4, 1):
            # Si el proyectil va hacia la izquierda
            screen.blit(projSprite180, self.projectileHitBox)
        if dir == (1, 3) or dir == (2, 2) or dir == (3, 1):
            # Si el proyectil va hacia izquierda abajo
            screen.blit(projSprite225, self.projectileHitBox)
        if dir == (8, 3) or dir == (1, 2) or dir == (2, 1):
            # Si el proyectil va hacia abajo
            screen.blit(projSprite270, self.projectileHitBox)
        if dir == (7, 3) or dir == (8, 2) or dir == (1, 1):
            # Si el proyectil va hacia derecha abajo
            screen.blit(projSprite315, self.projectileHitBox)

    def destroy(self):
        """
        Reproduce la animación de destrucción del proyectil y destruye su rectángulo.
        :return:
        """
        dir = (self.gate, self.dir)
        if dir == (6, 3) or dir == (7, 2) or dir == (8, 1):
            # Si el proyectil va hacia la derecha
            screen.blit(projDestSprite0, self.projectileHitBox)
        if dir == (5, 3) or dir == (6, 2) or dir == (7, 1):
            # Si el proyectil va hacia arriba derecha
            screen.blit(projDestSprite45, self.projectileHitBox)
        if dir == (4, 3) or dir == (5, 2) or dir == (6, 1):
            # Si el proyectil va hacia arriba
            screen.blit(projDestSprite90, self.projectileHitBox)
        if dir == (3, 3) or dir == (4, 2) or dir == (5, 1):
            # Si el proyectil va hacia arriba izquierda
            screen.blit(projDestSprite135, self.projectileHitBox)
        if dir == (2, 3) or dir == (3, 2) or dir == (4, 1):
            # Si el proyectil va hacia la izquierda
            screen.blit(projDestSprite180, self.projectileHitBox)
        if dir == (1, 3) or dir == (2, 2) or dir == (3, 1):
            # Si el proyectil va hacia izquierda abajo
            screen.blit(projDestSprite225, self.projectileHitBox)
        if dir == (8, 3) or dir == (1, 2) or dir == (2, 1):
            # Si el proyectil va hacia abajo
            screen.blit(projDestSprite270, self.projectileHitBox)
        if dir == (7, 3) or dir == (8, 2) or dir == (1, 1):
            # Si el proyectil va hacia derecha abajo
            screen.blit(projDestSprite315, self.projectileHitBox)

        self.projectileHitBox = None

def generateProjectile(projSpeed):
    # Modificadores según la dificultad
    if gameDifficulty == 0:
        # Probabilidad de 1/100 de generar un proyectil
        if random.randint(1, 100) == 1:
            return Projectile(projSpeed)
        else:
            return None
    elif gameDifficulty == 1:
        # Probabilidad de 1/10 de generar un proyectil
        if random.randint(1, 10) == 1:
            return Projectile(projSpeed)
        else:
            return None
    elif gameDifficulty == 2:
        # Probabilidad de 1/5 de generar un proyectil
        if random.randint(1, 5) == 5:
            return Projectile(projSpeed)
        else:
            return None
    elif gameDifficulty == 3:
        # Probabilidad de 1/3 de generar un proyectil
        if random.randint(1, 3) == 1:
            return Projectile(projSpeed)
        else:
            return None
    elif gameDifficulty == 4:
        # Probabilidad de 1/2 de generar un proyectil
        if random.randint(1, 2) == 1:
            return Projectile(projSpeed)
        else:
            return None

def checkBounds(rect: pygame.Rect, gate: int = 0):
    """
    Comprueba que el rectángulo dado se encuentra en los límites del área jugable.
    Para comprobar que un rectángulo se encuentra dentro, se tomarán varios rectángulos y se unirán para formar,
    de forma aproximada, el límite exterior de la Arena del juego.
    Comprobando si el rectángulo dado colisiona con la unión de rectángulos que forman el límite, se considera que
    el rectángulo está "fuera" del área jugable.
    Si es un proyectil, se destruirá.
    Si es el jugador, se detendrá su movimiento.

    Los rectángulos de los límites son 10, se pueden ver en el archivo borders.png en Data/Images.

    La función collidelist(list[Rect]) comprueba si el rectángulo colisiona con alguno de los que hay en la lista, y
    devuelve su índice si encuentra uno con el que colisione.
    Si no encuentra ninguno, devuelve -1.

    :param rect: Rectángulo que se comprobará.
    :param gate: Predeterminado 0. Si se trata de un proyectil, usa unos limites para que el proyectil no desaparezca al
    spawnear.
    :return: True si está dentro de los límites. False si el rectángulo colisiona con el límite del área jugable.
    """
    if gate == 0:
        return rect.collidelist(border) == -1
    if gate == 1:
        # Ignora el rectángulo horizontal superior.
        aux = border.copy()
        aux.pop(2)
        return rect.collidelist(aux) == -1
    if gate == 2:
         # Ignora los rectángulos de la esquina superior derecha (los pequeños)
        aux = border.copy()
        aux.pop(6)
        aux.pop(7)
        return rect.collidelist(aux) == -1
    if gate == 3:
         # Ignora el rectángulo vertical derecho.
        aux = border.copy()
        aux.pop(1)
        return rect.collidelist(aux) == -1
    if gate == 4:
         # Ignora el rectángulo inferior derecho.
        aux = border.copy()
        aux.pop(9)
        return rect.collidelist(aux) == -1
    if gate == 5:
         # Ignora el rectángulo horizontal inferior.
        aux = border.copy()
        aux.pop(3)
        return rect.collidelist(aux) == -1
    if gate == 6:
         # Ignora el rectángulo inferior izquierdo.
        aux = border.copy()
        aux.pop(8)
        return rect.collidelist(aux) == -1
    if gate == 7:
         # Ignora el rectángulo vertical izquierdo.
        aux = border.copy()
        aux.pop(0)
        return rect.collidelist(aux) == -1
    if gate == 8:
         # Ignora los rectángulos pequeños superiores derechos.
        aux = border.copy()
        aux.pop(4)
        aux.pop(5)
        return rect.collidelist(aux) == -1

    """
    rect1 = pygame.Rect((0, 0), (width // 6, height)) # Sexto vertical izquierdo de la pantalla
    rect2 = pygame.Rect((width * 5 // 6, 0), (width // 6, height)) # Sexto vertical derecho de la pantalla
    rect3 = pygame.Rect((0, 0), (width, height // 6)) # Sexto horizontal superior de la pantalla
    rect4 = pygame.Rect((0, height * 5 // 6), (width, height // 6)) # Sexto horizontal inferior de la pantalla
    rect5 = pygame.Rect((width // 6, height // 6), (width // 12, height // 6)) # Esquina superior izquierda, rectángulo vertical
    rect6 = pygame.Rect((width * 3 // 12, height // 6), (width // 12, height // 12)) # Esquina superior izquierda, rectángulo horizontal
    rect7 = pygame.Rect((width * 9 // 12, height // 6), (width // 12, height // 6)) # Esquina superior derecha, rectángulo vertical
    rect8 = pygame.Rect((width * 4 // 6, height // 6), (width // 12, height // 12)) # Esquina superior derecha, cuadrado
    rect9 = pygame.Rect((width // 6, height * 9 // 12), (width // 12, height // 12)) # Esquina inferior izquierda
    rect10 = pygame.Rect((width * 9 // 12, height * 9 // 12), (width // 12, height // 12)) # Esquina inferior derecha
    border = [rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8, rect9, rect10] # Uníon de los rectángulos
    """

def clearConsole():
    print("\n" * 20)
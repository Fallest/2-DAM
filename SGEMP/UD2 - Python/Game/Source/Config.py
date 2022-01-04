import random
import pygame
from pygame import Rect
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

"""     Ventana     """
# Tamaño de la ventana
width, height = get_monitors()[0].width, get_monitors()[0].height
# Ventana
screen = pygame.display
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

# Fuentes para el texto
pygame.font.init()
titleFont = pygame.font.Font("../Data/font.ttf", height // 3)
titleFontSurface = titleFont.render("D0DCH!", False, blue)
pauseFontSurface = titleFont.render("PAUSA", False, blue)
deathFontSurface = titleFont.render("HAS MUERTO", False, red)
buttonFont = pygame.font.Font("../Data/font.ttf", height // 12)

# Ajustamos la imagen de fondo al tamaño del monitor
bgMainTitle = pygame.transform.scale(bgMainTitle, (width, height))
bgGame = pygame.transform.scale(bgGame, (width, height))

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
    health: int
    stamina: int
    pos: tuple[int, int]
    speed: tuple[int, int]
    alive: bool

    # La hitbox del jugador es un rectángulo de 1/20 de la longitud de la pantalla
    # En un principio la hitbox está colocada en el centro de la pantalla, por lo que las cordenadas
    # serían (x, y) = ()
    playerHitBox = pygame.Rect((width // 2-(((1/20)*width) // 2)),
                               (height // 2-(((1/20)*width) // 2)),
                               (1/20)*width,
                               (1/20)*width)

    def __init__(self):
        self.health = 100
        self.stamina = 100
        self.pos = (width // 2, height // 2)
        self.speed = (0, 0)
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


    def moveUp(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0], self.speed[1] + v)
    def moveDown(self, v):
        if self.alive and self.stamina > 0:
            self.speed = (self.speed[0], self.speed[1] - v)
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

            # Si se ha movido el jugador, se quita 1 de SP.
            if self.pos[0] != newXpos or self.pos[1] != newYpos:
                self.updateSP(-1)
            # Si la SP es 0, se para al jugador.
            if self.stamina == 0:
                self.stop()

            self.pos = (newXpos, newYpos)
            clearConsole()
            print("HP: ", self.health)
            print("SP: ", self.stamina)
            print(self.pos)

    def getPos(self):
        return self.pos

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
    projectileHitBox: Rect
    speed: tuple[int, int]
    gate: int
    dir: int

    # La altura y anchura del rectángulo que formará la hitbox del proyectil.
    pWidth = (1 / 40) * width
    pHeight = (1 / 40) * width

    def __init__(self, projSpeed):
        self.gate = random.randint(1, 8)
        self.dir = random.randint(1, 3)
        self.speed = self.assignSpeed(projSpeed)
        self.projectileHitBox = self.assignInitialPos()

    # sqrt(x**2 + y**2) = projSpeed
    def assignSpeed(self, projSpeed):
        # La velocidad dada es siempre positiva, puesto que viene de la función Game.assignProjectileSpeed().
        # Puerta 1
        if self.gate == 1:
            if self.dir == 1:
                return ((projSpeed // 2), -(projSpeed // 2))
            if self.dir == 2:
                return (0, -projSpeed)
            if self.dir == 3:
                return (-(projSpeed // 2), -(projSpeed // 2))
        # Puerta 2
        elif self.gate == 2:
            if self.dir == 1:
                return (0, -projSpeed)
            if self.dir == 2:
                return (-(projSpeed // 2), -(projSpeed // 2))
            if self.dir == 3:
                return (-(projSpeed), 0)
        # Puerta 3
        elif self.gate == 3:
            if self.dir == 1:
                return (-(projSpeed // 2), -(projSpeed // 2))
            if self.dir == 2:
                return (-(projSpeed), 0)
            if self.dir == 3:
                return (-(projSpeed // 2), (projSpeed // 2))
        # Puerta 4
        elif self.gate == 4:
            if self.dir == 1:
                return (-(projSpeed), 0)
            if self.dir == 2:
                return (-(projSpeed // 2), (projSpeed // 2))
            if self.dir == 3:
                return (0, (projSpeed))
        # Puerta 5
        elif self.gate == 5:
            if self.dir == 1:
                return (-(projSpeed // 2), (projSpeed // 2))
            if self.dir == 2:
                return (0, (projSpeed))
            if self.dir == 3:
                return ((projSpeed // 2), (projSpeed // 2))
        # Puerta 6
        elif self.gate == 6:
            if self.dir == 1:
                return (0, projSpeed)
            if self.dir == 2:
                return ((projSpeed // 2), (projSpeed // 2))
            if self.dir == 3:
                return (projSpeed, 0)
        # Puerta 7
        elif self.gate == 7:
            if self.dir == 1:
                return ((projSpeed // 2), (projSpeed // 2))
            if self.dir == 2:
                return (projSpeed, 0)
            if self.dir == 3:
                return ((projSpeed // 2), -(projSpeed // 2))
        # Puerta 8
        elif self.gate == 8:
            if self.dir == 1:
                return (projSpeed, 0)
            if self.dir == 2:
                return ((projSpeed // 2), -(projSpeed // 2))
            if self.dir == 3:
                return (0, -(projSpeed))

    def updatePos(self):
        """
        Actualiza la posición del proyectil.
        """
        self.projectileHitBox = self.projectileHitBox.move(self.speed[0], self.speed[1])
        print("Proyectil: ", self.projectileHitBox.centerx, ", ", self.projectileHitBox.centery)

        # Aplicamos un límite a los projectiles
        if self.projectileHitBox.centerx > width or self.projectileHitBox.centerx < 0:
            return None
        elif self.projectileHitBox.centery > height or self.projectileHitBox.centery < 0:
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

def clearConsole():
    print("\n" * 20)
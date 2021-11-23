"""
Clase principal, para ejecutar el programa.
Va a mostrar el menú principal, junto con una imagen de fondo.
"""
import pygame, sys
import game, config

mainClock = pygame.time.Clock()
from pygame.locals import *

# Inicializamos pygame
pygame.init()

# ------------- FUNCIONES
load_audio = pygame.mixer.music.load()
play_audio = pygame.mixer.music.play()
pause_audio = pygame.mixer.music.pause()
unpause_audio = pygame.mixer.music.unpause()
rewind_audio = pygame.mixer.music.rewind()

# Nombre de la ventana
pygame.display.set_caption(config.game_name)

# Superficie para la ventana
screen = pygame.display.set_mode((config.width, config.height), 0, 32, 0, 1)

# Imagen de fondo del menu
bg_image = pygame.image.load(config.bg_image_file)

def fill_gradient(surface, color, gradient, rect=None, vertical=True, forward=True):
    """
    Cubre una superficie con un gradiente
    color -> color inicial
    gradient -> color final
    rect -> area a llenar; de forma predeterminada se toma el rect de la superficie dada
    vertical -> True=vertical; False=horizontal
    forward -> True=forward; False=reverse

    Pygame recipe: http://www.pygame.org/wiki/GradientCode
    """
    if rect is None: rect = surface.get_rect()
    x1, x2 = rect.left, rect.right
    y1, y2 = rect.top, rect.bottom
    if vertical:
        h = y2 - y1
    else:
        h = x2 - x1
    if forward:
        a, b = color, gradient
    else:
        b, a = color, gradient
    rate = (
        float(b[0] - a[0]) / h,
        float(b[1] - a[1]) / h,
        float(b[2] - a[2]) / h
    )
    fn_line = pygame.draw.line
    if vertical:
        for line in range(y1, y2):
            color = (
                min(max(a[0] + (rate[0] * (line - y1)), 0), 255),
                min(max(a[1] + (rate[1] * (line - y1)), 0), 255),
                min(max(a[2] + (rate[2] * (line - y1)), 0), 255)
            )
            fn_line(surface, color, (x1, line), (x2, line))
    else:
        for col in range(x1, x2):
            color = (
                min(max(a[0] + (rate[0] * (col - x1)), 0), 255),
                min(max(a[1] + (rate[1] * (col - x1)), 0), 255),
                min(max(a[2] + (rate[2] * (col - x1)), 0), 255)
            )
            fn_line(surface, color, (col, y1), (col, y2))

def draw_rect(surface, color, rect, font, text, size = "small"):
    """
    Dibuja un rectángulo en una superficie, poniendo un texto encima del rectángulo.
    :param surface: Superficie para poner el rectángulo
    :param color: Color del rectángulo
    :param rect: Rectángulo a dibujar
    :param font: Fuente del texto a escribir sobre el rectángulo
    :param text: Texto a escribir en el rectángulo
    :param size: Tamaño del rectángulo (para los botones)
    :return: None
    """
    if size == "small":
        pygame.draw.rect(surface, color, rect, 13, 13)
    elif size == "big":
        pygame.draw.rect(surface, color, rect, 15, 15)

    text_surf = font.render(text, 1, config.white)
    rect = text_surf.get_rect(center=rect.center)
    screen.blit(text_surf, rect)

def draw_text(text, font, color, surface, x, y):
    """
    Escribe un texto en la superficie dada.
    Concretamente escribe un texto dentro de un rectángulo dentro de una superficie.
    :param text: Texto a escribir
    :param font: Fuente del texto
    :param color: Color del texto
    :param surface: Superficie sobre la que escribir
    :param x: Coordenada X para poner el texto.
    :param y: Coordenada Y para poner el texto.
    """
    # Renderizamos el texto en una fuente (textObj)
    textObj = font.render(text, 1, color)
    # Obtenemos el rectángulo de la fuente
    textRect = textObj.get_rect()
    # Colocamos la esquina superior izquierda del rectángulo del texto en la superficie
    textRect.topleft = (x, y)
    # Y dibujamos el texto en en el rectángulo
    surface.blit(textObj, textRect)

def main_menu():
    load_audio(config.music_file)
    if config.audio: play_audio(-1)

    config.click = False
    while True:
        # Llena la pantalla del color negro
        screen.fill(config.black)
        # Escribe el texto del menú en la pantalla
        draw_text("Menú principal", config.menu_font, config.white, screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj XD
        mx, my = pygame.mouse.get_pos()

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        play_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, config.blue, play_button_rect, config.button_font, "Jugar")

        options_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, config.blue, options_button_rect, config.button_font, "Opciones")

        exit_button_rect = pygame.Rect(405, 375, 75, 26)
        draw_rect(screen, config.blue, exit_button_rect, config.button_font, "Salir")

        if play_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = play_button_rect
            aux_rect.update(403, 272, 79, 30)
            draw_rect(screen, config.clear_blue, aux_rect, config.button_font, "Jugar", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Jugar"
                show_game()

        if options_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = options_button_rect
            aux_rect.update(403, 322, 79, 30)
            draw_rect(screen, config.clear_blue, aux_rect, config.button_font, "Opciones", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Opciones"
                show_options()

        if exit_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = exit_button_rect
            aux_rect.update(403, 372, 79, 30)
            draw_rect(screen, config.clear_blue, aux_rect, config.button_font, "Salir", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Salir"
                pygame.quit()
                sys.exit()


        # Variable para saber si se ha hecho clic en un tick del reloj
        config.click = False
        # Trato de eventos
        for event in pygame.event.get():
            # Si se presiona la X de la ventana
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            # Si se presiona ESC
            if event.type == KEYDOWN:
                if event.key == K_ESCAPE:
                    pygame.quit()
                    sys.exit()
            # Si se ha hecho clic
            if event.type == MOUSEBUTTONDOWN:
                if event.button == 1:
                    config.click = True

        # Actualizamos la pantalla
        pygame.display.update()
        # Avanzamos un tick del reloj
        mainClock.tick(60)

def show_options():
    config.click = False
    while True:
        if not config.audio: pause_audio()

        screen.fill(config.black)
        # Escribe el texto de las opciones en la pantalla
        draw_text("Opciones", config.menu_font, config.white, screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj
        mx, my = pygame.mouse.get_pos()

        # Variable para el texto del botón del audio
        audio_str = "On" if config.audio else "Off"

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        audio_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, config.blue if config.audio else config.red, audio_button_rect,
                  config.button_font, "Audio: " + audio_str)

        back_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, config.blue, back_button_rect, config.button_font, "Atrás")

        if audio_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = audio_button_rect
            aux_rect.update(403, 272, 79, 30)
            audio_str = "On" if config.audio else "Off"
            draw_rect(screen, config.clear_blue if config.audio else config.clear_red, aux_rect,
                      config.button_font, "Audio: " + audio_str, "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Audio"
                # Se cambia el estado del audio, y si se ha vuelto a poner, se reinicia la música
                config.switch_audio()
                if config.audio:
                    rewind_audio()
                    unpause_audio()

        if back_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = back_button_rect
            aux_rect.update(403, 322, 79, 30)
            draw_rect(screen, config.clear_blue, aux_rect, config.button_font, "Atrás", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Atrás"
                break

        # Variable para saber si se ha hecho clic en un tick del reloj
        config.click = False
        # Trato de eventos
        for event in pygame.event.get():
            # Si se presiona la X de la ventana
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            # Si se presiona ESC
            if event.type == KEYDOWN:
                if event.key == K_ESCAPE:
                    break
            # Si se ha hecho clic
            if event.type == MOUSEBUTTONDOWN:
                if event.button == 1:
                    config.click = True

        # Actualizamos la pantalla
        pygame.display.update()
        # Avanzamos un tick del reloj
        mainClock.tick(60)

def show_game():
    game.start()

if __name__ == "__main__":
    main_menu()
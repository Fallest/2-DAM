"""
Clase principal, para ejecutar el programa.
Va a mostrar el menú principal, junto con una imagen de fondo.
"""
import pygame, sys
import game, config

mainClock = pygame.time.Clock()
from pygame.locals import *

# Variables
w, h = 500, 500

# Inicializamos pygame
pygame.init()

# Nombre de la ventana
pygame.display.set_caption("NoName Game")

# Ventana
screen = pygame.display.set_mode((w, h), 0, 32)

# Fuente a usar en el texto
menu_font = pygame.font.SysFont("Centaur", 20)
button_font = pygame.font.SysFont("Papyrus", 15)

# Imagen de fondo del menu
bg_image = pygame.image.load('C:\\Users\\David\\Documents\\2-DAM\\SGEMP\\UD2 - Python\\Game\\Data\\Images\\main_bg.jpg')

def draw_rect(surface, color, rect, font, text, size = "small"):
    if size == "small":
        pygame.draw.rect(surface, color, rect, 13, 13)
    elif size == "big":
        pygame.draw.rect(surface, color, rect, 15, 15)
    text_surf = font.render(text, 1, (255, 255, 255))
    rect = text_surf.get_rect(center=rect.center)
    screen.blit(text_surf, rect)

def draw_text(text, font, color, surface, x, y):
    """
    Escribe un texto en la pantalla dado un texto, la fuente, el color,
    la superficie en la que escribir, y su localización.
    """
    textObj = font.render(text, 1, color)
    textRect = textObj.get_rect()
    textRect.topright = (x, y)
    surface.blit(textObj, textRect)

def main_menu():
    pygame.mixer.music.load("C:\\Users\\David\\Documents\\2-DAM\\SGEMP\\UD2 - Python\\Game\\Data\\Music\\QuinnAndValor.mp3")
    if config.audio: pygame.mixer.music.play(-1)


    click = False
    #screen.blit(bg_image, (0, 0))
    while True:
        # Llena la pantalla del color negro
        screen.fill((0, 0, 0))
        # Escribe el texto del menú en la pantalla
        draw_text("Menú principal", menu_font, (255, 255, 255), screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj
        mx, my = pygame.mouse.get_pos()

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        play_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, (50, 90, 168), play_button_rect, button_font, "Jugar")

        options_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, (50, 90, 168), options_button_rect, button_font, "Opciones")

        exit_button_rect = pygame.Rect(405, 375, 75, 26)
        draw_rect(screen, (50, 90, 168), exit_button_rect, button_font, "Salir")

        if play_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = play_button_rect
            aux_rect.update(403, 272, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, button_font, "Jugar", "big")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Jugar"
                show_game()

        if options_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = options_button_rect
            aux_rect.update(403, 322, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, button_font, "Opciones", "big")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Opciones"
                show_options()

        if exit_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = exit_button_rect
            aux_rect.update(403, 372, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, button_font, "Salir", "big")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Salir"
                pygame.quit()
                sys.exit()


        # Variable para saber si se ha hecho clic en un tick del reloj
        click = False
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
                    click = True

        # Actualizamos la pantalla
        pygame.display.update()
        # Avanzamos un tick del reloj
        mainClock.tick(60)

def show_options():
    click = False
    #screen.blit(bg_image, (0, 0))
    while True:
        if not config.audio: pygame.mixer.music.pause()

        screen.fill((0, 0, 0))
        # Escribe el texto de las opciones en la pantalla
        draw_text("Opciones", menu_font, (255, 255, 255), screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj
        mx, my = pygame.mouse.get_pos()

        # Variable para el texto del botón del audio
        audio_str = "On" if config.audio else "Off"

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        audio_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, (50, 90, 168) if config.audio else (230, 30, 30), audio_button_rect,
                  button_font, "Audio: " + audio_str)

        back_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, (50, 90, 168), back_button_rect, button_font, "Atrás")

        if audio_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = audio_button_rect
            aux_rect.update(403, 272, 79, 30)
            audio_str = "On" if config.audio else "Off"
            draw_rect(screen, (65, 116, 217) if config.audio else (201, 71, 71), aux_rect,
                      button_font, "Audio: " + audio_str, "big")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Audio"
                # Se cambia el estado del audio, y si se ha vuelto a poner, se reinicia la música
                config.switch_audio()
                if config.audio:
                    pygame.mixer.music.rewind()
                    pygame.mixer.music.unpause()

        if back_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = back_button_rect
            aux_rect.update(403, 322, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, button_font, "Atrás", "big")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Atrás"
                break

        # Variable para saber si se ha hecho clic en un tick del reloj
        click = False
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
                    click = True

        # Actualizamos la pantalla
        pygame.display.update()
        # Avanzamos un tick del reloj
        mainClock.tick(60)

def show_game():
    game.start()

if __name__ == "__main__":
    main_menu()
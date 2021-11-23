"""
Clase para ejecutar el juego.
"""
import pygame, sys, config
from pygame.locals import *
from main import *


game_clock = pygame.time.Clock()

def start():
    # Actualizamos la global para jugar
    config.start_game()

    screen.fill((0, 0, 0))
    """
    Para inicializar el juego
    """
    config.click = False
    while config.game_running:
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
                    pause()
                    screen.fill((0, 0, 0))

        # Actualizamos la pantalla
        pygame.display.update()
        # Avanzamos un tick del reloj
        game_clock.tick(60)

def pause():
    config.click = False
    # screen.blit(bg_image, (0, 0))

    while True:
        if not config.audio: pygame.mixer.music.pause()

        screen.fill((71, 188, 201, 100))
        # Escribe el texto de las opciones en la pantalla
        draw_text("Pausa", config.menu_font, (255, 255, 255), screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj
        mx, my = pygame.mouse.get_pos()

        # Variable para el texto del botón del audio
        audio_str = "On" if config.audio else "Off"

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        continue_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, (50, 90, 168), continue_button_rect, config.button_font, "Continuar")

        audio_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, (50, 90, 168) if config.audio else (230, 30, 30), audio_button_rect,
                  config.button_font, "Audio: " + audio_str)

        menu_button_rect = pygame.Rect(405, 375, 75, 26)
        draw_rect(screen, (50, 90, 168), menu_button_rect, config.button_font, "Menú principal")

        if continue_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = continue_button_rect
            aux_rect.update(403, 272, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, config.button_font, "Continuar", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Atrás"
                break

        if audio_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = audio_button_rect
            aux_rect.update(403, 322, 79, 30)
            audio_str = "On" if config.audio else "Off"
            draw_rect(screen, (65, 116, 217) if config.audio else (201, 71, 71), aux_rect,
                      config.button_font, "Audio: " + audio_str, "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Audio"
                config.switch_audio()
                if config.audio:
                    pygame.mixer.music.rewind()
                    pygame.mixer.music.unpause()

        if menu_button_rect.collidepoint((mx, my)):
            # Si el ratón está encima del botón, lo coloreamos más claro y lo hacemos grande
            aux_rect = menu_button_rect
            aux_rect.update(403, 372, 79, 30)
            draw_rect(screen, (65, 116, 217), aux_rect, config.button_font, "Menú principal", "big")
            if config.click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Menú principal"
                config.stop_game()
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


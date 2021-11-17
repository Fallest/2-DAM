"""
Clase principal, para ejecutar el programa.
Va a mostrar el menú principal, junto con una imagen de fondo.
"""
import pygame, sys

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
font = pygame.font.SysFont("Arial", 20)
# Imagen de fondo del menu
bg_image = pygame.image.load('C:\\Users\\David\\Documents\\2-DAM\\SGEMP\\UD2 - Python\\Game\\Data\\Images\\main_bg.jpg')

def draw_rect(surface, color, rect, font, text):
    pygame.draw.rect(surface, color, rect, 13, 13)
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
    click = False
    screen.blit(bg_image, (0, 0))
    while True:
        # Llena la pantalla del color negro
        #screen.fill((0, 0, 0))
        # Escribe el texto del menú en la pantalla
        draw_text("Menú principal", font, (255, 255, 255), screen, 480, 240)

        # Vamos a obtener la posición del mouse en cada tick del reloj
        mx, my = pygame.mouse.get_pos()

        # Creamos los botones de nuestro menú y escribimos texto en ellos
        play_button_rect = pygame.Rect(405, 275, 75, 26)
        draw_rect(screen, (50, 90, 168), play_button_rect, font, "Jugar")

        options_button_rect = pygame.Rect(405, 325, 75, 26)
        draw_rect(screen, (50, 90, 168), options_button_rect, font, "Opciones")

        exit_button_rect = pygame.Rect(405, 375, 75, 26)
        draw_rect(screen, (50, 90, 168), exit_button_rect, font, "Salir")

        if play_button_rect.collidepoint((mx, my)):
            draw_rect(screen, (65, 116, 217), play_button_rect, font, "Jugar")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Jugar"
                show_game()

        if options_button_rect.collidepoint((mx, my)):
            draw_rect(screen, (65, 116, 217), options_button_rect, font, "Opciones")
            if click:
                # Si se ha hecho clic cuando el ratón estaba encima del botón "Opciones"
                show_options()

        if exit_button_rect.collidepoint((mx, my)):
            draw_rect(screen, (65, 116, 217), exit_button_rect, font, "Salir")
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

if __name__ == "__main__":
    main_menu()



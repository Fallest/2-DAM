import pygame, sys

pygame.init()

name_game = "NoName Game" # Nombre del juego
# Audio
audio = True
music_file = "C:\\Users\\David\\Documents\\2-DAM\\SGEMP\\UD2 - Python\\Game\\Data\\Music\\QuinnAndValor.mp3"
# Para jugar
game_running = True
# Click
click = False
# Colores
black = (0, 0, 0)
white = (255, 255, 255)
blue = (50, 90, 168)
clear_blue = (65, 116, 217)
red = (230, 30, 30)
clear_red = (201, 71, 71)
# Variables
width, height = 1080, 720
# Fuentes para el texto
menu_font = pygame.font.SysFont("Centaur", 20)
button_font = pygame.font.SysFont("Papyrus", 15)
# Imagen de fondo
bg_image_file = 'C:\\Users\\David\\Documents\\2-DAM\\SGEMP\\UD2 - Python\\Game\\Data\\Images\\main_bg.jpg'

def switch_audio():
    global audio
    audio = not audio

def stop_game():
    global game_running
    game_running = False

def start_game():
    global game_running
    game_running = True
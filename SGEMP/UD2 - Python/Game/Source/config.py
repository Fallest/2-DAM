# Audio
audio = True
# Para jugar
game_running = True
# Colores
black = (0, 0, 0)
white = (255, 255, 255)
blue = (50, 90, 168)
clear_blue = (65, 116, 217)
red = (230, 30, 30)
clear_red = (201, 71, 71)

def switch_audio():
    global audio
    audio = not audio

def stop_game():
    global game_running
    game_running = False

def start_game():
    global game_running
    game_running = True
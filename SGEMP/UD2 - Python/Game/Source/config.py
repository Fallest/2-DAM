audio = True
game_running = True

def switch_audio():
    global audio
    audio = not audio

def stop_game():
    global game_running
    game_running = False

def start_game():
    global game_running
    game_running = True
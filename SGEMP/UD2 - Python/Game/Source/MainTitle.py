import pygame, Config


def run():
    """
    Pantalla principal.
    En ella se escoge la dificultad del juego (predefinido "Normal").

    Los niveles de dificultad son 1, 2, 3 (fácil, normal, y difícil, respectivamente).
    Si se presiona 5 veces para aumentar la dificultad se selecciona BulletHell (4).

    Si se selecciona "Salir", devuelve 0.
    Si se selecciona "Jugar", devuelve 1.
    """
    # Rellena la pantalla con RGB y añade el bg
    Config.screen.fill(Config.white)
    #Config.screen.blit(Config.bgMainTitle, (0, 0))
    # Selección en la pantalla:
    # 1 - Jugar, 2 - Dificultades, 3 - audio, 4 - Salir
    selection = 1

    while Config.running:
        # Control de eventos
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                Config.running = False
                break

            # Si se presiona una tecla, comprobar cuál es
            if event.type == pygame.KEYDOWN:
                """
                Si se presiona la flecha arriba, se sube (baja) de selección
                """
                if event.key == pygame.K_UP:
                    if selection > 1:
                        selection -= 1;
                """
                Si se presiona la flecha abajo, se baja (sube) de selección
                """
                if event.key == pygame.K_UP:
                    if selection < 4:
                        selection += 1;
                """
                Si se presiona <- y se está seleccionando la dificultad, se baja de dificultad
                """
                if event.key == pygame.K_LEFT:
                    if selection == 2:
                        Config.decreaseDif()
                """
                Si se presiona -> y se está seleccionando la dificultad, se sube de dificultad
                """
                if event.key == pygame.K_RIGHT:
                    if selection == 2 and Config.gameDifficulty > 1:
                        Config.increaseDif()
                """-------------------------------------------------------------------------"""
                """
                Si se presiona "z":
                    -y se está seleccionando "Jugar", se devuelve 1.
                    -y se está seleccionando "Audio", se activa/desactiva el audio.
                    -y se está seleccionando "Salir", se devuelve 0.
                """
                if event.key == pygame.K_z:
                    if selection == 1:
                        return 1
                    if selection == 3:
                        Config.audio = not Config.audio
                    if selection == 4:
                        return 0
import pygame, Config

def run():
    """
    Función para iniciar la pantalla de pausa.
    El bucle del juego se pausa para entrar en esta función.
    Desde ella se puede volver al Menú principal, continuar el juego, activar/desactivar el audio, y salir del juego.

    Si se selecciona "Continuar" o se presiona "ESC", devuelve 2.
    Si se selecciona "Menú principal", devuelve 1.
    Si se selecciona "Salir", devuelve 0.
    """
    print("Ejecutando PauseScreen.run()...")

    # Se colorea la pantalla con una capa semitransparente.
    fade = pygame.Surface((Config.width, Config.height), pygame.SRCALPHA)
    fade.fill(Config.fadedWhite)
    Config.screen.blit(fade, (0, 0))

    # Escribimos el título de la pausa
    Config.screen.blit(Config.pauseFontSurface, (Config.width // 20, Config.height // 20))

    # Variable para la selección
    selection = 1

    # Dibujamos los botones:
    Config.drawButtons(first="Continuar", diff=False, second="Menú principal")

    # Al inicio está seleccionado "Continuar"
    Config.drawSelectedButton(selection, first="Continuar", diff=False, second="Menú principal")

    while Config.running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return 0

            # Si se presiona una tecla, comprobar cuál es
            if event.type == pygame.KEYDOWN:
                """
                Si se presiona "ESC", se continua el juego.
                """
                if event.key == pygame.K_ESCAPE:
                    return 2
                """
                Si se presiona la flecha arriba, se sube (baja) de selección
                """
                if event.key == pygame.K_UP:
                    if selection > 1:
                        Config.drawUnselectedButton(selection, first="Continuar", diff=False, second="Menú principal")
                        selection -= 1
                        Config.drawSelectedButton(selection, first="Continuar", diff=False, second="Menú principal")
                """
                Si se presiona la flecha abajo, se baja (sube) de selección
                """
                if event.key == pygame.K_DOWN:
                    if selection < 4:
                        Config.drawUnselectedButton(selection, first="Continuar", diff=False, second="Menú principal")
                        selection += 1
                        Config.drawSelectedButton(selection, first="Continuar", diff=False, second="Menú principal")
                """-------------------------------------------------------------------------"""
                """
                Si se presiona "z":
                    -y se está seleccionando "Continuar", se devuelve 2.
                    -y se está seleccionando "Menú principal", se devuelve 1.
                    -y se está seleccionando "Audio", se activa/desactiva el audio.
                    -y se está seleccionando "Salir", se devuelve 0.
                """
                if event.key == pygame.K_z:
                    if selection == 1:
                        return 2
                    if selection == 2:
                        return 1
                    if selection == 3:
                        Config.audio = not Config.audio
                        Config.drawSelectedButton(selection, first="Continuar", diff=False, second="Menú principal")
                    if selection == 4:
                        return 0

        # No olvidemos actualizar el display en cada iteración
        pygame.display.update()


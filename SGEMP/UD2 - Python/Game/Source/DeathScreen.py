import pygame, Config

def run():
    """
    Función para iniciar la pantalla de fin del juego.
    El bucle del juego se pausa para entrar en esta función.
    Desde ella se puede volver al Menú principal, volver a jugar, o salir del juego.

    Si se selecciona "Volver a jugar", devuelve 2.
    Si se selecciona "Menú principal", devuelve 1.
    Si se selecciona "Salir", devuelve 0.
    """
    print("Ejecutando DeathScreen.run()...")

    # Se colorea la pantalla con una capa semitransparente roja oscura.
    fade = pygame.Surface((Config.width, Config.height), pygame.SRCALPHA)
    fade.fill(Config.fadedRed)
    Config.screen.blit(fade, (0, 0))

    # Variable para la selección
    selection = 1

    # Escribimos el título de la pantalla de muerte
    Config.screen.blit(Config.deathFontSurface, (Config.width // 20, Config.height // 20))

    # Dibujamos los botones:
    Config.drawButtons(first="Jugar de nuevo", diff=False, second="Menú principal", death=True)

    # Al inicio está seleccionado "Jugar de nuevo"
    Config.drawSelectedButton(selection, first="Jugar de nuevo", diff=False, second="Menú principal", third=True)

    while Config.running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return 0

            # Si se presiona una tecla, comprobar cuál es
            if event.type == pygame.KEYDOWN:
                """
                Si se presiona la flecha arriba, se sube (baja) de selección
                """
                if event.key == pygame.K_UP:
                    if selection > 1:
                        Config.drawUnselectedButton(selection, first="Jugar de nuevo", diff=False,
                                                  second="Menú principal", third=True)
                        selection -= 1
                        Config.drawSelectedButton(selection, first="Jugar de nuevo", diff=False,
                                                  second="Menú principal", third=True)
                """
                Si se presiona la flecha abajo, se baja (sube) de selección
                """
                if event.key == pygame.K_DOWN:
                    if selection < 3:
                        Config.drawUnselectedButton(selection, first="Jugar de nuevo", diff=False,
                                                  second="Menú principal", third=True)
                        selection += 1
                        Config.drawSelectedButton(selection, first="Jugar de nuevo", diff=False,
                                                  second="Menú principal", third=True)
                """-------------------------------------------------------------------------"""
                """
                Si se presiona "z":
                    -y se está seleccionando "Volver a jugar", devuelve 2.
                    -y se está seleccionando "Menú principal", devuelve 1.
                    -y se está seleccionando "Salir", se devuelve 0.
                """
                if event.key == pygame.K_z:
                    if selection == 1:
                        return 2
                    if selection == 2:
                        return 1
                    if selection == 3:
                        return 0

        # No olvidemos actualizar el display en cada iteración
        pygame.display.update()
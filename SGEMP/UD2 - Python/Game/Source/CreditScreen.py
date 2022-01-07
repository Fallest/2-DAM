import pygame, Config

def run():
    """
    Función para iniciar la pantalla de créditos.
    Desde ella se puede volver al Menú principal.

    Si se presiona "x", se sale de la pantalla de créditos (devuelve 1).
    """
    print("Ejecutando CreditScreen.run()...")

    # Añade el bg con una capa de transparencia encima para oscurecerlo
    Config.screen.fill(Config.black)

    Config.screen.blit(Config.bgMainTitle, (0, 0))

    # Escribimos el título
    Config.screen.blit(Config.titleFontSurface, (Config.width // 20, Config.height // 20))

    # Escribimos los datos
    authorSurf = Config.textFont.render("Autor                 David Bernal Navarrete", False, Config.white)
    creditsSurf = Config.textFont.render("Software utilizado    Neural Composer de CodeParade")
    Config.screen.blit(authorSurf, ((Config.width // 6, Config.height * 3 // 6)))
    Config.screen.blit(creditsSurf, ((Config.width // 6, Config.height * 4 // 6)))

    while Config.running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return 0

            # Si se presiona una tecla, comprobar cuál es
            if event.type == pygame.KEYDOWN:
                """
                Si se presiona "x", se sale.
                """
                if event.key == pygame.K_x:
                    return 1


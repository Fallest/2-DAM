"""
Automatización de Notepad.
"""
from pywinauto.application import Application
from time import sleep

def runNotepad():
    # Iniciamos la aplicación
    app = Application().start('notepad.exe')

    # Creamos la cadena con las instrucciones
    text = "Este programa va a realizar las siguientes acciones:\n"\
           "1. Mostrar la versión de Notepad.\n" \
           "2. Escribir una cita célebre.\n" \
           "3. Tomar una captura del texto y guardarla como captura.png\n" \
           "4. Guardar el texto en texto.txt\n" \
           "5. Cerrar el programa.\n\n"

    cita = "\tEl cielo está destinado a crecer."

    # Escribe las instrucciones
    app.UntitledNotepad.Edit.type_keys(text,
                                       with_spaces=True,
                                       with_newlines=True)

    # 1. Mostrar la versión
    app.UntitledNotepad.menu_select("Ayuda -> Acerca del Bloc de notas")
    sleep(3)
    app.AcercaDelBlocDeNotas.Aceptar.click()

    # 2. Escribir una cita célebre
    sleep(1)
    app.UntitledNotepad.Edit.type_keys(cita,
                                       with_spaces=True,
                                       with_newlines=True,
                                       with_tabs=True)

    # 3. Tomar la captura y guardarla
    sleep(1)
    app.UntitledNotepad.capture_as_image().save('captura.png')

    # 4. Guarda el archivo
    sleep(1)
    app.UntitledNotepad.menu_select("Archivo -> Guardar")
    app.GuardarComo.Edit.type_keys("texto.txt")
    app.GuardarComo.Guardar.click()

    # 5. Cerrar el programa
    app.UntitledNotepad.menu_select("Archivo -> Salir")

#runNotepad()
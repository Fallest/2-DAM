"""
Automatización de Notepad.
"""
from pywinauto.application import Application
from time import sleep

def runNotepad():
    # Iniciamos la aplicación
    app = Application().start('notepad.exe')

    # Creamos la cadena con las instrucciones
    text: list(str) = ["Este programa va a realizar las siguientes acciones:\n",
           "\t1. Mostrar la versión de Notepad.\n" ,
           "\t2. Escribir una cita célebre.\n" ,
           "\t3. Tomar una captura del texto y guardarla como captura.png\n" ,
           "\t4. Guardar el texto en texto.txt\n" ,
           "\t5. Cerrar el programa.\n\n"]

    cita = "\t\tEl cielo está destinado a crecer.\n"

    # 1. Mostrar la versión
    app.UntitledNotepad.Edit.type_keys(text[0],
                                       with_spaces=True,
                                       with_newlines=True)
    app.UntitledNotepad.Edit.type_keys(text[1],
                                       with_spaces=True,
                                       with_newlines=True)
    app.UntitledNotepad.menu_select("Ayuda -> Acerca del Bloc de notas")
    sleep(3)
    app.AcercaDelBlocDeNotas.Aceptar.click()


    # 2. Escribir una cita célebre
    app.UntitledNotepad.Edit.type_keys(text[2],
                                       with_spaces=True,
                                       with_newlines=True)
    sleep(2)
    app.UntitledNotepad.Edit.type_keys(cita,
                                       with_spaces=True,
                                       with_newlines=True,
                                       with_tabs=True)

    # 3. Tomar la captura y guardarla
    app.UntitledNotepad.Edit.type_keys(text[3],
                                       with_spaces=True,
                                       with_newlines=True)
    sleep(2)
    app.UntitledNotepad.capture_as_image().save('captura.png')

    # 4. Guarda el archivo
    app.UntitledNotepad.Edit.type_keys(text[4],
                                       with_spaces=True,
                                       with_newlines=True)
    sleep(2)
    app.UntitledNotepad.menu_select("Archivo -> Guardar")
    app.GuardarComo.Edit.type_keys("texto.txt")
    app.GuardarComo.Guardar.click()

    # 5. Cerrar el programa
    app.UntitledNotepad.Edit.type_keys(text[5],
                                       with_spaces=True,
                                       with_newlines=True)
    sleep(2)
    app.UntitledNotepad.menu_select("Archivo -> Guardar")
    app.UntitledNotepad.menu_select("Archivo -> Salir")

runNotepad()
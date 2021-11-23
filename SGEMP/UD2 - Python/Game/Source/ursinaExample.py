from ursina import *

class Test_cube(Entity):
    def __init__(self):
        super().__init__(
            model = 'cube',
            color = color.white,
            texture = 'white_cube',
            rotation = Vec3(45, 45, 45)
        )

class Test_button(Button):
    def __init__(self):
        super().__init__(
            parent = scene,
            model = 'cube',
            texture = 'brick',
            color = color.blue,
            highlight_color = color.red,
            pressed_color = color.lime
        )

    def input(self, key):
        if self.hovered:
            if key == 'left mouse down':
                print('Botton pressed')

# Cargar texturas
sans_texture = load_texture('assests/Sans.png')

def update():
    """
    Función llamada por Ursina en cada frame.
    """
    if held_keys['a']:
        text_square.x -= 1 * time.dt # Para que se mueva copn el framerate le ponemos time.delta
    if held_keys['d']:
        text_square.x += 1 * time.dt # Para que se mueva copn el framerate le ponemos time.delta
    if held_keys['w']:
        text_square.y += 1 * time.dt # Para que se mueva copn el framerate le ponemos time.delta
    if held_keys['s']:
        text_square.y -= 1 * time.dt # Para que se mueva copn el framerate le ponemos time.delta

app = Ursina()

text_square = Entity(model = 'quad', color = color.red, scale = (1, 1), position = (0, 0))

#sans = Entity(model = 'quad', texture = sans_texture)

#test_cube = Test_cube()
test_button = Test_button()

app.run()
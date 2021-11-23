from ursina import *

class Voxel(Button):
    def __init__(self, pos = (0, 0, 0)):
        super().__init__(
            parent = scene,
            position = pos,
            model = 'cube',
            origin_y = 0.5,
            texture = 'white_cube',
            color = color.white,
            highlight_color = color.lime
        )

app = Ursina()

for z in range(8):
    for x in range(8):
        voxel = Voxel((x, 0, z))

app.run()
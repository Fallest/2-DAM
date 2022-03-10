from odoo import fields, models

class Pasajero(models.Model):
    _name = "sgemp.pasajero"
    _description = "Modelo para los pasajeros."

    name = fields.Text(required=True, default="Doe")
    surname = fields.Text()
    age = fields.Integer(readonly=True, default=25)
    luggage_weight = fields.Float(required=True, default=50)

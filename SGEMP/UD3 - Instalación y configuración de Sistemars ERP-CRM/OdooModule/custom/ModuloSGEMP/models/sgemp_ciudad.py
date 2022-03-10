from odoo import fields, models

class Ciudad(models.Model):
    _name = "sgemp.ciudad"
    _description = "Modelo para las ciudades."

    name = fields.Text(required=True)
    country = fields.Text(required=True)
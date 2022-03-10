from odoo import fields, models

class Aerolinea(models.Model):
    _name = "sgemp.aerolinea"
    _description = "Modelo para las aerolíneas."

    name = fields.Text(required=True)
    planes = fields.Integer(required=True, default=2)
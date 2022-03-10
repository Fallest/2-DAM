from odoo import fields, models

class TestModel(models.Model):
    _name = "test.model"
    _description = "Modelo de ejemplo."

    name = fields.Text(required=True)
    planes = fields.Integer(required=True)
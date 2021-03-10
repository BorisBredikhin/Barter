from django.contrib import admin
from barter import models

# Register your models here.
admin.site.register(models.Category)
admin.site.register(models.Profile)
admin.site.register(models.Task)
admin.site.register(models.Rating)
admin.site.register(models.TaskAddress)
admin.site.register(models.UserAddress)
admin.site.register(models.Tag)
admin.site.register(models.Review)
from django.contrib import admin

# Register your models here.
from token_auth.models import Token

admin.site.register(Token)

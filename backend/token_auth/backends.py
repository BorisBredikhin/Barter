from django.contrib.auth import get_user_model
from django.contrib.auth.backends import BaseBackend
from .models import Token

class TokenAuthBackend(BaseBackend):
    def authenticate(self, request, token=None):
        try:
            return Token.objects.get(key=token).user
        except Token.DoesNotExist:
            return None

    def get_user(self, user_id):
        try:
            return get_user_model().objects.get(pk=user_id)
        except get_user_model().DoesNotExist:
            return None
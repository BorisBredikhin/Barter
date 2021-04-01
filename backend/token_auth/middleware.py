from asgiref.sync import sync_to_async
from django.contrib.auth import authenticate
from django.http import HttpResponseBadRequest
from fastapi import Request

from token_auth.models import Token


class TokenMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        auth_header = str(request.META.get('HTTP_AUTHORIZATION', '')).partition(' ')

        if auth_header[0].lower() != 'token':
            return None

        # If they specified an invalid token, let them know.
        if not auth_header[2]:
            return HttpResponseBadRequest("Improperly formatted token")

        user = authenticate(token=auth_header[2])
        if user:
            request.profile = user

        return self.get_response(request)


async def TokenAuth(request: Request):
    name, token = request.headers["Authorization"].split()
    def get_prof(token):
        return Token.objects.get(key=token).profile
    return await sync_to_async(get_prof)(token)

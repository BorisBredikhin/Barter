from django.contrib.auth import authenticate
from django.http import HttpResponseBadRequest


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
            request.user = user

        return self.get_response(request)

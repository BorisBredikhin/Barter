from django.contrib.auth import authenticate
from fastapi import APIRouter, FastAPI

from token_auth.models import Token
from token_auth.schemas import LoginSchema

app = APIRouter()

@app.post('/login/')
def login(data: LoginSchema):
    user = authenticate(username=data.username, password=data.password)

    if user is not None:
        token = Token.objects.get(user_id=user.pk)
        # token.save()
        return {'token': token.key, 'message': 'ok'}
    else:
        return {'message': 'wrong username or password'}

import hashlib

from fastapi import APIRouter, FastAPI

from barter.models import Profile
from token_auth.models import Token
from token_auth.schemas import LoginSchema

app = APIRouter()

@app.post('/login/')
def login(data: LoginSchema):
    user = Profile.objects.get(password = hashlib.sha1(data.password.encode("utf-8")).hexdigest())

    if user is not None:
        token = Token.objects.get(user_id=user.pk)
        # token.save()
        return {'token': token.key, 'message': 'ok'}
    else:
        return {'message': 'wrong username or password'}

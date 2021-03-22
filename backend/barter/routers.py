from fastapi import APIRouter

from barter import models, schemas
from token_auth.routers import app

router = APIRouter()

@router.get('/hello')
async def hello():
    return {'msg': 'hello from django fastapi'}

@router.post(
    '/register/',
    name='register',
    summary='register new user'
)
def register(data: schemas.RegisterSchema):
    user = models.User.objects.create_user(username=data.username, password=data.password, first_name=data.first_name, last_name=data.last_name)
    user.save()

    profile = models.Profile.objects.get(pk=user.pk)
    profile.birthday = data.birth_day
    profile.primary_activity = data.primary_activity
    profile.phone_number = data.phone_number
    profile.save()

    return {
        'message': 'user created succesfully',
        'pk': user.pk
    }

router.include_router(app, prefix="/auth")
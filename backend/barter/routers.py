from fastapi import APIRouter, Request, Depends
import hashlib

from barter import models, schemas
from barter.models import Profile, Rating
from token_auth.middleware import TokenAuth
from token_auth.routers import app

router = APIRouter()


@router.get('/hello')
async def hello():
    return {'msg': 'hello from django fastapi'}


@router.post(
    '/register/',
    name='register',
    summary='register new profile'
)
def register(data: schemas.RegisterSchema):
    profile = models.Profile()
    profile.username = data.username
    profile.password = hashlib.sha1(data.password.encode("utf-8")).hexdigest()
    profile.first_name = data.first_name
    profile.last_name = data.last_name
    profile.birthday = data.birth_day
    profile.primary_activity = data.primary_activity
    profile.phone_number = data.phone_number
    profile.save()

    return {
        'message': 'profile created succesfully',
        'pk': profile.pk
    }


@router.get("/profile/", response_model=schemas.ProfileSchema)
def self_profile_view(request: Request, user: Profile = Depends(TokenAuth)):
    rating = Rating.get(user)
    return {
        "first_name": user.first_name,
        "last_name": user.last_name,
        "username": user.username,
        "primary_activity": user.primary_activity,
        "points": user.points,
        "rating_as_executor": rating.rating_as_executor,
        "rating_as_customer": rating.rating_as_customer,
    }


router.include_router(app, prefix="/auth")
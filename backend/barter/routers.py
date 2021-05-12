from asgiref.sync import sync_to_async
from django.db.models import Q
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


@router.get("/tasks/", response_model=schemas.TaskLstSchema)
def get_tasks(request: Request, user: Profile = Depends(TokenAuth)):
    return {
        "tasks": list(map(
            lambda x: x.to_dict,
            models.Task.objects.filter(
                ~Q(customer_id=user.pk),
                executor=None,
                status=models.task_statuses[0] # только новые заказы
            )
        ))
    }

@router.post("/tasks/new")
async def new_task(request: Request,
                   data: schemas.NewTaskSchema,
                   user: Profile = Depends(TokenAuth)):
    if True:
        def helper():
            obj = models.Task.objects.create(
                customer=user,
                title=data.title,
                description=data.description,
                price=data.price,
                category=models.Category.objects.get(title=data.category) if not data.category is None else None,
                address_str=data.address,
                status=models.task_statuses[0],
            )
            obj.save()
            return obj
        obj = await sync_to_async(helper)()

        # todo: notify users about new task

        return {
            "status": "ok",
            "task": obj.to_dict()
        }
    # except Exception:
    #     print(Exception)
    #     return {
    #         "status": "error"
    #     }


router.include_router(app, prefix="/auth")
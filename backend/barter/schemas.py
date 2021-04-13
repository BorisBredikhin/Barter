from pydantic import BaseModel
from pydantic_django import ModelSchema

from . import models


class ProfileSchema(BaseModel):
    first_name: str
    last_name: str
    username: str
    primary_activity: str
    points: int
    rating_as_customer: float
    rating_as_executor: float


class CategorySchema(ModelSchema):
    class Config:
        model = models.Category


class UserAddressSchema(ModelSchema):
    class Config:
        model = models.UserAddress


class RatingSchema(ModelSchema):
    class Config:
        model = models.Rating


class TaskSchema(ModelSchema):
    class Config:
        model = models.Task


class TagSchema(ModelSchema):
    class Config:
        model = models.Tag


class TaskAddressSchema(ModelSchema):
    class Config:
        model = models.TaskAddress


class ReviewSchema(ModelSchema):
    class Config:
        model = models.Review


class RegisterSchema(BaseModel):
    first_name: str
    last_name: str
    username: str
    birth_day: str
    primary_activity: str
    phone_number: str
    password: str
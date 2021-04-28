from pydantic import BaseModel
from pydantic_django import ModelSchema
from typing import List, Optional

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
    customer: int
    executor: Optional[int]
    title: str
    description: str
    price: int
    status: str


class TaskLstSchema(BaseModel):
    tasks: List[TaskSchema]

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

class NewTaskSchema(BaseModel):
    title: str
    description: str
    price: int
    category: Optional[str]

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

class NewTaskSchema(BaseModel):
    customer_id: int
    title: str
    description: str
    price: str
    category: Optional[str]
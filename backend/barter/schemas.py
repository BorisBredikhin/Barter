from pydantic_django import ModelSchema

from . import models


class UserSchema(ModelSchema):
    class Config:
        model = models.User
        exclude = ['password']


class ProfileSchema(ModelSchema):
    class Config:
        model = models.Profile


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

import json

from django.conf import settings
from django.contrib.auth import get_user_model
from django.contrib.auth.models import User
from django.test import TransactionTestCase
from fastapi.testclient import TestClient

from backend.asgi import app
from barter.models import Profile

reverse = app.router.url_path_for


class RegisterTest(TransactionTestCase):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.c = TestClient(app)

    def setUp(self) -> None:
        Profile(
            username="john",
            password="e9c1240161604e16a32ded612ba0c91b9b751fe0",
            first_name="john",
            last_name="pwdnj"
        ).save()


    def test_register_without_photo(self):
        response = self.c.post('api/register/', headers={"X-Token": "ikikjm"}, data=json.dumps({
            'first_name': 'Demo',
            'last_name': 'User',
            'username': 'demouser',
            'birth_day': '1992-02-17',
            'primary_activity': 'programming',
            'phone_number': '+0123456789',
            'password': 'P@ssw0rd'}))

        self.assertEqual(response.json()['message'], 'profile created succesfully')

    def test_login_get_token(self):
        response = self.c.post('api/auth/login/', headers={"X-Token": "ikikjm"}, data=json.dumps({
            "username": "john",
            "password": "johnpassword"
        }))

        response_json = response.json()

        self.assertTrue(len(response_json['token'])>0)

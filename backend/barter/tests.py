import json

from django.conf import settings
from django.contrib.auth import get_user_model
from django.contrib.auth.models import User
from django.test import TransactionTestCase
from fastapi.testclient import TestClient

from backend.asgi import app

reverse = app.router.url_path_for


class RegisterTest(TransactionTestCase):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.c = TestClient(app)

    def setUp(self) -> None:
        User\
            .objects\
            .create_user('john', 'lennon@thebeatles.com', 'johnpassword')\
            .save()


    def test_register_without_photo(self):
        response = self.c.post('api/register/', headers={"X-Token": "ikikjm"}, data=json.dumps({
            'first_name': 'Demo',
            'last_name': 'User',
            'username': 'demouser',
            'birth_day': '1992-02-17',
            'primary_activity': 'programming',
            'phone_number': '+0123456789',
            'password': 'P@ssw0rd'}))

        self.assertEqual(response.json()['message'], 'user created succesfully')

    def test_login_get_token(self):
        response = self.c.post('api/auth/login/', headers={"X-Token": "ikikjm"}, data=json.dumps({
            "username": "john",
            "password": "johnpassword"
        }))

        response_json = response.json()

        self.assertTrue(len(response_json['token'])>0)

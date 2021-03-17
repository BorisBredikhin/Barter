import json

from django.test import TransactionTestCase
from fastapi.testclient import TestClient

from backend.asgi import app

reverse = app.router.url_path_for


class RegisterTest(TransactionTestCase):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.c = TestClient(app)

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

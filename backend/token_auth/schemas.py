from pydantic.main import BaseModel


class LoginSchema(BaseModel):
    username: str
    password: str
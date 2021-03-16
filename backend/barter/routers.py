from fastapi import APIRouter

# from barter import views, schemas

router = APIRouter()

@router.get('/hello')
async def hello():
    return {'msg': 'hello from django fastapi'}

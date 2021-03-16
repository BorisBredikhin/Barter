import uvicorn

if __name__ == '__main__':
    uvicorn.run('backend.asgi:app', reload=True)
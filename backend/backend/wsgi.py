"""
WSGI config for backend project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/3.1/howto/deployment/wsgi/
"""

import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")


import os

from django.core.wsgi import get_wsgi_application
from fastapi import FastAPI

from barter import routers

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'backend.settings')

application = get_wsgi_application()


app = FastAPI(
    title="Barter",
    description="Backend application for Barter project",
    version="0.0.0"
)

app.include_router(routers.router, prefix="/api")

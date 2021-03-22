from django.conf import settings
from django.db import models
import secrets

class Token(models.Model):
    key = models.CharField(max_length=64, primary_key=True)
    user = models.OneToOneField(settings.AUTH_USER_MODEL, related_name="token", on_delete=models.CASCADE)
    created = models.DateTimeField(auto_now_add=True)

    def save(self, *args, **kwargs):
        if not self.key:
            self.key = self.generate_key()
        return super(Token, self).save(*args, **kwargs)

    def generate_key(self):
        return secrets.token_hex(64)

    def __unicode__(self):
        return self.key

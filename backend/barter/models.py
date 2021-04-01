from django.db import models

from barter import validators


class Profile(models.Model):
    username = models.CharField(max_length=100)
    password = models.CharField(max_length=256)
    first_name = models.CharField(max_length=256)
    last_name = models.CharField(max_length=256)

    photo = models.ImageField(null=True)
    birthday = models.DateField(null=True)
    primary_activity = models.CharField(max_length=150, null=True)
    phone_number = models.IntegerField(null=True)
    points = models.IntegerField(validators=[validators.non_negative_validator], default=0)

    def __str__(self):
        return self.first_name + ' ' + self.last_name

class Category(models.Model):
    title = models.CharField(max_length=50)

    def __str__(self):
        return self.title


class Address(models.Model):
    country = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    street = models.CharField(max_length=50)
    house = models.CharField(max_length=6) # могут быть номера домов вида 20а, 21/8
    room = models.IntegerField()

    latitude = models.FloatField(null=True, blank=True)
    longitude = models.FloatField(null=True, blank=True)

    class Meta:
        abstract = True


class UserAddress(Address):
    user = models.OneToOneField(
        Profile,
        on_delete=models.CASCADE,
        primary_key=True,
    )


class Rating(models.Model):
    user = models.OneToOneField(
        Profile,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    rating_as_customer = models.FloatField(default=5.0)
    rating_as_executor = models.FloatField(default=5.0)

    @classmethod
    def get(self, profile: Profile):
        try:
            return self.objects.get(user=profile)
        except self.DoesNotExist:
            rating = self.objects.create()
            rating.user = profile
            rating.save()
            return rating

task_statuses = [
    ("Новый", "Новый"),
    ("Ожидает подтверждение", "Ожидает подтверждение"),
    ("Подтверждён", "Подтверждён"),
    ("Исполнен", "Исполнен"),
]

class Task(models.Model):
    customer = models.ForeignKey(Profile, on_delete=models.PROTECT, related_name="customer")
    executor = models.ForeignKey(Profile, on_delete=models.PROTECT, related_name="executor")
    title = models.CharField(max_length=50)
    description = models.TextField()
    price = models.IntegerField(validators=[validators.non_negative_validator])
    status = models.CharField(max_length=21, choices=task_statuses)
    category = models.ForeignKey('Category', on_delete=models.SET_NULL, null=True)


class Tag(models.Model):
    task = models.ManyToManyField(Task)
    name = models.CharField(max_length=50)


class TaskAddress(Address):
    task = models.OneToOneField(
        Task,
        on_delete=models.CASCADE,
        primary_key=True,
    )

class Review(models.Model):
    from_user = models.ForeignKey(Profile, on_delete=models.RESTRICT, related_name="from_user")
    to_user = models.ForeignKey(Profile, on_delete=models.RESTRICT, related_name="to_user")
    task = models.ForeignKey(Task, on_delete=models.RESTRICT)
    review_date = models.DateTimeField()
    review_text = models.TextField()
    status_to = models.CharField(max_length=21, choices=task_statuses)

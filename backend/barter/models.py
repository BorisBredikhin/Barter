from django.db import models


class User(models.Model):
    firstName = models.CharField(max_length=50)
    lastName = models.CharField(max_length=50)
    birthDay = models.DateField()
    mail = models.EmailField()
    phoneNumber = models.IntegerField()
    primaryActivity = models.CharField(max_length=50)
    photo = models.ImageField()

    points = models.IntegerField()

    ratingAsCustomer = models.IntegerField()
    ratingAsExecutor = models.IntegerField()
    password = models.CharField(max_length=50)


class ConfDataUser(models.Model):
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    password = models.CharField(max_length=50)
    score = models.IntegerField()


class Categories(models.Model):
    user = models.ManyToManyField(
        User,
    )
    category = models.CharField(max_length=50)


class AddressUser(models.Model):
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    country = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    street = models.CharField(max_length=50)
    house = models.IntegerField()
    room = models.IntegerField()


class CurrentAddressUser(models.Model):
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    latitude = models.DecimalField(max_digits=3, decimal_places=6)
    longitude = models.DecimalField(max_digits=3, decimal_places=6)


class Ratings(models.Model):
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    ratingAsCustomer = models.DecimalField(max_digits=4, decimal_places=2)
    ratingAsExecutor = models.DecimalField(max_digits=4, decimal_places=2)


class Tasks(models.Model):
    customer = models.ForeignKey(User, on_delete=models.PROTECT)
    executor = models.ForeignKey(User, on_delete=models.PROTECT)
    title = models.CharField(max_length=50)
    description = models.TextField()
    price = models.IntegerField()
    status = models.CharField(max_length=20)


class Tag(models.Model):
    task = models.ManyToManyField(Tasks)
    name = models.CharField(max_length=50)


class AddressTask(models.Model):
    task = models.OneToOneField(
        Tasks,
        on_delete=models.CASCADE,
        primary_key=True,
    )
    country = models.CharField(max_length=50)
    city = models.CharField(max_length=50)
    street = models.CharField(max_length=50)
    house = models.IntegerField()
    room = models.IntegerField()


class Reviews(models.Model):
    fromId = models.ForeignKey(User, on_delete=models.RESTRICT)
    toId = models.ForeignKey(User, on_delete=models.RESTRICT)
    task = models.ForeignKey(Tasks, on_delete=models.RESTRICT)
    reviewDate = models.DateTimeField()
    reviewText = models.TextField()
    rating = models.IntegerField()
    status_to = models.CharField(max_length=50)
# Create your models here.

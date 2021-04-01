from django.core.exceptions import ValidationError


def non_negative_validator(value):
    if value < 0:
        raise ValidationError(f'{value=} must be > 0')


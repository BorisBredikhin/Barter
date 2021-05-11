import abc

class Dictionable(abc.ABC):
    @abc.abstractmethod
    def to_dict(self) -> dict:
        pass
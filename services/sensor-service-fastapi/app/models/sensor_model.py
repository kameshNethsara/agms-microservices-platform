from pydantic import BaseModel

class SensorData(BaseModel):
    zoneId: int
    temperature: float
    humidity: float
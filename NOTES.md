# Search hotel

## Purpose
You'll write your first end-point to search hotels with available rooms.

## Input
GET method to /api/v1/search
Query parameters:
- 'z' for zipcode
- 'd' as array strings which are the arrival and departure dates

Example: `/api/v1/search?q=75001&d=2024-01-01&d=2024-01-02`

## Output
```json
{
  "hotels": [
    {
      "id": 1,
      "name": "Elegance Hotel",
      "address": "25 RUE DU LOUVRE, 75001, PARIS",
      "available_rooms": 10,
      "price": 150,
      "amenities": ["Free Wi-Fi", "Parking", "Complimentary Breakfast"]
    },
    {
      "id": 2,
      "name": "Charming Inn",
      "address": "21 RUE DU BOULOI, 75001, PARIS",
      "available_rooms": 5,
      "price": 120,
      "amenities": ["Free Wi-Fi", "Swimming Pool", "Room Service"]
    }
  ]
}
```

## Tips
- Use `@WebMvcTest`
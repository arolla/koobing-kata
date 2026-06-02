# Book a room

## Purpose

Implement the end-point for room booking.

## Input

POST method to /api/v1/booking

```json
{
  "hostel_id": 1,
  "room_id": "46da9f48-ea47-4d9d-9f4b-52b5e56f4e2e",
  "arrival": "2020-01-01",
  "departure": "2020-01-02",
  "email": "foo.bar@example.com"
}
```

### Output

HTTP code: 201

```json
{
  "booking_number": "A123"
}
```
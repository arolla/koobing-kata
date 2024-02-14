# 100: Search hotel when no one is available

## Purpose

You'll write an end-point to search hotels with available rooms.\
In this user story, no hotel is found.

## Input
GET method to /api/v1/search
Query parameters:
- 'z' for zipcode
- 'd' as array strings which are the arrival and departure dates

Example: `/api/v1/search?q=75001&d=2024-01-01&d=2024-01-02`

## Output

HTTP code: 404

```json
{
  "search_criteria": {
    "zipcode": "75001",
    "arrival_date": "2024-01-01",
    "departure_date": "2024-01-02"
  }
}
```
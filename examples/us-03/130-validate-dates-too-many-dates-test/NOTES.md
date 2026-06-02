# Validate provided dates while search

## Purpose

You'll check service client provides 2 dates in the search end-point.

## Case 1

### Input
GET method to /api/v1/search
Query parameters:
- 'z' for zipcode
- 'd' as array strings which are the arrival and departure dates

Example: `/api/v1/search?q=75001&d=2024-01-01`

### Output

HTTP code: 400

```json
{
  "message": "Two dates must be provided when searching hotels."
}
```

## Case 2

### Input

GET method to /api/v1/search
Query parameters:

- 'z' for zipcode
- 'd' as array strings which are the arrival and departure dates

Example: `/api/v1/search?q=75001&d=2024-01-01&d=2024-01-02&d=2024-01-03`

### Output

HTTP code: 400

```json
{
  "message": "Two dates must be provided when searching hotels."
}
```
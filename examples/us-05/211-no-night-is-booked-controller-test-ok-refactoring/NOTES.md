# Implement search service

## Purpose

Refuse when a booking doesn't contain at least one night.

## Todo

+ First implement in service
+ After implement this behavior in controller

For the controller, we expect:

400 Bad Request as HTTP status code

This response:

```json
{
  "message": "A booking must contain at least one night."
}
```

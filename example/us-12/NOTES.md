# Implement persistence

## Purpose

Add correlation in all log messages. Each HTTP request must have a unique correlation identifier.

## Todo

1. Add log in debug all along then search chain: controller, service, repository
2. All log from the same request must have the same correlation identifier
3. Each request must have a unique correlation identifier


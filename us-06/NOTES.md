# Implement search service

## Purpose

When hotel repository is too slow, we don't want to overload it with too long requests.

## What

Simulate when the repository is used for hotel search is too slow.
A repository is too slow when it takes more than 500ms to respond.

Manage this case in the domain service layer.

> TIP: Use the repository stub.

## Expected

An empty list of available hotels and log the issue.


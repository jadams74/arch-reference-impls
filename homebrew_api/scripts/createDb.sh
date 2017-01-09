#!/usr/bin/env bash

# assumes psql installed. Assumes pwd configured as env var.
# TODO Dockerize this.

psql -h localhost -U postgres -f homebrew.sql
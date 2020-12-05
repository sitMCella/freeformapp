package de.mcella.openapi.v3.freeformapp

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class InvalidRequestException(message: String): ResponseStatusException(HttpStatus.BAD_REQUEST, message)

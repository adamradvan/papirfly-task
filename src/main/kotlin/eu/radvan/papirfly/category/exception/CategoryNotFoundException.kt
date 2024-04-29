package eu.radvan.papirfly.category.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CategoryNotFoundException(message: String) : Exception(message)

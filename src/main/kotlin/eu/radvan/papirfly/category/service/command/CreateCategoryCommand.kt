package eu.radvan.papirfly.category.service.command

import eu.radvan.papirfly.category.controller.CreateCategoryRequest

data class CreateCategoryCommand(
    val name: String,
    val description: String
)

fun CreateCategoryRequest.toCommand() = CreateCategoryCommand(
    name = name,
    description = description
)
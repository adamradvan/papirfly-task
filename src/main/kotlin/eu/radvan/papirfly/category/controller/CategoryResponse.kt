package eu.radvan.papirfly.category.controller

import eu.radvan.papirfly.category.Category
import java.util.*

data class CategoryResponse(
    val id: UUID,
    val categoryId: Long,
    val name: String,
    val description: String,
)

fun Category.toResponse() = CategoryResponse(
    id = id,
    categoryId = sequenceId!!,
    name = name,
    description = description
)
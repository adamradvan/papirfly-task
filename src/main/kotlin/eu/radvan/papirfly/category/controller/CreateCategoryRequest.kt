package eu.radvan.papirfly.category.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class CreateCategoryRequest(
    @field:[NotBlank Length(max = 64)] val name: String,
    @field:[NotBlank Length(max = 2048)] val description: String,
)
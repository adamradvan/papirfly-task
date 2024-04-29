package eu.radvan.papirfly.article.controller

import eu.radvan.papirfly.common.Currency
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal

data class CreateArticleRequest(
    @field:[NotBlank Length(max = 64)] val name: String,
    @field:[NotBlank Length(max = 2048)] val description: String,
    @field:PositiveOrZero val price: BigDecimal,
    val currency: Currency?,
    val categories: List<Long>,
)
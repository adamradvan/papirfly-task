package eu.radvan.papirfly.article.service.command

import eu.radvan.papirfly.article.controller.CreateArticleRequest
import eu.radvan.papirfly.common.Currency
import java.math.BigDecimal

data class CreateArticleCommand(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val currency: Currency?,
    val categories: List<Long>,
)

fun CreateArticleRequest.toCommand() = CreateArticleCommand(
    name = name,
    description = description,
    price = price,
    currency = currency,
    categories = categories
)
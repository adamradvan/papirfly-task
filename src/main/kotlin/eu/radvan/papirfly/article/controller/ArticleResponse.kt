package eu.radvan.papirfly.article.controller

import eu.radvan.papirfly.article.Article
import eu.radvan.papirfly.common.Currency
import java.math.BigDecimal
import java.util.*

data class ArticleResponse(
    val id: UUID,
    val articleId: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val currency: Currency?,
    val categories: List<Long>,
)

fun Article.toResponse(categorySequenceIds: List<Long>) = ArticleResponse(
    id = id,
    articleId = sequenceId!!,
    name = name,
    description = description,
    price = price,
    currency = currency,
    categories = categorySequenceIds
)
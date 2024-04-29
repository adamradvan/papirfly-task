package eu.radvan.papirfly.article.service

import eu.radvan.papirfly.article.Article
import eu.radvan.papirfly.article.controller.ArticleResponse
import eu.radvan.papirfly.article.controller.toResponse
import eu.radvan.papirfly.article.exception.ArticleCurrencyMissingException
import eu.radvan.papirfly.article.repository.ArticleRepository
import eu.radvan.papirfly.category.Category
import eu.radvan.papirfly.category.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CreateArticleService(
    private val repository: ArticleRepository,
    private val categoryRepository: CategoryRepository,
) {

    @Transactional
    operator fun invoke(command: CreateArticleCommand): ArticleResponse {
        if (command.price != BigDecimal.ZERO && command.currency == null) {
            throw ArticleCurrencyMissingException(
                "Cannot create article: ${command.name} with price: ${command.price} because currency is missing."
            )
        }

        val categories: List<Category> = categoryRepository.findAllBySequenceIdIn(command.categories)

        return Article(
            name = command.name,
            description = command.description,
            price = command.price,
            currency = command.currency,
            categoryIds = categories.map { it.id }
        ).let {
            val saved = repository.saveAndFlush(it)

            saved.toResponse(
                categorySequenceIds = categories.map { category -> category.sequenceId!! }
            )
        }
    }

}

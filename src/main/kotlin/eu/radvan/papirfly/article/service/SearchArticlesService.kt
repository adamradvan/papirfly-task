package eu.radvan.papirfly.article.service

import eu.radvan.papirfly.article.controller.ArticleResponse
import eu.radvan.papirfly.article.controller.toResponse
import eu.radvan.papirfly.article.repository.ArticleRepository
import eu.radvan.papirfly.article.repository.ArticleSpecs
import eu.radvan.papirfly.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class SearchArticlesService(
    private val repository: ArticleRepository,
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(command: SearchArticlesQuery): List<ArticleResponse> {
        val articles = repository.findAll(
            ArticleSpecs.descriptionEquals(command.description)
                .and(ArticleSpecs.nameContainsIgnoringCase(command.name))
        )

        val categoriesMap = categoryRepository
            .findAllById(articles.flatMap { it.categoryIds })
            .associateBy { it.id }

        return articles.map { article ->
            article.toResponse(
                categorySequenceIds = article.categoryIds.map { categoriesMap.getValue(it).sequenceId!! }
            )
        }
    }

}

package eu.radvan.papirfly.article.service

import eu.radvan.papirfly.article.controller.ArticleResponse
import eu.radvan.papirfly.article.controller.toResponse
import eu.radvan.papirfly.article.exception.ArticleNotFoundException
import eu.radvan.papirfly.article.repository.ArticleRepository
import eu.radvan.papirfly.article.service.query.GetArticleBySequenceIdQuery
import eu.radvan.papirfly.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class GetArticleBySequenceIdService(
    private val articleRepository: ArticleRepository,
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(command: GetArticleBySequenceIdQuery): ArticleResponse {
        val article = articleRepository.findBySequenceId(command.articleSequenceId)
            ?: throw ArticleNotFoundException("Article not found for id: ${command.articleSequenceId}")

        val categories = categoryRepository.findAllById(article.categoryIds).map { it.sequenceId!! }

        return article.toResponse(categorySequenceIds = categories)
    }

}

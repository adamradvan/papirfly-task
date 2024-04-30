package eu.radvan.papirfly.article.service

import eu.radvan.papirfly.article.Article
import eu.radvan.papirfly.article.exception.ArticleNotFoundException
import eu.radvan.papirfly.article.repository.ArticleRepository
import eu.radvan.papirfly.article.service.query.GetArticleBySequenceIdQuery
import eu.radvan.papirfly.category.Category
import eu.radvan.papirfly.category.repository.CategoryRepository
import eu.radvan.papirfly.common.Currency
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

class GetArticleBySequenceIdServiceTest {
    private val articleRepository = mockk<ArticleRepository>()
    private val categoryRepository = mockk<CategoryRepository>()

    private val underTest = GetArticleBySequenceIdService(
        articleRepository,
        categoryRepository
    )

    @AfterEach
    fun tearDown() {
        confirmVerified(
            articleRepository,
            categoryRepository,
        )
        clearAllMocks()
    }

    @Test
    fun `get existing article successfully`() {
        // given
        val articleId = UUID.randomUUID()
        val articleSequenceId = 1L
        val categoryId = UUID.randomUUID()

        every { categoryRepository.findAllById(any()) } returns listOf(
            mockk<Category> {
                every { id } returns categoryId
                every { sequenceId } returns 1
            }
        )

        every { articleRepository.findBySequenceId(any()) } returns mockk<Article> {
            every { id } returns articleId
            every { sequenceId } returns articleSequenceId
            every { name } returns "name"
            every { description } returns "description"
            every { price } returns BigDecimal.TEN
            every { currency } returns Currency.CZK
            every { categoryIds } returns listOf(categoryId)
        }

        // when
        val result = underTest(
            GetArticleBySequenceIdQuery(
                articleSequenceId = articleSequenceId
            )
        )

        // then
        result.run {
            this.id shouldBe articleId
            this.articleId shouldBe 1
            this.name shouldBe "name"
            this.description shouldBe "description"
            this.price shouldBe BigDecimal.TEN
            this.currency shouldBe Currency.CZK
            this.categories shouldContainExactly listOf(1)
        }

        verify {
            categoryRepository.findAllById(listOf(categoryId))
            articleRepository.findBySequenceId(articleSequenceId)
        }
    }

    @Test
    fun `get non-existent article - should throw`() {
        // given
        val articleSequenceId = 1L

        every { articleRepository.findBySequenceId(any()) } returns null

        // when
        shouldThrowWithMessage<ArticleNotFoundException>(
            "Article not found for id: $articleSequenceId"
        ) {
            underTest(
                GetArticleBySequenceIdQuery(
                    articleSequenceId = articleSequenceId
                )
            )
        }

        // then
        verify {
            articleRepository.findBySequenceId(articleSequenceId)
        }

        verify(exactly = 0) {
            categoryRepository.findAllById(any())
        }
    }

}
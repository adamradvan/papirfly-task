package eu.radvan.papirfly.article.service

import eu.radvan.papirfly.article.Article
import eu.radvan.papirfly.article.exception.ArticleCurrencyMissingException
import eu.radvan.papirfly.article.repository.ArticleRepository
import eu.radvan.papirfly.article.service.command.CreateArticleCommand
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

class CreateArticleServiceTest {
    private val articleRepository = mockk<ArticleRepository>()
    private val categoryRepository = mockk<CategoryRepository>()

    private val underTest = CreateArticleService(
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
    fun `create new article successfully`() {
        // given
        val articleId = UUID.randomUUID()
        val categoryId = UUID.randomUUID()

        every { categoryRepository.findAllBySequenceIdIn(any()) } returns listOf(
            mockk<Category> {
                every { id } returns categoryId
                every { sequenceId } returns 1
            }
        )

        val slot = slot<Article>()
        every { articleRepository.saveAndFlush(capture(slot)) } returns mockk<Article> {
            every { id } returns articleId
            every { sequenceId } returns 1
            every { name } returns "name"
            every { description } returns "description"
            every { price } returns BigDecimal.TEN
            every { currency } returns Currency.CZK
            every { categoryIds } returns listOf(categoryId)
        }

        // when
        val result = underTest(
            CreateArticleCommand(
                name = "name",
                description = "description",
                price = BigDecimal.TEN,
                currency = Currency.CZK,
                categories = listOf(1)
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
            categoryRepository.findAllBySequenceIdIn(listOf(1))
            articleRepository.saveAndFlush(slot.captured)
        }
    }

    @Test
    fun `creating new article with non zero price and null currency should throw`() {
        // given
        val name = "name"
        val price = BigDecimal.TEN

        // when
        shouldThrowWithMessage<ArticleCurrencyMissingException>(
            "Cannot create article: $name with price: $price because currency is missing."
        ) {
            underTest(
                CreateArticleCommand(
                    name = name,
                    description = "description",
                    price = price,
                    currency = null,
                    categories = emptyList()
                )
            )
        }

        // then
        verify(exactly = 0) {
            categoryRepository.findAllBySequenceIdIn(any())
            articleRepository.saveAndFlush(any())
        }
    }

}
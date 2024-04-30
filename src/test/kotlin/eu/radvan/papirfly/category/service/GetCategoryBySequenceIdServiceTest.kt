package eu.radvan.papirfly.category.service

import eu.radvan.papirfly.category.Category
import eu.radvan.papirfly.category.exception.CategoryNotFoundException
import eu.radvan.papirfly.category.repository.CategoryRepository
import eu.radvan.papirfly.category.service.query.GetCategoryBySequenceIdQuery
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.util.*

class GetCategoryBySequenceIdServiceTest {
    private val categoryRepository = mockk<CategoryRepository>()

    private val underTest = GetCategoryBySequenceIdService(
        categoryRepository
    )

    @AfterEach
    fun tearDown() {
        confirmVerified(
            categoryRepository,
        )
        clearAllMocks()
    }

    @Test
    fun `get existing category successfully`() {
        // given
        val categorySequenceId = 1L
        val categoryId = UUID.randomUUID()

        every { categoryRepository.findBySequenceId(any()) } returns mockk<Category> {
            every { id } returns categoryId
            every { sequenceId } returns categorySequenceId
            every { name } returns "name"
            every { description } returns "description"
        }

        // when
        val result = underTest(
            GetCategoryBySequenceIdQuery(
                categorySequenceId = categorySequenceId
            )
        )

        // then
        result.run {
            this.id shouldBe categoryId
            this.categoryId shouldBe 1
            this.name shouldBe "name"
            this.description shouldBe "description"
        }

        verify {
            categoryRepository.findBySequenceId(categorySequenceId)
        }
    }

    @Test
    fun `get non-existent category - should throw`() {
        // given
        val categorySequenceId = 1L

        every { categoryRepository.findBySequenceId(any()) } returns null

        // when
        shouldThrowWithMessage<CategoryNotFoundException>(
            "Category not found for id: $categorySequenceId"
        ) {
            underTest(
                GetCategoryBySequenceIdQuery(
                    categorySequenceId = categorySequenceId
                )
            )
        }

        // then
        verify {
            categoryRepository.findBySequenceId(categorySequenceId)
        }
    }

}
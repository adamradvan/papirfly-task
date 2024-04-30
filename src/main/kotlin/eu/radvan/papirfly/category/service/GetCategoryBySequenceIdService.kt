package eu.radvan.papirfly.category.service

import eu.radvan.papirfly.category.controller.CategoryResponse
import eu.radvan.papirfly.category.controller.toResponse
import eu.radvan.papirfly.category.exception.CategoryNotFoundException
import eu.radvan.papirfly.category.repository.CategoryRepository
import eu.radvan.papirfly.category.service.query.GetCategoryBySequenceIdQuery
import org.springframework.stereotype.Service

@Service
class GetCategoryBySequenceIdService(
    private val categoryRepository: CategoryRepository
) {

    operator fun invoke(command: GetCategoryBySequenceIdQuery): CategoryResponse =
        categoryRepository.findBySequenceId(command.categorySequenceId)
            ?.toResponse()
            ?: throw CategoryNotFoundException("Category not found for id: ${command.categorySequenceId}")

}

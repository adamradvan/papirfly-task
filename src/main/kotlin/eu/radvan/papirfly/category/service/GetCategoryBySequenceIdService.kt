package eu.radvan.papirfly.category.service

import eu.radvan.papirfly.category.controller.CategoryResponse
import eu.radvan.papirfly.category.controller.toResponse
import eu.radvan.papirfly.category.exception.CategoryNotFoundException
import eu.radvan.papirfly.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class GetCategoryBySequenceIdService(private val repository: CategoryRepository) {

    operator fun invoke(command: GetCategoryBySequenceIdQuery): CategoryResponse =
        repository.findBySequenceId(command.categorySequenceId)
            ?.toResponse()
            ?: throw CategoryNotFoundException("Category not found for id: ${command.categorySequenceId}")

}

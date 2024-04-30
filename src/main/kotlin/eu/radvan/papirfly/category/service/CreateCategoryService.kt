package eu.radvan.papirfly.category.service

import eu.radvan.papirfly.category.Category
import eu.radvan.papirfly.category.controller.CategoryResponse
import eu.radvan.papirfly.category.controller.toResponse
import eu.radvan.papirfly.category.repository.CategoryRepository
import eu.radvan.papirfly.category.service.command.CreateCategoryCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateCategoryService(private val repository: CategoryRepository) {

    @Transactional
    operator fun invoke(command: CreateCategoryCommand): CategoryResponse =
        Category(
            name = command.name,
            description = command.description
        ).let {
            val saved = repository.saveAndFlush(it)

            saved.toResponse()
        }

}

package eu.radvan.papirfly.category.service

import eu.radvan.papirfly.category.controller.CategoryResponse
import eu.radvan.papirfly.category.controller.toResponse
import eu.radvan.papirfly.category.repository.CategoryRepository
import eu.radvan.papirfly.category.repository.CategorySpecs
import org.springframework.stereotype.Service

@Service
class SearchCategoriesService(private val repository: CategoryRepository) {

    operator fun invoke(command: SearchCategoriesQuery): List<CategoryResponse> =
        repository.findAll(
            CategorySpecs.descriptionEquals(command.description)
                .and(CategorySpecs.nameContainsIgnoringCase(command.name))
        ).map { it.toResponse() }

}

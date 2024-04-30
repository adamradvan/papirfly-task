package eu.radvan.papirfly.category.controller

import eu.radvan.papirfly.category.service.CreateCategoryService
import eu.radvan.papirfly.category.service.GetCategoryBySequenceIdService
import eu.radvan.papirfly.category.service.SearchCategoriesService
import eu.radvan.papirfly.category.service.command.toCommand
import eu.radvan.papirfly.category.service.query.GetCategoryBySequenceIdQuery
import eu.radvan.papirfly.category.service.query.SearchCategoriesQuery
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/categories")
class CategoryController(
    private val createCategoryService: CreateCategoryService,
    private val getCategoryBySequenceIdService: GetCategoryBySequenceIdService,
    private val searchCategoriesService: SearchCategoriesService
) {

    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK) // HttpStatus.CREATED could be used
    fun createCategory(
        @Valid
        @RequestBody
        request: CreateCategoryRequest,
    ): CategoryResponse = createCategoryService(request.toCommand())


    @GetMapping("/{categoryId}", produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getCategory(
        @PathVariable categoryId: Long
    ): CategoryResponse = getCategoryBySequenceIdService(
        GetCategoryBySequenceIdQuery(
            categorySequenceId = categoryId
        )
    )

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun searchCategories(
        @RequestParam("name", required = false) name: String?,
        @RequestParam("description", required = false) description: String?,
    ): List<CategoryResponse> = searchCategoriesService(
        SearchCategoriesQuery(
            name = name,
            description = description
        )
    )

}

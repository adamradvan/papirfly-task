package eu.radvan.papirfly.category.repository

import eu.radvan.papirfly.category.Category
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CategoryRepository : JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    fun findBySequenceId(sequenceId: Long): Category?

    fun findAllBySequenceIdIn(categorySequenceIds: List<Long>): List<Category>

}

object CategorySpecs {

    fun descriptionEquals(description: String?): Specification<Category> =
        Specification { root, _, criteriaBuilder ->
            description?.let {
                criteriaBuilder.equal(root.get<String>("description"), it)
            }
        }

    fun nameContainsIgnoringCase(name: String?): Specification<Category> =
        Specification { root, _, criteriaBuilder ->
            name?.let {
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + it.lowercase() + "%"
                )
            }
        }
}
package eu.radvan.papirfly.article.repository

import eu.radvan.papirfly.article.Article
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository : JpaRepository<Article, UUID>, JpaSpecificationExecutor<Article> {

    fun findBySequenceId(sequenceId: Long): Article?

}

object ArticleSpecs {

    fun descriptionEquals(description: String?): Specification<Article> =
        Specification { root, _, criteriaBuilder ->
            description?.let {
                criteriaBuilder.equal(root.get<String>("description"), it)
            }
        }

    fun nameContainsIgnoringCase(name: String?): Specification<Article> =
        Specification { root, _, criteriaBuilder ->
            name?.let {
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + it.lowercase() + "%"
                )
            }
        }
}
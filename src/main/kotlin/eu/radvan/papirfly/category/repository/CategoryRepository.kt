package eu.radvan.papirfly.category.repository

import eu.radvan.papirfly.category.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID>
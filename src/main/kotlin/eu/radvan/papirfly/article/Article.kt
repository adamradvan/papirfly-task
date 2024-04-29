package eu.radvan.papirfly.article

import eu.radvan.papirfly.common.Currency
import eu.radvan.papirfly.common.DomainEntity
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import org.hibernate.annotations.Generated
import org.hibernate.annotations.Type
import org.hibernate.generator.EventType
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "pfy_article")
class Article(
    id: UUID = UUID.randomUUID(),
    name: String,
    description: String,
    price: BigDecimal,
    currency: Currency?,
    categoryIds: List<UUID> = listOf()
) : DomainEntity(id) {

    init {
        checkCurrency(price, currency)
    }

    @Generated(event = [EventType.INSERT])
    @Column(
        name = "article_id",
        columnDefinition = "serial",
        updatable = false,
        nullable = false
    )
    val sequenceId: Long? = null

    @Column(length = 64, nullable = false)
    var name: String = name
        private set

    @Column(length = 2048, nullable = false)
    var description: String = description
        private set

    @Min(0)
    @Column(nullable = false)
    var price: BigDecimal = price
        private set

    @Enumerated(EnumType.STRING)
    var currency: Currency? = currency
        private set

    @Column(
        name = "categories",
        columnDefinition = "text[]"
    )
    @Type(ListArrayType::class)
    private val allCategoryIds: MutableList<UUID> = categoryIds.toMutableList()

    @Transient
    var categoryIds: List<UUID> = allCategoryIds
        get() = allCategoryIds.toList()
        private set


    private fun checkCurrency(price: BigDecimal, currency: Currency?) {
        if (price != BigDecimal.ZERO && currency == null) {
            error("Currency cannot be null if price is not zero.")
        }
    }

}
package eu.radvan.papirfly.category

import eu.radvan.papirfly.common.DomainEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Generated
import org.hibernate.generator.EventType
import java.util.*

@Entity
@Table(name = "pfy_category")
class Category(
    id: UUID = UUID.randomUUID(),
    name: String,
    description: String
) : DomainEntity(id) {

    @Generated(event = [EventType.INSERT])
    @Column(name = "category_id", columnDefinition = "serial", updatable = false)
    val sequenceId: Long? = null

    @Column(length = 64)
    var name: String = name
        private set

    @Column(length = 2048)
    var description: String = description
        private set

}
package eu.radvan.papirfly.common

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.util.ProxyUtils
import java.time.Instant
import java.util.*

@MappedSuperclass
abstract class DomainEntity(
    @Id val id: UUID = UUID.randomUUID()
) {
    @CreationTimestamp
    val createdAt: Instant = Instant.now()

    @UpdateTimestamp
    var updatedAt: Instant = Instant.now()

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as DomainEntity

        return this.id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

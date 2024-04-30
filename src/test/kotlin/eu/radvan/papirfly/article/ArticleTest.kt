package eu.radvan.papirfly.article

import io.kotest.assertions.throwables.shouldThrowWithMessage
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ArticleTest {

    @Test
    fun `new instance with non zero price and currency null will throw`() {
        shouldThrowWithMessage<IllegalStateException>(
            message = "Currency cannot be null if price is not zero."
        ) {
            Article(
                price = BigDecimal.TEN,
                currency = null,
                name = "Jaden",
                description = "Andriana",
                categoryIds = listOf()
            )
        }
    }

}
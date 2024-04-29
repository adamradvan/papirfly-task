package eu.radvan.papirfly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PapirflyApplication

fun main(args: Array<String>) {
    runApplication<PapirflyApplication>(*args)
}

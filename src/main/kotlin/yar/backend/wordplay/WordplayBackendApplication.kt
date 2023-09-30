package yar.backend.wordplay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordplayBackendApplication

fun main(args: Array<String>) {
    runApplication<WordplayBackendApplication>(*args)
}

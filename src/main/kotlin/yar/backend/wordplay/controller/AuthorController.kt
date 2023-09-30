package yar.backend.wordplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yar.backend.wordplay.dto.AuthorDto
import yar.backend.wordplay.service.AuthorService

@RestController
@RequestMapping("/api/authors")
@CrossOrigin
class AuthorController(private val authorService: AuthorService) {

    @GetMapping
    fun findAuthor(@RequestParam name: String): ResponseEntity<List<AuthorDto>> {
        return ResponseEntity.ok(authorService.findByName(name))
    }

}
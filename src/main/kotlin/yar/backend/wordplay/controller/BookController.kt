package yar.backend.wordplay.controller

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import yar.backend.wordplay.dto.BookDto
import yar.backend.wordplay.service.BookService

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = ["http://localhost:5173"])
class BookController(private val bookService: BookService) {


    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getAllBooks(
        @SortDefault(sort = ["id"], direction = Sort.Direction.ASC) pageable: Pageable,
    ) = ResponseEntity.ok(bookService.getAllBooks(pageable))

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getAllBooks(@PathVariable id: Long): ResponseEntity<BookDto> {
        return bookService.get(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("edit")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun editBook(@RequestBody book: BookDto) {
        bookService.update(book)
    }

    @GetMapping("search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun search(@RequestParam text: String): List<BookDto> {
        return bookService.search(text)
    }

    @DeleteMapping
    fun delete(@RequestParam("bookId") bookIds: List<Long>) {
        bookService.delete(bookIds)
    }
}

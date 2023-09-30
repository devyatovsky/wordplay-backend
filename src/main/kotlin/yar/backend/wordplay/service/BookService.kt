package yar.backend.wordplay.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import yar.backend.wordplay.dto.BookDto

interface BookService {
    fun getAllBooks(pageable: Pageable): Page<BookDto>
    fun get(id: Long): BookDto?
    fun update(book: BookDto)
    fun delete(bookIds: List<Long>)

    fun search(searchText: String): List<BookDto>
}

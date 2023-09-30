package yar.backend.wordplay.service.impl

import jakarta.persistence.criteria.Join
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import yar.backend.wordplay.dto.BookDto
import yar.backend.wordplay.entity.AuthorEntity
import yar.backend.wordplay.entity.BookEntity
import yar.backend.wordplay.entity.ImageEntity
import yar.backend.wordplay.entity.PublisherEntity
import yar.backend.wordplay.mapping.toBookDto
import yar.backend.wordplay.repo.BookRepository
import yar.backend.wordplay.service.AuthorService
import yar.backend.wordplay.service.BookService
import yar.backend.wordplay.service.ImageService
import yar.backend.wordplay.service.PublisherService
import java.math.BigDecimal


@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val authorService: AuthorService,
    private val publishService: PublisherService,
    private val imageService: ImageService,
) : BookService {

    override fun getAllBooks(pageable: Pageable): Page<BookDto> = bookRepository.findAll(pageable).map { it.toBookDto() }
    override fun get(id: Long): BookDto? = bookRepository.findByIdOrNull(id)?.toBookDto()

    override fun search(searchText: String): List<BookDto> {
        return bookRepository.findAllByBookNameContainingIgnoringCaseOrAuthorNameContainingIgnoringCase(searchText).map { it.toBookDto() }
    }

    @Transactional
    override fun update(book: BookDto) {
        val authorEntity = authorService.saveOrUpdate(book.author)
        val publisherEntity = publishService.saveOrUpdate(book.publisher)
        val bookCoverId = imageService.getImageIdByLink(book.cover)
        val imageEntity = imageService.findImage(bookCoverId)!!
        val bookEntity = book.toEntity(author = authorEntity, publisher = publisherEntity, cover = imageEntity)
        bookRepository.save(bookEntity)

    }

    override fun delete(bookIds: List<Long>) {
        bookRepository.deleteAllById(bookIds)
    }
}

private fun BookDto.toEntity(
    id: Long? = null,
    bookName: String? = null,
    author: AuthorEntity,
    cover: ImageEntity,
    publisher: PublisherEntity,
    publishYear: String? = null,
    price: BigDecimal? = null,
    description: String? = null,
    category: String? = null
) = BookEntity(
    id = id ?: this.id,
    bookName = bookName ?: this.bookName,
    publishYear = publishYear ?: this.publishYear,
    price = price ?: this.buyPrice,
    description = description ?: this.description,
    cover = cover,
    author = author,
    publisher = publisher,
    category = category ?: this.category
)



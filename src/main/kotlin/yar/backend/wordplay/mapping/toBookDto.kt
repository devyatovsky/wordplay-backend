package yar.backend.wordplay.mapping

import yar.backend.wordplay.dto.AuthorDto
import yar.backend.wordplay.dto.BookDto
import yar.backend.wordplay.dto.ImageDto
import yar.backend.wordplay.dto.PublisherDto
import yar.backend.wordplay.entity.*
import java.math.BigDecimal

const val IMAGE_URL = "http://localhost:8080/api/img/"

fun BookEntity.toBookDto() =
    BookDto(
        id,
        author.toAuthorDto(),
        bookName,
        description,
        publisher.toPublisherDto(),
        publishYear,
        price,
        price / BigDecimal.valueOf(4),
        IMAGE_URL + cover.id,
        category
    )

fun PublisherEntity.toPublisherDto() = PublisherDto(id, name)

fun AuthorEntity.toAuthorDto() = AuthorDto(id, name)

fun ImageEntity.toDto(contentType: String, byteArray: ByteArray): ImageDto = ImageDto(contentType, byteArray)

fun UserEntity.toDto() = UserDto(id!!, fullName, username, IMAGE_URL + avatar.id, role)
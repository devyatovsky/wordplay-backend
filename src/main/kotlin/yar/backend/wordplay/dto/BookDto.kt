package yar.backend.wordplay.dto

import java.math.BigDecimal


data class BookDto(
    val id: Long?,
    val author: AuthorDto,
    val bookName: String,
    val description: String,
    val publisher: PublisherDto,
    val publishYear: String,
    val buyPrice: BigDecimal,
    val rentPrice: BigDecimal?,
    val cover: String,
    val category: String
)

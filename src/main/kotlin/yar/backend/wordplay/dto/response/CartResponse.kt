package yar.backend.wordplay.dto.response

import yar.backend.wordplay.dto.BookPurchaseDto
import java.math.BigDecimal

data class CartResponse(val books: List<BookPurchaseDto>, val totalPrice: BigDecimal, val id: Long)

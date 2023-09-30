package yar.backend.wordplay.service

import yar.backend.wordplay.dto.request.AddToCartRequest
import yar.backend.wordplay.dto.response.CartResponse

interface CartService {
    fun getCartByUsername(username: String): CartResponse
    fun add(username: String, request: AddToCartRequest): CartResponse
    fun remove(username: String, bookId: Long): CartResponse
    fun buy(username: String): CartResponse
}

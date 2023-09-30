package yar.backend.wordplay.service.impl

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import yar.backend.wordplay.dto.BookPurchaseDto
import yar.backend.wordplay.dto.request.AddToCartRequest
import yar.backend.wordplay.dto.response.CartResponse
import yar.backend.wordplay.dto.response.RentNotificationResponse
import yar.backend.wordplay.entity.BookPurchaseTypeEntity
import yar.backend.wordplay.entity.CartEntity
import yar.backend.wordplay.entity.PurchaseType
import yar.backend.wordplay.mapping.toBookDto
import yar.backend.wordplay.repo.BookPurchaseTypeRepository
import yar.backend.wordplay.repo.BookRepository
import yar.backend.wordplay.repo.CartRepository
import yar.backend.wordplay.repo.UserRepository
import yar.backend.wordplay.service.CartService
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Service
class CartServiceImpl(
    private val bookPurchaseTypeRepository: BookPurchaseTypeRepository,
    private val cartRepository: CartRepository,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
) : CartService {

    override fun getCartByUsername(username: String): CartResponse {

        val findByUserUsernameOrderByDate = cartRepository.findByUserUsernameOrderByDate(username)
        val findAllByCartUserUsername = bookPurchaseTypeRepository.findAllByCartUserUsername(username)

        val bookList = mutableListOf<BookPurchaseDto>()
        var totalPrice = BigDecimal.ZERO
        findAllByCartUserUsername.forEach {
            totalPrice += calculate(it.purchaseType!!, it.book!!.price)
            bookList.add(BookPurchaseDto(it.book.toBookDto(), it.purchaseType))
        }
        return CartResponse(bookList, totalPrice, findByUserUsernameOrderByDate.id!!)
    }

    @Transactional
    override fun add(username: String, request: AddToCartRequest): CartResponse {
        val bookEntity =
            bookRepository.findByIdOrNull(request.bookId) ?: throw IllegalArgumentException("Book not found!")
        val cartEntity = cartRepository.findByUserUsernameOrderByDate(username)
        bookPurchaseTypeRepository.save(
            BookPurchaseTypeEntity(
                book = bookEntity,
                purchaseType = request.purchaseType,
                cart = cartEntity
            )
        )
        return getCartByUsername(username)
    }

    @Transactional
    override fun remove(username: String, bookId: Long): CartResponse {
        bookPurchaseTypeRepository.removeByCartUserUsernameAndBookId(username, bookId)
        return getCartByUsername(username)
    }

    @Transactional
    override fun buy(username: String): CartResponse {
        val cart = cartRepository.findByUserUsernameOrderByDate(username)
        cart.released = true
        cartRepository.save(cart)
        val user = userRepository.findByUsername(username)
        val savedCart = cartRepository.save(CartEntity(user = user))
        return CartResponse(emptyList(), BigDecimal.ZERO, savedCart.id!!)
    }

    private fun calculate(purchaseType: PurchaseType, price: BigDecimal): BigDecimal {
        return when (purchaseType) {
            PurchaseType.RENT_2_MONTH -> price.divide(BigDecimal.valueOf(1.5))
            PurchaseType.RENT_MONTH -> price.divide(BigDecimal.valueOf(2))
            PurchaseType.RENT_2_WEEKS -> price.divide(BigDecimal.valueOf(4))
            else -> price
        }
    }
}
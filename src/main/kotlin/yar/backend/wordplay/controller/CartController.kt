package yar.backend.wordplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yar.backend.wordplay.dto.request.AddToCartRequest
import yar.backend.wordplay.dto.response.CartResponse
import yar.backend.wordplay.service.CartService


@RestController
@RequestMapping("/api/cart")
class CartController(private val cartService: CartService) {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    fun getCart(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(cartService.getCartByUsername(userDetails.username))
    }

    @PostMapping("book")
    fun add(
        @RequestBody request: AddToCartRequest,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(cartService.add(userDetails.username, request))
    }

    @DeleteMapping("book/{bookId}")
    fun delete(
        @PathVariable bookId: Long,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(cartService.remove(userDetails.username, bookId))
    }

    @PostMapping("buy")
    fun delete(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<CartResponse> {
        return ResponseEntity.ok(cartService.buy(userDetails.username))
    }
}
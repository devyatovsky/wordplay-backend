package yar.backend.wordplay.dto.request

import yar.backend.wordplay.entity.PurchaseType

data class AddToCartRequest(val bookId: Long, val purchaseType: PurchaseType)

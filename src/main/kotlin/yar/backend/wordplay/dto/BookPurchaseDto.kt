package yar.backend.wordplay.dto

import yar.backend.wordplay.entity.PurchaseType

data class BookPurchaseDto(val book: BookDto, val purchaseType: PurchaseType)

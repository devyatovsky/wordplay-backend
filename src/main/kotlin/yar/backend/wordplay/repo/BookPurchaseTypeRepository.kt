package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import yar.backend.wordplay.entity.BookPurchaseTypeEntity

interface BookPurchaseTypeRepository : JpaRepository<BookPurchaseTypeEntity, Long> {

    fun findAllByCartUserUsername(username: String): List<BookPurchaseTypeEntity>
    fun removeByCartUserUsernameAndBookId(username: String, bookId: Long)
}
package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import yar.backend.wordplay.entity.CartEntity

interface CartRepository : JpaRepository<CartEntity, Long> {

    fun findByUserUsernameOrderByDate(username: String): CartEntity
    fun findAllByUserUsernameAndReleasedTrueOrderByDate(username: String): List<CartEntity>
}
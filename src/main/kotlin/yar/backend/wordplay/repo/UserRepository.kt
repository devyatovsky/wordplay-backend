package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import yar.backend.wordplay.entity.UserEntity

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String) : UserEntity?
}
package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import yar.backend.wordplay.entity.AuthorEntity

interface AuthorRepository : JpaRepository<AuthorEntity, Long> {


    fun findAllByNameContaining(name: String): List<AuthorEntity>


    @Transactional
    @Modifying
    @Query("update AuthorEntity a set a.name = ?1 where a.id = ?2 and a.name = ?3")
    fun updateAuthor(name: String, id: Long, name1: String)


    @Transactional
    @Modifying
    @Query("update AuthorEntity a set a.name = ?1 where a.id = ?2")
    fun updateNameById(name: String, id: Long)
}
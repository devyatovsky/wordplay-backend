package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import yar.backend.wordplay.entity.BookEntity

interface BookRepository : JpaRepository<BookEntity, Long> {
    @Query("select b from BookEntity b where lower(b.bookName) like concat('%', lower(:search), '%') or lower(b.author.name) like concat('%', lower(:search), '%')")
    fun findAllByBookNameLikeOrAndAuthorNameLike(@Param("search") search: String): List<BookEntity>

    //    @Query(
//        """select b from BookEntity b
//where b.bookName like upper(concat('%', ?1, '%') ) or upper(b.author.name) like upper(concat(?1, '%'))"""
//    )
    @Query(
        """select b from BookEntity b
            where upper(b.bookName) like upper(concat('%', ?1, '%'))
            or upper(b.author.name) like upper(concat('%', ?1, '%'))"""
    )
    fun findAllByBookNameContainingIgnoringCaseOrAuthorNameContainingIgnoringCase(search: String): List<BookEntity>
}
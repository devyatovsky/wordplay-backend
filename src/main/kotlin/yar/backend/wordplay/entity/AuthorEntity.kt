package yar.backend.wordplay.entity

import jakarta.persistence.*

@Entity
@Table(name = "authors")
class AuthorEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    var name: String,

    @OneToMany(fetch = FetchType.LAZY)
    var books: List<BookEntity> = emptyList()
)
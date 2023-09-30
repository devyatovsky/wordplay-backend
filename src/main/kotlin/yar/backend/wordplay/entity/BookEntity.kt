package yar.backend.wordplay.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "books")
class BookEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var bookName: String,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: AuthorEntity,

    @OneToOne
    @JoinColumn(name = "cover_id")
    val cover: ImageEntity,

    @ManyToOne
    var publisher: PublisherEntity,
    var publishYear: String,
    var price: BigDecimal,
    var description: String,
    var category: String,

)
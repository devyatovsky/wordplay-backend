package yar.backend.wordplay.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.util.Date


@Entity
@Table(name = "cart")
class CartEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null,

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    val books: List<BookPurchaseTypeEntity> = emptyList(),

    @CreatedDate
    @Column(name = "created_date")
    val date: Date = Date.from(Instant.now()),

    @Column(name = "released")
    var released: Boolean = false,
)
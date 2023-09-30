package yar.backend.wordplay.entity

import jakarta.persistence.*

@Entity
@Table(name = "book_purchase_type")
class BookPurchaseTypeEntity (
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "book_id")
    val book: BookEntity? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_type")
    val purchaseType: PurchaseType? = null,

    @ManyToOne
    @JoinColumn(name = "cart_id")
    val cart: CartEntity? = null
)
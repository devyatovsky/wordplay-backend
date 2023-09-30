package yar.backend.wordplay.entity

import jakarta.persistence.*

@Entity
@Table(name = "images")
class ImageEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "path")
    val path: String,
    @Column(name = "content_type")
    val contentType: String,
)
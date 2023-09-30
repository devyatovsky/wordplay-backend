package yar.backend.wordplay.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @Column(name = "full_name")
    var fullName: String,

    @Column(name = "username")
    private var username: String = "",

    @Column(name = "password")
    private var password: String = "",

    @OneToOne
    @JoinColumn(name = "avatar_id")
    var avatar: ImageEntity,

    @Enumerated(EnumType.STRING)
    var role: Role
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> = listOf(SimpleGrantedAuthority(role.name))

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
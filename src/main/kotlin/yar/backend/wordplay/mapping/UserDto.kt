package yar.backend.wordplay.mapping

import yar.backend.wordplay.entity.Role

/**
 * DTO for {@link yar.backend.wordplay.entity.UserEntity}
 */
data class UserDto(
    val id: Long,
    val fullName: String,
    val username: String,
    val avatar: String,
    val role: Role? = null,
)
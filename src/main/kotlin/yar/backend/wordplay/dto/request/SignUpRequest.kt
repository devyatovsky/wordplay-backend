package yar.backend.wordplay.dto.request

data class SignUpRequest(
    val fullName: String,
    val username: String,
    val password: String,
    val avatar: String
)
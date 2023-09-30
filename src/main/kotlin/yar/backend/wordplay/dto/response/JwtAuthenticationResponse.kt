package yar.backend.wordplay.dto.response

import yar.backend.wordplay.mapping.UserDto

data class JwtAuthenticationResponse(val token: String, val user: UserDto)
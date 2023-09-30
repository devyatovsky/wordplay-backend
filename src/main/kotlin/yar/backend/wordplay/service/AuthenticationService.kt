package yar.backend.wordplay.service

import yar.backend.wordplay.dto.request.SignInRequest
import yar.backend.wordplay.dto.request.SignUpRequest
import yar.backend.wordplay.dto.response.JwtAuthenticationResponse

interface AuthenticationService {
    fun signup(request: SignUpRequest): JwtAuthenticationResponse
    fun signin(request: SignInRequest): JwtAuthenticationResponse
}
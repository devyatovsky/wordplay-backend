package yar.backend.wordplay.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yar.backend.wordplay.dto.request.SignInRequest
import yar.backend.wordplay.dto.request.SignUpRequest
import yar.backend.wordplay.dto.response.JwtAuthenticationResponse
import yar.backend.wordplay.service.AuthenticationService

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping("signup")
    fun signup(@RequestBody request: SignUpRequest): ResponseEntity<JwtAuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.signup(request))
    }

    @PostMapping("signin")
    fun signin(@RequestBody request: SignInRequest): ResponseEntity<JwtAuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.signin(request))
    }
}
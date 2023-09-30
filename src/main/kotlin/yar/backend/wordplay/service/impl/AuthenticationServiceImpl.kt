package yar.backend.wordplay.service.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import yar.backend.wordplay.dto.request.SignInRequest
import yar.backend.wordplay.dto.request.SignUpRequest
import yar.backend.wordplay.dto.response.JwtAuthenticationResponse
import yar.backend.wordplay.entity.CartEntity
import yar.backend.wordplay.entity.Role
import yar.backend.wordplay.entity.UserEntity
import yar.backend.wordplay.mapping.toDto
import yar.backend.wordplay.repo.CartRepository
import yar.backend.wordplay.repo.UserRepository
import yar.backend.wordplay.service.AuthenticationService
import yar.backend.wordplay.service.ImageService
import yar.backend.wordplay.service.JwtService

@Service
class AuthenticationServiceImpl(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository,
    private val imageService: ImageService,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override fun signup(request: SignUpRequest): JwtAuthenticationResponse {
        val imageIdByLink = imageService.getImageIdByLink(request.avatar)
        val avatar = imageService.findImage(imageIdByLink) ?: throw IllegalArgumentException("Avatar not found!")
        val userEntity = UserEntity(
            fullName = request.fullName,
            password = passwordEncoder.encode(request.password),
            role = Role.USER,
            avatar = avatar,
            username = request.username
        )
        val savedUser = userRepository.save(userEntity)
        cartRepository.save(CartEntity(user = savedUser))
        val jwt = jwtService.generateToken(userEntity)
        return JwtAuthenticationResponse(jwt, userEntity.toDto())
    }

    override fun signin(request: SignInRequest): JwtAuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.username, request.password)
        )
        val user = userRepository.findByUsername(request.username) ?: throw IllegalArgumentException("Invalid email or password")
        val jwt = jwtService.generateToken(user)
        return JwtAuthenticationResponse(jwt, user.toDto())
    }
}
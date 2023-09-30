package yar.backend.wordplay.service

import org.springframework.security.core.userdetails.UserDetailsService

interface UserService {
    fun userDetailsService(): UserDetailsService
}
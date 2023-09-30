package yar.backend.wordplay.service.impl

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import yar.backend.wordplay.repo.UserRepository
import yar.backend.wordplay.service.UserService

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun userDetailsService(): UserDetailsService =
        UserDetailsService { username ->
            userRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("User not found")
        }
}
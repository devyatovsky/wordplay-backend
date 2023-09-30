package yar.backend.wordplay.service

import yar.backend.wordplay.dto.AuthorDto
import yar.backend.wordplay.entity.AuthorEntity

interface AuthorService {
    fun findByName(name: String): List<AuthorDto>
    fun saveOrUpdate(author: AuthorDto): AuthorEntity

}

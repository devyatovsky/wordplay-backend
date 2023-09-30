package yar.backend.wordplay.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import yar.backend.wordplay.dto.AuthorDto
import yar.backend.wordplay.entity.AuthorEntity
import yar.backend.wordplay.repo.AuthorRepository
import yar.backend.wordplay.mapping.toAuthorDto
import yar.backend.wordplay.service.AuthorService

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository,
) : AuthorService {

    override fun findByName(name: String): List<AuthorDto> =
        authorRepository.findAllByNameContaining(name).map { it.toAuthorDto() }

    override fun saveOrUpdate(author: AuthorDto): AuthorEntity {
        return if (author.id != null) {
            authorRepository.findByIdOrNull(author.id)?.let {
                it.name = author.name
                authorRepository.save(it)
            } ?: authorRepository.save(AuthorEntity(name = author.name))
        } else {
            authorRepository.save(AuthorEntity(name = author.name))
        }
    }
}
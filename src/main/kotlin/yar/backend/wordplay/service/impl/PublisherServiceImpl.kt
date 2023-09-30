package yar.backend.wordplay.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import yar.backend.wordplay.dto.PublisherDto
import yar.backend.wordplay.entity.PublisherEntity
import yar.backend.wordplay.repo.PublisherRepository
import yar.backend.wordplay.service.PublisherService

@Service
class PublisherServiceImpl(private val publisherRepository: PublisherRepository) : PublisherService {

    override fun saveOrUpdate(publisher: PublisherDto): PublisherEntity {
        return if (publisher.id != null) {
            publisherRepository.findByIdOrNull(publisher.id)?.let {
                it.name = publisher.name
                publisherRepository.save(it)
            } ?: publisherRepository.save(PublisherEntity(name = publisher.name))
        } else {
            publisherRepository.save(PublisherEntity(name = publisher.name))
        }
    }
}
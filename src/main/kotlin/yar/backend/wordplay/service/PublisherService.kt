package yar.backend.wordplay.service

import yar.backend.wordplay.dto.PublisherDto
import yar.backend.wordplay.entity.PublisherEntity


interface PublisherService {
    fun saveOrUpdate(publisher: PublisherDto): PublisherEntity
}

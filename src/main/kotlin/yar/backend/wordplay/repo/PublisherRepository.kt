package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import yar.backend.wordplay.entity.PublisherEntity

interface PublisherRepository : JpaRepository<PublisherEntity, Long> {
}
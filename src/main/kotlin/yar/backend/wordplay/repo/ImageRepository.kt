package yar.backend.wordplay.repo;

import org.springframework.data.jpa.repository.JpaRepository
import yar.backend.wordplay.entity.ImageEntity

interface ImageRepository : JpaRepository<ImageEntity, Long>
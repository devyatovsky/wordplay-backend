package yar.backend.wordplay.service

import org.springframework.web.multipart.MultipartFile
import yar.backend.wordplay.dto.ImageDto
import yar.backend.wordplay.entity.ImageEntity

interface ImageService {

    fun saveImage(file: MultipartFile): String
    fun getImage(id: Long): ImageDto?
    fun findImage(id: Long): ImageEntity?
    fun getImageIdByLink(link: String): Long
}

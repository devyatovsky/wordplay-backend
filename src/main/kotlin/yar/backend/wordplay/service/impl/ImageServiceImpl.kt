package yar.backend.wordplay.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils
import org.springframework.web.multipart.MultipartFile
import yar.backend.wordplay.dto.ImageDto
import yar.backend.wordplay.entity.ImageEntity
import yar.backend.wordplay.mapping.IMAGE_URL
import yar.backend.wordplay.mapping.toDto
import yar.backend.wordplay.repo.ImageRepository
import yar.backend.wordplay.service.ImageService
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository,
    @Value("\${upload.folder}")
    val uploadFolder: String,
) : ImageService {

    override fun saveImage(file: MultipartFile): String {
        val path = Paths.get(uploadFolder)
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        val inputStream = file.inputStream
        val filePath = path.resolve(file.originalFilename!!)
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        val image = ImageEntity(path = file.originalFilename!!, contentType = file.contentType!!)
        return IMAGE_URL + imageRepository.save(image).id
    }

    override fun getImage(id: Long): ImageDto? {
        return imageRepository.findByIdOrNull(id)?.let {
            val path = Paths.get("$uploadFolder/${it.path}" )
            val file = File(path.toUri())
            val bytes = StreamUtils.copyToByteArray(file.inputStream())
            it.toDto(it.contentType, bytes)
        }
    }

    override fun findImage(id: Long): ImageEntity? {
        return imageRepository.findByIdOrNull(id)
    }

    override fun getImageIdByLink(link: String): Long {
        val lastIndexOf = link.lastIndexOf('/') + 1
        return link.substring(lastIndexOf).toLong()
    }

}


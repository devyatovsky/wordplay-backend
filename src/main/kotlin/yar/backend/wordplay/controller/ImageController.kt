package yar.backend.wordplay.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import yar.backend.wordplay.service.ImageService
import java.io.IOException

@RestController
@RequestMapping("/api/img")
@CrossOrigin
class ImageController(private val imageService: ImageService) {


    @PostMapping("/upload")
    fun uploadImage(@RequestParam("file") file: MultipartFile): ResponseEntity<Pair<String, String>> {
        return try {
            return ResponseEntity.ok("result" to imageService.saveImage(file))
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseEntity("result" to "Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getImage(@PathVariable id: Long): ResponseEntity<ByteArray> {
        val image = imageService.getImage(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity
            .ok()
            .contentType(MediaType.valueOf(image.contentType))
            .body(image.byteArray)
    }
}
package yar.backend.wordplay.dto.response

import yar.backend.wordplay.dto.BookDto
import java.util.Date

data class RentNotificationResponse(val date: Date, val bookDto: BookDto)
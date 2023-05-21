package edu.victor.creditapplication.Exception

import java.lang.Exception
import java.sql.Timestamp
import java.time.LocalDateTime

class ExceptionDetails (
        val title: String,
        val timestamp: LocalDateTime,
        val status: Int,
        val exception: String,
        val details: MutableMap<String, String?>
)
package com.example.k42un0k0smoke.lib

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


object DurationUtil{
    fun fromLocalDateTime(startAt:LocalDateTime, endAt:LocalDateTime):Duration{
        val diff = ChronoUnit.SECONDS.between(startAt, endAt)
        return Duration.ofSeconds(diff)
    }
}

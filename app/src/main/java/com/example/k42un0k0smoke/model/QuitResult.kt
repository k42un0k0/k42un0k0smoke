package com.example.k42un0k0smoke.model

import java.time.LocalDateTime

class QuitResult(
    val uid: Int?,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val totalSavings: Long
)
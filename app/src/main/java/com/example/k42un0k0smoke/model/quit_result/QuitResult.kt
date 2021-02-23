package com.example.k42un0k0smoke.model.quit_result

import com.example.k42un0k0smoke.model.quit_result.QuitResultTotalSavings
import java.time.LocalDateTime

data class QuitResult(
    val uid: Int?,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val totalSavings: QuitResultTotalSavings
)


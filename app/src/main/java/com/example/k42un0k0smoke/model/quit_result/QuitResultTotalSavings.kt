package com.example.k42un0k0smoke.model.quit_result

import com.example.k42un0k0smoke.lib.DurationUtil
import java.time.LocalDateTime

data class QuitResultTotalSavings(val value: Long) {
    override fun toString(): String {
        return "$value 円"
    }

    companion object {
        fun compute(
            startAt: LocalDateTime,
            endAt: LocalDateTime,
            savingsPerDay: Long
        ): QuitResultTotalSavings {
            val duration = DurationUtil.fromLocalDateTime(startAt, endAt)
            return QuitResultTotalSavings(duration.toDays() * savingsPerDay)
        }
    }
}
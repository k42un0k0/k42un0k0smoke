package com.example.k42un0k0smoke.model.quit_result

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.LocalDateTime

class QuitResultTotalSavingsTest {
    @Test
    fun static_compute() {
        val startAt = LocalDateTime.now()
        val endAt = LocalDateTime.now()
        val savingsPerDay = 1000L
        var totalSavings = QuitResultTotalSavings.compute(startAt, endAt, savingsPerDay)
        assertThat(totalSavings.value, `is`(0L))

        totalSavings = QuitResultTotalSavings.compute(startAt, endAt.plusHours(1), savingsPerDay)
        assertThat(totalSavings.value, `is`(0L))

        totalSavings = QuitResultTotalSavings.compute(startAt, endAt.plusHours(13), savingsPerDay)
        assertThat(totalSavings.value, `is`(0L))

        totalSavings = QuitResultTotalSavings.compute(startAt, endAt.plusHours(24), savingsPerDay)
        assertThat(totalSavings.value, `is`(1000L))

        totalSavings = QuitResultTotalSavings.compute(startAt, endAt.plusDays(7), savingsPerDay)
        assertThat(totalSavings.value, `is`(7 * 1000L))

        totalSavings = QuitResultTotalSavings.compute(startAt, endAt.plusYears(1), savingsPerDay)
        assertThat(totalSavings.value, `is`(365 * 1000L))
    }
}
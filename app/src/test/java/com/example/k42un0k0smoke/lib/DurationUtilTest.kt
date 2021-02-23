package com.example.k42un0k0smoke.lib;

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime


class DurationUtilTest {

    @Before
    fun setUp() {
    }

    @Test
    fun static_fromLocalDateTime() {
        val startAt = LocalDateTime.now()
        val endAt = LocalDateTime.now()
        var duration = DurationUtil.fromLocalDateTime(startAt, endAt)
        assertThat(duration, `is`(Duration.ofSeconds(0)))

        duration = DurationUtil.fromLocalDateTime(startAt, endAt.plusHours(1))
        assertThat(duration, `is`(Duration.ofSeconds(3600)))
    }
}
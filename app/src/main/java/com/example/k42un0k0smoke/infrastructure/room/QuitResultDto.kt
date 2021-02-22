package com.example.k42un0k0smoke.infrastructure.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.k42un0k0smoke.model.QuitResult
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity(tableName = "result")
data class QuitResultDto(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "start_at") val startAt: Long,
    @ColumnInfo(name = "end_at") val endAT: Long,
    @ColumnInfo(name = "total_savings") val totalSavings: Long
) {
    fun toModel(): QuitResult {
        return QuitResult(
            this.uid,
            LocalDateTime.ofEpochSecond(this.startAt, 0, ZoneOffset.UTC),
            LocalDateTime.ofEpochSecond(this.endAT, 0, ZoneOffset.UTC),
            this.totalSavings
        )
    }

    companion object {
        fun fromModel(quitResult: QuitResult): QuitResultDto {
            return QuitResultDto(
                quitResult.uid ?: DtoConstants.NOT_GENERATED_PRIMARY_KEY_INT,
                quitResult.startAt.toEpochSecond(ZoneOffset.UTC),
                quitResult.endAt.toEpochSecond(ZoneOffset.UTC),
                quitResult.totalSavings
            )
        }
    }
}

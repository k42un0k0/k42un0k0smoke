package com.example.k42un0k0smoke.infrastructure.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.k42un0k0smoke.model.QuitResult

@Entity(tableName = "result_dto")
data class QuitResultDto(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "start_at") val startAt: String?,
    @ColumnInfo(name = "end_at") val endAT: String?,
    @ColumnInfo(name = "total_savings") val totalSavings: Long
) {
    fun toModel(): QuitResult {
        return QuitResult(this.uid, this.startAt, this.endAT, this.totalSavings)
    }

    companion object {
        fun fromModel(quitResult: QuitResult): QuitResultDto {
            return QuitResultDto(
                quitResult.uid ?: DtoConstants.NOT_GENERATED_PRIMARY_KEY_INT,
                quitResult.startAt,
                quitResult.endAt,
                quitResult.totalSavings
            )
        }
    }
}

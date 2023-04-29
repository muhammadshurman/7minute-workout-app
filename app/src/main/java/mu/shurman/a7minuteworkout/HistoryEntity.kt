package mu.shurman.a7minuteworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@Created_by : @Muhammad Al-Shurman on 4/29/2023
 *@E-mail: muhd.shurman@gmail.com
 */
@Entity(tableName = "history-table")
data class HistoryEntity(
    @PrimaryKey
    val date : String
)

package mu.shurman.a7minuteworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *@Created_by : @Muhammad Al-Shurman on 4/29/2023
 *@E-mail: muhd.shurman@gmail.com
 */
@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
     fun fetchALlDates():Flow<List<HistoryEntity>>
}
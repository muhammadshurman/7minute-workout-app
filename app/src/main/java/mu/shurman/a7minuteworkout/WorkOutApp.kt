package mu.shurman.a7minuteworkout

import android.app.Application

/**
 *@Created_by : @Muhammad Al-Shurman on 4/29/2023
 *@E-mail: muhd.shurman@gmail.com
 */
class WorkOutApp: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}
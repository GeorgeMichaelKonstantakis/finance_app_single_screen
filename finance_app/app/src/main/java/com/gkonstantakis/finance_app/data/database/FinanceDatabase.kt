package com.gkonstantakis.finance_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gkonstantakis.finance_app.data.database.entities.BasketsInfo
import com.gkonstantakis.finance_app.data.database.entities.ProfitInfo

@Database(entities = [BasketsInfo::class, ProfitInfo::class], version = 1)
abstract class FinanceDatabase : RoomDatabase() {

    abstract fun financeDao(): FinanceDao

    companion object {
        const val FINANCE_DATABASE: String = "finance_db"
    }
}
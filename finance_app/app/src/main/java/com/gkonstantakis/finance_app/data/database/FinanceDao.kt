package com.gkonstantakis.finance_app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gkonstantakis.finance_app.data.database.entities.BasketsInfo
import com.gkonstantakis.finance_app.data.database.entities.ProfitInfo

@Dao
interface FinanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfit(profitInfo: ProfitInfo): Long

    @Query("SELECT * FROM profit_info")
    suspend fun getProfitInfo(): List<ProfitInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBaskets(financeInfo: BasketsInfo): Long

    @Query("SELECT * FROM baskets_info")
    suspend fun getBasketsInfo(): List<BasketsInfo>
}
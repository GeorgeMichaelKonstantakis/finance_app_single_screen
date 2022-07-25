package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.models.BasketsInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getProfit(): Flow<DataState<List<ProfitInfoDomain>>>

    suspend fun getBaskets(): Flow<DataState<List<BasketsInfoDomain>>>

    suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain)

    suspend fun updateBaskets(financeInfoDomain: BasketsInfoDomain)
}
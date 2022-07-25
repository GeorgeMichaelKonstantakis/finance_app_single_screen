package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getProfit(): Flow<DataState<List<ProfitInfoDomain>>>

    suspend fun getFinance(): Flow<DataState<List<FinanceInfoDomain>>>

    suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain)

    suspend fun updateFinance(financeInfoDomain: FinanceInfoDomain)
}
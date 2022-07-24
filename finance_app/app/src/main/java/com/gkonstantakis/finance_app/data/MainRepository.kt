package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getProfit(): Flow<DataState<ProfitInfoDomain>>

    suspend fun getFinance(): Flow<DataState<FinanceInfoDomain>>

    suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain): Flow<DataState<ProfitInfoDomain>>

    suspend fun updateFinance(financeInfoDomain: FinanceInfoDomain): Flow<DataState<FinanceInfoDomain>>
}
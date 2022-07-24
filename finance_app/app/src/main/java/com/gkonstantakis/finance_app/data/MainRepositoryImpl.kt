package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.database.FinanceDao
import com.gkonstantakis.finance_app.data.mapping.FinanceInfoMapper
import com.gkonstantakis.finance_app.data.mapping.ProfitInfoMapper
import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val financeDao: FinanceDao,
    private val financeInfoMapper: FinanceInfoMapper,
    private val profitInfoMapper: ProfitInfoMapper
) : MainRepository {

    override suspend fun getProfit(): Flow<DataState<ProfitInfoDomain>> = flow {
        try {
            val databaseProfit = financeDao.getProfitInfo().first()
            val profit = profitInfoMapper.mapToDomain(databaseProfit)
            emit(DataState.SuccessGetProfit(profit))
        } catch (e: Exception) {
        }
    }

    override suspend fun getFinance(): Flow<DataState<FinanceInfoDomain>> = flow {
        try {
            val databaseFinance = financeDao.getFinanceInfo().first()
            val finance = financeInfoMapper.mapToDomain(databaseFinance)
            emit(DataState.SuccessGetFinance(finance))
        } catch (e: Exception) {
        }
    }

    override suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain): Flow<DataState<ProfitInfoDomain>> =
        flow {
            try {
                val databaseProfit = profitInfoMapper.mapFromDomain(profitInfoDomain)
                financeDao.updateProfit(databaseProfit)
                emit(DataState.SuccessUpdateProfit(profitInfoDomain))
            } catch (e: Exception) {
            }
        }

    override suspend fun updateFinance(financeInfoDomain: FinanceInfoDomain): Flow<DataState<FinanceInfoDomain>> =
        flow {
            try {
                val databaseFinance = financeInfoMapper.mapFromDomain(financeInfoDomain)
                financeDao.updateBaskets(databaseFinance)
                emit(DataState.SuccessUpdateFinance(financeInfoDomain))
            } catch (e: Exception) {
            }
        }
}
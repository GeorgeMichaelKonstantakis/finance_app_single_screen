package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.database.FinanceDao
import com.gkonstantakis.finance_app.data.mapping.BasketsInfoMapper
import com.gkonstantakis.finance_app.data.mapping.ProfitInfoMapper
import com.gkonstantakis.finance_app.data.models.BasketsInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val financeDao: FinanceDao,
    private val basketsInfoMapper: BasketsInfoMapper,
    private val profitInfoMapper: ProfitInfoMapper
) : MainRepository {

    override suspend fun getProfit(): Flow<DataState<List<ProfitInfoDomain>>> = flow {
        try {
            val databaseProfit = financeDao.getProfitInfo()
            val profit = profitInfoMapper.mapToDomainList(databaseProfit)
            emit(DataState.SuccessGetProfit(profit))
        } catch (e: Exception) {
        }
    }

    override suspend fun getBaskets(): Flow<DataState<List<BasketsInfoDomain>>> = flow {
        try {
            val databaseBaskets = financeDao.getBasketsInfo()
            val baskets = basketsInfoMapper.mapToDomainList(databaseBaskets)
            emit(DataState.SuccessGetFinance(baskets))
        } catch (e: Exception) {
        }
    }

    override suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain) {
        try {
            val databaseProfit = profitInfoMapper.mapFromDomain(profitInfoDomain)
            financeDao.updateProfit(databaseProfit)
        } catch (e: Exception) {
        }
    }

    override suspend fun updateBaskets(basketsInfoDomain: BasketsInfoDomain) {
        try {
            val databaseBaskets = basketsInfoMapper.mapFromDomain(basketsInfoDomain)
            financeDao.updateBaskets(databaseBaskets)
        } catch (e: Exception) {
        }
    }
}
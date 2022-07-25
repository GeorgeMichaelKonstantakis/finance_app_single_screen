package com.gkonstantakis.finance_app.data

import android.util.Log
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

    override suspend fun getProfit(): Flow<DataState<List<ProfitInfoDomain>>> = flow {
        try {
            val databaseProfit = financeDao.getProfitInfo()
            val profit = profitInfoMapper.mapToDomainList(databaseProfit)
            emit(DataState.SuccessGetProfit(profit))
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl","getProfit: "+e.toString())
        }
    }

    override suspend fun getFinance(): Flow<DataState<List<FinanceInfoDomain>>> = flow {
        try {
            val databaseFinance = financeDao.getFinanceInfo()
            val finance = financeInfoMapper.mapToDomainList(databaseFinance)
            emit(DataState.SuccessGetFinance(finance))
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl","getFinance: "+e.toString())
        }
    }

    override suspend fun updateProfit(profitInfoDomain: ProfitInfoDomain) {
        try {
            val databaseProfit = profitInfoMapper.mapFromDomain(profitInfoDomain)
            financeDao.updateProfit(databaseProfit)
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl","updateProfit: "+e.toString())
        }
    }

    override suspend fun updateFinance(financeInfoDomain: FinanceInfoDomain) {
        try {
            val databaseFinance = financeInfoMapper.mapFromDomain(financeInfoDomain)
            financeDao.updateBaskets(databaseFinance)
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl","updateFinance: "+e.toString())
        }
    }
}
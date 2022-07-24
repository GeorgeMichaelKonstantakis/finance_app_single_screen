package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain

sealed class DataState<out R> {

    data class SuccessGetProfit(val data: ProfitInfoDomain) : DataState<ProfitInfoDomain>()

    data class SuccessGetFinance(val data: FinanceInfoDomain) : DataState<FinanceInfoDomain>()

    data class SuccessUpdateProfit(val data: ProfitInfoDomain) : DataState<ProfitInfoDomain>()

    data class SuccessUpdateFinance(val data: FinanceInfoDomain) : DataState<FinanceInfoDomain>()

//    data class Error(val exception: Exception) : DataState()
}
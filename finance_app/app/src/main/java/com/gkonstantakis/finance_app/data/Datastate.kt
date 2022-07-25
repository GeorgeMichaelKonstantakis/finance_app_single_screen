package com.gkonstantakis.finance_app.data

import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain

sealed class DataState<out R> {

    data class SuccessGetProfit<out T>(val data: T) : DataState<T>()

    data class SuccessGetFinance<out T>(val data: T) : DataState<T>()

    data class SuccessUpdateProfit<out T>(val data: T) : DataState<T>()

    data class SuccessUpdateFinance<out T>(val data: T) : DataState<T>()

    data class Error(val exception: Exception) : DataState<Any?>()
}
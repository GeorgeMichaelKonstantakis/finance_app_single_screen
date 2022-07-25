package com.gkonstantakis.finance_app.data

sealed class DataState<out R> {

    data class SuccessGetProfit<out T>(val data: T) : DataState<T>()

    data class SuccessGetFinance<out T>(val data: T) : DataState<T>()
}
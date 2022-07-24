package com.gkonstantakis.finance_app.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkonstantakis.finance_app.data.DataState
import com.gkonstantakis.finance_app.data.MainRepositoryImpl
import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import com.gkonstantakis.finance_app.ui.utils.MainStateEventParameters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepositoryImpl
) : ViewModel() {

    private var _profitDataState: MutableLiveData<DataState<ProfitInfoDomain>> = MutableLiveData()
    val profitDataState: LiveData<DataState<ProfitInfoDomain>>
        get() = _profitDataState

    private var _financeDataState: MutableLiveData<DataState<FinanceInfoDomain>> = MutableLiveData()
    val financeDataState: LiveData<DataState<FinanceInfoDomain>>
        get() = _financeDataState

    fun setDataStateEvent(
        mainStateEvent: MainStateEvent,
        mainStateEventParameters: MainStateEventParameters
    ) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetNewBasketValues -> {
                    mainRepository.getFinance().apply {
                        _financeDataState.value = this.first()
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetNewMakeProfitValue -> {
                    mainRepository.getProfit().apply {
                        _profitDataState.value = this.first()
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.SetNewMakeProfitValue -> {
                    val newProfit = ProfitInfoDomain(
                        id = 0,
                        profit = mainStateEventParameters.profit
                    )
                    mainRepository.updateProfit(newProfit)
                }
                is MainStateEvent.SetNewBasketValues -> {
                    val newFinance = FinanceInfoDomain(
                        id = 0,
                        baskets = mainStateEventParameters.baskets
                    )
                    mainRepository.updateFinance(newFinance)
                }
            }
        }
    }



}

sealed class MainStateEvent {
    object GetNewBasketValues : MainStateEvent()
    object GetNewMakeProfitValue : MainStateEvent()
    object SetNewBasketValues : MainStateEvent()
    object SetNewMakeProfitValue : MainStateEvent()
}
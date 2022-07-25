package com.gkonstantakis.finance_app.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkonstantakis.finance_app.data.DataState
import com.gkonstantakis.finance_app.data.MainRepositoryImpl
import com.gkonstantakis.finance_app.data.models.BasketsInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import com.gkonstantakis.finance_app.ui.utils.MainStateEventParameters
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepositoryImpl
) : ViewModel() {

    private var _profitDataState: MutableLiveData<DataState<List<ProfitInfoDomain>>> = MutableLiveData()
    val profitDataState: LiveData<DataState<List<ProfitInfoDomain>>>
        get() = _profitDataState

    private var _financeDataState: MutableLiveData<DataState<List<BasketsInfoDomain>>> = MutableLiveData()
    val financeDataState: LiveData<DataState<List<BasketsInfoDomain>>>
        get() = _financeDataState

    fun setDataStateEvent(
        mainStateEvent: MainStateEvent,
        mainStateEventParameters: MainStateEventParameters?
    ) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetNewBasketValues -> {
                    mainRepository.getBaskets().onEach {
                        _financeDataState.value = it
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.GetNewMakeProfitValue -> {
                    mainRepository.getProfit().onEach {
                        _profitDataState.value = it
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.SetNewMakeProfitValue -> {
                    val newProfit = mainStateEventParameters?.let {
                        ProfitInfoDomain(
                            id = 0,
                            profit = it.profit
                        )
                    }
                    async {
                        mainRepository.updateProfit(newProfit!!)
                    }
                }
                is MainStateEvent.SetNewBasketValues -> {
                    val newBaskets = mainStateEventParameters?.let {
                        BasketsInfoDomain(
                            id = 0,
                            baskets = it.baskets
                        )
                    }
                    async {
                        mainRepository.updateBaskets(newBaskets!!)
                    }
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
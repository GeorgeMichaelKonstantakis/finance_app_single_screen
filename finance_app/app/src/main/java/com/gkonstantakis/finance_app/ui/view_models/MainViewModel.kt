package com.gkonstantakis.finance_app.ui.view_models

import androidx.lifecycle.ViewModel
import com.gkonstantakis.finance_app.data.MainRepositoryImpl

class MainViewModel(
    private val mainRepository: MainRepositoryImpl
) : ViewModel() {



}

sealed class MainStateEvent {
    object GetNewBasketValues : MainStateEvent()
    object GetNewMakeProfitValue : MainStateEvent()
    object SetNewBasketValues : MainStateEvent()
    object SetNewMakeProfitValue : MainStateEvent()
}
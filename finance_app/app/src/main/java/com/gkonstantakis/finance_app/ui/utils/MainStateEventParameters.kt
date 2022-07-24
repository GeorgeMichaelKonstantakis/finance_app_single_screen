package com.gkonstantakis.finance_app.ui.utils

import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain

data class MainStateEventParameters(
    var baskets: Int,
    var profit: Int
){
}

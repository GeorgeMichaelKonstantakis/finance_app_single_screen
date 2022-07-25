package com.gkonstantakis.finance_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gkonstantakis.finance_app.FinanceApplication
import com.gkonstantakis.finance_app.data.DataState
import com.gkonstantakis.finance_app.data.models.FinanceInfoDomain
import com.gkonstantakis.finance_app.data.models.ProfitInfoDomain
import com.gkonstantakis.finance_app.databinding.ActivityMainBinding
import com.gkonstantakis.finance_app.ui.utils.MainStateEventParameters
import com.gkonstantakis.finance_app.ui.view_models.MainStateEvent
import com.gkonstantakis.finance_app.ui.view_models.MainViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var financeApplication: FinanceApplication
    private lateinit var viewModel: MainViewModel

    private lateinit var basketEditText: TextInputEditText
    private lateinit var basketStep: TextView
    private lateinit var basketTotalAmount: TextView
    private lateinit var basketProgressBar: CircularProgressIndicator
    private lateinit var basketProgressBarText: TextView
    private lateinit var basketSubtractButton: Button
    private lateinit var basketAdditionButton: Button
    private lateinit var makeProfitEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        financeApplication = (application as FinanceApplication)

        viewModel = MainViewModel(financeApplication.mainRepository)

        initUIElems(binding)

        subscribeObservers()

        viewModel.setDataStateEvent(MainStateEvent.GetNewBasketValues,null)

        basketEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val inputText = p0.toString()
                Log.e("MainActivity","basketEditText: "+inputText.toInt())
                viewModel.setDataStateEvent(
                    MainStateEvent.SetNewBasketValues,
                    MainStateEventParameters(
                        inputText.toInt(),
                        0
                    )
                )
            }

        })
    }

    private fun initUIElems(binding: ActivityMainBinding) {
        basketEditText = binding.basketEditText
        basketStep = binding.basketStep
        basketTotalAmount = binding.basketTotalAmount
        basketProgressBar = binding.progressBar
        basketProgressBarText = binding.progressBarText
        basketSubtractButton = binding.negativeButton
        basketAdditionButton = binding.positiveButton
        makeProfitEditText = binding.profitEditText
    }

    private fun subscribeObservers() {
        viewModel.financeDataState.observe(this, Observer { datastate ->
            when (datastate) {
                is DataState.SuccessGetFinance<List<FinanceInfoDomain>> -> {
                    val baskets = datastate.data[0].baskets
                    basketEditText.setText(baskets.toString(),TextView.BufferType.EDITABLE)
                }
            }
        })
        viewModel.profitDataState.observe(this, Observer { datastate ->
            when (datastate) {
                is DataState.SuccessGetProfit<List<ProfitInfoDomain>> -> {
                    val profit = datastate.data[0].profit
                    makeProfitEditText.setText(profit.toString(),TextView.BufferType.EDITABLE)
                }
            }
        })
    }
}
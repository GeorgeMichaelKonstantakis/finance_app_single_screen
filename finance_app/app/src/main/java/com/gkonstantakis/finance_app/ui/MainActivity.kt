package com.gkonstantakis.finance_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gkonstantakis.finance_app.FinanceApplication
import com.gkonstantakis.finance_app.databinding.ActivityMainBinding
import com.gkonstantakis.finance_app.ui.view_models.MainViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {

    private lateinit var financeApplication: FinanceApplication
    private lateinit var viewModel: MainViewModel

    private lateinit var basketEditText: EditText
    private lateinit var basketStep: TextView
    private lateinit var basketTotalAmount: TextView
    private lateinit var basketProgressBar: CircularProgressIndicator
    private lateinit var basketProgressBarText: TextView
    private lateinit var basketSubtractButton: Button
    private lateinit var basketAdditionButton: Button
    private lateinit var makeProfitEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        financeApplication = (application as FinanceApplication)

        viewModel = MainViewModel(financeApplication.mainRepository)

        initUIElems(binding)
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
}
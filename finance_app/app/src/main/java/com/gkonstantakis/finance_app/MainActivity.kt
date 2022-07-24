package com.gkonstantakis.finance_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gkonstantakis.finance_app.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {

    lateinit var basketEditText: EditText
    lateinit var basketStep: TextView
    lateinit var basketTotalAmount: TextView
    lateinit var basketProgressBar: CircularProgressIndicator
    lateinit var basketProgressBarText: TextView
    lateinit var basketSubtractButton: Button
    lateinit var basketAdditionButton: Button
    lateinit var makeProfitEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
package com.example.tipapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipapp.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){

        val stringFromEditText = binding.costOfService.text.toString()
        val cost = stringFromEditText.toDoubleOrNull()

        if (cost == null){
            binding.tipResult.text = ""
            return
        }

        val tipPercentage =
            when(binding.tipOption.checkedRadioButtonId){
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
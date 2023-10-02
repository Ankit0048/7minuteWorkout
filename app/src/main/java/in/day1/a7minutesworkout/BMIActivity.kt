package `in`.day1.a7minutesworkout

import `in`.day1.a7minutesworkout.databinding.ActivityBmiBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisible: String = METRIC_UNITS_VIEW
    private var binding: ActivityBmiBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Calculate BMI"
        binding?.toolbarBmi?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.rlgrp?.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            }
            else {
                makeVisibleUSUnitsView()
            }
        }

        binding?.llDisplayBMIResult?.setOnClickListener {

            if(currentVisible == METRIC_UNITS_VIEW && validateMetricUnits()) {
                val heightValue = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val BMI = weightValue / (heightValue * heightValue)
                displayBMI(BMI)
            }
            else if (currentVisible == US_UNITS_VIEW && validateUSUnits()) {
                val heightValueFoot = binding?.etUSUnitFoot?.text.toString().toFloat()
                val heightValueInc = binding?.etUSUnitInches?.text.toString().toFloat()
                val weightValue = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val heightValue = (heightValueFoot*12*2.5 + heightValueInc*2.5)/100
                val BMI = weightValue / (heightValue * heightValue)
                displayBMI(BMI.toFloat())
            }
            else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
            binding?.etMetricUnitHeight?.text?.clear()
            binding?.etMetricUnitWeight?.text?.clear()


        }
    }
//Function to validaate the US Units
    private fun validateUSUnits(): Boolean {
    var isValid = true

    if(binding?.etMetricUnitWeight?.text.toString().isEmpty())
    {
        isValid = false
    }
    else if(binding?.etUSUnitFoot?.text.toString().isEmpty()) {
        isValid = false
    }
    else if(binding?.etUSUnitInches?.text.toString().isEmpty()) {
        isValid = false
    }
    return  isValid
    }

    //    It makes the metricView visible
    private fun makeVisibleMetricUnitsView() {
        currentVisible = US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.llViewUS?.visibility = View.GONE

        binding?.etMetricUnitHeight?.text?.clear()
        binding?.etMetricUnitWeight?.text?.clear()

        binding?.llDisplayBMI?.visibility = View.INVISIBLE
}

//Make the US
    private fun makeVisibleUSUnitsView() {
        currentVisible = US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.llViewUS?.visibility = View.VISIBLE

        binding?.etUSUnitFoot?.text?.clear()
        binding?.etUSUnitInches?.text?.clear()

        binding?.llDisplayBMI?.visibility = View.INVISIBLE
    }
//    Check whether the enterred data is valid or not
    private fun validateMetricUnits():Boolean {
        var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty())
        {
            isValid = false
        }
        else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return  isValid
    }

//    Function to display the bmi result
    private fun displayBMI(BMI: Float) {
    val text_BMI_Val = BigDecimal(BMI.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
    binding?.llDisplayBMI?.visibility = View.VISIBLE
    binding?.yourBMI?.text = text_BMI_Val

    if (BMI >= 25.00) {
        binding?.BMIcondition?.text = "OverWeight"
        binding?.descriptionBMI?.text =
            "You condition is not very well. You need to work hard to get" +
                    "to good shape"

    } else if (BMI <= 15.00) {
        binding?.BMIcondition?.text = "UnderWeight"
        binding?.descriptionBMI?.text =
            "You condition is not very well. You need to take proper nutrition" +
                    "to get good healthy condition of your body"
    } else {
        binding?.BMIcondition?.text = "Normal"
        binding?.descriptionBMI?.text = "Keep working you are in good condition"
    }

    }
}
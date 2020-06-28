package com.example.healthyfitness.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.healthyfitness.R
import com.example.healthyfitness.viewmodel.BMIViewModel
import kotlinx.android.synthetic.main.fragment_bmi.*
import java.text.DecimalFormat

class BMIFragment : Fragment() {

    private lateinit var bmiViewModel: BMIViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // bmiViewModel = ViewModelProviders.of(this).get(BMIViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bmi, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*bmiViewModel.text.observe(viewLifecycleOwner, Observer {
            tv_bmi_status.text = it
        })*/
        var weight: Double = 0.0
        var height: Double = 0.0


        btnBmiCalculate.setOnClickListener {
            var userWeight = editBMIWeight.text.toString().trim()
            var userHeight = editBMIHeight.text.toString().trim()
            var inches = editBMIinches.text.toString().trim()

            if (TextUtils.isEmpty(userWeight) && TextUtils.isEmpty(userHeight)
            ) {
                editBMIWeight.setError("Require")
                editBMIHeight.setError("Require")

            } else {

                if (switchBMIWeight.isChecked()) {
                    weight = userWeight?.toDouble() / 0.45;

                } else {
                    weight = userWeight?.toDouble()
                }


                if (switchBMIHeight.isChecked()) {
                    height = userHeight?.toDouble() / 2.54;

                } else {
                    if (TextUtils.isEmpty(inches)) {
                        height = userHeight?.toDouble() * 12
                    }else{
                        height = userHeight?.toDouble() * 12 + inches.toDouble()
                    }}
            }
            Log.d("Height>>>", height.toString())
            Log.d("Weight>>>", weight.toString())


            if (!userWeight.isEmpty() && !userHeight.isEmpty()) {
                val decimalFormat = DecimalFormat(".##")
                val bmiResult = weight!! * 703 / (height!! * height!!)

                Log.d("Bmi Result >>>", bmiResult.toString())

                val bmiStatus: String = bmiStatus(bmiResult.toString().toDouble())
                Log.d("BMI Status>>>",bmiStatus)
                tv_bmi_status.text = bmiStatus

                tv_bmi_result.text = " " + decimalFormat.format(bmiResult)
                tv_lable_result.visibility = View.VISIBLE
                tv_bmi_result.visibility = View.VISIBLE
                tv_lable_status.visibility = View.VISIBLE
                tv_bmi_status.visibility = View.VISIBLE
                floatingBmiSave.visibility = View.VISIBLE
            }
        }

        btnClearAll.setOnClickListener {
            editBMIWeight.text.clear()
            editBMIHeight.text.clear()
            editBMIinches.text.clear()
            tv_bmi_status.text = null
            tv_bmi_result.text = null
            //floatingBmiSave.visibility = View.GONE
        }

        switchBMIWeight.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                tvBMIWeightUnit.text = "kg"
            } else {
                tvBMIWeightUnit.text = "lb"
            }
        }

        switchBMIHeight.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                tvBMIunit.text = "cm"
                editBMIinches.visibility = View.GONE
                tvBMIunitInch.visibility = View.GONE
            } else {
                tvBMIunit.text = "ft"
                tvBMIunitInch.text = "in"
                editBMIinches.visibility = View.VISIBLE
                tvBMIunitInch.visibility = View.VISIBLE
            }
        }
    }

    private fun bmiStatus(bmi: Double): String {
        var status = ""
        if (bmi < 18.5){
            status = "Underweight :("
            tv_bmi_result.setTextColor(resources.getColor(R.color.colorBlue))
            tv_bmi_status.setTextColor(resources.getColor(R.color.colorBlue))
        } else if (bmi > 18.5 && bmi < 24.9) {
            status = "Normal :)"
            tv_bmi_result.setTextColor(resources.getColor(R.color.colorPrimary))
            tv_bmi_status.setTextColor(resources.getColor(R.color.colorPrimary))
        } else if (bmi > 25 && bmi < 29.9) {
            status = "Overweight :("
            tv_bmi_result.setTextColor(Color.RED)
            tv_bmi_status.setTextColor(Color.RED)
        } else if (bmi > 30 && bmi < 39.9) {
            status = "Obesity :("
            tv_bmi_result.setTextColor(Color.RED)
            tv_bmi_status.setTextColor(Color.RED)
        } else if (bmi > 40) {
            status = "Severe Obesity :("
            tv_bmi_result.setTextColor(Color.RED)
            tv_bmi_status.setTextColor(Color.RED)
        } else {
            status = "Unknown"
        }
        return status

        Log.d("Status>>>",status)
    }
}

package edu.rit.connors.brandon.loancalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.text.Editable //Interface allowing you to change the content and markup of text in a GUI.
import android.text.TextWatcher //respond to events when the user interacts with an EditText component
import android.widget.SeekBar // widget and layout for SeekBar component
import android.widget.SeekBar.OnSeekBarChangeListener //respond to user moving the SeekBar’s thumb
import android.widget.TextView //widget and layout for TextView component
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import kotlin.math.pow


class  MainActivity : AppCompatActivity() {

    private var currentLoanAmt = 0.00;
    private var currentLoanInt = 5;

    companion object{
        private val LOAN_AMT = "LOAN_AMT";
        private val LOAN_INT = "LOAN_INT";
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            currentLoanAmt = 0.00;
            currentLoanInt = 5;
        } else {
            currentLoanAmt = savedInstanceState.getDouble(LOAN_AMT);
            currentLoanInt = savedInstanceState.getInt(LOAN_INT);
        }
        view_loan_amt.addTextChangedListener(loanAmtListener)
        view_loan_int.setOnSeekBarChangeListener(loanIntListener)
    }
    private fun update(){

        val emi5 = calculateEMI(5);
        val total5 = calculateTotalAmt(emi5,5)
        view_emi_5.setText(String.format("%.02f",emi5))
        view_total_5.setText(String.format("%.02f",total5))

        val emi10 = calculateEMI(10);
        val total10 = calculateTotalAmt(emi10,10)
        view_emi_10.setText(String.format("%.02f",emi10))
        view_total_10.setText(String.format("%.02f",total10))

        val emi15 = calculateEMI(15);
        val total15 = calculateTotalAmt(emi15,15)
        view_emi_15.setText(String.format("%.02f",emi15))
        view_total_15.setText(String.format("%.02f",total15))

        val emi20 = calculateEMI(20);
        val total20 = calculateTotalAmt(emi20,20)
        view_emi_20.setText(String.format("%.02f",emi20))
        view_total_20.setText(String.format("%.02f",total20))

        val emi25 = calculateEMI(25);
        val total25 = calculateTotalAmt(emi25,25)
        view_emi_25.setText(String.format("%.02f",emi25))
        view_total_25.setText(String.format("%.02f",total25))

        val emi30 = calculateEMI(30);
        val total30 = calculateTotalAmt(emi30,30)
        view_emi_30.setText(String.format("%.02f",emi30))
        view_total_30.setText(String.format("%.02f",total30))
    }
    private fun calculateEMI(years : Int) : Double{
        val p = currentLoanAmt;
        val r = currentLoanInt.toDouble() / 1200;
        val n = years * 12;
        val rPlusOne = r + 1;
        val emi = ((p * r)*(rPlusOne.pow(n))) / (rPlusOne.pow(n) - 1);
        return emi;
    }
    private fun calculateTotalAmt(emi : Double, years: Int) : Double{
        val n = years * 12;
        val total = emi * n;
        return total;
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(LOAN_AMT, this.currentLoanAmt)
        outState.putInt(LOAN_INT, this.currentLoanInt)
    }
    private val loanIntListener = object : SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
            val min = 5;
            if(progress < min){
                currentLoanInt = 5
            }else {
                currentLoanInt = progress
            }
            val display = "$currentLoanInt%";
            view_loan_perc.setText(display);
            update();
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(p0: SeekBar?) {

        }
    }
    private val loanAmtListener = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            currentLoanAmt = try{
                s.toString().toDouble();
            }catch (e: NumberFormatException){
                0.00
            }
            update();
        }

    }

}
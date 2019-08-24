package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.renderscript.Sampler
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException
import java.util.function.ToDoubleBiFunction

class MainActivity() : AppCompatActivity(),View. OnClickListener {

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btncalculate: Button
    private lateinit var tvresult: TextView



    companion object{
        private const val STATE_RESULT="state_result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_legth);
        btncalculate = findViewById(R.id.btn_calculate);
        tvresult = findViewById(R.id.tv_result);

        btncalculate.setOnClickListener(this)

        if (savedInstanceState!=null){
            val result=savedInstanceState.getString(STATE_RESULT) as String
            tvresult.text=result
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT ,tvresult.text.toString())
    }

    override fun onClick(v: View?){
        if (v?.id== R.id.btn_calculate){
            val inputLeght =edtLength.text.toString().trim { it <= ' '}
            val inputWidth =edtWidth.text.toString().trim{ it <= ' '}
            val inputHeight =edtHeight.text.toString().trim{ it <= ' '}

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLeght)){
                isEmptyFields = true
                edtLength.error ="Field ini tidak boleh kosong"
            }

            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true
                edtWidth.error ="Field ini tidak boleh kosong"
            }

            if (TextUtils.isEmpty(inputHeight)){
                isEmptyFields = true
                edtHeight.error ="field ini tidak boleh kosong"
            }

            val  leghth = toDouble(inputLeght)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (leghth == null) {
                isInvalidDouble =true
                edtLength.error ="field ini tidak boleh kosong"

            }

            if (width == null) {
                isInvalidDouble = true
                edtWidth.error ="field ini tidak boleh kosong"
            }

            if (height == null) {
                isInvalidDouble = true
                edtHeight.error ="field ini tidak boleh kosong"
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume =leghth as Double  * width as Double  * height as Double
                tvresult.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

}
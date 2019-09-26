package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.math.RoundingMode
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btUno.setOnClickListener { appendOnExpression("1", true) }
        btDos.setOnClickListener { appendOnExpression("2", true) }
        btTres.setOnClickListener { appendOnExpression("3", true) }
        btCuatro.setOnClickListener { appendOnExpression("4", true) }
        btCinco.setOnClickListener { appendOnExpression("5", true) }
        btSeis.setOnClickListener { appendOnExpression("6", true) }
        btSiete.setOnClickListener { appendOnExpression("7", true) }
        btOcho.setOnClickListener { appendOnExpression("8", true) }
        btNueve.setOnClickListener { appendOnExpression("9", true) }
        btCero.setOnClickListener { appendOnExpression("0", true) }
        btPunto.setOnClickListener { appendOnExpression(".", true) }

        btSuma.setOnClickListener { appendOnExpression("+", false) }
        btResta.setOnClickListener { appendOnExpression("-", false) }
        btMultiplicar.setOnClickListener { appendOnExpression("*", false) }
        btDividir.setOnClickListener { appendOnExpression("/", false) }
        btParentesisIZQ.setOnClickListener { appendOnExpression("(", false) }
        btParentesisDE.setOnClickListener { appendOnExpression(")", false) }

        var memoria=0;

        btC.setOnClickListener {
            tvExpresion.text = ""
            tvResultado.text = ""
        }

        btBorrar.setOnClickListener {
            val string = tvExpresion.text.toString()
            if(string.isNotEmpty()){
                tvExpresion.text = string.substring(0,string.length-1)
            }
            tvResultado.text = ""
        }

        btIgual.setOnClickListener {
            try {

                val expresion = ExpressionBuilder(tvExpresion.text.toString()).build()
                val resultado = expresion.evaluate()
                val longResultado = resultado.toLong()
                if(resultado == longResultado.toDouble())
                    tvResultado.text = longResultado.toString()
                else
                    tvResultado.text = resultado.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }

        btMc.setOnClickListener {
            memoria = 0;
            Toast.makeText(this, "Número en memoria: " + memoria, Toast.LENGTH_SHORT).show()
        }

        btMr.setOnClickListener {
            if (memoria != 0) {
                tvExpresion.setText(memoria.toString())
                tvResultado.setText("")
            } else {
                Toast.makeText(this, "Número en memoria: " + memoria, Toast.LENGTH_SHORT).show()
            }
        }

        btMas.setOnClickListener {
            if (tvResultado.text.isNotEmpty()) {
                memoria = memoria + tvResultado.text.toString().toInt();
                Toast.makeText(this, "Número en memoria: " + memoria, Toast.LENGTH_SHORT).show()
            }
        }

        btMenos.setOnClickListener {
            if (tvResultado.text.isNotEmpty()) {
                memoria = memoria - tvResultado.text.toString().toInt();
                Toast.makeText(this, "Número en memoria: " + memoria, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        if(tvResultado.text.isNotEmpty()){
            tvExpresion.text = ""
        }

        if (canClear) {
            tvResultado.text = ""
            tvExpresion.append(string)
        } else {
            tvExpresion.append(tvResultado.text)
            tvExpresion.append(string)
            tvResultado.text = ""
        }
    }
}

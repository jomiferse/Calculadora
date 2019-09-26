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

        btUno.setOnClickListener { funcionExpresion("1", true) }
        btDos.setOnClickListener { funcionExpresion("2", true) }
        btTres.setOnClickListener { funcionExpresion("3", true) }
        btCuatro.setOnClickListener { funcionExpresion("4", true) }
        btCinco.setOnClickListener { funcionExpresion("5", true) }
        btSeis.setOnClickListener { funcionExpresion("6", true) }
        btSiete.setOnClickListener { funcionExpresion("7", true) }
        btOcho.setOnClickListener { funcionExpresion("8", true) }
        btNueve.setOnClickListener { funcionExpresion("9", true) }
        btCero.setOnClickListener { funcionExpresion("0", true) }
        btPunto.setOnClickListener { funcionExpresion(".", true) }

        btSuma.setOnClickListener { funcionExpresion("+", false) }
        btResta.setOnClickListener { funcionExpresion("-", false) }
        btMultiplicar.setOnClickListener { funcionExpresion("*", false) }
        btDividir.setOnClickListener { funcionExpresion("/", false) }
        btParentesisIZQ.setOnClickListener { funcionExpresion("(", false) }
        btParentesisDE.setOnClickListener { funcionExpresion(")", false) }

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
                tvExpresion.setText(memoria.toString())
                tvResultado.setText("")
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

    fun funcionExpresion(texto: String, limpiar: Boolean) {

        if(tvResultado.text.isNotEmpty()){
            tvExpresion.text = ""
        }

        if (limpiar) {
            tvResultado.text = ""
            tvExpresion.append(texto)
        } else {
            tvExpresion.append(tvResultado.text)
            tvExpresion.append(texto)
            tvResultado.text = ""
        }
    }
}

package co.edu.udea.analisis.saveUr

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

import java.io.File
import java.time.LocalDate


class FacturaAddActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura_add)
        val add:Button=findViewById(R.id.addF)
        add.setOnClickListener{
            val info=concat()
            println(info)
            addFactura(info)
            val intent: Intent = Intent(this,FacturasMesActivity::class.java)
            startActivity(intent)
        }

    }



    fun addFactura(texto: String) {//texto= arriendo;25;00;-1
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "facturas")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "facturas.txt")
            ficheroFisico.appendText("$texto\n")


        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun concat(): String {
        val titulo:String=findViewById<EditText>(R.id.TituloF).text.toString()
        val dia:String=findViewById<EditText>(R.id.DiaF).text.toString()
        val valor:String=findViewById<EditText>(R.id.ValorF).text.toString()
        val cuotas:String=findViewById<EditText>(R.id.CuotasF).text.toString()
        val date = LocalDate.now()
        val concatenar="${titulo};${dia};${valor};${cuotas};${date}"
        return concatenar
    }


    }

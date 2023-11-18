package co.edu.udea.analisis.saveUr

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

import java.io.File
import java.time.LocalDate

class EgresosActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_egresos)
        val buttonE: ImageButton = findViewById(R.id.backToHomeE)
        val buttonSave: Button = findViewById(R.id.SaveEgreso)
        buttonE.setOnClickListener {
            backHome()
        }
        buttonSave.setOnClickListener {
            val Titulo = findViewById<TextView>(R.id.IdTituloEgreso)
            val Valor = findViewById<TextView>(R.id.IdValorEgreso)

            val valor:String=Valor.text.toString()
            val valor1:Float= valor.toFloat()
            val date = LocalDate.now()
            //println(date)
            //ESCRIBO EL NUEVO DATO EN EL ARCHIVO
            val outString="${Titulo.text.toString()};${date};${valor1*-1}"
            //println(outString)
            Guardar(outString)
            backHome()
        }
    }
    fun backHome(){
        val intent: Intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
    fun Guardar(texto: String) {//texto= carne;30/07/22;-23000
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "datos.txt")
            ficheroFisico.appendText("$texto\n")
            println(texto)

        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }
    }
}
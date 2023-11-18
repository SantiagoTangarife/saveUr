package co.edu.udea.analisis.saveUr

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import java.io.File
import java.time.LocalDate

class IngresosActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos)
        val buttonI: ImageButton = findViewById(R.id.backToHomeI)
        val buttonSave: Button = findViewById(R.id.SaveIngreso)


        buttonI.setOnClickListener {
            backHome()
        }
        buttonSave.setOnClickListener {
            val Titulo = findViewById<TextView>(R.id.IdTituloIngreso)
            val Valor = findViewById<TextView>(R.id.IdValorIngreso)
            val valor:String=Valor.text.toString()
            val date = LocalDate.now()
            val ingresoFijo=findViewById<CheckBox>(R.id.ingresoFijo)     //Es un Ingreso Fijo?
            val inFijo:String=ingresoFijo.isChecked().toString()
            println("#############################################################")
            //println(inFijo)

            //ESCRIBO EL NUEVO DATO EN EL ARCHIVO
            val outString="${Titulo.text.toString()};${date};${valor};${inFijo}"
            println(outString)
            Guardar(outString)

            backHome()
        }
    }
    fun backHome(){
        val intent: Intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
    fun Guardar(texto: String) {//texto= Pago;30/07/22;23000
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "datos.txt")



            ficheroFisico.appendText("$texto\n")


        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }
    }

}

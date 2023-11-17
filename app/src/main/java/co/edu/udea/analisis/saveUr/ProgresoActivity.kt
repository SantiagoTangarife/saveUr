package co.edu.udea.analisis.saveUr

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_progreso.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.util.*

class ProgresoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progreso)
        val back=findViewById<ImageButton>(R.id.backToHome)
        //PRIMERO CAPTURO LA LISTA DE MIS ELEMENTOS Y DEBO TENER UN LISTOF CON EL SIGUIENTE FORMATO [TITULO,VALOR,(INGRESO/EGRESO),FECHA] //val elemento=Elemento("carne",7500.0f,R.drawable.egreso, dato)
        val lista= GenerarProgreso()
        val adapter=ElementosAdapter(this,lista)
        ListView.adapter=adapter

        back.setOnClickListener{
            val intent: Intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun Cargar() : String {
        var texto = ""
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            val ficheroFisico = File(miCarpeta, "datos.txt")
            val fichero = BufferedReader(
                InputStreamReader(FileInputStream(ficheroFisico))
            )
            texto = fichero.use(BufferedReader::readText)
        }
        catch (e : Exception) {
            // Nada
        }

        return texto

    }


    fun GenerarProgreso(): ArrayList<Elemento> {
        val a=Cargar()
        val money= arrayListOf<Elemento>()
        val line=a.split("\n")
        for(i in line){
            if(i!=""){
                val t=i.split(";")
                //println(t)   //0=titulo, 1 =Fecha; 2=Valor
                val elemento:Elemento;
                if(t[2].toFloat()>0.0f){
                     elemento=Elemento(t[0],t[2].toFloat(),R.drawable.ingreso, t[1])
                }
                else{
                     elemento=Elemento(t[0],t[2].toFloat(),R.drawable.egreso, t[1])
                }
                money.add(elemento)}
        }
        return money.reversed() as ArrayList<Elemento>
    }

}
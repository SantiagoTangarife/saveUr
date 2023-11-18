package co.edu.udea.analisis.saveUr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.FOCUSABLES_TOUCH_MODE
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AhorroProgramadoActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahorro_programado)
        val buttonSA: Button = findViewById(R.id.AhorroSave)
        var inicio=CargarDb()
        if(inicio== listOf("")){
            inicio= listOf("0","0")
        }

        findViewById<EditText>(R.id.CantidadMonetariaProgramada).hint="$${inicio[0]}"  //dinero
        findViewById<EditText>(R.id.FechasProgramadas).hint="${inicio[1]}"            //cuotas
        buttonSA.setOnClickListener{
            if(modificarAhorro()){////CLICK EN E BOTON GUARDAR
                val intent: Intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)}
        }
    }


    fun onCheckboxClicked(view: View) {
        val modif=findViewById<CheckBox>(R.id.Edit)     //Habilitar Edicion
        val ahorro=findViewById<EditText>(R.id.CantidadMonetariaProgramada)  //dinero
        val meses=findViewById<EditText>(R.id.FechasProgramadas)            //cuotas
        if(modif.isChecked()){
            ahorro.isFocusableInTouchMode=true
            meses.isFocusableInTouchMode=true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun modificarAhorro(): Boolean {
        //CAPTURO LOS DATOS EDITADOS
        val ahorro=findViewById<EditText>(R.id.CantidadMonetariaProgramada).text.toString().toFloat()  //dinero
        val meses=findViewById<EditText>(R.id.FechasProgramadas).text.toString().toInt()           //cuotas
        //evaluo si es posible ese ahorro mensual
        val capacidad=capacidadMes()
        val dinero =ahorro/meses
        println("capacidad ${capacidad}\n" +
                "dinero ${dinero} ")
        if(capacidad>=dinero){
            //AQUI DEBO CREAR UNA BASE DE DATOS PARA QUE ADQUIERA DICHOS VALORES
            EditarDbA(ahorro,meses)
            return(true)
        }
        else{
            val mensaje = findViewById<TextView>(R.id.mensaje)
            mensaje.setBackgroundColor(resources.getColor(R.color.red))
            mensaje.text="\n Lo siento, pero parece imposible alcanzar este objetivo de ahorro con los valores proporcionados. Te recomendaría considerar las siguientes opciones:\n" +
                    "\n" +
                    "    *Aumenta el número de meses.\n" +

                    "    *Disminuye el valor del objetivo.\n" +

                    "    *Incrementa tus ingresos fijos.\n"+
                    "\n" +
                    "Pues para esta cantidad de meses tu ahorro maximo seria de $${capacidad*meses*0.9}"
            return(false)
        }

    }

    fun CargarDb() : List<String> {
        var texto = ""
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "ahorro")//CREO ARCHIVO Ruta
            val ficheroFisico = File(miCarpeta, "ahorro.txt")//BUSCO ARCHIVO
            val fichero = BufferedReader(       //Leer archivo
                InputStreamReader(FileInputStream(ficheroFisico))
            )
            texto = fichero.use(BufferedReader::readText)
        }
        catch (e : Exception) {
            // Nada
        }
        //println("#################################################################")
        //println(texto)
        if(!texto.isEmpty()){
            val t=texto.split("\n")
            //println(t)
            val p=(t.size)-2
            //println(t[p].split(","))
            return t[p].split(",")
        }
        else{
            return listOf("")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun EditarDbA(ahorro:Float, meses:Int){

        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "ahorro")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "ahorro.txt")
            val date= LocalDate.now()    //fecha actual para comparar
            ficheroFisico.appendText("$ahorro,$meses,$date\n")

                //CADA MES DEBE DISMINUIR EL VALOR DE LA VARIABLE MES EN 1 UNIDAD
        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }

    }

    fun capacidadMes(): Float {
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "datos")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val miCarpeta2 = File(rutaSD, "facturas")
            if (!miCarpeta2.exists()) {
                miCarpeta2.mkdir()
            }

            var ingresos=0f
            val egresosdata = File(miCarpeta2, "facturas.txt")
            val ingresosdata = File(miCarpeta, "datos.txt")

            val lineas1 = ingresosdata.readLines()
            lineas1.forEach { linea ->
                val campos = linea.split(";")  // Dividir la línea en campos

                if(campos.size==4){
                    val ultimocampo=campos.last()
                    if(ultimocampo.toBoolean()==true){
                        ingresos += campos[2].toFloat()
                    }
                }

            }



            var egresos=0f


            var lines = egresosdata.readLines()
            lines.forEach { line ->
                val campos = line.split(";")  // Dividir la línea en campos
                egresos += campos[2].toFloat()

            }



            return (ingresos - egresos)
        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
            return (0f)
        }

    }


}
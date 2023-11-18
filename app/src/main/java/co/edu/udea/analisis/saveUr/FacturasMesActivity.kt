package co.edu.udea.analisis.saveUr

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi

import kotlinx.android.synthetic.main.activity_facturas_mes.*
import kotlinx.android.synthetic.main.activity_progreso.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.util.ArrayList


class FacturasMesActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actualizar()
        setContentView(R.layout.activity_facturas_mes)
        val add:ImageView=findViewById(R.id.add)
        add.setOnClickListener{
            val intent: Intent = Intent(this,FacturaAddActivity::class.java)
            startActivity(intent)
        }


        //val factura=Factura("Arriendo","${25} de cada mes",650F,-1,R.drawable.egreso)
        //val factura2=Factura("Pago","${15} de cada mes", 650F,-1,R.drawable.egreso)

        val listaFacturas= GenerarLista()
        if(listaFacturas.size==0){
            val factura=Factura("----","-- de cada mes","---","-1",R.drawable.egreso)
            listaFacturas.add(factura)
        }
        val adapter=FacturasAdapter(this,listaFacturas)
        listaF.adapter=adapter


    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))}

    fun CargarF() : List<String> {
        var texto = ""
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "facturas")//CREO ARCHIVO Ruta
            val ficheroFisico = File(miCarpeta, "facturas.txt")//BUSCO ARCHIVO
            val fichero = BufferedReader(       //Leer archivo
                InputStreamReader(FileInputStream(ficheroFisico))
            )
            texto = fichero.use(BufferedReader::readText)
        }
        catch (e : Exception) {
            // Nada
        }

        val t=texto.split("\n")
        //t=[["titulo;dia del mes;valor a pagar;cuotas restantes"],["titulo;dia del mes;valor a pagar;cuotas restantes"]]

        //println(t[p].split(","))
        return t

    }

    fun GenerarLista(): ArrayList<Factura> {
        val line=CargarF()
        val money= arrayListOf<Factura>()

        for(i in line){
            if(i!=""){


                val t=i.split(";")
                //println(t)   //0=titulo, 1 =DiaDelMes; 2=Valor;3Cuotas Restantes
                //val factura:Factura;
                var c=""
                var k="--------"
                if (t[3]!="-1") {
                     c = t[3]
                }
                if(t[2]!="00"){
                    k=t[2]
                }
                val factura=Factura(t[0],"${t[1]} de cada mes",k,c,R.drawable.egreso)
                money.add(factura)}
        }
        return money
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizar(){
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "facturas")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val inputFile = File(miCarpeta, "facturas.txt")

            // Día del mes en el que deseas disminuir la cuota

            val date= LocalDate.now()    //fecha actual para comparar
            val fecha=date.toString().split("-")
            val lines = inputFile.readLines()
            inputFile.writeText("")  // Vaciar el archivo original

            lines.forEach { line ->
                val campos = line.split(";")  // Dividir la línea en campos
                //println(campos.size)

                if (campos.size == 5) {
                    val titulo = campos[0]
                    val dia = campos[1].toInt()
                    val valor = campos[2].toFloat()
                    val cuotas = campos[3].toInt()
                    val fechaCreacion = campos[4].toString().split("-")
                    //println("$dia --> ${fecha[2]}")

                    //println("${fechaCreacion[1]} --> ${fecha[1]}")
                    // Verificar si el día coincide con el día elegido

                    if (dia < fecha[2].toInt()) {
                        println("cumplio 1")
                        if (fechaCreacion[1].toInt()<fecha[1].toInt() || fechaCreacion[0].toInt()<fecha[0].toInt()){
                            println("cumplio 2")
                            // Disminuir en 1 el valor de la cuota
                            val nuevoCuotas = cuotas - 1


                            // Crear una nueva línea con los campos actualizados
                            val nuevaLinea="${titulo};${dia};${valor};${nuevoCuotas};${date}"


                            // Escribir la línea actualizada en el archivo original
                            inputFile.appendText("$nuevaLinea\n")
                        }
                        else {
                            // Si el día no coincide, escribir la línea original en el archivo original
                            inputFile.appendText("$line\n")
                        }
                    } else {
                        // Si el día no coincide, escribir la línea original en el archivo original
                        inputFile.appendText("$line\n")
                    }
                } else {
                    // Si la línea no tiene el formato esperado, escribirla tal como está en el archivo original
                    inputFile.appendText("$line\n")
                }
            }

        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }
    }




}
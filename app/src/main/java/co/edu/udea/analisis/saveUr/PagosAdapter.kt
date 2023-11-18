package co.edu.udea.analisis.saveUr

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import kotlinx.android.synthetic.main.item_pago.view.*
import java.time.LocalDate


class PagosAdapter(private val mContext: Context, private val ListaPagos:List<Pago> ):
    ArrayAdapter<Pago>(mContext,0,ListaPagos) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_pago, parent, false)
        val factura = ListaPagos[position]
        layout.nameP.text = factura.Amigo
        layout.valorPrestamo.text = factura.valor.toString()
        layout.pagoParcial.setTag(position)
        layout.pagoParcial.setOnClickListener {
            val name = layout.nameP.text.toString()//nombre del amigo
            val dinero =layout.valorPrestamo.text.toString()//cuanto le preste
            val abono = layout.CantidadP.text.toString()//abono
            println(dinero)
            var data= EditarDbA(name,dinero,abono)
            EditarDb(data);

            // Actualizar la lista de pagos en el adaptador
            val intent: Intent = Intent(mContext,PagosActivity::class.java)
            mContext.startActivity(intent)

        }

        return layout
    }


    fun EditarDbA(amigo: String, prestamo: String, abono: String): ArrayList<String> {
        var texto = ""
        try {
            val rutaSD = mContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "deudores")//CREO ARCHIVO Ruta
            val ficheroFisico = File(miCarpeta, "deudores.txt")//BUSCO ARCHIVO
            val fichero = BufferedReader(       //Leer archivo
                InputStreamReader(FileInputStream(ficheroFisico))
            )
            texto = fichero.use(BufferedReader::readText)

        } catch (e: Exception) {
            // Nada
        }

        val data = ArrayList<String>()
        if (!texto.isEmpty()) {
            val t = texto.split("\n")
            for (i in t) {

                var k = i.split(";")
                //println(prestamo)
                //println(k[1].toFloat().equals(prestamo.toFloat()))

                if (k[0].equals(amigo) &&(k[1].toFloat().equals(prestamo.toFloat()))) {
                    val Resultado = prestamo.toFloat() - abono.toFloat()

                    val texto = "${k[0]};${Resultado}"
                    data.add(texto)
                } else {
                    data.add(i)
                }
            }
        }
        return data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun EditarDb(data:ArrayList<String>){
        try {
            val rutaSD = mContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "deudores")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "deudores.txt")
            ficheroFisico.writeText("")
            for(i in data){
                ficheroFisico.appendText("$i\n")
            }
            //CADA MES DEBE DISMINUIR EL VALOR DE LA VARIABLE MES EN 1 UNIDAD
        }
        catch (e: Exception) {
            Toast.makeText(mContext,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }

    }


}
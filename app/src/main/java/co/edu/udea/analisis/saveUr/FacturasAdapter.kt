package co.edu.udea.analisis.saveUr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.item_factura.view.*
import kotlinx.android.synthetic.main.item_producto.view.*

class FacturasAdapter(private val mContext:Context,private val ListaFacturas:List<Factura> ):ArrayAdapter<Factura>(mContext,0,ListaFacturas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_factura,parent,false)

        val factura=ListaFacturas[position]

        layout.name.text=factura.Titulo
        layout.date.text=factura.Fecha
        layout.valorPagar.text= "$${factura.Costo}"
        layout.cuotasP.text="${factura.Cuota}"
        layout.imagenF.setImageResource(factura.Imagen)
        return layout
    }
}
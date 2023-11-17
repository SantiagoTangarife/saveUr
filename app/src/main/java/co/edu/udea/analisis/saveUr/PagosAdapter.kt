package co.edu.udea.analisis.saveUr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_factura.view.*
import kotlinx.android.synthetic.main.item_pago.view.*

class PagosAdapter(private val mContext: Context, private val ListaPagos:List<Pago> ):
    ArrayAdapter<Pago>(mContext,0,ListaPagos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_pago,parent,false)

        val factura=ListaPagos[position]

        layout.nameP.text=factura.Amigo
        layout.valorPrestamo.text=factura.valor.toString()

        return layout
    }
}
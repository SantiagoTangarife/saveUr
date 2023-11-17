package co.edu.udea.analisis.saveUr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.item_producto.view.*

class ElementosAdapter(private val mContext:Context,private val ListaElementos:List<Elemento>):ArrayAdapter<Elemento>(mContext,0,ListaElementos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_producto,parent,false)

        val elemento=ListaElementos[position]

        layout.titulo.text=elemento.Titulo
        layout.valor.text="$${elemento.Valor}"
        layout.imageView3.setImageResource(elemento.Imagen)
        layout.fecha.text=elemento.Fecha


        return layout
    }

}
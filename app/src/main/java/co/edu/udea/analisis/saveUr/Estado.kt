package co.edu.udea.analisis.saveUr

import android.animation.ObjectAnimator
import android.graphics.Color
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_home.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import kotlin.math.abs

abstract class Estado() {

    fun barra(InEg: ArrayList<Float>,progressBar2: ProgressBar,CargarDbAh:List<String>): Estado {
        val cuota=CargarDbAh
        progressBar2.max= InEg[0].toInt()
        val currentProgress= abs(InEg[1].toInt()) +(cuota[0].toFloat()/cuota[1].toInt()).toInt()
        ObjectAnimator.ofInt(progressBar2,"progress",currentProgress).setDuration(1).start()
        val In=InEg[0]
        val Eg= abs(InEg[1]) +1+(cuota[0].toFloat()/cuota[1].toInt())
        //color(Eg/In)
        //color(progressBar2)
        var estado:Estado=Normal()
        if((Eg/In)<0.35){
            estado=Normal()        }

        else if((Eg/In)>0.80){
            estado=Critico()        }
        else if((Eg/In)>0.55){
            estado=Grave()        }
        else if((Eg/In)>0.35){
            estado=Aceptable()  }


        return  estado

    }


   open fun color(ProgressBar2:ProgressBar) {
       ProgressBar2.progressDrawable.setColorFilter(Color.GREEN,android.graphics.PorterDuff.Mode.SRC_IN)
    }



}
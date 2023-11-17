package co.edu.udea.analisis.saveUr

import android.graphics.Color
import android.widget.ProgressBar

class Critico: Estado() {
    override fun color(tinte: ProgressBar){
        tinte.progressDrawable.setColorFilter(Color.RED,android.graphics.PorterDuff.Mode.SRC_IN)
    }
}
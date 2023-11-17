package co.edu.udea.analisis.saveUr

import android.graphics.Color
import android.widget.ProgressBar

class Aceptable : Estado() {
    override fun color(tinte: ProgressBar){
        tinte.progressDrawable.setColorFilter(Color.BLUE,android.graphics.PorterDuff.Mode.SRC_IN)
    }
}
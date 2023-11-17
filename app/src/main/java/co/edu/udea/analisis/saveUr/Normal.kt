package co.edu.udea.analisis.saveUr

import android.graphics.Color
import android.widget.ProgressBar

class Normal: Estado() {
    override fun color(tinte:ProgressBar){
        tinte.progressDrawable.setColorFilter(Color.GREEN,android.graphics.PorterDuff.Mode.SRC_IN)
    }
}
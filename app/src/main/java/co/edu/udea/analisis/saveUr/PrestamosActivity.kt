package co.edu.udea.analisis.saveUr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class PrestamosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prestamos)
        val back:ImageButton=findViewById(R.id.prestamosBack)
        val addPago:Button=findViewById(R.id.aggPago)
        val addPrestamo:Button=findViewById(R.id.aggPrestamo)

        back.setOnClickListener{
            val intent: Intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
        addPago.setOnClickListener{
            val intent: Intent = Intent(this,PagosActivity::class.java)
            startActivity(intent)
        }
        addPrestamo.setOnClickListener{
            val intent: Intent = Intent(this,PrestamoAddActivity::class.java)
            startActivity(intent)
        }
    }
}
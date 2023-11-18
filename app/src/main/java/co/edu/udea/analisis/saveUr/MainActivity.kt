package co.edu.udea.analisis.saveUr

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts




import java.io.BufferedReader
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonI: Button = findViewById(R.id.google)
        buttonI.setOnClickListener {

        }
        val buttonII: Button = findViewById(R.id.button)
        buttonII.setOnClickListener {
            onClick()
        }


    }


    fun onClick() {
        val cambio: Intent = Intent(this, HomeActivity::class.java)
        startActivity(cambio)

    }
}


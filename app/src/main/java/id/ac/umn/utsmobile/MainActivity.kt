package id.ac.umn.jam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        val clockTextView = findViewById<TextView>(R.id.clock)
//        clock = WaktuGame(clockTextView)

        val swipeButton: Button = findViewById(R.id.buttonSwipe1)
        swipeButton.setOnClickListener{
            gantiGambar()
        }
        val swipesButton: Button = findViewById(R.id.buttonSwipe2)
        swipesButton.setOnClickListener{
            gantiGambar()
        }
        val test: Button = findViewById(R.id.buttonLogin)
        test.setOnClickListener {
            val inputNama = findViewById<EditText>(R.id.inputNama)
            val pilihKarakter = findViewById<ImageView>(R.id.char1)

            val inputText = inputNama.text.toString()
            val intent = Intent(this, GameDashboard::class.java).apply {
                putExtra("EXTRA_TEXT", inputText)
            }
            startActivity(intent)
        }


    }

    private fun gantiGambar() {
        val dice = PilihChar(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.char1)
        when (diceRoll) {
            1 -> diceImage.setImageResource(R.drawable.character_boy)
            2 -> diceImage.setImageResource(R.drawable.character_girl)
        }

    }

}






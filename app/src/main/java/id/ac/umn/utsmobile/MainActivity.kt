package id.ac.umn.utsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Find the TextView that will display the time
        val timeTextView = findViewById<TextView>(R.id.timeTextView)

        // Update the time
        updateTime()
    }
    private var timeMultiplier = 10
    private fun updateTime() {
        val timeTextView = findViewById<TextView>(R.id.timeTextView)
        val calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        var minute = calendar.get(Calendar.MINUTE)
        var second = calendar.get(Calendar.SECOND)

        // Calculate the new time, where 1 second = 1 minute in the game
        val timeMultiplier = 60
        val newSecond = (second + 1) * timeMultiplier
        if (newSecond >= 60) {
            minute += newSecond / 60
            second = newSecond % 60
        } else {
            second = newSecond
        }
        if (minute >= 60) {
            hour += minute / 60
            minute %= 60
        }

        // Format the time string as "HH:mm:ss"
        val timeString = String.format("%02d:%02d:%02d", hour, minute, second / timeMultiplier)
        timeTextView.text = timeString

        // Update the time again after 1 second
        timeTextView.postDelayed({ updateTime() }, (1000 / timeMultiplier).toLong())
    }
}

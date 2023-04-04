package id.ac.umn.jam

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

class WaktuGame( private val clock: TextView, private val layout: View) {
    private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val handler = Handler(Looper.getMainLooper())




    //Membuat kecepatan 1 menit = 60 detik
    private val speedRatio = 60
    //Variabel yang menunjukkan waktu
    private var currentTime = Calendar.getInstance()


    init {
        handler.post(object : Runnable {
            override fun run() {
                currentTime.add(Calendar.SECOND, speedRatio)

                // Kondisi dimana suasana jam disesuaikan dengan text
                val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
                val timeOfDay = when (currentHour) {
                    in 6..11 -> {
                        clock.setTextColor(Color.BLACK)
                        "pagi"
                    }
                    in 12..17 -> {
                        clock.setTextColor(Color.BLACK)
                        "siang"
                    }
                    else -> {
                        clock.setTextColor(Color.GREEN)
                        "malam"
                    }
                }

                val background = when (currentHour) {
                    in 6..11 -> R.drawable.background_day
                    in 12..17 -> R.drawable.background_day
                    else -> R.drawable.background_night
                }
                layout.setBackgroundResource(background)


                // format jam dan suasana ke dalam teks
                val clockText = dateFormat.format(currentTime.time)
                val timeOfDayText = "Selamat $timeOfDay "
                val fullText = "$timeOfDayText\n$clockText"

                // tampilan jam dan suasana pada TextView
                clock.text = fullText

                handler.postDelayed(this, 1000) //  update setiap 1 detik
            }
        })
    }
    fun addTime(){
        //3600 = 1 jam jadi dia langsung skip time 1 jam
        currentTime.add(Calendar.SECOND, 7200)
    }

    fun stop() {
        handler.removeCallbacksAndMessages(null)
    }
}

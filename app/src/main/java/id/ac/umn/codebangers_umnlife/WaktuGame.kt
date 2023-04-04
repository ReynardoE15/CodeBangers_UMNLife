package id.ac.umn.jam

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class WaktuGame(private val clock: TextView, progressPengetahuan: ProgressBar, progressKenyang: ProgressBar, progressEnergi: ProgressBar, progressMotivasi: ProgressBar, private val mahasiswa: Mahasiswa) {
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
                progressPengetahuan.progress = mahasiswa.pengetahuan.toInt()
                progressKenyang.progress = mahasiswa.kenyang.toInt()
                progressEnergi.progress = mahasiswa.energi.toInt()
                progressMotivasi.progress = mahasiswa.motivasi.toInt()

                // Kondisi dimana suasana jam disesuaikan dengan text
                val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
                val timeOfDay = when (currentHour) {
                    in 6..11 -> "pagi"
                    in 12..17 -> "siang"
                    else -> "malam"
                }

                // format jam dan suasana ke dalam teks
                val clockText = dateFormat.format(currentTime.time)
                val timeOfDayText = "Selamat $timeOfDay"
                val fullText = "$timeOfDayText\n$clockText"

                Log.d(TAG, "Pengetahuan: ${mahasiswa.pengetahuan}")

                // tampilan jam dan suasana pada TextView
                clock.text = fullText

                handler.postDelayed(this, 1000) //  update setiap 1 detik
                mahasiswa.kurangiPengetahuan(1)
                mahasiswa.kurangiKenyang(1)
                mahasiswa.kurangiEnergi(1)
                mahasiswa.kurangiMotivasi(1)
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

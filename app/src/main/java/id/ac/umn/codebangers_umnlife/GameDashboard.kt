package id.ac.umn.jam

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import id.ac.umn.jam.databinding.ActivityMainBinding


class GameDashboard : AppCompatActivity() {
    private var currentSemester: Int = 1
    private lateinit var clock: WaktuGame
    private lateinit var semesterTextView: TextView
    private lateinit var mahasiswa: Mahasiswa
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mahasiswa = Mahasiswa("Andrew")
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressPengetahuan.progress = mahasiswa.pengetahuan.toInt()
        binding.progressKenyang.progress = mahasiswa.kenyang.toInt()
        binding.progressEnergi.progress = mahasiswa.energi.toInt()
        binding.progressMotivasi.progress = mahasiswa.motivasi.toInt()
        semesterTextView = findViewById(R.id.textView)

        val textView = findViewById<TextView>(R.id.textView)
        val inputText = intent.getStringExtra("EXTRA_TEXT")
        textView.text = inputText

        val clockTextView = findViewById<TextView>(R.id.clock)
        clock = WaktuGame(clockTextView, binding.progressPengetahuan, binding.progressKenyang, binding.progressEnergi, binding.progressMotivasi,mahasiswa)

        //  tombol tambah waktu
        binding.buttonLearn.setOnClickListener{
            if(binding.progressPengetahuan.progress == 0)
            { clock.addTime()
                Log.d(TAG, "kenyang before: ${mahasiswa.kenyang}")
                StartProgressBar(binding.progressPengetahuan, 1) } }

        binding.buttonEat.setOnClickListener {
            if(binding.progressKenyang.progress == 0)
            { clock.addTime()
                StartProgressBar(binding.progressKenyang, 2) } }

        binding.buttonPlay.setOnClickListener {
            if(binding.progressMotivasi.progress == 0)
            { clock.addTime()
                StartProgressBar(binding.progressMotivasi,3) } }

        binding.buttonSleep.setOnClickListener {
            if(binding.progressEnergi.progress == 0) {
                clock.addTime()
                StartProgressBar(binding.progressEnergi, 4)
            } }
    }
    private fun StartProgressBar(progressBar: ProgressBar, tipeStatus: Int) {
        mahasiswa.start = false
        val progressTimer = object : CountDownTimer(5000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = ((5000 - millisUntilFinished) / 50).toInt()
            }

            override fun onFinish() {
                currentSemester++
                mahasiswa.start = true
                when(tipeStatus)
                {
                    1 -> {
                        mahasiswa.pengetahuan = progressBar.progress.toDouble()
                        mahasiswa.kurangiMotivasi(5301)
                        mahasiswa.kurangiKenyang(3501)
                        mahasiswa.kurangiEnergi(7101)
                        Log.d(TAG, "kenyang: after ${mahasiswa.kenyang}")
                    }
                    2 -> {
                        mahasiswa.kenyang = progressBar.progress.toDouble()
                        mahasiswa.motivasi += 30
                        mahasiswa.kurangiPengetahuan(3501)
                        mahasiswa.kurangiEnergi(7101)
                    }
                    3 -> {
                        mahasiswa.motivasi = progressBar.progress.toDouble()
                        mahasiswa.kurangiPengetahuan(3501)
                        mahasiswa.kurangiEnergi(5301)
                        mahasiswa.kurangiKenyang(3501)
                    }
                    4 -> {
                        mahasiswa.energi = progressBar.progress.toDouble()
                        mahasiswa.motivasi += 30
                        mahasiswa.kurangiPengetahuan(3501) }
                }
                semesterTextView.text = "Semester: $currentSemester, Progress ${progressBar.progress}"
            }
        }

        progressTimer.start()
    }

    override fun onDestroy() {
        clock.stop()
        super.onDestroy()
    }
}
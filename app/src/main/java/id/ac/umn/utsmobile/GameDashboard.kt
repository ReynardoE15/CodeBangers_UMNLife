package id.ac.umn.jam

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Layout
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


class GameDashboard : AppCompatActivity() {
    private lateinit var clock: WaktuGame
    private lateinit var layout: ConstraintLayout

    private lateinit var progressBar1: ProgressBar
    private lateinit var semesterTextView: TextView
    private var currentSemester: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar1 = findViewById(R.id.progressBar)
        semesterTextView = findViewById(R.id.textView)


        val textView = findViewById<TextView>(R.id.textView)
        val inputText = intent.getStringExtra("EXTRA_TEXT")
        textView.text = inputText
        val backgrounSuasana = findViewById<View>(R.id.bg)


        val clockTextView = findViewById<TextView>(R.id.clock)
         clock = WaktuGame(clockTextView,backgrounSuasana)


        //  tombol tambah waktu
        findViewById<Button>(R.id.button).setOnClickListener {
            clock.addTime()
            startProgressBar(progressBar1)
        }
//
    }
    private fun startProgressBar(progressBar: ProgressBar) {
        progressBar.progress = 0

        val timer = object : CountDownTimer(5000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((5000 - millisUntilFinished) / 50).toInt()
                progressBar.progress = progress
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            }

            override fun onFinish() {
                    currentSemester++
                    semesterTextView.text = "Semester: $currentSemester"
            }
        }

        timer.start()
    }



    override fun onDestroy() {
        clock.stop()
        super.onDestroy()
    }
}
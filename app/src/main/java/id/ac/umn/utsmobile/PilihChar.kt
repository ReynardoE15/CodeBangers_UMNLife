package id.ac.umn.jam

import android.widget.ImageView

class PilihChar(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
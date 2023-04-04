package id.ac.umn.jam

class Mahasiswa(
    private val nama:String,var kenyang: Double = 50.0,
    var energi: Double = 50.0,
    var motivasi: Double = 50.0, var pengetahuan: Double = 0.0)
{
    //sdsd
    var start:Boolean = true
    fun kurangiPengetahuan(angka: Int = 0, pengurang:Int = 0) {
        if(pengetahuan  <= 0 || !start) return
        pengetahuan -= ((angka * (1/180.0)) + 0.55)
    }

//    fun kurangiKenyang(angka: Int = 0, pengurang: Int = 0) {
//        if(kenyang <= 0 || !start) return
//        kenyang -= ((angka * 0.01) + (0.55 * angka))
//        kenyang -= pengurang
//    }

    fun kurangiKenyang(angka: Int) {
        if(kenyang <= 0 || !start) return
        kenyang -= ((angka * (1/180.0)) + 0.55)
    }

    fun kurangiEnergi(angka: Int= 0) {
        if(energi <= 0 || !start) return
        energi -= ((angka * (1/180.0)) + 0.55)
    }

    fun kurangiMotivasi(angka: Int= 0) {
        if(motivasi <= 0 || !start) return
        motivasi -= ((angka * (1/180.0)) + 0.55)
    }
}
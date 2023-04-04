package id.ac.umn.jam

class Mahasiswa(val nama:String, var kenyang: Int = 50, var energi: Int=50, var motivasi:Int = 50, var pengetahuan: Int = 0)
{
 fun kurangiPengetahuan(angka:Int){
     pengetahuan -= angka
 }
}
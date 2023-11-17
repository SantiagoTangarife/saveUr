package co.edu.udea.analisis.saveUr

import java.io.File
import java.io.InputStream

class Dinero(){//dinero:Float,ingresos:ArrayList<Float>,egresos:ArrayList<Float>,Tingresos:ArrayList<Float>,Tegresos:ArrayList<Float>) {

    fun RegistroMes(Lista:ArrayList<String>): ArrayList<Float> {//recive Texto[2]
        var totalIng=0.0f
        var totalEgr=0.0f
        for(i in Lista.indices){
            if(Lista[i].toFloat()>0.0f){
                totalIng+=Lista[i].toFloat()
            }
            else{
                totalEgr+=Lista[i].toFloat()
            }
        }
        val m= arrayListOf(totalIng,totalEgr)
        return m
    }



}
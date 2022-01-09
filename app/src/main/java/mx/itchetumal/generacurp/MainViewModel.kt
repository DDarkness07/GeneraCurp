package mx.itchetumal.generacurp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {

    public val curp:MutableLiveData<String> = MutableLiveData()


    fun calculaCurp(apPaterno: String, apMaterno: String,nombre: String,
                    año: String, mes: String, dia: String, sexo: String, estado: String){

        var cCurp = "${obtenerPrimeros4(nombre, apPaterno, apMaterno)}${obtenerAño(año)}${obtenerMes(mes)}${obtenerDia(dia)}${sexo}${obtenerEstado(estado)}"+
                    "${obtenerConsonante(apPaterno)}${obtenerConsonante(apMaterno)}${obtenerConsonante(nombre)}XX"

        this.curp.value = cCurp
    }



    fun obtenerPrimeros4(n: String, ap1: String, ap2: String): String{
        val dosApePaterno = ap1.substring(0,2).uppercase()
        val unoApeMaterno = ap2.substring(0,1).uppercase()
        val dosNombre = n.substring(0,1).uppercase()

        return dosApePaterno+unoApeMaterno+dosNombre
    }

    fun obtenerAño(año: String): String{
        return año.substring(2,4)
    }

    fun obtenerMes(mes: String): String{
        return when(mes.uppercase()){
            "ENERO"->{
                "01"
            }
            "FEBRERO"->{
                "02"
            }
            "MARZO"->{
                "03"
            }
            "ABRIL"->{
                "04"
            }
            "MAYO"->{
                "05"
            }
            "JUNIO"->{
                "06"
            }
            "JULIO"->{
                "07"
            }
            "AGOSTO"->{
                "08"
            }
            "SEPTIEMBRE"->{
                "09"
            }
            "OBTUBRE"->{
                "10"
            }
            "NOVIEMBRE"->{
                "11"
            }
            "DICIEMBRE"->{
                "12"
            }
            else -> mes
        }
    }

    fun obtenerDia(dia: String): String{
       return if (dia.toInt() < 10){
            "0${dia}"
        }else{
            dia
       }
    }
    fun obtenerEstado(estado: String): String{
        return when(estado){
            "Aguascalientes"->{
                "AS"
            }
            "Baja California"->{
                "BC"
            }
            "Baja California Sur"->{
                "BS"
            }
            "Campeche"->{
                "CC"
            }
            "Chiapas"->{
                "CS"
            }
            "Chihuahua"->{
                "CH"
            }
            "Coahuila"->{
                "CL"
            }
            "Colima"->{
                "CM"
            }
            "Durango"->{
                "DG"
            }
            "Guanajuato"->{
                "GT"
            }
            "Guerrero"->{
                "GR"
            }
            "Hidalgo"->{
                "HG"
            }
            "Jalisco"->{
                "JC"
            }
            "Estado de México"->{
                "DF"
            }
            "Michoacán"->{
                "MN"
            }
            "Morelos"->{
                "MS"
            }
            "Nayarit"->{
                "NT"
            }
            "Nuevo León"->{
                "NL"
            }
            "Oaxaca"->{
                "OC"
            }
            "Puebla"->{
                "PL"
            }
            "Querétaro"->{
                "QO"
            }
            "Quintana Roo"->{
                "QR"
            }

            "San Luis Potosí"->{
                "SP"
            }
            "Sinaloa"->{
                "SL"
            }
            "Sonora"->{
                "SR"
            }
            "Tabasco"->{
                "TC"
            }
            "Tamaulipas"->{
                "TS"
            }
            "Tlaxcala"->{
                "TL"
            }
            "Veracruz"->{
                "VZ"
            }
            "Yucatán"->{
                "YN"
            }
            "Zacatecas"->{
                "ZS"
            }
            else -> "XX"
        }
    }

    fun obtenerConsonante(cadena: String): String{
        var consonantes ="";
        var index = 0;
        for (element in cadena) {

            if (esConsonante(element) && index != 0) {
                consonantes += element.toString()

            }
            index++;
        }

        return consonantes.substring(0,1).uppercase()
    }

    fun esConsonante(letra: Char): Boolean {
        return "bcdfghjklmnñpqrstvwxyz".contains(letra.toString().lowercase())
    }


}
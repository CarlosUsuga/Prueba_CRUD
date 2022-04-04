package com.carlosusuga.prueba_crud.Models

class ContactoClass(
    tipoDocumento:String,
    numDocumento: Int,
    firtName:String,
    secondName:String,
    firtLastName:String,
    secondLastName:String,
    sexo:String,
    edad: Int,
    foto: Int
)
{

    var tipoDocumento:String = ""
    var numDocumento:Int = 0
    var firtName:String = ""
    var secondName:String = ""
    var firtLastName:String = ""
    var secondLastName:String = ""
    var sexo:String = ""
    var edad:Int = 0
    var foto:Int = 0

    init {
        this.tipoDocumento = tipoDocumento
        this.numDocumento = numDocumento
        this.firtName = firtName
        this.secondName = secondName
        this.firtLastName = firtLastName
        this.secondLastName = secondLastName
        this.sexo = sexo
        this.edad = edad
        this.foto = foto
    }
}
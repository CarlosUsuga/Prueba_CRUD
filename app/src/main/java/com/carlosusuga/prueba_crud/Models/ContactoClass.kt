package com.carlosusuga.prueba_crud.Models

class ContactoClass(
    numDocumento: Int,
    tipoDocumento:String,
    firstName:String,
    secondName:String,
    firstLastName:String,
    secondLastName:String,
    sexo:String,
    edad: Int
)
{

    var numDocumento:Int? = null
    var tipoDocumento:String? = null
    var firstName:String? = null
    var secondName:String? = null
    var firstLastName:String? = null
    var secondLastName:String? = null
    var sexo:String? = null
    var edad:Int? = null

    init {
        this.numDocumento = numDocumento
        this.tipoDocumento = tipoDocumento
        this.firstName = firstName
        this.secondName = secondName
        this.firstLastName = firstLastName
        this.secondLastName = secondLastName
        this.sexo = sexo
        this.edad = edad
    }
}
package com.carlosusuga.prueba_crud.SQLite

import android.provider.BaseColumns

class UsersContract {

    companion object {

        val VERSION = 1
        class Entrada: BaseColumns {
            companion object {
                val NOMBRE_TABLA = "User"

                val COLUMNA_TDOC = ""
                val COLUMNA_DOCUMENTO = ""
                val COLUMNA_PNOMBRE = ""
                val COLUMNA_SNOMBRE = ""
                val COLUMNA_PAPELLIDO = ""
                val COLUMNA_SAPELLIDO = ""
                val COLUMNA_SEXO = ""
                val COLUMNA_EDAD = ""
                val COLUMNA_FOTO = ""
            }
        }
    }

}
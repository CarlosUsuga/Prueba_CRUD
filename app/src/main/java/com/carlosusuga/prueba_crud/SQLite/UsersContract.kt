package com.carlosusuga.prueba_crud.SQLite

import android.provider.BaseColumns

class UsersContract {

    companion object {

        val VERSION = 1
        class Entrada: BaseColumns {
            companion object {
                //val VERSION: Int = 1
                val NOMBRE_TABLA = "'contactos'"

                val COLUMNA_DOCUMENTO = "'numDocumento'"
                val COLUMNA_TDOC = "'tipoDocumento'"
                val COLUMNA_PNOMBRE = "'firstName'"
                val COLUMNA_SNOMBRE = "'secondName'"
                val COLUMNA_PAPELLIDO = "'firstLastName'"
                val COLUMNA_SAPELLIDO = "'secondLastName'"
                val COLUMNA_SEXO = "'sexo'"
                val COLUMNA_EDAD = "'edad'"
            }
        }
    }

}
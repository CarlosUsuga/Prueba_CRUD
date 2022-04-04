package com.carlosusuga.prueba_crud.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, UsersContract.Companion.Entrada.NOMBRE_TABLA, null, UsersContract.VERSION) {

    companion object {
        val CREATE_ALUMNOS_TABLA = "CREATE TABLE " + UsersContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO + " INT PRIMARY KEY, " +
                UsersContract.Companion.Entrada.COLUMNA_TDOC + " TEXT " +
                UsersContract.Companion.Entrada.COLUMNA_PNOMBRE + "TEXT" +
                UsersContract.Companion.Entrada.COLUMNA_SNOMBRE + "TEXT" +
                UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO + "TEXT" +
                UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO + "TEXT" +
                UsersContract.Companion.Entrada.COLUMNA_SEXO + "TEXT" +
                UsersContract.Companion.Entrada.COLUMNA_EDAD + "INT" +
                UsersContract.Companion.Entrada.COLUMNA_FOTO + "INT)"

        val REMOVE_ALUMNOS_TABLA = "DROP TABLE IF EXISTS " + UsersContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_ALUMNOS_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {
        db?.execSQL(REMOVE_ALUMNOS_TABLA)
        onCreate(db)
    }


}
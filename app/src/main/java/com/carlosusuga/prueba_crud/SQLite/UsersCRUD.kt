package com.carlosusuga.prueba_crud.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.carlosusuga.prueba_crud.Models.ContactoClass

class UsersCRUD(context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newUsers(item: ContactoClass){
        // abrir la BD en modo escritura
        val db: SQLiteDatabase = helper?.writableDatabase!!

        // mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO, item.numDocumento)
        values.put(UsersContract.Companion.Entrada.COLUMNA_TDOC, item.tipoDocumento)
        values.put(UsersContract.Companion.Entrada.COLUMNA_PNOMBRE, item.firstName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SNOMBRE, item.secondName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO, item.firstLastName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO, item.secondLastName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SEXO, item.sexo)
        values.put(UsersContract.Companion.Entrada.COLUMNA_EDAD, item.edad)

        // Insertar una nueva fila en la tabla
        val newRowId = db.insert(UsersContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        db.close()
    }

    fun getUsers(): ArrayList<ContactoClass>{

        val items:ArrayList<ContactoClass> = ArrayList()

        // Abrir DB en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        val columnas = arrayOf(
            UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO,
            UsersContract.Companion.Entrada.COLUMNA_TDOC,
            UsersContract.Companion.Entrada.COLUMNA_PNOMBRE,
            UsersContract.Companion.Entrada.COLUMNA_SNOMBRE,
            UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO,
            UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO,
            UsersContract.Companion.Entrada.COLUMNA_SEXO,
            UsersContract.Companion.Entrada.COLUMNA_EDAD
        )

        // Crear un cursor para recorrer la tabla
        val c:Cursor = db.query(
            UsersContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        // Hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            items.add(ContactoClass(
                c.getInt(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_TDOC)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_PNOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SNOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SEXO)),
                c.getInt(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_EDAD))
            ))
        }

        // cerrar DB
        db.close()

        return items
    }

    fun getUser(numDocumento:Int): ContactoClass {
        var item: ContactoClass? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(
            UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO,
            UsersContract.Companion.Entrada.COLUMNA_TDOC,
            UsersContract.Companion.Entrada.COLUMNA_PNOMBRE,
            UsersContract.Companion.Entrada.COLUMNA_SNOMBRE,
            UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO,
            UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO,
            UsersContract.Companion.Entrada.COLUMNA_SEXO,
            UsersContract.Companion.Entrada.COLUMNA_EDAD
        )

        val c:Cursor = db.query(
            UsersContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " numDocumento = ?",
            arrayOf(numDocumento.toString()),
            null,
            null,
            null
        )

        while(c.moveToNext()){
            item = ContactoClass(
                c.getInt(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_TDOC)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_PNOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SNOMBRE)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO)),
                c.getString(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_SEXO)),
                c.getInt(c.getColumnIndexOrThrow(UsersContract.Companion.Entrada.COLUMNA_EDAD))
                )
        }
        c.close()

        return item!!
    }

    fun updateUsers(item: ContactoClass){

        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(UsersContract.Companion.Entrada.COLUMNA_DOCUMENTO, item.numDocumento)
        values.put(UsersContract.Companion.Entrada.COLUMNA_TDOC, item.tipoDocumento)
        values.put(UsersContract.Companion.Entrada.COLUMNA_PNOMBRE, item.firstName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SNOMBRE, item.secondName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_PAPELLIDO, item.firstLastName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SAPELLIDO, item.secondLastName)
        values.put(UsersContract.Companion.Entrada.COLUMNA_SEXO, item.sexo)
        values.put(UsersContract.Companion.Entrada.COLUMNA_EDAD, item.edad)
        db.update(
            UsersContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "numDocumento = ?",
            arrayOf(item.numDocumento.toString())
        )

        db.close()
    }

    fun deleteUsers(item: ContactoClass){
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(UsersContract.Companion.Entrada.NOMBRE_TABLA,
            "numDocumento = ?",
            arrayOf(item.numDocumento.toString())
        )

        db.close()
    }
}
package com.carlosusuga.prueba_crud.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.carlosusuga.prueba_crud.Models.ContactoClass
import com.carlosusuga.prueba_crud.R
import com.carlosusuga.prueba_crud.SQLite.UsersCRUD


class DetalleActivity : AppCompatActivity() {

//    var crud: UsersCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
/*
        val numDocumento = findViewById<EditText>(R.id.edtNumDocumento)
        val tipoDocumento = findViewById<EditText>(R.id.edtTipoDocumento)
        val firstName = findViewById<EditText>(R.id.edtFirtName)
        val secondName = findViewById<EditText>(R.id.edtSecondName)
        val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
        val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
        val sexo = findViewById<EditText>(R.id.edtSexo)
        val edad = findViewById<EditText>(R.id.edtEdad)
        val bActualizar = findViewById<Button>(R.id.bActualizar)
        val bEliminar = findViewById<Button>(R.id.bEliminar)

        val index = intent.getIntExtra(numDocumento.toString(), 0)

        crud = UsersCRUD(this)

        val contacto = crud?.getUser(index)

        numDocumento.setText(contacto!!.numDocumento, TextView.BufferType.EDITABLE)
        tipoDocumento.setText(contacto.tipoDocumento, TextView.BufferType.EDITABLE)
        firstName.setText(contacto.firstName, TextView.BufferType.EDITABLE)
        secondName.setText(contacto.secondName, TextView.BufferType.EDITABLE)
        firstLastName.setText(contacto.firstLastName, TextView.BufferType.EDITABLE)
        secondLastName.setText(contacto.secondLastName, TextView.BufferType.EDITABLE)
        sexo.setText(contacto.sexo, TextView.BufferType.EDITABLE)
        edad.setText(contacto.edad, TextView.BufferType.EDITABLE)

        bActualizar.setOnClickListener {
            crud?.updateUsers(ContactoClass(
                numDocumento.text.toString().toInt(),
                tipoDocumento.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                firstLastName.text.toString(),
                secondLastName.text.toString(),
                sexo.text.toString(),
                edad.text.toString().toInt()
            ))
            startActivity(Intent(this, MainActivity::class.java))
        }

        bEliminar.setOnClickListener {
            crud?.deleteUsers(ContactoClass(
                numDocumento.text.toString().toInt(),
                tipoDocumento.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                firstLastName.text.toString(),
                secondLastName.text.toString(),
                sexo.text.toString(),
                edad.text.toString().toInt()
            ))
            startActivity(Intent(this, MainActivity::class.java))
        }
*/
    }

}
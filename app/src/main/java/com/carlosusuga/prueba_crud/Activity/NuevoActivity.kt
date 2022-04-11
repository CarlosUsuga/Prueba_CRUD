package com.carlosusuga.prueba_crud.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.carlosusuga.prueba_crud.Models.ContactoClass
import com.carlosusuga.prueba_crud.R
import com.carlosusuga.prueba_crud.SQLite.UsersCRUD

class NuevoActivity : AppCompatActivity() {

    var crud: UsersCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val numDocumento = findViewById<EditText>(R.id.edtNumDocumento)
        val tipoDocumento = findViewById<EditText>(R.id.edtTipoDocumento)
        val firstName = findViewById<EditText>(R.id.edtFirtName)
        val secondName = findViewById<EditText>(R.id.edtSecondName)
        val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
        val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
        val sexo = findViewById<EditText>(R.id.edtSexo)
        val edad = findViewById<EditText>(R.id.edtEdad)
        val bAdd = findViewById<Button>(R.id.bAdd)


        crud = UsersCRUD(this)


        bAdd.setOnClickListener {
            crud?.newUsers(ContactoClass(
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
    }

}
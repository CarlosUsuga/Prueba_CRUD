package com.carlosusuga.prueba_crud.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.carlosusuga.prueba_crud.R


class DetalleActivity : AppCompatActivity() {

    var fotoIndex:Int = 0

    val fotos = arrayOf(
        R.drawable.foto_01,
        R.drawable.foto_02,
        R.drawable.foto_03,
        R.drawable.foto_04,
        R.drawable.foto_05,
        R.drawable.foto_06
    )

    var foto: ImageView? = null

    var index:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        foto = findViewById(R.id.ivFoto)

        index = intent.getStringExtra("ID")?.toInt()!!

        mapearDatos()

        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {

            android.R.id.home -> {
                finish()
                return true
            }

            R.id.itActualizar -> {
                val intent = Intent(this, NuevoActivity::class.java)
                intent.putExtra("ID", index.toString())
                startActivity(intent)
                return true
            }

            R.id.itEliminar -> {
                MainActivity.eliminarContacto(index)
                finish()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    fun mapearDatos(){
        val contacto = MainActivity.obtenerContacto(index)

        val tipoDoc = findViewById<EditText>(R.id.edtTipoDocumento)
        val numDoc = findViewById<EditText>(R.id.edtNumDocumento)
        val firstName = findViewById<EditText>(R.id.edtFirtName)
        val secondName = findViewById<EditText>(R.id.edtSecondName)
        val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
        val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
        val edad = findViewById<EditText>(R.id.edtEdad)
        val tipoSexo = findViewById<EditText>(R.id.edtSexo)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tipoDoc.setText(contacto.tipoDocumento, TextView.BufferType.EDITABLE)
        numDoc.setText(contacto.numDocumento, TextView.BufferType.EDITABLE)
        firstName.setText(contacto.firtName, TextView.BufferType.EDITABLE)
        secondName.setText(contacto.secondName, TextView.BufferType.EDITABLE)
        firstLastName.setText(contacto.firtLastName, TextView.BufferType.EDITABLE)
        secondLastName.setText(contacto.secondLastName, TextView.BufferType.EDITABLE)
        edad.setText(contacto.edad.toString() + " Años", TextView.BufferType.EDITABLE)
        tipoSexo.setText(contacto.sexo, TextView.BufferType.EDITABLE)
        ivFoto.setImageResource(contacto.foto)

    }

    fun rellenarDatos(index: Int){
        val contacto = MainActivity.obtenerContacto(index)

        val tipoDoc = findViewById<EditText>(R.id.edtTipoDocumento)
        val numDoc = findViewById<EditText>(R.id.edtNumDocumento)
        val firstName = findViewById<EditText>(R.id.edtFirtName)
        val secondName = findViewById<EditText>(R.id.edtSecondName)
        val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
        val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
        val edad = findViewById<EditText>(R.id.edtEdad)
        val tipoSexo = findViewById<EditText>(R.id.edtSexo)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tipoDoc.setText(contacto.tipoDocumento, TextView.BufferType.EDITABLE)
        numDoc.setText(contacto.numDocumento, TextView.BufferType.EDITABLE)
        firstName.setText(contacto.firtName, TextView.BufferType.EDITABLE)
        secondName.setText(contacto.secondName, TextView.BufferType.EDITABLE)
        firstLastName.setText(contacto.firtLastName, TextView.BufferType.EDITABLE)
        secondLastName.setText(contacto.secondLastName, TextView.BufferType.EDITABLE)
        edad.setText(contacto.edad.toString() + " Años", TextView.BufferType.EDITABLE)
        tipoSexo.setText(contacto.sexo, TextView.BufferType.EDITABLE)
        ivFoto.setImageResource(contacto.foto)

        var posicion = 0
        for (foto in fotos){
            if(contacto.foto == foto){
                fotoIndex = posicion
            }
            posicion++
        }
    }

    override fun onResume() {
        super.onResume()
        mapearDatos()
    }
}
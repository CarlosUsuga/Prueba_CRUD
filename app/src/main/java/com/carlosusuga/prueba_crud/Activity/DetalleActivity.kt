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

        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }

        mapearDatos()

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

        val tipoDoc = findViewById<TextView>(R.id.tvTpDoc)
        val numDoc = findViewById<TextView>(R.id.tvNumDoc)
        val firstName = findViewById<TextView>(R.id.tvNombres)
        val tipoSexo = findViewById<TextView>(R.id.tvSexo)
        val edad = findViewById<TextView>(R.id.tvIndoEdad)
        //val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tipoDoc.text = contacto.tipoDocumento
        numDoc.text = contacto.numDocumento.toString()
        firstName.text = contacto.firtName + " " + contacto.secondName + " " + contacto.firtLastName + " " + contacto.secondLastName
        tipoSexo.text = contacto.sexo
        edad.text = contacto.edad.toString() + " Años"

        //ivFoto.setImageResource(contacto.foto)
    }

    fun rellenarDatos(index: Int){
        val contacto = MainActivity.obtenerContacto(index)

        val tipoDoc = findViewById<TextView>(R.id.tvTpDoc)
        val numDoc = findViewById<TextView>(R.id.tvNumDoc)
        val firstName = findViewById<TextView>(R.id.tvNombres)
        val tipoSexo = findViewById<TextView>(R.id.tvSexo)
        val edad = findViewById<TextView>(R.id.tvIndoEdad)
       // val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tipoDoc.text = contacto.tipoDocumento
        numDoc.text = contacto.numDocumento.toString()
        firstName.text = contacto.firtName + " " + contacto.secondName + " " + contacto.firtLastName + " " + contacto.secondLastName
        tipoSexo.text = contacto.sexo
        edad.text = contacto.edad.toString() + " Años"
//        ivFoto.setImageResource(contacto.foto)

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
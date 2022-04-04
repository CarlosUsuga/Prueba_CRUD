package com.carlosusuga.prueba_crud.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.carlosusuga.prueba_crud.Models.ContactoClass
import com.carlosusuga.prueba_crud.R

class NuevoActivity : AppCompatActivity() {

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

    var index:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        foto = findViewById(R.id.ivFoto)

        foto?.setOnClickListener{
            seleccionarFoto()
        }

        // Reconocer diferencia entre actualizar y nuevo contacto
        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            android.R.id.home -> {
                finish()
                return true
            }

            R.id.itCrearNuevo -> {
                //crear un elemento de tipo contacto
                val tipoDoc = findViewById<EditText>(R.id.edtTipoDocumento)
                val numDoc = findViewById<EditText>(R.id.edtNumDocumento)
                val firstName = findViewById<EditText>(R.id.edtFirtName)
                val secondName = findViewById<EditText>(R.id.edtSecondName)
                val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
                val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
                val edad = findViewById<EditText>(R.id.edtEdad)
                val tipoSexo = findViewById<EditText>(R.id.edtSexo)

                //validar campos
                var campos = ArrayList<String>()
                campos.add(tipoDoc.text.toString())
                campos.add(numDoc.text.toString())
                campos.add(firstName.text.toString())
                campos.add(secondName.text.toString())
                campos.add(firstLastName.text.toString())
                campos.add(secondLastName.text.toString())
                campos.add(edad.text.toString())
                campos.add(tipoSexo.text.toString())

                var flag = 0
                for (campo in campos){
                    if (campo.isNullOrEmpty())
                        flag++
                }

                if (flag > 0){
                    Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show()
                }else{
                    if (index > -1){
                        MainActivity.actualizarContacto(
                            index,
                            ContactoClass(
                                campos.get(0),
                                campos.get(1).toInt(),
                                campos.get(2),
                                campos.get(3),
                                campos.get(4),
                                campos.get(5),
                                campos.get(6),
                                campos.get(7).toInt(),
                                obtenerFoto(fotoIndex)
                            )
                        )
                    }else{
                        MainActivity.agregarContacto(
                            ContactoClass(
                                campos.get(0),
                                campos.get(1).toInt(),
                                campos.get(2),
                                campos.get(3),
                                campos.get(4),
                                campos.get(5),
                                campos.get(6),
                                campos.get(7).toInt(),
                                obtenerFoto(fotoIndex)
                            )
                        )
                    }

                    finish()
                    Log.d("No elementos", MainActivity.contactos?.count().toString())

                }
                return true
            }

            else ->{return super.onOptionsItemSelected(item)}
        }

    }


    fun seleccionarFoto(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccione foto de perfil")

        val adaptadorDiagonal = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        adaptadorDiagonal.add("Foto 01")
        adaptadorDiagonal.add("Foto 02")
        adaptadorDiagonal.add("Foto 03")
        adaptadorDiagonal.add("Foto 04")
        adaptadorDiagonal.add("Foto 05")
        adaptadorDiagonal.add("Foto 06")

        builder.setAdapter(adaptadorDiagonal){
                dialog, which ->

            fotoIndex = which
            foto?.setImageResource(obtenerFoto(fotoIndex))
        }

        builder.setNegativeButton("Cancelar"){
                dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    fun obtenerFoto(index: Int): Int{
        return fotos.get(index)
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

}
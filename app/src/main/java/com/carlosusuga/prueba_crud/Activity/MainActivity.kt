package com.carlosusuga.prueba_crud.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import java.util.ArrayList
import androidx.appcompat.widget.Toolbar
import com.carlosusuga.prueba_crud.Adapters.AdaptadorCustom
import com.carlosusuga.prueba_crud.Adapters.AdaptadorCustomGrid
import com.carlosusuga.prueba_crud.Models.ContactoClass
import com.carlosusuga.prueba_crud.R

class MainActivity : AppCompatActivity() {

    var lvLista:ListView? = null
    var gridList:GridView? = null
    var swUser:ViewSwitcher? = null

    var fotoIndex:Int = 0

    val fotos = arrayOf(
        R.drawable.foto_01,
        R.drawable.foto_02,
        R.drawable.foto_03,
        R.drawable.foto_04,
        R.drawable.foto_05,
        R.drawable.foto_06
    )

    var index:Int = 0

    companion object{
        var contactos:ArrayList<ContactoClass>? = null
        var adaptador: AdaptadorCustom? = null
        var adaptadorGrid: AdaptadorCustomGrid? = null

        fun agregarContacto(contacto: ContactoClass){
            adaptador?.addItem(contacto)
        }

        fun obtenerContacto(index: Int): ContactoClass {
            return  adaptador?.getItem(index) as ContactoClass
        }

        fun eliminarContacto(index:Int){
            adaptador?.removeItem(index)
        }

        fun actualizarContacto(index:Int, nuevoContacto: ContactoClass){
            adaptador?.updateItem(index, nuevoContacto)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbarMain = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        contactos = ArrayList()
        contactos?.add(ContactoClass("C.C", 1000011, "carlos", "andres", "usuga", "jaramillo", "Masculino", 26, R.drawable.foto_01))

        gridList = findViewById(R.id.gridList)
        lvLista = findViewById(R.id.lvLista)
        adaptador = AdaptadorCustom(this, contactos!!)
        adaptadorGrid = AdaptadorCustomGrid(this, contactos!!)
        swUser = findViewById(R.id.swUser)

        lvLista?.adapter = adaptador
        gridList?.adapter = adaptadorGrid

        lvLista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }

        // Reconocer la diferencia entre actualizar y crear nuevo contacto
        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as SearchView

        val itemSwitch = menu.findItem(R.id.swUser)
        itemSwitch?.setActionView(R.layout.switch_item)
        val switchView = itemSwitch?.actionView?.findViewById<Switch>(R.id.sCambiaVista)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar contacto"

        searchView.setOnQueryTextFocusChangeListener{ v, hasFocus ->
            //preparar datos del contacto
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //filtrar

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // filtrar
                adaptador?.filtrar(newText!!)
                Log.d("ITEMS", contactos?.count().toString())
                return true
            }

        })

        switchView?.setOnCheckedChangeListener { buttonView, isChecked ->
            searchView?.queryHint
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            R.id.itNuevo -> {
                val intent = Intent(this, NuevoActivity::class.java)
                startActivity(intent)
                return true
            }

            else ->{return super.onOptionsItemSelected(item)}
        }

    }

    override fun onResume() {
        super.onResume()

        adaptador?.notifyDataSetChanged()
    }

    fun rellenarDatos(index: Int){
        val contacto = obtenerContacto(index)

        val tipoDoc = findViewById<EditText>(R.id.edtTipoDocumento)
        val numDoc = findViewById<EditText>(R.id.edtNumDocumento)
        val firstName = findViewById<EditText>(R.id.edtFirtName)
        val secondName = findViewById<EditText>(R.id.edtSecondName)
        val firstLastName = findViewById<EditText>(R.id.edtFirtLastName)
        val secondLastName = findViewById<EditText>(R.id.edtSecondLastName)
        val tipoSexo = findViewById<EditText>(R.id.edtSexo)
        val edad = findViewById<EditText>(R.id.edtEdad)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tipoDoc.setText(contacto.tipoDocumento, TextView.BufferType.EDITABLE)
        numDoc.setText(contacto.numDocumento, TextView.BufferType.EDITABLE)
        firstName.setText(contacto.firtName, TextView.BufferType.EDITABLE)
        secondName.setText(contacto.secondName, TextView.BufferType.EDITABLE)
        firstLastName.setText(contacto.firtLastName, TextView.BufferType.EDITABLE)
        secondLastName.setText(contacto.secondLastName, TextView.BufferType.EDITABLE)
        tipoSexo.setText(contacto.sexo, TextView.BufferType.EDITABLE)
        edad.setText(contacto.edad.toString() + " Años", TextView.BufferType.EDITABLE)
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
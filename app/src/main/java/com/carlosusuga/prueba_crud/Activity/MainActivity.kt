package com.carlosusuga.prueba_crud.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.ArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlosusuga.prueba_crud.Adapters.AdaptadorCustom
import com.carlosusuga.prueba_crud.Interface.ClickListener
import com.carlosusuga.prueba_crud.Interface.LongClickListener
import com.carlosusuga.prueba_crud.Models.ContactoClass
import com.carlosusuga.prueba_crud.R
import com.carlosusuga.prueba_crud.SQLite.UsersCRUD
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var lvLista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptador: AdaptadorCustom? = null

    var contactos: ArrayList<ContactoClass>? = null
    var crud: UsersCRUD? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        lvLista = findViewById(R.id.lvLista)

        lvLista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lvLista?.layoutManager = layoutManager

        fab.setOnClickListener {
            startActivity(Intent(this, NuevoActivity::class.java))
        }

        crud = UsersCRUD(this)
        contactos = crud?.getUsers()

        adaptador = AdaptadorCustom(contactos!!, object: ClickListener{
            override fun onClick(vista: View, index: Int) {
                //val intent = Intent(applicationContext, DetalleActivity::class.java)
                //intent.putExtra("ID", contactos!!.get(index).numDocumento)
                //startActivity(intent)
            }

        }, object: LongClickListener{
            override fun longClick(vista: View, index: Int) {}

        })

        lvLista?.adapter = adaptador
    }
}
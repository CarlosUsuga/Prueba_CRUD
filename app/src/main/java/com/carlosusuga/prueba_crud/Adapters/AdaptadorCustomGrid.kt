package com.carlosusuga.prueba_crud.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.carlosusuga.prueba_crud.R
import com.carlosusuga.prueba_crud.Models.ContactoClass

class AdaptadorCustomGrid(var context: Context, items:ArrayList<ContactoClass>): BaseAdapter() {
    //Almacenar los elementos que se van a mostrar en el listView
    var items:ArrayList<ContactoClass>? = null
    var copiaItems:ArrayList<ContactoClass>? = null

    init {
        this.items = ArrayList(items)
        this.copiaItems = items
    }

    override fun getCount(): Int {
        //regresar el numero de elementos de mi lista
        return this.items?.count()!!
    }

    override fun getItem(position: Int): Any {
        //Se regresa el objeto entero
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        //Regresar el id del elemento seleccionado
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = null
        var vista:View? = convertView

        if(vista == null){
            vista = LayoutInflater.from(context).inflate(R.layout.template_contacto_grid, null)
            viewHolder = ViewHolder(vista)
            vista?.tag = viewHolder
        }else{
            viewHolder = vista.tag as? ViewHolder
        }

        val item = getItem(position) as ContactoClass

        //Asignacion de valores a elementos graficos
        viewHolder?.firstName?.text = item.firtName + " " + item.firtLastName
        viewHolder?.numDocumento?.text = item.numDocumento.toString()
        //viewHolder?.foto?.setImageResource(item.foto)

        return vista!!
    }

    fun addItem(item: ContactoClass){
        copiaItems?.add(item)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun removeItem(index:Int){
        copiaItems?.removeAt(index)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, newItem: ContactoClass){
        copiaItems?.set(index, newItem)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun filtrar(str:String){
        items?.clear()

        if (str.isEmpty()){
            items = ArrayList(copiaItems)
            notifyDataSetChanged()
            return
        }

        var busqueda = str
        busqueda = busqueda.toLowerCase()

        for (item in copiaItems!!){
            val numDocumento = item.numDocumento.toString().toLowerCase()

            if (numDocumento.contains(busqueda)){
                items?.add(item)
            }

        }

        notifyDataSetChanged()

    }

    private class ViewHolder(vista:View){

        var firstName: TextView? = null
        var firstLastName: TextView? = null
        var numDocumento: TextView? = null
        var foto: ImageView? = null

        init {

            firstName = vista.findViewById(R.id.firtName)
            firstLastName = vista.findViewById(R.id.firtLastName)
            numDocumento = vista.findViewById(R.id.numDocumento)
           // foto = vista.findViewById(R.id.ivFoto)

        }
    }
}
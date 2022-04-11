package com.carlosusuga.prueba_crud.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carlosusuga.prueba_crud.Interface.ClickListener
import com.carlosusuga.prueba_crud.Interface.LongClickListener
import com.carlosusuga.prueba_crud.R
import com.carlosusuga.prueba_crud.Models.ContactoClass
import java.util.ArrayList

class AdaptadorCustom(items:ArrayList<ContactoClass>, var listener: ClickListener,
                      var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items:ArrayList<ContactoClass>? = null
    var multiSeleccion = false

    var itemsSeleccionados:ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = ArrayList(items)
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_contacto,parent,false)
        viewHolder = ViewHolder(vista, listener, longClickListener)


        return viewHolder!!
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.tipoDocumento?.text = item?.tipoDocumento
        holder.numDocumento?.text = item?.numDocumento.toString()
        holder.firstName?.text = item?.firstName
        holder.secondName?.text = item?.secondName
        holder.firstLastName?.text = item?.firstLastName
        holder.secondLastName?.text = item?.secondLastName
        holder.sexo?.text = item?.sexo
        holder.edad?.text = item?.edad.toString()

        if(itemsSeleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    class ViewHolder(vista: View, listener: ClickListener, longClickListener: LongClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{

        var vista = vista

        var numDocumento:TextView? = null
        var tipoDocumento:TextView? = null
        var firstName:TextView? = null
        var secondName:TextView? = null
        var firstLastName:TextView? = null
        var secondLastName:TextView? = null
        var sexo:TextView? = null
        var edad:TextView? = null

        var listener: ClickListener? = null
        var longListener: LongClickListener? = null

        init {
            numDocumento = vista.findViewById(R.id.tvNumDocumento)
            tipoDocumento = vista.findViewById(R.id.tvTipoDocumento)
            firstName = vista.findViewById(R.id.tvFirstName)
            secondName = vista.findViewById(R.id.tvSecondName)
            firstLastName = vista.findViewById(R.id.tvFirstLastName)
            secondLastName = vista.findViewById(R.id.tvSecondLastName)
            sexo = vista.findViewById(R.id.tvSexo)
            edad = vista.findViewById(R.id.tvEdad)


            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!, adapterPosition)
            return true
        }

    }


    fun iniciarActionMode(){
        multiSeleccion = true
    }

    fun destruirActionMode(){
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        // eliminar elementos seleccionados
        for(item in itemsSeleccionados!!){
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index:Int){
        if(multiSeleccion){
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.remove(index)
            }else{
                itemsSeleccionados?.add(index)
            }

            notifyDataSetChanged()
        }
    }

    fun obtenerNumeroElementosSeleccionados():Int{
        return itemsSeleccionados?.count()!!
    }

    fun eliminarSeleccionados(){
        if(itemsSeleccionados?.count()!! > 0){
            var itemsEliminados = ArrayList<ContactoClass>()

            for(index in itemsSeleccionados!!){
                itemsEliminados.add(items?.get(index)!!)
            }

            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }
}
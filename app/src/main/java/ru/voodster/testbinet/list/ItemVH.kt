package ru.voodster.testbinet.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.voodster.testbinet.R
import ru.voodster.testbinet.api.ItemModel
import java.text.SimpleDateFormat
import java.util.*

class ItemVH(itemView: View):RecyclerView.ViewHolder(itemView) {

    private val bodyV:TextView = itemView.findViewById(R.id.item_body)
    private val dateV:TextView = itemView.findViewById(R.id.info_created)
    private val changedV:TextView = itemView.findViewById(R.id.info_changed)
    fun bind(item:ItemModel){
        bodyV.text = item.body.removeSurrounding("&quot;", "&quot;")
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm", Locale.ROOT)
        dateV.text = sdf.format(Date(item.da))
        if (item.da!=item.dm){
            changedV.visibility = View.VISIBLE
            changedV.text = sdf.format(Date(item.dm))
        }else{
            changedV.findViewById<TextView>(R.id.info_changed).visibility = View.GONE
        }
    }
}
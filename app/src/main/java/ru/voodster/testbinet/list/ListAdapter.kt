package ru.voodster.testbinet.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.voodster.testbinet.R
import ru.voodster.testbinet.api.ItemModel
import ru.voodster.testbinet.ext.ItemDiffUtilCallback

class ListAdapter(
    private val inflater: LayoutInflater,
    private val listener: ((filmItem: ItemModel) -> Unit)?
) : RecyclerView.Adapter<ItemVH>() {


    private val fakeItem = ItemModel("4klJeiCKTs", "Вторая запись", 1442236233, 1442236233)
    private val fakeList = arrayListOf(fakeItem)


    private var itemList: ArrayList<ItemModel> = fakeList

    fun setItems(list: List<ItemModel>) {
        val itemDiffUtilCallback = ItemDiffUtilCallback(itemList, list)
        val diffResult = DiffUtil.calculateDiff(itemDiffUtilCallback)
        itemList.clear()
        itemList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(inflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(itemList[position])

        holder.itemView.setOnClickListener {
            listener?.invoke(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
package ru.voodster.testbinet.ext

import androidx.recyclerview.widget.DiffUtil
import ru.voodster.testbinet.api.ItemModel

class ItemDiffUtilCallback(
    private val oldList: List<ItemModel>,
    private val newList: List<ItemModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].body == newList[newItemPosition].body
    }
}
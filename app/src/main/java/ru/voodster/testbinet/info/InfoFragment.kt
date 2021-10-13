package ru.voodster.testbinet.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.voodster.testbinet.R
import ru.voodster.testbinet.R.id.info_changed
import ru.voodster.testbinet.api.ItemModel
import java.text.SimpleDateFormat
import java.util.*

class InfoFragment : Fragment() {
    private lateinit var item: ItemModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<ItemModel>(ARG_ITEM)?.let {
            item = it
        }
        setViews(view, item)
    }

    private fun setViews(view: View, item: ItemModel) {
        view.findViewById<TextView>(R.id.info_body).text =
            item.body.removeSurrounding("&quot;", "&quot;")

        val sdf = SimpleDateFormat("dd/MM/yy hh:mm", Locale.ROOT)
        view.findViewById<TextView>(R.id.info_created).text = sdf.format(Date(item.da))
        if (item.da != item.dm) {
            view.findViewById<TextView>(info_changed).visibility = View.VISIBLE
            view.findViewById<TextView>(info_changed).text = sdf.format(Date(item.dm))
        } else {
            view.findViewById<TextView>(info_changed).visibility = View.GONE
        }
    }

    companion object {
        private const val ARG_ITEM = "item"
        fun newInstance(item: ItemModel) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ITEM, item)
                }
            }
    }
}

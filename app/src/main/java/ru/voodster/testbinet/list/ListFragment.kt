package ru.voodster.testbinet.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import ru.voodster.testbinet.DataViewModel
import ru.voodster.testbinet.R
import ru.voodster.testbinet.api.ItemModel


class ListFragment : Fragment() {
    private val viewModel: DataViewModel by activityViewModels()
    var listener: OnItemClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is OnItemClickListener) {
            listener = activity as OnItemClickListener
        } else {
            throw Exception("Activity must implement onFilmClickListener")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        initRecycler()
        viewModel.getData()

        viewModel.items.observe(viewLifecycleOwner, { list ->
            (view.findViewById<RecyclerView>(R.id.itemListRV).adapter as ListAdapter).setItems(list)
        })

    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.itemListRV)
            ?.adapter = ListAdapter(LayoutInflater.from(context)) {
            listener?.onItemClick(it)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: ItemModel)
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}
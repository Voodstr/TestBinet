package ru.voodster.testbinet.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import ru.voodster.testbinet.DataViewModel
import ru.voodster.testbinet.R

class AddFragment : Fragment() {


    private val viewModel: DataViewModel by activityViewModels()

    companion object {
        fun newInstance(): AddFragment {
            return AddFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.add_save).setOnClickListener {
            if (view.findViewById<TextInputEditText>(R.id.add_edit_text).text.toString()
                    .isNotEmpty()
            ) {
                viewModel.addEntry(view.findViewById<TextInputEditText>(R.id.add_edit_text).text.toString())
            }
            view.findViewById<TextInputEditText>(R.id.add_edit_text).setText("")
        }

        view.findViewById<Button>(R.id.add_cancel).setOnClickListener {
            view.findViewById<TextInputEditText>(R.id.add_edit_text).setText("")
        }
    }

}
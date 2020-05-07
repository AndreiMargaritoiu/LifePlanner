package com.amdev.lifehelper.bdays.bdaysadd

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.amdev.lifehelper.R
import kotlinx.android.synthetic.main.fragment_bdaysadd.*

class AddBdaysFragment : Fragment() {

    private lateinit var addBdaysViewModel: AddBdaysViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addBdaysViewModel = ViewModelProvider(this).get(AddBdaysViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bdaysadd, container, false)

        val numberPicker1: NumberPicker = root.findViewById(R.id.nrpicker_day)
        numberPicker1.minValue = 1
        numberPicker1.maxValue = 31

        val numberPicker2: NumberPicker = root.findViewById(R.id.nrpicker_month)
        numberPicker2.minValue = 1
        numberPicker2.maxValue = 12

        setupUI(root)
        setHasOptionsMenu(true);

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.addupdate_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_utilsub -> {
                saveBday()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveBday() {
        val name: String = et_personName.text.toString()
        val day: Int = nrpicker_day.value
        val month: Int = nrpicker_month.value
        val details: String = et_details.text.toString()

        if (name.trim().isEmpty() || details.trim().isEmpty()) {
            Toast.makeText(context, "Can't save empty content!", Toast.LENGTH_SHORT).show()
            return
        }
        addBdaysViewModel.onSave(name, day, month, details)
        view?.findNavController()?.navigate(R.id.action_nav_addbdays_to_nav_bdays)
        Toast.makeText(context, "Successfully created", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val view = activity!!.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setupUI(view: View) { // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                activity?.let { addBdaysViewModel.hideKeyboardFrom(it, view) }
                false
            }
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }
}

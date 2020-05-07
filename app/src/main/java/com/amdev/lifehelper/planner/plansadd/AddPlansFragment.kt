package com.amdev.lifehelper.planner.plansadd

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.NumberPicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.amdev.lifehelper.R
import kotlinx.android.synthetic.main.fragment_plansadd.*

class AddPlansFragment : Fragment() {

    private lateinit var addPlansViewModel: AddPlansViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_plansadd, container, false)
        addPlansViewModel = ViewModelProvider(this).get(AddPlansViewModel::class.java)

        val numberPicker1: NumberPicker = root.findViewById(R.id.nrpicker_planDay)
        numberPicker1.minValue = 1
        numberPicker1.maxValue = 31

        val numberPicker2: NumberPicker = root.findViewById(R.id.nrpicker_planMonth)
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
                savePlan()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun savePlan() {
        val name: String = et_planName.text.toString()
        val day: Int = nrpicker_planDay.value
        val month: Int = nrpicker_planMonth.value
        val details: String = et_planDetails.text.toString()

        if (name.trim().isEmpty() || details.trim().isEmpty()) {
            Toast.makeText(context, "Can't save empty content!", Toast.LENGTH_SHORT).show()
            return
        }
        addPlansViewModel.onSave(name, day, month, details)
        view?.findNavController()?.navigate(R.id.action_nav_addplans_to_nav_plans)
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
                activity?.let { addPlansViewModel.hideKeyboardFrom(it, view) }
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

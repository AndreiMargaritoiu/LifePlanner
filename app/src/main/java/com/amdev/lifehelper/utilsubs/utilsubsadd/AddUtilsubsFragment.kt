package com.amdev.lifehelper.utilsubs.utilsubsadd

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
import kotlinx.android.synthetic.main.fragment_utilsubsadd.*


class AddUtilsubsFragment : Fragment() {

    private lateinit var addUtilsubsViewModel: AddUtilsubsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addUtilsubsViewModel = ViewModelProvider(this).get(AddUtilsubsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_utilsubsadd, container, false)

        val numberPicker: NumberPicker = root.findViewById(R.id.nrpicker_deadline)
        numberPicker.minValue = 1
        numberPicker.maxValue = 28

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
                saveUtilsub()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveUtilsub() {
        val provider: String = et_provider.text.toString()
        var amount = 0;
        if (!et_amount.text.toString().equals("")) {
            amount = et_amount.text.toString().toInt()
        }
        val deadline: Int = nrpicker_deadline.value

        if (provider.trim().isEmpty() || amount == 0) {
            Toast.makeText(context, "Can't save empty content!", Toast.LENGTH_SHORT).show()
            return
        }
        addUtilsubsViewModel.onSave(provider, deadline, amount)
        view?.findNavController()?.navigate(R.id.action_nav_addutilsubs_to_nav_utilsubs)
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
                activity?.let { addUtilsubsViewModel.hideKeyboardFrom(it, view) }
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

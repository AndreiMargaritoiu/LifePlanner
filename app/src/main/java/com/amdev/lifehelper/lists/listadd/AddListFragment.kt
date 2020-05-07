package com.amdev.lifehelper.lists.listadd

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amdev.lifehelper.R

class AddListFragment : Fragment() {

    companion object {
        fun newInstance() = AddListFragment()
    }

    private lateinit var viewModel: AddListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

package com.amdev.lifehelper.utilsubs.utilsubupdate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.amdev.lifehelper.R

class UpdateUtilsubsFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateUtilsubsFragment()
    }

    private lateinit var viewModel: UpdateUtilsubsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_utilsubsupdate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateUtilsubsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

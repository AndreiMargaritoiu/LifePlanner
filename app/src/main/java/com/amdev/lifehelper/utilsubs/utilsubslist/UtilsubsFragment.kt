package com.amdev.lifehelper.utilsubs.utilsubslist

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amdev.lifehelper.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UtilsubsFragment : Fragment() {

    private lateinit var utilsubsViewModel: UtilsubsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_utilsubs, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_utilsubs)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter = UtilsubsAdapter()
        recyclerView.adapter = adapter

        utilsubsViewModel = ViewModelProvider(this).get(UtilsubsViewModel::class.java)
        utilsubsViewModel.getAllutilsubs()
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.utilsubs = it
                }
            })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("Do you want to delete this item ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, id -> adapter.getNoteAt(viewHolder.adapterPosition)
                        ?.let { utilsubsViewModel.onDelete(it) }
                        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                        utilsubsViewModel.getAllutilsubs()
                            .observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    adapter.utilsubs = it
                                }
                            })
                    })
                val alert = dialogBuilder.create()
                alert.show()
            }
        }).attachToRecyclerView(recyclerView)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_nav_utilsubs_to_nav_addutilsubs)
        }

        return root
    }

}

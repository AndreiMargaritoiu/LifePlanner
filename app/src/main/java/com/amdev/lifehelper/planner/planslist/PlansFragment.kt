package com.amdev.lifehelper.planner.planslist

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.amdev.lifehelper.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlansFragment : Fragment() {

    private lateinit var planViewModel: PlansViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root =  inflater.inflate(R.layout.fragment_plans, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rv_plans)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter = PlansAdapter()
        recyclerView.adapter = adapter

        planViewModel = ViewModelProvider(this).get(PlansViewModel::class.java)
        planViewModel.getAllPlans()
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.plans = it
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
                        ?.let { planViewModel.onDelete(it) }
                        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                        planViewModel.getAllPlans()
                            .observe(viewLifecycleOwner, Observer {
                                it?.let {
                                    adapter.plans = it
                                }
                            })
                    })
                val alert = dialogBuilder.create()
                alert.show()
            }
        }).attachToRecyclerView(recyclerView)

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_nav_plans_to_nav_addplans)
        }

        return root
    }
}


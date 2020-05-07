package com.amdev.lifehelper.planner.planslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amdev.lifehelper.R
import com.amdev.lifehelper.databases.plansdatabase.Plan

class PlansAdapter : RecyclerView.Adapter<PlansAdapter.PlansHolder>() {

    var plans = listOf<Plan>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlansHolder {
        return PlansHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: PlansHolder,
        position: Int
    ) {
        val item = plans[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    fun getNoteAt(position: Int): Plan? {
        return plans.get(position)
    }

    class PlansHolder private constructor(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val textViewName: TextView = itemView.findViewById(R.id.tv_planName)
        val textViewDate: TextView = itemView.findViewById(R.id.tv_planDate)

        fun bind(item: Plan) {
            textViewName.setText(item.planName)
            val date : String = item.planDate.toString() + "/" + item.planMonth.toString()
            textViewDate.setText(date)
        }

        companion object {
            fun from(parent: ViewGroup): PlansHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_plan, parent, false)
                return PlansHolder(view)
            }
        }
    }
}
package com.amdev.lifehelper.utilsubs.utilsubslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amdev.lifehelper.R
import com.amdev.lifehelper.databases.utilsubsdatabase.Utilsub


class UtilsubsAdapter : RecyclerView.Adapter<UtilsubsAdapter.UtilsubsHolder>() {

    var utilsubs = listOf<Utilsub>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UtilsubsHolder {
        return UtilsubsHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: UtilsubsHolder,
        position: Int
    ) {
        val item = utilsubs[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return utilsubs.size
    }

    fun getNoteAt(position: Int): Utilsub? {
        return utilsubs.get(position)
    }

    class UtilsubsHolder private constructor(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val textViewProvider: TextView = itemView.findViewById(R.id.tv_provider)
        val textViewAmount: TextView = itemView.findViewById(R.id.tv_amount)
        val textViewDeadline: TextView = itemView.findViewById(R.id.tv_deadline)

        fun bind(item: Utilsub) {
            textViewProvider.setText(item.provider)
            textViewAmount.setText(item.amount.toString())
            textViewDeadline.setText(item.deadline.toString())
        }

        companion object {
            fun from(parent: ViewGroup): UtilsubsHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_utilsub, parent, false)
                return UtilsubsHolder(view)
            }
        }
    }
}
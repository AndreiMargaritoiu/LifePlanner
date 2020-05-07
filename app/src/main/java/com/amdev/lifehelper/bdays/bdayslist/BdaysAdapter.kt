package com.amdev.lifehelper.bdays.bdayslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amdev.lifehelper.R
import com.amdev.lifehelper.databases.bdaysdatabase.Bday

class BdaysAdapter : RecyclerView.Adapter<BdaysAdapter.BdaysHolder>() {

    var bdays = listOf<Bday>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BdaysHolder {
        return BdaysHolder.from(parent)
    }

    override fun onBindViewHolder(
        holder: BdaysHolder,
        position: Int
    ) {
        val item = bdays[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return bdays.size
    }

    fun getNoteAt(position: Int): Bday? {
        return bdays.get(position)
    }

    class BdaysHolder private constructor(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val textViewName: TextView = itemView.findViewById(R.id.tv_name)
        val textViewDate: TextView = itemView.findViewById(R.id.tv_date)

        fun bind(item: Bday) {
            textViewName.setText(item.personName)
            val date : String = item.bdayDate.toString() + "/" + item.bdayMonth.toString()
            textViewDate.setText(date)
        }

        companion object {
            fun from(parent: ViewGroup): BdaysHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_bday, parent, false)
                return BdaysHolder(view)
            }
        }
    }
}
package com.example.k42un0k0smoke.modules.quit_results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.k42un0k0smoke.R
import com.example.k42un0k0smoke.model.QuitResult

class MyItemRecyclerViewAdapter : ListAdapter<QuitResult, MyItemRecyclerViewAdapter.ViewHolder>(QuitResultDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idView: TextView = view.findViewById(R.id.item_number)
        private val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        fun bind(quitResult: QuitResult) {
            idView.text = quitResult.uid.toString()
            contentView.text = quitResult.startAt
        }
    }
}

object QuitResultDiffCallback : DiffUtil.ItemCallback<QuitResult>() {
    override fun areItemsTheSame(oldItem: QuitResult, newItem: QuitResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuitResult, newItem: QuitResult): Boolean {
        return oldItem.uid == newItem.uid
    }

}
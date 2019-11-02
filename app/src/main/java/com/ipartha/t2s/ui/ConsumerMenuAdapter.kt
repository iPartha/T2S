package com.ipartha.t2s.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ipartha.t2s.BR
import com.ipartha.t2s.databinding.ActivityMainBinding
import com.ipartha.t2s.mvvm.ConsumerMenu

class ConsumerMenuAdapter() : ListAdapter<ConsumerMenu, ConsumerMenuAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val consumerMenu = getItem(position)
        holder.apply {
            bind(consumerMenu)
            itemView.tag = consumerMenu
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItem(position: Int): ConsumerMenu {
        return currentList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActivityMainBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    class ViewHolder(private val binding : ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ConsumerMenu) {
            //binding.setVariable(BR.consumerMenu, item)
            binding.apply {
                binding.setVariable(BR.consumerMenu, item)
                executePendingBindings()
            }
        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<ConsumerMenu>() {
        override fun areContentsTheSame(oldItem: ConsumerMenu, newItem: ConsumerMenu): Boolean {
            return oldItem.row == newItem.row
        }

        override fun areItemsTheSame(oldItem: ConsumerMenu, newItem: ConsumerMenu): Boolean {
            return oldItem == newItem
        }


    }

}
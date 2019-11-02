package com.ipartha.t2s.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ipartha.t2s.BR
import com.ipartha.t2s.R
import com.ipartha.t2s.databinding.ConsumerMenuListBinding
import com.ipartha.t2s.mvvm.ConsumerMenu


class ConsumerMenuAdapter(private val dataModelList: List<ConsumerMenu>) :
    RecyclerView.Adapter<ConsumerMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.consumer_menu_list, parent, false
        ) as ConsumerMenuListBinding

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.bind(dataModel)
    }


    override fun getItemCount(): Int {
        return dataModelList.size
    }

    inner class ViewHolder(var binding: ConsumerMenuListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Any) {
            binding.setVariable(BR.consumerMenu, obj)
            binding.executePendingBindings()
        }
    }

}

package com.maxfin.kvartirkatest.adapters.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxfin.kvartirkatest.R
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.viewHolders.base.BaseVH
import com.maxfin.kvartirkatest.viewHolders.base.ItemFillable

open class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() where T : ItemModel {

    var data: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemFillable) {
            holder.fill(data[position])
        };
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val itemView = inflater.inflate(R.layout.banner, parent, false)
        return BaseVH(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun stockData(list:List<T>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: MutableList<T>) {
        data.addAll(list)
        notifyDataSetChanged()
    }


}
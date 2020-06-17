package com.maxfin.kvartirkatest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxfin.kvartirkatest.R
import com.maxfin.kvartirkatest.adapters.base.BaseAdapter
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.FlatPresenter
import com.maxfin.kvartirkatest.viewHolders.FlatViewHolder
import com.maxfin.kvartirkatest.viewHolders.base.ItemFillable

class FlatAdapter(val presenter: FlatPresenter) : BaseAdapter<ItemModel>() {

    var viewState = 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemFillable) {
            holder.fill(data[position])
        };
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder.layoutPosition > itemCount -2) {
            presenter.getFlats(presenter.idTown,itemCount.toString(),true)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return FlatViewHolder(
            inflater.inflate(
                R.layout.flat_card,
                parent,
                false
            ), presenter
        )


    }
}

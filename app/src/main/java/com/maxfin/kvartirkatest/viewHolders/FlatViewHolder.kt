package com.maxfin.kvartirkatest.viewHolders

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxfin.kvartirkatest.R
import com.maxfin.kvartirkatest.model.Flat
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.FlatPresenter
import com.maxfin.kvartirkatest.viewHolders.base.ItemFillable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.flat_card.view.*

class FlatViewHolder(itemView: View, val presenter: FlatPresenter) : RecyclerView.ViewHolder(itemView),
    ItemFillable {

    val adress = itemView.findViewById<TextView>(R.id.flatAddress)
    val image = itemView.findViewById<ImageView>(R.id.flatImage)
    val price = itemView.findViewById<TextView>(R.id.flatPrice)

    override fun fill(item: Any?) {
        if (item is ItemModel) {
            var model = item.data
            if (model is Flat) {
                adress.text = model.name
                price.text = model.price + model.currency
                Picasso.get()
                    .load(model.photo)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(image);

                itemView.setOnClickListener {
                    presenter.goToDetail(model.id)
                }
            }



        }
    }
}

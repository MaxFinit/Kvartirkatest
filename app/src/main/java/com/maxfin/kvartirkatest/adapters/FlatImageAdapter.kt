package com.maxfin.kvartirkatest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.maxfin.kvartirkatest.R
import com.squareup.picasso.Picasso

class FlatImageAdapter(private val list: MutableList<String>?, private val context: Context) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image: ImageView
        val inflater = LayoutInflater.from(context);
        val imageLayout = inflater.inflate(
            R.layout.flat_image, container,
            false
        ) as ViewGroup
        image = imageLayout.findViewById<ImageView>(R.id.imageFlat)
        Picasso.get()
            .load(list?.get(position))
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(image);
        container.addView(imageLayout)

        return imageLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int {
       return list!!.size
    }
}
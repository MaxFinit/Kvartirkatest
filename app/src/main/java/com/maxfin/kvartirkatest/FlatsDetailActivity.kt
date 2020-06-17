package com.maxfin.kvartirkatest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxfin.kvartirkatest.adapters.FlatAdapter
import com.maxfin.kvartirkatest.adapters.FlatImageAdapter
import com.maxfin.kvartirkatest.model.Flat
import com.maxfin.kvartirkatest.model.FlatDetail
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.FlatDetailPresenter
import com.maxfin.kvartirkatest.presenters.FlatPresenter
import com.maxfin.kvartirkatest.presenters.IFlatDetailView
import com.maxfin.kvartirkatest.presenters.IFlatView
import kotlinx.android.synthetic.main.activity_flats.*
import kotlinx.android.synthetic.main.activity_flats_details.*

class FlatsDetailActivity: AppCompatActivity(), IFlatDetailView {

    val presenter = FlatDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flats_details)

    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.getFlats(intent.getStringExtra("idFlat"))

    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun setDetails(flats: ItemModel) {
        var data = flats.data
        if (data is FlatDetail) {
            flatTitle.text = "${data.title} ${data.address}" //${data.rooms} комнаты
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                flatDescription.text = (Html.fromHtml(data.description_full, Html.FROM_HTML_MODE_COMPACT));
            } else {
                flatDescription.text = (Html.fromHtml(data.description_full))
            }
            flatPrice.text = "за сутки\n" + data.price + data.currency
            viewPager.adapter = FlatImageAdapter(data.photos,this)
            viewPager.currentItem = 0
        }


    }

    override fun onProgressOn() {
        runOnUiThread {
            flatDetailProgress.visibility = View.VISIBLE
        }

    }

    override fun onProgressOff() {
        runOnUiThread {
            flatDetailProgress.visibility = View.GONE
        }

    }

}
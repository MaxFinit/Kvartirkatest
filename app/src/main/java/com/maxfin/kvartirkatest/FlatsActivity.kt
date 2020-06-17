package com.maxfin.kvartirkatest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxfin.kvartirkatest.adapters.FlatAdapter
import com.maxfin.kvartirkatest.model.base.ItemModel
import com.maxfin.kvartirkatest.presenters.FlatPresenter
import com.maxfin.kvartirkatest.presenters.IFlatView
import kotlinx.android.synthetic.main.activity_flats.*

class FlatsActivity : AppCompatActivity(), IFlatView {

    val presenter = FlatPresenter(this)
    val flatsAdapter = FlatAdapter(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flats)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        initRecyclerView()
        presenter.getFlats(intent.getStringExtra("idTown"), "0", false)

    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    private fun initRecyclerView() {
        var linearManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        flatRecyclerView.layoutManager = linearManager
        flatRecyclerView.adapter = flatsAdapter
    }


    override fun setFlats(flats: MutableList<ItemModel>) {
        if (flats.size > 0)
            flatsAdapter.stockData(flats)
        else
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
    }

    override fun addFlats(flats: MutableList<ItemModel>) {
        flatsAdapter.addData(flats)
    }

    override fun goToDetail(flatsId: String) {
        val intent = Intent(this, FlatsDetailActivity::class.java)
        intent.putExtra("idFlat", flatsId)
        startActivity(intent)
    }

    override fun onProgressOn() {
        runOnUiThread {
            flatProgress.visibility = View.VISIBLE
        }

    }

    override fun onProgressOff() {
        runOnUiThread {
            flatProgress.visibility = View.GONE
        }

    }

}
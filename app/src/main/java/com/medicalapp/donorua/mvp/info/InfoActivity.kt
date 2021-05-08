package com.medicalapp.donorua.mvp.info

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.infodetails.InfoDetailsActivity
import kotlinx.android.synthetic.main.activity_info.*


class InfoActivity: AppCompatActivity(), IInfoContract.IInfoView {

    lateinit var presenter: InfoPresenter

    private lateinit var infoAdapter: InfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        presenter = InfoPresenter(this)
        configureRecycler()
    }

    private fun configureRecycler() {
        val list = mutableListOf<InfoModel>()

        InfoEnum.values().forEach { enum ->
            list.add(InfoModel(enum) {
               InfoDetailsActivity.openActivity(this, enum)
            })
        }

        infoAdapter = InfoAdapter(list)
        activity_info_recycler_view.adapter = infoAdapter
        activity_info_recycler_view.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
    }
}
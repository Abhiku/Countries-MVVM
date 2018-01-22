package com.example.abhijeet.myapplication.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView

import com.example.abhijeet.myapplication.BR
import com.example.abhijeet.myapplication.R
import com.example.abhijeet.myapplication.data.model.db.Country
import com.example.abhijeet.myapplication.databinding.ActivityMainBinding
import com.example.abhijeet.myapplication.ui.base.BaseActivity
import com.example.abhijeet.myapplication.ui.main.adapter.CountryAdapter
import com.example.abhijeet.myapplication.ui.main.callback.CountryClickCallback
import com.example.abhijeet.myapplication.util.adapter.EqualSpacingItemDecoration

import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator,
        CountryClickCallback {

    lateinit var adapter: CountryAdapter

    @Inject
    override lateinit var viewModel: MainViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        setUpRecyclerView()

        setUpSearchViewListener()

        viewModel.getAllCountries()
    }

    private fun setUpRecyclerView() {
        adapter = CountryAdapter(this)

        viewDataBinding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        viewDataBinding!!.recyclerView.addItemDecoration(EqualSpacingItemDecoration(dpToPx(4,this)))
        viewDataBinding!!.recyclerView.adapter = adapter
    }

    private fun setUpSearchViewListener() {
        viewDataBinding!!.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.logTextChanged(newText)
                return true
            }

        })
    }

    override fun setCountryAdapter(list: List<Country>) {
        adapter.countryList = list
        adapter.notifyDataSetChanged()
    }

    override fun onClick(project: Country) {

    }
}

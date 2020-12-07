package rocketlaunch.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rocketlaunch.R
import rocketlaunch.data.api.ApiHelper
import rocketlaunch.data.api.RetrofitBuilder
import rocketlaunch.data.model.RocketLaunchData
import rocketlaunch.ui.base.ViewModelFactory
import rocketlaunch.ui.main.adapter.RocketLaunchAdapter
import rocketlaunch.ui.main.viewmodel.RocketLaunchViewModel
import rocketlaunch.util.ApiUtil.Status

import kotlinx.android.synthetic.main.activity_rocket_launch.*

class RocketLaunchActivity : AppCompatActivity() {
    private lateinit var viewModel: RocketLaunchViewModel
    private lateinit var adapter: RocketLaunchAdapter

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_rocket_launch)
    setupViewModel()
    setupUI()
    setupObservers()
}


private fun setupViewModel() {
    viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
    ).get(RocketLaunchViewModel::class.java)
}

private fun setupUI() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = RocketLaunchAdapter(arrayListOf())
    recyclerView.addItemDecoration(
            DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
    )

    recyclerView.adapter = adapter
}

private fun setupObservers() {
    viewModel.getUsers().observe(this, Observer {
        it?.let { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    noDatatv.visibility = View.GONE

                    resource.data?.let { rocketLaunchData -> retrieveList(rocketLaunchData) }
                }
                Status.ERROR -> {
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    noDatatv.visibility = View.VISIBLE
                    noDatatv.text = this.resources.getString(R.string.no_internet_connection)
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    noDatatv.visibility = View.GONE

                }
            }
        }
    })
}

private fun retrieveList(rocketLaunchData: List<RocketLaunchData>) {
    adapter.apply {

        addData(rocketLaunchData)
        notifyDataSetChanged()
    }
}

}
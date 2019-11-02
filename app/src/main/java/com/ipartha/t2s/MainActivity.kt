
package com.ipartha.t2s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.ipartha.t2s.mvvm.ConsumerMenuViewModel
import com.ipartha.t2s.data.Result
import com.ipartha.t2s.databinding.ActivityMainBinding
import com.ipartha.t2s.mvvm.ConsumerMenuRepository
import com.ipartha.t2s.mvvm.ConsumerMenuViewModelFactory
import com.ipartha.t2s.networking.ConsumerAPI
import com.ipartha.t2s.networking.ConsumerRemoteSource
import com.ipartha.t2s.networking.RetrofitService
import com.ipartha.t2s.roomdb.DBConstants
import com.ipartha.t2s.roomdb.T2SRoomDB
import com.ipartha.t2s.ui.ConsumerMenuAdapter
import com.ipartha.t2s.ui.VerticalItemDecoration
import com.ipartha.t2s.ui.hide
import com.ipartha.t2s.ui.show


class MainActivity : AppCompatActivity() {

    lateinit var consumerMenuViewModel : ConsumerMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding  = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        initView(binding)
    }

    private fun initView(binding : ActivityMainBinding) {

        binding.recyclerView.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen.margin).toInt(), true) )

        val localDB =
            Room.databaseBuilder(applicationContext, T2SRoomDB::class.java, DBConstants.DB_NAME)
                .build()

        val consumerAPI : ConsumerAPI = RetrofitService.createService(ConsumerAPI::class.java)
        val consumerRemoteSource = ConsumerRemoteSource(consumerAPI)
        val consumerMenuRepository = ConsumerMenuRepository(localDB.consumerMenuDao(), consumerRemoteSource)
        consumerMenuViewModel = ViewModelProviders.of(this,
            ConsumerMenuViewModelFactory(consumerMenuRepository)
        ).get(ConsumerMenuViewModel::class.java)

        observeConsumerMenu(binding)
    }

    private fun observeConsumerMenu(binding : ActivityMainBinding) {
        consumerMenuViewModel.consumerMenu.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let {
                        val adapter = ConsumerMenuAdapter(it)
                        binding.recyclerView.adapter = adapter
                    }
                }
                Result.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    //Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}

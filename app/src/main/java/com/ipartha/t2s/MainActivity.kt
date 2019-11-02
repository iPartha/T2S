
package com.ipartha.t2s

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
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
import com.ipartha.t2s.ui.hide
import com.ipartha.t2s.ui.show
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.ipartha.t2s.ui.MyRecyclerViewAdapter


class MainActivity : AppCompatActivity() {

    lateinit var consumerMenuViewModel : ConsumerMenuViewModel
    lateinit var mBinding  : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {

        val localDB =
            Room.databaseBuilder(applicationContext, T2SRoomDB::class.java, DBConstants.DB_NAME)
                .build()

        val consumerAPI : ConsumerAPI = RetrofitService.createService(ConsumerAPI::class.java)
        val consumerRemoteSource = ConsumerRemoteSource(consumerAPI)
        val consumerMenuRepository = ConsumerMenuRepository(localDB.consumerMenuDao(), consumerRemoteSource)
        consumerMenuViewModel = ViewModelProviders.of(this,
            ConsumerMenuViewModelFactory(consumerMenuRepository)
        ).get(ConsumerMenuViewModel::class.java)

        observeConsumerMenu()
    }

    private fun observeConsumerMenu() {
        consumerMenuViewModel.consumerMenu.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    mBinding.progressBar.hide()
                    result.data?.let {
                        val adapter = MyRecyclerViewAdapter(it)
                        mBinding.recyclerView.adapter = adapter
                    }
                }
                Result.Status.LOADING -> {
                    mBinding.progressBar.show()
                }
                Result.Status.ERROR -> {
                    mBinding.progressBar.hide()
                    //Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}

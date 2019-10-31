
package com.ipartha.t2s

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.ipartha.t2s.mvvm.ConsumerMenuViewModel
import com.ipartha.t2s.data.Result
import com.ipartha.t2s.mvvm.ConsumerMenuRepository
import com.ipartha.t2s.mvvm.ConsumerMenuViewModelFactory
import com.ipartha.t2s.networking.ConsumerAPI
import com.ipartha.t2s.networking.ConsumerRemoteSource
import com.ipartha.t2s.networking.RetrofitService
import com.ipartha.t2s.roomdb.DBConstants
import com.ipartha.t2s.roomdb.T2SRoomDB

class MainActivity : AppCompatActivity() {

    lateinit var consumerMenuViewModel : ConsumerMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

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
        return super.onCreateView(name, context, attrs)
    }

    private fun observeConsumerMenu() {
        consumerMenuViewModel.consumerMenu.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    Log.i("Partha ", "Partha Success "+result)
                }
                Result.Status.LOADING -> {
                    Log.i("Partha ", "Partha Loading "+result)
                }
                Result.Status.ERROR -> {
                    Log.i("Partha ", "Partha Error "+result)
                }
            }
        })
    }
}

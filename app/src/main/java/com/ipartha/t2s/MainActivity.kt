
package com.ipartha.t2s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ipartha.t2s.mvvm.ConsumerMenuViewModel
import com.ipartha.t2s.data.Result
import com.ipartha.t2s.databinding.ActivityMainBinding
import com.ipartha.t2s.mvvm.ConsumerMenuRepository
import com.ipartha.t2s.mvvm.ConsumerMenuViewModelFactory
import com.ipartha.t2s.ui.ConsumerMenuAdapter
import com.ipartha.t2s.ui.hide
import com.ipartha.t2s.ui.show


class MainActivity : AppCompatActivity() {

    lateinit var consumerMenuViewModel : ConsumerMenuViewModel
    lateinit var mErrorLayout : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding  = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        initView(binding)
    }

    private fun initView(binding : ActivityMainBinding) {

        val consumerMenuRepository = ConsumerMenuRepository(this)
        consumerMenuViewModel = ViewModelProviders.of(this,
            ConsumerMenuViewModelFactory(consumerMenuRepository)
        ).get(ConsumerMenuViewModel::class.java)

        observeConsumerMenu(binding)
        consumerMenuViewModel.fetchConsumerMenu()

        binding.listener = retryClick
        mErrorLayout = binding.errorLayout.root
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
                    mErrorLayout.show()
                    binding.errorMessage = result.message
                }
            }
        })
    }

    private var retryClick = View.OnClickListener {
        mErrorLayout.hide()
        consumerMenuViewModel.refreshConsumerMenu()
    }
}

package com.ipartha.t2s.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider




class ConsumerMenuViewModel(repository: ConsumerMenuRepository) : ViewModel() {

    val consumerMenu = repository.consumerMenu

}

class ConsumerMenuViewModelFactory(private val repository: ConsumerMenuRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConsumerMenuViewModel(repository) as T
    }
}
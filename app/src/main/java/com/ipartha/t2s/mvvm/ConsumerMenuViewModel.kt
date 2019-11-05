package com.ipartha.t2s.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.channels.Channel
import androidx.lifecycle.liveData
import com.ipartha.t2s.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch


class ConsumerMenuViewModel(private val repository: ConsumerMenuRepository) : ViewModel() {

    companion object {
        private val FETCH_MENU = "fetch"
        private val REFRESH_MENU = "refresh"
    }



    private val commandsChannel = Channel<String>()

    val consumerMenu : LiveData<Result<List<ConsumerMenu>>> = liveData(Dispatchers.IO) {
        commandsChannel.consumeEach {
            emit(Result.loading())
            when(it) {
                FETCH_MENU->{
                    emit(repository.getConsumerMenu())
                }
                REFRESH_MENU->{
                    emit(repository.getConsumerMenu())
                }
            }
        }
    }

    fun fetchConsumerMenu() {
        GlobalScope.launch {
            commandsChannel.send(FETCH_MENU)
        }
    }

    fun refreshConsumerMenu() {
        GlobalScope.launch {
            commandsChannel.send(REFRESH_MENU)
        }
    }

}

class ConsumerMenuViewModelFactory(private val repository: ConsumerMenuRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConsumerMenuViewModel(repository) as T
    }
}
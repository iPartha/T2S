
package com.ipartha.t2s

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ipartha.t2s.networking.ConsumerRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        ConsumerRepository.getConsumerMenuOptions()
    }
}

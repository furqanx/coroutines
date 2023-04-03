package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCount.setOnClickListener(){
            tvCount.text = count++.toString()
        }


        btnDownloadUserData.setOnClickListener(){
            CoroutineScope(Dispatchers.Main).launch {
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData(){
        for(i in 1..1000000){
//            Log.i("MyTag","Downloading data $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){
                tvUserMessage.text = "Downloading data $i in ${Thread.currentThread().name}"
            }
        }
    }
}


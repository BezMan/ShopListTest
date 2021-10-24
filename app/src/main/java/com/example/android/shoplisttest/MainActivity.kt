package com.example.android.shoplisttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SocketHandler.ISocketListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SocketHandler(this).createSocketConnection()


    }

    override fun onDataReceived(message: String) {
        Log.d("TAG", "onTextMessage: $message")

    }


}
package com.example.android.shoplisttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SocketHandler.ISocketListener {


    private val socketHandler = SocketHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        socketHandler.createSocketConnection()




    }

    private fun initUI() {
        connBtn.setOnClickListener {
            if(connBtn.text =="Connect") {
                socketHandler.openConnection()
                connBtn.text = "Disconnect"
            } else {
                socketHandler.closeConnection()
                connBtn.text = "Connect"
            }
        }
    }

    override fun onDataReceived(message: String) {
        Log.d("zzzzz", "onTextMessage: $message")

    }


}
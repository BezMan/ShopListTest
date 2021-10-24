package com.example.android.shoplisttest

import android.util.Log
import com.example.android.shoplisttest.Consts.SOCKET_URI
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory

class SocketHandler(context: ISocketListener) {

    private lateinit var ws: WebSocket
    private var listener: ISocketListener = context


    interface ISocketListener {
        fun onDataReceived(message: String)
    }

    @Synchronized
    fun createSocketConnection() {
        try {
            // Create a WebSocket factory and set 5000 milliseconds as a timeout
            // value for socket connection.
            val factory = WebSocketFactory().setConnectionTimeout(5000)

            // Create a WebSocket. The timeout value set above is used.
            ws = factory.createSocket(SOCKET_URI)

            ws.addListener(object : WebSocketAdapter() {

                override fun onTextMessage(websocket: WebSocket, message: String) {
                    listener.onDataReceived(message)
                }

                override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                    super.onError(websocket, cause)
                    Log.d("zzzzz", "error: $cause")
                }
            })

        } catch (e: Exception) {

        }
    }


    @Synchronized
    fun openConnection() {
        createSocketConnection()
        ws.connectAsynchronously()
    }

    @Synchronized
    fun closeConnection() {
        ws.disconnect()
    }
}
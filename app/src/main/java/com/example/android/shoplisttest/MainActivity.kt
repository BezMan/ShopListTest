package com.example.android.shoplisttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SocketHandler.ISocketListener,
    ShopListAdapter.MyItemClickListener {


    private val socketHandler = SocketHandler(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var shopListAdapter: ShopListAdapter
    private var list : MutableList<ShopItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        setupListAdapter()

    }

    private fun initUI() {

        recyclerView = recView
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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

    private fun setupListAdapter() {
        shopListAdapter = ShopListAdapter(this)
        recyclerView.apply {
            adapter = shopListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    override fun onDataReceived(message: String) {
        Log.d("zzzzz", "onTextMessage: $message")

        list.add(ShopItem("", message, message))
        shopListAdapter.notifyItemInserted(0)

    }

    override fun onShopItemClick(item: ShopItem) {
        Log.d("zzzzz", "item clicked: $item")
    }


}
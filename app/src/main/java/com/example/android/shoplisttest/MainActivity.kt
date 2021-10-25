package com.example.android.shoplisttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SocketHandler.ISocketListener,
    ShopListAdapter.MyItemClickListener {


    private val socketHandler = SocketHandler(this)

    private lateinit var shopListAdapter: ShopListAdapter
    private var list : ArrayList<ShopItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        setupListAdapter()


        connBtn.performClick()

    }

    private fun initUI() {

        recView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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
        shopListAdapter = ShopListAdapter(this, list)
        shopListAdapter.setHasStableIds(true)


        recView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = shopListAdapter


        }
    }


    override fun onDataReceived(item: ShopItem) {
        Log.d("zzzzz", "onTextMessage: $item")

        list.add(0, ShopItem(list.size.toLong(), "", item.name, item.weight))
        shopListAdapter.notifyItemInserted(0)

        recView.smoothScrollToPosition(0)

    }

    override fun onShopItemClick(item: ShopItem) {
        Log.d("zzzzz", "item clicked: $item")
    }


}
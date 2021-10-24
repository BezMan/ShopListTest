package com.example.android.shoplisttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class ShopListAdapter(context: MyItemClickListener) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    private var listener: MyItemClickListener = context


    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ShopItem>() {
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return false
        }
    }

    internal val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(articleHolder: ShopItemViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        articleHolder.itemView.apply {
            item_title.text = shopItem.name
            item_weight.text = shopItem.weight
//            item_color.setBackgroundColor(shopItem.bagColor)

            setOnClickListener { listener.onShopItemClick(shopItem) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    interface MyItemClickListener {
        fun onShopItemClick(item: ShopItem)
    }


}

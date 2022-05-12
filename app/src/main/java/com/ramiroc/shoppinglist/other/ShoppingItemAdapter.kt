package com.ramiroc.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.ramiroc.shoppinglist.R
import com.ramiroc.shoppinglist.data.db.entities.ShoppingItem
import com.ramiroc.shoppinglist.data.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*


class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

        inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = "${currentItem.amount}"

        holder.itemView.ivDelete.setOnClickListener{
            viewModel.delete(currentItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
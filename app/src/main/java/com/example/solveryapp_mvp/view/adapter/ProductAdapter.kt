package com.example.solveryapp_mvp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.solveryapp_mvp.R
import com.example.solveryapp_mvp.entity.ProductViewState

interface OnProductSelected {
    fun onSelect(productViewState: ProductViewState)
}

interface OnProductDeleted {
    fun onDelete(productViewState: ProductViewState)
}

class ProductAdapter(
    private val listener: OnProductSelected,
    private val deleteListener: OnProductDeleted
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var products = emptyList<ProductViewState>()

    fun setProducts(productsViewStates: List<ProductViewState>) {
        this.products = productsViewStates
        notifyDataSetChanged()
    }

    fun itemUpdated(position: Int, productsViewStates: List<ProductViewState>) {
        this.products = productsViewStates
        notifyItemChanged(position)
    }

    fun itemDeleted(position: Int, productsViewStates: List<ProductViewState>) {
        this.products = productsViewStates
        notifyItemChanged(position)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(productViewState: ProductViewState) {
            itemView.findViewById<TextView>(R.id.name).text = productViewState.name
            itemView.findViewById<TextView>(R.id.producer).text = productViewState.producer
            itemView.findViewById<TextView>(R.id.cost).text = productViewState.cost.toString()

            val drawable = ContextCompat.getDrawable(itemView.context, productViewState.avatar)
            itemView.findViewById<ImageView>(R.id.avatar).setImageDrawable(drawable)

            itemView.setOnClickListener {
                listener.onSelect(productViewState)
            }

            itemView.findViewById<View>(R.id.deleteItemButton).setOnClickListener {
                deleteListener.onDelete(productViewState)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = products[position]
        holder.setData(product)
    }

    override fun getItemCount(): Int = products.size

}
package com.example.solveryapp_mvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

interface OnProductSelected {
    fun onSelect(product: Product)
}

interface OnProductDeleted {
    fun onDelete(product: Product)
}

class ProductAdapter(
    private val listener: OnProductSelected,
    private val deleteListener: OnProductDeleted
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var products = emptyList<Product>()

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun itemUpdated(position: Int, products: List<Product>) {
        this.products = products
        notifyItemChanged(position)
    }

    fun itemDeleted(position: Int, products: List<Product>) {
        this.products = products
        notifyItemChanged(position)
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(product: Product) {
            itemView.findViewById<TextView>(R.id.name).text = product.name
            itemView.findViewById<TextView>(R.id.producer).text = product.producer
            itemView.findViewById<TextView>(R.id.cost).text = product.cost.toString()

            val drawable = ContextCompat.getDrawable(itemView.context, product.avatar)
            itemView.findViewById<ImageView>(R.id.avatar).setImageDrawable(drawable)

            itemView.setOnClickListener {
                listener.onSelect(product)
            }

            itemView.findViewById<View>(R.id.deleteItemButton).setOnClickListener {
                deleteListener.onDelete(product)
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
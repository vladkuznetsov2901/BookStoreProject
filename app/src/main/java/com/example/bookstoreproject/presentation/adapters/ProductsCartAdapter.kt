package com.example.bookstoreproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreproject.databinding.BookItemSampleBinding
import com.example.bookstoreproject.databinding.CartItemSampleBinding
import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.Product

class ProductsCartAdapter(
    private val onAddToCartClick: (Product) -> Unit,
) :
    ListAdapter<Product, ProductsCartAdapter.CartItemViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(
            CartItemSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {

            bookName.text = item.name
            bookPrice.text = item.price.toString() + " руб."
            bookYear.text = item.year.toString()

            Glide.with(bookImage.context).load(item.imagepath).into(bookImage)
            removeFromCartButton.setOnClickListener {
                onAddToCartClick(item)
            }
        }


    }

    class CartItemViewHolder(val binding: CartItemSampleBinding) :
        RecyclerView.ViewHolder(binding.root)


    class DiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

}
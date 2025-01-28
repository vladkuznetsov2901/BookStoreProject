package com.example.bookstoreproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreproject.databinding.BookItemSampleBinding
import com.example.bookstoreproject.domain.models.Book

class ProductsAdapter(
    private val onAddToCartClick: (Book) -> Unit,
    private val isBookInCart: (Int) -> Boolean
) :
    ListAdapter<Book, ProductsAdapter.VacancyViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
            BookItemSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {

            bookName.text = item.book_name
            bookPrice.text = item.price.toString() + " руб."
            bookYear.text = item.year.toString()

            Glide.with(bookImage.context).load(item.imagepath).into(bookImage)

            if (isBookInCart(item.id_book)) {
                addToCartButton.text = "В корзине"
                addToCartButton.isEnabled = false
            } else {
                addToCartButton.text = "Добавить в корзину"
                addToCartButton.isEnabled = true
                addToCartButton.setOnClickListener {
                    onAddToCartClick(item)
                }
            }
        }


    }

    class VacancyViewHolder(val binding: BookItemSampleBinding) :
        RecyclerView.ViewHolder(binding.root)


    class DiffUtilCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem.id_book == newItem.id_book

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem == newItem
    }

}
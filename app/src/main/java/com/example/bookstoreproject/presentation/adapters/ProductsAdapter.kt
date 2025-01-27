package com.example.bookstoreproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookstoreproject.domain.models.Book

class ProductsAdapter() :
    ListAdapter<Book, VacancyAdapter.VacancyViewHolder>(DiffUtilCallback()) {

    private var onItemClickListener: OnItemClickListener? = null
    private var onFavoriteBtnClick: OnFavoriteButtonClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
            VacancyCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            if (item.lookingNumber != 0) {
                nowWatchingCountText.text =
                    "${nowWatchingCountText.text} ${item.lookingNumber} человек"
            } else nowWatchingCountText.visibility = View.GONE
            vacancyTitleText.text = item.title
            if (item.salary.short != null) {
                vacancySalaryText.text = item.salary.short
            } else vacancySalaryText.visibility = View.GONE
            vacancyCityText.text = item.address.town
            vacancyCompanyText.text = item.company
            vacancyExperienceText.text = item.experience.previewText
            publishedDateText.text = formatPublishedDate(item.publishedDate)
            vacancyButton.setOnClickListener {
                val bottomSheetDialog = ResponseBottomSheetDialog()
                bottomSheetDialog.show(fragmentManager, bottomSheetDialog.tag)
            }
            setIconToImageButton(item.isFavorite, holder.binding)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(item.id)
        }

        holder.binding.favoriteButton.setOnClickListener {
            onFavoriteBtnClick?.onFavoriteBtnClick(item.id)
            setIconToImageButton(item.isFavorite, holder.binding)
        }



    }

    fun formatPublishedDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date? = inputFormat.parse(dateString)

        val outputFormat = SimpleDateFormat("dd MMMM", Locale("ru"))

        return date?.let { "Опубликовано ${outputFormat.format(it)}" } ?: "Неизвестная дата"
    }

    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    interface OnFavoriteButtonClickListener {
        fun onFavoriteBtnClick(id: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setOnFavoriteButtonClickListener(listener: OnFavoriteButtonClickListener) {
        onFavoriteBtnClick = listener
    }

    fun setIconToImageButton(isInDb: Boolean, binding: VacancyCardItemBinding) {
        if (isInDb) {
            binding.favoriteButton.setImageResource(
                R.drawable.favorites_true_ic
            )
        } else binding.favoriteButton.setImageResource(
            R.drawable.favorites_ic
        )
    }

    class VacancyViewHolder(val binding: VacancyCardItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    class DiffUtilCallback : DiffUtil.ItemCallback<Vacancy>() {
        override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean =
            oldItem == newItem
    }

}
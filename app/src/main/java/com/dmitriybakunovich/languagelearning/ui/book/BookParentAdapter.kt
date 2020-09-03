package com.dmitriybakunovich.languagelearning.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmitriybakunovich.languagelearning.R
import com.dmitriybakunovich.languagelearning.data.model.BookParentModel
import kotlinx.android.synthetic.main.item_book_category.view.*

class BookParentAdapter(private val clickListener: BookAdapter.OnItemClickListener) :
    ListAdapter<BookParentModel, BookParentAdapter.BookParentViewHolder>(DiffCallback()) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_book_category, parent, false
        )
        val adapter = BookAdapter(clickListener)
        val viewHolder = BookParentViewHolder(view, adapter)
        initRecycler(viewHolder, adapter)
        return viewHolder
    }

    private fun initRecycler(viewHolder: BookParentViewHolder, bookAdapter: BookAdapter) {
        viewHolder.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                viewHolder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false
            )
            itemAnimator = null
            setRecycledViewPool(viewPool)
            adapter = bookAdapter
        }
    }

    override fun onBindViewHolder(holder: BookParentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<BookParentModel>() {
        override fun areItemsTheSame(oldItem: BookParentModel, newItem: BookParentModel): Boolean {
            return oldItem.category == newItem.category
        }

        override fun areContentsTheSame(
            oldItem: BookParentModel,
            newItem: BookParentModel
        ): Boolean {
            return oldItem.books == newItem.books
        }
    }

    class BookParentViewHolder(itemView: View, private var adapter: BookAdapter) :
        RecyclerView.ViewHolder(itemView) {

        val recyclerView: RecyclerView = itemView.recyclerBookChild
        private val textCategory: TextView = itemView.textTitle

        fun bind(book: BookParentModel) {
            textCategory.text = book.category
            adapter.submitList(book.books)
        }
    }
}
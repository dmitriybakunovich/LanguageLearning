package com.dmitriybakunovich.languagelearning.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmitriybakunovich.languagelearning.R
import com.dmitriybakunovich.languagelearning.data.db.entity.BookData
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(
    private val books: List<BookData>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false),
            clickListener
        )

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    fun getBook(): List<BookData> = books

    class BookViewHolder(itemView: View, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val nameBook: TextView = itemView.nameBook
        private val progressBook: TextView = itemView.progressBook

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(book: BookData) {
            nameBook.text = book.bookName
            progressBook.text = book.progressRead.toString()
        }
    }
}
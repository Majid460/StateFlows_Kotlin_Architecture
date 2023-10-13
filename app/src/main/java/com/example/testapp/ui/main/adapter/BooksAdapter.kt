package com.example.testapp.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.data.models.books.BooksResponse
import com.example.testapp.databinding.ItemBookLayoutBinding
import com.example.testapp.utils.ImageUtils

class BooksAdapter(val context: Context, val onItemClickListener:(item: BooksResponse)->Unit) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private var data: MutableList<BooksResponse> = arrayListOf()
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: MutableList<BooksResponse>) {
        if (data.isEmpty()) {
            this.data = arrayListOf()
            notifyDataSetChanged()
        } else {
            if (this.data.isEmpty()) {
                this.data = data
                notifyDataSetChanged()
            } else {
                this.data = arrayListOf()
                this.data = data
                notifyDataSetChanged()
            }
        }
    }
    inner class ViewHolder(private val binding: ItemBookLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: BooksResponse) {
            binding.apply {
                ImageUtils.loadImageWithRoundedCorners(
                    ivAvatar,
                    item.url,
                    R.drawable.placeholder_user,
                    8F
                )
                tvName.text = item.title
                tvAuthorName.text = item.author
                card.setOnClickListener {

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(layoutInflater,
            R.layout.item_book_layout,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
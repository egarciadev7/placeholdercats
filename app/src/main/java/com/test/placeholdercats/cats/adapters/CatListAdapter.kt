package com.test.placeholdercats.cats.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.entities.Cat
import com.test.placeholdercats.R
import com.test.placeholdercats.databinding.CatItemBinding

class CatListAdapter :
    ListAdapter<Cat, CatListAdapter.ViewHolder>(CatAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setCatUI(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CatItemBinding.bind(itemView)

        fun setCatUI(
            cat: Cat,
        ) {
            val context = binding.root.context
            binding.name.text = cat.breedName
            binding.country.text = cat.origin
            binding.intelligence.text = context.getString(R.string.intelligence, cat.intelligence.toString())
            cat.imageUrl?.let {
                Glide.with(context).load(it).into(binding.image)
            }
        }
    }
}

class CatAdapterDiffCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }
}


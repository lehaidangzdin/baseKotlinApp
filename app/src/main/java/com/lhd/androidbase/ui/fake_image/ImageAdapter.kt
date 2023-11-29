package com.lhd.androidbase.ui.fake_image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lhd.androidbase.databinding.ItemImagesBinding

class ImageAdapter(private var lsImages: List<String> = ArrayList()) :
    RecyclerView.Adapter<ImageAdapter.ItemView>() {

    fun setData(ls: List<String>) {
        lsImages = ls
        notifyDataSetChanged()
    }

    class ItemView(val binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            ItemImagesBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return lsImages.size
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val item = lsImages[position]
        holder.binding.image = item
//        holder.binding.itemProduct.setOnClickListener{
//            this.listener.clickItem(item)
//        }
    }
}
package com.example.mini_sport_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_stories.view.*


class StoriesRecyclerViewAdapter(var items: List<Items> = ArrayList(), val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_stories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is StoriesViewHolder -> {
                holder.bindData(items.get(position), itemClickListener)
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun storiesList(storiesList: List<Items>) {
        items = storiesList
        notifyDataSetChanged()
    }

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image_view
        val title = itemView.text_view
        val durationIcon = itemView.icon_duration
        val durationText = itemView.duration_text_view
        val sectionLabel = itemView.sport_section_label

        fun bindData(storyItems: Items, clickListener: OnItemClickListener) {
            title.text = storyItems.title
            durationIcon.setImageResource(R.drawable.ic_duration)
            durationText.text = storyItems.lastUpdatedText
            sectionLabel.text = storyItems.sectionLabel

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(storyItems.image?.large)
                .into(image)
            itemView.setOnClickListener {
                clickListener.onItemClicked(it, layoutPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(view: View, position: Int)
    }
}
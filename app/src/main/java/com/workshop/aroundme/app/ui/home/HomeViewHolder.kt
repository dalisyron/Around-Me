package com.workshop.aroundme.app.ui.home

import android.media.Image
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.PlaceEntity
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.AsyncTask
import java.net.URL

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)
    private val addressTextView = itemView.findViewById<TextView>(R.id.address)
    private val thumbnailImageView = itemView.findViewById<ImageView>(R.id.image)
    private val likeCountTextView = itemView.findViewById<TextView>(R.id.likeCountTextView)
    private val likeCountImageView = itemView.findViewById<ImageView>(R.id.likeCountImageView)

    fun bind(placeEntity: PlaceEntity, onPlaceListItemClickListener: OnPlaceListItemClickListener) {
        nameTextView.text = placeEntity.name
        addressTextView.text = placeEntity.address
        if (placeEntity.likeCount?:0 > 0) {
            likeCountTextView.text = placeEntity.likeCount.toString()
            likeCountImageView.visibility = View.VISIBLE
        } else {
            likeCountTextView.text = ""
            likeCountImageView.visibility = View.GONE
        }

        thumbnailImageView.setImageBitmap(placeEntity.images?.getOrNull(0)?.bitmap)
        itemView.setOnClickListener {
            onPlaceListItemClickListener.onItemClicked(placeEntity)
        }

    }

}
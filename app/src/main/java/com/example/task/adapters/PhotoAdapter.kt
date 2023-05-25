package com.example.task.adapters

import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.task.R
import com.example.task.models.LikesObject

class PhotoAdapter(var context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    lateinit var data : LikesObject
    var shouldShow : Boolean = false

    internal fun setData(data: LikesObject) {
        this.data = data
        shouldShow=data.canSeeProfiles
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView

        init {
            image = itemView.findViewById(R.id.imageViewer)
            title = itemView.findViewById(R.id.likeName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        var current = data.profiles[position]
        Glide.with(context)
            .load(current.avatar)//current.avatar, test data
            .apply(RequestOptions().override(450, 600))
            .into(holder.image)

        if (!shouldShow) {
            holder.image.setRenderEffect(
                RenderEffect.createBlurEffect(
                    20.0f, 20.0f, Shader.TileMode.CLAMP
                )
            )
        }
        holder.title.setText(current.firstName)

    }


    override fun getItemCount() = data.likesReceived
}
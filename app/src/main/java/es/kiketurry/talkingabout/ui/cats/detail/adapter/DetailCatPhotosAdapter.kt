package es.kiketurry.talkingabout.ui.cats.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.R

class DetailCatPhotosAdapter(context: Context, private val dataSet: ArrayList<String>, private val itemPhotoClickListener: ItemPhotoClickListener): RecyclerView.Adapter<DetailCatPhotoViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    interface ItemPhotoClickListener {
        fun onItemPhotoClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCatPhotoViewHolder {
        val view = layoutInflater.inflate(R.layout.recyclerview_item_cat_photo, parent, false)
        return DetailCatPhotoViewHolder(view, itemPhotoClickListener)
    }

    override fun onBindViewHolder(holder: DetailCatPhotoViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun refreshPhotos(listPhotos: ArrayList<String>){
        dataSet.clear()
        dataSet.addAll(listPhotos)
        notifyDataSetChanged()
    }

}
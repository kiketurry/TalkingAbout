package es.kiketurry.talkingabout.ui.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.RecyclerviewItemPhotoBinding

class DetailCatPhotoViewHolder(
    var view: View,
    private val photoClickListener: DetailCatPhotosAdapter.ItemPhotoClickListener
) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerviewItemPhotoBinding.bind(view)

    fun bind(urlPhoto: String) {
        binding.ivPhoto

        Glide.with(view.context)
            .load(urlPhoto)
            .placeholder(R.drawable.loading_glide)
            .apply(RequestOptions().error(R.drawable.loading_glide))
            .into(binding.ivPhoto)

        view.setOnClickListener { photoClickListener.onItemPhotoClick(adapterPosition) }
    }

}

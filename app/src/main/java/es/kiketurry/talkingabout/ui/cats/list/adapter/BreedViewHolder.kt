package es.kiketurry.talkingabout.ui.cats.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.databinding.RecyclerviewItemCatBreedBinding

class BreedViewHolder(var view: View, private val breedClickListener: BreedsAdapter.ItemBreedClickListener) :
    RecyclerView.ViewHolder(view) {

    private val binding = RecyclerviewItemCatBreedBinding.bind(view)


    fun bind(breed: String) {
        binding.tvBreed.text = breed
        view.setOnClickListener { breedClickListener.onItemBreedClick(adapterPosition) }
    }

}

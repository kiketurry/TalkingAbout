package es.kiketurry.talkingabout.ui.bgg.listthings.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.databinding.RecyclerviewItemBggThingBinding
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ListThingsBGGAdapter.ItemThingBGGClickListener

class ThingBGGViewHolder(var view: View, private val thingBGGClickListener: ItemThingBGGClickListener) : RecyclerView.ViewHolder(view) {

    private val binding = RecyclerviewItemBggThingBinding.bind(view)

    fun bind(thingBGGModel: ThingBGGModel) {
        Glide.with(view.context)
            .load(thingBGGModel.image)
            .placeholder(R.drawable.loading_image_bgg)
            .apply(RequestOptions().error(R.drawable.loading_image_bgg))
            .into(binding.ivImage)

        if (thingBGGModel.nameEs.isNullOrBlank()) {
            binding.tvName.text = thingBGGModel.nameFirst
        } else {
            binding.tvName.text = thingBGGModel.nameEs
        }

        binding.ivWhatsapp.setOnClickListener { thingBGGClickListener.onWhatsappThingClick(thingBGGModel) }
        view.setOnClickListener { thingBGGClickListener.onItemThingClick(thingBGGModel) }
    }

}

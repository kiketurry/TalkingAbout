package es.kiketurry.talkingabout.ui.bgg.listthings.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel

class ListThingsBGGAdapter(
    context: Context,
    private val dataSet: ArrayList<ThingBGGModel>,
    private val itemThingsBGGClickListener: ItemThingBGGClickListener
) : RecyclerView.Adapter<ThingBGGViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    interface ItemThingBGGClickListener {
        fun onItemThingClick(position: ThingBGGModel)
        fun onWhatsappThingClick(position: ThingBGGModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingBGGViewHolder {
        val view = layoutInflater.inflate(R.layout.recyclerview_item_bgg_thing, parent, false)
        return ThingBGGViewHolder(view, itemThingsBGGClickListener)
    }

    override fun onBindViewHolder(holder: ThingBGGViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun refreshThings(listThings: ArrayList<ThingBGGModel>) {
        dataSet.clear()
        dataSet.addAll(listThings)
        notifyDataSetChanged()
    }

}
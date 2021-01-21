package es.kiketurry.talkingabout.ui.cats.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.R

class BreedsAdapter(context: Context, private val dataSet: ArrayList<String>, private val itemBreedClickListener: ItemBreedClickListener): RecyclerView.Adapter<BreedViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    interface ItemBreedClickListener {
        fun onItemBreedClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = layoutInflater.inflate(R.layout.recyclerview_item_cat_breed, parent, false)
        return BreedViewHolder(view, itemBreedClickListener)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun refreshBreeds(listBreeds: ArrayList<String>){
        dataSet.clear()
        dataSet.addAll(listBreeds)
        notifyDataSetChanged()
    }

}
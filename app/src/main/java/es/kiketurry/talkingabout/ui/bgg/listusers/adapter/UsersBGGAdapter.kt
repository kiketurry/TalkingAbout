package es.kiketurry.talkingabout.ui.bgg.listusers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel

class UsersBGGAdapter(
    context: Context,
    private val dataSet: ArrayList<UserBGGModel>,
    private val itemUserBGGClickListener: ItemUserBGGClickListener
) : RecyclerView.Adapter<UserBGGViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    interface ItemUserBGGClickListener {
        fun onItemUserBGGClick(userBGGModel: UserBGGModel)
        fun onEditUserBGGClick(userBGGModel: UserBGGModel)
        fun onDeleteUserBGGClick(userBGGModel: UserBGGModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBGGViewHolder {
        val view = layoutInflater.inflate(R.layout.recyclerview_item_user_bgg, parent, false)
        return UserBGGViewHolder(view, itemUserBGGClickListener)
    }

    override fun onBindViewHolder(holder: UserBGGViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun refreshUsers(listUserBGG: ArrayList<UserBGGModel>) {
        dataSet.clear()
        dataSet.addAll(listUserBGG)
        notifyDataSetChanged()
    }

}
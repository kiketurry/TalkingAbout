package es.kiketurry.talkingabout.ui.bgg.listusers.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.databinding.RecyclerviewItemBggUserBinding

class UserBGGViewHolder(var view: View, private val userBGGClickListener: UsersBGGAdapter.ItemUserBGGClickListener) :
    RecyclerView.ViewHolder(view) {

    private val binding = RecyclerviewItemBggUserBinding.bind(view)

    fun bind(userBGGModel: UserBGGModel) {
        if (adapterPosition % 2 == 0) {
            binding.clContainer.setBackgroundColor(view.context.getColor(R.color.backgroundUserBGGListLight))
        } else {
            binding.clContainer.setBackgroundColor(view.context.getColor(R.color.backgroundUserBGGListDark))
        }
        binding.tvName.text = userBGGModel.name
        binding.tvNick.text = userBGGModel.userBGG
        binding.tvPhone.text = "(${userBGGModel.prefix}) ${userBGGModel.phone}"
        binding.tvEmail.text = userBGGModel.email
        view.setOnClickListener { userBGGClickListener.onItemUserBGGClick(userBGGModel) }
        binding.ivDeleteUserBGG.setOnClickListener { userBGGClickListener.onDeleteUserBGGClick(userBGGModel) }
        binding.ivEditUserBGG.setOnClickListener { userBGGClickListener.onEditUserBGGClick(userBGGModel) }
    }

}

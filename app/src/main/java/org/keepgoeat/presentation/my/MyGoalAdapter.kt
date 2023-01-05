package org.keepgoeat.presentation.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.keepgoeat.databinding.ItemMyGoalBinding
import org.keepgoeat.domain.model.AchievedGoal
import org.keepgoeat.util.ItemDiffCallback

class MyGoalAdapter :
    ListAdapter<AchievedGoal, MyGoalAdapter.MyGoalViewHolder>(
        ItemDiffCallback<AchievedGoal>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.id == new.id }
        )
    ) {
    private lateinit var inflater: LayoutInflater

    class MyGoalViewHolder(private val binding: ItemMyGoalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AchievedGoal) {
            binding.goal = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGoalViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        return MyGoalViewHolder(ItemMyGoalBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyGoalViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
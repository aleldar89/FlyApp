package com.example.airtickets_feature.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.BaseModel

class BaseModelDiffCallback : DiffUtil.ItemCallback<BaseModel>() {
    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem == newItem
    }
}

package com.example.airtickets_feature.adapters

import com.example.domain.models.BaseModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class BaseAdapter : AsyncListDifferDelegationAdapter<BaseModel>(BaseModelDiffCallback()) {
    init {
        delegatesManager
            .addDelegate(offerAdapterDelegate())
            .addDelegate(ticketOfferAdapterDelegate())
            .addDelegate(ticketAdapterDelegate())
    }
}
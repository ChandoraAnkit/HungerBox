package com.androidy.hungerbox.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


/**
 * Created by Ankit
 */


abstract class BaseListAdapter<T, VH: BaseViewHolder>(diffUtil: DiffUtil.ItemCallback<T>)
    : ListAdapter<T, VH>(diffUtil) {

    override fun onViewAttachedToWindow(holder: VH) {
        holder.onShow()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        holder.onHide()
    }

}
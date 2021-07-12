package com.androidy.hungerbox.commons.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Ankit
 */


open class BaseViewHolder(private val itemBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(itemBinding.root), LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    open fun bind(pos: Int) {
        itemBinding.run {
            lifecycleOwner = this@BaseViewHolder
            executePendingBindings()
        }
    }

    override fun getLifecycle() = lifecycleRegistry

    fun onShow(){
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun onHide(){
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

}
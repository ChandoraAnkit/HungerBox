package com.androidy.hungerbox.commons.extensions

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun <V: ViewDataBinding>ViewGroup.bindingInflate(@LayoutRes layoutId: Int) =
        DataBindingUtil.inflate<ViewDataBinding>(context.inflater(),
                layoutId,this, false) as V

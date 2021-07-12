package com.androidy.hungerbox.commons.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.androidy.hungerbox.navigation.Navigator
import javax.inject.Inject


abstract class BaseFragment<T : ViewDataBinding, V : ViewModel>(
    @LayoutRes private val layoutId: Int) : Fragment() {

//    @Inject
//    lateinit var navigator: Navigator

    lateinit var viewBinding: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
    }

    abstract fun initDi()

    abstract fun initDataBinding()
}

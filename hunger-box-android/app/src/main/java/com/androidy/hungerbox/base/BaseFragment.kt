package com.androidy.hungerbox.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.androidy.hungerbox.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment<out T: ViewDataBinding, out V: BaseViewModel> : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    private lateinit var mViewDataBinding: T
    private lateinit var mRootView: View

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariables(): Map<Int, Any?>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.apply {
            getBindingVariables().forEach {
                setVariable(it.key, it.value)
            }
            lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        }

    }

    protected fun viewDataBinding() = mViewDataBinding

    protected fun viewModelFactory() = mViewModelFactory
}

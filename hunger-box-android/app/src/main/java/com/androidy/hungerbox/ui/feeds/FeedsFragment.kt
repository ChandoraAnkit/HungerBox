package com.androidy.hungerbox.ui.feeds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentFeedsBinding
import com.androidy.hungerbox.utils.ext.getDrawerLayout
import com.androidy.hungerbox.utils.ext.setUpToolbar

import kotlinx.android.synthetic.main.layout_toolbar.*

class FeedsFragment : BaseFragment<FragmentFeedsBinding, FeedsViewModel>() {

    private val mViewModel by viewModels<FeedsViewModel>{ viewModelFactory()}

    override fun getLayoutId() = R.layout.fragment_feeds

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(toolbar,R.id.navigation_feeds_fragment, drawerLayout = getDrawerLayout())
    }

}

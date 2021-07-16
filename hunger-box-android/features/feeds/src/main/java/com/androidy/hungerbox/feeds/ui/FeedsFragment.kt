package com.androidy.hungerbox.feeds.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androidy.hungerbox.commonUi.databinding.LayoutToolbarBinding

import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.commons.extensions.setUpToolbar
import com.androidy.hungerbox.coreComponent
import com.androidy.hungerbox.feeds.R
import com.androidy.hungerbox.feeds.databinding.FragmentFeedsBinding
import com.androidy.hungerbox.feeds.di.DaggerFeedsComponent
import com.androidy.hungerbox.navigation.NavigationMainDirections
import com.androidy.hungerbox.utils.getDrawerLayout

class FeedsFragment : BaseFragment<FragmentFeedsBinding, FeedsViewModel>
    (R.layout.fragment_feeds) {

    private val viewModel by viewModels<FeedsViewModel>()
    private lateinit var toolbarBinding: LayoutToolbarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = LayoutToolbarBinding.inflate(layoutInflater)
        setUpToolbar(toolbarBinding.toolbar, R.id.fragment_feeds, drawerLayout = getDrawerLayout())
    }

    override fun initDi() {
        DaggerFeedsComponent
            .builder()
            .coreComponent(coreComponent())
            .feedsFragment(this)
            .build()
            .inject(this)
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }
}

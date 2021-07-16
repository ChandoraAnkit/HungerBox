package com.androidy.hungerbox.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.androidy.hungerbox.commonUi.databinding.LayoutToolbarBinding
import com.androidy.hungerbox.commons.base.BaseFragment

import com.androidy.hungerbox.commons.extensions.getDrawerLayout

import com.androidy.hungerbox.commons.extensions.setUpToolbar
import com.androidy.hungerbox.coreComponent
import com.androidy.hungerbox.profile.R
import com.androidy.hungerbox.profile.databinding.FragmentProfileBinding
import com.androidy.hungerbox.profile.di.DaggerProfileComponent
import com.androidy.hungerbox.profile.di.ProfileModule

import com.androidy.hungerbox.utils.getDrawerLayout


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>
    (R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var toolbarBinding: LayoutToolbarBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = LayoutToolbarBinding.inflate(layoutInflater)
        setUpToolbar(toolbarBinding.toolbar, R.id.fragment_profile, drawerLayout = getDrawerLayout())
    }

    override fun initDi() {
        DaggerProfileComponent
            .builder()
            .coreComponent(coreComponent())
            .profileFragment(this)
            .build()
            .inject(this)
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
        
    }

}

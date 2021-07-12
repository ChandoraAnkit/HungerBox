package com.androidy.hungerbox.auth.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.androidy.hungerbox.auth.R
import com.androidy.hungerbox.auth.databinding.FragmentRegisterBinding
import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.auth.AuthViewModel
import com.androidy.hungerbox.commonUi.databinding.LayoutToolbarBinding
import com.androidy.hungerbox.commons.extensions.hideLoader
import com.androidy.hungerbox.commons.extensions.setUpSnackBar
import com.androidy.hungerbox.commons.extensions.setUpToolbar
import com.androidy.hungerbox.commons.extensions.showLoader
import com.androidy.hungerbox.utils.EventObserver

class RegisterFragment : BaseFragment<FragmentRegisterBinding, AuthViewModel>
    (R.layout.fragment_register) {

    private val viewModel by activityViewModels<AuthViewModel>()
    private lateinit var toolbarBinding: LayoutToolbarBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = LayoutToolbarBinding.inflate(layoutInflater)

        setUpToolbar(toolbarBinding.toolbar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpNavigation()
        setUpSnackBar()
        setObservers()
    }

    private fun setUpNavigation() {
        viewModel.registerUserEvent.observe(viewLifecycleOwner, EventObserver {
//            val navOptions = NavOptions.Builder()
//                    .setPopUpTo(R.id.navigation_register_graph, true)
//                    .build()
//            findNavController().navigate(R.id.mobile_navigation,null, navOptions)
        })
    }

    private fun setObservers() {
        viewModel.isDataLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                view.showLoader("Please Wait...")
            else
                view.hideLoader()
        })
    }

    private fun setUpSnackBar() {
        view?.setUpSnackBar(viewLifecycleOwner, viewModel.snackBarTextEvent)
    }

    override fun initDi() {
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

}

package com.androidy.hungerbox.auth.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.auth.AuthViewModel
import com.androidy.hungerbox.auth.R
import com.androidy.hungerbox.auth.databinding.FragmentLoginBinding
import com.androidy.hungerbox.commonUi.databinding.LayoutToolbarBinding
import com.androidy.hungerbox.commons.extensions.*
import com.androidy.hungerbox.utils.EventObserver


class LoginFragment : BaseFragment<FragmentLoginBinding, AuthViewModel>
    (R.layout.fragment_login) {

    private val viewModel by activityViewModels<AuthViewModel>()
    private lateinit var toolbarBinding: LayoutToolbarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = LayoutToolbarBinding.inflate(layoutInflater)
        getAppCompatActivity().supportActionBar?.setDisplayHomeAsUpEnabled(false)

//        setUpToolbar(toolbarBinding.toolbar, R.id.navigation_login_fragment)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpNavigation()
        setUpSnackBar()
        setObservers()
    }

    private fun setUpNavigation() {
        viewModel.registerUserEvent.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(R.id.action_login_fragment_to_register_fragment)
        })

        viewModel.loginUserEvent.observe(viewLifecycleOwner, EventObserver{
//            val navOptions = NavOptions.Builder()
//                    .setPopUpTo(R.id.navigation_register_graph, true)
//                    .build()
//            findNavController().navigate(R.id.mobile_navigation,null, navOptions)
        })
    }

    private fun setUpSnackBar() {
        view?.setUpSnackBar(viewLifecycleOwner, viewModel.snackBarTextEvent)
    }

    private fun setObservers() {
        viewModel.isDataLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                view.showLoader("Please Wait...")
            else
                view.hideLoader()
        })
    }

    override fun initDi() {
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

}

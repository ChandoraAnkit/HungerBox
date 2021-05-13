package com.androidy.hungerbox.ui.loginRegister.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentLoginBinding
import com.androidy.hungerbox.ui.loginRegister.LoginRegisterViewModel
import com.androidy.hungerbox.utils.EventObserver
import com.androidy.hungerbox.utils.ext.hideLoader
import com.androidy.hungerbox.utils.ext.setUpSnackBar
import com.androidy.hungerbox.utils.ext.setUpToolbar
import com.androidy.hungerbox.utils.ext.showLoader
import kotlinx.android.synthetic.main.layout_toolbar.*


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginRegisterViewModel>() {

    private val mViewModel by activityViewModels<LoginRegisterViewModel>{ viewModelFactory()}

    override fun getLayoutId() = R.layout.fragment_login

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(toolbar, R.id.navigation_login_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpNavigation()
        setUpSnackBar()
        setObservers()
    }

    private fun setUpNavigation() {
        mViewModel.registerUserEvent.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(R.id.action_login_fragment_to_register_fragment)
        })

        mViewModel.loginUserEvent.observe(viewLifecycleOwner, EventObserver{
            val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.navigation_register_graph, true)
                    .build()
            findNavController().navigate(R.id.mobile_navigation,null, navOptions)
        })
    }

    private fun setUpSnackBar() {
        view?.setUpSnackBar(viewLifecycleOwner, mViewModel.snackBarTextEvent)
    }

    private fun setObservers() {
        mViewModel.isDataLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                view.showLoader("Please Wait...")
            else
                view.hideLoader()
        })
    }

}

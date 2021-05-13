package com.androidy.hungerbox.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentProfileBinding
import com.androidy.hungerbox.utils.ext.getDrawerLayout
import com.androidy.hungerbox.utils.ext.setUpToolbar
import kotlinx.android.synthetic.main.layout_toolbar.*

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private val mViewModel by viewModels<ProfileViewModel>{ viewModelFactory()}

    override fun getLayoutId() = R.layout.fragment_profile

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(toolbar,R.id.navigation_profile_fragment, drawerLayout = getDrawerLayout())
    }

}

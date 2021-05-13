package com.androidy.hungerbox.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentNotificationsBinding
import com.androidy.hungerbox.utils.ext.getDrawerLayout
import com.androidy.hungerbox.utils.ext.setUpToolbar
import kotlinx.android.synthetic.main.layout_toolbar.*

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    private val mViewModel by viewModels<NotificationsViewModel>{ viewModelFactory()}

    override fun getLayoutId() = R.layout.fragment_notifications

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(toolbar,R.id.navigation_notifications_fragment, drawerLayout = getDrawerLayout())
    }

}


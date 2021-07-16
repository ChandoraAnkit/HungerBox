package com.androidy.hungerbox.utils

import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.androidy.hungerbox.R


fun Fragment.getDrawerLayout(): DrawerLayout =
    requireActivity().findViewById(R.id.drawer_layout)
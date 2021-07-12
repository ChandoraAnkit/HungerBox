package com.androidy.hungerbox.addrecipe.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Ankit
 */


class AddRecipeStepsPagerAdapter(fragment: Fragment,
                                 private val destFragments: List<Fragment>)
    : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) = destFragments[position]

    override fun getItemCount() = destFragments.size

}
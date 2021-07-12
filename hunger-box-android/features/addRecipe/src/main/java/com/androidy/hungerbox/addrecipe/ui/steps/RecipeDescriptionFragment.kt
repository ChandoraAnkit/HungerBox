package com.androidy.hungerbox.addrecipe.ui.steps

import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.addRecipe.R
import com.androidy.hungerbox.addRecipe.databinding.FragmentRecipeDescriptionBinding

import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.addrecipe.ui.AddRecipeViewModel


class RecipeDescriptionFragment : BaseFragment<FragmentRecipeDescriptionBinding,
        AddRecipeViewModel>(R.layout.fragment_recipe_description) {

    private val viewModel by requireParentFragment().viewModels<AddRecipeViewModel>()

    override fun initDi() {
        TODO("Not yet implemented")
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
    }

}

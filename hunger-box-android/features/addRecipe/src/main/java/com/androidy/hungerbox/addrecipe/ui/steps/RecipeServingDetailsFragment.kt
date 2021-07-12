package com.androidy.hungerbox.addrecipe.ui.steps

import androidx.fragment.app.viewModels
import com.androidy.hungerbox.addRecipe.R
import com.androidy.hungerbox.addRecipe.databinding.FragmentRecipeServingDetailsBinding
import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.addrecipe.ui.AddRecipeViewModel

class RecipeServingDetailsFragment : BaseFragment<FragmentRecipeServingDetailsBinding,
        AddRecipeViewModel>(R.layout.fragment_recipe_serving_details) {

    private val viewModel by requireParentFragment().viewModels<AddRecipeViewModel>()


    override fun initDi() {
        TODO("Not yet implemented")
    }

    override fun initDataBinding() {
        viewBinding.viewModel =  viewModel
        viewBinding.lifecycleOwner = this
    }

}

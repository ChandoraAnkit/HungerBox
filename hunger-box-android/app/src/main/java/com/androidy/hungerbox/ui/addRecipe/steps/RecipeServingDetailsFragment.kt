package com.androidy.hungerbox.ui.addRecipe.steps

import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentRecipeServingDetailsBinding
import com.androidy.hungerbox.ui.addRecipe.AddRecipeViewModel

class RecipeServingDetailsFragment : BaseFragment<FragmentRecipeServingDetailsBinding,
        AddRecipeViewModel>() {

    private val mViewModel by lazy {
        requireParentFragment()
                .viewModels<AddRecipeViewModel> {viewModelFactory()}.value
    }

    override fun getLayoutId() = R.layout.fragment_recipe_serving_details

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)

}

package com.androidy.hungerbox.ui.addRecipe.steps

import androidx.fragment.app.viewModels
import com.androidy.hungerbox.BR

import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentRecipeDescriptionBinding
import com.androidy.hungerbox.ui.addRecipe.AddRecipeViewModel


class RecipeDescriptionFragment : BaseFragment<FragmentRecipeDescriptionBinding,
        AddRecipeViewModel>() {

    private val mViewModel by lazy {
        requireParentFragment()
                .viewModels<AddRecipeViewModel> {viewModelFactory()}.value
    }

    override fun getLayoutId() = R.layout.fragment_recipe_description

    override fun getBindingVariables() =
        mapOf(BR.viewModel to mViewModel)


}

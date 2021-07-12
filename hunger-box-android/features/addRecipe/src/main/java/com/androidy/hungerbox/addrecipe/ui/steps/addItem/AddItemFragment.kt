package com.androidy.hungerbox.addrecipe.ui.steps.addItem


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.addRecipe.R
import com.androidy.hungerbox.addRecipe.databinding.FragmentAddItemBinding
import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.addrecipe.ui.AddRecipeViewModel


class AddItemFragment : BaseFragment<FragmentAddItemBinding,
        AddRecipeViewModel>(R.layout.fragment_add_item) {

    private val viewModel  by requireParentFragment().viewModels<AddRecipeViewModel>()

    private var pageType = INGREDIENT

    private val ingredientAdapter by lazy { AddItemsAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            pageType = getInt(ARG_TYPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ingredientRv.layoutManager = LinearLayoutManager(activity)
        viewBinding.ingredientRv.adapter = ingredientAdapter
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.pageType = pageType
    }

    override fun initDi() {
        TODO("Not yet implemented")
    }

    companion object {

        const val INGREDIENT = 0
        const val DIRECTION = 1

        private const val ARG_TYPE = "arg_type"

        fun newInstance(type: Int) =
                AddItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_TYPE, type)
                    }
                }
    }

}

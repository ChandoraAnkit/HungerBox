package com.androidy.hungerbox.ui.addRecipe.steps.addItem


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentAddItemBinding
import com.androidy.hungerbox.ui.addRecipe.AddRecipeViewModel


class AddItemFragment : BaseFragment<FragmentAddItemBinding,
        AddRecipeViewModel>() {

    private val mViewModel by lazy {
        requireParentFragment()
                .viewModels<AddRecipeViewModel> { viewModelFactory() }.value
    }

    private var pageType = INGREDIENT

    private val ingredientAdapter by lazy { AddItemsAdapter(mViewModel) }

    override fun getLayoutId() = R.layout.fragment_add_item

    override fun getBindingVariables() =
            mapOf(BR.pageType to pageType, BR.viewModel to mViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            pageType = getInt(ARG_TYPE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding().ingredientRv.layoutManager = LinearLayoutManager(activity)
        viewDataBinding().ingredientRv.adapter = ingredientAdapter
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

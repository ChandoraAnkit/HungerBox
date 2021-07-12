package com.androidy.hungerbox.addrecipe.ui

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.androidy.hungerbox.addrecipe.ui.steps.addItem.AddItemFragment
import com.androidy.hungerbox.addrecipe.ui.steps.addItem.AddItemsAdapter


@BindingAdapter("app:items")
fun RecyclerView.setItems(items: List<com.androidy.hungerbox.addrecipe.data.Item>) {
    (adapter as AddItemsAdapter)
            .submitList(items.toMutableList())
}

@BindingAdapter("app:pageType", "app:viewModel", requireAll = true )
fun EditText.setData(pageType: Int, viewModel: AddRecipeViewModel) {
    doAfterTextChanged { text ->
        if (pageType == AddItemFragment.INGREDIENT)
            viewModel.ingredient.value = text.toString()
        else
            viewModel.direction.value = text.toString()
    }
}

@BindingAdapter("app:onPageChangedListener")
fun ViewPager2.onPageChangedListener(viewModel: AddRecipeViewModel) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.currentStep.value = RecipeSteps.values()[position]
        }
    })
}
package com.androidy.hungerbox.ui.addRecipe

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.androidy.hungerbox.data.addRecipe.Item
import com.androidy.hungerbox.ui.addRecipe.steps.addItem.AddItemFragment
import com.androidy.hungerbox.ui.addRecipe.steps.addItem.AddItemsAdapter


@BindingAdapter("app:items")
fun RecyclerView.setItems(items: List<Item>) {
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
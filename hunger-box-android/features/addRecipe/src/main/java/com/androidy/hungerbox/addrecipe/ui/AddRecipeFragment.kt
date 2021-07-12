package com.androidy.hungerbox.addrecipe.ui


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androidy.hungerbox.addRecipe.R
import com.androidy.hungerbox.addRecipe.databinding.FragmentAddRecipeBinding
import com.androidy.hungerbox.addrecipe.di.DaggerAddRecipeComponent
import com.androidy.hungerbox.commons.base.BaseFragment
import com.androidy.hungerbox.addrecipe.ui.steps.RecipeDescriptionFragment
import com.androidy.hungerbox.addrecipe.ui.steps.addItem.AddItemFragment
import com.androidy.hungerbox.addrecipe.ui.steps.RecipeServingDetailsFragment
import com.androidy.hungerbox.commonUi.databinding.LayoutToolbarBinding
import com.androidy.hungerbox.commons.extensions.*
import com.androidy.hungerbox.coreComponent
import com.androidy.hungerbox.navigation.NavigationMainDirections
import com.androidy.hungerbox.utils.EventObserver
import com.google.android.gms.common.util.CollectionUtils.listOf
import kotlinx.android.synthetic.main.fragment_add_recipe.*


class AddRecipeFragment : BaseFragment<FragmentAddRecipeBinding, AddRecipeViewModel>
    (R.layout.fragment_add_recipe) {

    private val viewModel by viewModels<AddRecipeViewModel>()
    private lateinit var toolbarBinding: LayoutToolbarBinding

    private val mBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentItem = recipe_steps_vp.currentItem
                if (currentItem == 0)
                    findNavController().navigateUp()
                else
                    recipe_steps_vp.setCurrentItem(currentItem - 1, true)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Custom back press handling for this fragment.
        getAppCompatActivity().onBackPressedDispatcher
            .addCallback(this, mBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = LayoutToolbarBinding.inflate(layoutInflater)
        setUpToolbar(toolbarBinding.toolbar)
        setHasOptionsMenu(true)
        setRecipeStepsAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpNavigation()
        setUpSnackBar()
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBackPressedCallback.remove()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                val currentItem = recipe_steps_vp.currentItem
                if (currentItem != 0) {
                    recipe_steps_vp.setCurrentItem(currentItem - 1, true)
                    true
                } else
                    super.onOptionsItemSelected(item)
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun setUpNavigation() {
        viewModel.recipesPageEvent.observe(viewLifecycleOwner, EventObserver {
//            findNavController().navigate(R.id.action_navigation_add_recipe_fragment_to_navigation_feeds_fragment)
        })

        viewModel.nextStepEvent.observe(viewLifecycleOwner, EventObserver {
            val currentItem = recipe_steps_vp.currentItem
            recipe_steps_vp.setCurrentItem(currentItem + 1, true)
        })
    }

    private fun setObservers() {
        viewModel.currentStep.observe(viewLifecycleOwner, Observer {
            if (it == RecipeSteps.DIRECTIONS)
                next_step_fab.setImageDrawable(context.getCompatDrawable(R.drawable.ic_done))
            else
                next_step_fab.setImageDrawable(context.getCompatDrawable(R.drawable.ic_next))
        })

        viewModel.isDataLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                view.showLoader("Please Wait...")
            else
                view.hideLoader()
        })
    }

    private fun setRecipeStepsAdapter() {
        recipe_steps_vp.adapter = AddRecipeStepsPagerAdapter(this, getFragments())
    }

    private fun setUpSnackBar() {
        view?.setUpSnackBar(viewLifecycleOwner, viewModel.snackBarTextEvent)
    }

    private fun getFragments() = listOf(
        RecipeDescriptionFragment(),
        RecipeServingDetailsFragment(),
        AddItemFragment.newInstance(AddItemFragment.INGREDIENT),
        AddItemFragment.newInstance(AddItemFragment.DIRECTION)
    )

    override fun initDi() {
        DaggerAddRecipeComponent
            .builder()
            .coreComponent(coreComponent())
            .addRecipeFragment(this)
            .build()
            .inject(this)
    }

    override fun initDataBinding() {
    }

}

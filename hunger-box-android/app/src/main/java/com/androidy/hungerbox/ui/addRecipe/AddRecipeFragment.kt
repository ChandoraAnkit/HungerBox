package com.androidy.hungerbox.ui.addRecipe


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseFragment
import com.androidy.hungerbox.databinding.FragmentAddRecipeBinding
import com.androidy.hungerbox.ui.addRecipe.steps.RecipeDescriptionFragment
import com.androidy.hungerbox.ui.addRecipe.steps.addItem.AddItemFragment
import com.androidy.hungerbox.ui.addRecipe.steps.RecipeServingDetailsFragment
import com.androidy.hungerbox.utils.EventObserver
import com.androidy.hungerbox.utils.ext.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject


class AddRecipeFragment : BaseFragment<FragmentAddRecipeBinding, AddRecipeViewModel>(),
        HasAndroidInjector {

    private val mViewModel by viewModels<AddRecipeViewModel> { viewModelFactory() }

    @Inject
    lateinit var mAndroidInjector: DispatchingAndroidInjector<Any>

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

    override fun getLayoutId() = R.layout.fragment_add_recipe

    override fun getBindingVariables() =
            mapOf(BR.viewModel to mViewModel)

    override fun androidInjector() = mAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Custom back press handling for this fragment.
        getAppCompatActivity().onBackPressedDispatcher
                .addCallback(this, mBackPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(toolbar)
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
        mViewModel.recipesPageEvent.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(R.id.action_navigation_add_recipe_fragment_to_navigation_feeds_fragment)
        })

        mViewModel.nextStepEvent.observe(viewLifecycleOwner, EventObserver {
            val currentItem = recipe_steps_vp.currentItem
            recipe_steps_vp.setCurrentItem(currentItem + 1, true)
        })
    }

    private fun setObservers() {
        mViewModel.currentStep.observe(viewLifecycleOwner, Observer{
            if (it == RecipeSteps.DIRECTIONS)
                next_step_fab.setImageDrawable(context.getCompatDrawable(R.drawable.ic_done))
            else
                next_step_fab.setImageDrawable(context.getCompatDrawable(R.drawable.ic_next))
        })

        mViewModel.isDataLoading.observe(viewLifecycleOwner, Observer {
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
        view?.setUpSnackBar(viewLifecycleOwner, mViewModel.snackBarTextEvent)
    }

    private fun getFragments() = listOf(RecipeDescriptionFragment(),
            RecipeServingDetailsFragment(),
            AddItemFragment.newInstance(AddItemFragment.INGREDIENT),
            AddItemFragment.newInstance(AddItemFragment.DIRECTION))

}

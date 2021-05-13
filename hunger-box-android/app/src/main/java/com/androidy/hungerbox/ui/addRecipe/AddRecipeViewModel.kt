package com.androidy.hungerbox.ui.addRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseViewModel
import com.androidy.hungerbox.data.Result
import com.androidy.hungerbox.data.addRecipe.Item
import com.androidy.hungerbox.data.addRecipe.RecipeModel
import com.androidy.hungerbox.data.addRecipe.source.AddRecipeRepository
import com.androidy.hungerbox.utils.Constants
import com.androidy.hungerbox.utils.Event
import com.androidy.hungerbox.utils.ext.removeIfPresent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class AddRecipeViewModel @Inject constructor(
        private val firebaseAuth: FirebaseAuth,
        private val repository: AddRecipeRepository)
    : BaseViewModel() {

    val time by lazy {
        ArrayList(Constants.TIME.keys)
    }

    val currentStep = MutableLiveData<RecipeSteps>()

    val recipeName = MutableLiveData<String>()
    val recipeDescription = MutableLiveData<String>()

    val foodCategory = MutableLiveData<Int>()
    val servingCounts = MutableLiveData<String>()
    val prepareTime = MutableLiveData<String>()
    val cookingTime = MutableLiveData<String>()

    val prepareTimeSpinnerCurrentItem = MutableLiveData<String>()
    val cookingTimeSpinnerCurrentItem = MutableLiveData<String>()

    val ingredient = MutableLiveData<String>()
    private val _ingredients = ArrayList<Item>()
    val ingredients = MutableLiveData<List<Item>>().apply { value = emptyList() }

    val direction = MutableLiveData<String>()
    private val _directions = ArrayList<Item>()
    val directions = MutableLiveData<List<Item>>().apply { value = emptyList() }

    private val _nextStepEvent = MutableLiveData<Event<Unit>>()
    val nextStepEvent: MutableLiveData<Event<Unit>> = _nextStepEvent

    private val _recipesPageEvent = MutableLiveData<Event<Unit>>()
    val recipesPageEvent: MutableLiveData<Event<Unit>> = _recipesPageEvent

    fun nextStep() {
        if (currentStep.value == RecipeSteps.DIRECTIONS)
            uploadRecipe()
        else
            _nextStepEvent.value = Event(Unit)
    }

    fun addItem() {
        if (currentStep.value == RecipeSteps.INGREDIENTS)
            addIngredient()
        else
            addDirection()
    }

    fun deleteItem(id: Long) {
        if (currentStep.value == RecipeSteps.INGREDIENTS) {
            _ingredients.removeIfPresent { it.id == id }
            ingredients.value = _ingredients
        } else {
            _directions.removeIfPresent { it.id == id }
            directions.value = _directions
        }
    }

    fun updateItem(pos: Int, item: String) {
        if (currentStep.value == RecipeSteps.INGREDIENTS) {
            _ingredients[pos].value = item
            ingredients.value = _ingredients
        } else {
            _directions[pos].value = item
            directions.value = _directions
        }
    }

    fun setEditable(pos: Int, value: Boolean) {
        if (currentStep.value == RecipeSteps.INGREDIENTS) {
            _ingredients[pos].editable = value
            ingredients.value = _ingredients
        } else {
            _directions[pos].editable = value
            directions.value = _directions
        }
    }

    private fun uploadRecipe() {
        _isDataLoading.value = true

        viewModelScope.launch {

            val foodType = when (foodCategory.value) {
                R.id.veg_rb -> Constants.VEG
                R.id.non_veg_rb -> Constants.NON_VEG
                else -> ""
            }

            val recipe = RecipeModel(firebaseAuth.currentUser?.uid, recipeName.value,
                    recipeDescription.value, ingredients.value,
                    directions.value, foodType,
                    "${prepareTime.value} ${Constants.TIME[prepareTimeSpinnerCurrentItem.value]}",
                    "${cookingTime.value} ${Constants.TIME[cookingTimeSpinnerCurrentItem.value]}",
                    prepareTime.value + cookingTime.value
            )

            when (val result = repository.uploadRecipe(recipe)) {
                is Result.Success -> {
                    Timber.d("Upload Recipe => Success")
                    _recipesPageEvent.value = Event(Unit)
                }

                is Result.Error -> {
                    Timber.d("Upload Recipe => ${result.exception.message}")
                }
            }
            _isDataLoading.value = false
        }
    }

    private fun addIngredient() {
        ingredient.value?.let {
            _ingredients.add(Item(value = it))
            ingredients.value = _ingredients
        }
    }

    private fun addDirection() {
        direction.value?.let {
            _directions.add(Item(value = it))
            directions.value = _directions
        }
    }

}

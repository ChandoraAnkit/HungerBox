package com.androidy.hungerbox.ui.addRecipe.steps.addItem

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.androidy.hungerbox.BR
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseListAdapter
import com.androidy.hungerbox.base.BaseViewHolder
import com.androidy.hungerbox.data.addRecipe.Item
import com.androidy.hungerbox.databinding.ItemAddBinding
import com.androidy.hungerbox.ui.addRecipe.AddRecipeViewModel
import com.androidy.hungerbox.utils.ext.bindingInflate
import com.androidy.hungerbox.utils.ext.gone
import com.androidy.hungerbox.utils.ext.show


/**
 * Created by Ankit
 */


class AddItemsAdapter(private val mViewModel: AddRecipeViewModel) :
        BaseListAdapter<Item, AddItemsAdapter.AddItemViewHolder>(AddItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val itemBinding = parent.bindingInflate<ItemAddBinding>(R.layout.item_add)
        return AddItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(mapOf(
                BR.item to getItem(position),
                BR.viewModel to mViewModel))
    }

    inner class AddItemViewHolder(private val itemBinding: ItemAddBinding)
        : BaseViewHolder(itemBinding), View.OnClickListener {

        init {
            itemBinding.doneIv.setOnClickListener(this)
            itemBinding.editIv.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.edit_iv -> editItem(adapterPosition, itemBinding)
                R.id.done_iv -> updateItem(adapterPosition, itemBinding)
            }
        }
    }

    private fun editItem(pos: Int, binding: ItemAddBinding) {
        binding.apply {
            itemEt.isEnabled = true
            itemEt.requestFocus()
            doneIv.show()
            editIv.gone()
            mViewModel.setEditable(pos, true)
        }
    }

    private fun updateItem(pos: Int, binding: ItemAddBinding) {
        binding.apply {
            itemEt.isEnabled = false
            editIv.show()
            doneIv.gone()
            mViewModel.setEditable(pos, false)
            mViewModel.updateItem(pos, itemEt.text.toString())
        }
    }

    class AddItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }


}
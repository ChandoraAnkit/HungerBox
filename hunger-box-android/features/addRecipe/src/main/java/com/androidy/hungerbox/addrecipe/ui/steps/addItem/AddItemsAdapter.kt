package com.androidy.hungerbox.addrecipe.ui.steps.addItem

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.androidy.hungerbox.addRecipe.R
import com.androidy.hungerbox.addRecipe.databinding.ItemAddBinding
import com.androidy.hungerbox.addrecipe.data.Item
import com.androidy.hungerbox.addrecipe.ui.AddRecipeViewModel
import com.androidy.hungerbox.commons.base.BaseListAdapter
import com.androidy.hungerbox.commons.base.BaseViewHolder
import com.androidy.hungerbox.commons.extensions.bindingInflate
import com.androidy.hungerbox.commons.extensions.gone
import com.androidy.hungerbox.commons.extensions.show


/**
 * Created by Ankit
 */


class AddItemsAdapter(private val mViewModel: AddRecipeViewModel) :
        BaseListAdapter<Item, AddItemsAdapter.AddItemViewHolder>(
            AddItemDiffCallback()
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val itemBinding = parent.bindingInflate<ItemAddBinding>(R.layout.item_add)
        return AddItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class AddItemViewHolder(private val itemBinding: ItemAddBinding)
        : BaseViewHolder(itemBinding), View.OnClickListener {

        init {
            itemBinding.doneIv.setOnClickListener(this)
            itemBinding.editIv.setOnClickListener(this)
        }

        override fun bind(pos: Int) {
            itemBinding.item = getItem(pos)
            itemBinding.viewModel = mViewModel

            super.bind(pos)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.edit_iv -> editItem(adapterPosition, itemBinding)
                R.id.done_iv -> updateItem(adapterPosition, itemBinding)
            }
        }
    }

    private fun editItem(pos: Int, binding: ItemAddBinding) {
        binding.run {
            itemEt.isEnabled = true
            itemEt.requestFocus()
            doneIv.show()
            editIv.gone()
            mViewModel.setEditable(pos, true)
        }
    }

    private fun updateItem(pos: Int, binding: ItemAddBinding) {
        binding.run {
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
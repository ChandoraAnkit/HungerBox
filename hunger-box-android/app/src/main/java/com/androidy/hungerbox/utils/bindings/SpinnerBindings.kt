package com.androidy.hungerbox.utils.bindings

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.androidy.hungerbox.utils.ext.*


@BindingAdapter("app:entries")
fun Spinner.setEntries( entries: List<Any>?){
   setSpinnerEntries(entries)
}

@BindingAdapter("app:onItemSelected")
fun Spinner.onItemSelectedListener(itemSelectedListener: ItemSelectedListener?){
    setSpinnerItemSelectedListener(itemSelectedListener)
}

@BindingAdapter("app:selectedValue")
fun Spinner.setSelectedValue(selectedValue: Any?) {
    setSpinnerValue(selectedValue)
}

@BindingAdapter("selectedValueAttrChanged")
fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
    setSpinnerInverseBindingListener(inverseBindingListener)
}

@InverseBindingAdapter(attribute = "app:selectedValue", event = "selectedValueAttrChanged")
fun Spinner.getSelectedValue(): Any? {
    return getSpinnerValue()
}
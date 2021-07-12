package com.androidy.hungerbox.commons.extensions

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingListener


fun Spinner.setSpinnerEntries(entries: List<Any>?) {
    entries?.let { data ->
        ArrayAdapter(context, R.layout.simple_spinner_item, data).let {
            it.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            adapter = it
        }
    }
}


fun Spinner.setSpinnerItemSelectedListener(listener: ItemSelectedListener?) {
    listener?.let {

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                listener.onItemSelected(getItemAtPosition(pos))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}

fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {

    listener?.let {
        onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (tag != position) {
                    listener.onChange()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}

fun Spinner.getSpinnerValue(): Any? {
    return selectedItem
}

fun Spinner.setSpinnerValue(value: Any?) {

    adapter?.let {
        val pos = (adapter as ArrayAdapter<Any>).getPosition(value)
        setSelection(pos)
        tag = pos
    }
}


interface ItemSelectedListener {
    fun onItemSelected(item: Any)
}
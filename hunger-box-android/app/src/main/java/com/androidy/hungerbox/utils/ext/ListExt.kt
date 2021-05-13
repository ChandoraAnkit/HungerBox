package com.androidy.hungerbox.utils.ext


/**
 * Defines to remove the all elements which satisfy the
 * predicate function which passes as parameter.
 */

fun <T>MutableList<out T>.removeIfPresent(predicate: (T) -> Boolean){
    val iterate  = iterator()
    while (iterate.hasNext()){
        if (predicate(iterate.next()))
            iterate.remove()
    }
}

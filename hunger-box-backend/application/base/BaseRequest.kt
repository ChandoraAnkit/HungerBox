package com.androidy.hungerbox.base

interface BaseRequest {
    fun validate(): Pair<Boolean, String>
}
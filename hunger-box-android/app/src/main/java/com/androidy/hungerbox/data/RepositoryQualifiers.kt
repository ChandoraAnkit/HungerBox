package com.androidy.hungerbox.data

import javax.inject.Qualifier


@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class Local

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class Remote


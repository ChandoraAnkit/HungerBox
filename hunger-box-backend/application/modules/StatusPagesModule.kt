package com.androidy.hungerbox.modules


import com.androidy.hungerbox.statusPages.generalStatusPages
import com.androidy.hungerbox.statusPages.recipeStatusPages
import com.androidy.hungerbox.statusPages.userStatusPages
import io.ktor.features.*


fun StatusPages.Configuration.statusPagesModule() {
    generalStatusPages()
    userStatusPages()
    recipeStatusPages()
}
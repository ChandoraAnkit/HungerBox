package com.androidy.hungerbox.utils

import com.androidy.hungerbox.base.BaseRequest
import com.androidy.hungerbox.statusPages.MissingArgumentException
import io.ktor.application.*
import io.ktor.request.*


suspend inline fun <reified T : BaseRequest> ApplicationCall.safeReceive(error: String? = null): T {
    val requestBody = kotlin.runCatching {
        receive<T>()
    }.getOrElse {
        throw MissingArgumentException(error ?: it.localizedMessage)
    }
    val validation = requestBody.validate()
    if (!validation.first)
        throw MissingArgumentException(validation.second)

    return requestBody
}

fun ApplicationCall.safeParameter(param: String, error: String? = null): String {
    val requestParam = parameters[param]
    if (requestParam.isNullOrEmpty())
        throw MissingArgumentException(error ?: FailureMessages.ARGUMENT_MISSING)
    return requestParam
}
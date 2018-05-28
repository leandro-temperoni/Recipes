package com.temperoni.recipes.domain.event

/**
 * @author Leandro Temperoni
 */
open class ResponseEvent<T> {

    var isSuccess: Boolean = false

    var payload: T? = null
        set(payload) {
            field = payload
            isSuccess = true
        }

    var errorMessage: String? = null
        set(errorMessage) {
            field = errorMessage
            isSuccess = false
        }
}

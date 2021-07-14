package com.mirza.adil.utils


/*
* The class Resource
*
* @author  Mirza Adil
* @email mirza.adil@gmail.com
* @version 1.0
* @since 14 Jul 2021
*
* This class is generic data class use to handle response, message and status in success, loading or error case.
*/
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}
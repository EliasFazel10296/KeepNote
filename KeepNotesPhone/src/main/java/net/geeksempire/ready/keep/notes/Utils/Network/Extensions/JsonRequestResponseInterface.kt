/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 7/10/20 12:53 PM
 * Last modified 7/10/20 12:45 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.ready.keep.notes.Utils.Network.Extensions

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

interface JsonRequestResponseInterface {
    fun jsonRequestResponseSuccessHandler(rawDataJsonArray: JSONArray) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, rawDataJsonArray.toString())
    }

    fun jsonRequestResponseSuccessHandler(rawDataJsonObject: JSONObject) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, rawDataJsonObject.toString())

    }

    fun jsonRequestResponseFailureHandler(jsonError: String?) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, jsonError.toString())

    }

    fun jsonRequestResponseFailureHandler(networkError: Int?) {
        Log.d(this@JsonRequestResponseInterface.javaClass.simpleName, networkError.toString())

    }
}
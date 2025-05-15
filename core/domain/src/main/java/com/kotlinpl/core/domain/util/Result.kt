package com.kotlinpl.core.domain.util

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.jvm.java
import kotlin.reflect.KClass

/**
 * Commented Annotations if kotlinx serialization plugin where used
 */
@Serializable(with = ResultSuccessSerializer::class)
sealed class Result<out D, out E: Error> {
    @Serializable
    data class Success<out D>(val data: @Serializable D) : Result<D, Nothing>()
    @Serializable
    data class Error<out E: com.kotlinpl.core.domain.util.Error>(val error: @Serializable E) :
        Result<Nothing, E>()
}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

/**
 * Type Adapter for [Gson]
 */
//class ResultTypeAdapter<D, E : Error> (
//    private val dataClass: Class<D>,
//    private val errorClass: Class<E>
//) : TypeAdapter<Result<D, E>>() {
//    override fun write(
//        out: JsonWriter?,
//        value: Result<D, E>?
//    ) {
//        when (value) {
//            is Result.Error<E> -> {
//                out!!.name("type").value("error")
//                out.name("error")
//                Gson().toJson(value.error, errorClass, out)
//                out.endObject()
//            }
//            is Result.Success<D> -> {
//                out!!.name("type").value("success")
//                out.name("data")
//                Gson().toJson(value.data, dataClass, out)
//                out.endObject()
//            }
//            null -> {
//                out?.nullValue()
//                return
//            }
//        }
//
//        out.endObject()
//    }
//
//    override fun read(`in`: JsonReader?): Result<D, E>? {
//        `in`?.beginObject()
//        var type: String? = null
//        var data: D? = null
//        var error: E? = null
//
//        while(`in`?.hasNext() == true) {
//            when(`in`.nextName()) {
//                "type"  -> type = `in`.nextString()
//                "data"  -> data = Gson().fromJson(`in`, dataClass)
//                "error" -> error = Gson().fromJson(`in`, errorClass)
//            }
//        }
//
//        `in`?.endObject()
//
//        return when(type) {
//            "success" -> Result.Success(data!!)
//            "error"   -> Result.Error(error!!)
//            else      -> null
//        }
//    }
//
//}

/**
 * Custom serializer for Result class using kotlinx.serialization library
 */
@Suppress("UNCHECKED_CAST")
private class ResultSuccessSerializer<T, E: Error>(
    private val successDataSerializer: KSerializer<T>,
    private val errorDataSerializer: KSerializer<E>
) : JsonContentPolymorphicSerializer<Result<T, E>> (Result::class as KClass<Result<T, E>>) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Result<T, E>> = when {
        Result.Success<T>::data.name in element.jsonObject -> Result.Success.serializer(successDataSerializer)
        Result.Error<E>::error.name in element.jsonObject -> Result.Error.serializer(errorDataSerializer)
        else -> throw SerializationException("Cannot determine the type to deserialize from JSON element $element")
    }
}
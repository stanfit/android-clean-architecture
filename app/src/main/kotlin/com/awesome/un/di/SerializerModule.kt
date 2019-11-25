package com.awesome.un.di

import com.awesome.un.domain.model.Post
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

/**
 * Dependency Module of Serializer.
 */
object SerializerModule {

    /**
     * Get Json Serializer.
     *
     * @return JsonSerializer
     */
    @UnstableDefault
    fun getJsonSerializer(): JsonSerializer {
        return KotlinxSerializer(Json.nonstrict).apply {
            register(Post.serializer().list)
        }
    }
}

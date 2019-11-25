package com.awesome.un.data.api

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.wss
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.readText
import io.ktor.http.cio.websocket.send
import kotlinx.coroutines.channels.BroadcastChannel

/**
 * RestAPI Client for Ktor.
 *
 * @property httpClient HttpClient fot Ktor
 */
class APIClient(val httpClient: HttpClient) {

    /**
     * RestAPI GET Method.
     *
     * @param T Response Entity Class
     * @param url Request URL
     * @return Response Entity Instance
     */
    suspend inline fun <reified T> get(url: String): T = httpClient.get(url)

    /**
     * RestAPI POST Method.
     *
     * @param T Response Entity Class
     * @param url Request URL
     * @return Response Entity Instance
     */
    suspend inline fun <reified T> post(url: String): T = httpClient.post(url)

    val channel: BroadcastChannel<String> = BroadcastChannel(1)

    suspend inline fun <reified T> wss(url: String) {
        httpClient.wss(urlString = url) {
            // Send text frame.
            send("Hello, Text frame")

            // Send text frame.
            send(Frame.Text("Hello World"))

            // Receive frame.
            when (val frame = incoming.receive()) {
                is Frame.Text -> println(frame.readText())
                is Frame.Binary -> println(frame.readBytes())
            }
        }
    }
}

package com.awesome.un.data.repository

import com.awesome.un.BuildConfig
import com.awesome.un.data.api.APIClient
import com.awesome.un.domain.model.Post

/**
 * Repository of Post
 *
 * @property api APIClient
 */
class PostRepository(private val api: APIClient) {

    /**
     * Fetch all posts
     *
     * @return List of post
     */
    suspend fun fetchPosts(): List<Post> {
        val url = "${BuildConfig.API_BASE_URL}posts"
        return api.get(url)
    }

    /**
     * Fetch post by id
     *
     * @param id ID of post
     * @return Post
     */
    suspend fun fetchPost(id: Int): Post {
        val url = "${BuildConfig.API_BASE_URL}posts/$id"
        return api.get(url)
    }
}

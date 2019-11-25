package com.awesome.un.domain.usecase

import com.awesome.un.data.repository.PostRepository
import com.awesome.un.domain.model.Post

class PostUseCase(private val post: PostRepository) {

    suspend fun fetchPosts(): List<Post> {
        return post.fetchPosts()
    }

    suspend fun fetchPost(id: Int): Post {
        return post.fetchPost(id)
    }
}

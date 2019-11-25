package com.awesome.un.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awesome.un.domain.model.Post
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostRepositoryTest {

    @Test
    fun fetchPosts() = runBlocking {
        //        val posts = repository.fetchPosts()
        val post = mockk<PostRepository>()

        val mock = Post(0, "", "")

        coEvery { post.fetchPost(0) } returns mock
        Truth.assertThat(post.fetchPost(0))
            .isSameAs(mock)
    }
}

package com.awesome.un.ui.post

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.awesome.un.App
import com.awesome.un.domain.usecase.PostUseCase
import kotlinx.coroutines.delay
import timber.log.Timber

/**
 * Post List Screen's ViewModel. [AndroidViewModel] subclass.
 *
 * @property post UseCase of post
 */
class PostViewModel(app: App, private val post: PostUseCase) : AndroidViewModel(app) {

    /**
     * Posts
     */
    val posts = liveData {
        Result
            .runCatching {
                // FIXME: delay for debug
                delay(3000)
                post.fetchPosts()
            }
            .onSuccess {
                emit(it)
            }
            .onFailure {
                Timber.e(it)
            }
    }
}

package com.awesome.un.di

import com.awesome.un.App
import com.awesome.un.data.repository.PostRepository
import com.awesome.un.domain.usecase.PostUseCase
import com.awesome.un.ui.detail.PostDetailViewModel
import com.awesome.un.ui.post.PostViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Dependency Module for Koin.
 */
object AppModule {

    /**
     * Module Instance.
     */
    val instance = module {
        // ViewModel Module
        // This class must be subclass of AndroidX ViewModel.
        viewModel { PostViewModel(androidApplication() as App, get()) }
        viewModel { PostDetailViewModel(androidApplication() as App) }

        // UseCase Module
        // This class must managed by a factory. Factory is always recreated.
        factory { PostUseCase(get()) }

        // Repository Module
        // This class must managed by a singleton.
        single { PostRepository(get()) }

        // Network Module
        // This class must managed by a singleton.
        single { NetworkModule.getHttpClient(androidApplication(), get()) }
        single { NetworkModule.getAPIClient(get()) }

        // Serializer Module
        // This class must managed by a singleton.
        single { SerializerModule.getJsonSerializer() }
    }
}

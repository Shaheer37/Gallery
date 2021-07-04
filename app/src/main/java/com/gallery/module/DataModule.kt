package com.gallery.module

import com.gallery.BuildConfig
import com.gallery.data.DataRepository
import com.gallery.data.remote.DataFactory
import com.gallery.data.remote.GalleryApi
import com.gallery.domain.GalleryRepository
import com.gallery.data.GalleryRepositoryImpl
import com.gallery.data.remote.RemoteDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    companion object{
        @Provides
        @JvmStatic
        fun providesJson(): Json {
            return Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        @Provides
        @JvmStatic
        @Singleton
        fun providesNewsService(json: Json): GalleryApi {
            return DataFactory.makeService(json = json, showLogs = BuildConfig.DEBUG)
        }

    }

    @Binds
    fun bindsGalleryRepository(galleryRepository: GalleryRepositoryImpl): GalleryRepository

    @Binds
    fun bindsDataRepository(dataRepository: RemoteDataRepository): DataRepository
}
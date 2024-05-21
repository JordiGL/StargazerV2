package com.golojodev.stargazer.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.golojodev.stargazer.data.ModelDatabase
import com.golojodev.stargazer.data.service.ServiceAPI
import com.golojodev.stargazer.data.workers.ModelsSyncWorker
import com.golojodev.stargazer.domain.repositories.LaunchRepository
import com.golojodev.stargazer.domain.repositories.LaunchRepositoryImpl
import com.golojodev.stargazer.domain.repositories.ModelRepository
import com.golojodev.stargazer.domain.repositories.ModelRepositoryImpl
import com.golojodev.stargazer.domain.repositories.ThemeRepository
import com.golojodev.stargazer.domain.repositories.ThemeRepositoryImpl
import com.golojodev.stargazer.domain.usecases.FetchModelsUseCase
import com.golojodev.stargazer.domain.usecases.GetFavoritesUseCase
import com.golojodev.stargazer.domain.usecases.GetModelsUseCase
import com.golojodev.stargazer.domain.usecases.GetThemeUseCase
import com.golojodev.stargazer.domain.usecases.SaveThemeUseCase
import com.golojodev.stargazer.domain.usecases.UpdateModelUseCase
import com.golojodev.stargazer.domain.usecases.UseCaseProvider
import com.golojodev.stargazer.domain.usecases.UseCaseProviderImpl
import com.golojodev.stargazer.presentation.viewmodels.MainViewModel
import com.golojodev.stargazer.presentation.viewmodels.ThemeViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit

// TODO: Canvia la URL
const val BASE_URL = "https://ll.thespacedevs.com/2.2.0/launch/"

private val json = Json {
    ignoreUnknownKeys = true
}

val appModules = module {
    single { MainViewModel(get()) }
    single { ThemeViewModel(get()) }
    single<ModelRepository> { ModelRepositoryImpl(get(), get(), get()) }
    single<ThemeRepository> { ThemeRepositoryImpl(get(), get()) }
    single<LaunchRepository> { LaunchRepositoryImpl(get(), get(), get()) }
    single {
        Retrofit.Builder().addConverterFactory(
            json.asConverterFactory(contentType = "application/json".toMediaType())
        )
            .baseUrl(BASE_URL)
            .build()
    }
    single { get<Retrofit>().create(ServiceAPI::class.java) }
    single { Dispatchers.IO }
    single {
        Room.databaseBuilder(
            androidContext(),
            ModelDatabase::class.java,
            "model-database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Insert initial row during database creation
                    db.execSQL("INSERT OR REPLACE INTO ThemeState (id, theme) VALUES (0,'DEFAULT')")
                }
            })
            .build()
    }
    single { get<ModelDatabase>().modelDao() }
    single { get<ModelDatabase>().themeStateDao() }
    single { get<ModelDatabase>().stargazerDao() }
    single { FetchModelsUseCase(get()) }
    single { GetModelsUseCase(get()) }
    single { GetThemeUseCase(get()) }
    single { SaveThemeUseCase(get()) }
    single { UpdateModelUseCase(get()) }
    single { GetFavoritesUseCase(get()) }
    single<UseCaseProvider>{ UseCaseProviderImpl(get() ,get() , get(), get(), get(), get()) }
    worker { ModelsSyncWorker(get(), get(), get()) }
}
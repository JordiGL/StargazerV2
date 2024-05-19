package com.golojodev.stargazer.data.service

import com.golojodev.stargazer.domain.models.Model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Determina les querys a fer a la API.
 *
 * @author GolojoDev
 */
interface ServiceAPI {
    @GET("service")
    suspend fun fetchData(
        @Query("query") tag: String
    ): Response<List<Model>>
}
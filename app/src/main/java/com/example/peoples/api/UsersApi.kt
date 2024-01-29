package com.example.peoples.api

import com.example.peoples.helpers.Constants
import com.example.peoples.models.UsersApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") userCount: Int,
        @Query("seed") seed: String = Constants.SEED,
        @Query("inc") inclusions: String =  "${Constants.NAME}," +
                                            "${Constants.EMAIL}," +
                                            "${Constants.PICTURE}," +
                                            "${Constants.LOCATION}," +
                                            "${Constants.DOB}"
    ): Response<UsersApiResponse>
}
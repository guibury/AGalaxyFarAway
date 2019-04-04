package com.guilhermebury.agalaxyfaraway.network

import com.guilhermebury.agalaxyfaraway.domain.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

interface RetrofitService {

    @GET("films/{title}/?format=json")
    fun filmCall(@Path("title") title: String): Call<Character>
}
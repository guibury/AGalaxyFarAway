package com.guilhermebury.agalaxyfaraway.model;

import com.guilhermebury.agalaxyfaraway.domain.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Guilherme Bury on 26/06/2017.
 */

public interface RetrofitService {

    //Call needs a response (Film)
    //@FormUrlEncoded
    @GET("films/{title}/?format=json")
    Call<Character> filmCall(@Path("title") String title);

    /*
        Call<SWApiService> filmCall(@Path("path") String path,
                                    @Field("title") String title,
                                    @Field("episode_id") String episodeId,
                                    @Field("opening_crawl") String openingCrawl,
                                    @Field("director") String director,
                                    @Field("producer") String producer,
                                    @Field("release_date") String releaseDate,
                                    @Field("characters") List<Character>characters,
                                    @Field("planets") List<Planet> planets,
                                    @Field("starships") List<Starship> starships,
                                    @Field("vehicles") List<Vehicle> vehicles,
                                    @Field("species") List<Specie> species
                                    );
     */
}

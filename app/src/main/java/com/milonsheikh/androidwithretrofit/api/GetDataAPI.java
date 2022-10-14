package com.milonsheikh.androidwithretrofit.api;

import com.milonsheikh.androidwithretrofit.model.Photo;
import com.milonsheikh.androidwithretrofit.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface GetDataAPI {

    @GET("photos")
    Call<List<Photo>> getAllPhotos();

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("users/{userId}/posts")
    Call<List<Post>> getPostsByPath(@Path("userId") int id);

    @GET("posts")
    Call<List<Post>> getPostsByQuery
            (@Query("userId") int[] id,
             @Query("_sort") String sort,
             @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPostsByQueryMap(@QueryMap Map<String, String> parameters);

    @GET
    Call<List<Post>> getPostsByUrl(@Url String url);
}

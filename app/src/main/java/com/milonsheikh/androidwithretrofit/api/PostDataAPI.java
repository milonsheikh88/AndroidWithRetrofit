package com.milonsheikh.androidwithretrofit.api;

import com.milonsheikh.androidwithretrofit.model.Post;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostDataAPI {

    @POST("posts")
    Call<Post> createPostByBody(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostByField(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostByFieldMap(@FieldMap Map<String, String> fields);
}

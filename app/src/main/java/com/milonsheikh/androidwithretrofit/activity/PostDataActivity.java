package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.milonsheikh.androidwithretrofit.R;
import com.milonsheikh.androidwithretrofit.api.PostDataAPI;
import com.milonsheikh.androidwithretrofit.api_client.APIClient;
import com.milonsheikh.androidwithretrofit.model.Post;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data);

        progressDialog = new ProgressDialog(PostDataActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        tvResult = findViewById(R.id.tv_post_data);

        /*Create handle for the RetrofitInstance interface*/
        PostDataAPI service = APIClient.getRetrofitInstance().create(PostDataAPI.class);

        /*By @Body Annotation*/
//        Post post = new Post(203, "New Title Here", "New body Text Here");
//        Call<Post> call = service.createPostByBody(post);

        /*By @Field Annotation*/
//        Call<Post> call = service.createPostByField(205, "New Title Here", "New body Text Here");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "255");
        fields.put("title", "New Title Here");
        fields.put("body", "New body Text Here");
        Call<Post> call = service.createPostByFieldMap(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    tvResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                tvResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                progressDialog.dismiss();
                tvResult.setText(t.getMessage());
            }
        });

    }
}
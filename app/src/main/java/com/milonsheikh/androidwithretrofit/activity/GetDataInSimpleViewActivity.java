package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.milonsheikh.androidwithretrofit.R;
import com.milonsheikh.androidwithretrofit.api.GetDataAPI;
import com.milonsheikh.androidwithretrofit.api_client.APIClient;
import com.milonsheikh.androidwithretrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataInSimpleViewActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_in_simple_view);

        progressDialog = new ProgressDialog(GetDataInSimpleViewActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        tvResult = findViewById(R.id.tv_get_data_simple);

        /*Create handle for the RetrofitInstance interface*/
        GetDataAPI service = APIClient.getRetrofitInstance().create(GetDataAPI.class);
        Call<List<Post>> call = service.getAllPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    tvResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressDialog.dismiss();
                tvResult.setText(t.getMessage());
            }
        });

    }
}
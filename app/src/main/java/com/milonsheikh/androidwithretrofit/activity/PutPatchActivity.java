package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.milonsheikh.androidwithretrofit.R;
import com.milonsheikh.androidwithretrofit.api.PutPatchDeleteAPI;
import com.milonsheikh.androidwithretrofit.api_client.APIClient;
import com.milonsheikh.androidwithretrofit.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutPatchActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_patch);

        String action = getIntent().getStringExtra("KEY");

        progressDialog = new ProgressDialog(PutPatchActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        tvResult = findViewById(R.id.tv_put_patch);

        /*Create handle for the RetrofitInstance interface*/
        PutPatchDeleteAPI service = APIClient.getRetrofitInstance().create(PutPatchDeleteAPI.class);
        Post post = new Post(12, null, "New Text");

        Call<Post> call = null;
        if (action.equals("put")){
         call = service.putPost(5, post);
        }
        if (action.equals("patch")){
            call = service.patchPost(5, post);
        }
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
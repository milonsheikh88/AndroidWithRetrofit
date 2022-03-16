package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.milonsheikh.androidwithretrofit.R;
import com.milonsheikh.androidwithretrofit.api.PutPatchDeleteAPI;
import com.milonsheikh.androidwithretrofit.api_client.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        progressDialog = new ProgressDialog(DeleteActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        tvResult = findViewById(R.id.tv_delete);

        /*Create handle for the RetrofitInstance interface*/
        PutPatchDeleteAPI service = APIClient.getRetrofitInstance().create(PutPatchDeleteAPI.class);

        Call<Void> call = service.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.dismiss();
                tvResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                tvResult.setText(t.getMessage());
            }
        });

    }
}
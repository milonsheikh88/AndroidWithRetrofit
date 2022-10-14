package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.milonsheikh.androidwithretrofit.R;
import com.milonsheikh.androidwithretrofit.adapter.PhotoAdapter;
import com.milonsheikh.androidwithretrofit.api.GetDataAPI;
import com.milonsheikh.androidwithretrofit.api_client.APIClient;
import com.milonsheikh.androidwithretrofit.model.Photo;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class GetDataAndShowRecyclerViewActivity extends AppCompatActivity {

    private PhotoAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_show_recycler_view);

        progressDialog = new ProgressDialog(GetDataAndShowRecyclerViewActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataAPI service = APIClient.getRetrofitInstance().create(GetDataAPI.class);
        Call<List<Photo>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<Photo>>() {

            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetDataAndShowRecyclerViewActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Photo> photoList) {
        recyclerView = findViewById(R.id.photo_recycler_view);
        adapter = new PhotoAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GetDataAndShowRecyclerViewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
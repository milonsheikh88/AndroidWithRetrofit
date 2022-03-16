package com.milonsheikh.androidwithretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.milonsheikh.androidwithretrofit.R;

public class MainActivity extends AppCompatActivity {
    private Button btnGetSim, btnGetRe, btnMani,btnPost, btnPut, btnPatch,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetSim = (Button) findViewById(R.id.button_get_simple);
        btnGetRe = (Button) findViewById(R.id.button_get_re);
        btnMani = (Button) findViewById(R.id.button_manipulation);
        btnPost = (Button) findViewById(R.id.button_post);
        btnPut = (Button) findViewById(R.id.button_put);
        btnPatch = (Button) findViewById(R.id.button_patch);
        btnDelete = (Button) findViewById(R.id.button_delete);

        btnGetSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetDataInSimpleViewActivity.class);
                startActivity(intent);
            }
        });

        btnGetRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetDataAndShowRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        btnMani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManipulationActivity.class);
                startActivity(intent);
            }
        });


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostDataActivity.class);
                startActivity(intent);
            }
        });

        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PutPatchActivity.class);
                intent.putExtra("KEY", "put");
                startActivity(intent);
            }
        });

        btnPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PutPatchActivity.class);
                intent.putExtra("KEY", "patch");
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

    }
}
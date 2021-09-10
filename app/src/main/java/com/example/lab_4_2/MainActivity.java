package com.example.lab_4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView ivCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle);
        ivCover = findViewById(R.id.ivCover);
        getData();
    }

    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManagement.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManagement service =  retrofit.create(APIManagement.class);
        service.getArticleData().enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (response.body() == null){
                    return;
                }
                Article model = response.body();
                tvTitle.setText(model.getTitle());
                Glide.with(MainActivity.this).load(model.getImage()).into(ivCover);
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Log.d("MainActivity","onFailure" + t);
            }
        });
    }
}
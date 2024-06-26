package com.example.myuserapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView firstName, lastName, age, email, city, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);

        fetchRandomUser();
    }

    private void fetchRandomUser() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RandomUserResponse> call = apiService.getRandomUser();

        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getResults().get(0);
                    firstName.setText(user.getName().getFirst());
                    lastName.setText(user.getName().getLast());
                    age.setText(String.valueOf(user.getDob().getAge()));
                    email.setText(user.getEmail());
                    city.setText(user.getLocation().getCity());
                    country.setText(user.getLocation().getCountry());
                    Glide.with(MainActivity.this).load(user.getPicture().getLarge()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                firstName.setText("error");
                lastName.setText("error");
                age.setText("error");
                email.setText("error");
                city.setText("error");
                country.setText("error");
                Toast.makeText(MainActivity.this, "Failed to fetch user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

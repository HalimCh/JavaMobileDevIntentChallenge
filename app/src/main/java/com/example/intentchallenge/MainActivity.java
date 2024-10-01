package com.example.intentchallenge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String moodv;
    String locationv;
    String telv;
    String namev;
    String webv;
    Button createContactButton;
    ImageView tel;
    ImageView loc;
    ImageView webb;
    ImageView mood;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        createContactButton = findViewById(R.id.createContactButton);
        tel = findViewById(R.id.tel);
        webb = findViewById(R.id.webb);
        loc = findViewById(R.id.loc);
        mood = findViewById(R.id.mood); // Add this to initialize mood ImageView

        // Hide elements initially
        mood.setVisibility(View.GONE);
        tel.setVisibility(View.GONE);
        webb.setVisibility(View.GONE);
        loc.setVisibility(View.GONE);

        // Register ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // Get data from DetailsActivity
                            telv = data.getStringExtra("tel");
                            namev = data.getStringExtra("name");
                            webv = data.getStringExtra("web");
                            locationv = data.getStringExtra("location");
                            moodv = data.getStringExtra("mood");

                            // Show the elements after receiving data
                            mood.setVisibility(View.VISIBLE);
                            tel.setVisibility(View.VISIBLE);
                            webb.setVisibility(View.VISIBLE);
                            loc.setVisibility(View.VISIBLE);

                            // Set onClick listeners for actions
                            tel.setOnClickListener(v -> {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telv));
                                startActivity(intent);
                            });

                            loc.setOnClickListener(v -> {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + locationv));
                                startActivity(intent);
                            });

                            webb.setOnClickListener(v -> {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + webv));
                                startActivity(intent);
                            });

                            // Use .equals() to compare the mood string
                            if (moodv.equals("good")) {
                                mood.setImageResource(R.drawable.baseline_mood_24);
                            } else if (moodv.equals("bad")) {
                                mood.setImageResource(R.drawable.baseline_mood_bad_24);
                            } else if (moodv.equals("ok")) {
                                mood.setImageResource(R.drawable.baseline_sentiment_satisfied_24);
                            }

                        } else {
                            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Set onClickListener for the button to launch DetailsActivity
        createContactButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.example.intentchallenge.DetailsActivity.class);
            activityResultLauncher.launch(intent);
        });
    }
}

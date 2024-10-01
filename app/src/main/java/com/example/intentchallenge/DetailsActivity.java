package com.example.intentchallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText tel;
    EditText web;
    EditText location;
    ImageView bad;
    ImageView ok;
    ImageView good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Link EditText and ImageView elements with layout IDs
        name = findViewById(R.id.name); // Ensure your XML layout has an EditText with id name
        tel = findViewById(R.id.number); // Ensure your XML layout has an EditText with id tel
        web = findViewById(R.id.web); // Ensure your XML layout has an EditText with id web
        location = findViewById(R.id.location); // Ensure your XML layout has an EditText with id location

        bad = findViewById(R.id.bad);
        ok = findViewById(R.id.ok);
        good = findViewById(R.id.good);

        // Set click listeners on the mood buttons
        bad.setOnClickListener(this);
        ok.setOnClickListener(this);
        good.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Retrieve text from input fields
        String namev = name.getText().toString();
        String telv = tel.getText().toString();
        String webv = web.getText().toString();
        String locationv = location.getText().toString();

        // Check if any of the fields are empty
        if (telv.isEmpty() || locationv.isEmpty() || webv.isEmpty() || namev.isEmpty()) {
            Toast.makeText(this, "Complete the fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create an intent to send data back to MainActivity
            Intent intent = new Intent();
            intent.putExtra("tel", telv);
            intent.putExtra("name", namev);
            intent.putExtra("location", locationv);
            intent.putExtra("web", webv);

            // Check which button was clicked and assign the mood
            if (v.getId() == R.id.bad) {
                intent.putExtra("mood", "bad");
            } else if (v.getId() == R.id.ok) {
                intent.putExtra("mood", "ok");
            } else if (v.getId() == R.id.good) {
                intent.putExtra("mood", "good");
            }

            // Send the result back to MainActivity and close DetailsActivity
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}

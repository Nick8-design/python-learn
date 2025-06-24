package com.nickdieda.pythonlearn.common;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RateUs extends AppCompatActivity {
    private static final String APP_PACKAGE_NAME = "com.nickdieda.pythonlearn";
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_us);

        Button rateButton = findViewById(R.id.rateButton);
        rateButton.setOnClickListener(v -> openPlayStore());
        ret=findViewById(R.id.returnback);


        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac=new Intent(getApplicationContext(), MainActivity.class);

                startActivity(bac);
            }
        });


    }

    private void openPlayStore() {
        Uri uri = Uri.parse("market://details?id=" + APP_PACKAGE_NAME);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

        // If the Play Store is not found, open in browser
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + APP_PACKAGE_NAME)));
        }
    }
}




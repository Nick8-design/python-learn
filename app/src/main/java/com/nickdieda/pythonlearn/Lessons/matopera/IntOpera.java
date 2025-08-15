package com.nickdieda.pythonlearn.Lessons.matopera;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nickdieda.pythonlearn.R;

import com.nickdieda.pythonlearn.common.AdaptiveBannerLoader;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class IntOpera extends AppCompatActivity {

    private TextView title,qz;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_int_opera);

        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);


        ret = findViewById(R.id.returnback);
        FrameLayout adContainer = findViewById(R.id.ad_view_container);

        AdaptiveBannerLoader.loadAd(
                this,
                adContainer
        );

        String tt = "Introduction To Mathematics Operators";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 16);

                startActivity(quiz);
            }
        });



        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac=new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(bac);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        AdaptiveBannerLoader.onResume();
    }

    @Override
    protected void onPause() {
        AdaptiveBannerLoader.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        AdaptiveBannerLoader.onDestroy();
        super.onDestroy();
    }
}
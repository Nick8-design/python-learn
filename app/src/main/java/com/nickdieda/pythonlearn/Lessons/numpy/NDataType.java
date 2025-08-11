package com.nickdieda.pythonlearn.Lessons.numpy;

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
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class NDataType extends AppCompatActivity {
    private CodeEditor pd0,pd1, pd2,pd3,pd4,pd5,pd7,pd6,pd8,pd9,pd10;
    private TextView title,qz,pd11,pd22,pd33,pd44,pd55,pd66,pd77,pd88,pd99,pd1010;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ndata_type);

        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);


        ret = findViewById(R.id.returnback);

        // pd0 = findViewById(R.id.pd0);

        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);


        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);


         FrameLayout adContainer=findViewById(R.id.ad_view_container);

        AdaptiveBannerLoader.loadAd(
                this,
                adContainer
        );

        String tt = "NumPy Data Types";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 58);

                startActivity(quiz);
            }
        });


        CodeLang.pyLangstatic(getApplicationContext(),pd1);






        pd11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd1.getText().toString());
                startActivity(exe1);
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
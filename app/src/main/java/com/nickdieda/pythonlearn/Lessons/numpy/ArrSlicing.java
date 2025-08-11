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


import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.AdHelper;
import com.nickdieda.pythonlearn.common.AdaptiveBannerLoader;
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class ArrSlicing extends AppCompatActivity {
    private CodeEditor pd0,pd1, pd2,pd3,pd4,pd5,pd7,pd6,pd8,pd9,pd10;
    private TextView title,qz,pd11,pd22,pd33,pd44,pd55,pd66,pd77,pd88,pd99,pd1010;
    private ImageView ret;

    private FrameLayout adContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_arr_slicing);



//        AdHelper.initializeAds(this); // Call once in each activity
//
//        adContainerView = findViewById(R.id.ad_view_container);
//        adContainerView.setVisibility(View.GONE); // Initially hide
//        AdHelper.loadBannerAd(this, adContainerView);

        FrameLayout adContainer = findViewById(R.id.ad_view_container);

        AdaptiveBannerLoader.loadAd(
                this,
                adContainer
        );


        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);


        ret = findViewById(R.id.returnback);


        pd2 = findViewById(R.id.pd2);
        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);
        pd22 = findViewById(R.id.pd22);
        pd3 = findViewById(R.id.pd3);
        pd33 = findViewById(R.id.pd33);
        pd4= findViewById(R.id.pd4);
        pd44= findViewById(R.id.pd44);



        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);
        pd2.setEditable(false);
        pd2.setTypefaceText(Typeface.MONOSPACE);

        pd3.setEditable(false);
        pd3.setTypefaceText(Typeface.MONOSPACE);


        pd4.setEditable(false);
        pd4.setTypefaceText(Typeface.MONOSPACE);




        String tt = "NumPy Array Slicing";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 57);

                startActivity(quiz);
            }
        });

        CodeLang.pyLangstatic(getApplicationContext(),pd1);
        CodeLang.pyLangstatic(getApplicationContext(),pd2);
        CodeLang.pyLangstatic(getApplicationContext(),pd3);
        CodeLang.pyLangstatic(getApplicationContext(),pd4);





        pd11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd1.getText().toString());
                startActivity(exe1);
            }
        });

        pd22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd2.getText().toString());
                startActivity(exe1);
            }
        });
        pd33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd3.getText().toString());
                startActivity(exe1);
            }
        });
        pd44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd4.getText().toString());
                startActivity(exe1);
            }
        });





     /*         pd55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd5.getText().toString());
                startActivity(exe1);
            }
        });

        pd66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd6.getText().toString());
                startActivity(exe1);
            }
        });
 pd77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd7.getText().toString());
                startActivity(exe1);
            }
        });
        pd88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd8.getText().toString());
                startActivity(exe1);
            }
        });
        pd99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd9.getText().toString());
                startActivity(exe1);
            }
        });

*/


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
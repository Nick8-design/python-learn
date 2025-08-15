package com.nickdieda.pythonlearn.Lessons.basic;

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
import com.nickdieda.pythonlearn.common.AdaptiveBannerLoader;
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;

import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class PyState extends AppCompatActivity {

    private CodeEditor pd1,pd2,pd3;
    private TextView title,ex1,qz,pd11,pd22,pd33;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_py_state);


        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);
        ret = findViewById(R.id.returnback);


        ex1 = findViewById(R.id.tryit);
        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);

        pd2 = findViewById(R.id.pd2);
        pd22 = findViewById(R.id.pd22);
        pd3 = findViewById(R.id.pd3);
        pd33 = findViewById(R.id.pd33);



        FrameLayout adContainer = findViewById(R.id.ad_view_container);

        AdaptiveBannerLoader.loadAd(
                this,
                adContainer
        );

        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);

        pd2.setEditable(false);
        pd2.setTypefaceText(Typeface.MONOSPACE);

        pd3.setEditable(false);
        pd3.setTypefaceText(Typeface.MONOSPACE);




        String tt = "Python Statements";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 5);

                startActivity(quiz);
            }

        });

        CodeLang.pyLangstatic(getApplicationContext(),pd1);
        CodeLang.pyLangstatic(getApplicationContext(),pd2);
        CodeLang.pyLangstatic(getApplicationContext(),pd3);


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




        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac=new Intent(getApplicationContext(), LessonsActivity.class);

                startActivity(bac);
            }
        });


    }
}
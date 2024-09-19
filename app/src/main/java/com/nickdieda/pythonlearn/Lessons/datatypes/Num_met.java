package com.nickdieda.pythonlearn.Lessons.datatypes;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.CodeLang;

import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class Num_met extends AppCompatActivity {
    private CodeEditor pd2, pd1,pd3,pd4;
    private TextView title,qz,pd11,pd22,pd33,pd44;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_num_met);



        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);
        pd4= findViewById(R.id.pd4);
        pd44= findViewById(R.id.pd44);

        ret = findViewById(R.id.returnback);


        pd2 = findViewById(R.id.pd2);
        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);
        pd22 = findViewById(R.id.pd22);
        pd3 = findViewById(R.id.pd3);
        pd33 = findViewById(R.id.pd33);
        pd4= findViewById(R.id.pd4);
        pd44= findViewById(R.id.pd44);




        pd2.setEditable(false);
        pd2.setTypefaceText(Typeface.MONOSPACE);

        pd3.setEditable(false);
        pd3.setTypefaceText(Typeface.MONOSPACE);

        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);
        pd4.setEditable(false);
        pd4.setTypefaceText(Typeface.MONOSPACE);



        String tt = "Python Number Methods";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 11);

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


        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bac=new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(bac);
            }
        });


    }
}
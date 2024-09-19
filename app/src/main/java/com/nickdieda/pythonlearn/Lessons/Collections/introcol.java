package com.nickdieda.pythonlearn.Lessons.Collections;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class introcol extends AppCompatActivity {
    private CodeEditor  pd1;
    private TextView title,qz,pd11;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introcol);


        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);


        ret = findViewById(R.id.returnback);



        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);


        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);




        String tt = "Introduction To Python container and collections";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 23);

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

}
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
import com.nickdieda.pythonlearn.common.AdHelper;
import com.nickdieda.pythonlearn.common.AdaptiveBannerLoader;
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;

import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class InstallPy extends AppCompatActivity {
  private  TextView title,ex1,qz;
 private   ImageView ret;
    private CodeEditor out1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_install_py);



        FrameLayout adContainer = findViewById(R.id.ad_view_container);

        AdaptiveBannerLoader.loadAd(
                this,
                adContainer
        );





        title=findViewById(R.id.title);
        qz=findViewById(R.id.qiuz);
        ret=findViewById(R.id.returnback);
        ex1=findViewById(R.id.tryit);
        out1=findViewById(R.id.out1);
        out1.setEditable(false);
        out1.setTypefaceText(Typeface.MONOSPACE);
        String tt="Installing Python on PC";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz=new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt",tt);
                quiz.putExtra("id",2);

                startActivity(quiz);



            }
        });


        CodeLang.pyLangstatic(getApplicationContext(),out1);


        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",out1.getText().toString());
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
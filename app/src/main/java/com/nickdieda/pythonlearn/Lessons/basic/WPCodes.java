package com.nickdieda.pythonlearn.Lessons.basic;

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

public class WPCodes extends AppCompatActivity {
    private CodeEditor out1,wpcb,wpca;
    private TextView title,ex1,qz,wpcsave,tryyname ;
    private ImageView ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wpcodes);

        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);
        ret = findViewById(R.id.returnback);


        ex1 = findViewById(R.id.tryit);
        wpcsave = findViewById(R.id.wpcsave);
        tryyname = findViewById(R.id.tryyname);

        out1 = findViewById(R.id.out1);
        wpca = findViewById(R.id.wpca);
        wpcb = findViewById(R.id.wpcb);

        wpca.setEditable(false);
        wpca.setTypefaceText(Typeface.MONOSPACE);

        wpcb.setEditable(false);
        wpcb.setTypefaceText(Typeface.MONOSPACE);

        out1.setEditable(false);
        out1.setTypefaceText(Typeface.MONOSPACE);
        String tt = "Writing,Saving and Opening Python files";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 3);

                startActivity(quiz);
            }

        });


        CodeLang.pyLangstatic(getApplicationContext(),out1);
        CodeLang.pyLangstatic(getApplicationContext(),wpcb);
        CodeLang.pyLangstatic(getApplicationContext(),wpca);

        tryyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",wpcb.getText().toString());
                startActivity(exe1);
            }
        });


        wpcsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",out1.getText().toString());
                startActivity(exe1);
            }
        });
        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",wpca.getText().toString());
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
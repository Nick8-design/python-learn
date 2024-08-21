package com.nickdieda.pythonlearn.Lessons.Collections;

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
import com.nickdieda.pythonlearn.common.setpylan;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import io.github.rosemoe.sora.widget.CodeEditor;

public class Setsc extends AppCompatActivity {
    private CodeEditor pd2, pd1,pd3,pd4,pd5,pd7,pd6,pd8,pd9,pd10,pd12, pd111,pd13,pd14,pd15,pd17,pd16,pd18,pd19,pd20;
    private TextView title,qz,pd11,pd22,pd33,pd44,pd55,pd66,pd77,pd88,pd99,pd1010,pd1111,pd1212,pd1313,pd1414,pd1515,pd1616,pd1717,pd1818,pd1919,pd2020;
    private ImageView ret;
    public  setpylan pylang=new setpylan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setsc);


        title = findViewById(R.id.title);
        qz = findViewById(R.id.qiuz);


        ret = findViewById(R.id.returnback);



        pd1 = findViewById(R.id.pd1);
        pd11 = findViewById(R.id.pd11);
        pd2 = findViewById(R.id.pd2);
        pd22 = findViewById(R.id.pd22);
        pd3 = findViewById(R.id.pd3);
        pd33 = findViewById(R.id.pd33);
        pd4= findViewById(R.id.pd4);
        pd44= findViewById(R.id.pd44);



        pd5 = findViewById(R.id.pd5);
        pd55 = findViewById(R.id.pd55);
        pd6 = findViewById(R.id.pd6);
        pd66 = findViewById(R.id.pd66);
        pd7= findViewById(R.id.pd7);
        pd77= findViewById(R.id.pd77);
        pd8= findViewById(R.id.pd8);
        pd88= findViewById(R.id.pd88);
        pd9= findViewById(R.id.pd9);
        pd99= findViewById(R.id.pd99);
        pd10= findViewById(R.id.pd10);
        pd1010= findViewById(R.id.pd1010);

        pd111 = findViewById(R.id.pd111);
        pd1111 = findViewById(R.id.pd1111);
       /*  pd12 = findViewById(R.id.pd12);
        pd1212 = findViewById(R.id.pd1212);
       pd13 = findViewById(R.id.pd13);
        pd1313 = findViewById(R.id.pd1313);
        pd14= findViewById(R.id.pd14);
        pd1414= findViewById(R.id.pd1414);



        pd15 = findViewById(R.id.pd15);
        pd1515 = findViewById(R.id.pd1515);
        pd16 = findViewById(R.id.pd16);
        pd1616 = findViewById(R.id.pd1616);
        pd17= findViewById(R.id.pd17);
        pd1717= findViewById(R.id.pd1717);
        pd18= findViewById(R.id.pd18);
        pd1818= findViewById(R.id.pd1818);

*/



        pd1.setEditable(false);
        pd1.setTypefaceText(Typeface.MONOSPACE);

        pd2.setEditable(false);
        pd2.setTypefaceText(Typeface.MONOSPACE);

        pd3.setEditable(false);
        pd3.setTypefaceText(Typeface.MONOSPACE);

        pd4.setEditable(false);
        pd4.setTypefaceText(Typeface.MONOSPACE);


        pd5.setEditable(false);
        pd5.setTypefaceText(Typeface.MONOSPACE);

        pd6.setEditable(false);
        pd6.setTypefaceText(Typeface.MONOSPACE);

        pd7.setEditable(false);
        pd7.setTypefaceText(Typeface.MONOSPACE);
        pd8.setEditable(false);
        pd8.setTypefaceText(Typeface.MONOSPACE);
        pd9.setEditable(false);
        pd9.setTypefaceText(Typeface.MONOSPACE);
        pd10.setEditable(false);
        pd10.setTypefaceText(Typeface.MONOSPACE);

        pd111.setEditable(false);
        pd111.setTypefaceText(Typeface.MONOSPACE);
   /*
        pd12.setEditable(false);
        pd12.setTypefaceText(Typeface.MONOSPACE);

    pd13.setEditable(false);
        pd13.setTypefaceText(Typeface.MONOSPACE);

        pd14.setEditable(false);
        pd14.setTypefaceText(Typeface.MONOSPACE);


        pd15.setEditable(false);
        pd15.setTypefaceText(Typeface.MONOSPACE);

        pd16.setEditable(false);
        pd16.setTypefaceText(Typeface.MONOSPACE);

        pd17.setEditable(false);
        pd17.setTypefaceText(Typeface.MONOSPACE);
        pd18.setEditable(false);
        pd18.setTypefaceText(Typeface.MONOSPACE);

*/



        String tt = "Python Sets  {}";
        title.setText(tt);
        title.setSelected(true);


        qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(getApplicationContext(), OpenQuiz.class);
                quiz.putExtra("tt", tt);
                quiz.putExtra("id", 26);

                startActivity(quiz);
            }
        });


        pylang.pyLang(getApplicationContext(),pd1);
        pylang.pyLang(getApplicationContext(),pd2);
        pylang.pyLang(getApplicationContext(),pd3);
        pylang.pyLang(getApplicationContext(),pd4);
        //   pylang.pyLang(getApplicationContext(),pd5);
        pylang.pyLang(getApplicationContext(),pd6);
        // pylang.pyLang(getApplicationContext(),pd7);
        pylang.pyLang(getApplicationContext(),pd8);
        // pylang.pyLang(getApplicationContext(),pd9);
        pylang.pyLang(getApplicationContext(),pd10);
        pylang.pyLang(getApplicationContext(),pd111);

        /* pylang.pyLang(getApplicationContext(),pd12);

        pylang.pyLang(getApplicationContext(),pd13);
        pylang.pyLang(getApplicationContext(),pd14);

        pylang.pyLang(getApplicationContext(),pd15);
        pylang.pyLang(getApplicationContext(),pd16);
        pylang.pyLang(getApplicationContext(),pd17);
        pylang.pyLang(getApplicationContext(),pd18);*/





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




        pd55.setOnClickListener(new View.OnClickListener() {
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


        pd1010.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd10.getText().toString());
                startActivity(exe1);
            }
        });


        pd1111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd111.getText().toString());
                startActivity(exe1);
            }
        });
     /*    pd1212.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd12.getText().toString());
                startActivity(exe1);
            }
        });
       pd1313.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd13.getText().toString());
                startActivity(exe1);
            }
        });




        pd1414.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd14.getText().toString());
                startActivity(exe1);
            }
        });

        pd1515.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd15.getText().toString());
                startActivity(exe1);
            }
        });
        pd1616.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd16.getText().toString());
                startActivity(exe1);
            }
        });
        pd1717.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd17.getText().toString());
                startActivity(exe1);
            }
        });
        pd1818.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe1 =new Intent(getApplicationContext(), CompilerPy.class);
                exe1.putExtra("try1",pd18.getText().toString());
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

}
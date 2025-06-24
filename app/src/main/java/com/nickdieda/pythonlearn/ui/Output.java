package com.nickdieda.pythonlearn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.BrightnessUtil;

public class Output extends AppCompatActivity {

    private ImageButton menuButton;
    TextView txtv;
    LinearLayout homefra,lessonfra,cd;
    ImageView homei,lessoni;
    TextView hometext,lessontext,percentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_output);
        menuButton = findViewById(R.id.menu_button);
        homefra=findViewById(R.id.home_fra);
        lessonfra=findViewById(R.id.lesson_fra);
        txtv=findViewById(R.id.outputr);

        homei=findViewById(R.id.home_image);
        lessoni=findViewById(R.id.lessons_im);
        hometext=findViewById(R.id.home_text);
        lessontext=findViewById(R.id.lesson_text);

        cd=findViewById(R.id.codeit);

        TextView title=findViewById(R.id.title);

        title.setText("Output");

        String outp=getIntent().getStringExtra("output");

        String f="\n\n\n[Program Finished]";
        txtv.setText(outp+f);
        txtv.setMovementMethod(new ScrollingMovementMethod());





        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.unbook);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.off));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_unselected);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        lessonfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(intent);

            }
        });
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CompilerPy.class);
                startActivity(intent);

            }
        });

        homefra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });


    }
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false);
        popupMenu.getMenu().findItem(R.id.rate_us).setVisible(false);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    BrightnessUtil.showBrightnessDialog(Output.this);
                    return true;

                }else if (item.getItemId() == R.id.action_save) {
                    Toast.makeText(getApplicationContext(), "Save clicked", Toast.LENGTH_SHORT).show();
                    return true;

                }else if (item.getItemId() == R.id.action_open) {
                    Toast.makeText(getApplicationContext(), "Open clicked", Toast.LENGTH_SHORT).show();
                    return true;

                } else {
                    return false;
                }
            }
        });

        popupMenu.show();
    }
}
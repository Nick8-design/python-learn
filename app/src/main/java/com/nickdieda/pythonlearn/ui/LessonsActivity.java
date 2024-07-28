package com.nickdieda.pythonlearn.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
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

import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;

public class LessonsActivity extends AppCompatActivity {
    LinearLayout homefra,lessonfra,button;
    ImageView homei,lessoni,compi,swi_img,uswi,cont_img,image;
    TextView hometext,lessontext,percentage;
    private ImageButton menuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lessons);

        TextView title=findViewById(R.id.title);

            title.setText("Lessons");
        menuButton = findViewById(R.id.menu_button);
                homefra=findViewById(R.id.home_fra);
                lessonfra=findViewById(R.id.lesson_fra);
                homei=findViewById(R.id.home_image);
                lessoni=findViewById(R.id.lessons_im);
                hometext=findViewById(R.id.home_text);
                lessontext=findViewById(R.id.lesson_text);
                percentage=findViewById(R.id.percentage);
                image=findViewById(R.id.image);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.book);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.on));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_icon);
                homefra.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homei.setImageResource(R.drawable.home);
                        lessoni.setImageResource(R.drawable.unbook);
                        hometext.setTextColor(getResources().getColor(R.color.on));
                        lessontext.setTextColor(getResources().getColor(R.color.off));
                        homei.setBackgroundResource(R.drawable.round_icon);
                        lessoni.setBackgroundResource(R.drawable.round_unselected);


                         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                           startActivity(intent);
                            }


                });



            }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());

        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                    return true;

                }else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }
}





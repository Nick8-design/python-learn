package com.nickdieda.pythonlearn.ui;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nickdieda.pythonlearn.Lessons.basic.InstallPy;
import com.nickdieda.pythonlearn.Lessons.basic.Introduction;
import com.nickdieda.pythonlearn.Lessons.basic.OverviewBasic;
import com.nickdieda.pythonlearn.Lessons.basic.PDis;
import com.nickdieda.pythonlearn.Lessons.basic.PyComments;
import com.nickdieda.pythonlearn.Lessons.basic.PyState;
import com.nickdieda.pythonlearn.Lessons.basic.Syntaxs;
import com.nickdieda.pythonlearn.Lessons.basic.WPCodes;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;

import io.github.rosemoe.sora.widget.CodeEditor;

public class LessonsActivity extends AppCompatActivity {
    LinearLayout homefra,lessonfra,cont,overviews,pyintro,pyinstall,writing,display,statement,syntax,comm;
    ImageView homei,lessoni,compi,swi_img,uswi,cont_img,image;
    TextView hometext,lessontext,percentage,hdt;
    private ImageButton menuButton;
    private CodeEditor fra;
    ProgressBar basicf,prointro,proinst,prowrite,prodis,prostate,prosyn,procom;
    private ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    private    int prog,b2,b3,b4,b5,b6,b7,b8;
    int totalProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lessons);

        sharedPreferences = getSharedPreferences("progress", MODE_PRIVATE);
        int bfp = sharedPreferences.getInt("overv", 0);
        int bint = sharedPreferences.getInt("intro", 0);
        int binsta = sharedPreferences.getInt("installa", 0);
        int bwpc = sharedPreferences.getInt("wpc", 0);
        int bdis = sharedPreferences.getInt("pdis", 0);
        int bsta = sharedPreferences.getInt("pystate", 0);
        int bsin = sharedPreferences.getInt("syntax", 0);
        int bcom = sharedPreferences.getInt("comments", 0);


        totalProgress=bfp+bint+binsta+bwpc+bdis+bsta+bsin+bcom;
        progressindicator();



        TextView title=findViewById(R.id.title);

            title.setText("Lessons");
                menuButton = findViewById(R.id.menu_button);
                homefra=findViewById(R.id.home_fra);
                prowrite=findViewById(R.id.prowrite);
        display=findViewById(R.id.display);
        procom=findViewById(R.id.procom);
        prosyn=findViewById(R.id.prosyn);
        comm=findViewById(R.id.comments);
                lessonfra=findViewById(R.id.lesson_fra);
                syntax=findViewById(R.id.syntax);
                 statement=findViewById(R.id.statement);
                homei=findViewById(R.id.home_image);
                lessoni=findViewById(R.id.lessons_im);
                hometext=findViewById(R.id.home_text);
                lessontext=findViewById(R.id.lesson_text);
                percentage=findViewById(R.id.percentage);
                image=findViewById(R.id.image);
                cont=findViewById(R.id.contless);
                pyinstall=findViewById(R.id.pyinstall);
                proinst=findViewById(R.id.proinst);
                writing=findViewById(R.id.writing);
                overviews=findViewById(R.id.pyover);
                hdt=findViewById(R.id.header);
                basicf=findViewById(R.id.overpro);
                pyintro=findViewById(R.id.pyintro);
                prointro=findViewById(R.id.prointro);
        prostate=findViewById(R.id.prostate);
                prodis=findViewById(R.id.prodis);




                proinst.setProgress(probar(binsta,b3));
                basicf.setProgress(probar(bfp,prog));
                prointro.setProgress(probar(bint,b2));
                prowrite.setProgress(probar(bwpc,b4));
                prodis.setProgress(probar(bdis,b5));
                prostate.setProgress(probar(bsta,b6));
         prosyn.setProgress(probar(bsin,b7));
        procom.setProgress(probar(bcom,b8));

        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), PyComments.class);

                startActivity(insta);
            }
        });

        syntax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), Syntaxs.class);

                startActivity(insta);
            }
        });
        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), PyState.class);

                startActivity(insta);
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), PDis.class);

                startActivity(insta);
            }
        });

        writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), WPCodes.class);

                startActivity(insta);
            }
        });
        pyinstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insta=new Intent(getApplicationContext(), InstallPy.class);

                startActivity(insta);
            }
        });





        pyintro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro=new Intent(getApplicationContext(), Introduction.class);

                startActivity(intro);
            }
        });

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



                overviews.setOnClickListener(new View.OnClickListener() {
                    String hdo=hdt.getText().toString();
                    @Override
                    public void onClick(View v) {
                Intent over=new Intent(getApplicationContext(), OverviewBasic.class);
                over.putExtra("tto",hdo);
                startActivity(over);
                    }
                });



            }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());

        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding

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


public int probar(int a ,int b){
    if(a==0){
        b=0;
    }else if(a==1){
        b=25;
    }else if(a==2){
        b=50;
    }else if(a==3){
        b=75;
    }else{
        b=100;
    }
    return b;
}
int p;
public void  progressindicator(){
    SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();



    if(totalProgress<10){
        editor.putInt("count", 3);
      } else if (totalProgress>=10) {
        editor.putInt("count", 9);
    }

    editor.apply();
}


}





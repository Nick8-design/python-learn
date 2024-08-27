package com.nickdieda.pythonlearn;

import static androidx.core.splashscreen.SplashScreen.installSplashScreen;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.MutableInt;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nickdieda.pythonlearn.common.BrightnessUtil;
import com.nickdieda.pythonlearn.common.ColorUtil;
import com.nickdieda.pythonlearn.common.ReturnActivity;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;
import com.nickdieda.pythonlearn.ui.Projects;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    LinearLayout homefra, lessonfra, button, compiler_b, projects,continuel;
    ImageView homei, lessoni, compi, swi_img, uswi, cont_img, image;
    TextView hometext, lessontext, percentage, percentag,title,covpas,topicname,strand,topicno,ts,tn;
    private ImageButton menuButton;
   private int i;
private int activityid;
    private static final int REQUEST_WRITE_STORAGE = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        installSplashScreen(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        menuButton = findViewById(R.id.menu_button);
        continuel = findViewById(R.id.continuel);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);
        homei = findViewById(R.id.home_image);
        lessoni = findViewById(R.id.lessons_im);
        hometext = findViewById(R.id.home_text);
        lessontext = findViewById(R.id.lesson_text);
        percentage = findViewById(R.id.percentage);
        image = findViewById(R.id.image);
        button = findViewById(R.id.swi);
        compiler_b = findViewById(R.id.compiler_btn);
        swi_img = findViewById(R.id.swi_img);
        title = findViewById(R.id.title);
        projects = findViewById(R.id.projectsbtn);
        topicname=findViewById(R.id.topicname);
        topicno=findViewById(R.id.topicno);
        strand=findViewById(R.id.strand);
        ts=findViewById(R.id.ts);
        covpas=findViewById(R.id.covpas);
        tn=findViewById(R.id.tn);
        percentag=findViewById(R.id.percentag);


        requestStoragePermission();
        createPythonProjectsFolder();
        title.setText("Home");





        covpas.setTextColor(ColorUtil.getRandomColor());


        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        i = sharedPreferences.getInt("count", 0);
        int imageResource = sharedPreferences.getInt("imageResource", R.drawable.p);
        int colorValue = sharedPreferences.getInt("colorValue", R.color.red);
        String tops=sharedPreferences.getString("topics","Welcome!,Lets learn python together.");
        String topsno=sharedPreferences.getString("topno","Home page");
        String stre=sharedPreferences.getString("strands","Home");
         activityid= sharedPreferences.getInt("idlearn", 0);

        topicname.setText(tops);
        topicno.setText(topsno);
        strand.setText(stre);

        topicname.setSelected(true);
        topicno.setSelected(true);
        strand.setSelected(true);
        ts.setSelected(true);
        tn.setSelected(true);



        image.setImageResource(imageResource);
        percentage.setText(getPercentageText(i));
        percentag.setText(getPercentageText(i));
        percentage.setTextColor(getResources().getColor(colorValue));
        percentag.setTextColor(getResources().getColor(colorValue));
        octColor(colorValue);



        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        compiler_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompilerPy.class);
                startActivity(intent);
            }
        });
        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Projects.class);
                startActivity(intent);
            }
        });


      //  button.setOnClickListener(new View.OnClickListener() {

        //    @Override
           // public void onClick(View view) {

                int imageResource1 = 0; // Initialize imageResource here
                int colorValue1 = 0;     // Initialize colorValue here

                // Determine image and color based on i
                switch (i) {
                    case 0:
                        imageResource1 = R.drawable.p;
                        colorValue1 = R.color.red;
                        break;
                    case 1:
                        imageResource1 = R.drawable.p10;
                        colorValue1 = R.color.orange;
                        break;
                    case 2:
                        imageResource1 = R.drawable.p20;
                        colorValue1 = R.color.off;
                        break;
                    case 3:
                        imageResource1 = R.drawable.p30;
                        colorValue1 = R.color.lightgray;
                        break;
                    case 4:
                        imageResource1 = R.drawable.p40;
                        colorValue1 = R.color.egg;
                        break;

                    case 5:
                        imageResource1 = R.drawable.p50;
                        colorValue1 = R.color.lightgray;
                        break;
                    case 6:
                        imageResource1 = R.drawable.p60;
                        colorValue1 = R.color.green;
                        break;
                    case 7:
                        imageResource1 = R.drawable.p70;
                        colorValue1 = R.color.blue;
                        break;
                    case 8:
                        imageResource1 = R.drawable.p80;
                        colorValue1 = R.color.pink;
                        break;
                    case 9:
                        imageResource1 = R.drawable.p90;
                        colorValue1 = R.color.white;
                        break;

                    case 10:
                        imageResource1 = R.drawable.p90;
                        colorValue1 = R.color.yellow;
                        break;
                    default:
                        i = 0;
                        imageResource1 = R.drawable.p;
                        colorValue1 = R.color.red;
                        break;
                }
                image.setImageResource(imageResource1);
                percentage.setText(getPercentageText(i));
                percentag.setText(getPercentageText(i));
               percentage.setTextColor(getResources().getColor(colorValue1));

               percentag.setTextColor(getResources().getColor(colorValue1));
                octColor(colorValue1);

                // Save state to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("count", i);
                editor.putInt("imageResource", imageResource1);
                editor.putInt("colorValue", colorValue1);
                editor.apply();

              //  i++;
          //  }


     //   });


        continuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnActivity rt=new ReturnActivity();
                Intent bk = rt.returnINT(getApplicationContext(),activityid);
                startActivity(bk);



            }
        });


        homei.setImageResource(R.drawable.home);
        lessoni.setImageResource(R.drawable.unbook);
        hometext.setTextColor(getResources().getColor(R.color.on));
        lessontext.setTextColor(getResources().getColor(R.color.off));
        homei.setBackgroundResource(R.drawable.round_icon);
        lessoni.setBackgroundResource(R.drawable.round_unselected);


        lessonfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(intent);

            }
        });


    }

    private String getPercentageText(int i) {
        switch (i) {
            case 0:
                return "0%";
            case 1:
                return "10%";
            case 2:
                return "20%";
            case 3:
                return "30%";
            case 4:
                return "40%";
            case 5:
                return "50%";
            case 6:
                return "60%";
            case 7:
                return "70%";

            case 8:
                return "80%";
            case 9:
                return "90%";

            case 10:
                return "100%";
            default:
                return "0%";
        }
    }



    public void octColor(int color) {
        Drawable background = percentage.getBackground();
        if (background instanceof VectorDrawable) {
            ((VectorDrawable) background).setColorFilter(
                    getResources().getColor(color),
                    PorterDuff.Mode.SRC_IN
            );
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count", i);
        editor.apply();

    }



    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    BrightnessUtil.showBrightnessDialog(MainActivity.this);
                    return true;

                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }


    private void createPythonProjectsFolder() {
        File pythonProjectsFolder = new File(Environment.getExternalStorageDirectory(), "Python programs");
        if (!pythonProjectsFolder.exists()) {
            pythonProjectsFolder.mkdirs();
        }
    }

    private void requestStoragePermission() {
        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        }
    }





}







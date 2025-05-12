package com.nickdieda.pythonlearn;

import static androidx.core.splashscreen.SplashScreen.installSplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nickdieda.pythonlearn.common.BrightnessUtil;
import com.nickdieda.pythonlearn.common.ColorUtil;
import com.nickdieda.pythonlearn.common.GenerateCertificate;
import com.nickdieda.pythonlearn.common.ReturnActivity;
import com.nickdieda.pythonlearn.common.certificate;
import com.nickdieda.pythonlearn.ui.CompilerPy;
import com.nickdieda.pythonlearn.ui.LessonsActivity;
import com.nickdieda.pythonlearn.ui.Projects;

import java.io.File;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    LinearLayout homefra, lessonfra, button, compiler_b, projects, continuel, swi;
    ImageView homei, lessoni, compi, swi_img, uswi, cont_img, image;
    TextView hometext, lessontext, percentage, percentag, title, covpas, topicname, strand, topicno, ts, tn;
    private ImageButton menuButton;
    private int mainactId = 8;
    private int i;
    private int activityid;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_WRITE_STORAGE = 112;
    private ActivityResultLauncher<Intent> folderPickerLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        installSplashScreen(this);
//        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {});
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        menuButton = findViewById(R.id.menu_button);
        continuel = findViewById(R.id.continuel);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);
        homei = findViewById(R.id.home_image);
        lessoni = findViewById(R.id.lessons_im);
        hometext = findViewById(R.id.home_text);
        lessontext = findViewById(R.id.lesson_text);
        percentage = findViewById(R.id.percentage);
        percentag = findViewById(R.id.percentag);
        image = findViewById(R.id.image);

        compiler_b = findViewById(R.id.compiler_btn);
        swi_img = findViewById(R.id.swi_img);
        title = findViewById(R.id.title);
        projects = findViewById(R.id.projectsbtn);
        topicname = findViewById(R.id.topicname);
        topicno = findViewById(R.id.topicno);
        strand = findViewById(R.id.strand);
        ts = findViewById(R.id.ts);
        covpas = findViewById(R.id.covpas);
        tn = findViewById(R.id.tn);
        swi = findViewById(R.id.swi);


        requestStoragePermission();
        createPythonProjectsFolder();
        title.setText("Home");


        covpas.setTextColor(ColorUtil.getRandomColor());


        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        i = sharedPreferences.getInt("count", 0);
        int imageResource = sharedPreferences.getInt("imageResource", R.drawable.p);
        int colorValue = sharedPreferences.getInt("colorValue", R.color.red);
        String tops = sharedPreferences.getString("topics", "Welcome!,Lets learn python together.");
        String topsno = sharedPreferences.getString("topno", "Home page");
        String stre = sharedPreferences.getString("strands", "Home");
        activityid = sharedPreferences.getInt("idlearn", 0);
        int totalm = sharedPreferences.getInt("total_marks", 0);



        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-5272550552627150/2093048931", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });






        swi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Reload a new ad after the current one is dismissed
                            loadInterstitialAd();
                        }
                    });
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }


                if (totalm > 202) {
//                       if(true){

//                    if (mInterstitialAd != null) {
//                        mInterstitialAd.show(MainActivity.this);
//                    } else {
//                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                    }


                    certificate cet=new certificate();
                    cet.requestUserNameBeforeDownload(MainActivity.this,totalm);

//                         GenerateCertificate.selectFolder(this, folderPickerLauncher);

                } else {
                    Toast.makeText(MainActivity.this, "Score 80% of the total marks to claim your certificate !", Toast.LENGTH_SHORT).show();
                }
            }
        });



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


                if (mInterstitialAd != null) {
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when the ad is dismissed.
                            Log.d("TAG", "Ad was dismissed.");
                            // Proceed to next activity
                            Intent intent = new Intent(getApplicationContext(), CompilerPy.class);
                            intent.putExtra("mainid", mainactId);
                            startActivity(intent);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show
                            Log.e("TAG", "Ad failed to show.");
                            // Proceed anyway
                            Intent intent = new Intent(getApplicationContext(), CompilerPy.class);
                            intent.putExtra("mainid", mainactId);
                            startActivity(intent);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null; // Set to null so it can't be reused.
                        }
                    });

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    // If ad is not ready, just go to activity
                    Intent intent = new Intent(getApplicationContext(), CompilerPy.class);
                    intent.putExtra("mainid", mainactId);
                    startActivity(intent);
                }


            }

        });



        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            jjj
//                if (mInterstitialAd != null) {
//                    mInterstitialAd.show(MainActivity.this);
//                } else {
//                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                }
//                Intent intent = new Intent(getApplicationContext(), Projects.class);
//                startActivity(intent);


                if (mInterstitialAd != null) {
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when the ad is dismissed.
                            Log.d("TAG", "Ad was dismissed.");
                            // Proceed to next activity
                            Intent intent = new Intent(getApplicationContext(), Projects.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show
                            Log.e("TAG", "Ad failed to show.");
                            // Proceed anyway
                            Intent intent = new Intent(getApplicationContext(), Projects.class);

                            startActivity(intent);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null; // Set to null so it can't be reused.
                        }
                    });

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    // If ad is not ready, just go to activity
                    Intent intent = new Intent(getApplicationContext(), Projects.class);
                    startActivity(intent);
                }



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


        continuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnActivity rt = new ReturnActivity();
                Intent bk = rt.returnINT(getApplicationContext(), activityid);
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

//
//                jjjj
//                if (mInterstitialAd != null) {
//                    mInterstitialAd.show(MainActivity.this);
//                } else {
//                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                }
//
//
//
//
//                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
//                startActivity(intent);



                if (mInterstitialAd != null) {
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when the ad is dismissed.
                            Log.d("TAG", "Ad was dismissed.");
                            // Proceed to next activity
                            Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show
                            Log.e("TAG", "Ad failed to show.");
                            // Proceed anyway
                            Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);

                            startActivity(intent);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            mInterstitialAd = null; // Set to null so it can't be reused.
                        }
                    });

                    mInterstitialAd.show(MainActivity.this);
                } else {
                    // If ad is not ready, just go to activity
                    Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                    startActivity(intent);
                }





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
        Drawable backg = percentag.getBackground();
        if (background instanceof VectorDrawable) {
            ((VectorDrawable) background).setColorFilter(
                    getResources().getColor(color),
                    PorterDuff.Mode.SRC_IN
            );
        }
        if (backg instanceof VectorDrawable) {
            ((VectorDrawable) backg).setColorFilter(
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

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-5272550552627150/2093048931", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "Interstitial ad loaded.");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Log.e(TAG, "Failed to load interstitial ad: " + loadAdError.getMessage());
                    }
                });
    }




}










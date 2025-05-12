package com.nickdieda.pythonlearn.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.AdHelper;
import com.nickdieda.pythonlearn.common.BrightnessUtil;
import com.nickdieda.pythonlearn.common.CodeLang;
import com.nickdieda.pythonlearn.common.ReturnActivity;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


import io.github.rosemoe.sora.widget.CodeEditor;


public class CompilerPy extends AppCompatActivity {
    TextView txtv;
    LinearLayout homefra, lessonfra, button, runb;
    private ImageButton menuButton;
    ImageView homei, lessoni, returnback, clear;
    TextView hometext, lessontext, percentage;
    private CodeEditor CodeArea;

    private Button runButton;
    String script = "#Your Python code goes here";
    String outgo;
    private static final int REQUEST_INPUT = 1;

    private FrameLayout adContainerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compiler_py);
        CodeArea = findViewById(R.id.codearea);



        MobileAds.initialize(this, initializationStatus -> {});


        AdHelper.initializeAds(this);
        adContainerView = findViewById(R.id.ad_view_container);
        adContainerView.setVisibility(View.GONE);
        AdHelper.loadBannerAd(this, adContainerView);





        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        String savedCode = sharedPreferences.getString("code", "");
        int retmain = sharedPreferences.getInt("backcode", 8);

        int activityid = sharedPreferences.getInt("idlearn", 0);

        if (savedCode.isEmpty()) {
            CodeArea.setText("#welcome to python");
        } else {

            CodeArea.setText(savedCode);
        }
        String code = getIntent().getStringExtra("code");

        if (code != null) {
            CodeArea.setText(code);
        }
        String ex1 = getIntent().getStringExtra("try1");

        if (ex1 != null) {
            CodeArea.setText(ex1);
        }


        Intent intent = getIntent();
        String fileUriString = intent.getStringExtra("fileUri");

        if (fileUriString != null) {
            Uri fileUri = Uri.parse(fileUriString);

            try (InputStream inputStream = getContentResolver().openInputStream(fileUri);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                CodeArea.setText(fileContent.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to read file content", Toast.LENGTH_SHORT).show();
            }
        } else
        {
          //  Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }


        menuButton = findViewById(R.id.menu_button);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);
        returnback = findViewById(R.id.returnback);


        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);

        homei = findViewById(R.id.home_image);
        lessoni = findViewById(R.id.lessons_im);
        hometext = findViewById(R.id.home_text);
        lessontext = findViewById(R.id.lesson_text);
        clear = findViewById(R.id.clear);

        TextView title = findViewById(R.id.title);

        title.setText("Compiler");


        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.unbook);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.off));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_unselected);


        runb = findViewById(R.id.outp);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });


        CodeArea.setTypefaceText(Typeface.MONOSPACE);

        int retma = getIntent().getIntExtra("mainid", 8);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("backcode", retma);
        editor.apply();


        ReturnActivity rt = new ReturnActivity();
        returnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk;
                if (retmain == 8) {
                    bk = new Intent(getApplicationContext(), MainActivity.class);
                } else if (retmain == 11) {
                    bk = new Intent(getApplicationContext(), Projects.class);
                } else if (retmain == 12) {
                    bk = new Intent(getApplicationContext(), Myproject.class);
                } else {
                    bk = rt.returnINT(getApplicationContext(), activityid);
                }
                startActivity(bk);
            }
        });


        CodeLang.pyLangstatic(getApplicationContext(), CodeArea);
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        runb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String script = CodeArea.getText().toString();
                Python py = Python.getInstance();
                PyObject pyobj = py.getModule("myscript");
                try {
                    PyObject obj = pyobj.callAttr("main", script);
                    String outgo = obj.toString();
                    launchOutputActivity(outgo);
                } catch (PyException e) {
                    if (e.getMessage().contains("Input required")) {

                    } else {
                        launchOutputActivity(e.getMessage());
                    }
                }
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodeArea.setText("");
            }
        });


        homefra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        lessonfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        // Store script in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("code", CodeArea.getText().toString());
        editor.apply();
    }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {

                    BrightnessUtil.showBrightnessDialog(CompilerPy.this);
                    return true;
                } else if (item.getItemId() == R.id.action_save) {
                    if (!CodeArea.getText().toString().trim().isEmpty()) {
                        showSaveAsDialog();
                    } else {
                        Toast.makeText(getApplicationContext(), "Code what you want to save", Toast.LENGTH_SHORT).show();
                    }
                    return true;

                } else if (item.getItemId() == R.id.action_open) {

                    Intent opn = new Intent(getApplicationContext(), Myproject.class);
                    startActivity(opn);

                    return true;

                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }


//    CodeArea codeArea = findViewById(R.id.code_area); // Make sure this ID matches your layout


    private void showErrorToast(String message) {
        Toast.makeText(CompilerPy.this, message, Toast.LENGTH_LONG).show();
    }


    private void launchOutputActivity(String output) {
        Intent intent = new Intent(getApplicationContext(), Output.class);
        intent.putExtra("output", output);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_USER_INPUT) {
                // Handle user input for Python script
                if (data != null) {
                    String userInput = data.getStringExtra("userInput");
                    Python py = Python.getInstance();
                    PyObject pyobj = py.getModule("myscript");
                    pyobj.callAttr("handle_input", userInput);
                }
            } else if (requestCode == REQUEST_CODE_OPEN_DOCUMENT_TREE) {
                // Handle folder selection for saving files
                if (data != null) {
                    Uri folderUri = data.getData();

                    // Persist permissions for the folder
                    getContentResolver().takePersistableUriPermission(folderUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    // Save the folder URI in SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                    sharedPreferences.edit().putString("selectedFolderUri", folderUri.toString()).apply();

                    Toast.makeText(this, "Folder selected successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private static final int REQUEST_CODE_USER_INPUT = 1;
    private static final int REQUEST_CODE_OPEN_DOCUMENT_TREE = 2;

    // Trigger folder selection
    private void selectFolder() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_OPEN_DOCUMENT_TREE);
    }


    public void saveScript(String scriptContent, String scriptName) {
        // Get the folder URI from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String folderUriString = sharedPreferences.getString("selectedFolderUri", null);

        if (folderUriString == null) {
            Toast.makeText(this, "No folder selected. Please select a folder first.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri folderUri = Uri.parse(folderUriString);

        try {
            // Use DocumentFile to work with the folder URI
            DocumentFile folder = DocumentFile.fromTreeUri(this, folderUri);
            if (folder == null || !folder.exists()) {
                Toast.makeText(this, "Selected folder does not exist.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if a file with the same name already exists
            String fileName = scriptName + ".py";
            DocumentFile existingFile = folder.findFile(fileName);
            if (existingFile != null) {
                Toast.makeText(this, "A script with this name already exists. Please choose a different name.", Toast.LENGTH_LONG).show();
                showSaveAsDialog(); // Prompt the user to enter a new name
                return;
            }

            // Create a new file in the selected folder
            DocumentFile newFile = folder.createFile("text/x-python", fileName);
            if (newFile == null) {
                Toast.makeText(this, "Failed to create file.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Write the content to the new file
            try (OutputStream outputStream = getContentResolver().openOutputStream(newFile.getUri())) {
                if (outputStream != null) {
                    outputStream.write(scriptContent.getBytes());
                    Toast.makeText(this, "Script saved successfully in " + folder.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to open file for writing.", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Failed to save script: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showSaveAsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save As");
        builder.setMessage("Write the name only");
        builder.setIcon(R.drawable.save);
        builder.setCancelable(true);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String scriptName = input.getText().toString().trim();
                String scriptContent = CodeArea.getText().toString();
                if (!scriptName.isEmpty()) {
                    saveScript(scriptContent, scriptName);
                } else {
                    Toast.makeText(getApplicationContext(), "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                    showSaveAsDialog();

                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                positiveButton.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_dark));
                negativeButton.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_dark));
            }
        });

        dialog.show();
    }


}
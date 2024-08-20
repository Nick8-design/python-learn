package com.nickdieda.pythonlearn.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.setpylan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import io.github.rosemoe.sora.widget.CodeEditor;


public class CompilerPy extends AppCompatActivity {
    TextView txtv;
    LinearLayout homefra, lessonfra, button, runb;
    private ImageButton menuButton;
    ImageView homei, lessoni, compi, swi_img, uswi, cont_img, image;
    TextView hometext, lessontext, percentage;
    private CodeEditor CodeArea;
    TextView clear;
    // private EditText CodeArea;
    private Button runButton;
    String script = "#Your Python code goes here";
    String outgo;
    private static final int REQUEST_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compiler_py);
        CodeArea = findViewById(R.id.codearea);

        SharedPreferences sharedPreferences = getSharedPreferences("app_datas", MODE_PRIVATE);
        String savedCode = sharedPreferences.getString("code", ""); // Default value if not found

        if (savedCode.isEmpty()) {
            CodeArea.setText("#delete..");
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
        String filePath = getIntent().getStringExtra("filePath");
        if (filePath != null) {
            String fileContent = readFileContent(new File(filePath));
            CodeArea.setText(fileContent);
        }


        menuButton = findViewById(R.id.menu_button);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);


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


        setpylan pylang=new setpylan();
        pylang.pyLang(getApplicationContext(),CodeArea);
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
                        launchInputActivity();
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

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {


                    Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_save) {
                    if (!CodeArea.getText().toString().trim().isEmpty()) {
                        showSaveAsDialog();
                    } else {
                        Toast.makeText(getApplicationContext(), "Code what you want to save", Toast.LENGTH_SHORT).show();
                    }
                    return true;

                } else if (item.getItemId() == R.id.action_open) {

                    Intent opn=new Intent(getApplicationContext(),Myproject.class);
                    startActivity(opn);

                    return true;

                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }

    private String readFileContent(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_LONG).show();
        }
        return stringBuilder.toString();
    }

    private void launchOutputActivity(String output) {
        Intent intent = new Intent(getApplicationContext(), Output.class);
        intent.putExtra("output", output);
        startActivity(intent);
    }

    private void launchInputActivity() {
        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
        startActivityForResult(intent, 1);  // Use a request code to identify the response
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String userInput = data.getStringExtra("userInput");
            Python py = Python.getInstance();
            PyObject pyobj = py.getModule("myscript");
            pyobj.callAttr("handle_input", userInput);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveScript(String scriptContent, String scriptName) {
        File pythonProjectsFolder = new File(Environment.getExternalStorageDirectory(), "Python programs");
        if (!pythonProjectsFolder.exists()) {
            pythonProjectsFolder.mkdirs();
        }

        File scriptFile = new File(pythonProjectsFolder, scriptName + ".py");

        if (scriptFile.exists()) {
            Toast.makeText(this, "A script with this name already exists. Please choose a different name.", Toast.LENGTH_LONG).show();
            showSaveAsDialog(); // Prompt the user to enter a new name
        } else {
            try (FileOutputStream fos = new FileOutputStream(scriptFile)) {
                fos.write(scriptContent.getBytes());
                Toast.makeText(this, "Saved in Python programs", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Failed to save script: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
                String scriptContent=CodeArea.getText().toString();
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
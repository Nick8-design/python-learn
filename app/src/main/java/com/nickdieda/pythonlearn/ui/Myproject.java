package com.nickdieda.pythonlearn.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.BrightnessUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Myproject extends AppCompatActivity {
    TextView title;
    LinearLayout pre;
    LinearLayout homefra, lessonfra,noPermission;
    RelativeLayout progressBar;
    ImageView homei, lessoni;
    TextView hometext, lessontext;
    Button setPermission;
    private static final int REQUEST_PERMISSION = 1;

    private final List<File> pythonFiles = new ArrayList<>();
    //private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproject);

        // Initialize views
        title = findViewById(R.id.title);
        setPermission = findViewById(R.id.setPermission);
        noPermission = findViewById(R.id.noPermission);
        pre = findViewById(R.id.prepro);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);
        homei = findViewById(R.id.home_image);
        lessoni = findViewById(R.id.lessons_im);
        hometext = findViewById(R.id.home_text);
        lessontext = findViewById(R.id.lesson_text);
        ImageButton menuButton = findViewById(R.id.menu_button);
        progressBar = findViewById(R.id.progressBar); // ProgressBar
        recyclerView = findViewById(R.id.phnpro); // RecyclerView for displaying the files

        // Initialize ExecutorService
        executorService = Executors.newSingleThreadExecutor();

        // Set title and button actions
        title.setText("My Projects");
        pre.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Projects.class);
            startActivity(intent);
        });

        homefra.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        lessonfra.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
            startActivity(intent);
        });

        menuButton.setOnClickListener(this::showPopupMenu);

        // Request storage permission and load files
        noPermission.setVisibility(View.GONE);
        requestStoragePermission();

        // Button to manually set permission
        setPermission.setOnClickListener(v -> requestStoragePermission());


    }

    // Load files using ExecutorService
    private void loadPythonFiles() {
       // Hide permission warning
        progressBar.setVisibility(View.VISIBLE); // Show progress bar
        recyclerView.setVisibility(View.GONE); // Hide RecyclerView
        noPermission.setVisibility(View.GONE);
        executorService.execute(() -> {
            File storageDir = Environment.getExternalStorageDirectory();
            scanDirectory(storageDir);

            runOnUiThread(() -> {

                // Hide progress bar and show RecyclerView once files are loaded
              // progressBar.setVisibility(View.GONE);
              //  noPermission.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                if (!pythonFiles.isEmpty()) {
                   recyclerView.setLayoutManager(new LinearLayoutManager(Myproject.this));
                    recyclerView.setAdapter(new MyAdapter(pythonFiles));
                    Toast.makeText(Myproject.this, "Found " + pythonFiles.size() + " files", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(Myproject.this, "No Python files found", Toast.LENGTH_SHORT).show();
                }



            });
        });

    }

    // Adapter for RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final List<File> files;

        MyAdapter(List<File> files) {
            this.files = files;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(files.get(position));
        }

        @Override
        public int getItemCount() {
            return files.size();
        }
    }
    // ViewHolder for RecyclerView items
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView fileNameTextView;
        private File file;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(this);
        }

        void bind(File file) {
            this.file = file;
            fileNameTextView.setText(file.getName());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Myproject.this, CompilerPy.class);
            intent.putExtra("filePath", file.getAbsolutePath());
            intent.putExtra("mainid",12);
            startActivity(intent);
        }
    }

    // Request storage permissions
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                noPermission.setVisibility(View.VISIBLE); // Show noPermission layout
                progressBar.setVisibility(View.GONE); // Hide progress bar
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivityForResult(intent, REQUEST_PERMISSION);
            } else {
                noPermission.setVisibility(View.GONE); // Hide noPermission layout
                loadPythonFiles(); // Load files if permission is granted

            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                noPermission.setVisibility(View.VISIBLE); // Show noPermission layout
                progressBar.setVisibility(View.GONE); // Hide progress bar
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                noPermission.setVisibility(View.GONE); // Hide noPermission layout
                loadPythonFiles(); // Load files if permission is granted

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    noPermission.setVisibility(View.GONE); // Hide noPermission layout
                    loadPythonFiles();
                    // Load files if permission is granted
                } else {
                    noPermission.setVisibility(View.VISIBLE); // Show noPermission layout
                    progressBar.setVisibility(View.GONE); // Hide progress bar
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                noPermission.setVisibility(View.GONE); // Hide noPermission layout
                loadPythonFiles(); // Load files if permission is granted

            } else {
                noPermission.setVisibility(View.VISIBLE); // Show noPermission layout
                progressBar.setVisibility(View.GONE); // Hide progress bar
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanDirectory(file);
                } else if (file.getName().endsWith(".py")) {
                    pythonFiles.add(file);

                }
            }
        }
    }



private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false);

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_settings) {
                BrightnessUtil.showBrightnessDialog(Myproject.this);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}

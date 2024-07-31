package com.nickdieda.pythonlearn.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Myproject extends AppCompatActivity {
    TextView title;
    LinearLayout pre;
    LinearLayout homefra, lessonfra, cd;
    ImageView homei, lessoni;
    TextView hometext, lessontext, percentage;
    private ImageButton menuButton;
    private static final int REQUEST_PERMISSION = 1;

    private List<File> pythonFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_myproject);

        requestStoragePermission();

        title = findViewById(R.id.title);
        pre = findViewById(R.id.prepro);
        homefra = findViewById(R.id.home_fra);
        lessonfra = findViewById(R.id.lesson_fra);
        homei = findViewById(R.id.home_image);
        lessoni = findViewById(R.id.lessons_im);
        hometext = findViewById(R.id.home_text);
        lessontext = findViewById(R.id.lesson_text);
        menuButton = findViewById(R.id.menu_button);


        title.setText("My Projects");
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Projects.class);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });


        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.unbook);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.off));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_unselected);

        lessonfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
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

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.phnpro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(pythonFiles);
        recyclerView.setAdapter(adapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<File> files;

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

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView fileNameTextView;
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
            startActivity(intent);
        }
    }


    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, REQUEST_PERMISSION);
            } else {
            // Permission already granted
            loadPythonFiles();
        }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                loadPythonFiles();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    loadPythonFiles();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void loadPythonFiles() {
        File storageDir = Environment.getExternalStorageDirectory();
        scanDirectory(storageDir);
        setupRecyclerView();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Call super method
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadPythonFiles();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
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
                    Toast.makeText(getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                    return true;

                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }
}
package com.nickdieda.pythonlearn.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Myproject extends AppCompatActivity {
    private static final int REQUEST_DIRECTORY = 2;
    private static final String PREFS_NAME = "MyProjectPrefs";
    private static final String SELECTED_FOLDER_URI = "selected_folder_uri";

    TextView title;
    LinearLayout pre;
    LinearLayout homefra, lessonfra, noPermission;
    RelativeLayout progressBar;
    ImageView homei, lessoni;
    TextView hometext, lessontext;
    Button setPermission;

    private final List<File> pythonFiles = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExecutorService executorService;
    private SharedPreferences sharedPreferences;

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

        // Initialize ExecutorService and SharedPreferences
        executorService = Executors.newSingleThreadExecutor();
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

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

        // Check if a directory was previously selected
        String savedUri = sharedPreferences.getString(SELECTED_FOLDER_URI, null);
        if (savedUri != null) {
            Uri folderUri = Uri.parse(savedUri);
            loadPythonFiles(folderUri);
        } else {
            noPermission.setVisibility(View.VISIBLE);
        }

        setPermission.setOnClickListener(v -> openDirectoryPicker());
    }

    // Load Python files from the selected directory
    // Replace File list with Uri list
    private final List<Uri> pythonFileUris = new ArrayList<>();

    // Load Python files from the selected directory
    private void loadPythonFiles(Uri folderUri) {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noPermission.setVisibility(View.GONE);

        executorService.execute(() -> {
            pythonFileUris.clear();

            try {
                String documentId = DocumentsContract.getTreeDocumentId(folderUri);
                Uri childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(folderUri, documentId);

                try (Cursor cursor = getContentResolver().query(
                        childrenUri,
                        new String[]{DocumentsContract.Document.COLUMN_DISPLAY_NAME, DocumentsContract.Document.COLUMN_MIME_TYPE},
                        null,
                        null,
                        null
                )) {
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String fileName = cursor.getString(cursor.getColumnIndexOrThrow(DocumentsContract.Document.COLUMN_DISPLAY_NAME));
                            String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(DocumentsContract.Document.COLUMN_MIME_TYPE));

                            if (mimeType != null && mimeType.equals("text/x-python") || fileName.endsWith(".py")) {

                                Uri fileUri = DocumentsContract.buildDocumentUriUsingTree(folderUri, documentId + "/" + fileName);


                                pythonFileUris.add(fileUri); // Add Uri instead of File
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
              //  progressBar.setVisibility(View.GONE);
                if (!pythonFileUris.isEmpty()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Myproject.this));
                    recyclerView.setAdapter(new MyAdapter(pythonFileUris));
                } else {
                    noPermission.setVisibility(View.VISIBLE);
                    Toast.makeText(Myproject.this, "No Python files found", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // Open directory picker
    private void openDirectoryPicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_DIRECTORY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DIRECTORY && resultCode == RESULT_OK) {
            if (data != null) {
                Uri treeUri = data.getData();
                if (treeUri != null) {
                    getContentResolver().takePersistableUriPermission(treeUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    // Save the selected folder URI
                    sharedPreferences.edit().putString(SELECTED_FOLDER_URI, treeUri.toString()).apply();

                    // Load Python files from the selected folder
                    loadPythonFiles(treeUri);
                }
            }
        }
    }

    // RecyclerView Adapter and ViewHolder
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final List<Uri> uris;

        MyAdapter(List<Uri> uris) {
            this.uris = uris;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(uris.get(position));
        }

        @Override
        public int getItemCount() {
            return uris.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView fileNameTextView;
        private Uri uri;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(this);
        }

        void bind(Uri uri) {
            this.uri = uri;
            fileNameTextView.setText(

                    uri.getPath().substring(uri.getPath().lastIndexOf('/') + 1)
//                    uri.getLastPathSegment().substring(uri.toString().lastIndexOf("/"))
            ); // Display file name
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Myproject.this, CompilerPy.class);
            intent.putExtra("fileUri", uri.toString()); // Pass Uri as string
            startActivity(intent);
        }
    }

//    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private final TextView fileNameTextView;
//        private File file;
//
//        MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            fileNameTextView = itemView.findViewById(R.id.item_text);
//            itemView.setOnClickListener(this);
//        }
//
//        void bind(File file) {
//            this.file = file;
//            fileNameTextView.setText(file.getName());
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(Myproject.this, CompilerPy.class);
//            intent.putExtra("filePath", file.getAbsolutePath());
//            intent.putExtra("mainid", 12);
//            startActivity(intent);
//        }
//    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false);
        popupMenu.getMenu().findItem(R.id.rate_us).setVisible(false);

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_settings) {
                return true;
            }
            return false;
        });

        popupMenu.show();
    }
}

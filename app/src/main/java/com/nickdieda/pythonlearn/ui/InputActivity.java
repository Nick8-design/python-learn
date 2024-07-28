package com.nickdieda.pythonlearn.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nickdieda.pythonlearn.R;

public class InputActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        TextView promptView = findViewById(R.id.prompt_view);
        EditText inputField = findViewById(R.id.input_field);
        Button submitButton = findViewById(R.id.submit_button);

        Intent intent = getIntent();
        String prompt = intent.getStringExtra("prompt");
        promptView.setText(prompt);

        submitButton.setOnClickListener(v -> {
            String userInput = inputField.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("userInput", userInput);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
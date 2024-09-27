package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickdieda.pythonlearn.Fbdb.DBAdd_name;

public class certificate {
//Context con;


    FirebaseDatabase database;
    DatabaseReference dbRef;
    public  void requestUserNameBeforeDownload(Context con,int totalProgress) {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle("Enter Full Name");

        // Create an EditText for user input
        final EditText input = new EditText(con);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            String fullName = input.getText().toString().trim();
            DBAdd_name.addname(con,fullName);


            //name verify

            database=FirebaseDatabase.getInstance();
            dbRef=database.getReference("Python Certificate List");


dbRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.hasChild(fullName)){
            Toast.makeText(con, "You have already claimed your certificate.", Toast.LENGTH_SHORT).show();

        }else {
          // generateCertificate.generateCertificate(con,fullName, totalProgress);
            Toast.makeText(con, "You can claim your certificate", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(con, "Check your network connection !!!", Toast.LENGTH_SHORT).show();
    }
});






        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

}

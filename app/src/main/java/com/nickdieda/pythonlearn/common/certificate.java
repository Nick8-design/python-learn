package com.nickdieda.pythonlearn.common;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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





            database=FirebaseDatabase.getInstance();
            dbRef=database.getReference("Python Certificate List");


            dbRef.orderByChild("name").equalTo(fullName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Name already exists
                        Toast.makeText(con, "You have already claimed your certificate.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Name doesn't exist; add to the database and generate certificate
                        Toast.makeText(con, "Congratulations " + fullName + ", wait for your certificate to be generated.", Toast.LENGTH_SHORT).show();
                        DBAdd_name.addname(con, fullName); // Add the name to Firebase
                        GenerateCertificate.generateCertificate(con, fullName, totalProgress); // Generate the certificate
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(con, "Check your network connection !!! And try again", Toast.LENGTH_LONG).show();
                }
            });






        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }





// To start folder picker


}

package com.nickdieda.pythonlearn.Fbdb;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nickdieda.pythonlearn.ui.LessonsActivity;

import java.util.HashMap;

public class DBAdd_name {
    public static <e> void addname(Context con, String nam){
        //create a hashmap
        HashMap<String,Object> namehash = new HashMap<>();
        namehash.put("name",nam);

        //instantiate db connections
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Python Certificate List");

        String key=myRef.push().getKey();
        namehash.put("key",key);
try {
    myRef.child(nam).setValue(namehash).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            Toast.makeText(con, "Name added to the Online database", Toast.LENGTH_SHORT).show();

        }


    });
}catch (Exception e){
    Toast.makeText(con, "Your name was not added on the online list.\nCheck your network connection", Toast.LENGTH_SHORT).show();


}

    }

}

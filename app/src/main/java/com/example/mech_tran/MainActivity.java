package com.example.mech_tran;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, phoneNumberEditText;
FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();

        user.put("username", "easy");
        user.put("email", "jdkdkd@jcc");
        user.put("phoneNumber", "09876543");
        firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_LONG).show();
            }
        });
        Button goToLoginButton = findViewById(R.id.goToLoginButton);

        goToLoginButton.setOnClickListener(view -> {

            // Create a new Login instance with sample data
            Login login = new Login("joe doe", "jfjf@fj", "1237338r");

            // Save user data to SharedPreferences using Database class
            Database.saveUserData(MainActivity.this, login.getUsername(), login.getEmail(), login.getPhoneNumber());

            // Navigate to Login activity
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);

        });
        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditText fields
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Display a toast with the information entered
                String toastMessage = "Username: " + username + "\nEmail: " + email + "\nPhone Number: " + phoneNumber;
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

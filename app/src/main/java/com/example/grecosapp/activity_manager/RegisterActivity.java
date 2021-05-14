package com.example.grecosapp.activity_manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grecosapp.R;
import com.example.grecosapp.data.User;
import com.example.grecosapp.popup_manager.ERROR;
import com.example.grecosapp.popup_manager.INFO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String photo = "https://firebasestorage.googleapis.com/v0/b/greco-sapp.appspot.com/o/avatarGenerico.png?alt=media&token=db68d346-dfec-490d-86e8-6e5a208b9649";
    private String listaId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();
    }

    public void doSignUp(View view) {
        EditText email_element = findViewById(R.id.email_input);
        EditText password_element = findViewById(R.id.password_input);

        String email = email_element.getText().toString().trim();
        String password = password_element.getText().toString().trim();
        System.out.println("èèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèè");
        System.out.println(email);
        System.out.println(password);
        System.out.println("èèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèè");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // *************
                    createNewUser(email);
                    // *************
                    getLoginPage(view);
                } else {
                    ERROR.USER_OR_PASSWORD_INVALID(getApplicationContext());
                }
                finish();
            }
        });

    }

    private void createNewUser(String email) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        String uid = mAuth.getCurrentUser().getUid();
        User user = new User(email, photo, listaId, uid);

        // get uid of current user
        // set value with new user object
        myRef.child(uid).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                INFO.USER_ADDED(getApplicationContext());
            }
        });

    }

    public void getLoginPage(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
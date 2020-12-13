
package com.example.quizchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizchallenge.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Sign Up");
        binding= ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setMessage("We are creating new account...");
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email ,pass,name,refercode;
                name=binding.nameBox.getText().toString();
                email=binding.emailBox.getText().toString();
                pass=binding.passwordBox.getText().toString();
                refercode=binding.referBox.getText().toString();

                final User user=new User(name,email,pass,refercode);
                dialog.show();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {   String uid=task.getResult().getUser().getUid();
                            database
                                    .collection("user")
                                    .document(uid)
                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {   dialog.dismiss();
                                        startActivity(new Intent(signupActivity.this,MainActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(signupActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            //Toast.makeText(signupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {   dialog.dismiss();
                            Toast.makeText(signupActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.createNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupActivity.this,loginActivity.class));
            }
        });

    }
}
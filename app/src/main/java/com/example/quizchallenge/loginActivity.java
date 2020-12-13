package com.example.quizchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizchallenge.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Login");
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Logging in...");

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(loginActivity.this,MainActivity.class));
            finish();
        }

       binding.createNewbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             String email, pass;
             email = binding.emailBox.getText().toString();
             pass= binding.passwordBox.getText().toString();
             dialog.show();
             auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {   dialog.dismiss();
                         startActivity(new Intent(loginActivity.this,MainActivity.class));
                         finish();

                     }
                     else
                     {   dialog.dismiss();
                         Toast.makeText(loginActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });

           }
       });

     binding.submitButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(loginActivity.this,signupActivity.class));
         }
     });
    }

}
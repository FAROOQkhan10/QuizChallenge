package com.example.quizchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.util.Freezable;
import android.os.Bundle;

import com.example.quizchallenge.databinding.ActivityResultBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class resultActivity extends AppCompatActivity {
    ActivityResultBinding binding;
    int Points=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityResultBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         int correctAns= getIntent().getIntExtra("correct",0);
         int totalQuestions= getIntent().getIntExtra("total",0);
         long points = correctAns*Points;

         binding.score.setText(String.format("%d/%d",correctAns,totalQuestions));
         binding.earnedCoins.setText(String.valueOf(points));

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection("user")
                .document(FirebaseAuth.getInstance().getUid())
                 .update("coins", FieldValue.increment(points));

    }
}
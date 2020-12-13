package com.example.quizchallenge;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizchallenge.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class profileFragment extends Fragment {


    TextView name,email;
    FirebaseAuth auth;
    FirebaseFirestore database;
    String userId;

    public profileFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = v.findViewById(R.id.Nameid);
        email = v.findViewById(R.id.Emailid);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        userId = auth.getCurrentUser().getUid();
        DocumentReference documentReference = database.collection("user").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
              name.setText(value.getString("name"));
              email.setText(value.getString("email"));
            }
        });

        return v;
    }


}
package com.example.quizchallenge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.quizchallenge.databinding.ActivityMainBinding;
import com.example.quizchallenge.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
  ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new homeFragment());
        transaction.commit();


       binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
           @Override
           public boolean onItemSelect(int i) {
               FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                switch (i)
                {
                    case 0:
                        transaction.replace(R.id.content,new homeFragment());
                        transaction.commit();
                        break;
                    case 1:
                        transaction.replace(R.id.content,new LeaderboardFragment());
                        transaction.commit();
                        break;

                    case 2:
                        transaction.replace(R.id.content,new WalletFragment());
                        transaction.commit();
                        break;

                    case 3:
                        transaction.replace(R.id.content,new profileFragment());
                        transaction.commit();
                        break;
                }
               return false;
           }
       });

               }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


}
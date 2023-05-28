package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.fitnessapp.databinding.ActivityFeedBinding;
import com.google.android.material.navigation.NavigationBarView;
public class FeedActivity extends AppCompatActivity {
    private ActivityFeedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        getSupportFragmentManager().beginTransaction().replace(binding.myFrame.getId(),new HomeFragment());
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
               if(item.getItemId() == R.id.home){
                   getSupportFragmentManager().beginTransaction().replace(binding.myFrame.getId(),new HomeFragment()).commit();
                   return true;
               } else if (item.getItemId() == R.id.profile) {
                   getSupportFragmentManager().beginTransaction().replace(binding.myFrame.getId(),new ProfileFragment()).commit();
                   return true;
               }else {
                   return false;
               }

            }
        });
        setContentView(view);
    }
}
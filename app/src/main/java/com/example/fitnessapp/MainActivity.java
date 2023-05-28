package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.databinding.ActivityMainBinding;
import com.example.fitnessapp.repository.FirebaseRepo;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseRepo firebaseRepo = new FirebaseRepo();
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        checkUser();
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseRepo.login(binding.editTextEmail.getText().toString(),binding.editTextPassword.getText().toString(),getApplicationContext()).observe((LifecycleOwner) getLifecycle(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseRepo.register(binding.editTextEmail.getText().toString(),binding.editTextPassword.getText().toString(),getApplicationContext());

            }
        });



    }
    private void checkUser(){
        if(firebaseRepo.getUser() == true){
            Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);

        }
    }
}
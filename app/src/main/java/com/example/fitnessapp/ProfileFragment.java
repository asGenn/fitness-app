package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.databinding.FragmentHomeBinding;
import com.example.fitnessapp.databinding.FragmentProfileBinding;
import com.example.fitnessapp.repository.FirebaseRepo;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private FirebaseRepo firebaseRepo = new FirebaseRepo();

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                firebaseRepo.signOut();
            }
        });
        return view;
    }
}
package com.example.fitnessapp.repository;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.fitnessapp.FeedActivity;
import com.example.fitnessapp.models.Meals;
import com.example.fitnessapp.models.Repast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FirebaseRepo {
    private FirebaseAuth mAuth;
    private FirebaseFirestore _firestore;
    private Meals meals;
    private MutableLiveData<Boolean> isLogged;
    public FirebaseRepo() {
        mAuth = FirebaseAuth.getInstance();
        _firestore = FirebaseFirestore.getInstance();
    }
    public MutableLiveData<Boolean> register(String email, String password, Context context){
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context,"Kayıt başarılı",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,FeedActivity.class);
                context.startActivity(intent);
                isLogged.postValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                isLogged.postValue(false);
            }
        });
        return isLogged;

    }
    public MutableLiveData<Boolean> login(String email, String password, Context context){
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context,"Giriş başarılı",Toast.LENGTH_SHORT).show();
                isLogged.postValue(true);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                isLogged.postValue(false);
            }
        });
        return isLogged;

    }
    public void createMeal(Meals meals, Context context){
        _firestore.collection("Yemekler").document(mAuth.getUid()).set(meals).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Başarılı bir şekilde database'e eklendi", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void signOut(){
        mAuth.signOut();
    }
    public boolean getUser(){
        if (mAuth.getCurrentUser() != null){
            return true;
        }
        return false;
    }
}

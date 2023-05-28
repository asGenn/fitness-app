package com.example.fitnessapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fitnessapp.databinding.FragmentHomeBinding;
import com.example.fitnessapp.models.Meals;
import com.example.fitnessapp.models.Repast;
import com.example.fitnessapp.repository.FirebaseRepo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private String selectedRepast;
    private String selectedMeal;

    private int kahvalti = 0, ogle = 0, aksam = 0;
    private FirebaseFirestore mFirestore;
    private FirebaseAuth currentUser ;

    public HomeFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        addFoodToSpinner();
        foods();
        kcalCalculator();
        return view;

    }

    public String addFoodToSpinner(){

        ArrayList<String> meal = new ArrayList<>();
        meal.add("Seçiniz");
        meal.add("Kahvaltı");
        meal.add("Öğle Yemeği");
        meal.add("Akşam Yemeği");
        ArrayAdapter<String> arrayMealAdapter;
        arrayMealAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, meal);
        binding.spinnerOgun.setAdapter(arrayMealAdapter);
        binding.spinnerOgun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRepast = adapterView.getItemAtPosition(i).toString();
                if(selectedRepast.equals("Kahvaltı")){
                    ArrayList<String> food = new ArrayList<>();
                    food.add("Seçiniz");
                    food.add("Kaşar Peyniri: kcal, 404, 100 g");
                    food.add("Krem Peynir: kcal, 349, 100 g");
                    food.add("Haşlanmış Yumurta:kcal, 155, 100 g");
                    food.add("Patates Kızartması: kcal, 280, 100 g");
                    food.add("Beyaz Peynir: kcal, 289, 100 g");
                    food.add("Beyaz Ekmek: kcal, 69, 1 dilim");
                    food.add("Çavdar Ekmeği: kcal, 60, 1 dilim");
                    food.add("Kepek Ekmeği: kcal, 53, 1 dilim");
                    ArrayAdapter<String> arrayFoodAdapter;
                    arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                    binding.spinnerOgun2.setAdapter(arrayFoodAdapter);

                } else if (selectedRepast.equals("Öğle Yemeği") || selectedRepast.equals("Akşam Yemeği")) {
                    ArrayList<String> food = new ArrayList<>();
                    food.add("Seçiniz");
                    food.add("Kuru Fasulye: kcal, 94, 100 g");
                    food.add("Havuçlu Bezelye: kcal, 48, 100 g");
                    food.add("Humus: kcal, 177, 100 g");
                    food.add("Pişmiş Enginar: kcal, 53, 100 g");
                    food.add("Hünkar Beğendi: kcal, 174, 100 g");
                    food.add("Kıymalı Pide: kcal, 186, 100 g");
                    food.add("Patates Püresi: kcal, 83, 100 g");
                    food.add("Pişmiş Kereviz: kcal, 26, 100 g");
                    food.add("Adana Kebap: kcal, 240, 100 g");
                    food.add("Bulgur Pilavı: kcal, 215, 100 g");
                    food.add("Sucuklu Kaşarlı Pide: kcal, 305, 100 g");
                    food.add("Lazanya: kcal, 132, 100 g");
                    food.add("Tereyağlı Pirinç Pilavı: kcal, 185, 100 g");
                    food.add("Kuzu Tandır: kcal, 150, 100 g");
                    food.add("Kıymalı Makarna: kcal, 130, 100 g");
                    food.add("Karışık Pizza: kcal, 185, 100 g");
                    food.add("Zeytinyağlı Yaprak Sarma: kcal, 185, 100 g");
                    food.add("Peynirli Makarna: kcal, 140, 100 g");
                    food.add("Tavuklu Salata: kcal, 48, 100 g");
                    food.add("Kıymalı Dolma: kcal, 80, 100 g");
                    food.add("Zeytinyağlı Taze Fasulye: kcal, 50, 100 g");
                    food.add("Fırında Tavuk: kcal, 138, 100 g");
                    food.add("Karnıyarık: kcal, 134, 100 g");
                    food.add("Beyaz Peynir: kcal, 289, 100 g");
                    food.add("Beyaz Ekmek: kcal, 69, 1 dilim");
                    food.add("Çavdar Ekmeği: kcal, 60, 1 dilim");
                    food.add("Kepek Ekmeği: kcal, 53, 1 dilim");
                    food.add("Zeytinyağlı Dolma: kcal, 175, 100 g");
                    food.add("Çıkolatalı Pasta: kcal, 431, 1 dilim");
                    food.add("Bisküvi: kcal, 418, 100 gr");
                    food.add("Dondurma: kcal, 193, 3 top");
                    food.add("Kola: kcal, 59, 100 ml");
                    food.add("Meyveli Soda: kcal, 46, 100 ml");
                    food.add("Soğuk Çay: kcal, 30, 100 ml");
                    food.add("Şekersiz Çay: kcal, 3, 100 ml");
                    food.add("Şekersiz Sade Kahve: kcal, 9, 100 ml");
                    ArrayAdapter<String> arrayFoodAdapter;
                    arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                    binding.spinnerOgun2.setAdapter(arrayFoodAdapter);
                    
                }  else {
                ArrayList<String> food = new ArrayList<>();
                food.add("Lütfen İlk Önce Öğün Seçiniz");
                ArrayAdapter<String> arrayFoodAdapter;
                arrayFoodAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, food);
                binding.spinnerOgun2.setAdapter(arrayFoodAdapter);
            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Öğün Seçiniz", Toast.LENGTH_SHORT).show();
            }
        });
        return selectedRepast;

    }
    public String getSelectedMeal(){
        binding.spinnerOgun2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMeal = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"Herhangi birşey seçilmedi.",Toast.LENGTH_SHORT).show();
            }
        });
        return selectedMeal;
    }

    public void foods() {


        binding.spinnerOgun2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Yemek Seçilemedi", Toast.LENGTH_SHORT).show();
            }
        });

        selectedMeal = addFoodToSpinner();

        binding.buttonYemekEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (currentUser.getUid() != null) {


                    Map<String, Object> foodData = new HashMap<>();
                    foodData.put("Meal", selectedMeal);
                    foodData.put("Food", selectedRepast);

                    mFirestore.collection("Yemekler").document(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete( Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    List<Map<String, Object>> foodList = (List<Map<String, Object>>) document.get("foodList");
                                    if (foodList == null) {
                                        foodList = new ArrayList<>();
                                    }
                                    foodList.add(foodData);

                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("foodList", foodList);

                                    mFirestore.collection("Yemekler").document(currentUser.getUid())
                                            .set(userData, SetOptions.merge())
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), "Eklendi", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure( Exception e) {
                                                    Toast.makeText(getActivity(), "Ekleme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    // Yeni kullanıcı belgesi oluştur
                                    Map<String, Object> userData = new HashMap<>();
                                    List<Map<String, Object>> foodList = new ArrayList<>();
                                    foodList.add(foodData);
                                    userData.put("foodList", foodList);

                                    mFirestore.collection("Yemekler").document(currentUser.getUid())
                                            .set(userData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), "Eklendi", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure( Exception e) {
                                                    Toast.makeText(getActivity(), "Ekleme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void kcalCalculator() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        ArrayList<Integer> kahvalti_kcal = new ArrayList<>();
        ArrayList<Integer> ogle_kcal = new ArrayList<>();
        ArrayList<Integer> aksam_kcal = new ArrayList<>();

        binding.buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kahvalti = 0;
                ogle = 0;
                aksam = 0;
                mFirestore.collection("Yemekler").document(mAuth.getUid())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    if (documentSnapshot.exists()) {
                                        List<Map<String, Object>> foodList = (List<Map<String, Object>>) documentSnapshot.get("foodList");

                                        if (foodList != null) {
                                            kahvalti_kcal.clear();
                                            ogle_kcal.clear();
                                            aksam_kcal.clear();

                                            for (Map<String, Object> food : foodList) {

                                                    String mealName = (String) food.get("Food");
                                                    String foodName = (String) food.get("Meal");
                                                    String[] foodArray = foodName.split(", ");
                                                    String numericValue = foodArray[1];
                                                    int cal = Integer.parseInt(numericValue);

                                                    if (mealName.equals("Kahvaltı")) {
                                                        kahvalti_kcal.add(cal);
                                                    } else if (mealName.equals("Öğle Yemeği")) {
                                                        ogle_kcal.add(cal);
                                                    } else if (mealName.equals("Akşam Yemeği")) {
                                                        aksam_kcal.add(cal);
                                                    }

                                            }


                                                for (int kcal : kahvalti_kcal) {
                                                    kahvalti += kcal;
                                                }
                                                for (int kcal : ogle_kcal) {
                                                    ogle += kcal;
                                                }
                                                for (int kcal : aksam_kcal) {
                                                    aksam += kcal;
                                                }
                                                binding.textViewTarih.setText(LocalDate.now().toString());

                                                binding.texViewCalBilgi.setText(
                                                        "Kahvaltıda yedikleriniz toplam " + kahvalti + " kaloridir.\n" +
                                                                "Öğle yemeğinde yedikleriniz toplam " + ogle + " kaloridir. \n" +
                                                                "Akşam yemeğinde yedikleriniz toplam " + aksam + " kaloridir.");

                                        } else {
                                            binding.texViewCalBilgi.setText("Hiç yemek eklenmemiş");
                                        }
                                    } else {
                                        binding.texViewCalBilgi.setText("Kullanıcıya ait veri bulunamadı");
                                    }
                                } else {

                                }
                            }
                        });
            }
        });
    }

}
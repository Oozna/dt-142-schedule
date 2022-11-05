package com.example.schedule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.schedule.databinding.ActivityHomeBinding;
import com.example.schedule.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    Fragment homeFragment;
    Fragment scheduleFragment;
    public String amountOfShifts;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        amountOfShifts = "9";
        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        replaceFragment(homeFragment);

        //Funktion hämta data
        Shift s = new Shift(0, LocalDate.of(2022,11,4), LocalTime.of(11,0), LocalTime.of(14,0), "Servitör");

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(homeFragment);
                    break;
                case R.id.schedule:
                    replaceFragment(scheduleFragment);
                    break;
                case R.id.more:
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private ArrayList<Shift> getShifts(){
        ArrayList<Shift> shifts = new ArrayList<Shift>();
        //shifts.add(new Shift(0, new Date(), new DateTime(), "Servitör"));
        return shifts;
    }
}
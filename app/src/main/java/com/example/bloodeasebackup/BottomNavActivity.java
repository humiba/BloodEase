package com.example.bloodeasebackup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodeasebackup.databinding.ActivityBottomNavBinding;
import com.example.bloodeasebackup.fragments.HomeFragment;
import com.example.bloodeasebackup.fragments.NotificationsTabFragment;
import com.example.bloodeasebackup.fragments.ProfileFragment;

public class BottomNavActivity extends AppCompatActivity {

    ActivityBottomNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String userEmail = getIntent().getStringExtra("signInEmail");
        Bundle bundle=new Bundle();
        bundle.putString("signInEmail", userEmail);
        HomeFragment homeFragment=new HomeFragment();
        homeFragment.setArguments(bundle);
        replaceFragment(homeFragment);

        binding.bototmNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home_nav) {
                replaceFragment(homeFragment);
            } else if (itemId == R.id.notifications_nav) {
                replaceFragment(new NotificationsTabFragment());
            } else if (itemId == R.id.profile_nav) {
                replaceFragment(new ProfileFragment());
            }

            return true;
        });

    }
    public String getUserEmail() {
        return getIntent().getStringExtra("signInEmail");
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
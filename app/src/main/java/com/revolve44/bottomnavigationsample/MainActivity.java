package com.revolve44.bottomnavigationsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.revolve44.bottomnavigationsample.ui.dashboard.DashboardFragment;
import com.revolve44.bottomnavigationsample.ui.home.HomeFragment;
import com.revolve44.bottomnavigationsample.ui.notifications.NotificationsFragment;

public class MainActivity extends AppCompatActivity {
    final Fragment fragment1 = new HomeFragment();
    final Fragment fragment2 = new DashboardFragment();
    final Fragment fragment3 = new NotificationsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).addToBackStack(null).commit();
        }

        fm.beginTransaction().replace(R.id.fragment_container, fragment3, "3").hide(fragment2).commit();
        fm.beginTransaction().replace(R.id.fragment_container, fragment2, "2").hide(fragment3).commit();
        fm.beginTransaction().replace(R.id.fragment_container,fragment1, "1").commit();


    }

    //switcher of fragmnets, he help for switching without loss filled form in fragments
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).replace(R.id.fragment_container,fragment1, "1").commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).replace(R.id.fragment_container, fragment2, "2").commit();
                    active = fragment2;
                    return true;
                //beginTransaction().hide(fragment1).hide(fragment3).show(fragment2).commit()

                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).replace(R.id.fragment_container, fragment3, "3").commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };
}

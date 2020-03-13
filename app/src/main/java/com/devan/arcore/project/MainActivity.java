package com.devan.arcore.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.devan.arcore.project.ui.main.SectionsPagerAdapter;



public class MainActivity extends AppCompatActivity {

    private boolean tru=false;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void secrete(View view) {
        count+=1;
        if(tru=false){
        new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tru=true;
            }

            @Override
            public void onFinish() {
                tru=false;
                count=0;
            }
        };
        }
        else {
            if (count==10){
                Intent intent=new Intent(this,SettingsActivity.class);
                view.getContext().startActivity(intent);
                count=0;
            }
        }
    }
}
package com.devan.arcore.project.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devan.arcore.project.R;

import com.devan.arcore.project.RecycleView.product.productset;
import com.devan.arcore.project.RecycleView.product;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;



import java.util.List;

public class ScrollingActivityProduct extends AppCompatActivity {

    private boolean mUserRequestedInstall = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initializing
        TextView textView=findViewById(R.id.scrollingText);
        ImageView imageView=findViewById(R.id.image_scrolling_top);
        List<productset> productsetList= product.getProductItemData();
        int id= productsetList.get(getIntent().getIntExtra("id",0)).getId();

        setTitle(productsetList.get(getIntent().getIntExtra("id",0)).getTitle());
        imageView.setImageResource(productsetList.get(getIntent().getIntExtra("id",0)).getImage());
        textView.setText(productsetList.get(getIntent().getIntExtra("id",0)).getDescription());






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollingActivityProduct.this,AR_activity.class);
                intent.putExtra("raw",id);
                view.getContext().startActivity(intent);

            }

        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //back animation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finishAfterTransition();
            return true;
        }
        return false;
    }
}

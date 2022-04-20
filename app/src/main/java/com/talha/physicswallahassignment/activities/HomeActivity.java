package com.talha.physicswallahassignment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.widget.Toast;

import com.talha.physicswallahassignment.R;

public class HomeActivity extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_home);
        fragmentContainerView = findViewById(R.id.recycler_fragment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(listener -> Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show());


    }
}
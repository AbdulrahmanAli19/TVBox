package com.example.tvbox.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tvbox.R;
import com.example.tvbox.databinding.ActivityMainBinding;
import com.example.tvbox.pojo.ShowModul;
import com.example.tvbox.pojo.services.ScrapeData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<ShowModul> showModuls = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ScrapeData scrapeData = new ScrapeData();
        scrapeData.execute();

    }
}
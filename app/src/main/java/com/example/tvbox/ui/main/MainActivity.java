package com.example.tvbox.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tvbox.R;
import com.example.tvbox.databinding.ActivityMainBinding;
import com.example.tvbox.pojo.modules.ShowModule;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        final ShowListAdapter listAdapter = new ShowListAdapter();
        try {
            viewModel.execute();
        }catch (Exception ex){
            Log.d(TAG, "onCreate: "+ex);
        }

        viewModel.mutableLiveData.observe(this, new Observer<ArrayList<ShowModule>>() {
            @Override
            public void onChanged(ArrayList<ShowModule> showModules) {
                Log.d(TAG, "onChanged: called");
                listAdapter.setArrayList(showModules);
            }
        });
        binding.showRecyclerView.setAdapter(listAdapter);
        binding.showRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
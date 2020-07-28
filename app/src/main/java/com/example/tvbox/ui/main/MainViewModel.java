package com.example.tvbox.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvbox.pojo.data.ScrapeData;
import com.example.tvbox.pojo.modules.ShowModule;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private ScrapeData scrapeData = new ScrapeData();

    public MutableLiveData<ArrayList<ShowModule>> mutableLiveData = new MutableLiveData<>();

    public void getShowData(){
        scrapeData.execute();
        mutableLiveData.setValue(getDataFromWeb());
    }

    private ArrayList<ShowModule> getDataFromWeb() {
        return scrapeData.getShowList();
    }
}

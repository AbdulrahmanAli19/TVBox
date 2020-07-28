package com.example.tvbox.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvbox.pojo.data.ScrapeData;
import com.example.tvbox.pojo.modules.ShowModule;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    public MutableLiveData<ArrayList<ShowModule>> mutableLiveData = new MutableLiveData<>();
    private ScrapeData scrapeData = new ScrapeData();

    public void execute() throws InterruptedException {
        scrapeData.execute();
        boolean isEmpty = true ;
        while (isEmpty){
            if (scrapeData.getShowList().size() == 0){
                TimeUnit.MICROSECONDS.sleep(10);
            }else {
                isEmpty = false;
            }
        }
        mutableLiveData.setValue(getDataFromWeb());
    }

    private ArrayList<ShowModule> getDataFromWeb() {
        return scrapeData.getShowList();
    }
}

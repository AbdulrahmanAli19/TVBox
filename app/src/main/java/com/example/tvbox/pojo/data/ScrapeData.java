package com.example.tvbox.pojo.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tvbox.pojo.modules.ShowModule;
import com.example.tvbox.pojo.services.translation.ResponseData;
import com.example.tvbox.pojo.services.translation.Translator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScrapeData extends AsyncTask<Void, Void, Void> {
    private static final String TAG = ScrapeData.class.getSimpleName();
    private ArrayList<ShowModule> showList = new ArrayList<>();
    private Translator translator = new Translator();
    private Translator translator1 = new Translator();
    private boolean isDataReady = false ;
    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.teleman.pl/program-tv/stacje/Eleven-Sports-1";
        try {
            final Document document = Jsoup.connect(url).get();
            for (Element doc : document.select("ul.stationItems li")) {
                Element element, element2, element3;
                String show, title, time;
                if (doc.className().equals("ad")) {
                    continue;
                } else {
                    show = ".detail a";
                    title = ".detail .genre";
                    time = "." + doc.className().replace(" ", ".") + " em";
                    element = doc.select(show).first();
                    element2 = doc.select(title).first();
                    element3 = doc.select(time).first();
                    showList.add(new ShowModule(element.text(), element2.text(), element3.text()));
                    translator.translate(element.text().replace("#",""), "en");
                    translator1.translate(element2.text(), "en");
                    TimeUnit.MICROSECONDS.sleep(100);
                }
            }
            boolean isEmpty = true;
            while (isEmpty) {
                if (translator.getListOfMovies().size() == 0 || translator1.getListOfMovies().size() == 0) {
                    TimeUnit.MICROSECONDS.sleep(1500);
                } else {
                    isEmpty = false;
                }
            }
            for (int i = 0; i <= showList.size(); i++) {
                String translatedName = translator.getListOfMovies()
                        .get(i).getResponseData().getTranslatedText();
                String translatedTitle = translator1.getListOfMovies()
                        .get(i).getResponseData().getTranslatedText();
                String title = showList.get(i).getTitle();
                String time = showList.get(i).getTime();
                showList.set(i, new ShowModule(translatedName, translatedTitle, time));
            }
            isDataReady = true ;
        } catch (Exception ex) {
            Log.d(TAG, "doInBackground: " + ex);
        }
        return null;
    }

    public ArrayList<ShowModule> getShowList() {
        return showList;
    }

    public boolean isDataReady() {
        return isDataReady;
    }
}


package com.example.tvbox.pojo.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tvbox.pojo.modules.ShowModule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class ScrapeData extends AsyncTask<Void,Void,Void> {
    private static final String TAG = ScrapeData.class.getSimpleName();
    private ArrayList<ShowModule> showList = new ArrayList<>();
    @Override
    protected Void doInBackground(Void... voids) {
        String url =
                "https://www.teleman.pl/program-tv/stacje/Eleven-Sports-1";
        try {
            final Document document = Jsoup.connect(url).get();
            Element element, element2, element3;
            String selector = "ul.stationItems li";
            String show, title, time;
            for (Element doc : document.select(selector)) {
                if (doc.className().equals("cat-spo with-photo")) {
                    show = ".detail a";
                    title = ".detail .genre";
                    time = "." + doc.className().replace(" ", ".") + " em";
                    element = doc.select(show).first();
                    element2 = doc.select(title).first();
                    element3 = doc.select(time).first();
                    showList.add(new ShowModule(element.text(), element2.text(), element3.text()));
                } else if(doc.className().equals("cat-dok")) {
                    show = ".detail a";
                    title = ".detail .genre";
                    time = "." + doc.className().replace(" ", ".") + " em";
                    element = doc.select(show).first();
                    element2 = doc.select(title).first();
                    element3 = doc.select(time).first();
                    showList.add(new ShowModule(element.text(), element2.text(), element3.text()));

                }else {
                    continue;
                }
            }
            for (ShowModule s : showList){
                Log.d(TAG, "doInBackground: "+s.getName()+s.getTime()+s.getTitle());
            }
        } catch (Exception ex) {
            Log.d(TAG, "doInBackground: " + ex);
        }
        return null;
    }

    public ArrayList<ShowModule> getShowList() {
        return showList;
    }

}


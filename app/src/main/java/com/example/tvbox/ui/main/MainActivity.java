package com.example.tvbox.ui.main;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tvbox.R;
import com.example.tvbox.databinding.ActivityMainBinding;
import com.example.tvbox.pojo.ShowModul;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<ShowModul> showModuls = new ArrayList<>();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new connection().execute();
    }

    public class connection extends AsyncTask<Void, Void, Void> {

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://www.teleman.pl/program-tv/stacje/Eleven-Sports-1";
            try {
                final Document document = Jsoup.connect(url).get();
                String selector = "ul.stationItems li";
                String show = ".detail a";
                String title = ".detail .genre";
                String time;
                Element element;
                Element element2;
                Element element3;
                for (Element doc : document.select(selector)) {
                    if (doc.className().equals("ad")) {
                        continue;
                    } else {
                        time = "." + doc.className().replace(" ", ".") + " em";
                        element = doc.select(show).first();
                        element2 = doc.select(title).first();
                        element3 = doc.select(time).first();
                        showModuls.add(new ShowModul(element.text(), element2.text(), element3.text()));
                    }
                }
                System.out.println("done");
            } catch (Exception ex) {
                System.out.println("onCreate: " + ex);

            }
            return null;
        }
    }
}
package com.example.tvbox.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.tvbox.R;
import com.example.tvbox.databinding.ActivityMainBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    final String url = "https://www.osn.com/en-eg/explore/channels/om1/osn-movies-first";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        new connection().execute();

    }

    public class connection extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                final Document document = Jsoup.connect(url).get();
                for(Element row : document.select("div.col-md-8.left-section")){
                    if (row.select("nth-of-type(4)").text().equals("")){

                    }
                }
            } catch (Exception ex) {
                Log.d(TAG, "onCreate: " + ex);
            }
            return null;
        }
    }
}
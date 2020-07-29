package com.example.tvbox.pojo.services.translation;

public class TranslateModule {
    String translatedText;
    Double match;

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public void setMatch(Double match) {
        this.match = match;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public Double getMatch() {
        return match;
    }
}
